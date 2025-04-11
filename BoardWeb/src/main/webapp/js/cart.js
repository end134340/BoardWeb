/*
 * 
 */

//수량
let pNum = document.querySelectorAll('input[name="p_num0"]');
countVal();
//updown버튼
let upBtn = document.querySelectorAll('div.updown span i.up');
let dwBtn = document.querySelectorAll('div.updown span i.down');

upBtn.forEach(item => {
  item.addEventListener('click', function (e) {
    let su = e.target.parentElement.previousElementSibling;
    su.value++
    changeVal({ target: su }); //강제 이벤트 걸기
  })
})

dwBtn.forEach(item => {
  item.addEventListener('click', function (e) {
    let su = e.target.parentElement.previousElementSibling.previousElementSibling;
    if (su.value > 1) {
      su.value--;
      changeVal({
        target: su
      });
    } else if (su.value == 1) {
      alert('1개 이하는 구매할 수 없습니다.');
    }
  })
  countVal();
})

pNum.forEach(item => {
  item.addEventListener('input', changeVal);
})

function changeVal(e) {
  if (e.target.value < 1) {
    alert('1개 이하는 구매할 수 없습니다.');
    e.target.value = 1;
  } else {
    let sum = e.target.parentElement.parentElement.nextElementSibling;
    let ea = e.target.parentElement.parentElement.previousElementSibling.firstElementChild;
    let result = ea.value * e.target.value;
    result = result.toLocaleString();

    sum.innerHTML = result + '원';
  }
  countVal();
  totalPrice();
}

let delBtn = document.querySelectorAll('.abutton');

delBtn.forEach(item => {
  item.addEventListener('click', delFnc)

})

function delFnc(e) {
  let pClass = e.target.parentElement;
  if (pClass.classList.contains('basketcmd')) {
    pClass.parentElement.parentElement.remove();
  } else if (pClass.classList.contains('basketrowcmd')) {
    if (e.target.innerHTML == '장바구니비우기') {
      document.querySelectorAll('div.row.data').forEach(item => item.remove());

    } else if (e.target.innerHTML == '선택상품삭제') {
      let checkbox = document.querySelectorAll('input[name="buy"]');
      checkbox.forEach(item => {
        if (item.checked) {
          item.parentElement.parentElement.parentElement.remove();

        }
      })
    }
  }
  countVal();
  totalPrice();
}

//전체 합계정보 
function totalPrice() {
  let price = 0;
  document.querySelectorAll('div.sum').forEach((item, idx) => {
    if (idx) {
      let str = item.innerHTML.substring(0, item.innerHTML.length);
      price += parseInt(str.replace(/,/g, ''));
    }
  })
  document.querySelector('div#sum_p_price span').innerHTML = price.toLocaleString();
}

//상품합계
function countVal() {
  pNum = document.querySelectorAll('input[name="p_num0"]');
  let total = document.querySelector('#sum_p_num span');
  total.innerHTML = '';
  let result = 0;
  pNum.forEach(item => {
    result += parseInt(item.value);
  });
  total.innerHTML = result;
}