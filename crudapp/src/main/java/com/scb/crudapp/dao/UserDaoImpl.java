package com.scb.crudapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.scb.crudapp.entity.User;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private JdbcTemplate template;

	public int saveUser(User user) {
		return template.update("insert into users (name,email,password,country) "
				+ "values (?,?,?,?)",user.getName(),user.getEmail(),user.getPassword()
				,user.getCountry());
	}

	public List<User> getAllUsers() {
		return template.query("select * from users", 
				BeanPropertyRowMapper.newInstance(User.class));
	}

	public User getUserById(int id) {
		try {
			User user= template.queryForObject("select * from users where id=?",
					BeanPropertyRowMapper.newInstance(User.class),id);
			return user;
		}
		catch(Exception e) {
			return null;
		}
	}

	public int updateUser(User user, int id) {
		if(getUserById(id)!=null)
		{
			return template.update("update users set name=?, email=?, password=?,"
					+ "country=? where id=?", user.getName(),user.getEmail(),
					user.getPassword(),user.getCountry(),id);
		}
		return 0;
	}

	public int deleteUser(int id) {
		return template.update("delete from users where id = ?",id);
	}

}
