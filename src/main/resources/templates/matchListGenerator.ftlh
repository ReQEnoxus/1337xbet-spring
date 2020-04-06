<#ftl encoding="UTF-8">
<#import "spring.ftl" as spring />
<#include "errors.ftlh">

<#macro matchBlock match>
    <div class="home-list-item">
        <table class="table table-bordered">
            <tr>
                <th scope="col" class="date-col"> <@spring.message 'main.date'/></th>
                <th scope="col" class="teams-col"><@spring.message 'main.teams'/></th>
                <th scope="col" class="coef-col">${match.homeTeam.name}</th>
                <th scope="col" class="coef-col">${match.awayTeam.name}</th>
                <th scope="col" class="coef-col"><@spring.message 'main.draw'/></th>
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
                       class="btn btn-outline-success"><@spring.message 'main.details'/></a>

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
<#if matches?has_content>
    <#list matches as match>
        <@matchBlock match=match />
    </#list>
<#elseif error??>
    <@errorMessage error=error />
</#if>

