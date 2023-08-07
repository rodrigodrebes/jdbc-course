package org.jdbccourse.model.DAO.implement;

import org.jdbccourse.db.DB;
import org.jdbccourse.model.DAO.SellerDAO;
import org.jdbccourse.model.entities.Department;
import org.jdbccourse.model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDAOJDBC implements SellerDAO {

    private Connection conn;

    public SellerDAOJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT seller.*, department.Name as DepName "
                +"FROM seller INNER JOIN department "
                +"ON seller.DepartmentId = department.Id "
                +"WHERE seller.Id = ?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // o resultado da operação é salvo na variável resultset
            rs = ps.executeQuery();

            if(rs.next()){
                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));

                Seller seller = new Seller();
                seller.setId(rs.getInt("Id"));
                seller.setName(rs.getString("Name"));
                seller.setEmail(rs.getString("Email"));
                seller.setSalary(rs.getDouble("BaseSalary"));
                seller.setBirthDate(rs.getDate("BirthDate"));
                seller.setDepartment(dep);

                return seller;
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        finally {
            DB.closeConnection();
        }

    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
