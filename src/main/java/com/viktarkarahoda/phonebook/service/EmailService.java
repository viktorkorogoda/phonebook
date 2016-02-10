package com.viktarkarahoda.phonebook.service;

import java.util.List;

import com.viktarkarahoda.phonebook.entity.Email;

public interface EmailService {

	boolean addEmail(Email email);
	
	List<Email> getEmailList(int idContact);

	List<Integer> getEmailIdsForIdContact(int idContact);
	
	boolean deleteEmailById(int idEmail);
	
	boolean updateEmail(Email email);
}
