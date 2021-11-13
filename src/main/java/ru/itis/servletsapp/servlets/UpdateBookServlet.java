package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.services.BooksService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/change-book")
@MultipartConfig
public class UpdateBookServlet extends HttpServlet {
    private BooksService booksService;

    @Override
    public void init(ServletConfig config) {
        this.booksService = (BooksService) config.getServletContext().getAttribute("booksService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("book_id"));
        Long bookId = Long.parseLong(request.getParameter("book_id"));
        request.getSession().setAttribute("book_id", bookId);
        request.getRequestDispatcher("change_book.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bookId = Long.parseLong(request.getParameter("book_id"));
        Optional<BookDto> bookDto = booksService.getById((Long) request.getSession().getAttribute("book_id"));
        if (bookDto.isPresent()) {
            bookDto.get().setId(bookId);
            bookDto.get().setIsPublished(Boolean.parseBoolean(request.getParameter("publish")));
            bookDto.get().setTitle(request.getParameter("title"));
            bookDto.get().setDescription(request.getParameter("description"));
            booksService.addBook(bookDto.get());
        }
        response.sendRedirect("/profile");
    }
}