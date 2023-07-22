package com.customers.rewards.controller;

import com.customers.rewards.model.CustomerRewardResponse;
import com.customers.rewards.model.RewardPerMonth;
import com.customers.rewards.service.interfaces.ICustomerRewardsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class CustomerRewardsControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerRewardsControllerTest.class);

    @InjectMocks
    private CustomerRewardsController customerRewardsController;

    @MockBean
    private ICustomerRewardsService customersRewardsService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void getRewards_SuccessEmptyList() throws Exception {

        Mockito.when(this.customersRewardsService.getCustomerRewardPoints("123"))
                .thenReturn(this.getResponse());
        MvcResult response = makeRequest();
        ObjectMapper mapper = new ObjectMapper();
        String strResponse = response.getResponse().getContentAsString();
        CustomerRewardResponse customerRewardResponse = null;
        try {
            customerRewardResponse = mapper.readValue(strResponse, CustomerRewardResponse.class);
        } catch (JsonProcessingException e){
            log.error("error while JsonProcessing", e);
        }
        Assert.assertNotNull(customerRewardResponse);
    }

    private MvcResult makeRequest() throws Exception {
        return this.mvc.perform(MockMvcRequestBuilders
                .get("/customer/123/rewards").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

    private CustomerRewardResponse getResponse(){
        CustomerRewardResponse response = new CustomerRewardResponse();
        response.setRewardTotal(50);
        List<RewardPerMonth> rewardPerMonthList = new ArrayList<>();
        RewardPerMonth month = new RewardPerMonth();
        month.setMonth("July");
        month.setTotalRewards(50);
        rewardPerMonthList.add(month);
        response.setRewardPerMonth(rewardPerMonthList);
        return response;
    }
}
