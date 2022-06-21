/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import ConnectDB.*;
import Model.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DoQuynhChi
 */
public class C_Product {

    public static ArrayList<Product> listProduct() {
        ArrayList<Product> arrListProduct = new ArrayList<>();
        
        Connection conn;
            try {
            conn = (Connection) ConnectionUtils.getMyConnection();

            ResultSet rs;
            CallableStatement stmt = conn.prepareCall("{call load_product(?)}");
            stmt.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            stmt.execute();
            
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                String id = rs.getString("proID");
                String name = rs.getString("proName");
                int price = rs.getInt("proPrice");
                String farmID = rs.getString("farmID");
                String type = rs.getString("proType");
                String image = rs.getString("image");
                String engName = rs.getString("englishName");
                
                arrListProduct.add(new Product(id, name, price, farmID, type, image, engName));
            }

            for (Product pro : arrListProduct) {
                CallableStatement stmt2 = conn.prepareCall("{call ?:= quantity_Product(?)}");
                stmt2.setString(2, pro.getProID());
                stmt2.registerOutParameter(1, java.sql.Types.INTEGER);

                stmt2.execute();
                pro.setQuantity(stmt2.getInt(1));
            }
            
            conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(C_Product.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(C_Product.class.getName()).log(Level.SEVERE, null, ex);
            }

        return arrListProduct;
}
    
    public static String getProID(String tenSP) throws ClassNotFoundException, SQLException {

        String maSP;

        Connection conn = (Connection) ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call ?:= get_proID(?)}");
        stmt.setString(2, tenSP);
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.execute();

        maSP = stmt.getString(1);

        conn.close();
        return maSP;
    }

}
