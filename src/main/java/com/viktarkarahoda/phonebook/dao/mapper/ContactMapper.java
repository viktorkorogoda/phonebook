package com.viktarkarahoda.phonebook.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.viktarkarahoda.phonebook.entity.Contact;

public class ContactMapper implements RowMapper<Contact> {

	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contact contact = new Contact();
		contact.setIdContact(rs.getInt("idContact"));
		contact.setFirstName(rs.getString("firstName"));
		contact.setMiddleName(rs.getString("middleName"));
		contact.setLastName(rs.getString("lastName"));
		contact.setBirthDate(rs.getString("birthDate"));
		contact.setAddress(rs.getString("address"));
		return contact;
	}
}
