package com.viktarkarahoda.phonebook.entity;

import java.io.Serializable;

public class Email implements Serializable {
	
	private static final long serialVersionUID = -4307380945772993236L;
	
	private int idContact;
	private int idEmail;
	private String type;
	private String address;
	private boolean isDefault;

	public int getIdContact() {
		return idContact;
	}
	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}
	public int getIdEmail() {
		return idEmail;
	}
	public void setIdEmail(int idEmail) {
		this.idEmail = idEmail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String email) {
		this.address = email;
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
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + idContact;
		result = prime * result + idEmail;
		result = prime * result + (isDefault ? 1231 : 1237);
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
		Email other = (Email) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (idContact != other.idContact)
			return false;
		if (idEmail != other.idEmail)
			return false;
		if (isDefault != other.isDefault)
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
		return "Email [idContact=" + idContact + ", idEmail=" + idEmail + ", type=" + type + ", address=" + address + ", isDefault=" + isDefault + "]";
	}
	
	
}
