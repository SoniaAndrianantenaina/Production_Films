<%@page import="mg.spring_mvc.model.Film"%>
<%@page import="mg.spring_mvc.model.Plateau" %>
<%@page import="mg.spring_mvc.model.Personnage" %>
<%@page import="java.util.List" %>
<% List<Personnage> personnage = (List<Personnage>) request.getAttribute("personnage");
    Film film = (Film) request.getAttribute("film");
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description"
              content="Start your development with Creative Studio landing page.">
        <meta name="author" content="Devcrud">
        <title>Create scene</title>


        <!-- font icons -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/vendors/themify-icons/css/themify-icons.css">

        <!-- Bootstrap + Creative Studio main styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/css/creative-studio.css">


    </head>

    <body data-spy="scroll" data-target=".navbar" data-offset="40" id="home">

        <!-- Page Navigation -->
        <nav class="navbar custom-navbar navbar-expand-lg navbar-dark" data-spy="affix"
             data-offset-top="20">
            <div class="container">
                <a href="index.jsp">
                    <button class="page-link" href="#"><i class="ti-angle-double-left"></i>
                        <span></span>
                    </button>
                </a>
            </div>
        </nav>
        <!-- End Of Page Navigation -->

        <!-- Contact Section -->
        <form action="./insertScene" >
            <section id="contact">
                <div class="container">

                    <div class="contact-card">
                        <div class="infos">
                            <h6 class="section-subtitle">Creation de scene</h6>
                            </h6>
                            <input type="hidden" value="<%=film.getId()%>" name="idfilm"/>
                            <div class="item">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="nomscene" id="exampleFormControlInput1" placeholder="nom scene">
                                </div>
                                <i class="ti-location-pin"></i>
                            </div>
                        </div>
                        <div class="form">
                            <h6 class="section-subtitle">Liste personnages</h6>
                            <h6 class="section-title mb-4">choisir des personnages pour
                                cette
                                scene</h6>

                            <div class="form-group">
                                <% for (Personnage pers : personnage) {%>
                                <input type="checkbox" name="personnage"
                                       value="<%=pers.getId()%>">
                                <%=pers.getNom()%>
                                <% }%>
                                
                            </div>
                            <button type="submit"
                                    class="btn btn-primary btn-block btn-lg mt-3">valider</button>
                        </div>
                    </div>
                </div>
            </section>
        </form>
        <!-- Contact Section -->

        <!-- core  -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/jquery/jquery-3.4.1.js"></script>
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.bundle.js"></script>

        <!-- bootstrap affix -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.affix.js"></script>

        <!-- Creative Studio js -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/js/creative-studio.js"></script>

    </body>

</html>