package com.customers.rewards.dao;

import java.util.List;

import com.customers.rewards.model.CustomerPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
@Repository
public interface CustomerPurchaseDao extends JpaRepository<CustomerPurchase, Long> {

	List<CustomerPurchase> findByCustomerId(String customerId);

}
