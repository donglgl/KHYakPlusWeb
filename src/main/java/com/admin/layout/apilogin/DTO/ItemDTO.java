package com.admin.layout.apilogin.DTO;


import com.admin.layout.vo.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ItemDTO {
    
    private Long itemNum;
    private String catName;
    private String catSubName;
    private String catSubName2;
    private String itemName; 
    private String itemImg;
    private String itemImgName;
    private String itemImgUrl;
    private String itemContent;
    private Long itemPrice;
    private String itemCnt;
    private String itemOldCnt;
    private String itemFun;
    private String itemToday;
    private String itemEng;
    private String itemType;
    
    // DTO에서 엔티티로 변환하는 메서드
    public item toEntity() {
        return new item(itemNum, catName, catSubName, catSubName2, itemName, itemImg, itemImgName,
                        itemImgUrl, itemContent, itemPrice, itemCnt, itemOldCnt, itemFun, itemToday,
                        itemEng, itemType);
    }
}
