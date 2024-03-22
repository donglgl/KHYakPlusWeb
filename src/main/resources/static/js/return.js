function toggleReturnTextArea(selectElement) {
  
  const textarea = document.getElementById('textarea');
  

  if (selectElement.value === 'reason2') {
      textarea.style.display = 'block'; 
  } else {
    
      textarea.style.display = 'none'; 
  }
}
function showConfirm() {
  if (confirm("정말로 진행하시겠습니까?")) {
    //확인을 눌렀을때
    window.location.href = "경로/확인.html"; 
  } else {
    // 사용자가 "취소"를 클릭한 경우
    window.location.href = "#"; 
  }
}