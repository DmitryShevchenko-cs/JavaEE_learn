package com.Lab5.mocks;

import com.Lab5.models.HasId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryStorage<T extends HasId>
{
    private static Integer _nextId = 1;
    private static final InMemoryStorage _storage;

    static {
        _storage = new InMemoryStorage();
    }

    public static InMemoryStorage getInstance() {
        return _storage;
    }

    private InMemoryStorage() {
        _map = new HashMap<Integer, T>();
    }

    public void put(T item)
    {
        if (item.getId() == null)
            item.setId(getNextId());
        else
        if (_map.containsKey(item.getId()))
            _map.remove(item.getId());
        _map.put(item.getId(), item);
    }

    public Optional<T> get(Integer id)
    {
        final T obj = _map.get(id);
        if (obj != null)
            return Optional.of(obj);
        return Optional.empty();
    }

    private Integer getNextId() {
        return _nextId++;
    }

    public List<T> getAll() {
        return new ArrayList<T>(_map.values());
    }

    private HashMap<Integer, T> _map;
}