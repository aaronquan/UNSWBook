<!-- FROM: https://bootsnipp.com/snippets/oVVBy -->
<fieldset>
	<form class="form-horizontal" action="EditProfileServlet" method="POST">
		<div class="form-group">
		  <label class="col-md-4 control-label" for="name">Name</label>  
		  <div class="col-md-4">
		  <input id="name" name="name" type="text" value="${user.getName()}" class="form-control input-md">
		  </div>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="email">Email</label>  
		  <div class="col-md-4">
		  <input id="email" name="email" type="text" value="${user.getEmailAddress()}" class="form-control input-md">
		  </div>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="gender">Gender</label>
		  <div class="col-md-4">
		    <select id="gender" name="gender" class="form-control" selected="${user.getGender()}">
		      <option value="None">None</option>
		      <option value="Male">Male</option>
		      <option value="Female">Female</option>
		      <option value="Other">Other</option>
		    </select>
		  </div>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="age">Age</label>  
		  <div class="col-md-4">
		  <input id="age" name="age" type="text" value="${user.getAge()}" class="form-control input-md">
		  </div>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="password">Password</label>  
		  <div class="col-md-4">
		  <input id="password" name="password" type="password" class="form-control input-md">
		  </div>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="confirm_password">Confirm password</label>  
		  <div class="col-md-4">
		  <input id="confirm_password" name="confirm_password" type="password" class="form-control input-md">
		  </div>
		</div>
		
		<div class="form-group">
		  <label class="col-md-4 control-label" for="submit"></label>
		  <div class="col-md-4">
			<input type="submit" value="Save" class="btn btn-success">
		  </div>
		</div>
		<input type="hidden" name="uid" value="${sessionScope.user}">
	</form>
</fieldset>