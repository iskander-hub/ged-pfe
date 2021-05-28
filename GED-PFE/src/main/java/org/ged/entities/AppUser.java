package org.ged.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class AppUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
	@Column(unique=true)
  private String username;
  private String password;
  @ManyToMany(fetch = FetchType.EAGER)
  private Set<AppRole> roles= new HashSet<AppRole>();
}
