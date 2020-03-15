<#ftl encoding='UTF-8'>
<#include "errors.ftlh">
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/LoginPageStyles.css">
    <link rel="stylesheet" type="text/css" href="css/error.css">
    <title>1337Bet - Login</title>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
</head>
<body>

<form class="form-signin" method="post" action="/login">
    <h1>1337xBet</h1>
    <h1 class="h4 mb-4 font-weight-normal">Войдите в систему</h1>
    <#if error??>
        <@errorMessage error=error />
    </#if>
    <label for="inputLogin" class="sr-only">Вход</label>
    <input type="text" id="inputLogin" class="form-control" placeholder="Логин  " name="login" required autofocus>
    <label for="inputPassword" class="sr-only">Пароль</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" name="password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me" name="remember"> Запомнить меня
        </label>
    </div>
    <button class="btn btn-lg btn-success btn-block mb-3" type="submit">Войти</button>
    <center class="mt-3">
        <a href="/register" class="card-link mx-auto">Создать аккаунт</a>
    </center>
    <p class="mt-5 mb-3 text-muted">&copy; Nikita inc.</p>
</form>
</body>
</html>