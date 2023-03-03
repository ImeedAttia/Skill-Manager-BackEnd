package com.soprahr.skillmanager.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "user")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5637136290946094988L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50)
	private String firstname;

	@Column(nullable = false, length = 50)
	private String lastname;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false)
	private String encryptedPassword;

	@Column(nullable = true)
	private String degree;

	@Column(nullable = false)
	private String job_title;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "user_department", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "department_id") })
	private List<DepartmentEntity> departments = new ArrayList<>();

	@Transient
	private List<Long> depIds = new ArrayList<>();

	
	public List<Long> getDepIds() {
		return depIds;
	}

	public void setDepIds(List<Long> depIds) {
		this.depIds = depIds;
	}

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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

	public void addDepartment(DepartmentEntity dept) {
		this.departments.add(dept);
		dept.getUsers().add(this);
	}

	public void removeDepartment(long deptId) {
		DepartmentEntity dept = this.departments.stream().filter(t -> t.getId() == deptId).findFirst().orElse(null);
		if (dept != null) {
			this.departments.remove(dept);
			dept.getUsers().remove(this);
		}
	}

}
