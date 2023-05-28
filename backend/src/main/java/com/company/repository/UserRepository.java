package com.company.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.model.User;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);
    @Transactional
    void deleteByUsername(String email);
}
