package com.Lab5.Dao;

import com.Lab5.builders.UserBuilder;
import com.Lab5.models.User;
import com.Lab5.profile.AccountType;
import com.Lab5.repositories.UsersRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private JdbcTemplate _template;

    // language=SQL
    private static final String SQL_SELECT_ALL =
            "SELECT a.*, t.title AS role FROM account a" +
                    " LEFT JOIN account_type t ON a.account_type_id = t.account_type_id" +
                    " ORDER BY login_name";

    private static final String SQL_SELECT_BY_ID =
            "SELECT a.*, t.title AS role FROM account a" +
                    " LEFT JOIN account_type t ON a.account_type_id = t.account_type_id" +
                    " WHERE account_id = ?";

    private final String SQL_SELECT_BY_LOGIN_NAME =
            "SELECT a.*, t.title AS role FROM account a" +
                    " LEFT JOIN account_type t ON a.account_type_id = t.account_type_id" +
                    " WHERE login_name = ?";

    private final String SQL_SELECT_BY_EMAIL =
            "SELECT a.*, t.title AS role FROM account a" +
                    " LEFT JOIN account_type t ON a.account_type_id = t.account_type_id" +
                    " WHERE email = ?";

    private Map<Integer, User> _users = new HashMap<>();

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        _template = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> find(Integer id)
    {
        _template.query(SQL_SELECT_BY_ID, userRowMapper, id);
        if (_users.containsKey(id))
            return Optional.of(_users.get(id));
        return Optional.empty();
    }

    public User findByName(String name) {
        return _template.queryForObject(SQL_SELECT_BY_LOGIN_NAME, userRowMapper, name);
    }

    public User findByEmail(final String email) {
        return _template.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email);
    }
    @Override
    public List<User> fetchAll() {
        return _template.query(SQL_SELECT_ALL, userRowMapper);
    }

    @Override
    public void save(User model)
    {
        try
        {
            Connection conn = _template.getDataSource().getConnection();

            final AbstractMap.SimpleEntry<Integer, AccountType> accountType =
                    _template.queryForObject(
                            "SELECT * FROM account_type WHERE title = ?",
                            accountTypeRowMapper,
                            model.getRole().toString());

            String sql = model.getId() == null
                    ? "INSERT INTO " +
                    "account(account_type_id, login_name, password, email, birth_date, login_date, taxes) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)"
                    : "UPDATE account SET " +
                    "account_type_id = ?, " +
                    "login_name = ?, " +
                    "password = ?, " +
                    "email = ?, " +
                    "birth_date = ?, " +
                    "login_date = ?, " +
                    "taxes = ? " +
                    "WHERE account_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountType.getKey());
            ps.setString(2, model.getName());
            ps.setString(3, model.getPassword());
            ps.setString(4, model.getEmail());
            ps.setDate(5, toDate(model.getBirthDate()));
            ps.setDate(6, toDate(model.getLoginDate()));
            ps.setDouble(7, model.get_tax());
            if (model.getId() != null)
                ps.setInt(8, model.getId());
            ps.execute();
        }
        catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User model) {
        // FIXME: not implemented
    }

    @Override
    public void delete(Integer id) {
        // FIXME: not implemented
    }

    private static java.sql.Date toDate(LocalDate date) {
        return date != null ? java.sql.Date.valueOf(date) : null;
    }

    private static LocalDate toLocalDate(java.sql.Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    private RowMapper<AbstractMap.SimpleEntry<Integer, AccountType>>
            accountTypeRowMapper = (ResultSet rs, int i) ->
    {
        final Integer id = rs.getInt("account_type_id");
        return id != null
                ? new AbstractMap.SimpleEntry<>(id, AccountType.valueOf(rs.getString("title")))
                : null;
    };

    private RowMapper<User> userRowMapper = (ResultSet rs, int i) ->
    {
        Integer id = rs.getInt("account_id");
        if (! _users.containsKey(id))
        {
            final String role = rs.getString("role");
//            _users.put(id, new User(
//                    id,
//                    role != null ? AccountType.valueOf(role) : AccountType.GUEST,
//                    rs.getString("login_name"),
//                    rs.getString("password"),
//                    rs.getString("email"),
//                    toLocalDate(rs.getDate("birth_date")),
//                    toLocalDate(rs.getDate("login_date")),
//                    rs.getDouble("taxes")
//            ));
            _users.put(id, new UserBuilder(id)
                    .setRole(role != null ? AccountType.valueOf(role) : AccountType.GUEST)
                    .setName(rs.getString("login_name"))
                    .setPassword(rs.getString("password"))
                    .setEmail(rs.getString("email"))
                    .setBirthDate(toLocalDate(rs.getDate("birth_date")))
                    .setLoginDate(toLocalDate(rs.getDate("login_date")))
                    .setTax(rs.getDouble("taxes"))
                    .build());
        }

        return _users.get(id);
    };

}
