package com.viktarkarahoda.phonebook.service;

import java.util.List;

import com.viktarkarahoda.phonebook.entity.Contact;

public interface ContactService {

	boolean addNewContact(Contact contact);

	List<Contact> getContactList();
	
	Contact getContactById(int idContact);
	
	Contact editContact(Contact contact);
	
}
