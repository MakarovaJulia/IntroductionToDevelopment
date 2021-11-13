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
        <div class="update-cover-wrapper">
            <div class="update-cover-container">
                <form class="update-cover" action="/update-cover" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" onchange='this.form.submit();'>
                    <p>Добавить обложку</p>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>