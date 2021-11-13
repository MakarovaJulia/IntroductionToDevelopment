<#-- @ftlvariable name="books" type="ru.itis.servletsapp.dto.out.BookDto" -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/book.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/books-menu.css">
</head>
<div class="zoom_wrapper">
    <div class="zoom_block"></div>
</div>
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
<main>
    <div class="cover">
    </div>
    <div class="book-form-container">
        <div class="details-container">
            <div class="details-title-container">
                <div class="details-title">
                    <h1>
                        Детали истории
                    </h1>
                </div>
            </div>
        </div>
        <form method="post">
            <div class="form-title">
                <h1>
                    Название
                </h1>
            </div>
            <label>
                <textarea class="title" required id="title" name="title"></textarea>
            </label>
            <div class="form-title">
                <h1>
                    Описание
                </h1>
            </div>
            <label>
                <textarea class="input-desc" required id="description" name="description"></textarea>
            </label>
            <input class="button1" type="submit" value="Сохранить">
        </form>
    </div>
</main>
</body>
</html>