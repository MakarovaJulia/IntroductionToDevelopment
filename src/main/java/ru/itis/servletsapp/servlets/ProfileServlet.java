package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.BookDto;
import ru.itis.servletsapp.dto.out.UserDto;
import ru.itis.servletsapp.services.BooksService;
import ru.itis.servletsapp.services.PostsService;
import ru.itis.servletsapp.services.UsersBooksService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private PostsService postsService;
    private BooksService booksService;
    private UsersBooksService usersBooksService;

    @Override
    public void init(ServletConfig config) {
        postsService = (PostsService) config.getServletContext().getAttribute("postsService");
        booksService = (BooksService) config.getServletContext().getAttribute("booksService");
        usersBooksService = (UsersBooksService) config.getServletContext().getAttribute("usersBooksService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        request.setAttribute("user", userDto);
        request.setAttribute("posts", postsService.getByAuthorId(userDto.getId()));
        request.setAttribute("books", booksService.getByAuthorId(userDto.getId()));
        List<BookDto> bookDtoList = usersBooksService.findByUserId(userDto.getId()).stream().map(
                n -> booksService.getById(n).get()).collect(Collectors.toList());
        for(BookDto book : bookDtoList ){
            System.out.println(book);
        }
        request.setAttribute("books_in_library", bookDtoList);
        request.getRequestDispatcher("/profile.ftl").forward(request, response);
    }
}