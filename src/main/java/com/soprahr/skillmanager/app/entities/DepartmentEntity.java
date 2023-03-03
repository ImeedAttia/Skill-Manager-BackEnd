package com.soprahr.skillmanager.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name="department")
public class DepartmentEntity implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7682628855924763040L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false,length = 50)
	private String domain;
	
	  @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.ALL
		      },
		      mappedBy = "departments")
		  @JsonIgnore
		  private List<UserEntity> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

}
