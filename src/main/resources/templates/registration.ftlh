<!DOCTYPE html>
<html>
<head>
    <title>NA SVyaZi</title>
</head>
<body>
<h1>NA SVyaZi</h1>
<h4>Регистрация</h4>
<form action="/registration" method="post" enctype="multipart/form-data">
    Аватарка <input type="file" name="file"><br><br>
    Имя пользователя: <input type="text" name="name"><br><br>
    Номер телефона: <input type="text" name="phoneNumber"><br><br>
    Эл. почта: <input type="email" name="email"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>