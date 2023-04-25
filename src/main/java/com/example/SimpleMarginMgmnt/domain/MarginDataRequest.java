package com.example.SimpleMarginMgmnt.domain;

import lombok.Data;

@Data
public class MarginDataRequest {
	private String instruction;
	private String baseCcy;
	private String termCcy;
	private String traderTier;
	private Integer amount;
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getBaseCcy() {
		return baseCcy;
	}
	public void setBaseCcy(String baseCcy) {
		this.baseCcy = baseCcy;
	}
	public String getTermCcy() {
		return termCcy;
	}
	public void setTermCcy(String termCcy) {
		this.termCcy = termCcy;
	}
	public String getTraderTier() {
		return traderTier;
	}
	public void setTraderTier(String traderTier) {
		this.traderTier = traderTier;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer fromAmount) {
		this.amount = fromAmount;
	}
	
}
