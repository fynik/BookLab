<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Авторизация</h1>
    <hr>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">Имя пользователя</label>
            <input type="text" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="username" name="username">
            <div id="username" class="invalid-feedback">
                Неверное имя пользователя
            </div>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="password">
            <div id="password" class="invalid-feedback">
                Неверный пароль
            </div>
        </div>
        <button type="submit" style="width: 100%" class="btn btn-dark">Войти</button>
    </form>
    <hr>
    <h3>Нет аккаунта BOOK LAB? <a href="/registration">Зарегистрируйся!</a></h3>
</@t.template>