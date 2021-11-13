package ru.itis.servletsapp.services;

import ru.itis.servletsapp.dto.in.UserForm;
import ru.itis.servletsapp.dto.out.UserDto;

public interface SignInService {
    UserDto signIn(UserForm userForm);
}