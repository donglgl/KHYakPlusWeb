


function showReturn(){
    window.location.href="./history.html";
}

document.addEventListener('DOMContentLoaded', function() {
    // 체크박스를 눌렀을 때의 이벤트 리스너 등록
    const checkboxes = document.querySelectorAll('i.fa-regular.fa-square');
    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('click', function() {
            // 체크 상태 토글
            checkbox.classList.toggle('fa-square');
            checkbox.classList.toggle('fa-check-square');
            checkbox.classList.toggle('checked'); // 'checked' 클래스를 추가/제거하여 시각적인 표시를 변경
        });
    });

    
   
});
