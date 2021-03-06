package model.dao.impl;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
      this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
            "INSERT INTO department "
                    +"(Name) "
                    +"VALUES (?)"
            );
            
            st.setString(1, obj.getName());
            
            st.executeUpdate();
                
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
         PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
            "UPDATE department "
                    +"SET Name = ? "
                    +"WHERE Id = ?"
            );
            
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            
            st.executeUpdate();
                
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
      PreparedStatement st = null;
      try{
          st = conn.prepareStatement(
          "DELETE FROM department WHERE id = ?"
          );
          
          st.setInt(1, id);
      }
      catch(SQLException e){
          throw new DbException(e.getMessage());
      }
      finally{
          DB.closeStatement(st);
      }
    }

    @Override
    public Department findById(Integer id) {
      PreparedStatement st = null;
        ResultSet rs = null;
      try{
          st = conn.prepareStatement(
                  "SELECT department.* FROM department WHERE Id = ?"
          );
                  st.setInt(1, id);
                 rs = st.executeQuery();
                  
                  while(rs.next()){
                    Department dep = new Department();
                    dep.setId(rs.getInt("Id"));
                    dep.setName(rs.getString("Name"));
                    return dep;
                  }
                  return null;
      }
        catch(SQLException e){
                  throw new DbException(e.getMessage());
                  }
      finally{
          DB.closeResultSet(rs);
          DB.closeStatement(st);
      }
      
    }

    @Override
    public List<Department> findAll() {
       PreparedStatement st = null;
       ResultSet rs = null;
       List<Department> list = new ArrayList<>();
       try{
           st = conn.prepareStatement(
                   "SELECT * FROM Department"
           );
           
           rs = st.executeQuery();
           while(rs.next()){
               Department dep = new Department();
               dep.setId(rs.getInt("Id"));
               dep.setName(rs.getString("Name"));
               list.add(dep);
           }
           
       }
       catch(SQLException e){
           throw new DbException(e.getMessage());
       }
       
       return list;
    }

}
