package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.model.FileInfo;
import ru.itis.servletsapp.services.BooksService;
import ru.itis.servletsapp.services.CoversService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/update-cover")
@MultipartConfig
public class UpdateCoverServlet extends HttpServlet {
    private CoversService coversService;
    private BooksService booksService;

    @Override
    public void init(ServletConfig config) {
        this.coversService = (CoversService) config.getServletContext().getAttribute("coversService");
        this.booksService = (BooksService) config.getServletContext().getAttribute("booksService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("book_id"));
        Long bookId = Long.parseLong(request.getParameter("book_id"));
        request.getSession().setAttribute("book_id", bookId);
        request.getRequestDispatcher("add_cover.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        Optional<BookDto> bookDto = booksService.getById((Long) request.getSession().getAttribute("book_id"));
        if (bookDto.isPresent()) {
            FileInfo fileInfo = coversService.saveFileToStorage(
                    bookDto.get(),
                    part.getInputStream(),
                    part.getSubmittedFileName(),
                    part.getContentType(),
                    part.getSize());
            bookDto.get().setCoverId(fileInfo.getId());
        }
        response.sendRedirect("/profile");
    }
}