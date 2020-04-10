<#ftl encoding='UTF-8'>
<#include "default.ftlh">

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

<#macro additionalJs>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/support.js"></script>
</#macro>

<#macro title>
    <title> 1337Bet - <@spring.message 'common.support'/> </title>
</#macro>


<#macro additionalCss>
    <link rel="stylesheet" type="text/css" href="css/HomePageStyles.css">
    <link rel="stylesheet" type="text/css" href="css/error.css">
</#macro>

<#macro body>
    <#if user??>
        <@navbarLoggedIn user=user />
    <#else>
        <@navbarAnonymous />
    </#if>

    <#if user.state == "ADMIN">
        <select id="receiver">
            <#list users as u>
                <option> ${u.login} </option>
            </#list>
        </select>
    </#if>

    <div style="color: #000; display: block;">
        <h3> Messages </h3>

        <div id="messageList" style="color: #000; display: block;">
            <#list messages as message>
                <strong> ${message.sender}: </strong> ${message.text} <br>
            </#list>
        </div>
    </div>


<input name="message" id="message" placeholder="Text">
<button onclick="send(document.getElementById('message').value, '${user.login}', <#if user.state == "ADMIN"> document.getElementById('receiver').value <#else> 'enoxus' </#if>)"> <@spring.message 'match.send'/> </button>


</#macro>

<@conentView />