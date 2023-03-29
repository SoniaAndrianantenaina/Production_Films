<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="mg.spring_mvc.model.Personnage, java.util.List" %>
<%@ page import="com.google.gson.Gson" %>


<%
    Personnage p = (Personnage) request.getAttribute("personnage");
//    out.println(p.getId());
    String myObjectJson = new Gson().toJson(p);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scene</title>

        <!-- font icons -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/vendors/themify-icons/css/themify-icons.css">

        <!-- Bootstrap + Creative Studio main styles -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/theme/assets/css/creative-studio.css">

    </head>
    <body>

        <a href="./listPersoScene">
            <button class="page-link" href="#"><i class="ti-angle-double-left"></i>
                <span></span> listes des personnages scenes
            </button>
        </a>
        <section id="contact">

            <div class="container">
                <h2>Ajouter une scene pour le personnage "<%=p.getNom()%>"</h2>
                <br>
                <div class="contact-card">

                    <div class="form" id="person-form">
                        <h6 class="section-subtitle"></h6>
                        <h6 class="section-title mb-4"></h6>
                        <form>
                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="action" placeholder="Action" name="action" required>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="expression" placeholder="Expression" name="expression" >
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control form-control-lg" id="dialogue" placeholder="Dialogue" name="dialogue" >
                            </div>
                            <div class="form-group">
                                <input type="time" step="1" min="00:00:00" max="23:59:59" class="form-control form-control-lg" id="debut" placeholder="Debut" name="debut" required>
                            </div>
                            <div class="form-group">
                                <input type="time" step="1" min="00:00:00" max="23:59:59" class="form-control form-control-lg" id="fin" placeholder="Fin" name="fin" required>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block btn-lg mt-3">Entrer</button>
                        </form>
                        <br><br>
                    </div>
                    <div class="infos">
                        <table class="table table-striped" id="person-table">
                            <thead>
                                <tr>
                                    <th scope="col">Action</th>
                                    <th scope="col">Expression</th>
                                    <th scope="col">Dialogue</th>
                                    <th scope="col">Debut</th>
                                    <th scope="col">Fin</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>

                        <form action="personnageSceneTraitement" method="get" id="inputscene">
                            <button class="btn btn-primary">Valider</button>
                        </form>
                    </div>
                </div>
            </div>
        </section>




        <script>
            function addPerson(event) {
                event.preventDefault();

                var action = document.getElementById("action").value;
                var expression = document.getElementById("expression").value;
                var dialogue = document.getElementById("dialogue").value;
                var debut = document.getElementById("debut").value;
                var fin = document.getElementById("fin").value;

                var person = {
                    action: action,
                    expression: expression,
                    dialogue: dialogue,
                    debut: debut,
                    fin: fin
                };

                addPersonToTable(person);

                document.getElementById("action").value = "";
                document.getElementById("expression").value = "";
                document.getElementById("dialogue").value = "";
                document.getElementById("debut").value = "";
                document.getElementById("fin").value = "";
            }

            function addPersonToTable(person) {
                var table = document.getElementById("person-table");
                var row = table.insertRow(-1);
                var actionCell = row.insertCell(0);
                var expressionCell = row.insertCell(1);
                var dialogueCell = row.insertCell(2);
                var debutCell = row.insertCell(3);
                var finCell = row.insertCell(4);

                var myObject = JSON.parse('<%= myObjectJson%>');
                var id = myObject.id;
                console.log(id);

                var inputScene = document.getElementById("inputscene");
                inputScene.innerHTML += '<input type="hidden" name="action" placeholder="action" value="' + person.action + '">' +
                        '<input type="hidden" name="expression" placeholder="expression" value="' + person.expression + '">' +
                        '<input type="hidden" name="dialogue" placeholder="dialogue" value="' + person.dialogue + '">' +
                        '<input type="hidden" name="debut" placeholder="debut" value="' + person.debut + '">' +
                        '<input type="hidden" name="fin" placeholder="fin" value="' + person.fin + '">' +
                        '<input type="hidden" name="id" placeholder="' + id + '" value="' + id + '">';

                actionCell.innerHTML = person.action;
                expressionCell.innerHTML = person.expression;
                dialogueCell.innerHTML = person.dialogue;
                debutCell.innerHTML = person.debut;
                finCell.innerHTML = person.fin;
            }

            var form = document.getElementById("person-form");
            form.addEventListener("submit", addPerson);
        </script>

        <!-- core  -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/jquery/jquery-3.4.1.js"></script>
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.bundle.js"></script>

        <!-- bootstrap affix -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/vendors/bootstrap/bootstrap.affix.js"></script>

        <!-- Creative Studio js -->
        <script src="${pageContext.request.contextPath}/resources/theme/assets/js/creative-studio.js"></script>
    </body>
</html>
