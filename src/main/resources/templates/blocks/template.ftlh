<#macro template user>
    <!doctype html>
    <html lang="ru">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/css/style.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="/static/css/bootstrap.min.css">

        <title>BOOK LAB</title>
    </head>
    <body>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a href="/" class="ntd">
                <span class="navbar-brand mb-0 h1">BOOK LAB</span>
            </a>
            <#if user.username??>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/allbooks">Библиотека</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/bookshelf">Моя книжная полка</a>
                </li>
            </ul>
                 <a href="/profile"><img src="/static/images/avatar.png" alt="mdo"
                             width="32" height="32" class="rounded-circle">
                 </a>
            <#else>
                <button class="btn btn-light my-2 my-sm-0" type="button" onclick="window.location.href = '/login'">
                    Войти
                </button>
            </#if>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>

    <script src="/static/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>