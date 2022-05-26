/**
 * validate tất cả các form nằm trong đây
 */
 // Kiểm tra chon địa điểm đi, đến khi chọn vé
 function validateLoaiVeForm() {
	let diemDi = document.getElementById('chonDiemDi');
	let diemDen = document.getElementById('chonDiemDen');
	if (diemDi.value == diemDen.value) {
		alert("Điểm đi và điểm đến không được trùng nhau!");
		return false;
	}
	return true;
}

function validateThemTuyenXeForm() {
	const diemDi = document.getElementById('diaDiemDi');
	const diemDen = document.getElementById('diaDiemDen');
	if (diemDi.value === diemDen.value) 
	{
		alert("Điểm đi và điểm đến không được trùng nhau!");
		return false;
	}
	return true;
}
 // loại vé
 (function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  let forms = document.querySelectorAll('.validation-loaive')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
})();

// thông tin
 (function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  let forms = document.querySelectorAll('.infoValidation')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
})();

// đăng nhập
 (function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  let forms = document.querySelectorAll('.loginValidation')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
})();

// đăng kí vé offline
 (function () {
		  'use strict'

		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  let forms = document.querySelectorAll('.validateKhachHang')

		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }

		        form.classList.add('was-validated')
		      }, false)
		    })
})();

 