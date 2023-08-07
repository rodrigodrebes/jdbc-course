package org.jdbccourse.model.DAO;

import org.jdbccourse.db.DB;
import org.jdbccourse.model.DAO.implement.SellerDAOJDBC;

public class DaoFactory {

    public static SellerDAO createsellerDao(){

        return new SellerDAOJDBC(DB.getConnection());
    }
}
