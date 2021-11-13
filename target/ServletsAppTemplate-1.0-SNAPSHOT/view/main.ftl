<#-- @ftlvariable name="user" type="ru.itis.servletsapp.dto.out.UserDto" -->
<#-- @ftlvariable name="published_books" type="java.util.List<ru.itis.servletsapp.dto.BookDto>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/style.css">
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
            <li><span><a href="/profile">Профиль</a></span></li>
            <li><span><a href="/sign-in">Войти</a></span></li>
        </nav>
    </div>
</header>
<div class="empty-container"></div>
<div class="background-container">
    <div class="top">
        <div class="portal">
            <h2>Ваши истории значимы. Пусть мир услышит их.</h2>
            <p>На Wonder вы можете писать свои истории и делиться ими.
            Найдите свою любимую историю или напишите бестселлер. </p>
        </div>
    </div>
</div>
<main>
    <div class="published-books-container">
        <div class="details-container">
            <div class="details-title-container">
                <div class="details-title">
                    <h1>
                        Книги
                    </h1>
                </div>
            </div>
        </div>
        <div id="book-list">
            <#list published_books as book>
                <div class="book-wrapper">
                    <#if book.coverId??>
                        <img class="book-cover" src="/files/${book.coverId}"/>
                    <#else>
                        <img class="book-cover" src="/no-cover.jpg"/>
                    </#if>
                    <div class="book-details-wrapper">
                        <div>
                            <div class="book-title">${book.title}</div>
                        </div>
                        <div>
                            <div class="book-description">${book.description}</div>
                        </div>
                    </div>
                    <div class="read-more">
                        <nav>
                            <li><span><a href="/book-info?read_book_id=${book.id}">Читать далее</a></span></li>
                        </nav>
                    </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</main>
</div>
</body>
</html>