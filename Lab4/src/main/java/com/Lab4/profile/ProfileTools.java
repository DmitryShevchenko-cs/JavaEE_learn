package com.Lab4.profile;

import com.Lab4.models.User;
import com.Lab4.repositories.UsersRepository;
import com.Lab4.repositories.UsersRepositoryInMemoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Objects;

public class ProfileTools
{
    public static String SESSION_LOGGEDIN_ATTRIBUTE_NAME = "user";

    //public static String ACCOUNT_IS_ADMIN_ATTRIBUTE_NAME = "isAdmin";

    public static String ACCOUNT_WHO_IS = "whoIs";
    public static String ACCOUNT_LIST_ATTRIBUTE_NAME = "accounts";

    public static UsersRepository getUsersRepository() {
        return new UsersRepositoryInMemoryImpl();
    }

    public static Authenticator getAuthenticator() {
        return new AuthenticatorImpl();
    }

    public static void doLogin(User account, HttpSession session)
    {
        session.setAttribute(ProfileTools.SESSION_LOGGEDIN_ATTRIBUTE_NAME, account.getName());

        if (Objects.equals(ProfileTools.whoIs(account), AccountType.ADMINISTRATOR)) {
            session.setAttribute(ProfileTools.ACCOUNT_WHO_IS,
                    ProfileTools.whoIs(account));
            session.setAttribute(ProfileTools.ACCOUNT_LIST_ATTRIBUTE_NAME,
                    ProfileTools.getUsersRepository().fetchAll());

        } else if (Objects.equals(ProfileTools.whoIs(account), AccountType.USER))
        {
            session.setAttribute(ProfileTools.ACCOUNT_WHO_IS,
                    ProfileTools.whoIs(account));
        }
        else if (Objects.equals(ProfileTools.whoIs(account), AccountType.GUEST))
        {
            session.setAttribute(ProfileTools.ACCOUNT_WHO_IS,
                    ProfileTools.whoIs(account));
        }


        session.setAttribute("failedLoginAttemptsCount", 0);
        session.removeAttribute("blockedDate");
        session.removeAttribute("blockedTime");

        account.setLoginDate(ProfileTools.generateLoginDate());
        ProfileTools.getUsersRepository().save(account);
    }

    public static boolean isLoggedIn(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute(SESSION_LOGGEDIN_ATTRIBUTE_NAME) != null;
    }

//    public static boolean isAdmin(User account) {
//        return account != null && Objects.equals(account.getRole(), AccountType.ADMINISTRATOR);
//    }

    public static AccountType whoIs(User account) {
        return account.getRole();
    }

    public static LocalDate generateLoginDate() {
        return LocalDate.now();
    }
}
