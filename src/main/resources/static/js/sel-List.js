 // 숫자를 10,000 형식으로 변환하는 함수
        function formatPrice(price) {
            if (price >= 1000) {
                return (price / 1000).toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "천";
            } else {
                return price;
            }
        }

        // 타임리프로 받아온 가격
        var itemPrice = /* 타임리프에서 받아온 값 */;

        // 형식화된 가격을 HTML 요소에 할당
        document.querySelector('.price').textContent = formatPrice(itemPrice) + '원';