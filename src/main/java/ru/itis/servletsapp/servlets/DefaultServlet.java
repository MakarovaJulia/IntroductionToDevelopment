package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.services.BooksService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class DefaultServlet extends HttpServlet {

    private BooksService booksService;

    @Override
    public void init(ServletConfig config) {
        booksService = (BooksService) config.getServletContext().getAttribute("booksService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookDto> bookDtos = booksService.getAll();
        bookDtos.removeIf(bookDto -> bookDto.getIsPublished() == false);
        request.setAttribute("published_books", bookDtos);
        request.getRequestDispatcher("main.ftl").forward(request, response);
    }
}