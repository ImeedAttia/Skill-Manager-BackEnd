package com.soprahr.skillmanager.app.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.soprahr.skillmanager.app.entities.RoleEntity;

@Repository
public interface RoleRepository extends  CrudRepository<RoleEntity, Long>{

}
