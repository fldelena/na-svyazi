<!DOCTYPE html>
<html>
<head>
    <title>NA SVyaZi</title>
</head>
<body>
<h1>NA SVyaZi</h1>
<h4>Подробная информация о посте</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
${publication.title}<br>
${publication.text}<br>
</body>
</html>