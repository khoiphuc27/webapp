package com.khoiphuc27.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.khoiphuc27.model.titleEnum;
import com.khoiphuc27.validator.Phone;

public class CustomerDTO {
	private int id;
	
	@Size(max=255)
	@NotEmpty(message="Name must not be empty")
	private String name;
	
	@NotNull(message="Birthday must not be empty")
//	@DateTimeFormat(iso=ISO.DATE, pattern="yyyy-MM-dd")
	private Date birthday;
	
	@NotEmpty(message="Phone must not be empty")
	@Phone
	private String phone;
	
	@Email
	@NotEmpty(message="Email must not be empty")
	private String email;
	
//	@NotEmpty
	private boolean gender = true;
	
	@Size(max=4000)
	@NotEmpty(message="Address must not be empty")
	private String address;
	
//	@NotEmpty
	private titleEnum title = titleEnum.MR;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public titleEnum getTitle() {
		return title;
	}

	public void setTitle(titleEnum title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
