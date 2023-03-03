package com.soprahr.skillmanager.app.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name="role")
public class RoleEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3114779715068988209L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRole;
	@Column(nullable = false)
	private String roleName;
	
	@OneToMany(mappedBy = "role",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<UserEntity> users;
	
	
	
	public long getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public String getTitre() {
		return roleName;
	}
	public void setTitre(String roleName) {
		this.roleName = roleName;
	}
}
