/**
 * 
 */const searchEl = document.querySelector('.search');
const searchInputEl = searchEl.querySelector('input');

searchEl.addEventListener('click', function () {
    //serch div를 클릭하면 함수가 실행되는데, focus를 클릭해라
    searchInputEl.focus();
});

//serachEl를 
searchInputEl.addEventListener('focus', function() {
    searchEl.classList.add('focused');
    //html 속성을 지정함.
    searchInputEl.setAttribute('placeholder', '검색어를 입력해주세요');
});

//input요소에 포커스가 해제되었을 때
searchInputEl.addEventListener('blur', function() {
    searchEl.classList.remove('focused');
    //html 속성을 지정함.
    searchInputEl.setAttribute('placeholder', '');
});