package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.ChapterDto;
import ru.itis.servletsapp.services.ChaptersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/add-chapter")
public class AddChapterServlet extends HttpServlet {
    private ChaptersService chaptersService;

    @Override
    public void init(ServletConfig config) {
        this.chaptersService = (ChaptersService) config.getServletContext().getAttribute("chaptersService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bookId = Long.parseLong(request.getParameter("book_id"));
        request.getSession().setAttribute("book_id", bookId);
        request.getRequestDispatcher("add_chapter.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ChapterDto form = ChapterDto.builder()
                .bookId((Long) request.getSession().getAttribute("book_id"))
                .number(Long.valueOf(request.getParameter("number")))
                .title(request.getParameter("title"))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .content(request.getParameter("content"))
                .build();
       chaptersService.addChapter(form);
       response.sendRedirect("/profile");
    }
}