<#ftl encoding='UTF-8'>
<#include "default.ftlh">
<#include "errors.ftlh">

<#macro replyBlock reply>
    <div class="home-list-comments mt-3 reply">
        <ul class="list-group w-100">
            <li class="list-group-item">
                <div class="home-comment-header">
                    <img class="nav-bar-userpic-pic" src="files/${reply.owner.avatarPath}"
                         alt="User avatar">
                    <a class="home-comment-user nav-link" href="/user?id=${reply.owner.id}">
                        <h6 class="home-comment-user">
                            ${reply.owner.login}
                        </h6>
                    </a>
                </div>
                <div class="home-comment-text">
                    ${reply.text}
                </div>
            </li>
        </ul>
    </div>
</#macro>

<#macro commentBlock comment>
    <div class="home-list-comments mt-3">
        <ul class="list-group w-100">
            <li class="list-group-item">
                <div class="home-comment-header">
                    <img class="nav-bar-userpic-pic" src="files/${comment.owner.avatarPath}"
                         alt="User avatar">
                    <a class="home-comment-user nav-link" href="/user?id=${comment.owner.id}">
                        <h6 class="home-comment-user">
                            ${comment.owner.login}
                        </h6>
                    </a>
                </div>
                <div class="home-comment-text">
                    ${comment.text}
                </div>
                <a class="reply-button mt-3 mb-3" data-toggle="collapse" href="#collapseReply${comment.id}"
                   aria-expanded="false" aria-controls="collapseExample">
                    Ответить
                </a>
                <div class="collapse" id="collapseReply${comment.id}" data-parent="#list" style="width: 100%;">
                    <form class="form-signin myForm reply-form" id="reply${comment.id}" method="post" novalidate
                          action="/comment" style="width: 100%;">>
                        <textarea class="form-control" rows="2" id="reply-form" placeholder="Ваш ответ" name="text"
                                  required></textarea>
                        <div class="invalid-feedback">
                            Комментарий не должен быть пустым
                        </div>

                        <button class="btn btn-sm btn-success btn-block mt-2" type="submit">Отправить</button>
                    </form>
                </div>
            </li>
        </ul>
    </div>
    <#list comment.replies as reply>
        <@replyBlock reply=reply />
    </#list>
</#macro>

<#macro additionalJs>
    <script type="text/javascript" src="js/match.js"></script>
</#macro>

<#macro additionalCss>
    <link rel="stylesheet" type="text/css" href="css/MatchInfoPageStyles.css">
    <link rel="stylesheet" type="text/css" href="css/error.css">
</#macro>

<#macro title>
    <title> 1337Bet - ${match.homeTeam.name} vs ${match.awayTeam.name} </title>
</#macro>

<#macro body>
    <#if user??>
        <@navbarLoggedIn user=user />
    <#else>
        <@navbarAnonymous />
    </#if>

<div class="home-container">
    <div class="home-list" id="list">
        <div class="home-list-items">
            <div class="home-list-table mt-5" style="width: 90%;">
                <table class="table table-bordered">
                    <tr>
                        <th scope="col">Дата</th>
                        <th scope="col">Команды</th>
                        <th scope="col">${match.homeTeam.name}</th>
                        <th scope="col">${match.awayTeam.name}</th>
                        <th scope="col">Ничья</th>
                    </tr>
                    <tr>
                        <td class="align-middle" rowspan="2"
                            class="align-middle">${match.localizedDate}</td class="align-middle">
                        <td class="align-middle" class="home-list-table-item"><img class="nav-bar-userpic-pic"
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
                    </tr>
                    <tr>
                        <td class="align-middle" class="align-middle"><img class="nav-bar-userpic-pic"
                                                                           src="${match.awayTeam.logoUrl}"
                                                                           alt="${match.awayTeam.name}">
                            ${match.awayTeam.name}
                        </td class="align-middle">
                    </tr>
                </table>
            </div>
            <div class="bet-form-wrapper">
                <#if error??>
                    <@errorMessage error=error/>
                <#elseif success??>
                    <@successMessage success=success />
                </#if>
                <form class="form-signin myForm" id="Bet" method="post" action="/bets" novalidate>
                    <h1 class="h4 mb-4 font-weight-normal myHead">Исход матча</h1>
                    <div class="btn-group btn-group-toggle myRadio" data-toggle="buttons">
                        <label class="btn btn-success btn-lg">
                            <input type="radio" name="prediction" id="option1" autocomplete="off" value="0" required> 1
                        </label>
                        <label class="btn btn-success btn-lg">
                            <input type="radio" name="prediction" id="option2" autocomplete="off" value="1" required> 2
                        </label>
                        <label class="btn btn-success btn-lg">
                            <input type="radio" name="prediction" id="option3" autocomplete="off" value="2" required> X
                        </label>
                    </div>
                    <div class="invalid-feedback" id="predictionInvalid">
                        Выберите исход
                    </div>
                    <label for="inputBet" class="sr-only">Сумма ставки</label>
                    <input type="text" id="inputBet" name="amount" class="form-control mt-3"
                           placeholder="Сумма ставки" required pattern="[0-9]+">
                    <div class="invalid-feedback">
                        Введено некорректное число
                    </div>
                    <div class="checkbox mb-3">
                        <label>
                        </label>
                    </div>
                    <button class="btn btn-lg btn-success btn-block" type="submit">Сделать ставку</button>
                </form>
            </div>
        <#if commentError??>
            <@errorMessage error=commentError />
        <#elseif commentSuccess??>
            <@successMessage success=commentSuccess />
        </#if>
            <div class="home-list-comments mt-5">
                <ul class="list-group" style="width: 100%;">
                    <li class="list-group-item">
                        <div class="home-comment-header">
                            <p class="h4">Оставить комментарий к матчу</p>
                        </div>
                        <form class="form-signin myForm" id="Comment" method="post" action="/comment" novalidate
                              style="width: 100%;">>
                            <textarea class="form-control" rows="2" id="comment-form" placeholder="Ваш комментарий"
                                      required name="text"></textarea>
                            <div class="invalid-feedback">
                                Комментарий не должен быть пустым
                            </div>
                            <button class="btn btn-sm btn-success btn-block mt-2" type="submit">Отправить</button>
                        </form>
                    </li>
                </ul>
            </div>

        <#list comments as comment>
            <@commentBlock comment=comment />
        </#list>
        </div>
    </div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
</#macro>

<@conentView />