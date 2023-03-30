<%-- 
    Document   : planning
    Created on : 7 mars 2023, 21:43:03
    Author     : 26132
--%>

<%@page import="java.util.List"%>
<%@page import="mg.spring_mvc.model.Scene"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Scene> list = (List<Scene>) request.getAttribute("listscene");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Planning</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/vendors/themify-icons/css/themify-icons.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/css/creative-studio.css">
    </head>
    <body data-spy="scroll" data-target=".navbar" data-offset="40" id="home" style="background-color: black">
        <nav class="navbar custom-navbar navbar-expand-lg navbar-dark" data-spy="affix" data-offset-top="20" >
            <div class="container">
                <a class="navbar-brand" href="#">
                    <img src="${pageContext.request.contextPath}/resources/theme/assets/imgs/logo.png" alt="Download free bootstrap 4 landing page, free boootstrap 4 templates, Download free bootstrap 4.1 landing page, free boootstrap 4.1.1 templates, Creative studio Landing page">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span></span>
                </button>
                <a href="./liste">
                    <button class="page-link"><i class="ti-angle-double-left"> retour </i>
                        <span></span>
                    </button>
                </a>

            </div>
        </nav>

        <section id="planning" >
            <div class="container">
                <div class="content-wrapper">
                    <form action="Planifier" method="post">
                        <div class="contact-card" style="margin-top: 60px;">
                            <div class="infos">
                                <h6 class="section-title mb-4">Planifier</h6>

                                <div class="item">
                                    <i class="ti-calendar"></i>
                                    <div>
                                        <h5>Date Debut</h5>
                                        <div class="form-group">
                                            <input type="date" name="datedebut" class="form-control form-control-lg" required>
                                        </div>
                                    </div>                          
                                </div>
                                <div class="item">
                                    <i class="ti-calendar"></i>
                                    <div>
                                        <h5>Date Fin</h5>
                                        <div class="form-group">
                                            <input type="date" name="datefin" class="form-control form-control-lg" required>
                                        </div>
                                    </div>                          
                                </div>
                                <div class="item">
                                    <i class="ti-archive"></i>
                                    <div class="mb-0">
                                        <h5>Planning</h5>
                                        <p>
                                        <div class="form-group">
                                            <select class="form-control" id="exampleFormControlSelect1" style="width: 200px" name="type">
                                                <option value="1">Plateau</option>
                                                <option value="2">Acteur</option>
                                            </select>
                                        </div>
                                        </p>
                                    </div>
                                </div>

                            </div>
                            <div class="form">
                                <h6 class="section-title mb-4">Scenes</h6>
                                <div class="row" >

                                    <% for (int i = 0; i < list.size(); i++) {%>  
                                    <div class="form-check-group col-3">
                                        <p><input  type="checkbox" value="<%=list.get(i).getId()%>" class="form-check" name="scenes">
                                            <%=list.get(i).getNom()%></p>
                                    </div>
                                    <%}%>
                                    <button type="submit" class="btn btn-primary btn-block btn-lg mt-3">Planifier</button>
                                </div>
                            </div>
                    </form>

                </div>

            </div>
        </section>

        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/jquery/jquery-3.4.1.js"></script>
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.bundle.js"></script>

        <!-- bootstrap affix -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.affix.js"></script>

        <!-- Creative Studio js -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/js/creative-studio.js"></script>
    </body>
</html>
