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
			<div class="container"> <b><a class="navbar-brand d-flex align-items-center">Customer List</a></b>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation"> <i class="fa fa-bars"></i> </button>
				<div class="collapse navbar-collapse navbar-right justify-content-end" id="navbarNav">

					<a class="navbar-brand d-flex align-items-center">Welcome ${logged_user}!</a>
					<ul class="navbar-nav">
						<li class="nav-item"><a class="btn btn-outline-light "  href="/logout">Log Out</a></li>
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
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Phone</th>
                <th scope="col">E-mail</th>
                <th scope="col">Address</th>
                <th scope="col">Birthday</th>
                <th scope="col">Actions<a href='/customers/add/' class="btn chic-a-button" style="margin-left:10px;  background-color: green;">Add customer</a></th>
            </tr>

            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td><c:out value="${customer.id}" /></td>
                    <td><c:out value="${customer.name}" /></td>
                    <td><c:out value="${customer.phone}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.birthday}" /></td>
                    <td>
                        <a href="<c:url value="/customers/${customer.id}/edit"/> " class="btn chic-a-button">Edit</a>
                        <a href="<c:url value="/customers/${customer.id}/orders"/> " class="btn chic-a-button">View Orders</a>
   			            <a href="<c:url value="/customers/${customer.id}/reports"/>"><img src="/images/icons/xlsx.png"></a>
                        <a href="<c:url value="/customers/${customer.id}/delete"/>" class="btn btn-danger">Delete</a>
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


