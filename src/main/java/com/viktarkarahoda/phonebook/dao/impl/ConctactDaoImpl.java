package com.viktarkarahoda.phonebook.dao.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.viktarkarahoda.phonebook.dao.ContactDao;
import com.viktarkarahoda.phonebook.dao.mapper.ContactMapper;
import com.viktarkarahoda.phonebook.entity.Contact;
import com.viktarkarahoda.phonebook.util.DateUtil;

@Repository
public class ConctactDaoImpl implements ContactDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int insertContact(Contact contact) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String SQL = "INSERT INTO Contacts (idContact, firstname, lastname, middlename, birthdate, address) "
				+ "VALUES (CONTACTS_SEQ.nextval, :firstname, :lastname, :middlename,  to_date(:birthdate,'dd.mm.yyyy'), :address)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("firstname", contact.getFirstName());
		namedParameters.addValue("lastname", contact.getLastName());
		namedParameters.addValue("middlename", contact.getMiddleName());
		namedParameters.addValue("birthdate", contact.getBirthDate());
		namedParameters.addValue("address", contact.getAddress());
		namedParameterJdbcTemplate.update(SQL, namedParameters, keyHolder, new String[] {"IDCONTACT"});
		return keyHolder.getKey().intValue();
	}

	@Override
	public Contact getContact(int idContact) {
		String SQL = "SELECT idContact, FirstName, LastName, MiddleName, BirthDate, Address FROM Contacts where Contacts.idContact = :idContact";;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idContact", idContact);
		List<Contact> contactList = namedParameterJdbcTemplate.query(SQL, namedParameters, new ContactMapper());
		if (CollectionUtils.isNotEmpty(contactList)) {
			return contactList.get(0);
		}
		return null;
	}

	@Override
	public List<Contact> getContactList() {
		String SQL = "SELECT idContact, FirstName, LastName, MiddleName, BirthDate, Address FROM Contacts";
		List<Contact> contactList = namedParameterJdbcTemplate.query(SQL, new ContactMapper());
		return ListUtils.emptyIfNull(contactList);
	}

	@Override
	public int updateContact(Contact contact) {
		String SQL = "UPDATE Contacts "
				+ "SET firstname = :firstname, "
				+ "lastname = :lastname, "
				+ "middlename = :middlename, "
				+ "birthdate = to_date(:birthdate,'dd.mm.yyyy'), "
				+ "address = :address "
				+ "where idcontact = :idcontact";
		contact.setBirthDate(DateUtil.dateConvert(contact.getBirthDate()));
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("firstname", contact.getFirstName());
		namedParameters.addValue("lastname", contact.getLastName());
		namedParameters.addValue("middlename", contact.getMiddleName());
		namedParameters.addValue("birthdate", contact.getBirthDate());
		namedParameters.addValue("address", contact.getAddress());
		namedParameters.addValue("idcontact", contact.getIdContact());
		return namedParameterJdbcTemplate.update(SQL, namedParameters);
	}
}
