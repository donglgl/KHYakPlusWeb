document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('edit-button').addEventListener('click', function() {
    document.getElementById('file-input').click();
  });

  document.getElementById('file-input').addEventListener('change', function() {
    const file = this.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = function(e) {
        document.getElementById('profile-pic').src = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  });
});

/* 모달 */
// Get the modal
var modal = document.getElementById('profileModal');

// Get the button that opens the modal
var btn = document.getElementById('editProfileBtn');

// Get the <span> element that closes the modal
var span = document.getElementsByClassName('close')[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = 'block';
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = 'none';
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}

// Get the save changes button
var saveBtn = document.getElementById('saveProfileChanges');

// When the user clicks the save changes button, update the profile picture and nickname
saveBtn.onclick = function() {
    var nicknameInput = document.getElementById('nicknameInput').value;
    var profileName = document.getElementById('profile-name');
    
    // Update nickname if the input is not empty
    if (nicknameInput.trim() != '') {
        profileName.textContent = nicknameInput;
    }

    var profilePicInput = document.getElementById('profilePictureInput').files[0];
    var profilePic = document.getElementById('profile-pic');

    // Update profile picture if a file was chosen
    if (profilePicInput) {
        var reader = new FileReader();
        reader.onload = function(e) {
            profilePic.src = e.target.result;
        }
        reader.readAsDataURL(profilePicInput);
    }
    
    // Close the modal
    modal.style.display = 'none';
}


function Postcode() {
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

function Redirect() {
    if (confirm("회원가입을 탈퇴하시겠습니까?")) {
        window.location.href = "/mypage/redirect"; // 컨트롤러의 URL로 리다이렉트
    }
}

function update() {
	  alert('수정하였습니다.');
	}

