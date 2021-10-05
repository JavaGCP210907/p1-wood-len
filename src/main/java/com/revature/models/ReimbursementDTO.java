package com.revature.models;

public class ReimbursementDTO {

	private int userId;
	private String type; 
	private int amount;
	private String description;
	private int rId;
	private String status;

	public ReimbursementDTO() {
		super();
	}

	public ReimbursementDTO(int userId) {
		super();
		this.userId = userId;
	}

	public ReimbursementDTO(int userId, int rId, String status) {
		super();
		this.userId = userId;
		this.rId = rId;
		this.status = status;
	}

	public ReimbursementDTO(int userId, String type, int amount, String description) {
		super();
		this.userId = userId;
		this.type = type;
		this.amount = amount;
		this.description = description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
