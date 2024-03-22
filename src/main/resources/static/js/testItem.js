function placeOrder() {
	    // 상품에 대한 주문 정보를 담은 객체들을 포함한 컨테이너 객체 생성
		console.log("placeOrder 실행");
	    var customOrderRequestsContainer = {
	        customOrderRequests: [
	            { itemNum: 3, detailCnt: 1 },
	            { itemNum: 2, detailCnt: 1 }
	        ]
	    };
	    console.log(customOrderRequestsContainer);
	    
	
	    fetch('/order/createOrder', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify(customOrderRequestsContainer) // 컨테이너 객체를 JSON
																// 형식으로 변환하여 전달
	    })
	    .then(response => {
	    	console.log(response);
	        if(response.ok) {	        	
	        	data = response.json();
	        	console.log(data);
	            return data;
	        } else {
	            throw new Error('주문을 처리하는 동안 문제가 발생했습니다. 잠시 후 다시 시도해주세요.');
	        }
	    })
	    .then(orderDetail => {
	    	console.log("orderDetiail 부분",orderDetail);
	    	console.log("orderDetiail 부분",orderDetail.orderNum);
	    	
	    	//요기 어떤 조건으로 할 지 결정해서 수정해주시면 됭
	        if(orderDetail.orderNum !== "0" ) {
	            alert("주문이 성공적으로 생성되었습니다! 주문 ID: " + orderDetail[0].orderNum);
	            // 페이지 이동
	            window.location.href = '/pay/' + orderDetail.orderNum;
	        } else {
	            throw new Error('주문 결과를 처리하는 동안 문제가 발생했습니다. 주문 ID를 찾을 수 없습니다.');
	        }
	    })
	    .catch(error => {
	        console.error('주문 생성 오류:', error);
	        alert('주문 생성 중 오류가 발생했습니다. 관리자에게 문의하세요.');
	    });
	}