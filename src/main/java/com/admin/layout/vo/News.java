package com.admin.layout.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class News {

    private String subject;
    private String url;
}
