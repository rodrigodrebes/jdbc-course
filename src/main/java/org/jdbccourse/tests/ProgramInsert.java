package org.jdbccourse.tests;

import org.jdbccourse.db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProgramInsert {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;

        // utilizado para inserir dados
        PreparedStatement ps = null;

        try{
            // executa a conexão com o BD;
            conn = DB.getConnection();

            // comando SQL
            String sql = "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?,?,?,?,?)";

            // tratamento do comando SQL
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // faz a inserção baseado no index
            ps.setString(1,"Carlos Marques");
            ps.setString(2, "carl@gmail.com");
            ps.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            ps.setDouble(4, 3500.0);
            ps.setInt(5,4);

            int rowsAffected = ps.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        }catch(SQLException | ParseException e){
            e.printStackTrace();

        }finally {
            DB.closeConnection();
        }
    }
}
