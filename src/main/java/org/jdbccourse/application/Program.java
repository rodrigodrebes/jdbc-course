package org.jdbccourse.application;

import org.jdbccourse.db.DB;
import org.jdbccourse.model.entities.Department;
import org.jdbccourse.model.entities.Seller;

import java.sql.Connection;
import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        DB.closeConnection();



        Department obj = new Department(1,"Books");
        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
        System.out.println(obj);
        System.out.println(seller);
    }
}
