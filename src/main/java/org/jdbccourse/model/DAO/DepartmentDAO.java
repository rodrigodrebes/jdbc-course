package org.jdbccourse.model.DAO;

import org.jdbccourse.model.entities.Department;

import java.util.List;

public interface DepartmentDAO {

    void insert(Department obj);

    void update(Department obj);

    void deleteById(Integer id);

    Department findById(Integer id);

    List<Department> findAll();

}
