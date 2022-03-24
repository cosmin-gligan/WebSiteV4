<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Orders for ${customer.name}</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<header class="top" style="margin-bottom:25px">
		<div class="row">
			<div class="col-sm-4 offset-md-4">
				<h1 class="title">Orders for ${customer.name}: <font color="yellow">&#36;${customer.getTotalSales()}</font></h1>
				<div style="text-align:center">
					<a href="<c:url value="/customers/${customer.id}/orders/add"/> " class="btn btn-light">Add New Order</a>
				    <a href="<c:url value="/customers"/>" class="btn btn-light">Back to Customers List</a>
				 </h1>
				</div>
			</div>
		</div>
	</header>
	<div class="container">

		<table class="table table-bordered table-light">
			<tr>
				<th>Number</th>
				<th>Value</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${customer.orders}" var="order">
				<tr>
					<td><c:out value="${order.number}" /></td>
					<td><c:out value="${order.value}" /></td>
					<td>
					    <a href="<c:url value="/static/customer-order-edit-rest.html#customerId=${customer.id}&orderId=${order.id}"/> " class="btn chic-a-button">Edit</a>
					    <a href="<c:url value="/customers/${customer.id}/orders/${order.id}/delete"/>" class="btn btn-danger">Delete</a>
					    <a href="<c:url value="/customers/${customer.id}/orders/${order.id}/printPDF"/>" target="_blank"><img src="/images/icons/pdf.png"></a>
					    <a href="<c:url value="/customers/${customer.id}/orders/${order.id}/printDocx"/>" target="_blank"><img src="/images/icons/word.png"></a>
                    </td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<script src="/js/formscript.js"></script>
</body>
</html>
