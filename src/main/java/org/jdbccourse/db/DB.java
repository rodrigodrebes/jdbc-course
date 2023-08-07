package org.jdbccourse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {

    private static Connection conn = null;
    public static Connection getConnection() {
        if(conn == null){
            try{
                String url = "jdbc:mysql://localhost:3306/coursejdbc";
                String username = "dba";
                String password = "12345";
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Conexão ao banco realizada com sucesso");
            }
            catch(SQLException e){
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
        return conn;

    }

    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                    throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
                }
            }
        }


}


