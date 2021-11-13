package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.dto.out.UserDto;
import ru.itis.servletsapp.services.BooksService;
import ru.itis.servletsapp.services.CommentsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/book-info")
public class BookInfoServlet extends HttpServlet {
    private CommentsService commentsService;
    private BooksService booksService;

    @Override
    public void init(ServletConfig config) {
        commentsService = (CommentsService) config.getServletContext().getAttribute("commentsService");
        booksService = (BooksService) config.getServletContext().getAttribute("booksService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        request.setAttribute("comments", commentsService.getAll());
        System.out.println(request.getParameter("read_book_id"));
        Long bookId = Long.parseLong(request.getParameter("read_book_id"));
        request.getSession().setAttribute("read_book_id", bookId);
        Optional<BookDto> bookDto = booksService.getById((Long) request.getSession().getAttribute("read_book_id"));
        System.out.println(bookDto.get());
        request.getSession().setAttribute("read_book", bookDto.get());
        request.getRequestDispatcher("book_info.ftl").forward(request, response);
    }
}