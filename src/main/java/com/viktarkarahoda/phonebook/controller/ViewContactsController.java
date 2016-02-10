package com.viktarkarahoda.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.viktarkarahoda.phonebook.entity.Contact;
import com.viktarkarahoda.phonebook.service.ContactService;

@RestController
public class ViewContactsController {


	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/viewcontacts")
	public ModelAndView init() {
		ModelAndView model = new ModelAndView();
		List<Contact> contacts = contactService.getContactList();
		model.setViewName("viewcontacts");
		model.addObject("contacts", contacts);
		return model;
	}
}
