/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Employee;
import Model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ngoclinh
 */
public class Controller_Employee {
    
    //Lay danh sach tat ca thong tin cua cac Employee de hien len bang
    public static List<Employee> findAll() throws SQLException, ClassNotFoundException {
        List<Employee> empList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        conn = (Connection) ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call display_all_Employee(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            Employee emp = new Employee(rs.getString("EMPID"),
                    rs.getString("FARMID"),
                    rs.getString("EMPNAME"), 
                    rs.getString("EMPADD"),
                    rs.getString("EMPPHONE"), 
                    rs.getString("EMPEMAIL"),
                    rs.getDate("STARTDATE"), 
                    rs.getString("USERID")
                    );
            empList.add(emp);
        }
        
        conn.close();
        return empList;
    }
    
    public static boolean insert(User us, Employee emp) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_newEmp(?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, us.getUserName());
        stmt.setString(2, us.getUserPassword());
        stmt.setString(3, us.getUserRole());
        stmt.setString(4, emp.getFarmID());
        stmt.setString(5, emp.getEmpName());
        stmt.setString(6, emp.getEmpAdd());
        stmt.setString(7, emp.getEmpPhone());
        stmt.setString(8, emp.getEmpEmail());
        stmt.setDate(9, (java.sql.Date) emp.getStartDate());

        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return false;
    }
    
    public static boolean delete(String str) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call delete_Emp(?)}");
        stmt.setString(1, str);
        boolean check = stmt.execute();
        conn.close();
        
        if (check == false) {
            return true;
        }
        return true;

    }
    
    //cap nhat mot nhan vien
    public static boolean update(Employee emp) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call update_Employee(?,?,?,?,?,?,?,?)}");
        stmt.setString(1, emp.getEmpID());
        stmt.setString(2, emp.getFarmID());
        stmt.setString(3, emp.getEmpName());
        stmt.setString(4, emp.getEmpAdd());
        stmt.setString(5, emp.getEmpPhone());
        stmt.setString(6, emp.getEmpEmail());
        stmt.setDate(7, (java.sql.Date) emp.getStartDate());
        stmt.setString(8, emp.getUserID());
        System.out.println(emp.getEmpID());
        boolean check = stmt.execute();

        conn.close();

        if (check == false) {
            return true;
        }
        return false;
    }
    
    public static ArrayList<Employee> Search_Employee(String manv, String mant, String tennv,
    String diachi, String sdt, String email, String erole, String usname) throws ClassNotFoundException, SQLException {
        ArrayList<Employee> EmpList = new ArrayList<>();
        Connection conn = ConnectionUtils.getMyConnection();
        PreparedStatement statement = null;
        
        CallableStatement stmt = conn.prepareCall("{call search_Employee_forAll(?,?,?,?,?,?,?,?,?)}");
        stmt.setString(1, manv);
        stmt.setString(2, mant);
        stmt.setString(3, tennv);
        stmt.setString(4, diachi);
        stmt.setString(5, sdt);
        stmt.setString(6, email);
        stmt.setString(7, erole);
        stmt.setString(8, usname);
        stmt.registerOutParameter(9, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(9);

        while (rs.next()) {
            String empid = rs.getString("EMPID");
            String farmid = rs.getString("FARMID");
            String empname = rs.getString("EMPNAME");
            String empadd = rs.getString("EMPADD");
            String empphone = rs.getString("EMPPHONE");
            String empemail = rs.getString("EMPEMAIL");
            Date startdate = rs.getDate("STARTDATE");
            String userid = rs.getString("USERID");

            EmpList.add(new Employee(empid, farmid, empname,empadd, empphone, empemail, startdate, userid));
        }
        
        conn.close();
        return EmpList;
    }
    
    
}

