package com.Lab3.profile;

import com.Lab3.models.StaticAuthenticatorImpl;
import com.Lab3.models.User;
import com.Lab3.repositories.UsersRepository;
import com.Lab3.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class ProfileTools {
    public static final String SESSION_LOGGEDIN_ATTRIBUTE_NAME = "user";
    public static final String ACCOUNT_IS_ADMIN_ATTRIBUTE_NAME = "isAdmin";

    // метод возвращает true в том случае, если пользователь залогинился

    public static boolean isLoggedIn(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        return session != null &&
                session.getAttribute(SESSION_LOGGEDIN_ATTRIBUTE_NAME) != null;
    }

    public static Authenticator getAuthenticator() {
        return new StaticAuthenticatorImpl();
    }

    public static boolean isAdmin(User account) {
        return account != null && Objects.equals(account.getRole(), AccountType.ADMINISTRATOR);
    }

    public static UsersRepository getUsersRepository() {
        return new UsersRepositoryInMemoryImpl();
    }

}
