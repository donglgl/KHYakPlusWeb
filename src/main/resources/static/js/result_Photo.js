    function selectCategory(category) {
        // 클릭한 버튼의 텍스트를 드롭다운 토글 버튼의 텍스트로 설정
        document.getElementById('searchDropdownToggle').textContent = category;
    }



    
    document.getElementById('searchOption').addEventListener('change', function() {
        var selectedValue = this.value;
        
        if(selectedValue =='all'){
        	document.getElementById('searchInput').setAttribute('name', 'All');
        	document.getElementById('searchInput').setAttribute('placeholder', '검색어를 입력하세요');
        }else if (selectedValue === 'title') {
            document.getElementById('searchInput').setAttribute('name', 'title');
            document.getElementById('searchInput').setAttribute('placeholder', '제품명을 입력하세요');
        }else if (selectedValue === 'ent') {
            document.getElementById('searchInput').setAttribute('name', 'ent');
            document.getElementById('searchInput').setAttribute('placeholder', '제조사를 입력하세요');
        }
    });
    
 
 
    
    
    //검색창 드롭박스 
    function search(){
    document.getElementById('searchOption').addEventListener('change', function() {
        var selectedValue = this.value;
        if  (selectedValue === 'title') {
            document.getElementById('searchInput').setAttribute('name', 'title');
            document.getElementById('searchInput').setAttribute('placeholder', '제품명을 입력하세요');
        } else if (selectedValue === 'ent') {
            document.getElementById('searchInput').setAttribute('name', 'ent');
            document.getElementById('searchInput').setAttribute('placeholder', '제조사를 입력하세요');
        }
    })
    };
    
    
    
    
    
    //사진형 목록형 버튼
 
    function repeatSearch1() {
        // 현재 검색된 URL에서 쿼리 매개변수를 추출합니다.
        const urlParams = new URLSearchParams(window.location.search);
        const searchKeyword = urlParams.get('title'); // 현재 검색어
        
       // 현재 검색어가 있을 경우에만 검색을 다시 수행합니다.
        if (searchKeyword) {
            // 현재 검색에 사용된 쿼리 매개변수를 새로운 페이지로 전달하여 검색을 다시 수행합니다.
            const newUrl = 'http://localhost:9080/processSearch?title=' + encodeURIComponent(searchKeyword);
            
            // 새로운 페이지로 이동합니다.
            window.location.href = newUrl;
        } else {
            console.log('현재 검색어가 없습니다.');
        }
    }

    function repeatSearch2() {
        // 현재 검색된 URL에서 쿼리 매개변수를 추출합니다.
        const urlParams = new URLSearchParams(window.location.search);
        const searchKeyword = urlParams.get('title'); // 현재 검색어
        
        // 현재 검색어가 있을 경우에만 검색을 다시 수행합니다.
        if (searchKeyword) {
            // 현재 검색에 사용된 쿼리 매개변수를 새로운 페이지로 전달하여 검색을 다시 수행합니다.
            const newUrl = 'http://localhost:9080:/processSearch/list?title=' + encodeURIComponent(searchKeyword);
            
            // 새로운 페이지로 이동합니다.
            window.location.href = newUrl;
        } else {
            console.log('현재 검색어가 없습니다.');
        }
    }
    
   
    //의약품 드롭다운
    document.getElementById('categorySelect').addEventListener('change', function() {
        var selectedValue = this.value; // 선택된 옵션의 값 가져오기
        var listItems = document.querySelectorAll('.list-item'); // 모든 list-item 요소 선택
        
        // 모든 list-item 요소에 대해 숨김 처리
        listItems.forEach(function(item) {
            item.style.display = 'none';
        });
        
        // 선택된 옵션 값에 따라 해당하는 클래스를 가진 요소들만 보이도록 설정
        if (selectedValue === 'medi') {
            listItems.forEach(function(item) {
                item.style.display = 'block';
            });
        } else {
            var selectedItems = document.querySelectorAll('.' + selectedValue); // 선택된 옵션 값과 같은 클래스를 가진 요소들 선택
            selectedItems.forEach(function(item) {
                item.style.display = 'block';
            });
        }
    });
