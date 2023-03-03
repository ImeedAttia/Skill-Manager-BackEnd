package com.soprahr.skillmanager.app.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.soprahr.skillmanager.app.entities.DepartmentEntity;
import com.soprahr.skillmanager.app.entities.RoleEntity;

public class UserDto implements Serializable{

	
	private static final long serialVersionUID = 3473374445628358690L;

	private long id;
	private String userId;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String degree;
	private String job_title;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;
	private RoleEntity role;
	private List<DepartmentEntity> departments;
	private List<Long> depIds = new ArrayList<>();


	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public RoleEntity getRole() {
		return role;
	}
	public void setRole(RoleEntity role) {
		this.role = role;
	}
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	public List<DepartmentEntity> getDepartments() {
		return departments;
	}
	public void setDepartments(List<DepartmentEntity> departments) {
		this.departments = departments;
	}
	public List<Long> getDepIds() {
		return depIds;
	}
	public void setDepIds(List<Long> depIds) {
		this.depIds = depIds;
	}
	 
	
	
	

}
