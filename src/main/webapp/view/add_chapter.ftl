<#-- @ftlvariable name="books" type="ru.itis.servletsapp.dto.out.BookDto" -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="/css/chapter.css">
    <link rel="stylesheet" href="/css/books-menu.css">
    <link rel="stylesheet" href="/css/style.css">

    <script src="/js/autosize.js"></script>
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
    <div class="cover">
    </div>
    <div class="book-form-container">
        <div class="details-container">
            <div class="details-title-container">
                <div class="details-title">
                    <h1>
                        Добавить главу
                    </h1>
                </div>
            </div>
        </div>
            <form method="post" id="chapter">
                <label>
                    <input type="number" placeholder="Номер главы" min="1" max="50" id="number" name="number" title="Номер" class="input-text qty text" size="3" />
                </label>
                <label>
                    <textarea class="title" required id="title" name="title" placeholder="Название"></textarea>
                </label>
                <label>
                    <textarea class="input-desc" required id="content" name="content"></textarea>
                </label>
                <input class="button1" type="submit" value="Сохранить">
            </form>
        </div>
</main>
</div>
<script>
    autosize(document.querySelectorAll('textarea'));
</script>
</body>
</html>