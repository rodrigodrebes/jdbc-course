package org.jdbccourse.model.DAO;

import org.jdbccourse.model.entities.Department;
import org.jdbccourse.model.entities.Seller;

import java.util.List;

public interface SellerDAO {

    void insert(Seller obj);

    void update(Seller obj);

    void deleteById(Integer id);

    Seller findById(Integer id);

    List<Seller> findAll();

    List<Seller> finByDepartment(Department department);

}
