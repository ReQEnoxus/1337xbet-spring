<#ftl encoding='UTF-8'>
<#include "default.ftlh">

<#macro betBlock bet>
<div class="home-list-item">
    <div class="home-list-teams" onclick="location.href='/match?id=${bet.match.id?c}'">
        <div class="home-list-teams-team1-container">
            <img class="home-list-teams-team1"
                 src="${bet.match.homeTeam.logoUrl}"
                 alt="${bet.match.homeTeam.name}">
            <div class="home-list-teams-team1-name">${bet.match.homeTeam.name}</div>
        </div>
        <div class="home-list-teams-team1-container" style="color: #000;">
            &nbsp;&nbsp;VS
        </div>
        <div class="home-list-teams-team2-container">
            <img class="home-list-teams-team2"
                 src="${bet.match.awayTeam.logoUrl}"
                 alt="${bet.match.awayTeam.name}">
            <div class="home-list-teams-team2-name">${bet.match.awayTeam.name}</div>
        </div>
    </div>
    <#if bet.active>
        <#assign color = "#000000">
        <#assign outcome = "Еще не сыграна">
    <#elseif bet.won>
        <#assign color = "#28a745">
        <#assign outcome = "Победа">
    <#else>
        <#assign color = "#f52419">
        <#assign outcome = "Поражение">
    </#if>
    <div class="home-list-item-my-bet" style="color: ${color};">
        <#if color == "#000000">
            ${outcome}
        <#elseif color == "#28a745">
            ${outcome} (+${bet.amount * bet.coefficient})
        <#elseif color == "#f52419">
            ${outcome} (-${bet.amount})
        </#if>
    </div>
</div>
</#macro>

<#macro additionalJs>
    <script type="text/javascript" src="js/match.js"></script>
</#macro>

<#macro title>
    <title> 1337Bet - Мои ставки </title>
</#macro>

<#macro additionalCss>
    <link rel="stylesheet" type="text/css" href="css/BetPageStyles.css">
</#macro>

<#macro body>
    <#if user??>
        <@navbarLoggedIn user=user />
    <#else>
        <@navbarAnonymous />
    </#if>

    <div class="home-container">
        <div class="home-list">
            <div class="home-list-header">
                <h1>Мои ставки</h1>
            </div>
            <#list bets as bet>
                <@betBlock bet=bet />
            </#list>
        </div>
    </div>

</#macro>

<@conentView />