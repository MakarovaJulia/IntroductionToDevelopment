package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.dto.out.ChapterDto;
import ru.itis.servletsapp.services.BooksService;
import ru.itis.servletsapp.services.ChaptersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@WebServlet("/read-book")
public class ReadBookServlet extends HttpServlet {
    private BooksService booksService;
    private ChaptersService chaptersService;

    @Override
    public void init(ServletConfig config) {
        this.booksService = (BooksService) config.getServletContext().getAttribute("booksService");
        this.chaptersService = (ChaptersService) config.getServletContext().getAttribute("chaptersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bookId = Long.parseLong(request.getParameter("read_book_id"));
        request.getSession().setAttribute("read_book_id", bookId);
        Optional<BookDto> bookDto = booksService.getById((Long) request.getSession().getAttribute("read_book_id"));
        System.out.println(bookDto.get());
        request.getSession().setAttribute("read_book", bookDto.get());
        List<ChapterDto> chapterDto = chaptersService.getByBookId(bookId);
        chapterDto.forEach(System.out::println);
        if(!chapterDto.isEmpty()) {
            chapterDto.sort(Comparator.comparing(ChapterDto::getNumber));
        }
        request.setAttribute("chapters_to_read", chapterDto);
        request.getRequestDispatcher("read_book.ftl").forward(request, response);
    }
}