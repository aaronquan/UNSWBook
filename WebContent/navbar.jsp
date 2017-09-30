<!-- FROM: https://bootsnipp.com/snippets/v8QNE -->
<nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a href="Home" class="navbar-brand">UNSWBook</a>
    </div>
      <a class="btn navbar-left" style="padding-top: 14px;" href="advancedSearchServlet">Advanced Search</a>
      <form class="navbar-form navbar-left" action="searchServlet" method="POST">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="search users..." name="keywords" id="keywords">
        </div>
        <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
      </form>
	  <a class="btn navbar-nav" style="padding-top: 14px;" href="Profile">${user.getName()}</a>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span></a>
            <div class="dropdown-menu" role="menu">
            <div class="media">
              <a class="media-left" href="#">
               <img src="http://placehold.it/64x64&text=Mirchu.net" alt="ALT NAME">
              </a>
              <div class="media-body">
                <h4 class="media-heading">Media heading</h4>
                Cras sit amet nibh libero, in gravida nulla.
              </div>
            </div>
            <div class="media">
              <a class="media-left" href="#">
               <img src="http://placehold.it/64x64&text=Mirchu.net" alt="ALT NAME">
              </a>
              <div class="media-body">
                <h4 class="media-heading">Media heading</h4>
                Cras sit amet nibh libero, in gravida nulla.
              </div>
            </div>
            <div class="media">
              <a class="media-left" href="#">
               <img src="http://placehold.it/64x64&text=Mirchu.net" alt="ALT NAME">
              </a>
              <div class="media-body">
                <h4 class="media-heading">Media heading</h4>
                Cras sit amet nibh libero, in gravida nulla.
              </div>
            </div>
          </div>   
        </li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-globe"></span></a>
            <div class="dropdown-menu" role="menu">
            <div class="media">
              <a class="media-left" href="#">
               <img src="http://placehold.it/64x64&text=Mirchu.net" alt="ALT NAME">
              </a>
              <div class="media-body">
                <h4 class="media-heading">Media heading</h4>
                Cras sit amet nibh libero, in gravida nulla.
              </div>
            </div>
            <div class="media">
              <a class="media-left" href="#">
               <img src="http://placehold.it/64x64&text=Mirchu.net" alt="ALT NAME">
              </a>
              <div class="media-body">
                <h4 class="media-heading">Media heading</h4>
                Cras sit amet nibh libero, in gravida nulla.
              </div>
            </div>
            <div class="media">
              <a class="media-left" href="#">
               <img src="http://placehold.it/64x64&text=Mirchu.net" alt="ALT NAME">
              </a>
              <div class="media-body">
                <h4 class="media-heading">Media heading</h4>
                Cras sit amet nibh libero, in gravida nulla.
              </div>
            </div>
          </div>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>&#32;<span class="caret"></span></a>
          <ul class="dropdown-menu setting" role="menu">
            <li><a href="EditProfileServlet">Edit profile</a></li>
            <li class="divider"></li>
            <li><a href="Logout">Logout</a></li>
          </ul>
        </li>
      </ul>
    </div>
</nav>