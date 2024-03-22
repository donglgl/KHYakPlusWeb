package com.admin.layout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.admin.layout.entity.kit;



@Repository
public interface kitRepository extends JpaRepository<kit, Long> {

}
