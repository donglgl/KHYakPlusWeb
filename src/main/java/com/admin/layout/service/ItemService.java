package com.admin.layout.service;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.layout.vo.item;
import com.admin.layout.repository.itemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private itemRepository itemRepository;

    public item getItemByItemNum(Long itemNum) {
        log.info("Looking for item with ID: {}", itemNum);
        item item = itemRepository.findByItemNum(itemNum);
       
        return item;
    }
}