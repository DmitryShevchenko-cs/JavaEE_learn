package com.Lab5.profile;

import com.Lab5.Dao.UsersRepositoryJdbcImpl;
import com.Lab5.models.User;
import com.Lab5.repositories.UsersRepository;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;

public class ProfileTools
{
    public static String SESSION_LOGGEDIN_ATTRIBUTE_NAME = "REGISTERED";

    //public static String ACCOUNT_IS_ADMIN_ATTRIBUTE_NAME = "isAdmin";

    public static String ACCOUNT_WHO_IS = "whoIs";
    public static String ACCOUNT_LIST_ATTRIBUTE_NAME = "accounts";

    public static String DATASOURCE_PROPERTIES_FILE = "WEB-INF/classes/db.properties";

//    public static UsersRepository getUsersRepository() {
//        return new UsersRepositoryInMemoryImpl();
//    }
//
//    public static Authenticator getAuthenticator() {
//        return new AuthenticatorImpl();
//    }

    public static UsersRepository getUsersRepository(DataSource ds) {
        return new UsersRepositoryJdbcImpl(ds);
    }

    public static Authenticator getAuthenticator(DataSource ds) {
        return new AuthenticatorImpl(ds);
    }

    public static DataSource getDataSource(final String propertiesFilePath)
    {
        DriverManagerDataSource ds = null;
        Properties properties = new Properties();

        try
        {
            properties.load(new FileInputStream(propertiesFilePath));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            ds = new DriverManagerDataSource();  // spring-jdbc
            ds.setUsername(dbUsername);
            ds.setPassword(dbPassword);
            ds.setUrl(dbUrl);
            ds.setDriverClassName(driverClassName);

            System.out.println(dbUrl + dbUsername + dbPassword + driverClassName);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        return ds;
    }

    public static void doLogin(User account, HttpSession session, DataSource ds)
    {
        session.setAttribute(ProfileTools.SESSION_LOGGEDIN_ATTRIBUTE_NAME, account.getName());
        account.setId(ProfileTools.getUsersRepository(ds).findByName(account.getName()).getId());

        if (Objects.equals(ProfileTools.whoIs(account), AccountType.ADMINISTRATOR)) {
            session.setAttribute(ProfileTools.ACCOUNT_WHO_IS,
                    ProfileTools.whoIs(account));
            session.setAttribute(ProfileTools.ACCOUNT_LIST_ATTRIBUTE_NAME,
                    ProfileTools.getUsersRepository(ds).fetchAll());

        } else if (Objects.equals(ProfileTools.whoIs(account), AccountType.REGISTERED))
        {
            session.setAttribute(ProfileTools.ACCOUNT_WHO_IS,
                    ProfileTools.whoIs(account));
            session.setAttribute("account", account);
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
        ProfileTools.getUsersRepository(ds).save(account);
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
