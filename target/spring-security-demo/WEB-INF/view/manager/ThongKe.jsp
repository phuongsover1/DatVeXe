<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <head>
 	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <script src="https://kit.fontawesome.com/d6bdcb3119.js"
             crossorigin="anonymous"
     ></script>
     <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/resources/img/manager.ico"/>

     <link rel="stylesheet"
           href="${pageContext.request.contextPath }/resources/css/style.css"
     >
     <link rel="stylesheet"
           href="${pageContext.request.contextPath }/resources/css/custom_bootstrap.css"
     >
     <title>Manager</title>

     <script type="text/javascript">
function drawchart() {

var dps = [[]];

var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light2", // "light1", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: "BIỂU ĐỒ THỂ HIỆN PHẦN TRĂM DOANH THU CỦA CÁC TUYẾN XE"
	},
	data: [{
		type: "pie",
		showInLegend: "true",
		legendText: "{label}",
		yValueFormatString: "#,###\"%\"",
		indexLabelFontSize: 16,
		indexLabel: "{label} - {y}",
		dataPoints: dps[0]
	}]
});

var yValue;
var label;

<c:forEach items="${doanhthu}" var="dataPoints" varStatus="loop">
	<c:forEach items="${dataPoints}" var="dataPoint">
			yValue = parseFloat("${dataPoint.tien}");
			label = "${dataPoint.ten}";
			dps[parseInt("${loop.index}")].push({
				label : label,
				y :yValue,
			});
	</c:forEach>
</c:forEach>

chart.render();
}
</script>
</head>
<body onload="drawchart()" style="margin-left: 20vw" class="p-4">
<!-- Menu -->
<div class="menu pt-3 px-4 d-flex flex-column gap-3">
    <a
            href="${pageContext.request.contextPath }/quanly/?isShowList=true"
            class="item"
    >
        <i class="fas fa-users"></i>
        Quản Lý Nhân Viên
    </a>
    <div class="quan-ly-tuyen-xe">
        <div class="item d-flex flex-row justify-content-between"
             role="button"
        >
            <div>
                <i class="fas fa-bus"></i>
                Tuyến Xe
            </div>
            <i class="fas fa-caret-down"></i>
        </div>
        <ul class="list">
            <li>
                <a
                        href="${pageContext.request.contextPath }/quanly/?isQuanLyTuyenXe=true"
                >Quản Lý Tuyến Xe</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath }/quanly/?isQuanLyDiaDiem=true">Quản Lý Địa Điểm</a>
            </li>
        </ul>
        <a
                href="${pageContext.request.contextPath }/quanly/thongKe"
                class="item"
        >
            <i class="fas fa-users"></i>
            Thống Kê
        </a>
        <form:form id="form2"
                   action="${pageContext.request.contextPath }/logout" method="POST"
        >
            <button type="submit" class="dropdown-item" form="form2">
                <i class="fas fa-sign-out-alt"></i>
                Đăng xuất
            </button>
        </form:form>
    </div>
</div>
	<section class="section mb-5">
	<div id="chartContainer" style="height: 370px; width: 100%;"></div>
	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	</section>
	<div class="dropdown">
	  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
	    Các Năm
	  </button>
	  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
	    <c:forEach items="${listNam}"  var="Nam">
	    			<li><a class="dropdown-item" href="${pageContext.request.contextPath }/quanly/thongKe/${Nam}">${Nam}</a></li>
	    </c:forEach>
	  </ul>
	</div>
	<table class="table table-success table-striped">
 		<thread>
 			<tr>
 				<th scope="col">Tháng</th>
 				<th scope="col">Doanh Thu</th>
 			</tr>
 		</thread>
 		<tbody>
 				<c:forEach items="${doanhthulist}" var="p">
 					<tr>
 						<th scope="row">${p.thang}</th>
 						<td>${p.tien}</td>
 					</tr>
 				</c:forEach>
 		</tbody>
	</table>
</body>
</html>