<!DOCTYPE html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<h1><b>Login</b></h1>
    <body>
    <form method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" placeholder="Write login">
        <br><br>

        <label for="password">Password:</label>
        <input type="text" id="password" name="password" placeholder="Write password">
        <br><br>

        <form method="POST" action="/my-app/login">
            <button name="btnLogin" type="submit">
                Login
            </button>
        </form>
        <br>
    </form>
        <h6>If you do not have an account yet, register</h6>
        <form method="POST" action="/my-app/login">
            <button name="btnRegistration" type="submit">
                Registration
            </button>
        </form>
    </body>
</html>