// 수량 증가 함수
function increaseQuantity() {
    var quantityInput = document.getElementById("quantity");
    quantityInput.value = parseInt(quantityInput.value) + 1;
    updateTotalPrice();
}

// 수량 감소 함수
function decreaseQuantity() {
    var quantityInput = document.getElementById("quantity");
    if (parseInt(quantityInput.value) > 1) {
        quantityInput.value = parseInt(quantityInput.value) - 1;
        updateTotalPrice();
    }
}

// 총 상품금액 업데이트 함수
function updateTotalPrice() {
    var priceText = document.getElementById("product-price").innerText; // 제품 가격 텍스트 가져오기
    var price = parseInt(priceText.replace(/[^\d]/g, '')); // 가격 가져오기
    var quantity = parseInt(document.getElementById("quantity").value); // 수량 가져오기

    var totalPrice = price * quantity; // 총 상품금액 계산

    document.getElementById("total-price").innerText = totalPrice.toLocaleString() + "원"; // 총 상품금액 업데이트
}

// 페이지 로드시 총 상품금액 업데이트
window.onload = function() {
    updateTotalPrice();
};



document.addEventListener('DOMContentLoaded', function () {
    var price = parseFloat(document.getElementById('product-price').innerText); // 상품 가격을 가져옵니다.
    var buttons = document.querySelectorAll('.btn-primary'); // 모든 btn-primary 클래스를 가진 버튼을 가져옵니다.

    // 버튼 클릭 시
    buttons.forEach(function(button) {
        button.addEventListener('click', function () {
            if (price === 0) { // 상품 가격이 0인 경우
                alert('전문의약품은 구매할 수 없습니다.');
            }
        });
    });
});




//김동현 값 넘겨주기
//수정된 sendItem() 함수
function sendItem() {
	var itemNum = parseInt($('#itemNum').text());
	var itemName = $('#itemName').text();
	var priceText = $('#product-price').text();
	var price = parseInt(priceText.replace(/[^\d]/g, ''));
	var quantity = parseInt($('#quantity').val());
	const data = {
			 itemNum:  parseInt($('#itemNum').text()),
			 itemName: $('#itemName').text(),
			 price: price = parseInt(priceText.replace(/[^\d]/g, '')),
			 quantity:parseInt($('#quantity').val())
			};
	$.ajax({
	   type: 'get',
	   url: '/sel_Detail/singleOrder',
	   data: data,
	   success: function(response) {
	       console.log('데이터 전송 성공:', response);
	       location.href = "/payAndmarket/one";
	   },
	   error: function(xhr, status, error) {
	       console.error('데이터 전송 실패:', status, error);
	   }
	});
	}


	function inCart() {
		 var itemName = $('#itemName').text();
		 var priceText = $('#product-price').text();
		 var price = parseInt(priceText.replace(/[^\d]/g, ''));
		 var quantity = parseInt($('#quantity').val());
		 const data = {
				 itemName: $('#itemName').text(),
				 price: price = parseInt(priceText.replace(/[^\d]/g, '')),
				 quantity:parseInt($('#quantity').val())
				};
		 $.ajax({
		     type: 'get',
		     url: '/Cart/insert',
		     data: data,
		     success: function(response) {
		         console.log('데이터 전송 성공:', response);
		         location.href = "/Cart";
		     },
		     error: function(xhr, status, error) {
		         console.error('데이터 전송 실패:', status, error);
		     }
		 });
		}
