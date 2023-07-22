package com.customers.rewards.service.interfaces;

import javax.validation.Valid;

import com.customers.rewards.exception.CustomerRewardsException;
import com.customers.rewards.model.CustomerRewardResponse;
import com.customers.rewards.model.CustomerPurchase;
import com.customers.rewards.model.CustomerPurchaseRequest;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
public interface ICustomerRewardsService {

	CustomerPurchase createCustomerPurchase(@Valid CustomerPurchaseRequest customerPurchaseRequest, String customerId) throws Exception;

	CustomerRewardResponse getCustomerRewardPoints(String customerId) throws CustomerRewardsException;

}
