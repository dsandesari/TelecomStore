
/*
 @author T139 Group C 
 */

package com.tcs.ilp.telecomstore.module3.dto;

public class RewardsBean {
	private int reward_id;
	private String reward_status;
	private int package_id;
	private String reward_date;
	private String _offers;
	
		public RewardsBean(int rewardId, String rewardStatus,String rewardDate, String offers) {
		super();
		reward_id = rewardId;
		reward_status = rewardStatus;
		
		reward_date = rewardDate;
		_offers = offers;
	}
		
		public RewardsBean(int rewardId, String productCode,int packageId, String rewardDate) {
		super();
		reward_id = rewardId;
		reward_status = productCode;
		reward_date = rewardDate;
		package_id=packageId;
	}
		public int getPackage_id() {
			return package_id;
		}
		public void setPackage_id(int packageId) {
			package_id = packageId;
		}
	public int getReward_id() {
			return reward_id;
	}

	public void setReward_id(int rewardId) {
		reward_id = rewardId;
	}
	public String getReward_status() {
		return reward_status;
	}
	public void setReward_status(String productCode) {
		reward_status = productCode;
	}
	public String getReward_date() {
		return reward_date;
	}
	public void setReward_date(String rewardDate) {
		reward_date = rewardDate;
	}
	public String getOffers() {
		return _offers;
	}
	public void setOffers(String offers) {
		_offers = offers;
	}
}
