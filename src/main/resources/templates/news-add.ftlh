<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Добавление новости</h2>
    <hr>
    <br>
    <form action="/moder/news_add" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <div class="form-group">
                <label for="title">Заголовок новости</label>
                <input type="text" class="form-control" required autofocus id="title" name="title">
            </div>
            <br>
            <div class="input-group">
                <span class="input-group-text">Текст новости</span>
                <textarea rows="10" class="form-control" aria-label="With textarea" name="text" required></textarea>
            </div>
        </div>

        <h5>Добавить изображение (в формате jpg или png)</h5>
        <div class="input-group mb-3">
            <input type="file" class="form-control" id="inputGroupFile02" name="file" accept=".png, .jpg, .jpeg">
            <label class="input-group-text" for="inputGroupFile02">Загрузить</label>
        </div>
        <button type="submit" style="width: 100%" class="btn btn-dark">Добавить новость</button>
    </form>
</@t.template>