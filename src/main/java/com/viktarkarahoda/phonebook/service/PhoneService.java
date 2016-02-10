package com.viktarkarahoda.phonebook.service;

import java.util.List;

import com.viktarkarahoda.phonebook.entity.Phone;

public interface PhoneService{

	boolean addPhone(Phone phone);
	
	List<Phone> getPhoneList(int idContact);
	
	List<Integer> getPhoneIdsForIdContact(int idContact);
	
	boolean deletePhoneById(int idPhone);
	
	boolean updatePhone(Phone phone);
}
