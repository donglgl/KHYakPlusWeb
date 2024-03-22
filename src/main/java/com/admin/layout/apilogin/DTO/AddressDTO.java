package com.admin.layout.apilogin.DTO;


import com.admin.layout.vo.Address;
import com.admin.layout.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long addNum;
    private Long memNum; // 회원 번호
    private String addName; // 주소명
    private Integer addCode; // 우편번호
    private String addAddr; // 주소
    private String addDetail; // 상세주소
    private String field; // 분야
    private String addMemo; // 메모

    public static AddressDTO fromEntity(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddNum(address.getAddNum());
        addressDTO.setMemNum(address.getMember().getMemNum());
        addressDTO.setAddName(address.getAddName());
        addressDTO.setAddCode(address.getAddCode());
        addressDTO.setAddAddr(address.getAddAddr());
        addressDTO.setAddDetail(address.getAddDetail());
//        addressDTO.setAddPh(address.getAddPh());
        addressDTO.setField(address.getField());
        addressDTO.setAddMemo(address.getAddMemo());
        return addressDTO;
    }

    public Address toEntity(Member member) {
        Address address = new Address();
        address.setAddNum(this.addNum);
        address.setMember(member);
        address.setAddName(this.addName);
        address.setAddCode(this.addCode);
        address.setAddAddr(this.addAddr);
        address.setAddDetail(this.addDetail);
        
        address.setField(this.field);
        address.setAddMemo(this.addMemo);
        return address;
    }
}