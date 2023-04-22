package com.Lab4.repositories;

import com.Lab4.mocks.InMemoryStorage;
import com.Lab4.models.Taxes.Incomes.JobIncome;
import com.Lab4.models.Taxes.Incomes.PropertySaleIncome;
import com.Lab4.models.User;
import com.Lab4.profile.AccountType;

import java.util.List;
import java.util.Optional;

public class UsersRepositoryInMemoryImpl implements UsersRepository {
    static {
        User u = new User(AccountType.REGISTERED, "user", "qwe", "user@gmail.com");
        u.addTax(new JobIncome(10000));
        u.addTax(new PropertySaleIncome(5000));
        InMemoryStorage<User> storage = InMemoryStorage.getInstance();
        storage.put(u);
        storage.put(new User(AccountType.ADMINISTRATOR, "admin", "qwe", "admin@gmail.com"));
        storage.put(new User(AccountType.GUEST, "moderator", "qwe", "guest@gmail.com"));
    }

    @Override
    public Optional<User> find(Integer id) {
        return InMemoryStorage.getInstance().get(id);
    }

    @Override
    public User findByName(final String name) {
        final List<User> accounts = InMemoryStorage.getInstance().getAll();

        System.out.println("findByName -> fetchAll = " + accounts);
        for (User acc : accounts) {
            if (name.equals(acc.getName())) {
                System.out.println("findByName -> " + acc);
                return acc;
            }
        }
        return null;
    }

    @Override
    public User findByEmail(final String email) {
        final List<User> accounts = InMemoryStorage.getInstance().getAll();
        for (User acc : accounts) {
            if (email.equals(acc.getEmail()))
                return acc;
        }
        return null;
    }

    @Override
    public void save(User model) {
        InMemoryStorage.getInstance().put(model);
    }

    @Override
    public void update(User model) {
        InMemoryStorage.getInstance().put(model);
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public final List<User> fetchAll() {
        return InMemoryStorage.getInstance().getAll();
    }
}

