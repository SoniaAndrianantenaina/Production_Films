<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>

<%
    String[] action = (String[]) request.getAttribute("action");
    out.println(action.length);
    for (int i = 0; i < action.length; i++) {
        out.println(action[i]);
    }

    String[] expression = (String[]) request.getAttribute("expression");
    out.println(expression.length);
    for (int i = 0; i < expression.length; i++) {
        out.println(expression[i]);
    }

    String[] dialogue = (String[]) request.getAttribute("dialogue");
    out.println(dialogue.length);
    for (int i = 0; i < dialogue.length; i++) {
        out.println(dialogue[i]);
    }

    String[] debut = (String[]) request.getAttribute("debut");
    out.println(debut.length);
    for (int i = 0; i < debut.length; i++) {
        out.println(debut[i]);
    }
    
    String[] fin = (String[]) request.getAttribute("fin");
    out.println(fin.length);
    for (int i = 0; i < fin.length; i++) {
        out.println(fin[i]);
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
