<!DOCTYPE html>
<html>
<head>
    <title>НА СВЯЗИ</title>
</head>
<body>
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
<form action="/user/friends/" method="get">
    <input type="submit" value="Друзья">
</form>
<form action="/user/subscriptions/" method="get">
    <input type="submit" value="Отправленные заявки">
</form>
<form action="/user/subscribers/" method="get">
    <input type="submit" value="Подписчики">
</form>
<hr>
<#list subscriptions as subscription>
<#--    Добавить фотографию-->
    <div>
        <a href="/user/info/${subscription.id}">${subscription.name}</a>
    </div>
    <br><br>
</#list>

</body>
</html>
