package com.admin.layout.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;


@Data
@Entity
@Table(name = "KIT")
public class kit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NO")
    private Long no;

    @Column(name = "MEM_CAT")
    private String memCat;

    @Column(name = "MEM_NUM")
    private Long memNum;
    
    @Column(name = "DIVISION")
    private String division;    
    
    @Column(name = "KIT_NAME")
    private String kitName;
    
    @Column(name = "PACKAGING")
    private String packaging ;    

    @Column(name = "KIT_FUN")
    private String kitFun;

    @Column(name = "END_DATE")
    private String endDate;
    
	@Column(name = "MODIFY_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;

	@Column(name = "REMAIN_DATE")
	private int remainDate;
    
}
