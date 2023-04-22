package com.company.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.model.Role;
import com.company.repository.RoleRepository;


@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public Role findById(Long id) {
    Role auth = this.roleRepository.getReferenceById(id);
    return auth;
  }

  public List<Role> findByName(String name) {
	List<Role> roles = this.roleRepository.findByName(name);
    return roles;
  }


}
