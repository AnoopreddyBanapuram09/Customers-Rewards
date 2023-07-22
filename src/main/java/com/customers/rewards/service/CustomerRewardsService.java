package com.customers.rewards.service;

import com.customers.rewards.exception.CustomerRewardsException;
import com.customers.rewards.model.CustomerRewardResponse;
import com.customers.rewards.model.RewardPerMonth;
import com.customers.rewards.dao.CustomerPurchaseDao;
import com.customers.rewards.model.CustomerPurchase;
import com.customers.rewards.model.CustomerPurchaseRequest;
import com.customers.rewards.service.interfaces.ICustomerRewardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
@Service
public class CustomerRewardsService implements ICustomerRewardsService {

    private final Logger logger = LoggerFactory.getLogger(CustomerRewardsService.class);

    public static final String EXCEPTION_LOG = "Error {} ";

    public static final String REQUEST_FAILED_LOG_WHILE_PURCHASING = "Error while saving purchase request";

    private final CustomerPurchaseDao customerPurchaseDao;

    @Autowired
    public CustomerRewardsService(CustomerPurchaseDao customerPurchaseDao) {
        this.customerPurchaseDao = customerPurchaseDao;
    }

    /*
     * This method maps CustomerPurchaseRequest body to customerPurchase which saves to Database
     *
     * @return CustomerPurchase
     */
    @Override
    public CustomerPurchase createCustomerPurchase(@Valid CustomerPurchaseRequest customerPurchaseRequest, String customerId) throws Exception {
        try {
            CustomerPurchase customerPurchase = new CustomerPurchase();
            customerPurchase.setAmount(customerPurchaseRequest.getTotalAmount());
            customerPurchase.setCustomerId(customerId);
            customerPurchase.setPurchaseDate(customerPurchaseRequest.getPurchaseDate());
            customerPurchase.setReward(totalRewardPoints(customerPurchaseRequest.getTotalAmount().intValue()));
            return customerPurchaseDao.save(customerPurchase);
        } catch (Exception exception) {
            logger.error(EXCEPTION_LOG, exception.getMessage());
            throw new CustomerRewardsException(HttpStatus.INTERNAL_SERVER_ERROR, REQUEST_FAILED_LOG_WHILE_PURCHASING);
        }
    }

    /*
     * This method calculates RewardsPoints and return TotalRewardsPoints
     *
     */
    private int totalRewardPoints(int amount) {
        int rewardPoints = 0;
        if (amount <= 50) {
            return rewardPoints;
        } else if (amount <= 100) {
            rewardPoints += (amount - 50);
        } else {
            rewardPoints += 50;
            rewardPoints += (amount - 100) * 2;
        }
        return rewardPoints;
    }

    /*
     * This method retrieves RewardPoints of a particular customer
     */
    @Override
    public CustomerRewardResponse getCustomerRewardPoints(String customerId) throws CustomerRewardsException {
        CustomerRewardResponse customerRewardResponse = new CustomerRewardResponse();
        try {
            List<RewardPerMonth> rewardPerMonthList = new ArrayList<>();
            List<CustomerPurchase> customerPurchase = customerPurchaseDao.findByCustomerId(customerId);
            if (!customerPurchase.isEmpty()) {
                LocalDate date = LocalDate.now().minusMonths(3);
                customerPurchase = customerPurchase.stream()
                        .filter(customer -> customer.getPurchaseDate().getMonth() >= date.getMonthValue())
                        .collect(Collectors.toList());
                Map<Integer, List<CustomerPurchase>> purchaseOrderGroupBy = customerPurchase.stream()
                        .collect(Collectors.groupingBy(rewards -> rewards.getPurchaseDate().getMonth()));

                for (Map.Entry<Integer, List<CustomerPurchase>> customerPurchaseGroup : purchaseOrderGroupBy.entrySet()) {
                    RewardPerMonth rewardPerMonth = new RewardPerMonth();
                    rewardPerMonth.setMonth(new DateFormatSymbols().getMonths()[customerPurchaseGroup.getKey()]);
                    rewardPerMonth.setTotalRewards(customerPurchaseGroup.getValue().stream().mapToInt(CustomerPurchase::getReward).sum());
                    rewardPerMonthList.add(rewardPerMonth);
                }
            }
            customerRewardResponse.setRewardTotal(customerPurchase.stream().mapToInt(CustomerPurchase::getReward).sum());
            customerRewardResponse.setRewardPerMonth(rewardPerMonthList);
            return customerRewardResponse;

        } catch (Exception exception) {
            logger.error(EXCEPTION_LOG, exception.getMessage());
            throw new CustomerRewardsException(HttpStatus.INTERNAL_SERVER_ERROR, REQUEST_FAILED_LOG_WHILE_PURCHASING);
        }
    }

}
