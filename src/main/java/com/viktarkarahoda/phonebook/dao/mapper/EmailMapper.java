package com.viktarkarahoda.phonebook.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.viktarkarahoda.phonebook.entity.Email;

public class EmailMapper implements RowMapper<Email> {

	@Override
	public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
		Email email = new Email();
		email.setIdContact(rs.getInt("idContact"));
		email.setIdEmail(rs.getInt("idEmail"));
		email.setType(rs.getString("type"));
		email.setAddress(rs.getString("email"));
		email.setIsDefault("Y".equals(rs.getString("defaultEmail")));
		return email;
	}
}
