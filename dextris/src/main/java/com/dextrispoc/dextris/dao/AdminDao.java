package com.dextrispoc.dextris.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dextrispoc.dextris.entity.Admin;
@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {
	@Query(value = "SELECT * FROM admin  WHERE admin_name = :name", nativeQuery = true)
	public Admin getAdminByName(String name);
	
}
