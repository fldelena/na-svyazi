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

    <form action="/friend/all/" method="get">
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
<form action="/friend/all/" method="get">
    <input type="submit" value="Друзья">
</form>
<form action="/friend/subscriptions/" method="get">
    <input type="submit" value="Отправленные заявки">
</form>
<form action="/friend/subscribers/" method="get">
    <input type="submit" value="Подписчики">
</form>
<hr>
<div>
    <#list invites as invite>
        <div>
            <a href="/user/info/${invite.fromUser.id}">${invite.fromUser.name}</a>
        </div>
        <div>
            <form action="/friend/add/${invite.id}" method="get">
                <input type="submit" value="Добавить в друзья">
            </form>
            <form action="/friend/unfriend/${invite.id}" method="get">
                <input type="submit" value="Оставить в подписчиках">
            </form>
        </div>
    </#list>
</div>
<hr>
<div>
    <#list friends as friend>
    <#--    Добавить фотографию-->
        <div>
            <a href="/user/info/${friend.id}">${friend.name}</a>
        </div>
        <div>
            <form action="/friend/delete/${friend.id}">
                <input type="submit" value="Удалить из друзей">
            </form>
        </div>
        <br><br>
    </#list>
</div>


</body>
</html>
