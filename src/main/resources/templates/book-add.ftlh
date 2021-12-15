<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Добавление книги</h2>
    <hr>
    <br>
    <form action="/moder/book_add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="pub_year">Наименование</label>
            <input type="text" class="form-control" required autofocus id="pub_year" name="title">
        </div>
        <div class="form-group">
            <label for="searchAuthor">Автор</label>

            <select id="searchAuthor" required name="author" class="form-control">
                <option value="">Автор не выбран</option>

                <#list authors as author>
                <option value="${author.id}">${author.lastName} ${author.firstName}</option>
                </#list>
            </select>
        </div>
        <br>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-text">Описание</span>
                <textarea rows="10" class="form-control" aria-label="With textarea" name="description" required></textarea>
            </div>
        </div>

        <div class="form-group">
            <label for="pub_year">Год издания</label>
            <input type="number" class="form-control" required step="1" id="pub_year" name="pub_year">
        </div>

        <h5>Добавить изображение (в формате jpg или png)</h5>
        <div class="input-group mb-3">
            <input type="file" class="form-control" id="inputGroupFile02" name="file" required accept=".png, .jpg, .jpeg">
            <label class="input-group-text" for="inputGroupFile02">Загрузить</label>
        </div>
        <button type="submit" style="width: 100%" class="btn btn-dark">Добавить книгу</button>
    </form>
</@t.template>