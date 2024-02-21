package com.dextrispoc.dextris.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.dextrispoc.dextris.entity.User;
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
	
	@Query(value = "SELECT * FROM user  WHERE user_name = :name", nativeQuery = true)
	public User getUserByName(String name);
	
	@Modifying
	@Query(value = "DELETE FROM user WHERE user_name = :name", nativeQuery = true)
	public int deleteUserByName(String name);

}
