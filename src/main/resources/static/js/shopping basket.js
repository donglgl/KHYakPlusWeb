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

let isFirstProduct = true; // 첫 번째 상품을 추가하는지 여부를 확인하는 변수입니다.

// '장바구니가 비었습니다'를 숨기기 위한 코드 
let addButton1 = document.getElementById('addButton1');
addButton1.addEventListener('click', function () {
  selectAllContainer.style.display = "block";
  shopcart_hidden.style.display = "none";
  shopcart_info.style.display = "block";
  buy_floating.style.display="flex";
  shopcart.style.display="block";
  buy_floating.style.justifyContent = "center";
  

  let cardheader = isFirstProduct ? `
  <div class="card-header card-header-custom" style="border-bottom: 2px solid black; display:flex; align-items:center;">
    <strong><span id="card_header_text">의약품</span></strong>
  </div>` : '';

  let cardBodyHeight = isFirstProduct ? '280px' : '230px'; // 첫 번째 상품일 경우 280px, 아니면 230px

  // 구급의약품에 대한 상품 HTML을 정의합니다.
  let newProduct = `
  <div class="card card-custom" style="height: ${cardBodyHeight};" data-price="65000">
   ${cardheader}
   <div class="card-body card-body-custom" style="padding: 0 0 0 30px;" position: relative;>
   <input type="checkbox" class="itemCheckbox" style="position: absolute; left: 0; top: 50%; width: 25px; height: 25px;" >
      <div class="product-info" style="width: 500px; height:230px; float: left; "><div class="product-info-inner" style="box-sizing: border-box; padding:20px 20px 20px 0px; height:230px; display:flex; align-items:center;">
      <img class="product-Image" src="./images/고라파덕.png" alt="고라파덕"><p class="product-Intro"><b>NEW리뉴얼 보장균수 2배 종근당 건강 락토핏 생유산균 골드 6통 업그레이드 10개월<br>65000원<b></p>
      </div></div>
      <div class="second-div" style="width: 180px; height:230px; float: left;">
      <div class="second-div-inner">
      <div>상품 수량 <span class="itemCount">1</span>개</div><br>
      <div class="count-wrap _count">
        <button type="button" class="minus">감소</button>
        <input type="text" class="inp" value="1" />
        <button type="button" class="plus">증가</button>
      </div>
    </div>
      </div>
      <div class="third-div" style="width: 155px; height:230px; float: left; display: flex; align-items: center; justify-content: center; flex-direction: column;">
      <span class="price-label" style="font-weight:bold;">상품 가격</span>
      <span class="price-value" style="font-size:1.5em; color:green;">65,400원</span>
  </div>
  <div class="fourth-div" style="width: 160px; height:230px; float: left; display: flex; justify-content:center; align-items: center;  flex-direction: row;">
  <span class="shipping-label" style="font-weight:bold;">배송비무료</span>
  </div>
  </div>
  </div>
  </div>`;

  let shopListFirst = document.getElementById('shop_list_first');
  shopListFirst.insertAdjacentHTML('beforeend', newProduct);
  isFirstProduct = false; // 첫 번째 상품 추가 후 false로 설정
});




let isSecondProduct = true; // 첫 번째 상품을 추가하는지 여부를 확인하는 변수입니다.

// '장바구니가 비었습니다'를 숨기기 위한 코드 
let addButton2 = document.getElementById('addButton2');
addButton2.addEventListener('click', function () {
  selectAllContainer.style.display = "block";
  shopcart_hidden.style.display = "none";
  shopcart.style.display="block";
  shopcart_info.style.display = "block";
  buy_floating.style.display="flex";
  buy_floating.style.justifyContent = "center";

  let cardheader = isSecondProduct ? `
  <div class="card-header card-header-custom" style="border-bottom: 2px solid black; display:flex; align-items:center;">
    <strong><span id="card_header_text">구급의약품</span></strong>
  </div>` : '';

  let cardBodyHeight = isSecondProduct ? '280px' : '230px'; // 첫 번째 상품일 경우 280px, 아니면 230px

  // 구급의약품에 대한 상품 HTML을 정의합니다.
  let newEmergency = `
  <div class="card card-custom" style="height: ${cardBodyHeight};" data-price="65000">
   ${cardheader}
   <div class="card-body card-body-custom" style="padding: 0 0 0 30px;">
   <input type="checkbox" class="itemCheckbox" style="position: absolute; left: 0; top: 50%; width: 25px; height: 25px;" >
      <div class="product-info" style="width: 500px; height:230px; float: left; "><div class="product-info-inner" style="box-sizing: border-box; padding:20px 20px 20px 0px; height:230px; display:flex; align-items:center;">
      <img class="product-Image" src="./images/고라파덕.png" alt="고라파덕"><p class="product-Intro"><b>NEW리뉴얼 보장균수 2배 종근당 건강 락토핏 생유산균 골드 6통 업그레이드 10개월<br>65000원<b></p>
      </div></div>
      <div class="second-div" style="width: 180px; height:230px; float: left;">
      <div class="second-div-inner">
      <div>상품 수량 <span class="itemCount">1</span>개</div><br>
      <div class="count-wrap _count">
        <button type="button" class="minus">감소</button>
        <input type="text" class="inp" value="1" />
        <button type="button" class="plus">증가</button>
      </div>
    </div>
      </div>
      <div class="third-div" style="width: 155px; height:230px; float: left; display: flex; align-items: center; justify-content: center; flex-direction: column;">
      <span class="price-label" style="font-weight:bold;">상품 가격</span>
      <span class="price-value" style="font-size:1.5em; color:green;">65,400원</span>
  </div>
  <div class="fourth-div" style="width: 160px; height:230px; float: left; display: flex; justify-content:center; align-items: center;  flex-direction: row;">
  <span class="shipping-label" style="font-weight:bold;">배송비무료</span>
  </div>
  </div>
  </div>
  </div>`;

  let shopListSecond = document.getElementById('shop_list_second');
  shopListSecond.insertAdjacentHTML('beforeend', newEmergency);
  isSecondProduct = false; // 첫 번째 상품 추가 후 false로 설정
});





