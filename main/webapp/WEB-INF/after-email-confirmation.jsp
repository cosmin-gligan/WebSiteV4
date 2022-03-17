<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>E-mail sent</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
</head>
<body>

	<div class="container">

		<c:if test="${not empty error}">
            <div class="alert alert-danger">Error: ${error}</div>
        </c:if>

        <H3>A email was sent to <i>${user.email}</i>.</H3>
        <H5>Please follow to instructions to complete the activation process.</H5>
			
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <a href="<c:url value="/login"/>" class="btn btn-warning">Back to login</a>
            </div>
        </div>
	</div>

</body>
</html>
