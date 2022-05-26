/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;
import Process.Random_Discount;
import ConnectDB.ConnectionUtils;
import Model.Discount;
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
        while(rs.next()) {
            Discount dis = new Discount(rs.getString("DISID"), rs.getString("DISCODE"),
                    rs.getFloat("VALUE"), rs.getString("CUSTYPE"),rs.getDate("STARTDATE"),
                    rs.getDate("ENDDATE"));
            listDis.add(dis);
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
        stmt.setFloat(2, dis.getValue());
        stmt.setString(3, dis.getCusType());
        stmt.setDate( 4,(java.sql.Date)dis.getStartDate());
        stmt.setDate(5,(java.sql.Date)dis.getEndDate());
        boolean check = stmt.execute();
        if (check == false) {
            return true;
        }
        return false;
    }
    
}
