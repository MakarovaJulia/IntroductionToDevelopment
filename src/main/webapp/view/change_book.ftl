<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
    <link href="/css/change_book.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/books-menu.css">
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
            <li><span><a href="/profile">Назад</a></span></li>
        </nav>
    </div>
</header>
<div class="empty-container"></div>
<main>
    <div class="published-books-container">
        <div class="details-container">
            <div class="details-title-container">
                <div class="details-title">
                    <h1>
                        Редактировать
                    </h1>
                </div>
            </div>
        </div>
        <div id="book-list">
            <form method="post" id="change-book">
                <label>
                    <input name="title" type="text" placeholder="Название книги" class="title">
                </label>
                <label>
                    <textarea class="input-desc" required id="description" name="description" placeholder="Описание"></textarea>
                </label>
                <label>
                    <input type="checkbox" name="publish" id="publish" value="true" checked>
                    <p>Опубликовать</p>
                </label>
            </form>
            <input class="button1" type="submit" value="Сохранить" form="change-book">
        </div>
    </div>
</main>
</body>
</html>