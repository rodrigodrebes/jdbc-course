package org.jdbccourse.application;

import org.jdbccourse.db.DB;

import java.sql.Connection;

public class Program {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        DB.closeConnection();
    }
}
