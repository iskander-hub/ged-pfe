package org.ged.dao;


import org.ged.entities.AppRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface RoleRepository extends JpaRepository<AppRole, Long> {
   
	public AppRole findByRoleName(String roleName);
	
	@Query("select r from AppRole r where r.roleName like:x")
	public Page<AppRole> chercher(@Param("x")String mc, Pageable pageable);
}
