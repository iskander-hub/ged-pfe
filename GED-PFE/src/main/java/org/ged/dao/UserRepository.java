package org.ged.dao;


import org.ged.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface UserRepository extends JpaRepository<AppUser, Long> {
	
	public AppUser findByUsername(String username);
	
	@Query("select u from AppUser u where u.username like:x")
	public Page<AppUser> chercher(@Param("x")String mc, Pageable pageable);

}
