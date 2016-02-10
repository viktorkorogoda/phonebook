package com.viktarkarahoda.phonebook.dao;

import java.util.List;

import com.viktarkarahoda.phonebook.entity.Phone;

public interface PhoneDao {
	
	int insertPhone(Phone phone);
	List<Phone> getPhones(int idContact);
		
	List<Integer> getPhoneIdsForIdContact(int idContact);
	
	int deletePhoneById(int idContact);
	
	int updatePhone(Phone phone);

}
