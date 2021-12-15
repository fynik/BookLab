<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Информация о книге</h2>
    <hr>
    <br>
    <div style="text-align: center">
        <input type="button" class="btn btn-dark" onclick="history.back();" value="Назад"/>
        <br>
        <img src="/images/${book.cover.storageName}"
             style="border-radius: 5px">
        <br>
        <br>
        <h4>${book.title}</h4>
        <br>
        <h4>${book.author.lastName} ${book.author.firstName}</h4>
        <br>
        <p>${book.description}</p>
        <br>
    </div>

</@t.template>