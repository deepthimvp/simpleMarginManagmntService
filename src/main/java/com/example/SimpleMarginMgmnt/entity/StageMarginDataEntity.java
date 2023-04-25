package com.example.SimpleMarginMgmnt.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Component
@Entity
@IdClass(StageMarginDataEntity.StageMarginDataCompositeKey.class)
@Table(name = "stg_margin_data")
public class StageMarginDataEntity {

	
	@Data
	public static class StageMarginDataCompositeKey implements Serializable {
		
		@GeneratedValue(generator = "INST_ID_SEQ", strategy = GenerationType.SEQUENCE)
		@SequenceGenerator(name = "INST_ID_SEQ", sequenceName = "INST_ID_SEQ",allocationSize=1)
		 @Column(name = "file_instnce_id") 
		private int fileInstanceId;
		 
		
		 @Column(name = "margin_order") private int marginOrder;

	}
	
	@Id
	private int fileInstanceId;
	 
	@Id
	private int marginOrder;

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
	private String bidOperator;
	@Column(name = "bid_modifier")
	private double bidModifier;
	@Column(name = "ask_operator")
	private String askOperator;
	@Column(name = "ask_modifier")
	private double askModifier;
	@Column(name = "remarks")
	private String remarks;
	
	

	public int getFileInstanceId() {
		return fileInstanceId;
	}

	public void setFileInstanceId(int fileInstanceId) {
		this.fileInstanceId = fileInstanceId;
	}

	public int getMarginOrder() {
		return marginOrder;
	}

	public void setMarginOrder(int fileRowId) {
		this.marginOrder = fileRowId;
	}

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

	public String getBidOperator() {
		return bidOperator;
	}

	public void setBidOperator(String bidOperator) {
		this.bidOperator = bidOperator;
	}

	public Double getBidModifier() {
		return bidModifier;
	}

	public void setBidModifier(Double bidModifier) {
		this.bidModifier = bidModifier;
	}

	public String getAskOperator() {
		return askOperator;
	}

	public void setAskOperator(String askOperator) {
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
