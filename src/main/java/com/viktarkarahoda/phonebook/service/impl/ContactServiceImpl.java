package com.viktarkarahoda.phonebook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viktarkarahoda.phonebook.dao.ContactDao;
import com.viktarkarahoda.phonebook.entity.Contact;
import com.viktarkarahoda.phonebook.entity.Email;
import com.viktarkarahoda.phonebook.entity.Phone;
import com.viktarkarahoda.phonebook.service.ContactService;
import com.viktarkarahoda.phonebook.service.EmailService;
import com.viktarkarahoda.phonebook.service.PhoneService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;

	@Autowired
	private PhoneService phoneService;

	@Autowired
	private EmailService emailService;
	
	@Override
	public boolean addNewContact(Contact contact) {
		int id = contactDao.insertContact(contact);

		for (Phone phn : contact.getPhoneList()) {
			phn.setIdContact(id);
			phoneService.addPhone(phn);
		}
		for (Email eml : contact.getEmailList()) {
			eml.setIdContact(id);
			emailService.addEmail(eml);
		}

		return id > 0;
	}

	@Override
	public List<Contact> getContactList() {
		List<Contact> contactList = contactDao.getContactList();
		for (Contact contact : contactList) {
			int idContact = contact.getIdContact();

			List<Phone> phoneList = phoneService.getPhoneList(idContact);
			contact.setPhoneList(phoneList);
			List<Email> emailList = emailService.getEmailList(idContact);
			contact.setEmailList(emailList);
		}
		return contactList;
	}

	@Override
	public Contact getContactById(int idContact) {
		Contact contact = contactDao.getContact(idContact);
		List<Phone> phoneList = phoneService.getPhoneList(idContact);
		contact.setPhoneList(phoneList);
		List<Email> emailList = emailService.getEmailList(idContact);
		contact.setEmailList(emailList);
		return contact;
	}

	@Override
	public Contact editContact(Contact contact) {
		updatePhones(contact);
		updateEmails(contact);
		
		contactDao.updateContact(contact);
		return null;
	}

	private void updatePhones(Contact contact) {
		List<Integer> phoneIdsFromDB = phoneService.getPhoneIdsForIdContact(contact.getIdContact());

		List<Integer> phoneIdsFromUI = new ArrayList<Integer>();
		for (Phone phone : contact.getPhoneList()) {
			phoneIdsFromUI.add(phone.getIdPhone());
		}

		for (Integer idPhone : phoneIdsFromDB) {
			if (!phoneIdsFromUI.contains(idPhone) && idPhone != 0) {
				phoneService.deletePhoneById(idPhone);
			}
		}

		for (Phone phone : contact.getPhoneList()) {
			if (phone.getIdPhone() == 0) {
				phoneService.addPhone(phone);
			} else {
				phoneService.updatePhone(phone);
			}
		}
	}

	private void updateEmails(Contact contact) {
		List<Integer> emailIdsFromDB = emailService.getEmailIdsForIdContact(contact.getIdContact());

		List<Integer> emailIdsFromUI = new ArrayList<Integer>();
		for (Email email : contact.getEmailList()) {
			emailIdsFromUI.add(email.getIdEmail());
		}

		for (Integer idEmail : emailIdsFromDB) {
			if (!emailIdsFromUI.contains(idEmail) && idEmail != 0) {
				emailService.deleteEmailById(idEmail);
			}
		}

		for (Email email : contact.getEmailList()) {
			if (email.getIdEmail() == 0) {
				emailService.addEmail(email);
			} else {
				emailService.updateEmail(email);
			}
		}
	}
	


}
