package org.jdbccourse.tests;

import org.jdbccourse.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgramDelete {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try{

            // faz a conexão com o banco
            conn = DB.getConnection();

            // comando SQL
            String sql = "DELETE FROM seller "
                    +"WHERE "
                    +"(Id = ?)";

            // tratamento do comando
            ps = conn.prepareStatement(sql);

            // atualiza os dados a partir do index
            ps.setInt(1, 8);


            int rowsAffected = ps.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);


        }catch(SQLException e){
            e.printStackTrace();

        }finally {
            DB.closeConnection();
        }
    }
}
