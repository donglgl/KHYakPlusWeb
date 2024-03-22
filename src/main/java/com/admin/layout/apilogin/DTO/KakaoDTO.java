package com.admin.layout.apilogin.DTO;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class KakaoDTO {
	private long id;
	private String nickname;
	private String email;
//추가되는 정보는 추가적으로 필드를 생성하면 됩니다.
//왠만하면 카카오에서 넘어오는 변수들과 DTO 클래스의 변수명은 일치시키는 것이 좋습니다.
}
