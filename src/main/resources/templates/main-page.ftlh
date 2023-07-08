<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>НА СВЯЗИ</title>
</head>
<body>

<br>
<div>
    <a href="/">НА СВЯЗИ</a>

    <form action="/user/info/${profile.id}" method="get">
        <input type="submit" value="Моя страница">
    </form>

    <form action="/user/friends/" method="get">
        <input type="submit" value="Друзья">
    </form>

    <form action="/user/search/" method="get">
        <input type="text" placeholder="Поиск пользователей" name="name">
        <input type="submit" value="Поиск">
    </form>

    <#if profile.email??>
        <h3>Имя пользователя: <i>${profile.name}</i></h3>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Выйти">
        </form>
    <#else>
        <a href="/login">Войти</a>
    </#if>
</div>
<hr>


<#list publications as publication>
    <div>
        <a href="/publication/info/${publication.id}">${publication.title}</a>
        <br>
        ${publication.text}
    </div>
    <br><br>
</#list>
</body>
</html>