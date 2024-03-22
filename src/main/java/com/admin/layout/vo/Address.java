package com.admin.layout.vo;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_num")
    private Long addNum;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_num", nullable = false)
    private Member member; // Member 클래스와의 참조 관계를 설정합니다.
    @Column(name = "add_name", nullable = false)
    private String addName;

    @Column(name = "add_code", nullable = false)
    private Integer addCode;

    @Column(name = "add_addr", nullable = false)
    private String addAddr;

    @Column(name = "add_detail", nullable = false)
    private String addDetail;

//    @Column(name = "add_ph", nullable = false)
//    private String addPh;

    @Column(name = "Field", nullable = false, columnDefinition = "CHAR(1) default 'N'")
    private String field;

    @Column(name = "add_memo")
    private String addMemo;

 
}