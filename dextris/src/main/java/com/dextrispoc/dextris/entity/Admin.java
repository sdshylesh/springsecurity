package com.dextrispoc.dextris.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
	@Id
	@GeneratedValue	
	int adminId;
	String adminName;
	String role;
	String adminPwd;
	String dob;
	String phoneNum;

}
