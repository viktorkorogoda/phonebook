package com.viktarkarahoda.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.viktarkarahoda.phonebook.entity.Contact;
import com.viktarkarahoda.phonebook.service.ContactService;

@RestController
public class IndexController {

	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/index")
	public ModelAndView init() {
		ModelAndView model = new ModelAndView();
		List<Contact> contacts = contactService.getContactList();
		model.setViewName("index");
		model.addObject("contacts", contacts);
		return model;
	}

	@RequestMapping(value = "addNewContact", method = RequestMethod.POST)
	@ResponseBody
	public String addNewContact(@RequestBody Contact contact, BindingResult result) {
		contactService.addNewContact(contact);
		return "viewcontacts";
	}
	
	@RequestMapping(value = "getContactById", method = RequestMethod.GET)
	@ResponseBody
	public Contact getContactToEdit(@RequestParam("idContact") int idContact) {
		Contact contact = contactService.getContactById(idContact);
		return contact;
	}
	
	@RequestMapping(value = "editContact", method = RequestMethod.POST)
	@ResponseBody
	public String editContact(@RequestBody Contact contact, BindingResult result) {
		contactService.editContact(contact);
		return "viewcontacts";
	}
}
