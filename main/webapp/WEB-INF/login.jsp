<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css">

<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
	  rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
	integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
        crossorigin="anonymous"></script>
</head>
<body>
	<main class="d-flex flex-column">
		<header class="top">
                <div class="row">
                    <div class="col-sm-4 offset-md-4">
                        <h1 class="title">Sign in</h1> </div>
                </div>
            </header>
		<div class="container">
			<article class="col-md-12">
				<div class="row">
					<div class="col-sm-4 offset-md-4">
						<form class="panel" role="form" method="post">
							<c:if test="${not empty error}">
								<div class="alert alert-danger">Error: ${error}</div>
							</c:if>
							<div class="row">
								<div class="col-md-12">
									<label class="floating-label">Username</label>
									<input type="text" id="user" name="user" >
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<label class="floating-label">Password</label>
									<input type="password"  id="password" name="password" >
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<button class="chic-button">Login</button>
								</div>
								<div class="col-md-12" style="margin-top:15px">
								    No account? <a href='/user/add'>Create one!</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</article>
		</div>
	</main>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="js/formscript.js"></script>

<script>
    $('#user').focus();
    $('#user').select();
</script>
</html>