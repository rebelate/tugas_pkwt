package repository;

import model.MenuItem;

import java.util.List;

public interface IMenuRepository {
    MenuItem getById(int id);

    List<MenuItem> getAll();
}
