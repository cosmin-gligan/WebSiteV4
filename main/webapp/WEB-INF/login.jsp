<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Login</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
 <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
          rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
        integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
        crossorigin="anonymous"></script>
<style>
html, body {
    margin: 0;
    padding: 0;
    min-height: 100%;
}

body {
    background-image: url('/images/login_background3.jpg');
    background-position-x: center;
    background-position-y: bottom;
    background-repeat: no-repeat;
    background-attachment: scroll;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}

@media screen and (orientation:portrait) {
    body {
        background-position-y: top;
        -webkit-background-size: contain;
        -moz-background-size: contain;
        -o-background-size: contain;
        background-size: contain;
    }
</style>
</head>



<body>

	<div class="container">
		<form class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<label class="control-label col-sm-2" for="name">User:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="user" name="user" value="">
				</div>
				<label class="control-label col-sm-2" for="name">Password:</label>
				<div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password" value="">
				</div>
			</div>

			<c:if test="${not empty error}">
				<div class="alert alert-danger">Error: ${error}</div>
			</c:if>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button class="btn btn-primary">Login</button style='width:50%'>
					<span style="width:50%" onclick="window.location='user/add'">
                        <img src="https://d1nhio0ox7pgb.cloudfront.net/_img/v_collection_png/24x24/plain/user_add.png" alt="add user"
                        style="float:right;"
                        width="24px" height="24px">
                    </span
				</div>
			</div>
		</form>
	</div>

</body>
<script>
    $('#user').focus();
    $('#user').select();
</script>
</html>