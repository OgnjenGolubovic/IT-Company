package com.company.repository;

import com.company.model.EmailBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailBlacklistRepository extends JpaRepository<EmailBlacklist, Integer> {

}
