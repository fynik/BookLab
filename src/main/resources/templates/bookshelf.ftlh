<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Книжная полка пользователя ${user.username}</h2><hr>

    <table class="table table-hover table-light">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Автор</th>
            <th scope="col">Издана</th>
            <th scope="col">Информация</th>
            <th scope="col"></th>
        </tr>
        <#list user.bookshelf as book>
            <tr>
                <th>${book.title}</th>
                <th>${book.author.lastName} ${book.author.firstName}</th>
                <th>${book.publishedYear}</th>
                <th><a href="/book/${book.id}" class="ntd">Подробная информация</a></th>
                <th>
                    <form action="/book/remove_from_user/${book.id}" method="post">
                        <input type="submit" class="btn btn-light" value="Удалить">
                    </form>
                </th>
            </tr>
        <#else>
            <h3>Книг нет</h3>
        </#list>
    </table>
</@t.template>