package org.jdbccourse.model.DAO;

import org.jdbccourse.model.DAO.implement.SellerDAOJDBC;

public class DaoFactory {

    public static SellerDAO createsellerDao(){

        return new SellerDAOJDBC();
    }
}
