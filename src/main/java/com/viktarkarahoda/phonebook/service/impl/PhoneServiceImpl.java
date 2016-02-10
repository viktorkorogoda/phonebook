package com.viktarkarahoda.phonebook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viktarkarahoda.phonebook.dao.PhoneDao;
import com.viktarkarahoda.phonebook.entity.Phone;
import com.viktarkarahoda.phonebook.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService{

	@Autowired
	PhoneDao phoneDao;
	
	@Override
	public boolean addPhone(Phone phone) {
		int res = phoneDao.insertPhone(phone);
		return res > 0;
	}

	@Override
	public List<Phone> getPhoneList(int idContact) {
		List<Phone> phoneList = phoneDao.getPhones(idContact);
		return phoneList;
	}

	@Override
	public List<Integer> getPhoneIdsForIdContact(int idContact) {
		List<Integer> phoneIdsList = phoneDao.getPhoneIdsForIdContact(idContact);
		return phoneIdsList;
	}

	@Override
	public boolean deletePhoneById(int idPhone) {
		int res = phoneDao.deletePhoneById(idPhone);
		return res > 0;
	}

	@Override
	public boolean updatePhone(Phone phone) {
		int res = phoneDao.updatePhone(phone);
		return res > 0;
	}

	
}
