<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Add Order</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<main class="d-flex flex-column">
		<header class="top">
            <div class="row">
                <div class="col-sm-4 offset-md-4">
                    <h1 class="title">Add new Order for <i>${customer.name}</i></h1>
                </div>
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
                                        <label class="floating-label">Order Number:</label>
										<input type="text" id="orderNumber" name="number">
									</div>
                                </div>

							    <div class="row">
									<div class="col-md-12">
										<button class="chic-button">Save</button>
									    <a href="/customers/${customer.id}/orders/" style="text-decoration:none">
                                           <input type="button" class="button-exit" value="Exit"/>
                                        </a>
									</div>
								</div>
                            </form>
                        </div>
                    </div>
                    <!-- END SIGN UP -->
                </article>
		</div>
	</main>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="/js/formscript.js"></script>
</body>
<script>
    $('#orderNumber').focus();
    $('#orderNumber').select();

</script>
</html>