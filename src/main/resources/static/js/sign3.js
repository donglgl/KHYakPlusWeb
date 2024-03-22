
document
		.addEventListener(
				'DOMContentLoaded',
				function() {
					// '프로필 꾸미기' 버튼 클릭 이벤트 리스너
					document.getElementById('editProfileBtn').addEventListener(
							'click', function() {
								// 파일 입력 클릭 트리거
								document.getElementById('file-input').click();
							});

					// 파일 입력 변경 이벤트 리스너
					document
							.getElementById('file-input')
							.addEventListener(
									'change',
									function() {
										const file = this.files[0];
										if (file) {
											const reader = new FileReader();
											reader.onload = function(e) {
												// 프로필 사진 미리보기 업데이트
												document
														.getElementById('profile-pic').src = e.target.result;
											};
											reader.readAsDataURL(file);
										}
									});

					// 모달 관련 변수
					var modal = document.getElementById('profileModal'); // 모달
					var btn = document.getElementById('editProfileBtn'); // 모달
																			// 열기
																			// 버튼
					var span = document.getElementsByClassName('close')[0]; // 모달
																			// 닫기
																			// 버튼
																			// (X)

					// 모달 열기
					btn.onclick = function() {
						modal.style.display = 'block';
					}

					// 모달 닫기 (X 클릭)
					span.onclick = function() {
						modal.style.display = 'none';
					}

					// 모달 외부 클릭 시 모달 닫기
					window.onclick = function(event) {
						if (event.target == modal) {
							modal.style.display = 'none';
						}
					}

					// '저장하기' 버튼 클릭 이벤트
					var saveBtn = document.getElementById('saveProfileChanges');
					saveBtn.onclick = function() {
						var nicknameInput = document
								.getElementById('nicknameInput').value;
						var profileName = document
								.getElementById('profile-name');
						
						// 닉네임 업데이트
						if (nicknameInput.trim() != '') {
							profileName.textContent = nicknameInput;
							
						}

						// 프로필 사진 업데이트
						var profilePicInput = document
								.getElementById('profilePictureInput').files[0];
						if (profilePicInput) {
							var reader = new FileReader();
							reader.onload = function(e) {
								document.getElementById('profile-pic').src = e.target.result;
							}
							reader.readAsDataURL(profilePicInput);
						}
						// 모달 닫기
						modal.style.display = 'none';
					}
				});

function checkId() {
	var id = $('#memId').val(); // id값이 "memId"인 입력란의 값을 저장
	$.ajax({
		url : './checkId', // Controller에서 요청 받을 주소
		type : 'post', // POST 방식으로 전달
		data : {
			memId : id
		},
		success : function(check) { // 컨트롤러에서 넘어온 true값을 받는다
			if (check == true) { // check가 true이 아니면(=true일 경우) -> 사용 가능한 아이디
				$('.id_ok').css("display", "inline-block");
				$('.id_No').css("display", "none");
			} else { // check가 false일 경우 -> 이미 존재하는 아이디
				$('.id_No').css("display", "inline-block");
				$('.id_ok').css("display", "none");
			}
		},
		error : function(request, status, error) {

			alert("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
};

function checkPwd() {
	var pwd = $('#memPwd').val();
	var pwdch = $('#memPwdCheck').val();

	if (pwdch == pwd) {
		$('.pass_ok').css("display", "inline-block");
		$('.pass_no').css("display", "none");
	} else {
		$('.pass_no').css("display", "inline-block");
		$('.pass_ok').css("display", "none");
	}
};

function phnumber() {
	// 전화번호 자동으로 하이픈 넣기
	$(function() {

		$('#userPhone')
				.keydown(
						function(event) {
							var key = event.charCode || event.keyCode || 0;
							$text = $(this);
							if (key !== 8 && key !== 9) {
								if ($text.val().length === 3) {
									$text.val($text.val() + '-');
								}
								if ($text.val().length === 8) {
									$text.val($text.val() + '-');
								}
							}
							return (key == 8 || key == 9 || key == 46
									|| (key >= 48 && key <= 57) || (key >= 96 && key <= 105));
						})
	});
}




function checkNickname() {
	var nickname = $('#nickname').val(); // id값이 "memId"인 입력란의 값을 저장
	$.ajax({
		url : './nickNameCheck', // Controller에서 요청 받을 주소
		type : 'post', // POST 방식으로 전달
		data : {
			nickname : nickname
		},
		success : function(check) { // 컨트롤러에서 넘어온 true값을 받는다
			if (check == true) { // check가 true이 아니면(=true일 경우) -> 사용 가능한 아이디
				$('.nickname_ok').css("display", "inline-block");
				$('.nickname_no').css("display", "none");
			} else { // check가 false일 경우 -> 이미 존재하는 아이디
				$('.nickname_no').css("display", "inline-block");
				$('.nickname_ok').css("display", "none");
			}
		},
		error : function(request, status, error) {

			alert("code:" + request.status + "\n" + "message:"
					+ request.responseText + "\n" + "error:" + error);
		}
	});
};


function Postcode1() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 전체 주소 변수 선언
            let fullAddr = '';
            // 참고항목 변수
            let extraAddr = '';

            // 사용자가 도로명 주소를 선택했을 경우
            if (data.userSelectedType === 'R') {
                // 우편번호와 도로명 주소를 조합합니다.
                fullAddr = data.zonecode + ' ' + data.roadAddress;

                // 건물명이 있는 경우 추가합니다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물의 공동주택 여부에 따라 '아파트', '연립주택' 등을 추가합니다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 참고항목이 있으면 괄호 안에 추가합니다.
                extraAddr = extraAddr ? ' (' + extraAddr + ')' : '';
            } else { // 사용자가 지번 주소를 선택했을 경우
                // 우편번호와 지번 주소를 조합합니다.
                fullAddr = data.zonecode + ' ' + data.jibunAddress;
            }

            // 여기에서 참고항목을 포함시켜 '주소' 입력 필드에 설정합니다.
            fullAddr += extraAddr ? ' ' + extraAddr : '';

            console.log(fullAddr);
            
            // '주소' 입력 필드에는 우편번호, 주소(도로명 또는 지번), 참고항목을 설정합니다.
            document.getElementById("Postaddress").value = fullAddr;

            // '상세주소' 입력 필드로 포커스를 이동합니다.
            document.getElementById("PostLastaddress").focus();
        }
    }).open();
}
