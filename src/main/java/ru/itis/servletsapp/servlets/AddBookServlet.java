package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.dto.out.UserDto;
import ru.itis.servletsapp.services.BooksService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/add-book")
public class AddBookServlet extends HttpServlet {
    private BooksService booksService;

    @Override
    public void init(ServletConfig config) {
        booksService = (BooksService) config.getServletContext().getAttribute("booksService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add_book.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        BookDto form = BookDto.builder()
                .title(request.getParameter("title"))
                .description(request.getParameter("description"))
                .isPublished(false)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .authorId(userDto.getId())
                .build();
        booksService.addBook(form);
        response.sendRedirect("/profile");
    }
}