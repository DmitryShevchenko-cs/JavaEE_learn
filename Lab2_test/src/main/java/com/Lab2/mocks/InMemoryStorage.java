package com.Lab2.mocks;

import com.Lab2.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryStorage
{
// переменная, которая хранит ссылку на единственный экземпляр объекта класса InMemoryStorage
    private static final InMemoryStorage _storage;

// вызывается один раз при загрузке класса в JVM
    static {
        _storage = new InMemoryStorage();
    }

// метод, предоставляющий доступ к объекту класса
    public static InMemoryStorage getInstance() {
        return _storage;
    }

// приватный констуктор, выполняющий инициализацию списка
    private InMemoryStorage()
    {
        _users = new ArrayList<>();
        _users.add(new User("John", "qwerty", "John@gmail.com", LocalDate.parse("1994-02-02")));
        _users.add(new User("John1", "qwerty", "John1@gmail.com", LocalDate.parse("1994-02-02")));
        _users.add(new User("John2", "qwerty", "John2@gmail.com", LocalDate.parse("1994-02-02")));
        _users.add(new User("John3", "qwerty", "John3@gmail.com", LocalDate.parse("1994-02-02")));
        _users.add(new User("Simon", "simon", "Simon@gmail.com", LocalDate.parse("1994-02-02")));
        _users.add(new User("Alex", "qwerty", "Alex@gmail.com", LocalDate.parse("1994-02-02")));
    }

// метод, возвращающий список пользователей
    public List<User> getAllUsers() {
        return _users;
    }

    public List<User> getByName(String name){
        if(name != "") return _users.stream().filter(el
                -> el.get_name().contains(name)).collect(Collectors.toList());
        return new ArrayList<>();
    }

    public User findByNameAndPassword(String name, String password){
        for (User user : _users) {
            if (user.get_name().equals(name) && user.get_password().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User findByEmailAndPassword(String email, String password){
        for (User user : _users) {
            if (user.get_email().equals(email) && user.get_password().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void set_user(User user) {
        this._users.add(user);
    }

    // поле-список, хранящее список пользователей системы
    private List<User> _users;

}