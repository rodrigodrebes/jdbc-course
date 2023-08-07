package org.jdbccourse.tests;

import org.jdbccourse.db.DB;

import java.sql.*;

public class ProgramUpdate {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try{

            // faz a conexão com o banco
            conn = DB.getConnection();

            // comando SQL
            String sql = "UPDATE seller "
                    +"SET BaseSalary = BaseSalary + ? "
                    +"WHERE "
                    +"(DepartmentId = ?)";

            // tratamento do comando
            ps = conn.prepareStatement(sql);

            // atualiza os dados a partir do index
            ps.setDouble(1, 200.0);
            ps.setInt(2, 2);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);


        }catch(SQLException e){
            e.printStackTrace();

        }finally {
            DB.closeConnection();
        }
    }
}
