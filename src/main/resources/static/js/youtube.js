
var tag = document.createElement('script');

tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

// 3. This function creates an <iframe> (and YouTube player)
//    after the API code downloads.
function onYouTubeIframeAPIReady() {
    new YT.Player('player', {
      videoId: 'weYOUrdzGCA', // 최초 재생할 유튜브 영상 ID
      height: '300', // 영상의 높이를 300px로 설정
      width: '300', // 영상의 너비를 300px로 설정
      playerVars: {
        autoplay: 1, // 자동 재생 활성화
        loop: 1, // 반복 재생 활성화
        playlist: 'weYOUrdzGCA' // 반복 재생할 유튜브 영상 ID 목록
      },
      events: {
        onReady: function(event) {
          event.target.mute(); // 음소거
        }
      }
    });
}