<#-- @ftlvariable name="user" type="ru.itis.servletsapp.dto.out.UserDto" -->
<#-- @ftlvariable name="read_book" type="ru.itis.servletsapp.dto.out.BookDto" -->
<#-- @ftlvariable name="comments" type="java.util.List<ru.itis.servletsapp.dto.CommentDto>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/book_info.css">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/book_info.css" rel="stylesheet">

    <script src="/js/jquery.min.js"></script>
    <script src="/js/book_info.js"></script>
</head>
<body>
<header>
    <div class="inner">
        <div class="logo">
            <div>
                <img src="/logo4.png" alt="Logo">
            </div>
        </div>
        <nav>
            <li><span><a href="/main">Главная</a></span></li>
            <li><span><a href="/profile">Профиль</a></span></li>
            <li><span><a href="/sign-in">Войти</a></span></li>
        </nav>
    </div>
</header>
<div class="empty-container"></div>
<main>
    <div class="published-books-container">
                <#if read_book.coverId??>
                    <img class="book-cover" src="/files/${read_book.coverId}"/>
                <#else>
                    <img class="book-cover" src="/no-cover.jpg"/>
                </#if>
                <div class="book-details-wrapper">
                    <div>
                        <div class="book-title">${read_book.title}</div>
                    </div>
                    <div>
                        <div class="book-description">${read_book.description}</div>
                    </div>
                </div>
                <div class="read-more">
                    <nav>
                        <li><span><a href="/read-book?read_book_id=${read_book.id}">Читать книгу</a></span></li>
                    </nav>
                </div>
        <div class="read-more">
            <form id="add-book-to-lib" action="/add-book-to-library" method="post">
                <input class="button1" type="submit" value="Добавить">
            </form>
        </div>
    </div>
    <div id="comments-list" class="comments-list">
        <form id="add-comment-form" action="/add-comment" method="post">
            <label>
                <textarea class="input_orange" required id="content" name="content"></textarea>
            </label>
            <input class="button1" type="submit" value="Опубликовать">
        </form>
        <div class="divider"></div>
        <div id="comment-list">
            <#list comments as comment>
                <div>
                    <#if comment.author.avatarId != 0>
                        <img class="user-avatar-post" alt="IMAGE" src="/files/${comment.author.avatarId}"/>
                    <#else>
                        <img class="user-avatar-post" alt="IMAGE" src="/no-avatar.png"/>
                    </#if>
                    <div class="light_gray text">${comment.author.firstName ! ""}</div>
                    <div class="light_gray text">${comment.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                    <div class="content">${comment.content}</div>
                    <#if comment_index < comments?size - 1>
                        <div class="divider"></div>
                    </#if>
                </div>
            </#list>
        </div>
    </div>
</main>
</div>
</body>
</html>