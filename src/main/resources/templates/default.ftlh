<#ftl encoding='UTF-8'>
<#import "spring.ftl" as spring />

<#macro head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
</#macro>

<#macro navbarLoggedIn user>
    <div class="nav-bar-container">
        <a class="navbar-brand" href="/" style="color: white;">1337Bet</a>
        <div class="nav-bar-username">
            <div class="nav-bar-user">
                <img class="nav-bar-userpic-pic"
                     src="files/${user.avatarPath}"
                     alt="User avatar">
            </div>
            <a class="nav-link" style="color: white;" href="/user">${user.name} ${user.lastName}</a>
        </div>
        <div class="nav-bar-balance"><@spring.message 'user.balance'/>: ${user.balance} leetCoins</div>
        <div class="nav-bar-more">
            <div class="dropdown">
                <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <@spring.message 'common.menu'/>
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" style="color: white;"
                       href="/user?id=${user.id}"><@spring.message 'common.myProfile'/></a>
                    <a class="dropdown-item" style="color: white;" href="/bets"><@spring.message 'common.myBets'/></a>
                    <a class="dropdown-item" style="color: white;"
                       href="/support"><@spring.message 'common.support'/></a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" style="color: white;" href="/logout"><@spring.message 'common.logout'/></a>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro navbarAnonymous>
    <div class="nav-bar-container">
        <a class="navbar-brand" style="color: white;" href="/">1337Bet</a>
        <div class="nav-bar-more">
            <div class="dropdown">
                <button class="btn btn-success dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <@spring.message 'common.menu'/>
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="/login" style="color: white;"><@spring.message 'common.signIn'/></a>
                    <a class="dropdown-item" href="/support"
                       style="color: white;"><@spring.message 'common.support'/></a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="/register"
                       style="color: white;"><@spring.message 'register.prompt'/></a>
                </div>
            </div>
        </div>
    </div>
</#macro>

<#macro additionalJs>

</#macro>

<#macro additionalCss>

</#macro>

<#macro title>

</#macro>

<#macro body>

</#macro>


<#macro conentView>
    <html>
    <head>
            <@head />
            <@additionalJs />
            <@additionalCss />
            <@title />
    </head>
    <body>
            <@body />
    </body>
    </html>
</#macro>