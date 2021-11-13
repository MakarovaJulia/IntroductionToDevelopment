package ru.itis.servletsapp.listeners;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.servletsapp.dao.*;
import ru.itis.servletsapp.dao.impl.*;
import ru.itis.servletsapp.services.*;
import ru.itis.servletsapp.services.impl.*;
import ru.itis.servletsapp.services.validation.Validator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomContextListener implements ServletContextListener {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/term_project";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    String path = "/Users/Julia/uploaded_files";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        FilesRepository filesRepository = new FilesRepositoryImpl(dataSource);
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        BooksRepository booksRepository = new BooksRepositoryImpl(dataSource);
        ChaptersRepository chaptersRepository = new ChaptersRepositoryImpl(dataSource);
        AvatarsService avatarsService = new AvatarsServiceImpl(filesRepository, usersRepository);
        CoversService coversService = new CoversServiceImpl(filesRepository, booksRepository);
        FilesService filesService = new FilesServiceImpl(path,filesRepository);
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        Validator validator = new ValidatorImpl(usersRepository);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder, validator);
        PostsRepository postsRepository = new PostsRepositoryImpl(dataSource);
        PostsService postsService = new PostsServiceImpl(postsRepository);
        CommentsRepository commentsRepository = new CommentsRepositoryImpl(dataSource);
        CommentsService commentsService = new CommentsServiceImpl(commentsRepository);
        ChaptersService chaptersService = new ChaptersServiceImpl(chaptersRepository);
        BooksService booksService = new BooksServiceImpl(booksRepository);
        ObjectMapper objectMapper = new ObjectMapper();
        BackPhotoService backPhotoService = new BackPhotoServiceImpl(filesRepository, usersRepository);
        UsersBooksRepository usersBooksRepository = new UsersBooksRepositoryImpl(dataSource);
        UsersBooksService usersBooksService = new UsersBooksServiceImpl(usersBooksRepository);

        servletContext.setAttribute("avatarsService", avatarsService);
        servletContext.setAttribute("coversService", coversService);
        servletContext.setAttribute("backPhotoService", backPhotoService);
        servletContext.setAttribute("filesService", filesService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("postsService", postsService);
        servletContext.setAttribute("commentsService", commentsService);
        servletContext.setAttribute("booksService", booksService);
        servletContext.setAttribute("chaptersService", chaptersService);
        servletContext.setAttribute("objectMapper", objectMapper);
        servletContext.setAttribute("usersBooksService", usersBooksService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}


