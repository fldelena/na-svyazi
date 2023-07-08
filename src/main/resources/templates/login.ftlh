<!DOCTYPE html>
<html>
<head>
    <title>NA SVyaZi</title>
</head>
<body>
<h1>NA SVyaZi</h1><hr>
<h4>Авторизация</h4>
<form action="/login" method="post">
    Эл. почта: <input type="email" name="username"><br><br>
    Пароль: <input type = "password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<a href="/registration">Регистрация</a>
</body>
</html>