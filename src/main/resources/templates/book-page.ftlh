<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Информация о книге</h2>
    <hr>
    <br>
    <#if userHasBook??>
        <div style="text-align: center">
            <input type="button" class="btn btn-dark" onclick="history.back();" value="Назад"/>
        </div>
        <br>
        <br>
    <#else>
        <div class="row justify-content-center">
            <div class="col-4">
                <input type="button" class="btn btn-dark" onclick="history.back();" value="Назад"/>
            </div>
            <div class="col-1">
                <form action="/book/add_to_user/${book.id}" method="post">
                    <input class="btn btn-dark" type="submit" value="Добавить на полку"/>
                </form>
            </div>
        </div>
        <br>
        <br>
    </#if>
    <div style="text-align: center">
        <img src="/images/${book.cover.storageName}"
             style="border-radius: 5px; height: 350px" alt="Обложка книги">
        <br>
        <br>
        <h4>${book.title} (${book.publishedYear})</h4>

        <h4>${book.author.lastName} ${book.author.firstName}</h4>
        <br>
        <p>${book.description}</p>
        <br>
    </div>

</@t.template>