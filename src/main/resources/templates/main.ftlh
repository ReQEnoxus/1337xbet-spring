<#ftl encoding="UTF-8">
<#include "default.ftlh">

<#macro additionalCss>
    <link rel="stylesheet" type="text/css" href="css/HomePageStyles.css">
    <link rel="stylesheet" type="text/css" href="css/error.css">
</#macro>

<#macro additionalJs>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/mainPage.js"></script>
</#macro>

<#macro title>
    <title> 1337Bet - <@spring.message 'main.title' /> </title>
</#macro>

<#macro matchBlock match>
    <div class="home-list-item">
        <table class="table table-bordered">
            <tr>
                <th scope="col" class="date-col"> <@spring.message 'main.date' /> </th>
                <th scope="col" class="teams-col"> <@spring.message 'main.teams' /></th>
                <th scope="col" class="coef-col">${match.homeTeam.name}</th>
                <th scope="col" class="coef-col">${match.awayTeam.name}</th>
                <th scope="col" class="coef-col"><@spring.message 'main.draw' /></th>
                <th class="more-col"></th>
            </tr>
            <tr>
                <td class="align-middle" rowspan="2"
                    class="align-middle">${match.localizedDate}</td class="align-middle">
                <td class="align-middle team-cell" class="home-list-table-item"><img class="nav-bar-userpic-pic"
                                                                                     src="${match.homeTeam.logoUrl}"
                                                                                     alt="${match.homeTeam.name}">
                    ${match.homeTeam.name}
                </td class="align-middle">
                <td class="align-middle" class="align-middle"
                    rowspan="2">${match.coefficients.home}</td class="align-middle">
                <td class="align-middle" class="align-middle"
                    rowspan="2">${match.coefficients.away}</td class="align-middle">
                <td class="align-middle" class="align-middle"
                    rowspan="2">${match.coefficients.draw}</td class="align-middle">
                <td class="align-middle" class="align-middle" rowspan="2">

                    <a href="/match?id=${match.id?c}"
                       class="btn btn-outline-success"> <@spring.message 'main.details' /></a>

                </td class="align-middle">
            </tr>
            <tr>
                <td class="align-middle team-cell" class="align-middle"><img class="nav-bar-userpic-pic"
                                                                             src="${match.awayTeam.logoUrl}"
                                                                             alt="${match.awayTeam.name}">
                    ${match.awayTeam.name}
                </td class="align-middle">
            </tr>
        </table>
    </div>
</#macro>

<#macro body>
    <#if user??>
        <@navbarLoggedIn user=user />
    <#else>
        <@navbarAnonymous />
    </#if>

    <div class="home-container">
        <div class="home-list">
            <div class="home-list-filter">
                <div class="form-group searchbar">
                    <input type="search" class="form-control" id="searchInput" aria-describedby="emailHelp"
                           placeholder="<@spring.message 'main.searchByTeams' />">
                    <small id="emailHelp" class="form-text text-muted"></small>
                </div>
                <div class="home-list-filter-text">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <button class="btn btn-success" type="button"><@spring.message 'main.date' /></button>
                        </div>
                        <select class="custom-select" id="dateSelector">
                            <option value="0" selected>${dates[0]}</option>
                            <#list 1..6 as index>
                                <option value=index>${dates[index]}</option>
                            </#list>
                        </select>
                    </div>
                </div>
            </div>
            <div class="home-list-items" id="matches">
                <#list matches as match>
                    <@matchBlock match=match />
                </#list>
            </div>
        </div>
    </div>
</#macro>

<@conentView />