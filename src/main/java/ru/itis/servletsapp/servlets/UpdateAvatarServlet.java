package ru.itis.servletsapp.servlets;

import ru.itis.servletsapp.dto.out.UserDto;
import ru.itis.servletsapp.model.FileInfo;
import ru.itis.servletsapp.services.AvatarsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/update-avatar")
@MultipartConfig
public class UpdateAvatarServlet extends HttpServlet {
    private AvatarsService avatarsService;

    @Override
    public void init(ServletConfig config) {
        this.avatarsService = (AvatarsService) config.getServletContext().getAttribute("avatarsService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        HttpSession session = request.getSession(true);
        UserDto userDto = (UserDto) session.getAttribute("user");
        FileInfo fileInfo = avatarsService.saveFileToStorage(
                userDto,
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType(),
                part.getSize());
        userDto.setAvatarId(fileInfo.getId());
        session.setAttribute("user", userDto);
        response.sendRedirect("/profile");
    }
}