<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Библиотека ресурса</h2><hr>

    <table class="table table-hover table-light">
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Имя пользователя</th>
            <th scope="col">email</th>
            <th scope="col"></th>
        </tr>
        <#list userlist as u>
            <tr>
                <th>${u.id}</th>
                <th>${u.username}</th>
                <th>${u.email}</th>
                <th><form action="/admin/user_del/${u.id}" method="post">
                        <input type="submit" class="btn btn-light" value="Удалить">
                    </form>
                </th>
            </tr>
        </#list>
    </table>
</@t.template>