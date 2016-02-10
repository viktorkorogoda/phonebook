package com.viktarkarahoda.phonebook.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.viktarkarahoda.phonebook.dao.PhoneDao;
import com.viktarkarahoda.phonebook.dao.mapper.PhoneMapper;
import com.viktarkarahoda.phonebook.entity.Phone;

@Repository
public class PhoneDaoImpl implements PhoneDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int insertPhone(Phone phone) {
		String SQL = "Insert into PHONE (idContact, idPhone,type, defaultphone, phonenumber) VALUES(:idContact, PHONE_SEQ.nextval, :type, :defaultphone, :phonenumber)";
		MapSqlParameterSource namedParamenters = new MapSqlParameterSource();
		namedParamenters.addValue("idContact", phone.getIdContact());
		namedParamenters.addValue("type", phone.getType());
		namedParamenters.addValue("phonenumber", phone.getNumber());
		namedParamenters.addValue("defaultphone", phone.getIsDefault() ? "Y" : "N");
		return namedParameterJdbcTemplate.update(SQL, namedParamenters);
	}

	@Override
	public List<Phone> getPhones(int idContact) {
		String SQL = "SELECT p.idContact, p.Type, p.phoneNumber, p.DefaultPhone, p.idPhone FROM Contacts c join Phone p on c.idContact = p.idContact where c.idContact = :idContact";
		MapSqlParameterSource namedParamenters = new MapSqlParameterSource();
		namedParamenters.addValue("idContact", idContact);
		List<Phone> phoneList = namedParameterJdbcTemplate.query(SQL, namedParamenters, new PhoneMapper());
		return ListUtils.emptyIfNull(phoneList);
	}

	@Override
	public List<Integer> getPhoneIdsForIdContact(int idContact) {
		String SQL = "SELECT p.idPhone FROM Contacts c join Phone p on c.idContact = p.idContact Where c.idContact = :idContact";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idContact", idContact);
		List<Integer> phoneIdsList = namedParameterJdbcTemplate.query(SQL, namedParameters, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("idPhone");
			}
		});
		return ListUtils.emptyIfNull(phoneIdsList);
	}

	@Override
	public int deletePhoneById(int idPhone) {
		String SQL = "DELETE From PHONE where idPhone = :idPhone";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idPhone", idPhone);
		return namedParameterJdbcTemplate.update(SQL, namedParameters);
	}

	@Override
	public int updatePhone(Phone phone) {
		String SQL = "Update PHONE SET type = :type, phonenumber = :phonenumber, defaultphone = :defaultphone WHERE idContact = :idContact and idPhone = :idPhone";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idContact", phone.getIdContact());
		namedParameters.addValue("type", phone.getType());
		namedParameters.addValue("phonenumber", phone.getNumber());
		namedParameters.addValue("idPhone", phone.getIdPhone());
		namedParameters.addValue("defaultphone", phone.getIsDefault() ? "Y" : "N");
		return namedParameterJdbcTemplate.update(SQL, namedParameters);
	}

}
