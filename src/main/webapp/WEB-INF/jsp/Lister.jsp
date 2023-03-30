<%@page import="mg.spring_mvc.model.Scene"%>
<%@page import="mg.spring_mvc.dao.HibernateDao"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Start your development with Creative Studio landing page.">
        <meta name="author" content="Devcrud">
        <title>Lister</title>

        <!-- font icons -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/vendors/themify-icons/css/themify-icons.css">

        <!-- Bootstrap + Creative Studio main styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/css/creative-studio.css">

    </head>

    <body class="section" data-spy="scroll" data-target=".navbar" data-offset="4" id="home">
        <%
            List<Scene> mots = (List<Scene>) request.getAttribute("liste");
            HibernateDao dao = (HibernateDao) request.getAttribute("hibernate");
            String path = request.getAttribute("path") + "";
            int nombre = (int) request.getAttribute("page");
            // int idfilm=(int)request.getAttribute("idfilm");
            String[] etats = (String[]) request.getAttribute("etats");
        %>
        <!-- Page Navigation -->
        <div class="navbar custom-navbar navbar-expand-lg navbar-dark" data-spy="affix" data-offset-top="10">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <img src="${pageContext.request.contextPath}/resources/theme/assets/imgs/logo.png" >
                </a>
            </div>
            <a href="./createscene" ><button class="btn btn-primary circle w-sm ml-3">ajouter scene</button></a>
            <a href="./planningScene" ><button class="btn btn-primary circle w-sm ml-3">planifier des scenes</button></a>
            <a href="./persoMail" ><button class="btn btn-primary circle w-sm ml-3">envoyer le planning au personnage</button></a>
        </div>
        <!-- End Of Page Navigation -->



        <section id="service" style="margin-top:100px; background:url('${pageContext.request.contextPath}/resources/theme/assets/imgs/folio-5.jpg');repeat:no-repeat;"  >
            <div class="container text-center">
                <div class="row tm-row">
                    <div class="col-12">
                        <h4>List scenery for movie number:<%//=idfilm%></h4>
                        <form method="post" class="form-inline tm-mb-80 tm-search-form">                
                            <input class="form-control rounded  ml-3" type="text" placeholder="Search..." name="mots">   
                            <button href="#contact" class="btn btn-primary circle w-sm ml-3"><i class="ti-search"></i></button>           
                        </form>
                    </div>                
                </div>
                <br>
                <% for (Scene scene : mots) {%>
                <div class="row align-items-center">
                    <div class="col-sm-6">    
                        <div class="card">
                            <div class="card-body">
                                <h2 class="mb-4"><i class="ti-palette text-primary"><a href="./detailsScene?idScene=<%=scene.getId()%>" ><%=scene.getNom()%></a></i></h2>


                                <%for (int j = 0; j < etats.length - 1; j++) {
                                        if (scene.getEtat() == 0) {%>
                                <form action="<%= request.getContextPath()%>/Terminer">
                                    <input type="hidden" name="idscene" value="<%=scene.getId()%>"<p></p>
                                    <p style="color:steelblue;"><%=etats[0]%>  </p>
                                    <%=request.getAttribute("bouton")%>     
                                </form>
                                <% } else if (scene.getEtat() == 1) {%>
                                <p style="color:green;"><%=etats[1]%><p>   
                                    <%} else if (scene.getEtat() == 2) {%>
                                <p style="color:palevioletred;"><%=etats[2]%></p> 
                                <%}
                                        break;
                                    }%>

                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <%}%>
            </div>  
            <center>
                <% for (int i = 0;
                    i< nombre ;
                    i

                    
                        ++) {%>
                <a   style="background: linear-gradient(#A6C0FE, #F68084);" class="btn btn-dark" href="?nbr=<%= i + 1%>"><%= i + 1%></a>
                <% }%>
            </center>
        </section>
        <div class="has-bg-img py-md">

        </div>


        <!-- core  -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/jquery/jquery-3.4.1.js"></script>
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.bundle.js"></script>

        <!-- bootstrap affix -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.affix.js"></script>

        <!-- Creative Studio js -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/js/creative-studio.js"></script>

    </body>
</html>
