
<!DOCTYPE html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<h1><b>Registration</b></h1>
    <body>
        <form method="post">
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" placeholder="Write login">
        <br><br>

        <label for="password">Password:</label>
        <input type="text" id="password" name="password" placeholder="Write password">
        <br><br>

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" placeholder="Write email">
        <br><br>

        <form method="POST" action="/my-app/registration">
            <button name="btnRegistration" type="submit">
                Registration
            </button>
        </form>
        <br>
        <h6>If you already have an account , log in</h6>
        <form method="POST" action="/my-app/registration">
            <button name="btnLogin" type="submit">
                Login
            </button>
        </form>
        </form>
    </body>
</html>