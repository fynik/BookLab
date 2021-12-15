<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h3 style="text-align: center">Последние добавленные книги</h3><hr>



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



<#--    <div class="card-columns">-->
<#--        <#list books as book>-->
<#--            <a href="/product/${product.id}" class="product-link">-->
<#--                <div class="card text-white bg-dark my-3 product">-->

<#--                    <img src="/images/${product.previewImageId}"-->
<#--                         class="card-img-top">-->
<#--                    <div class="card-body">-->
<#--                        <h5 class="card-title">${product.title}</h5>-->
<#--                        <p class="card-text">${product.price} ₽</p>-->
<#--                    </div>-->
<#--                    <div class="card-footer">-->
<#--                        ${product.city}-->
<#--                    </div>-->
<#--                </div>-->
<#--            </a>-->
<#--        </#list>-->
<#--    </div>-->

</@t.template>