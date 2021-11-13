package ru.itis.servletsapp.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.servletsapp.dto.out.CommentDto;
import ru.itis.servletsapp.dto.out.UserDto;
import ru.itis.servletsapp.services.CommentsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {
    private CommentsService commentsService;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) {
        commentsService = (CommentsService) config.getServletContext().getAttribute("commentsService");
        objectMapper = (ObjectMapper) config.getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDto userDto = (UserDto) request.getSession(true).getAttribute("user");
        CommentDto form = CommentDto.builder()
                .content(request.getParameter("content"))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .author(userDto)
                .build();
        CommentDto createdComment = commentsService.addComment(form);
        objectMapper.writeValue(response.getWriter(), createdComment);
    }
}