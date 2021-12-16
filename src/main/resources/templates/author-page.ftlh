<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Информация о книге</h2>
    <hr>
    <br>
    <div style="text-align: center">
        <input type="button" class="btn btn-dark" onclick="history.back();" value="Назад"/>
        <br>
        <br>
        <img src="/images/${author.photo.storageName}"
             style="border-radius: 5px" alt="Фото автора">
        <br>
        <br>
        <h4>${author.lastName}</h4>
        <br>
        <h4>${author.firstName}</h4>
        <br>
        <p>${author.birthYear}</p>
        <br>

    </div>

</@t.template>