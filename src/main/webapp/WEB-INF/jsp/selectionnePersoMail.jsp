<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="mg.spring_mvc.model.Personnage, java.util.List" %>
<%
    List<Personnage> perso = (List<Personnage>) request.getAttribute("perso");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perso</title>
        <!-- font icons -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/vendors/themify-icons/css/themify-icons.css">
        <!-- Bootstrap + Creative Studio main styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/css/creative-studio.css">
    </head>
    <body>
        <h1><center> Envoyer le futur planning des acteurs</center></h1>
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
                                    <h1 class="small text-primary font-weight-bold">Acteur</h1>
                                    <h5 class="card-title"><%=p.getNom()%></a></h5>
                                    <form action="generate-pdf" method="get">
                                        <input type="hidden" value="<%=p.getId() %>" name="acteur" class="btn btn-primary">
                                        <input type="submit" value="Exporter son planning en pdf" class="btn btn-primary">
                                        
                                    </form>
                                    <br>
                                    <a href="envoiMail?id=<%=p.getId() %>">Envoyer son planning par mail</a>
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
