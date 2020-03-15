<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="css/error.css">
    <title>Document</title>
</head>
<body>
    <#if authorized??>
    <div class="success-message">
        <div class="success-sign"></div>
        <div class="error-message-text"> Аккаунт успешно подтвержден, выполните вход</div>
    </div>
    <#else>
        <div class="error-message">
            <div class="error-sign"></div>
            <div class="error-message-text">
                Неверный код подтверждения
            </div>
        </div>
    </#if>
</body>
</html>