package org.jdbccourse.tests;

import org.jdbccourse.db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramRead {
    public static void main(String[] args) {

        Connection conn = null;

        // utilizados para LER dados
        Statement st = null;
        ResultSet rs = null;

        try{
            // executa a conexão com o BD;
            conn = DB.getConnection();

            st = conn.createStatement();

            rs = st.executeQuery("SELECT * FROM department");

            // enquanto existir próximo item de tabela...
            while(rs.next()){
                //recupere um inteiro que está no campo Id e uma String que está no campo Nome
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }

        }catch(SQLException e){
            e.printStackTrace();

        }finally {
            DB.closeConnection();
        }
    }
}
