package com.soprahr.skillmanager.app.requests;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.soprahr.skillmanager.app.entities.DepartmentEntity;
import com.soprahr.skillmanager.app.entities.RoleEntity;

public class UserRequest {
	
	//@valid fel controller pour valider ces condition
	@NotBlank(message = "Ce Champs Ne doit etre null !")
	@NotEmpty(message = "Ce Champs Ne doit etre null !")
	@NotNull(message = "Ce Champs Ne doit etre null !")
	@Size(min = 3,message = "Ce champs doit avoir au moins 3 Caractere !")
	private String firstname;
	@NotBlank(message = "Ce Champs Ne doit etre null !")
	@NotEmpty(message = "Ce Champs Ne doit etre null !")
	@NotNull(message = "Ce Champs Ne doit etre null")
	@Size(min = 3,message = "Ce champs doit avoir au moins 3 Caractere !")
	private String lastname;
	@NotNull(message = "Ce Champs Ne doit etre null !")
	@Email(message = "Ce champs doit respecter le format email !")
	private String email;
	@NotBlank(message = "Ce Champs Ne doit etre null !")
	@NotEmpty(message = "Ce Champs Ne doit etre null !")
	@NotNull(message = "Ce Champs Ne doit etre null !")
	@Size(min = 8,message = "mot de passe doit avoir au minimum 8 caractere !")
	@Size(max = 12,message = "mot de passe doit avoir au max 12 caractere !")
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",message="Mot de passe doit avoir des lettres maj et des lettre miniscule")
	private String password;
	@NotNull(message = "Ce Champs Ne doit etre null !")
	@Email(message = "Ce champs doit respecter le format email !")
	private String degree;
	@NotNull(message = "Ce Champs Ne doit etre null !")
	@Email(message = "Ce champs doit respecter le format email !")
	private String job_title;
	private RoleEntity role;
	private List<Long> departments= new ArrayList<>();
	private List<Long> depIds = new ArrayList<>();

	
	//Getters and Setters
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
	public List<Long> getDepartments() {
		return departments;
	}
	public void setDepartments(List<Long> departments) {
		this.departments = departments;
	}
	public List<Long> getDepIds() {
		return depIds;
	}
	public void setDepIds(List<Long> depIds) {
		this.depIds = depIds;
	}
}
