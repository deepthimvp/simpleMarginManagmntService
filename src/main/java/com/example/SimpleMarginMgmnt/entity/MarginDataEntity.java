package com.example.SimpleMarginMgmnt.entity;


import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Component
@Entity
@Table(name = "margin_data")
public class MarginDataEntity {
	
	
	/*
	 * @Column(name = "fileInstanceId") private int fileInstanceId;
	 * 
	 * @Column(name = "instanceRowId") private int instanceRowId;
	 */

	@Id
	@Column(name = "margin_order") 
	private int marginOrder;
	
	public int getMarginOrder() {
		return marginOrder;
	}
	public void setMarginOrder(int marginOrder) {
		this.marginOrder = marginOrder;
	}
	@Column(name = "instruction")
	private String instruction;
	@Column(name = "base_ccy")
	private String baseCcy;
	@Column(name = "term_ccy")
	private String termCcy;
	@Column(name = "trader_tier")
	private String traderTier;
	@Column(name = "from_amt")
	private Integer fromAmount;
	@Column(name = "to_amt")
	private Integer toAmount;
	@Column(name = "amt_ccy")
	private String amtCcy;
	
	public String getAmtCcy() {
		return amtCcy;
	}
	public void setAmtCcy(String amtCcy) {
		this.amtCcy = amtCcy;
	}
	@Column(name = "bid_operator")
	private char bidOperator;
	@Column(name = "bid_modifier")
	private Double bidModifier;
	@Column(name = "ask_operator")
	private char askOperator;
	@Column(name = "ask_modifier")
	private Double askModifier;
	@Column(name = "remarks")
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
	public Double getBidModifier() {
		return bidModifier;
	}
	public void setBidModifier(Double bidModifier) {
		this.bidModifier = bidModifier;
	}
	public char getAskOperator() {
		return askOperator;
	}
	public void setAskOperator(char askOperator) {
		this.askOperator = askOperator;
	}
	public Double getAskModifier() {
		return askModifier;
	}
	public void setAskModifier(Double askModifier) {
		this.askModifier = askModifier;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	

	

}
