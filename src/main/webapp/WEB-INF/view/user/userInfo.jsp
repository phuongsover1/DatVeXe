<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>

	<!--  end navbar  -->
	<!-- body -->
	
	<!-- CHO TÀI KHOẢN KHÁCH HÀNG -->
	<sec:authorize access="hasRole('USER')">	
	<div class="container ">
		<h3 class="text-center my-4">Thông tin cá nhân</h3>
		<div class="row mt-5">
			<div class="col-12 col-md-8 col-xl-6">
				<div class="card m-auto " style="max-width: 700px;">
					<div class="card-header text-muted">THÔNG TIN CÁ NHÂN</div>
					<div class="card-body">
						<div class="row my-4">
							<div class="col-5 text-muted">Họ và Tên:</div>
							<div class="col-7">${user.hoTen}</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Email:</div>
							<div class="col-7">${user.email }</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Số điện thoại:</div>
							<div class="col-7">${user.phoneNumber }</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">CMND/CCCD:</div>
							<div class="col-7">${user.cmnd }</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Giới tính:</div>
							<c:choose>
								<c:when test="${user.gioiTinh == null}">
									<div class="col-7 text-muted">Chưa có thông tin</div>
								</c:when>
								<c:otherwise>
									<div class="col-7">${user.gioiTinh }</div>
								</c:otherwise>
							</c:choose>

						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Ngày sinh:</div>
							<c:choose>
								<c:when test="${user.ngaySinh == null}">
									<div class="col-7 text-muted">Chưa có thông tin</div>
								</c:when>
								<c:otherwise>
									<div class="col-7">${user.ngaySinh }</div>
								</c:otherwise>
							</c:choose>


						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Nghề nghiệp:</div>
							<c:choose>
								<c:when test="${user.ngheNghiep == null}">
									<div class="col-7 text-muted">Chưa có thông tin</div>
								</c:when>
								<c:otherwise>
									<div class="col-7">${user.ngheNghiep }</div>
								</c:otherwise>
							</c:choose>


						</div>
					</div>
				</div>
			</div>

			<div
				class="col-12 col-md-4 col-xl-6 d-flex justify-content-center align-items-center">
				<a class="my-4 mt-md-0 btn btn-success"
					href="${pageContext.request.contextPath }/user/changeUserInfo?userId=${user.userId}">Cập
					nhật thông tin</a>
			</div>
		</div>



	</div>
	</sec:authorize>
	
	
	<!-- CHO NHÂN VIÊN -->
	<sec:authorize access="hasRole('EMPLOYEE')" >
			<div class="container ">
		<h3 class="text-center my-4">Thông tin cá nhân</h3>
		<div class="row mt-5">
			<div class="col-12 col-md-8 col-xl-6">
				<div class="card m-auto " style="max-width: 700px;">
					<div class="card-header text-muted">THÔNG TIN CÁ NHÂN</div>
					<div class="card-body">
						<div class="row my-4">
							<div class="col-5 text-muted">Họ và Tên:</div>
							<div class="col-7">${nhanVien.hoTen}</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Email:</div>
							<div class="col-7">${nhanVien.email }</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Số điện thoại:</div>
							<div class="col-7">${nhanVien.soDienThoai }</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">CMND/CCCD:</div>
							<div class="col-7">${nhanVien.cmnd }</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Giới tính:</div>
								<div class="col-7">${nhanVien.gioiTinh }</div>
						</div>

						<div class="row my-4">
							<div class="col-5 text-muted">Ngày sinh:</div>
							<c:choose>
								<c:when test="${nhanVien.ngaySinh == null}">
									<div class="col-7 text-muted">Chưa có thông tin</div>
								</c:when>
								<c:otherwise>
									<div class="col-7">${nhanVien.ngaySinh }</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>

			<div
				class="col-12 col-md-4 col-xl-6 d-flex justify-content-center align-items-center">
				<a class="my-4 mt-md-0 btn btn-success"
					href="${pageContext.request.contextPath }/nhanvien/changeInfo">Cập
					nhật thông tin</a>
			</div>
		</div>
	</div>
	</sec:authorize>
	


<%@ include file="/WEB-INF/view/footer.jsp" %>