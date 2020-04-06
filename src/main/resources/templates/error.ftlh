<#ftl encoding='UTF-8'>
<#include "default.ftlh">

<#macro title>
    <title> 404 </title>
</#macro>
<#macro additionalCss>

    <link rel="stylesheet" type="text/css" href="css/errorPage.css">
    <link rel="stylesheet" type="text/css" href="css/BetPageStyles.css">
</#macro>

<#macro body>
    <#if user??>
        <@navbarLoggedIn user=user />
    <#else>
        <@navbarAnonymous />
    </#if>
    <div class="d-flex justify-content-center align-items-center" id="main">
        <h1 class="mr-3 pr-3 align-top border-right inline-block align-content-center">404</h1>
        <div class="inline-block align-middle">
            <h2 class="font-weight-normal lead" id="desc"><@spring.message 'error.404' /></h2>
        </div>
    </div>
</#macro>

<@conentView />