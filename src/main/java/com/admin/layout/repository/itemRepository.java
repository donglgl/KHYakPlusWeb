package com.admin.layout.repository;

import com.admin.layout.vo.item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface itemRepository extends JpaRepository<item, Long> {

	item findByItemName(String name);
    item findByItemNum(Long itemNum);
    Page<item> findAll(Pageable pageable);
}
