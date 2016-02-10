package com.viktarkarahoda.phonebook.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.viktarkarahoda.phonebook.entity.Phone;

public class PhoneMapper  implements RowMapper<Phone> {

	@Override
	public Phone mapRow(ResultSet rs, int rowNum) throws SQLException {
		Phone phone = new Phone();
		phone.setIdContact(rs.getInt("idContact"));
		phone.setIdPhone(rs.getInt("idPhone"));
		phone.setType(rs.getString("type"));
		phone.setNumber(rs.getString("phoneNumber"));
		phone.setIsDefault("Y".equals(rs.getString("defaultPhone")));
		return phone;
	}
}
