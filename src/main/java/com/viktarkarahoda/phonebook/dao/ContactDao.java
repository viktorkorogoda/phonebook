package com.viktarkarahoda.phonebook.dao;

import java.util.List;

import com.viktarkarahoda.phonebook.entity.Contact;

public interface ContactDao {
	
	int insertContact(Contact contact);
	
	Contact getContact(int idContact);  
	
	List<Contact> getContactList();  
	
	int updateContact(Contact contact);
	
	
}
