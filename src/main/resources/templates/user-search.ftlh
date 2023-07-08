<!DOCTYPE html>
<html>
<head>
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

<div>
    <#list users as user>
        <div>
            <#if user.avatar??>
                <a href="/user/info/${user.id}"><img src="/images/${user.avatar.id}" height="60px"/></a>
                <a href="/user/info/${user.id}">${user.name}</a>
            </#if>
        </div>
    </#list>
</div>

</body>
</html>