<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="it-IT">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href="images/icons/logoA.svg" type="image/x-icon">
    <title>Asimov: Login</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="style/loginSignup.css">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>

<%@ include file="header.jsp"%>

    <main class="containerFlexLoginSignup">
        <div class="containerLogin">
            <form action="ServletLogin" method="post" id="formLoginSignup">
                <div class="email margin-top">
                    <label for="emailBox">Email<br></label>
                    <input type="email" id="emailBox" class="inputSpace" name="email" placeholder="Inserisci l'email" onblur="validaEmail()">
                </div>

                <div class="password margin-top">
                    <label for="passwordBox">Password<br></label>
                    <input type="password" id="passwordBox" class="inputSpace" name="password" placeholder="Inserisci la password" onblur="validaPassword()">
                </div>

                <%
                    if(request.getAttribute("msg") != null){
                %>
                    <div class="warningLogin">
                        Email o password errati!
                    </div>
                <%
                    }
                %>
                <div class="loginSignup">
                    <input type="submit" class="button" value="Accedi" onclick="login(event)">
                </div>
                <div class="registerLink">
                    <span class="regText">Nuovo utente?<br><a href="signup.jsp" class="underlineEffect">Crea un account</a></span>
                </div>
            </form>
        </div>
    </main>

<%@ include file="footer.jsp"%>

</body>
<script src="script/formValidator.js"></script>
</html>
