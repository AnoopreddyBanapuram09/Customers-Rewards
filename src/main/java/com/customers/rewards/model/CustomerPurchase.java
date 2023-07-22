package com.customers.rewards.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
@Entity
@Table
public class CustomerPurchase {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String customerId;

	@Column
	private Float amount;

	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "MM/dd/yyy")
	private Date purchaseDate;

	@Column
	private int reward;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}
}
