package com.admin.layout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.layout.entity.wish;



@Repository
public interface wishRepository extends JpaRepository<wish, Long> {

}
