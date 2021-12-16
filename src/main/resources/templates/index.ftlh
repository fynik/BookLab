<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h3 style="text-align: center">Последние добавленные книги</h3><hr>

    <div class="card-group">
        <#list books as book>
            <a href="/book/${book.id}" class="ntd">
                <div class="card text-dark bg-light my-3 el-hover">
                    <img src="/images/${book.cover.storageName}" class="card-img-top" alt="Обложка" style="height: 250px">
                    <div class="card-body">
                        <h5 class="card-title">${book.title}</h5>
                        <p class="card-text">${book.author.lastName} ${book.author.firstName}</p>
                    </div>
                </div>
            </a>
        </#list>
    </div>



    <h3 style="text-align: center">Последние Новости</h3><hr>

    <#list news as nItem>
        <div class="card mb-3" style="max-width: 100%;">
            <div class="row g-0">
                <div class="col-md-1">
                    <#if nItem.picture??>
                        <img src="/images/${nItem.picture.storageName}" class="img-fluid rounded-start" alt="News">
                    <#else>
                        <img src="/static/images/newspaper-icon.png" class="img-fluid rounded-start" alt="News">
                    </#if>
                </div>
                <div class="col-md-11">
                    <div class="card-body">
                        <h5 class="card-title">${nItem.title}</h5>
                        <p class="card-text">${nItem.content}</p>
                        <p class="card-text"><small class="text-muted">Добавлено ${nItem.user.username} ${nItem.published}</small></p>
                    </div>
                </div>
            </div>
        </div>
    </#list>

</@t.template>