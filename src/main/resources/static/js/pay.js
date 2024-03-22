document.getElementById('payBtn2').addEventListener('click', function() {
  window.location.href = '/totalOrder'; // 혹은 원하는 URL
});
        
        
        
        
sessionStorage.setItem('login_name', '${mem_name}');

var element_layer = document.getElementById('layer');

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 합니다.
    element_layer.style.display = 'none';
    // 아래 코드를 추가하여 필드를 초기화합니다.
    document.getElementById('sample2_postcode').value = '';
    document.getElementById('sample2_address').value = '';
    document.getElementById('sample2_detailAddress').value = '';
}

function sample2_execDaumPostcode() {
    document.getElementById("modal_title").innerText = "배송지 찾기";
    var newAddressButton = document.getElementById('delivery_change');
    newAddressButton.style.display = 'none';
    document.getElementById('exampleModal').style.zIndex = '102';
    element_layer.style.display = 'block';
    // 현재 스크롤 위치를 저장해놓습니다.
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            // 주소 처리 로직
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수
            var addressInputFields = document.getElementById('address_input_fields');
            addressInputFields.style.display = 'block';
            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }
            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            }
            document.getElementById("modal_title").innerText = "배송지 입력";
            var fullAddress = addr + extraAddr;
            document.getElementById('sample2_address').value = fullAddress;
            document.getElementById('sample2_detailAddress').focus();
            document.getElementById('sample2_postcode').value = data.zonecode; // 우편번호 설정

            element_layer.style.display = 'none';
            document.body.scrollTop = currentScroll;
        },
        width : '100%',
        height : '100%',
        maxSuggestItems : 5
    }).embed(element_layer);

    element_layer.style.display = 'block';
    document.body.scrollTop = 0;
}

let modalSubmitButton = document.getElementById('modal-submit');
modalSubmitButton.removeEventListener('click', saveAddress); // 이전 리스너 제거
modalSubmitButton.addEventListener('click', saveAddress); // 새 리스너 추가
function saveAddress() {
    var fullAddress = document.getElementById('sample2_address').value;
    var detailAddress = document.getElementById('sample2_detailAddress').value;
    var postcode = document.getElementById('sample2_postcode').value;
    var userName = sessionStorage.getItem('login_name'); // 세션 스토리지에서 사용자 이름을 가져옵니다.

    // 서버로 데이터를 전송하기 전에 데이터를 콘솔에 출력합니다.
    console.log('Before sending data to server:', { fullAddress, detailAddress, userName, postcode });

    // AJAX 요청을 통해 서버로 주소 정보와 사용자 이름을 전송합니다.
    $.ajax({
        url: '/save-address',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            addName: userName,
            addAddr: fullAddress,
            addDetail: detailAddress,
            addCode: postcode
        }),
        success: function(response) {
            console.log('Response from server:', response);
            updateModalContent(response); // 응답을 받아 모달 내용을 업데이트합니다.
            document.getElementById('sample2_postcode').value = '';
            document.getElementById('sample2_address').value = '';
            document.getElementById('sample2_detailAddress').value = '';
        },
        error: function(xhr, status, error) {
            console.error('Error occurred while saving address:', xhr.responseText, status, error);
            // 오류 발생 시 모달에 오류 메시지를 표시합니다.
           
        }
    });
}
function updateModalContent(response) {
    console.log('updateModalContent function called with response:', response);
    var modalContent = document.getElementById('modal-content');

    // 서버로부터 받은 응답이 정상인지 확인합니다.
    if (response.addName && response.addAddr && response.addDetail && response.addCode) {
        // 모든 필요한 응답 프로퍼티가 존재하면 모달 내용을 업데이트합니다.
        modalContent.innerHTML = `
            <div class="modal-header" style="border-bottom: 1px solid black;">
                <span class="modal-title" id="modal_title" style="font-size: 1.8rem; font-weight: bold; margin-left:5.2rem;">배송지 목록</span>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body" style="height: auto; display: flex; flex-direction: column; align-items: center;">
                <input id="delivery_change" type="button" onclick="sample2_execDaumPostcode()" value="신규배송지+" style="width: 14rem; height: 3rem; background-color: var(--color-sub-blue); color: var(--color-white); font-weight: bold; font-size: 1.3rem; margin-bottom: 1rem;">
                <div>
                    <div style="font-weight: bold;">
                        <p>이름: ${response.addName}</p>
                <p>배송지: ${response.addAddr}</p>
                <p>상세주소: ${response.addDetail}</p>
                <p>우편번호: ${response.addCode}</p>
                    </div>
                </div>
            </div>
        `;
    } else {
        console.error('Some properties are missing from the server response:', response);
        // 필요한 프로퍼티가 누락된 경우 사용자에게 오류 메시지를 표시합니다.
        modalContent.innerHTML = `
            <p>서버로부터 올바른 응답을 받지 못했습니다. 입력한 정보를 다시 확인해 주세요.</p>
        `;
    }
}



// 다음 우편번호 서비스의 '닫기' 버튼 기능을 구현합니다.
function closeDaumPostcode() {
    // 우편번호 찾기 화면을 닫습니다.
    element_layer.style.display = 'none';
}
	


function toggleTextarea() {
  let selectValue = document.getElementById("deliveryOption").value;
  if (selectValue === "2") {
   
      document.querySelector('.memo').style.display = 'block';
      document.querySelector('.memo').focus();
    
  } else {
      document.querySelector('.memo').style.display = 'none';
  }
  
}
