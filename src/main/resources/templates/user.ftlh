<#ftl encoding='UTF-8'>
<#include "default.ftlh">

<#macro additionalJs>
    <script type="text/javascript" src="js/profile.js"></script>
</#macro>

<#macro additionalCss>
    <link rel="stylesheet" type="text/css" href="css/ProfilePage.css">
</#macro>

<#macro title>
    <title> 1337Bet - ${requestedUser.login} </title>
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
            <h2>Профиль пользователя ${requestedUser.login}</h2>
        </div>
        <div class="home-list-container">
            <div class="home-list-container-table">
                <div class="table table-borderless tg">
                    <table class="table">
                        <tr>
                            <th class="tg-0pky-pic" rowspan="4" class="align-middle"><img class="nav-bar-userpic"
                                                                                          src="files/${requestedUser.avatarPath}"
                                                                                          alt="User avatar">
                            </th>
                            <th class="tg-0pky">Имя</th>
                            <td class="tg-0pky">${requestedUser.name}</td>
                        </tr>
                        <tr>
                            <th class="tg-0pky">Фамилия</th>
                            <td class="tg-0pky">${requestedUser.lastName}</td>
                        </tr>
                        <tr>
                            <th class="tg-0pky">Логин</th>
                            <td class="tg-0pky">${requestedUser.login}</td>
                        </tr>
                        <tr>
                            <th class="tg-0lax">Email</th>
                            <td class="tg-0lax">${requestedUser.email}</td>
                        </tr>
                        <tr>

                            <td class="text-center">
                                <div></div>
                            </td>
                            <th class="tg-0lax">Баланс</th>
                            <td class="tg-0lax">${requestedUser.balance} leetCoins</td>
                        </tr>
                    </table>
                </div>
            </div>
            <#if owner>
                <div class="home-list-container-button">
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#accountModal">
                        Изменить
                        персональные данные
                    </button>
                </div>
            </#if>
        </div>
    </div>
</div>

    <#if owner>
<div class="modal fade" id="accountModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="m-content b-radius mf">
            <div class="modal-header mh">
                <h3>Изменить данные</h3>
            </div>
            <div class="modal-body">
                <form class="needs-validation" id="accountEditForm" method="post" action="/edit"
                      enctype="multipart/form-data" novalidate>
                    <label for="nameNew"> Имя </label>
                    <input type="text" name="name" value="${requestedUser.name}" class="form-control" required
                           pattern="[А-Яа-я]+"/>
                    <div class="invalid-feedback">
                        Имя должно содержать только русские буквы
                    </div>
                    <label for="lastnameNew" class="mt-3"> Фамилия </label>
                    <input type="text" name="lastName" value="${requestedUser.lastName}" class="form-control" required
                           pattern="[А-Яа-я]+"/>
                    <div class="invalid-feedback">
                        Фамилия должна содержать только русские буквы
                    </div>
                    <label for="balanceNew" class="mt-3"> Баланс </label>
                    <input type="text" name="balance" value="${requestedUser.balance?c}" class="form-control" required
                           pattern="[0-9]+"/>
                    <div class="invalid-feedback">
                        Баланс должен быть целым числом
                    </div>

                    <label class="mt-3">Фотография</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroupFileAddon01">Avatar</span>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="inputGroupFile01"
                                   aria-describedby="inputGroupFileAddon01" name="file">
                            <div class="invalid-feedback">
                                Некорректный файл
                            </div>
                            <label class="custom-file-label" for="inputGroupFile01">Выберите файл</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Отмена</button>
                        <button type="submit" class="btn btn-success">Сохранить изменения</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </#if>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
</div>
</#macro>

<@conentView />