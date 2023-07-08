<!DOCTYPE html>
<html>
<head>
    <title>НА СВЯЗИ</title>
</head>
<body>
<#--ШАПКА-->
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
<#--ИНФОРМАЦИЯ-->
<div>
    <#if user.avatar??>
        <img src="/images/${user.avatar.id}" height="60px"/><br><br>
    </#if>
    ${user.name}<br><br>
    ${user.phoneNumber}<br><br>
</div>
<hr>
<#--ДОБАВИТЬ(УДАЛИТЬ) ДРУЗЕЙ, СООБЩЕНИЯ-->
<div>
    <#if isOwner == false>
        <#if isFriend == false>
            <form action="/user/add-friend/${user.id}" method="get">
                <input type="submit" value="Добавить в друзья">
            </form>
        <#else>
            <form action="/user/delete-friend/${user.id}" method="get">
                <input type="submit" value="Удалить из друзей">
            </form>
        </#if>
        <hr>
    </#if>
</div>
<#--СТЕНА-->
<div>
    <div>
        <#if isOwner == true>
            <form action="/publication/save" method="post" enctype="multipart/form-data">
                <label> Заголовок
                    <input type="text" name="title">
                </label><br>
                <label> Текст
                    <input type="text" name="text">
                </label><br>
                Изображение <input type="file" name="file"><br>
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" value="Опубликовать">
            </form>
        </#if>
    </div>
    <div>
        <#list user.publications as publication>
            <#list publication.images as img>
                <img src="/images/${img.id}" height="60px"/>
            </#list>
            <br>
            <a href="/publication/info/${publication.id}">${publication.title}</a><br>
            ${publication.text} <br>
            <form action="/publication/delete/${publication.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type="submit" value="Удалить">
            </form>
        </#list>
    </div>
</div>
</body>
</html>