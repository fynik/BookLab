<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Добавление автора</h2>
    <hr>
    <br>
    <form action="/moder/author_add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="fname">Имя</label>
            <input type="text" class="form-control" required autofocus id="fname" name="fname">
        </div>
        <div class="form-group">
            <label for="lname">Фамилия</label>
            <input type="text" class="form-control" required id="lname" name="lname">
        </div>
        <div class="form-group">
            <label for="byear">Год рождения</label>
            <input type="number" class="form-control" required step="1" id="byear" name="birth_year">
        </div>
        <h5>Добавить изображение (в формате jpg или png)</h5>
        <div class="input-group mb-3">
            <input type="file" class="form-control" id="inputGroupFile02" name="file" required accept=".png, .jpg, .jpeg">
            <label class="input-group-text" for="inputGroupFile02">Загрузить</label>
        </div>
        <button type="submit" style="width: 100%" class="btn btn-dark">Добавить автора</button>
    </form>
</@t.template>