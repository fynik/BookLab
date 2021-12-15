<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Удаление книг</h2><hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Фамилия автора</th>
            <th scope="col">Имя Автора</th>
            <th scope="col">Дата добавления</th>
            <th scope="col">Удалить</th>
            <th scope="col">Подробная информация</th>
        </tr>
        <#list books as book>
            <tr>
                <th>${book.title}</th>
                <th>${book.author.lastName}</th>
                <th>${book.author.firstName}</th>
                <th>${book.additionDate}</th>
                <th>
                    <form action="/moder/book_del/${book.id}" method="post">
                        <input type="submit" class="btn btn-light" value="Удалить">
                    </form>
                </th>
                <th><a href="/book/${book.id}">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Книг нет</h3>
        </#list>
    </table>
</@t.template>