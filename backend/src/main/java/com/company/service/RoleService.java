package com.company.service;

import java.util.ArrayList;
import java.util.List;

import com.company.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.model.Role;
import com.company.repository.RoleRepository;


@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public Role findById(Long id) {
    return this.roleRepository.getReferenceById(id);
  }

  public List<Role> findByName(String name) {
	return this.roleRepository.findByName(name);
  }

  public List<Role> getAll(){
    return this.roleRepository.findAll();
  }

  public void update(Role role){
    this.roleRepository.save(role);
  }

}