document.addEventListener('click', function (event) {
  if (event.target.classList.contains('minus')) {
    let inp = event.target.nextElementSibling;
    let currentValue = parseInt(inp.value, 10);
    if (isNaN(currentValue)) currentValue = 1; // NaN 체크 추가
    if (currentValue > 1) {
      inp.value = currentValue - 1;
    }
    updateItemCount(event.target.closest('.card-custom'), inp.value);
    updateItemPrice(event.target.closest('.card-custom'), inp.value);
  }
  else if (event.target.classList.contains('plus')) {
    let inp = event.target.previousElementSibling;
    let currentValue = parseInt(inp.value, 10);
    if (isNaN(currentValue)) currentValue = 0; // NaN 체크 추가
    inp.value = currentValue + 1;
    updateItemCount(event.target.closest('.card-custom'), inp.value);
    updateItemPrice(event.target.closest('.card-custom'), inp.value);
  }
});

// updateItemCount 함수를 하나로 통합합니다.
function updateItemCount(cardElement, value) {
  let itemCountElement = cardElement.querySelector('.itemCount');
  if (itemCountElement) {
    itemCountElement.textContent = value;
  }
}


//가격계산 변동
function updateItemPrice(cardElement, quantity) {
  let priceElement = cardElement.querySelector('.price-value');
  let basePrice = parseInt(cardElement.dataset.price, 10); // 기본 가격을 data-price 속성에서 가져옵니다.
  let newPrice = basePrice * quantity; // 새로운 가격을 계산합니다.
  priceElement.textContent = `${newPrice.toLocaleString()}원`; // 새로운 가격을 화면에 표시합니다.
}

function updateTotal() {
  const cards = document.querySelectorAll('.card-custom');
  let totalQuantity = 0;
  let totalPrice = 0;

  cards.forEach(card => {
    // 체크박스가 선택된 카드만 계산에 포함합니다.
    const isChecked = card.querySelector('.itemCheckbox').checked;
    if (isChecked) {
      const quantityElement = card.querySelector('.itemCount');
      const quantity = parseInt(quantityElement ? quantityElement.textContent : '0', 10);
      const priceElement = card.querySelector('.price-value');
      const price = parseInt(priceElement ? priceElement.textContent.replace(/[^0-9]/g, '') : '0', 10);
      totalQuantity += quantity;
      totalPrice += price;
    }
  });

  // 총 수량과 총 금액을 페이지 하단에 업데이트합니다.
  document.getElementById('float_count').textContent = totalQuantity;
  document.getElementById('float_price').textContent = totalPrice.toLocaleString('ko-KR');
}

// 체크박스의 상태가 변경될 때와 수량 변경 버튼('+', '-') 클릭 시 총합을 업데이트합니다.
document.addEventListener('change', function(event) {
  if (event.target.classList.contains('itemCheckbox') || event.target.classList.contains('itemCount')) {
    updateTotal();
  }
});

document.addEventListener('click', function(event) {
  if (event.target.classList.contains('minus') || event.target.classList.contains('plus')) {
    // 잠시 후 총합을 업데이트합니다. (DOM 업데이트 대기)
    setTimeout(updateTotal, 0);
  }
});

// 페이지 로딩 시 최초 1회 총합을 업데이트합니다.
document.addEventListener('DOMContentLoaded', updateTotal);


//주문하기
function cartConfirm() {
  
  if (confirm("주문하시겠습니까?")) {
    // 확인을 누르면 결제 페이지로 이동
    window.location.href = "./pay.html";
  } else {
    // 취소할 경우 아무것도 하지 않음
  }
}



















// function cartConfirm() {
//   // 만약 사용자가 로그인되어 있지 않다면
//   // if (!isLoggedIn()) {
//   //   // 로그인 페이지로 이동
//   //   window.location.href = "로그인.html";
//   // } else {
//   //   // 로그인 되어있다면 주문.
//     if (confirm("주문하시겠습니까?")) {
//       // 확인을 누르면 결제 페이지로 이동
//       window.location.href = "./pay.html";
//     } else {
//       // 취소할 경우 아무것도 하지 않음
//     }
//   }


// function isLoggedIn() {
//   // 세션에서 isLoggedIn 값을 확인하여 로그인 상태를 반환
//   return sessionStorage.getItem('isLoggedIn') === 'true';
// }