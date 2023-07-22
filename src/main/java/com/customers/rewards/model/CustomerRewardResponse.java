package com.customers.rewards.model;

import java.util.List;

/*
 *
 * Author @AnoopreddyBanapuram09
 *
 */
public class CustomerRewardResponse {

	private List<RewardPerMonth> rewardPerMonth;

	public List<RewardPerMonth> getRewardPerMonth() {
		return rewardPerMonth;
	}

	public void setRewardPerMonth(List<RewardPerMonth> rewardPerMonth) {
		this.rewardPerMonth = rewardPerMonth;
	}

	public int getRewardTotal() {
		return rewardTotal;
	}

	public void setRewardTotal(int rewardTotal) {
		this.rewardTotal = rewardTotal;
	}

	private int rewardTotal;

}
