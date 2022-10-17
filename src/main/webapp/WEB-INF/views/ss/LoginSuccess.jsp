
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>로그인 성공</title>
    <link rel="stylesheet" href="/css/table.css"/>

    <%
        String userName = CmmUtil.nvl((String) request.getAttribute("userName"));
    %>
</head>
<body>

<%=userName%> 로그인 성공

</body>
</html>