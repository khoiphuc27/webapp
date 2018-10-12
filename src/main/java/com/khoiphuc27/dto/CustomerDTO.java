package com.khoiphuc27.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.khoiphuc27.model.titleEnum;
import com.khoiphuc27.validator.Phone;

public class CustomerDTO {
	int id;
	@Size(max=20)
	@NotNull
	private String name;
	@NotNull
	private String birthday;
	@Phone
	@NotNull
	private String phone;
	@Email
	@NotNull
	private String email;
	@NotNull
	private boolean gender = true;
	@Size(max=20)
	@NotNull
	private String address;
	@NotNull
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
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
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
