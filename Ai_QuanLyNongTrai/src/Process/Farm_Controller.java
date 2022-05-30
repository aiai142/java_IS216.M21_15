/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.ConnectionUtils;
import Model.Farm;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diuai
 */
public class Farm_Controller {
    public static List<Farm> findAllFarm() throws SQLException, ClassNotFoundException {
        List<Farm> listFarm = new ArrayList<>();
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call FIND_ALL_FARM(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);
        while (rs.next()) {
            Farm fa = new Farm(rs.getString("FARMID"), rs.getString("FARMNAME"), rs.getString("FARMADD"));
            listFarm.add(fa);

        }
        return listFarm;
    }

    public static boolean insertFarm(Farm fa) throws SQLException, ClassNotFoundException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call insert_Farm(?,?)}");
        stmt.setString(1, fa.getFarmName());
        stmt.setString(2, fa.getFarmAdd());
        check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;

    }

    public static void deleteFarm(String farmID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call delete_Farm(?)}");
        stmt.setString(1, farmID);
        stmt.execute();

    }

    public static boolean updateFarm(Farm fa) throws SQLException, ClassNotFoundException {
        boolean check = true;
        Connection con = null;
        PreparedStatement stat = null;
        con = ConnectionUtils.getMyConnection();
        CallableStatement stmt = con.prepareCall("{call update_Farm(?,?,?)}");
        stmt.setString(1, fa.getFarmID());
        stmt.setString(2, fa.getFarmName());
        stmt.setString(3, fa.getFarmAdd());
        check=stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }

    public static ArrayList<Farm> findFarm(String farmname) throws SQLException, ClassNotFoundException {
        ArrayList<Farm> listFarm = new ArrayList<>();
        Connection con = ConnectionUtils.getMyConnection();
        PreparedStatement stat = null;
        CallableStatement stmt = con.prepareCall("{call Find_FARM(?, ?)}");
        stmt.setString(1,farmname);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);
        while (rs.next()) {
            String idFarm=rs.getString("FARMID");
            String nameFarm = rs.getString("FARMNAME");
            String addFarm = rs.getString("FARMADD");
            listFarm.add(new Farm(idFarm,nameFarm,addFarm));
        }
        return listFarm;
    }
}
