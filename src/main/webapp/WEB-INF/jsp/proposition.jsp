<%-- 
    Document   : planning
    Created on : 7 mars 2023, 21:43:03
    Author     : 26132
--%>

<%@page import="mg.spring_mvc.model.Planning"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mg.spring_mvc.model.Scene"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Scene> list = (List<Scene>) request.getAttribute("listscene");
    String action = (String) request.getAttribute("action");
    ArrayList<ArrayList<Planning>> plannings = (ArrayList<ArrayList<Planning>>) request.getAttribute("plannings");
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

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#about">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#service">Service</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="planningScene">Planifier</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="listePlanning">Liste Planning</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#testimonial">Testimonial</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#blog">Blog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#contact">Contact</a>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
        <section id="about" style="margin-top: 50px">
            <div class="container">
                <div class="content-wrapper">
                    <div class="col-lg-12 col-12 text-center">
                        <h6 data-aos="fade-up"></h6>

                        <h2 class="text-white" data-aos="fade-up" data-aos-delay="200">Proposition de Planning </h2>
                    </div>
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <form action="valider_proposition" method="post"> 
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                                <tr>
                                                    <th>
                                                        Date
                                                    </th>
                                                    <th>
                                                        Plateau
                                                    </th>
                                                    <th>
                                                        Heure
                                                    </th>
                                                    <th>
                                                        Scene
                                                    </th>
                                                    <th>
                                                        Valider
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for (int i = 0; i < plannings.size(); i++) {
                                                        for (int j = 0; j < plannings.get(i).size(); j++) {%>
                                                <tr>
                                                    <td rowspan="<%=plannings.get(i).size()%>"  class="py-1">

                                                        <strong> <%=plannings.get(i).get(j).getDate()%></strong>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="py-1">
                                                        <strong> <%=plannings.get(i).get(j).getPlateau().getNom()%></strong>

                                                    </td>
                                                    <td class="py-1">                                               
                                                        <strong> <%=plannings.get(i).get(j).getTime()%></strong>
                                                    </td>
                                                    <td class="py-1">                                               
                                                        <button class="btn btn-primary" style="width: 150px">
                                                            <strong>Scene <%=plannings.get(i).get(j).getScene().getId()%></strong></br>
                                                            <strong> <%=plannings.get(i).get(j).getScene().getNom()%></strong>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <input type="checkbox" name="planning" value="<%=plannings.get(i).get(j).toString()%>">
                                                    </td>
                                                </tr>
                                                <%}
                                                    }%>
                                            </tbody>
                                        </table>

                                    </div>
                                    <button class="btn btn-primary" type="submit" style="width: 150px; float: right; margin-right: 10px;margin-left: 10px;">Valider</button>
                                </form>

                            </div>
                        </div>
                    </div>
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
