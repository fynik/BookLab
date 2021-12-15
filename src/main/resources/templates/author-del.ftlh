<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Удаление авторов</h2><hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Фамилия</th>
            <th scope="col">Имя</th>
            <th scope="col">Год рождения</th>
            <th scope="col">Удалить</th>
            <th scope="col">Подробная информация</th>
        </tr>
        <#list authors as author>
            <tr>
                <th>${author.lastName}</th>
                <th>${author.firstName}</th>
                <th>${author.birthYear}</th>
                <th>
                    <form action="/moder/author_del/${author.id}" method="post">
                        <input type="submit" class="btn btn-light" value="Удалить">
                    </form>
                </th>
                <th><a href="/author/${author.id}">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Книг нет</h3>
        </#list>
    </table>
</@t.template>