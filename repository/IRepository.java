package repository;

import domain.Entity;

import java.util.List;

public interface IRepository <ID, T extends Entity<ID>>{
    Boolean save(T entity);
    List<T> getAll();
    Boolean delete(ID id);
    Boolean update(T entity);
}
