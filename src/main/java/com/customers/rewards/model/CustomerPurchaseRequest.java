package com.customers.rewards.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
public class CustomerPurchaseRequest {

	@NotNull
	private Float totalAmount;

	@JsonFormat(pattern = "MM/dd/yyy")
	@NotNull
	private Date purchaseDate;

	public Float getTotalAmount() { return totalAmount;}

	public void setTotalAmount(Float totalAmount) { this.totalAmount = totalAmount; }

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
