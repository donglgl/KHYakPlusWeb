// <!-- 전체 선택 및 전체 취소 버튼 -->

// <button id="deselectAll">전체 취소</button>

// <!-- 체크박스 목록 -->
// <div>
//   <input type="checkbox" class="itemCheckbox"> 체크박스 1<br>
//   <input type="checkbox" class="itemCheckbox"> 체크박스 2<br>
//   <input type="checkbox" class="itemCheckbox"> 체크박스 3<br>
//   <!-- 여기에 더 많은 체크박스를 추가할 수 있습니다. -->
// </div>
// 올바른 변수 이름과 클래스 선택자를 사용

// '전체 선택' 체크박스의 이벤트 리스너
const selectAllContainer = document.getElementById('selectAllContainer');
selectAllContainer.addEventListener('click', (event) => {
  // 현재 문서에 존재하는 모든 .itemCheckbox 요소를 새롭게 선택합니다.
  document.querySelectorAll('.itemCheckbox').forEach((checkbox) => {
    checkbox.checked = event.target.checked;
  });
});







// // '전체 취소' 버튼의 이벤트 리스너 (이 코드는 문제가 제기되지 않았지만 동일한 패턴을 적용할 수 있습니다)
// document.getElementById('deselectAll').addEventListener('click', () => {
//     // 현재 문서에 존재하는 모든 .itemCheckbox 요소를 새롭게 선택합니다.
//     document.querySelectorAll('.itemCheckbox').forEach((checkbox) => {
//       checkbox.checked = false;
//     });
//   });
//JavaScript에서 forEach 메서드는 배열의 각 요소에 대해 주어진 함수를 실행합니다. 이는 배열의 내장 메서드로, 다음과 같이 사용됩니다






const shopcart=document.querySelector('.shopcart');
const shopcart_info=document.querySelector('.shopcart_info');
const buy_floating=document.querySelector('.buy_floating');


//장바구니가 비었습니다를 숨기기위한코드 
const shopcart_hidden = document.getElementById('shopcart_item_hidden');





function updateItemCount(cardElement, value) {
  let itemCountElement = cardElement.querySelector('.itemCount');
  if (itemCountElement) {
    itemCountElement.textContent = value;
  }
}
function calculateTotal() {
	  var totalPrice = 0;
	  var totalQuantity = 0;

	  document.querySelectorAll('.cart_table tbody tr').forEach(function(row) {
	    var priceText = row.querySelector('.itemPrice').textContent;
	    var quantityText = row.querySelector('.itemCount').textContent;

	    // 숫자로 변환합니다.
	    var price = parseInt(priceText.replace(/[^0-9]/g, ''), 10);
	    var quantity = parseInt(quantityText.replace(/[^0-9]/g, ''), 10);

	    // 총합을 계산합니다.
	    totalPrice += price * quantity;
	    totalQuantity += quantity;
	  });

	  // 결과를 페이지에 표시합니다.
	  document.getElementById('float_price').textContent = addCommasToNumber(totalPrice);
	  document.getElementById('float_count').textContent = totalQuantity;
	}

	// 페이지가 로드되면 calculateTotal 함수를 실행합니다.
	document.addEventListener('DOMContentLoaded', function() {
	  calculateTotal();
	});

//주문하기
function cartConfirm() {
  
  if (confirm("주문하시겠습니까?")) {
    // 확인을 누르면 결제 페이지로 이동
    window.location.href = "./pay.html";
  } else {
    // 취소할 경우 아무것도 하지 않음
  }
}


//천단위 쉼표
function addCommasToNumber(number) {
    let numberString = number.toString();
    let result = numberString;
    
    // 소수점을 찾는 코드를 제거하고, 정수 부분에만 쉼표를 추가합니다.
    let parts = numberString.split('.');
    let integerPart = parts[0];
    let decimalPart = parts.length > 1 ? '.' + parts[1] : '';
    
    // 정규 표현식을 사용하여 천 단위마다 쉼표를 추가합니다.
    integerPart = integerPart.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    
    result = integerPart + decimalPart;
    
    return result;
}

