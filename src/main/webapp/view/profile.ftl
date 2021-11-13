<#-- @ftlvariable name="user" type="ru.itis.servletsapp.dto.out.UserDto" -->
<#-- @ftlvariable name="books" type="java.util.List<ru.itis.servletsapp.dto.BookDto>" -->
<#-- @ftlvariable name="books_in_library" type="java.util.List<ru.itis.servletsapp.dto.BookDto>" -->
<#-- @ftlvariable name="posts" type="java.util.List<ru.itis.servletsapp.dto.PostDto>" -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Профиль</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/profile.css" rel="stylesheet">

    <script src="/js/jquery.min.js"></script>
    <script src="/js/profile.js"></script>
</head>
<body>
<#include "menu.ftl">
<div class="back-photo-container">
<#if user.backId??>
    <img class="user-back-photo" style="width: 100%;
     height: 60vh;
     position: absolute;
     z-index: -1;
     object-fit: cover;" alt="IMAGE" src="/files/${user.backId}"/>
</#if>
</div>
<div class="container">
    <div class="center-content">
        <div class="update-back-wrapper">
            <form class="update-back-photo" action="/update-back-photo" method="post" enctype="multipart/form-data">
                <input type="file" name="file" onchange='this.form.submit();'>
            </form>
        </div>
        <div class="container-profile">
            <div class="white-container">
                <#if user.avatarId??>
                    <img class="user-avatar" alt="IMAGE" src="/files/${user.avatarId}"/>
                <#else>
                    <img class="user-avatar" alt="IMAGE" src="/no-avatar.png"/>
                </#if>
                <div class="user-info-text">
                    <div class="user-info">${user.firstName}</div>
                    <div class="user-info">${user.lastName}</div>
                </div>
                    <div class="update-avatar-container">
                        <form class= "update-avatar" action="/update-avatar" method="post" enctype="multipart/form-data">
                            <input type="file" name="file" onchange='this.form.submit();'>
                        </form>
                    </div>
            </div>
            <div class="profile-content">
                <div id="book-list" class="book-list">
                    <div class="details-container">
                        <div class="details-title-container">
                            <div class="details-title">
                                <h1>
                                    Книги
                                </h1>
                            </div>
                        </div>
                    </div>
                    <#list books as book>
                        <div class="book-wrapper">
                            <#if book.coverId??>
                                <img class="book-cover" alt="IMAGE" src="/files/${book.coverId}"/>
                            <#else>
                                <img class="book-cover" alt="IMAGE" src="/no-cover.jpg"/>
                            </#if>
                            <div class="book-details-wrapper">
                                <div>
                                    <div class="book-title">${book.title}</div>
                                </div>
                                <div class="links">
                                    <nav>
                                        <li><span><a href="/change-book?book_id=${book.id}">Редактировать</a></span></li>
                                    </nav>
                                    <nav>
                                        <li><span><a href="/add-chapter?book_id=${book.id}">Добавить главу</a></span></li>
                                    </nav>
                                    <nav>
                                        <li><span><a href="/update-cover?book_id=${book.id}">Добавить обложку</a></span></li>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
                <div id="posts-list" class="book-list">
                    <form id="add-post-form" action="/add-post" method="post">
                        <label>
                            <textarea class="input_orange" required id="content" name="content"></textarea>
                        </label>
                            <input class="button1" type="submit" value="Опубликовать">
                    </form>
                    <div class="divider"></div>
                        <div id="post-list">
                            <#list posts as post>
                                <div>
                                    <#if user.avatarId??>
                                        <img class="user-avatar-post" alt="IMAGE" src="/files/${user.avatarId}"/>
                                    <#else>
                                        <img class="user-avatar-post" alt="IMAGE" src="/no-avatar.png"/>
                                    </#if>
                                    <div class="light_gray text">${post.author.firstName ! ""}</div>
                                    <div class="light_gray text">${post.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                                    <div class="content">${post.content}</div>
                                    <#if post_index < posts?size - 1>
                                        <div class="divider"></div>
                                    </#if>
                                </div>
                            </#list>
                        </div>
                </div>
            </div>
            <div id="book-list" class="book-list">
                <div class="details-container">
                    <div class="details-title-container">
                        <div class="details-title">
                            <h1>
                                Моя Библиотека
                            </h1>
                        </div>
                    </div>
                </div>
                <#list books_in_library as book_lib>
                    <div class="book-wrapper">
                        <#if book_lib.coverId??>
                            <img class="book-cover" src="/files/${book_lib.coverId}"/>
                        <#else>
                            <img class="book-cover" src="/no-cover.jpg"/>
                        </#if>
                        <div class="book-details-wrapper">
                            <div>
                                <div class="book-title">${book_lib.title}</div>
                            </div>
                            <div>
                                <div class="book-description">${book_lib.description}</div>
                            </div>
                        </div>
                        <div class="links">
                            <nav>
                                <li><span><a href="/read-book?read_book_id=${book_lib.id}">Читать</a></span></li>
                            </nav>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</div>
</body>
</html>