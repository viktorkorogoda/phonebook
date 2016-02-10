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

import com.viktarkarahoda.phonebook.dao.EmailDao;
import com.viktarkarahoda.phonebook.dao.mapper.EmailMapper;
import com.viktarkarahoda.phonebook.entity.Email;

@Repository
public class EmailDaoImpl implements EmailDao{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int insertEmail(Email email) {
		String SQL = "Insert into Email (idContact, idEmail,type, defaultemail, email) VALUES(:idContact, EMAIL_SEQ.nextval,:type, :defaultemail, :email)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idContact", email.getIdContact());
		namedParameters.addValue("type", email.getType());
		namedParameters.addValue("email", email.getAddress());
		namedParameters.addValue("defaultemail", email.getIsDefault() ? "Y": "N");
		return namedParameterJdbcTemplate.update(SQL, namedParameters);
	}

	@Override
	public List<Email> getEmails(int idContact) {
		String SQL = "SELECT e.idContact, e.Type, e.email, e.DefaultEmail, e.idEmail FROM Contacts c join Email e on c.idContact = e.idContact Where c.idContact = :idContact";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idContact", idContact);
		List<Email> emailList = namedParameterJdbcTemplate.query(SQL, namedParameters, new EmailMapper());
		return ListUtils.emptyIfNull(emailList);
	}

	@Override
	public List<Integer> getEmailIdsForIdContact(int idContact) {
		String SQL = "SELECT e.idEmail FROM Contacts c join Email e on c.idContact = e.idContact Where c.idContact = :idContact";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idContact", idContact);
		List<Integer> emailIdsList = namedParameterJdbcTemplate.query(SQL, namedParameters, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("idEmail");
			}
		});
		return ListUtils.emptyIfNull(emailIdsList);
	}

	@Override
	public int deleteEmailById(int idEmail) {
		String SQL = "DELETE From EMAIL where idEmail = :idEmail";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idEmail", idEmail);
		return namedParameterJdbcTemplate.update(SQL, namedParameters);
		
	}

	@Override
	public int updateEmail(Email email) {
		String SQL = "Update Email"
					+ " SET type = :type, email = :email, defaultemail = :defaultemail"
					+ " WHERE idContact = :idContact and idEmail = :idEmail";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idContact", email.getIdContact());
		namedParameters.addValue("type", email.getType());
		namedParameters.addValue("email", email.getAddress());
		namedParameters.addValue("idEmail", email.getIdEmail());
		namedParameters.addValue("defaultemail", email.getIsDefault() ? "Y": "N");
		return namedParameterJdbcTemplate.update(SQL, namedParameters);
	}
	
	

}
