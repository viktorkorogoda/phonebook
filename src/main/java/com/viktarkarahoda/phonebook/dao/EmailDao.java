package com.viktarkarahoda.phonebook.dao;

import java.util.List;

import com.viktarkarahoda.phonebook.entity.Email;

public interface EmailDao {

	int insertEmail(Email email);
	
	List<Email> getEmails(int idContact);
	
	List<Integer> getEmailIdsForIdContact(int idContact);
	
	int deleteEmailById(int idEmail);
	
	int updateEmail(Email email);
}
