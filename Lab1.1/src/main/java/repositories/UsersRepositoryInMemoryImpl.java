package repositories;

import mocks.InMemoryStorage;
import models.User;

import java.util.List;

public class UsersRepositoryInMemoryImpl implements UsersRepository
{
    public List<User> findByName(String name) { return InMemoryStorage.getInstance().getByName(name);}

    public List<User> findAll() {
        return InMemoryStorage.getInstance().getAllUsers();
    }

    public void set_user(User user) {
        InMemoryStorage.getInstance().set_user(user);
    }
}
