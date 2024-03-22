package com.admin.layout.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.layout.apilogin.DTO.AddressDTO;
import com.admin.layout.repository.AddressRepository;
import  com.admin.layout.repository.memberRepository;
import com.admin.layout.vo.Address;
import com.admin.layout.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private memberRepository memberRepository;

    @PostMapping("/save-address")
    public ResponseEntity<?> saveAddress(@RequestBody AddressDTO addressDTO, HttpSession session) {
        log.info("AddressDTO: " + addressDTO);
        Long memNum = (Long) session.getAttribute("login_memNum");
        log.info("Session login_memNum: " + memNum);

        if (memNum == null) {
            log.error("login_memNum is null in session");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        Optional<Member> user = memberRepository.findById(memNum);
        if (!user.isPresent()) {
            log.error("User with memNum " + memNum + " not found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        Member member = user.get();

        // 유효성 검사: 주소 값 확인
        if (addressDTO.getAddAddr() == null || addressDTO.getAddAddr().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address cannot be null or empty");
        }

        // 새로운 주소 객체를 생성하여 데이터베이스에 저장합니다.
        Address newAddress = new Address();
        newAddress.setMember(member);
        newAddress.setAddAddr(addressDTO.getAddAddr());
        newAddress.setAddDetail(addressDTO.getAddDetail());
        newAddress.setAddCode(addressDTO.getAddCode());
        newAddress.setAddName(member.getMemName());
        newAddress.setField("N");

        addressRepository.save(newAddress);

        // 응답 데이터에 주소 정보를 포함시킵니다.
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Address saved successfully");
        response.put("addName", newAddress.getAddName());
        response.put("addAddr", newAddress.getAddAddr());
        response.put("addDetail", newAddress.getAddDetail());
        response.put("addCode", newAddress.getAddCode());
        response.put("field", newAddress.getField());

        return ResponseEntity.ok(response);
    }
}