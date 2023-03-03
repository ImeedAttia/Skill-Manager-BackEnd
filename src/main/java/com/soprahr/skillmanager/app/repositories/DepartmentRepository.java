package com.soprahr.skillmanager.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.soprahr.skillmanager.app.entities.DepartmentEntity;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long>{
	List<DepartmentEntity> findDepartmentByUsersId(Long UserId);
}
