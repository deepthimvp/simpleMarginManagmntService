package com.example.SimpleMarginMgmnt.domain;

import lombok.Data;

@Data
public class MarginData {
	private int fileInstanceId;
	private int marginOrder;
	public int getMarginOrder() {
		return marginOrder;
	}
	public void setMarginOrder(int marginOrder) {
		this.marginOrder = marginOrder;
	}
	private String instruction;
private String baseCcy;
private String termCcy;
private String traderTier;
private Integer fromAmount;
private Integer toAmount;
private String amtCcy;

public String getAmtCcy() {
	return amtCcy;
}
public void setAmtCcy(String amtCcy) {
	this.amtCcy = amtCcy;
}
private char bidOperator;
private String bidModifier;
private char askOperator;
private String askModifier;
private String remarks;
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
public Integer getFromAmount() {
	return fromAmount;
}
public void setFromAmount(Integer fromAmount) {
	this.fromAmount = fromAmount;
}
public Integer getToAmount() {
	return toAmount;
}
public void setToAmount(Integer toAmount) {
	this.toAmount = toAmount;
}
public char getBidOperator() {
	return bidOperator;
}
public void setBidOperator(char bidOperator) {
	this.bidOperator = bidOperator;
}
public String getBidModifier() {
	return bidModifier;
}
public void setBidModifier(String bidModifier) {
	this.bidModifier = bidModifier;
}
public char getAskOperator() {
	return askOperator;
}
public void setAskOperator(char askOperator) {
	this.askOperator = askOperator;
}
public String getAskModifier() {
	return askModifier;
}
public void setAskModifier(String askModifier) {
	this.askModifier = askModifier;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public int getFileInstanceId() {
	return fileInstanceId;
}
public void setFileInstanceId(int fileInstanceId) {
	this.fileInstanceId = fileInstanceId;
}


}

