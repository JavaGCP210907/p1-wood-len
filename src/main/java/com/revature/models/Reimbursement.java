package com.revature.models;

import java.util.Date;

public class Reimbursement {
	
	private int id;
	
	private int amount;
	private String description;
	private Status status;
	private Type type;
	private Date submit_date;
	private Date resolved_date;

	public Reimbursement() {
		super();
	}

	public Reimbursement(int id, int amount, String description, Status status, Type type) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int amount, String description, Status status, Type type) {
		super();
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.type = type;
	}
	
	

	public Reimbursement(int id, int amount, String description, Status status, Type type, Date submit_date,
			Date resolved_date) {
		super();
		this.id= id;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.type = type;
		this.submit_date = submit_date;
		this.resolved_date = resolved_date;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", description=" + description + ", status=" + status
				+ ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Date getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(Date submit_date) {
		this.submit_date = submit_date;
	}

	public Date getResolve_date() {
		return resolved_date;
	}

	public void setResolve_date(Date resolved_date) {
		this.resolved_date = resolved_date;
	}

}
