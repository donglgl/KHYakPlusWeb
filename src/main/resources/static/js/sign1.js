// 모달을 가져옵니다.
var modal = document.getElementById("termsModal");

// "더보기" 링크를 가져옵니다.
var viewTermsLink = document.getElementById("viewTerms");

// 모달을 닫는 <span> 요소(닫기 버튼)를 가져옵니다.
var span = document.getElementsByClassName("close-button")[0];

// "더보기" 링크 클릭 이벤트
viewTermsLink.onclick = function(event) {
    event.preventDefault(); // 링크의 기본 동작을 막습니다.
    modal.style.display = "block"; // 모달창을 표시합니다.
}

// <span> (x) 클릭 이벤트
span.onclick = function() {
    modal.style.display = "none"; // 모달창을 숨깁니다.
}

// 모달 외부 클릭 이벤트
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none"; // 모달창을 숨깁니다.
    }
}

//체크박스
const checkAllCheckbox = document.getElementById('check-all');

// 개별 동의 체크박스들을 모두 가져오기
const individualCheckboxes = document.querySelectorAll('.agree');

// 전체동의 체크박스의 상태가 변경되었을 때 실행되는 함수
function toggleAllCheckboxes() {
    // 전체동의 체크박스의 상태에 따라 개별 동의 체크박스들의 상태 변경
    individualCheckboxes.forEach(checkbox => {
        checkbox.checked = checkAllCheckbox.checked;
    });
}

// 전체동의 체크박스에 change 이벤트 리스너 추가
checkAllCheckbox.addEventListener('change', toggleAllCheckboxes);

