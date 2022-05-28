/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Process.Random_Discount;
import View.AdminHome;
import ConnectDB.ConnectionUtils;
import Model.Discount;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Types;
import javax.swing.JTextField;

/**
 *
 * @author diuai
 */
public class DiscountController {

    public static List<Discount> findAllDis() throws SQLException, ClassNotFoundException {
        List<Discount> listDis = new ArrayList<>();
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call FIND_ALL_DIS(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
        while (rs.next()) {
            Discount dis = new Discount(rs.getString("DISID"), rs.getString("DISCODE"),
                    rs.getDouble("VALUE"), rs.getString("CUSTYPE"), rs.getDate("STARTDATE"),
                    rs.getDate("ENDDATE"), 0);
            listDis.add(dis);
        }
        for (Discount dis : listDis) {
            stmt = con.prepareCall("{call ?:=check_disid(?)}");
            stmt.setString(2, dis.getDisCode());
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.execute();
            dis.setStatus(stmt.getInt(1));
        }
        return listDis;

    }

    public static boolean insertDis(Discount dis) throws SQLException, ClassNotFoundException {
//        boolean check = true;
        Connection conn = null;
        PreparedStatement statement = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt = conn.prepareCall("{call insert_Discount(?,?,?,?,?)}");
        stmt.setString(1, dis.getDisCode());
        stmt.setDouble(2, dis.getValue());
        stmt.setString(3, dis.getCusType());
        stmt.setDate(4, new java.sql.Date(dis.getStartDate().getTime()));
        stmt.setDate(5, new java.sql.Date(dis.getEndDate().getTime()));
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }

    public static void deleteDis(String disID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement statement = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call delete_Dis(?)}");
        stmt.setString(1, disID);
        stmt.execute();
    }

    public static boolean updateDis(Discount dis) throws SQLException, ClassNotFoundException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call update_Dis(?,?,?,?,?,?)}");
        stmt.setString(1, dis.getDisID());
        stmt.setString(2, dis.getDisCode());
        stmt.setDouble(3, dis.getValue());
        stmt.setString(4, dis.getCusType());
        stmt.setDate(5, new java.sql.Date(dis.getStartDate().getTime()));
        stmt.setDate(6, new java.sql.Date(dis.getEndDate().getTime()));
        check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;

    }

    public static ArrayList<Discount> findDis(String discode) throws SQLException, ClassNotFoundException {
        ArrayList<Discount> listDis = new ArrayList<>();
        Connection con = ConnectionUtils.getMyConnection();
        PreparedStatement stat = null;
        CallableStatement stmt = con.prepareCall("{call Find_DIS(?,?)}");
        stmt.setString(1, discode);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);
        while (rs.next()) {
            String disid = rs.getString("DISID");
            String code = rs.getString("DISCODE");
            Double value = rs.getDouble("VALUE");
            String loai = rs.getString(rs.getString("CUSTYPE"));
            Date ngaybd = rs.getDate("STARTDATE");
            Date ngaykt = rs.getDate("ENDDATE");
            listDis.add(new Discount(disid, code, value, loai, ngaybd, ngaykt, 0));
        }
        for (Discount dis : listDis) {
            stmt = con.prepareCall("{call ?:=check_disid(?)}");
            stmt.setString(2, dis.getDisID());
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.execute();
            dis.setStatus(stmt.getInt(1));
        }
        return listDis;
    }
}
