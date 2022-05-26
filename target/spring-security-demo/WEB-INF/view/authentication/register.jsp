<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>



	<div class="container">
		<h3 class="text-center mt-4">THÔNG TIN TÀI KHOẢN</h3>

		<c:if test="${param.tontaiTK != null }">
			<div class="alert alert-danger" role="alert">Số điện thoại đã
				tồn tại.</div>
		</c:if>

		<c:if test="${param.tontaiUser != null }">	
				<div class="card">
					<div class="card-body">
						<p class="card-text">
							Số điện thoại này đã từng được anh/chị ${tempUser.hoTen } đặt vé tại quầy nhưng chưa tạo tài khoản.<br />
							Thông tin tài khoản bạn có thể chỉnh sửa sau khi đăng kí<br />
							Bạn có muôn tạo tài khoản này với mật khẩu vừa đặt?<br />

						</p>
					</div>
					<div class="card-footer">
						<form:form
					action="${pageContext.request.contextPath }/saveNewAccount"
					method="POST" modelAttribute="tempAccount">
					<a class="btn btn-warning"
						href="${pageContext.request.contextPath }/showRegisterPage">Không</a>
					<input type="submit" value="Đồng ý" class="btn btn-primary">
				</form:form>
					</div>
				</div>


			
		</c:if>



		<form action="${pageContext.request.contextPath }/saveNewUser"
			method="POST" class="m-auto mt-5" style="width: 400px;">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div class="mb-3">
				<label for="nameInput" class="form-label">Họ tên *</label>
				<spring:bind path="newUser.hoTen">
					<input type="text" class="form-control" id="nameInput"
						name="${status.expression}" value="${status.value}">
					<br />

				</spring:bind>
			</div>
			<div class="mb-3">
				<label for="phoneInput" class="form-label">Số điện thoại *</label>
				<spring:bind path="newAccount.username">
					<input type="text" class="form-control" id="phoneInput"
						name="${status.expression}" value="${status.value}">
					<br />
				</spring:bind>
			</div>

			<div class="mb-3">
				<label for="passwordInput" class="form-label">Password *</label>

				<spring:bind path="newAccount.password">
					<input type="password" class="form-control" id="passwordInput"
						name="${status.expression}" value="${status.value}">
					<br />
				</spring:bind>
			</div>

			<div class="mb-3">
				<label for="emailInput" class="form-label">Email *</label>
				<spring:bind path="newUser.email">
					<input type="email" class="form-control" id="emailInput"
						name="${status.expression}" value="${status.value}">
					<br />
				</spring:bind>

			</div>

			<div class="mb-3">
				<label for="idNoInput" class="form-label">CMND/CCCD *</label>
				<spring:bind path="newUser.cmnd">
					<input type="text" class="form-control" id="idNoInput"
						name="${status.expression}" value="${status.value}">
					<br />
				</spring:bind>

			</div>

			<div class="mb-3">
				<label for="addressInput" class="form-label">Địa chỉ *</label>
				<spring:bind path="newUser.diaChi">
					<input type="text" class="form-control" id="addressInput"
						name="${status.expression}" value="${status.value}">
					<br />
				</spring:bind>

			</div>

			<button type="submit" class="btn btn-outline-success float-end">Đăng
				ký</button>

		</form>
	</div>
	<div class="clearfix"></div>

<%@ include file="/WEB-INF/view/footer.jsp" %>