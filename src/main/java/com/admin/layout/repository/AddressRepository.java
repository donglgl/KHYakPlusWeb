package com.admin.layout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.layout.vo.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {



	

}
