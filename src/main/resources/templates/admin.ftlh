<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Управление пользователями</h2><hr>

    <table class="table table-hover table-light">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Имя пользователя</th>
            <th scope="col">email</th>
            <th scope="col">Роли</th>
            <th scope="col">Состояние</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        <#list userlist as u>
            <tr>
                <th>${u.id}</th>
                <th>${u.username}</th>
                <th>${u.email}</th>
                <th>
                    <#list u.getRoles() as r>
                        ${r.getName()}<br>
                    </#list>
                <th>
                    <#if u.getActive() == true>
                        Active
                    <#else>
                        Banned
                    </#if>
                </th>
                <th>
                    <#if u.username != user.username>
                        <form action="/admin/user_change_status/${u.id}" method="post">
                            <input type="submit" class="btn btn btn-outline-warning" value="Ban/Unban">
                        </form>
                    </#if>
                </th>
                <th>
                    <#if u.username != user.username>
                        <form action="/admin/user_del/${u.id}" method="post">
                            <input type="submit" class="btn btn-outline-danger" value="Удалить">
                        </form>
                    </#if>
                </th>
            </tr>
        </#list>
    </table>
</@t.template>