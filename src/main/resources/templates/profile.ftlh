<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Личный кабинет</h2>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.username}</h2>
        <br>
        Электоронная почта: <b>${user.email}</b>
        <br>
        <#if user.isModer()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/moder/news_add">
                Добавить новость
            </a><br>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/moder/book_add">
                Добавить книгу
            </a><br>
        </#if>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a><br>
        </#if>
        <form action="/logout" method="post">
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>

        <#--        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"-->
        <#--           href="/my/products">-->
        <#--            Мои товары-->
        <#--        </a>-->


        <br>
        <#if user.isModer()>
            <h3 style="text-align: center">Мои новости:</h3>
            <hr>
            <#list user.news as nel>
                <div class="accordion" id="accordion1">
                    <div class="accordion-item">
                        <h6 class="accordion-header" id="heading${nel.id}">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${nel.id}" aria-expanded="true" aria-controls="collapse${nel.id}">
                                Новость №${nel.id}, опубликована ${nel.published}
                            </button>
                        </h6>
                        <div id="collapse${nel.id}" class="accordion-collapse collapse show" aria-labelledby="heading${nel.id}" data-bs-parent="#accordion1">
                            <div class="accordion-body">
                                <div class="card" style="width: 100%;">
                                    <#if nel.picture??>
                                        <img src="/images/${nel.picture.storageName}" class="card-img-top" alt="picture">
                                    </#if>
                                    <div class="card-body">
                                        <p class="card-text">${nel.content}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <#else>
                <h4>Вы еще не опубликовали ни одной новости</h4>
            </#list>
        </#if>
    </div>
</@t.template>