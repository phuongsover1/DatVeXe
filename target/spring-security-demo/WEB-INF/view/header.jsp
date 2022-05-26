<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Vé xe Phương Trang</title>
<script src="https://kit.fontawesome.com/d6bdcb3119.js"
	crossorigin="anonymous"></script>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/resources/img/london_bus.ico"/>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/custom_bootstrap.css">
</head>

<body>
	<!-- ////////////////////////// Main Navbar-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid px-xl-5">
			<img
				src="${pageContext.request.contextPath }/resources/img/tiger left.png"
				class="img-fluid d-none d-xl-inline" style="width: 100px;" alt="">
			<a class="pe-1" href="${pageContext.request.contextPath }"><img
				src="${pageContext.request.contextPath }/resources/img/logo.png"
				alt="" style="width: 200px;"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#mainNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>


			<div class="collapse navbar-collapse" id="mainNavbar">
				<ul class="navbar-nav w-100 justify-content-lg-between">
					<li class="nav-item mx-2"><a
						class="nav-link px-0 fw-bolder text-uppercase" href="${pageContext.request.contextPath }">Trang
							Chủ</a></li>
					<li class="nav-item mx-3 "><a
						class="nav-link  px-0 fw-bolder text-uppercase" href="#">Lịch
							trình</a></li>
					<li class="nav-item mx-3"><a
						class="nav-link  px-0 fw-bolder text-uppercase" href="#">Tin
							tức</a></li>

					<li class="nav-item mx-3"><a
						class="nav-link  px-0 fw-bolder text-uppercase" href="#">Liên
							hệ</a></li>

					<li class="nav-item mx-3 mr-5"><a
						class="nav-link  px-0 fw-bolder text-uppercase" href="#">Về
							chúng tôi</a></li>
					<!-- //////////////// nút đăng nhập -->

					<!-- Chưa đăng nhập thì hiện cái này -->
					<sec:authorize access="isAnonymous()">
						<a href="${pageContext.request.contextPath }/showLoginPage"
							class="btn btn-warning d-inline-block mr-auto fw-bold"> <i
							class="fas fa-user-circle"></i> Đăng nhập
						</a>
					</sec:authorize>


					<!-- Nếu đã đăng nhập mà là khách hàng rồi thì hiện cái này -->
					<sec:authorize access="hasRole('USER')">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle btn btn-warning d-inline-block mr-auto p-2 fw-bold"
							data-bs-toggle="dropdown" href="#"><i
								class="fas fa-user-circle"> </i> ${user.hoTen }</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath }/user/showInfo"><i
										class="fas fa-user"></i> Thông tin tài khoản</a></li>
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath }/user/userBookedTickets"><i
										class="fas fa-history d-inline-block"></i> Các vé đã đặt</a></li>

								<li><hr class="dropdown-divider"></li>

								<form:form id="form2"
									action="${pageContext.request.contextPath }/logout"
									method="POST">
									<li>
										<button type="submit" class="dropdown-item" form="form2">
											<i class="fas fa-sign-out-alt"></i> Đăng xuất
										</button>
									</li>
								</form:form>


							</ul></li>
					</sec:authorize>
					<!--  Nếu đã đăng nhập mà là nhân viên thanh toán thì hiện cái này -->
					<sec:authorize access="hasRole('EMPLOYEE')">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle btn btn-warning d-inline-block mr-auto p-2 fw-bold"
							data-bs-toggle="dropdown" href="#"><i
								class="fas fa-user-circle"> </i> ${nhanVien.hoTen }</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath }/nhanvien/showInfo"><i
										class="fas fa-user"></i> Thông tin tài khoản</a></li>
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath }/nhanvien/veChuaThanhToan"><i
										class="fas fa-history d-inline-block"></i> Thanh toán vé</a></li>
										
								<li><a class="dropdown-item" href="${pageContext.request.contextPath }/nhanvien/nghiepVu"><i class="fas fa-bus-alt"></i> Thêm chuyến xe</a></li>

								<li><hr class="dropdown-divider"></li>

								<form:form id="form1"
									action="${pageContext.request.contextPath }/logout"
									method="POST">
									<li>
										<button type="submit" class="dropdown-item" form="form1">
											<i class="fas fa-sign-out-alt"></i> Đăng xuất
										</button>
									</li>
								</form:form>


							</ul></li>
					</sec:authorize>

				</ul>
			</div>
			<img
				src="${pageContext.request.contextPath }/resources/img/tiger right.png"
				class="img-fluid d-none d-xl-inline" style="width: 100px;" alt="">
		</div>


	</nav>