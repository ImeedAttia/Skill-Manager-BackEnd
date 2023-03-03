package com.soprahr.skillmanager.app.responses;

import java.util.List;

import com.soprahr.skillmanager.app.entities.DepartmentEntity;
import com.soprahr.skillmanager.app.entities.RoleEntity;

public class UserResponses {
	private String userId;
	private String firstname;
	private String lastname;
	private String email;
	private String degree;
	private String job_title;
	private RoleEntity role;
	private List<DepartmentEntity> departments;

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
	public List<DepartmentEntity> getDepartments() {
		return departments;
	}
	public void setDepartments(List<DepartmentEntity> departments) {
		this.departments = departments;
	}

}
