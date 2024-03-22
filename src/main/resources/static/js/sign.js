
$(function(){
	
	let mem_gen = "";  //성별 전역변수
	
	//성별 체크
	$(".mem_gen").on("click",function(){
		//라디오 클릭시 성별이 mem_gen 변수에 초기화;
		mem_gen = $(this).val();
		
		console.log(mem_gen); // M or F;
		
	})
	
	
	
	$("#membership").on("click", function(){
		
		alert("회원가입 하시겠습니까?");
		
		let mem_id = $("#mem_id").val();		 // 아이디
		let mem_pwd = $("#mem_pwd").val();		 // 패스워드
		let mem_name = $("#mem_name").val();		// 닉네임
		let mem_ph = $("#mem_ph").val();		 // 휴대폰 번호
		let mem_birthday = $("#mem_birthday").val();		 // 생년월일
		let mem_email = $("#mem_email").val();		 // 이메일
	
		let Member = {"mem_id":mem_id, "mem_pwd":mem_pwd, "mem_name":mem_name, 
								"mem_ph":mem_ph, "mem_birthday":mem_birthday, "mem_email":mem_email, "mem_gen":mem_gen} // 입력값을 json타입으로 변환
		
		$.ajax({
			url : "Membership",
			type : "post",
			data : Member,
			dataType : "json",
			success : function(data) {
				
				
				console.log(data);
				
			}
		
		
			})
		})
	});

	document.addEventListener('DOMContentLoaded', function() {
		var emailInput = document.querySelector('input[type="email"]');
		
		emailInput.addEventListener('input', function() {
			var valid = this.value.includes('@') && this.value.includes('.');
			if (valid) {
				this.style.borderColor = 'green';
			} else {
				this.style.borderColor = 'red';
			}
		});
	});
	document.addEventListener('DOMContentLoaded', function() {
		const form = document.querySelector('.login-form');
		const username = form.querySelector('input[name="mem_id"]');
		const password = form.querySelector('input[name="mem_pwd"]');
		const confirmPassword = form.querySelector('input[name="confirm-password"]');
		const address = form.querySelector('input[name="adress"]'); // 오타 수정: 'adress' -> 'address'
		const nickname = form.querySelector('input[name="mem_name"]');
		const phone = form.querySelector('input[name="mem_ph"]');
		const birthday = form.querySelector('input[name="mem_birthday"]');
		const email = form.querySelector('input[type="email"]');
	
		form.addEventListener('submit', function(e) {
			e.preventDefault(); // 폼 제출 중지
	
			// 간단한 유효성 검사
			if (!username.value.trim()) {
				alert('아이디를 입력해주세요.');
				username.focus();
				return;
			}
	
			if (password.value.length < 8) {
				alert('비밀번호는 8자 이상이어야 합니다.');
				password.focus();
				return;
			}
	
			if (password.value !== confirmPassword.value) {
				alert('비밀번호가 일치하지 않습니다.');
				confirmPassword.focus();
				return;
			}
	
			if (!address.value.trim()) {
				alert('주소를 입력해주세요.');
				address.focus();
				return;
			}
	
			if (!nickname.value.trim()) {
				alert('닉네임을 입력해주세요.');
				nickname.focus();
				return;
			}
	
			if (!phone.value.trim()) {
				alert('휴대전화번호를 입력해주세요.');
				phone.focus();
				return;
			}
	
			if (!birthday.value.trim()) {
				alert('생년월일을 입력해주세요.');
				birthday.focus();
				return;
			}
	
			if (!email.value.includes('@') || !email.value.includes('.')) {
				alert('유효한 이메일 주소를 입력해주세요.');
				email.focus();
				return;
			}
	
			// 모든 검사를 통과했을 때 폼 제출
			// form.submit(); // 실제로 폼 제출을 활성화하려면 이 주석을 해제하세요.
			alert('회원가입이 완료되었습니다.'); // 예시 메시지
		});
	});