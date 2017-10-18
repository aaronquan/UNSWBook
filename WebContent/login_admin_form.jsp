<!-- FROM: https://bootsnipp.com/snippets/featured/login-and-register-tabbed-form -->
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<div class="panel panel-login">
			<div class="panel-heading">
				<div class="row">
					<div class="col-xs-6">
						<a href="#" class="active" id="login-form-link">Login</a>
					</div>
					<div class="col-xs-6">
						<a href="#" id="register-form-link">Register</a>
					</div>
				</div>
				<hr>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form id="login-form" action="LoginAdmin" method="post" role="form" style="display: block;">
							<div class="form-group">
								<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
							</div>
							<div class="form-group">
								<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
							</div>
							<% if (((String) request.getAttribute("loginMessage")).matches(".+")) {%>
								<div class="alert alert-danger">
									${loginMessage}
								</div>
							<% } %>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-6 col-sm-offset-3">
										<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-lg-12">
										<div class="text-center">
											<a href="recoveryServlet" tabindex="5" class="forgot-password">Forgot Password?</a>
										</div>
									</div>
								</div>
							</div>
						</form>
						<form id="register-form" action="registerServlet" method="post" role="form" style="display: none;">
							<div class="form-group">
								<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
							</div>
							<div class="form-group">
								<input type="text" name="first_name" id="first_name" tabindex="1" class="form-control" placeholder="First Name" value="">
							</div>
							<div class="form-group">
								<input type="text" name="surname" id="surname" tabindex="1" class="form-control" placeholder="Surname" value="">
							</div>
							<div class="form-group">
								<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="">
							</div>
							<div class="form-group">
								<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
							</div>
							<div class="form-group">
								<input type="password" name="confirm_password" id="confirm_password" tabindex="2" class="form-control" placeholder="Confirm Password">
							</div>
							<div class="form-group">
								<div class="row">
									<div class="col-sm-6 col-sm-offset-3">
										<input type="submit" name="register_submit" id="register_submit" tabindex="4" class="form-control btn btn-login" value="Register Now">
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>