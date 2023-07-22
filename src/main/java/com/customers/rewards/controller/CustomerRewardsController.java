package com.customers.rewards.controller;

import com.customers.rewards.model.CustomerPurchaseRequest;
import com.customers.rewards.service.interfaces.ICustomerRewardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
*
* Author @AnoopreddyBanapuram09
*
*/
@RestController
public class CustomerRewardsController {

    private final Logger log = LoggerFactory.getLogger(CustomerRewardsController.class);
    public static final String PURCHASE_REQUEST_LOG = "Received request to insert purchase request";
    public static final String REWARDS_REQUEST_LOG = "Received request to get reward points of a customer";
    public static final String CONTROLLER_LOG = "CustomerRewardsController ";
    public static final String POST_REQUEST_PATH = "/customer/{customerId}/purchase";
    public static final String GET_REQUEST_PATH = "/customer/{customerId}/rewards";

    @Autowired
    public ICustomerRewardsService customersRewardsService;

    /*
     * This POST endpoint saves reward points to DB and returns CustomerPurchase object as success response
     */
    @PostMapping(path = POST_REQUEST_PATH,
            consumes = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE},
            produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> postCustomerPurchase(
            @PathVariable(value = "customerId") String customerId,
            @Valid @RequestBody CustomerPurchaseRequest customerPurchaseRequest) throws Exception {
        log.info(CONTROLLER_LOG + PURCHASE_REQUEST_LOG);
        return new ResponseEntity<>(customersRewardsService.createCustomerPurchase(customerPurchaseRequest, customerId), HttpStatus.OK);
    }

    /*
     * This GET endpoint retrieves reward points of customer from DB and returns CustomerRewardResponse as success response
     */
    @GetMapping(path = GET_REQUEST_PATH,
            produces = {org.springframework.http.MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getCustomerPoints(@PathVariable String customerId)
            throws Exception {
        log.info(CONTROLLER_LOG + REWARDS_REQUEST_LOG);
        return new ResponseEntity<>(customersRewardsService.getCustomerRewardPoints(customerId), HttpStatus.OK);
    }
}
