<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Последние добавленные книги</h2><hr>

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