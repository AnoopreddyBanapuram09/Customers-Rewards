package com.customers.rewards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
@RestController
public class IsAliveController {

    private final Logger log = LoggerFactory.getLogger(IsAliveController.class);

    public static final String LOG_REQUEST= "Received request for IsAliveController";

    public static final String IS_ALIVE_CONTROLLER_RESPONSE= "Customers Rewards App is up and alive";

    @GetMapping(path = "/isAlive",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> isAlive(){

        log.info(LOG_REQUEST);

        return new ResponseEntity<>(IS_ALIVE_CONTROLLER_RESPONSE, HttpStatus.OK);
    }

}
