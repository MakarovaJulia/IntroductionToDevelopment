<#-- @ftlvariable name="published_books" type="java.util.List<ru.itis.servletsapp.dto.BookDto>" -->
<#-- @ftlvariable name="read_book" type="ru.itis.servletsapp.dto.out.BookDto" -->
<#-- @ftlvariable name="chapters" type="ru.itis.servletsapp.dto.out.ChapterDto" -->
<#-- @ftlvariable name="chapters_to_read" type="java.util.List<ru.itis.servletsapp.dto.ChapterDto>" -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/chapter.css">
    <link rel="stylesheet" href="/css/books-menu.css">
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

            <li><span><a href="/main">Назад</a></span></li>
        </nav>
    </div>
</header>
<div class="empty-container"></div>
<main>
    <div class="cover">
    </div>
    <div class="book-form-container">
        <div class="details-container">
            <div class="details-title-container">
                <div class="details-title">
                        <div>
                            <h1 class="book-title">${read_book.title}</h1>
                        </div>
                </div>
            </div>
        </div>
        <div id="book-list" class="book-list">
            <#list chapters_to_read as chapter>
                <div class="book-wrapper">
                    <div class="chapter-title-wrapper">
                        <div class="chapter-title">${chapter.title}</div>
                    </div>
                    <div class="chapter-content">${chapter.content}</div>
                </div>
            </#list>
        </div>
    </div>
</main>
</div>
</body>
</html>