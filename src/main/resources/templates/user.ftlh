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
            <h2><@spring.message 'user.profile'/> ${requestedUser.login}</h2>
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
                            <th class="tg-0pky"><@spring.message 'register.name'/></th>
                            <td class="tg-0pky">${requestedUser.name}</td>
                        </tr>
                        <tr>
                            <th class="tg-0pky"><@spring.message 'register.lastName'/></th>
                            <td class="tg-0pky">${requestedUser.lastName}</td>
                        </tr>
                        <tr>
                            <th class="tg-0pky"><@spring.message 'login.prompt'/></th>
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
                            <th class="tg-0lax"><@spring.message 'user.balance'/></th>
                            <td class="tg-0lax">${requestedUser.balance} leetCoins</td>
                        </tr>
                    </table>
                </div>
            </div>
            <#if owner>
                <div class="home-list-container-button">
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#accountModal">
                        <@spring.message 'user.editButtonTitle'/>
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
                <h3><@spring.message 'user.editModalTitle'/></h3>
            </div>
            <div class="modal-body">
                <form class="needs-validation" id="accountEditForm" method="post" action="/edit"
                      enctype="multipart/form-data" novalidate>
                    <label for="nameNew"> <@spring.message 'register.name'/> </label>
                    <input type="text" name="name" value="${requestedUser.name}" class="form-control" required
                           pattern="[А-Яа-я]+"/>
                    <div class="invalid-feedback">
                        <@spring.message 'register.nameInvalid'/>
                    </div>
                    <label for="lastnameNew" class="mt-3"> <@spring.message 'register.lastName'/> </label>
                    <input type="text" name="lastName" value="${requestedUser.lastName}" class="form-control" required
                           pattern="[А-Яа-я]+"/>
                    <div class="invalid-feedback">
                        <@spring.message 'register.lastNameInvalid'/>
                    </div>
                    <label for="balanceNew" class="mt-3"> <@spring.message 'user.balance'/> </label>
                    <input type="text" name="balance" value="${requestedUser.balance?c}" class="form-control" required
                           pattern="[0-9]+"/>
                    <div class="invalid-feedback">
                        <@spring.message 'user.incorrectBalance'/>
                    </div>

                    <label class="mt-3"><@spring.message 'user.photo'/></label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroupFileAddon01">Avatar</span>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="inputGroupFile01"
                                   aria-describedby="inputGroupFileAddon01" name="file">
                            <div class="invalid-feedback">
                                <@spring.message 'user.incorrectFile'/>
                            </div>
                            <label class="custom-file-label"
                                   for="inputGroupFile01"><@spring.message 'user.chooseFile'/></label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger"
                                data-dismiss="modal"><@spring.message 'user.cancel'/></button>
                        <button type="submit" class="btn btn-success"><@spring.message 'user.saveChanges'/></button>
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