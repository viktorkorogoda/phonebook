package com.viktarkarahoda.phonebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viktarkarahoda.phonebook.dao.EmailDao;
import com.viktarkarahoda.phonebook.entity.Email;
import com.viktarkarahoda.phonebook.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	EmailDao emailDao;

	@Override
	public boolean addEmail(Email email) {
		int res = emailDao.insertEmail(email);
		return res > 0;
	}

	@Override
	public List<Email> getEmailList(int idContact) {
		List<Email> emailList = emailDao.getEmails(idContact);
		return emailList;
	}

	@Override
	public List<Integer> getEmailIdsForIdContact(int idContact) {
		List<Integer> emailIdsList = emailDao.getEmailIdsForIdContact(idContact);
		return emailIdsList;
	}

	@Override
	public boolean deleteEmailById(int idEmail) {
		int res = emailDao.deleteEmailById(idEmail);
		return res > 0;
	}

	@Override
	public boolean updateEmail(Email email) {
		int res = emailDao.updateEmail(email);
		return res > 0;
	}
	
	
}
