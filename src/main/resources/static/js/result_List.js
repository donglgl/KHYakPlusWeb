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
 
    function repeatSearch1() {
    	    // 현재 검색된 URL에서 쿼리 매개변수를 추출합니다.
        const urlParams = new URLSearchParams(window.location.search);
        const title = urlParams.get('title'); // 현재 검색어
        const ent = urlParams.get('ent'); // 현재 업체명
        const all = urlParams.get('all'); // 모든 약물 데이터 여부

        // 쿼리 매개변수가 없는 경우, 현재 페이지 URL을 기본으로 설정합니다.
        let newUrl = window.location.href;

        // 검색어가 있거나 업체명이 있는 경우에만 검색을 다시 수행합니다.
        if (title || ent) {
            // 검색에 사용된 쿼리 매개변수를 새로운 페이지로 전달하여 검색을 다시 수행합니다.
            newUrl = 'http://localhost:9080/processSearch?';
            if (title) newUrl += 'title=' + encodeURIComponent(title) + '&';
            if (ent) newUrl += 'ent=' + encodeURIComponent(ent) + '&'; // '&' 추가
        } else if (all) {
            // 모든 약물 데이터를 가져오는 경우에 쿼리 매개변수를 추가합니다.
            newUrl = 'http://localhost:9080/processSearch/list?all=' + encodeURIComponent(all) + '&';
        } else {
            console.log('검색어 또는 업체명이 없습니다.');
        }

    function repeatSearch2() {
        // 현재 검색된 URL에서 쿼리 매개변수를 추출합니다.
        const urlParams = new URLSearchParams(window.location.search);
        const title = urlParams.get('title'); // 현재 검색어
        const ent = urlParams.get('ent'); // 현재 업체명
        const all = urlParams.get('all'); // 모든 약물 데이터 여부

        // 쿼리 매개변수가 없는 경우, 현재 페이지 URL을 기본으로 설정합니다.
        let newUrl = window.location.href;

        // 검색어가 있거나 업체명이 있는 경우에만 검색을 다시 수행합니다.
        if (title || ent) {
            // 검색에 사용된 쿼리 매개변수를 새로운 페이지로 전달하여 검색을 다시 수행합니다.
            newUrl = 'http://localhost:9080/processSearch/list?';
            if (title) newUrl += 'title=' + encodeURIComponent(title) + '&';
            if (ent) newUrl += 'ent=' + encodeURIComponent(ent) + '&'; // '&' 추가
        } else if (all) {
            // 모든 약물 데이터를 가져오는 경우에 쿼리 매개변수를 추가합니다.
            newUrl = 'http://localhost:9080/processSearch/list?all=' + encodeURIComponent(all) + '&';
        } else {
            console.log('검색어 또는 업체명이 없습니다.');
        }

        // 마지막 '&' 문자 제거
        newUrl = newUrl.slice(0, -1);

        // 새로운 페이지로 이동합니다.
        window.location.href = newUrl;
    }


   

 // 의약품 드롭다운
 document.getElementById('categorySelect').addEventListener('change', function() {
     var selectedValue = this.value;
     var listItems = document.querySelectorAll('.list-item');

     listItems.forEach(function(item) {
         item.style.display = 'none';
     });

     if (selectedValue === 'medi') {
         listItems.forEach(function(item) {
             item.style.display = 'block';
         });
     } else {
         var selectedItems = document.querySelectorAll('.' + selectedValue);
         selectedItems.forEach(function(item) {
             item.style.display = 'block';
         });
     }
 });
