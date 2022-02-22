<html>
<body>
<h1>Asta este un fisier jsp</h1>

<% java.util.Date date = new java.util.Date(); %>
Hello! <br> The time is
<%
    out.println(String.valueOf(date));
    out.println( "<br>Your machines address is " );
    out.println( request.getRemoteHost());
%>
</body>
</html>
