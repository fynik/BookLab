<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center; color: red">Файл должен быть в формате jpg или png</h2>
    <br>
    <br>
    <input type="button" class="btn btn-dark" onclick="history.back();" value="Назад" style="text-align: center; width: 100%"/>

</@t.template>