function changeText(id) {
    var textContainer = document.getElementById('textContainer');
    var targetElement = document.getElementById(id);
    
    if (targetElement) {
        textContainer.innerHTML = targetElement.innerHTML;
        targetElement.style.display = 'block'; // 요소를 표시합니다.
    } else {
        textContainer.textContent = '해당하는 내용이 없습니다.';
    }
}