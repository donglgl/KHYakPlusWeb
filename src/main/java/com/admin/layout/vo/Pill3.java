package com.admin.layout.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pill3 {
	
		private Integer numOfRows;
		private Integer pageNo;
	 	private String ITEM_SEQ; //품번
	    private String VALID_TERM; //유통기한
	    private String EE_DOC_DATA;	//효과효능
	    private String UD_DOC_DATA;	//용법용량
	    private String NB_DOC_DATA;	//주의사항
	    private String MATERIAL_NAME; //전성분

}
