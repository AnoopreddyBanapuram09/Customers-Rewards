package com.customers.rewards.service;

import com.customers.rewards.dao.CustomerPurchaseDao;
import com.customers.rewards.model.CustomerPurchase;
import com.customers.rewards.model.CustomerPurchaseRequest;
import com.customers.rewards.model.CustomerRewardResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerRewardsServiceTest {

    @InjectMocks
    private CustomerRewardsService customerRewardsService;
    @Mock
    private CustomerPurchaseDao customerPurchaseDao;

    @Test
    public void getCustomerRewardsPoints_Success() throws Exception {
        List<CustomerPurchase> customerPurchaseList = new ArrayList<>();
        customerPurchaseList.add(entityCustomerPurchase());
        Mockito.when(customerPurchaseDao.findByCustomerId("123")).thenReturn(customerPurchaseList);
        CustomerRewardResponse customerRewardsResponse = customerRewardsService.getCustomerRewardPoints("123");
        Assert.assertNotNull(customerRewardsResponse);
        assertEquals(customerRewardsResponse.getRewardTotal(), 50);
        assertEquals(customerRewardsResponse.getRewardPerMonth().get(0).getTotalRewards(), 50);
    }

    @Test
    public void getRewards_SuccessEmptyList() throws Exception {
        List<CustomerPurchase> customerPurchaseList = new ArrayList<>();
        Mockito.when(customerPurchaseDao.findByCustomerId("123")).thenReturn(customerPurchaseList);
        CustomerRewardResponse customerRewardsResponse = customerRewardsService.getCustomerRewardPoints("123");
        Assert.assertNotNull(customerRewardsResponse);
        assertEquals(customerRewardsResponse.getRewardTotal(), 0);
    }

    @Test
    public void saveCustomerPurchase_Test() throws Exception {
        Mockito.when(customerPurchaseDao.save(Mockito.any())).thenReturn(entityCustomerPurchase());
        CustomerPurchase customerPurchase = customerRewardsService.createCustomerPurchase(buildCustomerPurchaseRequest(), "123");
        Assert.assertNotNull(customerPurchase);
        assertEquals(customerPurchase.getAmount(), 100f);
        assertEquals(customerPurchase.getReward(), 50);
        assertEquals(customerPurchase.getCustomerId(), "123");
    }

    private CustomerPurchase entityCustomerPurchase() {
        CustomerPurchase customerPurchase = new CustomerPurchase();
        customerPurchase.setAmount(100f);
        customerPurchase.setReward(50);
        customerPurchase.setCustomerId("123");
        customerPurchase.setId(1L);
        customerPurchase.setPurchaseDate(new Date());
        return customerPurchase;
    }

    private CustomerPurchaseRequest buildCustomerPurchaseRequest() {
        CustomerPurchaseRequest request = new CustomerPurchaseRequest();
        request.setTotalAmount(100f);
        request.setPurchaseDate(new Date());
        return request;
    }

}
