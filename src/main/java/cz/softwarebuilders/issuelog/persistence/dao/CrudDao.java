package cz.softwarebuilders.issuelog.persistence.dao;

import java.util.List;

public interface CrudDao<T> {
    void save(T entity);

    T load(Long id);

    List<T> getAll();

    void flush();

    void flushAndClear();
}
