package org.jdbccourse.model.DAO.implement;

import org.jdbccourse.db.DB;
import org.jdbccourse.model.DAO.SellerDAO;
import org.jdbccourse.model.entities.Department;
import org.jdbccourse.model.entities.Seller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDAOJDBC implements SellerDAO {

    private Connection conn;

    public SellerDAOJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

        PreparedStatement ps = null;

        String sql = "INSERT INTO seller "
                +"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                +"VALUES "
                +"(?,?,?,?,?)";

        try{
            DB.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // placeholders
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getEmail());
            ps.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            ps.setDouble(4, obj.getSalary());
            ps.setInt(5, obj.getDepartment().getId());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected>0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            }else{
                throw new SQLException("Nenhuma linha foi alterada");
            }

        }catch(SQLException e){
            e.printStackTrace();
        } finally{
            DB.closeConnection();
        }

    }

    @Override
    public void update(Seller obj) {

        PreparedStatement ps = null;

        String sql = "UPDATE seller "
                +"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                +"WHERE Id = ?";

        try{
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // placeholders
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getEmail());
            ps.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            ps.setDouble(4, obj.getSalary());
            ps.setInt(5, obj.getDepartment().getId());
            ps.setInt(6, obj.getId());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        } finally{
            DB.closeConnection();
        }


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

                // precisamos associar os objetos em memória

                Department dep = instantiateDepartment(rs);

                Seller seller = instantiateSeller(rs, dep);

                return seller;
            }
            return null;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public List<Seller> findAll() {

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT seller.*, department.Name as DepName "
                +"FROM seller INNER JOIN department "
                +"ON seller.DepartmentId = department.Id "
                +"ORDER BY Name";
        try{
            ps = conn.prepareStatement(sql);

            // o resultado da operação é salvo na variável resultset
            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){

                // se o departamento já existir, reaproveita-se ele
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(rs, dep);

                list.add(seller);

            }
            return list;
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
    public List<Seller> finByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT seller.*, department.Name as DepName "
                +"FROM seller INNER JOIN department "
                +"ON seller.DepartmentId = department.Id "
                +"WHERE DepartmentId = ? "
                +"ORDER BY Name";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, department.getId());

            // o resultado da operação é salvo na variável resultset
            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<>();

            Map<Integer, Department> map = new HashMap<>();


            while(rs.next()){

                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(rs, dep);

                list.add(seller);

            }
            return list;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        finally {
            DB.closeConnection();
        }
    }


    // instanciações de classes

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(dep);
        return seller;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }
}


