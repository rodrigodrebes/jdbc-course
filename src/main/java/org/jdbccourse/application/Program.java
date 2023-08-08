package org.jdbccourse.application;

import org.jdbccourse.db.DB;
import org.jdbccourse.model.DAO.DaoFactory;
import org.jdbccourse.model.DAO.SellerDAO;
import org.jdbccourse.model.DAO.implement.SellerDAOJDBC;
import org.jdbccourse.model.entities.Department;
import org.jdbccourse.model.entities.Seller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

//        Connection conn = DB.getConnection();
//        DB.closeConnection();

//        Department obj = new Department(1,"Books");
//        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
//        System.out.println(obj);
//        System.out.println(seller);


//        System.out.println("------ TEST 01: seller findById");
//        // teste Find By Id
        SellerDAO sellerDAO = DaoFactory.createsellerDao();
//
        Seller vendedor = sellerDAO.findById(4);
//
//        System.out.println(vendedor);
//
//
//        System.out.println("\n------- TEST 02: seller findByDepartmentId");
//        Department depto = new Department(2, null);
//        List<Seller> list = sellerDAO.finByDepartment(depto);
//        for(Seller seller : list){
//            System.out.println(seller);
//        }
//
//
//        System.out.println("\n------- TEST 03: seller findByAll");
//       list = sellerDAO.findAll();
//        for(Seller seller : list){
//            System.out.println(seller);
//        }

//        System.out.println("\n------- TEST 04: seller insert");
//        Seller newSeller = new Seller(null, "Ludwig", "lud@gmail.com", new Date(), 8000.0, depto);
//        sellerDAO.insert(newSeller);
//        System.out.println("Inserted! new id = "+ newSeller.getId());
//        }

         System.out.println("\n------- TEST 05: seller update");
            vendedor = sellerDAO.findById(1);
            vendedor.setName("Rodrigo");
            vendedor.setEmail("rodrigo@gmail.com");
            sellerDAO.update(vendedor);
        System.out.println("Update completed! ");


    }
}

