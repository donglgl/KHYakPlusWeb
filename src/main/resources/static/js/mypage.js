document.getElementById('toggle-info').addEventListener('click', function() {
    var infoView = document.getElementById('info-view');
    var infoEdit = document.getElementById('info-edit');
    if (infoView.style.display === "none") {
        infoView.style.display = "block";
        infoEdit.style.display = "none";
    } else {
        infoView.style.display = "none";
        infoEdit.style.display = "block";
        // AJAX를 사용하여 사용자 정보를 불러오고 폼에 채우는 코드를 여기에 추가
        // 예시:
        /*
        fetch('userInfoEndpoint', {
            method: 'GET', // 또는 'POST'
        })
        .then(response => response.json())
        .then(data => {
            document.getElementById('edit-name').value = data.name;
            document.getElementById('edit-email').value = data.email;
            // 기타 필드 채우기
        })
        .catch((error) => {
            console.error('Error:', error);
        });
        */
    }
});

// 폼 제출 이벤트 리스너를 추가하여 AJAX를 사용해 수정된 정보를 서버로 전송
document.getElementById('edit-form').addEventListener('submit', function(e) {
    e.preventDefault();
    // 폼 데이터를 가져와서 AJAX 요청으로 서버에 전송
    // 예시:
    /*
    fetch('updateUserInfoEndpoint', {
        method: 'POST',
        body: JSON.stringify({
            name: document.getElementById('edit-name').value,
            email: document.getElementById('edit-email').value,
            // 기타 필드
        }),
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        // 성공적으로 정보를 수정했을 때의 처리
    })
    .catch((error) => {
        console.error('Error:', error);
    });
    */
});

document.getElementById('upload-photo').addEventListener('change', function(e) {
    if (e.target.files && e.target.files[0]) {
        var reader = new FileReader();
        
        reader.onload = function(e) {
            document.getElementById('profile-photo').src = e.target.result;
        }
        
        reader.readAsDataURL(e.target.files[0]);
    }
});

// 프로필 사진이 없을 때 기본 이미지 설정
window.onload = function() {
    var profilePhoto = document.getElementById('profile-photo');
    if (!profilePhoto.src) {
        profilePhoto.src = 'default-profile.png'; // 여기에 기본 프로필 사진 경로를 설정
    }
};