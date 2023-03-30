<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="mg.spring_mvc.model.Personnage" %>
<%@ page import="com.google.gson.Gson" %>
<%
    Personnage perso = (Personnage)request.getAttribute("perso");
    String myObjectJson = new Gson().toJson(perso);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://smtpjs.com/v3/smtp.js"></script>
        <script>

            function envoyerPDF() {
                var myObject = JSON.parse('<%= myObjectJson%>');
                console.log(myObject.id);
                Email.send({
                    SecureToken: "SMTPJS",
                    To: myObject.email,
                    From: "mihobyfahasoavana@gmail.com",
                    Subject: "Envoi de fichier PDF en pièce jointe",
                    Body: "Bonjour,<br><br>Veuillez trouver ci-joint le fichier PDF demandé.<br><br>Cordialement,<br>Votre nom",
                    Attachments: [
                        {
                            name: "pdfActeur"+myObject.id+".pdf",
                            path: "D:/pdfActeur"+myObject.id+".pdf",
                        },
                    ],
                }).then(function (message) {
                    alert("Le message a bien été envoyé !");
                });
            }
            window.onload = envoyerPDF();
        </script>
    </head>
    <body>

    </body>
</html>
