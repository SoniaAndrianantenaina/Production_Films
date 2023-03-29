<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="mg.spring_mvc.model.Personnage, java.util.List" %>
<%
    List<Personnage> perso = (List<Personnage>) request.getAttribute("personnages");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perso</title>
        <!-- font icons -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/vendors/themify-icons/css/themify-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/css/listPersonnages.css">
        <!-- Bootstrap + Creative Studio main styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/css/creative-studio.css">
    </head>
    <body>

        <a href="./liste">
            <button class="page-link" href="#"><i class="ti-angle-double-left"></i>
                <span></span> listes des scenes
            </button>
        </a>


        <h1>Listes des personnages selectionnes</h1>
        <br>
        <% for (Personnage p : perso) {%>
        <a class="nav-link" href="scene_Personnage?id=<%=p.getId()%>"><%=p.getNom()%></a>
        <% }%>

        <!-- core  -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/jquery/jquery-3.4.1.js"></script>
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.bundle.js"></script>

        <!-- bootstrap affix -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.affix.js"></script>

        <!-- Creative Studio js -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/js/creative-studio.js"></script>
    </body>
</html>
