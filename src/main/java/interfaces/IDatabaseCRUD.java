package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabaseCRUD<T> {

    int delete(Long id) throws SQLException;

    T search(Long id) throws SQLException;

    void save(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    ArrayList<T> findAll() throws SQLException;

}