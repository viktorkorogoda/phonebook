package com.viktarkarahoda.phonebook.entity;

import java.io.Serializable;

public class Phone implements Serializable {

	private static final long serialVersionUID = 4946852262863857027L;
	
	private int idContact;
	private int idPhone;
	private String type;
	private String number;
	private boolean isDefault;

	public int getIdContact() {
		return idContact;
	}
	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}
	public int getIdPhone() {
		return idPhone;
	}
	public void setIdPhone(int idPhone) {
		this.idPhone = idPhone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idContact;
		result = prime * result + idPhone;
		result = prime * result + (isDefault ? 1231 : 1237);
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Phone other = (Phone) obj;
		if (idContact != other.idContact)
			return false;
		if (idPhone != other.idPhone)
			return false;
		if (isDefault != other.isDefault)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Phone [idContact=" + idContact + ", idPhone=" + idPhone + ", type=" + type + ", number=" + number + ", isDefault=" + isDefault + "]";
	}
	
	
}
