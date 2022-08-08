<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

<html>
  <head>
    <title>Customers</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	<link rel="stylesheet" href="/css/style.css">
  </head>

  <body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light">
			<div class="container"> <b><a class="navbar-brand d-flex align-items-center">Product List</a></b>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation"> <i class="fa fa-bars"></i> </button>
				<div class="collapse navbar-collapse navbar-right justify-content-end" id="navbarNav">

					<a class="navbar-brand d-flex align-items-center">Welcome ${logged_user}!</a>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="btn btn-outline-light "  href="/main">Home</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">Error: ${error}</div>
    </c:if>

	<div class="container">

       <table class="table table-bordered table-light">
            <tr>
                <th scope="col" align="center">ID</th>
                <th scope="col" align="center">Name</th>
                <th scope="col" align="center">Weight</th>
                <th scope="col" align="center">Price</th>
                <th scope="col" align="center">Image</th>
                <th scope="col" align="center">Actions<a href='/products/add/' class="btn chic-a-button" style="margin-left:3px;  background-color: green;">Add product</a></th>
            </tr>

            <c:forEach items="${products}" var="product">
                <tr>
                    <td align="center"><c:out value="${product.id}  " /></td>
                    <td><c:out value="${product.name}" /></td>
                    <td align="right"><c:out value="${product.weight}" /></td>
                    <td align="right"><c:out value="${product.price}" /></td>
                    <td align="center"><img src="${product.image}" width="50px" height="50px"/></td>
                    <td>
                        <a href="<c:url value="/products/${product.id}/edit"/> " class="btn chic-a-button">Edit</a>
                        <a href="<c:url value="/products/${product.id}/delete"/>" class="btn btn-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>

		</table>
	</div>
  </body>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="/js/formscript.js"></script>

</html>


