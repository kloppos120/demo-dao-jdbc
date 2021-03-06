
package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(db.DB.getConnection());
    }
    
    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDaoJDBC(db.DB.getConnection());
    }
    
}
