    function selectCategory(category) {
        // 클릭한 버튼의 텍스트를 드롭다운 토글 버튼의 텍스트로 설정
        document.getElementById('searchDropdownToggle').textContent = category;
    }


 
    // 검색창 드롭박스
    
    document.getElementById('searchOption').addEventListener('change', function() {
        var selectedValue = this.value;
        
        if(selectedValue =='all'){
        	document.getElementById('searchInput').setAttribute('name', 'all');
        	document.getElementById('searchInput').setAttribute('placeholder', '검색어를 입력하세요');
        }else if (selectedValue == 'title') {
            document.getElementById('searchInput').setAttribute('name', 'title');
            document.getElementById('searchInput').setAttribute('placeholder', '제품명을 입력하세요');
        }else if (selectedValue == 'ent') {
            document.getElementById('searchInput').setAttribute('name', 'ent');
            document.getElementById('searchInput').setAttribute('placeholder', '제조사를 입력하세요');
        }
    });
    
    
    
    
    
    //사진형 목록형 버튼
    function repeat1() {
        var currentUrl = window.location.href;
        var newUrl = currentUrl.replace('/processSearch/list','/processSearch');
        // 새로운 URL로 이동합니다.
        window.location.href = newUrl;
    }
    function repeat2() {
        var currentUrl = window.location.href;
        var newUrl = currentUrl.replace('/processSearch','/processSearch/list');
        // 새로운 URL로 이동합니다.
        window.location.href = newUrl;
    }
    




   


