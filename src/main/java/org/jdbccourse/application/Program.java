package org.jdbccourse.application;

import org.jdbccourse.db.DB;
import org.jdbccourse.model.entities.Department;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        DB.closeConnection();

        Department obj = new Department(1,"Books");
        System.out.println(obj);
    }
}
