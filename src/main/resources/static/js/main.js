
const searchEl = document.querySelector('.search');
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

/* 스크롤- 일정한 스크롤이 내려가면 오른쪽 배너광고가 사라집니다. */

const badgeEl = document.querySelector('.badges');

/* 스크롤 스크롤이 시작되면 함수를 실행하겠습니다. 
_.throttle는 lodash 라이브러리입니다. 
아래의 함수는 일정한 시간동안만,*/
window.addEventListener('scroll', _.throttle(function(){
    console.log('scrollY');
    if (window.scrollY > 500) {
        /*광고 배너 숨겨버립니다.*/
        /*gasp : badgeEl 요소는 .6초 애니메이션이 실행되고 점점 사라질 것입니다*/
        gsap.to(badgeEl, .6, {
            opacity: 0,
            display: 'none'
        });
    }else{
        gsap.to(badgeEl, .6, {
            opacity: 1,
            display: 'block'
        });
    }
},
300));


/* _.throttle(함수, 시간) */
/*    if (window.scrollY > 500) {
        /*광고 배너 숨겨버립니다.*/
    //    /**/badgeEl.style.display = 'none';
    // }else{
    //     badgeEl.style.display = 'block';
    // }*/