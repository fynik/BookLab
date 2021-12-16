<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Библиотека ресурса</h2><hr>

    <table class="table table-hover table-light">
        <tr>
            <th scope="col">Название</th>
            <th scope="col">Автор</th>
            <th scope="col">Издана</th>
            <th scope="col">Информация</th>
        </tr>
        <#list books as book>
            <tr>
                <th>${book.title}</th>
                <th>${book.author.lastName} ${book.author.firstName}</th>
                <th>${book.publishedYear}</th>
                <th><a href="/book/${book.id}" class="ntd">Подробная информация</a></th>
            </tr>
        <#else>
            <h3>Книг нет</h3>
        </#list>
    </table>
</@t.template>