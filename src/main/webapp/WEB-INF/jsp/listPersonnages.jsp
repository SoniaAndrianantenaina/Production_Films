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
                <span></span> listes des scenes scenes
            </button>
        </a>
<h1><center>Listes des acteurs selectionnes</center></h1>
        <br>

        <!-- Team Section -->
        <section id="team">
            <div class="container">
                <div class="row">
                    <% for (Personnage p : perso) {%>
                    <div class="col-sm-6 col-md-4">
                        <div class="card text-center mb-4">
                            <img class="card-img-top inset" src="${pageContext.request.contextPath}/resources/theme/assets/imgs/image4.png">
                            <div class="card-body">
                                <h6 class="small text-primary font-weight-bold">Acteur</h6>
                                <h5 class="card-title"><a class="nav-link" href="scene_Personnage?id=<%=p.getId()%>"><%=p.getNom()%></a></h5>

                                <div class="socials">
                                    <a href="javascript:void(0)"><i class="ti-facebook"></i> </a>
                                    <a href="javascript:void(0)"><i class="ti-github"></i></a>
                                    <a href="javascript:void(0)"><i class="ti-dropbox"></i></a>
                                    <a href="javascript:void(0)"><i class="ti-twitter"></i></a>
                                </div>
                            </div>
                        </div>

                    </div>
                    <% }%>

                </div>
            </div>
        </section>
        <!-- End of Team Sectoin -->

        <!-- core  -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/jquery/jquery-3.4.1.js"></script>
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.bundle.js"></script>

        <!-- bootstrap affix -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.affix.js"></script>

        <!-- Creative Studio js -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/js/creative-studio.js"></script>
    </body>
</html>
