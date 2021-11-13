package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.UserDto;
import ru.itis.servletsapp.services.UsersBooksService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-book-to-library")
public class AddBookToLibraryServlet extends HttpServlet {
    private UsersBooksService usersBooksService;

    @Override
    public void init(ServletConfig config) {
        usersBooksService = (UsersBooksService) config.getServletContext().getAttribute("usersBooksService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        Long bookId = (Long) request.getSession().getAttribute("read_book_id");
        usersBooksService.add(userDto.getId(),bookId);
        response.sendRedirect("/profile");
    }
}