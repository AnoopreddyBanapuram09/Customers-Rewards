package com.customers.rewards.controller;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IsAliveControllerTest {

    @Autowired
    private IsAliveController isAliveController;

    @Test
    public void test_isAlive() {

        ResponseEntity<Object> response = new ResponseEntity<>(
                "Customers Rewards App is up and alive", HttpStatus.OK);
        ResponseEntity<Object> responseEntity = isAliveController.isAlive();
        Assertions.assertEquals(response, responseEntity);

    }
}
