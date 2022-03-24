<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />

		<title>Invoice</title>


		<!-- Invoice styling -->
		<style>
			body {
				font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
				text-align: center;
				color: #777;
			}

			body h1 {
				font-weight: 300;
				margin-bottom: 0px;
				padding-bottom: 0px;
				color: #000;
			}

			body h3 {
				font-weight: 300;
				margin-top: 10px;
				margin-bottom: 20px;
				font-style: italic;
				color: #555;
			}

			body a {
				color: #06f;
			}

			.invoice-box {
				max-width: 800px;
				margin: auto;
				padding: 30px;
				border: 1px solid #eee;
				box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
				font-size: 16px;
				line-height: 24px;
				font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
				color: #555;
			}

			.invoice-box table {
				width: 100%;
				line-height: inherit;
				text-align: left;
				border-collapse: collapse;
			}

			.invoice-box table td {
				padding: 5px;
				vertical-align: top;
			}

			.invoice-box table tr td:nth-child(2) {
				text-align: right;
			}

			.invoice-box table tr td:nth-child(3) {
				text-align: right;
			}

			.invoice-box table tr.top table td {
				padding-bottom: 20px;
			}

			.invoice-box table tr.top table td.title {
				font-size: 45px;
				line-height: 45px;
				color: #333;
			}

			.invoice-box table tr.information table td {
				padding-bottom: 40px;
			}

			.invoice-box table tr.heading td {
				background: #eee;
				border-bottom: 1px solid #ddd;
				font-weight: bold;
			}

			.invoice-box table tr.details td {
				padding-bottom: 20px;
			}

			.invoice-box table tr.item td {
				border-bottom: 1px solid #eee;
			}

			.invoice-box table tr.item.last td {
				border-bottom: none;
			}

			.invoice-box table tr.total td:nth-child(2) {
				border-top: 2px solid #eee;
				font-weight: bold;
			}

			@media only screen and (max-width: 600px) {
				.invoice-box table tr.top table td {
					width: 100%;
					display: block;
					text-align: center;
				}

				.invoice-box table tr.information table td {
					width: 100%;
					display: block;
					text-align: center;
				}
			}
		</style>
	</head>

	<body>
		<div class="invoice-box">
			<table>
				<tr class="top">
					<td colspan="2">
						<table>
							<tr>
                                <c:set var="scheme" value="${pageContext.request.scheme}"/>
                                <c:set var="serverName" value="${pageContext.request.serverName}"/>
                                <c:set var="serverPort" value="${pageContext.request.serverPort}"/>
                                <c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>

								<td class="title" align="left">
									<img src="${baseURL}/images/logo.png" width="250px"/>
								</td>

								<td>
									Invoice #: ${order.number}<br/>
									Created: ${order.placed}<br />
									Delivery: ${order.delivery}
								</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr class="information">
					<td colspan="2">
						<table>
							<tr>
								<td>
									Cosmin Gligan<br/>
									12345 Sunny Road<br/>
									cosmin.gligan@gmail.com
								</td>

								<td>
									${order.customer.name}<br/>
									${order.customer.address}<br/>
									${order.customer.email}
								</td>
							</tr>
						</table>
					</td>
				</tr>
                </table>

                <table>
				<tr class="heading">
					<td>Payment Method</td>
					<td >&nbsp;</td>
					<td>Check #</td>
				</tr>

				<tr class="details">
					<td>Check</td>
                    <td >&nbsp;</td>
					<td>&#36;${order.getValue()}</td>
				</tr>

				<tr class="heading">
					<td>Item</td>
			        <td>Image</td>
					<td>Price</td>
				</tr>

                <c:forEach items="${order.orderProducts}" var="orderProduct">
                    <tr class="item">
                        <td>${orderProduct.product.name}</td>
                        <td><img src="${baseURL}/${orderProduct.product.image}" width="50px" height="50px"/></td>
                        <td>&#36;${orderProduct.value}</td>
                    </tr>
                </c:forEach>


				<tr class="total">
					<td colspan="2"></td>

					<td>Total: &#36;${order.getValue()}</td>
				</tr>
			</table>
		</div>
	</body>
</html>