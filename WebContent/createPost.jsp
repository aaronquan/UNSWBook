<!-- FROM https://bootsnipp.com/snippets/662WP -->
<div class="container">
	<div class="col-md-12">
	    <div class="panel panel-default">
			<div class="row">
			    <div class="col-md-10 col-md-offset-1">
		    		<h4>New post</h4>
		    		<form action="PostServlet" method="POST">
		    		    <div class="form-group">
		    		        <textarea rows="5" class="form-control" name="postContent" id="postContent" style="resize: none;"></textarea>
		    		    </div>  
		    		    <div class="form-group">
		    		        <button type="submit" id="create_post_button" class="btn btn-primary">Post</button>
		    		    </div>
		    		    <input type="hidden" name="user" value="${profileUser}">
		    		</form>
				</div>
			</div>
		</div>
	</div>
</div>