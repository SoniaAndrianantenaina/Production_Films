<%@page import="mg.spring_mvc.model.Personnage"%>
<%@page import="mg.spring_mvc.model.ScenePersonnage"%>
<%@page import="mg.spring_mvc.model.Scene"%>
<%
    Scene scene = (Scene)(request.getAttribute("scene"));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="./listPersoScene" >personnage film</a>
    </body>
</html>
