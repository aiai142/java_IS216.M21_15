package View;

import ConnectDB.ConnectionUtils;
import Model.Employee;
import Model.Stock;
import Model.Transport;
import Model.User;
import Process.Controller_Employee;
import Process.Controller_Stock;
import Process.Controller_Transport;
import Model.Customer;
import Model.Discount;
import Model.Farm;
import Model.Inventory_Product;
import Model.Inventory_Resources;
import Model.Order_Export;
import Model.Product;
import Model.Resources;
import Model.Supplier;
import Process.C_AdminHome;
import Process.Controller_Inventory_Product;
import Process.Controller_Inventory_Resources;
import Process.Controller_Product;
import Process.Controller_Resource;
import Process.Controller_Statistical;
import Process.Customer_Controller;
import Process.Discount_Controller;
import Process.Farm_Controller;
import Process.RandomStringGenerator;
import Process.Supplier_Controller;
import com.itextpdf.text.DocumentException;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.CallableStatement;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.sql.Date;
import java.text.NumberFormat;
import javax.swing.JComboBox;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
public class AdminHome extends javax.swing.JFrame {

    ArrayList<Order_Export> listOrderExport;
    DefaultTableModel tableModelHD;
    String id_login;
    int index = 0;

    DefaultTableModel tableModel8; //tableModel cua stock
    DefaultTableModel tableModel9; //tableModel cua employee
    DefaultTableModel tableModel10; //tableModel cua transport

    //Bien global cho trang stock
    List<Stock> stockList;

    //Bien global cho user
    List<User> userList;

    //Bien global cho trang employee
    List<Employee> employeeList;

    //Bien global cho trang transport
    List<Transport> transportList;

    /**
     * Creates new form AdminHome
     */
    DefaultTableModel tableModel;
    DefaultTableModel tableModel1;
    DefaultTableModel tableModel2;
    DefaultTableModel tableModel3;
    //Bien list chung cho nong trai
    List<Farm> listFarm = new ArrayList<>();
    //Bien list chung cho Nha cung cap
    List<Supplier> listSup = new ArrayList<>();
    //Bien list chung cho Khach hang
    List<Customer> listCus = new ArrayList<>();
    // Bien list chung cho khuyen mai
    List<Discount> listDis = new ArrayList<>();

    DefaultTableModel tableModel5;
    DefaultTableModel tableModel7;
    DefaultTableModel tableModel6;
    DefaultTableModel tableModel11;

    //Bien list global cho trang nguyen vat lieu
    List<Resources> resourcesList;

    //Bien global cho trang quan ly TON NGUYEN VAT LIEU
    List<Inventory_Resources> in_resourcesList;
    List<String> data_combobox_id_resources;
    List<String> data_combobox_stockid;

    //bien global cho trang quan ly nong san
    List<Product> productList;
    List<String> data_combobox_farmid;

    //Bien global cho trang quan ly TON NONG SAN
    List<Inventory_Product> in_productList;
    List<String> data_combobox_id_product;
    List<String> data_combobox_stockid_pro;

    String name;

    CardLayout cardlayout;

    public AdminHome(String id) throws SQLException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null);
        cardlayout = (CardLayout) jpnCardLayout.getLayout();
        //Set table cho quan li nong trai

        //bang quan ly stock
        tableModel8 = (DefaultTableModel) jTable8.getModel();
        setCellsAlignment(jTable8, SwingConstants.CENTER);
        setHeader(jTable8);

        //bang quan ly Employee
        tableModel9 = (DefaultTableModel) jTable9.getModel();
        setCellsAlignment(jTable9, SwingConstants.CENTER);
        setHeader(jTable9);

        //bang quan ly Transport
        tableModel10 = (DefaultTableModel) jTable10.getModel();
        setCellsAlignment(jTable10, SwingConstants.CENTER);
        setHeader(jTable10);

        tableModel = (DefaultTableModel) tableNongTrai.getModel();
        setCellsAlignment(tableNongTrai, SwingConstants.CENTER);
        setHeader(tableNongTrai);

        //Set table cho quan li nha cung cap
        tableModel1 = (DefaultTableModel) tableSup.getModel();
        setCellsAlignment(tableSup, SwingConstants.CENTER);
        setHeader(tableSup);

        //Set table cho quan li khach hang
        tableModel2 = (DefaultTableModel) tableKH.getModel();
        setHeader(tableKH);


        //Set table cho quan li ma khuyen mai
        tableModel3 = (DefaultTableModel) tableKM.getModel();
        setCellsAlignment(tableKM, SwingConstants.CENTER);
        setHeader(tableKM);


        //bang quan ly nguyen vat lieu
        tableModel5 = (DefaultTableModel) table_ds_nguyenVL.getModel();
        setCellsAlignment(table_ds_nguyenVL, SwingConstants.CENTER);
        setHeader(table_ds_nguyenVL);

        //bang quan ly ton nguyen vat lieu
        tableModel7 = (DefaultTableModel) table_ds_inven_re.getModel();
        setCellsAlignment(table_ds_inven_re, SwingConstants.CENTER);
        setHeader(table_ds_inven_re);

        //bang quan ly nong san
        tableModel6 = (DefaultTableModel) table_ds_nong_san.getModel();
        setCellsAlignment(table_ds_nong_san, SwingConstants.CENTER);
        setHeader(table_ds_nong_san);

        //bang quan ly ton nong san
        tableModel11 = (DefaultTableModel) table_ds_ton_ns.getModel();
        setCellsAlignment(table_ds_ton_ns, SwingConstants.CENTER);
        setHeader(table_ds_ton_ns);

        //bang danh sach hoa don
        tableModelHD = (DefaultTableModel) jTableListOrderExport.getModel();
        setHeader(jTableListOrderExport);
        
        //
        id_login = id;
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call load_name(?,?)}");
        stmt.setString(1, id_login);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
        stmt.execute();
        
        name = stmt.getString(2);
        
        cbb_dx_card1.addItem(name);
        cbb_dx_card1.setSelectedIndex(1);
        

        cbb_dx_card2.addItem(name);
        cbb_dx_card2.setSelectedIndex(1);

        cbb_dx_card3.addItem(name);
        cbb_dx_card3.setSelectedIndex(1);

        cbb_dx_card4.addItem(name);
        cbb_dx_card4.setSelectedIndex(1);

        cbb_dx_card5.addItem(name);
        cbb_dx_card5.setSelectedIndex(1);

        cbb_dx_card6.addItem(name);
        cbb_dx_card6.setSelectedIndex(1);

        cbb_dx_card7.addItem(name);
        cbb_dx_card7.setSelectedIndex(1);

        cbb_dx_card8.addItem(name);
        cbb_dx_card8.setSelectedIndex(1);

        cbb_dx_card9.addItem(name);
        cbb_dx_card9.setSelectedIndex(1);

        cbb_dx_card10.addItem(name);
        cbb_dx_card10.setSelectedIndex(1);

        cbb_dx_card12.addItem(name);
        cbb_dx_card12.setSelectedIndex(1);

        cbb_dx_card13.addItem(name);
        cbb_dx_card13.setSelectedIndex(1);

        try {
            reset_product();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void setHeader(JTable table) {
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(Color.decode("#19647E"));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
    }

    public void reset_statistical() {
        cbb_thong_ke.setSelectedIndex(-1);
        cbb_thong_ke_nam.setSelectedIndex(-1);
        cbb_thong_ke_thang.setSelectedIndex(-1);

        cbb_thong_ke_sp.setSelectedIndex(-1);
        cbb_thong_ke_sp_nam.setSelectedIndex(-1);
        cbb_thong_ke_sp_thang.setSelectedIndex(-1);
        cbb_top_sp.setSelectedIndex(-1);

        cbb_thong_ke_sp1.setSelectedIndex(-1);
        cbb_thong_ke_sp_nam1.setSelectedIndex(-1);
        cbb_thong_ke_sp_thang1.setSelectedIndex(-1);
        cbb_top_sp1.setSelectedIndex(-1);
    }

    //reset lai cac text field tren trang quan ly nguyen vat lieu
    public void reset_resources() {
        textMaVL.setText("");
        texttenVL.setText("");
        cbxloaiVL.setSelectedIndex(-1);
        textgiaVL.setText("");
        textMaVL.setEditable(true);
    }

    //Hien thi noi dung cua trang quan ly nguyen vat lieu
    private void showResources() throws SQLException, ClassNotFoundException {
        resourcesList = Controller_Resource.findAll();
        tableModel5.setRowCount(0);

        for (Resources rc : resourcesList) {
            tableModel5.addRow(new Object[]{tableModel5.getRowCount() + 1, rc.getReID(),
                rc.getResourcesName(), rc.getRePrice(), rc.getQuantity(), rc.getUnit()});
        }
    }

    //reset lai cac text field tren trang quan ly ton nguyen 
    public void reset_in_resources() throws SQLException, ClassNotFoundException {
        showInvenResources();
        show_Combobox_id_resources();
        show_Combobox_stockid_resources();
        show_Combobox_name_resources();
        cbb_makhovl_ton.setSelectedIndex(-1);
        cbb_mavl_ton.setSelectedIndex(-1);
        cbb_tenvl_ton.setSelectedIndex(-1);
        text_soluongvl_ton.setText("");
        cbb_makhovl_ton.setEnabled(true);
        cbb_mavl_ton.setEnabled(true);
    }

    //Hien thi noi dung cua trang quan ly ton kho nguyen vat lieu
    private void showInvenResources() throws SQLException, ClassNotFoundException {
        in_resourcesList = Controller_Inventory_Resources.findAll();
        tableModel7.setRowCount(0);
        for (Inventory_Resources rc : in_resourcesList) {
            tableModel7.addRow(new Object[]{tableModel7.getRowCount() + 1, rc.getStockId(),
                rc.getReId(), rc.getName(), rc.getNum_inventory_re()});
        }
    }

    //Hien thi ma kho cho combobox ma kho 
    //tren trang quan ly ton kho nguyen vat lieu
    private void show_Combobox_stockid_resources() throws SQLException, ClassNotFoundException {
        data_combobox_stockid = Controller_Inventory_Resources.allStockId();
        cbb_makhovl_ton.removeAllItems();
        for (String id : data_combobox_stockid) {
            cbb_makhovl_ton.addItem(id);
        }
    }

    //Hien thi ma nguyen vat lieu cho combobox ma nguyen vat lieu 
    //tren trang quan ly ton kho nguyen vat lieu
    private void show_Combobox_id_resources() throws SQLException, ClassNotFoundException {
        data_combobox_id_resources = Controller_Inventory_Resources.id_resources();
        cbb_mavl_ton.removeAllItems();
        for (String id : data_combobox_id_resources) {
            cbb_mavl_ton.addItem(id);
        }
    }
    
    private void show_Combobox_name_resources() throws SQLException, ClassNotFoundException {
        List<String> data_combobox_name_resources = Controller_Inventory_Resources.name_resources();
        cbb_tenvl_ton.removeAllItems();
        for (String name : data_combobox_name_resources) {
            cbb_tenvl_ton.addItem(name);
        }
    }

    private void show_Combobox_farmid_emp() throws SQLException, ClassNotFoundException {
        data_combobox_farmid = Controller_Product.farm_id();
        boxFarmID.removeAllItems();
        for (String id : data_combobox_farmid) {
                boxFarmID.addItem(id);
        }
    }
    
    //hien thi ten nguyen vat lieu o bang TON NGUYEN VAT LIEU khi chon tu cbb
    private void show_cbb_name_resources() throws SQLException, ClassNotFoundException {
        String id = (String) cbb_mavl_ton.getSelectedItem();
        if (id != null) {
            String name = Controller_Inventory_Resources.get_name_resources(id);
            cbb_tenvl_ton.setSelectedItem(name);
        }
    }
    
    private void show_cbb_id_resources() throws SQLException, ClassNotFoundException {
        String name = (String) cbb_tenvl_ton.getSelectedItem();
        if (name != null) {
            String id = Controller_Inventory_Resources.get_id_resources(name);
            cbb_mavl_ton.setSelectedItem(id);
        }
    }

    //Show thong tin cua nong san len trang quan ly
    private void showProduct() throws SQLException, ClassNotFoundException {
        cbxloaiMaKM.setSelectedIndex(-1);
        productList = Controller_Product.findAll();
        tableModel6.setRowCount(0);
        for (Product pro : productList) {
            tableModel6.addRow(new Object[]{tableModel6.getRowCount() + 1, pro.getProID(),
                pro.getProName(), pro.getProPrice(), pro.getFarmID(), pro.getProType(), pro.getQuantity(), pro.getImage()});
        }
    }

    //show combobox ma trang trai tren bang quan ly nong san
    private void show_Combobox_farmid_product() throws SQLException, ClassNotFoundException {
        data_combobox_farmid = Controller_Product.farm_id();
        cbb_ma_nong_trai_sp.removeAllItems();
        for (String id : data_combobox_farmid) {
            if (data_combobox_farmid.contains(id)) {
                cbb_ma_nong_trai_sp.addItem(id);
            }
        }
    }

    //reset trang quan ly nong san
    public void reset_product() throws SQLException, ClassNotFoundException {
        //  showInvenProduct();
        show_Combobox_farmid_product();
        showProduct();
        texttenSP.setText("");
        textGia_nong_san.setText("");
        text_link_anh.setText("");
        textmaSP.setText("");
        cbb_ma_nong_trai_sp.setSelectedIndex(-1);
        cbb_loai_sp.setSelectedIndex(-1);
        textmaSP.setEditable(true);
        jLabelHinhNS.setIcon(null);
    }

    //Hien thi ma kho cho combobox ma kho 
    //tren trang quan ly TON NONG SAN
    private void show_Combobox_stockid_product() throws SQLException, ClassNotFoundException {
        data_combobox_stockid_pro = Controller_Inventory_Product.allStockId();
        cbb_ton_makho_ns.removeAllItems();
        for (String id : data_combobox_stockid_pro) {
            cbb_ton_makho_ns.addItem(id);
        }
    }

    //Hien thi ma nong san cho combobox ma nong san
    //tren trang quan ly TON NONG SAN
    private void show_Combobox_id_product() throws SQLException, ClassNotFoundException {
        data_combobox_id_product = Controller_Inventory_Product.id_product();
        cbb_ton_mans.removeAllItems();
        for (String id : data_combobox_id_product) {
            cbb_ton_mans.addItem(id);
        }
    }
        
      private void show_Combobox_name_product() throws SQLException, ClassNotFoundException {
        List<String> data_combobox_name_product = Controller_Inventory_Product.name_product();
        cbb_ton_tenns.removeAllItems();
        for (String id : data_combobox_name_product) {
            cbb_ton_tenns.addItem(id);
        }
    }

    //reset lai cac text field tren trang quan ly TON NONG SAN
    public void reset_in_product() throws SQLException, ClassNotFoundException {
        show_Combobox_id_product();
        show_Combobox_stockid_product();
        show_Combobox_name_product();
        showInvenProduct();
        cbb_ton_makho_ns.setSelectedIndex(-1);
        cbb_ton_mans.setSelectedIndex(-1);
        cbb_ton_tenns.setSelectedIndex(-1);
        txt_ton_soluong_ns.setText("");
        cbb_ton_makho_ns.setEnabled(true);
        cbb_ton_mans.setEnabled(true);
    }

    //hien thi ma nong san o bang TON nong san khi chon tu cbb ten nong san
    private void show_cbb_id_product() throws SQLException, ClassNotFoundException {
        String name = (String) cbb_ton_tenns.getSelectedItem();
        if (name != null) {
            String id = Controller_Inventory_Product.get_id_product(name);
            cbb_ton_mans.setSelectedItem(id);
        }
    }
    
    
    //hien thi ten nong san o bang TON nong san khi chon tu cbb ma nong san
    private void show_cbb_name_product() throws SQLException, ClassNotFoundException {
        String id = (String) cbb_ton_mans.getSelectedItem();
        if (id != null) {
            String name = Controller_Inventory_Product.get_name_product(id);
            cbb_ton_tenns.setSelectedItem(name);
        }
    }

    private void showInvenProduct() throws SQLException, ClassNotFoundException {
        in_productList = Controller_Inventory_Product.findAll();
        tableModel11.setRowCount(0);
        for (Inventory_Product rc : in_productList) {
            tableModel11.addRow(new Object[]{tableModel11.getRowCount() + 1, rc.getStockId(),
                rc.getProId(), rc.getName(), rc.getNum_inventory_pro()});
        }
    }

    public static void setCellsAlignment(JTable table, int alignment) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    //Reset lai cac text field trang cua trang nong trai
    //reset lai cac text field tren trang quan ly nguyen vat lieu
    //reset lai cac text field tren trang quan ly kho
    public void reset_stock() {
        textMaKho.setText("");
        boxTrangThai.setSelectedIndex(-1);
        boxLoai.setSelectedIndex(-1);
        textMaKho.setEditable(true);
    }

    //reset lai cac text field tren trang quan ly nhan vien
    public void reset_Employee() throws SQLException, ClassNotFoundException{
        show_Combobox_farmid_emp();
        textmaNV.setText("");
        boxFarmID.setSelectedIndex(-1);
        texttenNV.setText("");
        textdiaChiNV.setText("");
        textsdtNV.setText("");
        textemailNV.setText("");
        txtStartDate.setDate(null);
        boxUserRole.setSelectedIndex(-1);
        textuserName.setText("");
        textpassWord.setText("");
        textMaKho.setEditable(true);
    }

    //reset lai cac text field tren trang quan ly van chuyen
    public void reset_transport() {
        textTransID.setText("");
        textOrd_Ex_Num.setText("");
        boxStatusTrans.setSelectedIndex(-1);
        textTransID.setEditable(true);
    }

    // Hien thi danh sach kho
    private void showStock() throws SQLException, ClassNotFoundException {
        stockList = Controller_Stock.findAll();
        tableModel8.setRowCount(0);
        String stt;
        String type;

        for (Stock st : stockList) {
            if (st.getStatusStock() == 1) {
                stt = "Chưa đầy";
            } else {
                stt = "Đầy";
            }
            if (st.getType() == 1) {
                type = "Sản phẩm";
            } else {
                type = "Vật liệu";
            }

            tableModel8.addRow(new Object[]{tableModel8.getRowCount() + 1, st.getStockID(),
                stt, type});
        }
    }

    //Hien thi danh sach nhan vien
    private void showEmployee() throws SQLException, ClassNotFoundException {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        employeeList = Controller_Employee.findAll();
        tableModel9.setRowCount(0);
        reset_Employee();
        for (Employee st : employeeList) {
            tableModel9.addRow(new Object[]{tableModel9.getRowCount() + 1, st.getEmpID(),
                st.getFarmID(), st.getEmpName(), st.getEmpAdd(), st.getEmpPhone(),
                st.getEmpEmail(), f.format(st.getStartDate()), st.getUserID()});
        }
    }

    //hien thi danh sach van chuyen
    private void showTransport() throws SQLException, ClassNotFoundException {
        transportList = Controller_Transport.findAll();
        tableModel10.setRowCount(0);
        String stt = "";

        for (Transport tr : transportList) {
            if (tr.getStatusTrans() == 1) {
                stt = "Thành công";
            } else if (tr.getStatusTrans() == 0){
                stt = "Thất bại";
            }else  {
                stt = "Ðang giao hàng";
            }
            tableModel10.addRow(new Object[]{tableModel10.getRowCount() + 1, tr.getTransID(),
                tr.getOrd_Ex_Num(), stt});
        }
    }

    public void reset_Farm() {
        textMaNT.setText("");
        textMaNT.setEditable(true);
        texttenNT.setText("");
        textdiaChiNT.setText("");

    }

    //Reset lai cac text field trang cua trang nha cung cap
    public void reset_Sup() {
        textmaNCC.setText("");
        textmaNCC.setEditable(true);
        texttenNCC.setText("");
        textsdtNCC.setText("");
        textdiaChiNCC.setText("");
        textemailNCC.setText("");
    }

    //Reset lai cac text field trang cua trang khach hang
    public void reset_Cus() {
        textmaKH.setText("");
        textmaKH.setEditable(true);
        texttenKH.setText("");
        cbxgenderKH.setSelectedIndex(-1);
        dateBirthKH.setDateFormatString("");
        textdiaChiKH.setText("");
        textsdtKH.setText("");
        textemailKH.setText("");
        cbxloaiKH.setSelectedIndex(-1);
        texttienTL.setText("");
        textmaUser.setText("");
    }

    //Reset lai cac text field cua trang ma khuyen mai
    public void reset_Dis() {
        textmaKM.setText("");
        textmaCode.setText("");
        textgiaTriKM.setText("");
        cbxloaiMaKM.setSelectedIndex(-1);
        date_start_dis.setDate(null);
        date_end_dis.setDate(null);
        //date_start_dis.setDateFormatString("dd/MM/yyyy");
        //date_end_dis.setDateFormatString("dd/MM/yyyy");
    }
    // Ham hien thi danh sach nong trai

    private void showFarm() throws SQLException, ClassNotFoundException {

        listFarm = Farm_Controller.findAllFarm();
        tableModel.setRowCount(0);
        for (Farm fa : listFarm) {
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, fa.getFarmID(),
                fa.getFarmName(), fa.getFarmAdd()});
        }

    }

    //Hien thi danh sach nha cung cap
    private void showSup() throws SQLException, ClassNotFoundException {
        listSup = Supplier_Controller.findAllSup();
        tableModel1.setRowCount(0);
        for (Supplier sup : listSup) {
            tableModel1.addRow(new Object[]{tableModel1.getRowCount() + 1, sup.getSupID(),
                sup.getSupName(), sup.getSupPhone(), sup.getSupAdd(), sup.getSupEmail()});
        }
    }

    //Hien thi danh sach khach hang
    private void showCus() throws SQLException, ClassNotFoundException {
        listCus = Customer_Controller.findAllCus();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        tableModel2.setRowCount(0);
        for (Customer cus : listCus) {
            if(cus.getDateOfBirth() != null){
            tableModel2.addRow(new Object[]{tableModel2.getRowCount() + 1, cus.getCusID(),
                cus.getCusName(), cus.getGender(),  f.format(cus.getDateOfBirth()), cus.getCusAdd(),
                cus.getCusPhone(), cus.getCusEmail(), cus.getCusType(), cus.getAccrued_Money(),
                cus.getUserID()});}
            else{
                tableModel2.addRow(new Object[]{tableModel2.getRowCount() + 1, cus.getCusID(),
                cus.getCusName(), cus.getGender(),  cus.getDateOfBirth(), cus.getCusAdd(),
                cus.getCusPhone(), cus.getCusEmail(), cus.getCusType(), cus.getAccrued_Money(),
                cus.getUserID()});
            }
        }
    }

    //Hien thi danh sach ma khuyen mai
    private void showDis() throws SQLException, ClassNotFoundException {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        date_start_dis.setDateFormatString("dd/MM/yyyy");
        date_end_dis.setDateFormatString("dd/MM/yyyy");
        listDis = Discount_Controller.findAllDis();
        tableModel3.setRowCount(0);
        for (Discount dis : listDis) {
            tableModel3.addRow(new Object[]{tableModel3.getRowCount() + 1, dis.getDisID(),
                dis.getDisCode(), dis.getValue(), dis.getCusType(), f.format(dis.getStartDate()), f.format(dis.getEndDate()), dis.getStatus()
            });
        }
    }

    /*
    * Hien thi danh sach don hang luu tru trong he thong
     */
    private void showOrderExport() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String trangThaiVanChuyen = "";

        try {
            listOrderExport = C_AdminHome.listOrder_Export();
            tableModelHD.setRowCount(0);

            for (Order_Export oe : listOrderExport) {
                if (oe.getTransID() != null) {
                    switch (oe.getStatusTrans()) {
                        case 0:
                            trangThaiVanChuyen = "Thất bại";
                            break;
                        case 1:
                            trangThaiVanChuyen = "Thành công";
                            break;
                        case 2:
                            trangThaiVanChuyen = "Đang giao hàng";
                            break;
                    }
                }else{
                    trangThaiVanChuyen = "";
                }

                tableModelHD.addRow(new Object[]{tableModelHD.getRowCount() + 1, oe.getOrd_Ex_Num(), oe.getCusID(),
                    f.format(oe.getDateOrdered()), oe.getTransID(), oe.getPreTotal(), oe.getDisID(), oe.getOrderTotal(), trangThaiVanChuyen});
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtManaPro = new javax.swing.JButton();
        jbtManaFarm = new javax.swing.JButton();
        jbtManaOrder = new javax.swing.JButton();
        jbtManaRe = new javax.swing.JButton();
        jbtManaSup = new javax.swing.JButton();
        jbtManaCus = new javax.swing.JButton();
        jbtManaDis = new javax.swing.JButton();
        jbtManaStock = new javax.swing.JButton();
        jbtManaEmp = new javax.swing.JButton();
        jbtManaTrans = new javax.swing.JButton();
        jbtManaStatis = new javax.swing.JButton();
        btntonKhovl = new javax.swing.JButton();
        btntonSanPham = new javax.swing.JButton();
        jpnCardLayout = new javax.swing.JPanel();
        jpnCard1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbb_dx_card1 = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        textmaSP = new javax.swing.JTextField();
        texttenSP = new javax.swing.JTextField();
        cbb_ma_nong_trai_sp = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btn_them_anh_ns = new javax.swing.JButton();
        textGia_nong_san = new javax.swing.JTextField();
        cbb_loai_sp = new javax.swing.JComboBox<>();
        text_link_anh = new javax.swing.JTextField();
        jLabelHinhNS = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        table_ds_nong_san = new javax.swing.JTable();
        btnthemSP = new javax.swing.JButton();
        btn_reset_product = new javax.swing.JButton();
        btnsuaSP = new javax.swing.JButton();
        btntimSP = new javax.swing.JButton();
        btnxoaSP = new javax.swing.JButton();
        jpnCard2 = new javax.swing.JPanel();
        btnthemNongTrai = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNongTrai = new javax.swing.JTable();
        btnxoaNongTrai = new javax.swing.JButton();
        btnsuaNongTrai = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        textMaNT = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        texttenNT = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        textdiaChiNT = new javax.swing.JTextField();
        btntimNongTrai = new javax.swing.JButton();
        btnresetNT = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbb_dx_card2 = new javax.swing.JComboBox<>();
        jpnCard3 = new javax.swing.JPanel();
        txtFTimHoaDon = new javax.swing.JTextField();
        btnTaoHoaDonMoi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableListOrderExport = new javax.swing.JTable();
        btnTimHoaDon = new javax.swing.JButton();
        btnResetTblListHoaDon = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbb_dx_card3 = new javax.swing.JComboBox<>();
        jComboBoxTimHD = new javax.swing.JComboBox<>();
        jLabel85 = new javax.swing.JLabel();
        jpnCard4 = new javax.swing.JPanel();
        btnthemVL = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_ds_nguyenVL = new javax.swing.JTable();
        btnxoaVL = new javax.swing.JButton();
        btnsuaVL = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        textMaVL = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        texttenVL = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        textgiaVL = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        cbxloaiVL = new javax.swing.JComboBox<>();
        btntimVL = new javax.swing.JButton();
        btn_reset_nguyen_vatlieu = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbb_dx_card4 = new javax.swing.JComboBox<>();
        jpnCard5 = new javax.swing.JPanel();
        btnthemNCC = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableSup = new javax.swing.JTable();
        btnxoaNCC = new javax.swing.JButton();
        btnsuaNCC = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        textmaNCC = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        texttenNCC = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        textsdtNCC = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        textdiaChiNCC = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        textemailNCC = new javax.swing.JTextField();
        btntimNCC = new javax.swing.JButton();
        btnresetNCC = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbb_dx_card5 = new javax.swing.JComboBox<>();
        jpnCard6 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbb_dx_card6 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        textmaKH = new javax.swing.JTextField();
        texttenKH = new javax.swing.JTextField();
        cbxgenderKH = new javax.swing.JComboBox<>();
        textsdtKH = new javax.swing.JTextField();
        cbxloaiKH = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        textdiaChiKH = new javax.swing.JTextField();
        dateBirthKH = new com.toedter.calendar.JDateChooser();
        textemailKH = new javax.swing.JTextField();
        textmaUser = new javax.swing.JTextField();
        texttienTL = new javax.swing.JTextField();
        btntimKH = new javax.swing.JButton();
        btnresetKH = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        jpnCard7 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbb_dx_card7 = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        textgiaTriKM = new javax.swing.JTextField();
        textmaCode = new javax.swing.JTextField();
        textmaKM = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        cbxloaiMaKM = new javax.swing.JComboBox<>();
        date_start_dis = new com.toedter.calendar.JDateChooser();
        date_end_dis = new com.toedter.calendar.JDateChooser();
        btntimMaKM = new javax.swing.JButton();
        btnthemMaKM = new javax.swing.JButton();
        btnxoaMaKM = new javax.swing.JButton();
        btnsuaMaKM = new javax.swing.JButton();
        btnresetKM = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableKM = new javax.swing.JTable();
        btnTaoMa = new javax.swing.JButton();
        jpnCard8 = new javax.swing.JPanel();
        btnthemKho = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        btnxoaKho = new javax.swing.JButton();
        btnsuaKho = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        textMaKho = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        boxTrangThai = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        boxLoai = new javax.swing.JComboBox<>();
        btntimKho = new javax.swing.JButton();
        btn_resetStock = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbb_dx_card8 = new javax.swing.JComboBox<>();
        jpnCard9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbb_dx_card9 = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        textmaNV = new javax.swing.JTextField();
        boxFarmID = new javax.swing.JComboBox<>();
        texttenNV = new javax.swing.JTextField();
        textdiaChiNV = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtStartDate = new com.toedter.calendar.JDateChooser();
        textemailNV = new javax.swing.JTextField();
        textsdtNV = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        textpassWord = new javax.swing.JTextField();
        textuserName = new javax.swing.JTextField();
        boxUserRole = new javax.swing.JComboBox<>();
        btntimNhanVien = new javax.swing.JButton();
        btnthemNhanVien = new javax.swing.JButton();
        btnxoaNhanVien = new javax.swing.JButton();
        btnsuaNhanVien = new javax.swing.JButton();
        btnResetNV = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jpnCard10 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        textTransID = new javax.swing.JTextField();
        textOrd_Ex_Num = new javax.swing.JTextField();
        boxStatusTrans = new javax.swing.JComboBox<>();
        btnUpdateTrans = new javax.swing.JButton();
        btnSearchTrans = new javax.swing.JButton();
        btnResetTransport = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbb_dx_card10 = new javax.swing.JComboBox<>();
        jpnCard11 = new javax.swing.JPanel();
        thongkedoanhthu = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        top_product = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        cbb_thong_ke_thang = new javax.swing.JComboBox<>();
        cbb_thong_ke_nam = new javax.swing.JComboBox<>();
        cbb_thong_ke = new javax.swing.JComboBox<>();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        btnXuatThongKeDoanhThu = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cbb_thong_ke_sp = new javax.swing.JComboBox<>();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        cbb_thong_ke_sp_nam = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        cbb_thong_ke_sp_thang = new javax.swing.JComboBox<>();
        jLabel80 = new javax.swing.JLabel();
        cbb_top_sp = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        cbb_thong_ke_sp1 = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        cbb_thong_ke_sp_nam1 = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        cbb_thong_ke_sp_thang1 = new javax.swing.JComboBox<>();
        jLabel84 = new javax.swing.JLabel();
        cbb_top_sp1 = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        top_cus = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jpnCard12 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbb_makhovl_ton = new javax.swing.JComboBox<>();
        cbb_mavl_ton = new javax.swing.JComboBox<>();
        text_soluongvl_ton = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        table_ds_inven_re = new javax.swing.JTable();
        btn_Timvl_ton = new javax.swing.JButton();
        btn_Themvl_ton = new javax.swing.JButton();
        btn_Xoavl_ton = new javax.swing.JButton();
        btn_Suavl_ton = new javax.swing.JButton();
        btn_datlai_nvl_ton = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        cbb_dx_card12 = new javax.swing.JComboBox<>();
        cbb_tenvl_ton = new javax.swing.JComboBox<>();
        jpnCard13 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        cbb_ton_makho_ns = new javax.swing.JComboBox<>();
        cbb_ton_mans = new javax.swing.JComboBox<>();
        txt_ton_soluong_ns = new javax.swing.JTextField();
        btn_Tim_nongsan_ton = new javax.swing.JButton();
        btn_them_nongsan_ton = new javax.swing.JButton();
        btn_xoa_nongsan_ton = new javax.swing.JButton();
        btn_sua_nongsan_ton = new javax.swing.JButton();
        btn_datlai_nongsan_ton = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        table_ds_ton_ns = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        cbb_dx_card13 = new javax.swing.JComboBox<>();
        cbb_ton_tenns = new javax.swing.JComboBox<>();

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton9.setText("jButton9");

        jButton11.setText("jButton11");

        jButton5.setText("jButton5");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(jTable2);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(jTable5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(778, 672));
        setSize(new java.awt.Dimension(1650, 1080));

        jPanel1.setBackground(new java.awt.Color(0, 179, 179));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FRESHFOOD");

        jbtManaPro.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaPro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/vegetable.png"))); // NOI18N
        jbtManaPro.setText("QUẢN LÝ NÔNG SẢN");
        jbtManaPro.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbtManaPro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaProActionPerformed(evt);
            }
        });

        jbtManaFarm.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaFarm.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaFarm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/farming.png"))); // NOI18N
        jbtManaFarm.setText("QUẢN LÝ TRANG TRẠI");
        jbtManaFarm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaFarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaFarmActionPerformed(evt);
            }
        });

        jbtManaOrder.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaOrder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/order.png"))); // NOI18N
        jbtManaOrder.setText("QUẢN LÝ BÁN HÀNG");
        jbtManaOrder.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaOrderActionPerformed(evt);
            }
        });

        jbtManaRe.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaRe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaRe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/fertilizer.png"))); // NOI18N
        jbtManaRe.setText("QUẢN LÝ NGUYÊN VẬT LIỆU");
        jbtManaRe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaReActionPerformed(evt);
            }
        });

        jbtManaSup.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaSup.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaSup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/supplier.png"))); // NOI18N
        jbtManaSup.setText("QUẢN LÝ NHÀ CUNG CẤP");
        jbtManaSup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaSup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaSupActionPerformed(evt);
            }
        });

        jbtManaCus.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaCus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaCus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/customer.png"))); // NOI18N
        jbtManaCus.setText("QUẢN LÝ KHÁCH HÀNG");
        jbtManaCus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaCusActionPerformed(evt);
            }
        });

        jbtManaDis.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaDis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaDis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/gift-voucher.png"))); // NOI18N
        jbtManaDis.setText("QUẢN LÝ MÃ KHUYẾN MÃI");
        jbtManaDis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaDis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaDisActionPerformed(evt);
            }
        });

        jbtManaStock.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaStock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/warehouse.png"))); // NOI18N
        jbtManaStock.setText("QUẢN LÝ KHO");
        jbtManaStock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaStockActionPerformed(evt);
            }
        });

        jbtManaEmp.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaEmp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/employee.png"))); // NOI18N
        jbtManaEmp.setText("QUẢN LÝ NHÂN VIÊN");
        jbtManaEmp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaEmpActionPerformed(evt);
            }
        });

        jbtManaTrans.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaTrans.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaTrans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/shipment.png"))); // NOI18N
        jbtManaTrans.setText("QUẢN LÝ VẬN CHUYỂN");
        jbtManaTrans.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaTransActionPerformed(evt);
            }
        });

        jbtManaStatis.setBackground(new java.awt.Color(244, 211, 94));
        jbtManaStatis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtManaStatis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/analysis.png"))); // NOI18N
        jbtManaStatis.setText("THỐNG KÊ VÀ BÁO CÁO");
        jbtManaStatis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaStatis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaStatisActionPerformed(evt);
            }
        });

        btntonKhovl.setBackground(new java.awt.Color(248, 211, 94));
        btntonKhovl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btntonKhovl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icontonkho1.png"))); // NOI18N
        btntonKhovl.setText("TỒN KHO NGUYÊN VẬT LIỆU");
        btntonKhovl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btntonKhovl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntonKhovlActionPerformed(evt);
            }
        });

        btntonSanPham.setBackground(new java.awt.Color(248, 211, 94));
        btntonSanPham.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btntonSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icontonkho1.png"))); // NOI18N
        btntonSanPham.setText("TỒN KHO NÔNG SẢN");
        btntonSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btntonSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntonSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jbtManaPro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaFarm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaRe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaSup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaDis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btntonKhovl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btntonSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaTrans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaStatis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jbtManaPro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaFarm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaRe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaSup, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaCus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaDis, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntonKhovl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntonSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaStatis, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanel1);

        jpnCardLayout.setBackground(new java.awt.Color(255, 255, 255));
        jpnCardLayout.setLayout(new java.awt.CardLayout());

        jpnCard1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(0, 179, 179));
        jPanel9.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DANH SÁCH SẢN PHẨM");

        cbb_dx_card1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_dx_card1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 930, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(cbb_dx_card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_dx_card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(19, 19, 19))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(1282, 280));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Mã sản phẩm");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Tên sản phẩm");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel73.setText("Mã nông trại");

        textmaSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textmaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textmaSPActionPerformed(evt);
            }
        });

        texttenSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        texttenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttenSPActionPerformed(evt);
            }
        });

        cbb_ma_nong_trai_sp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_ma_nong_trai_sp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Giá");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Loại");

        btn_them_anh_ns.setBackground(new java.awt.Color(102, 255, 153));
        btn_them_anh_ns.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_them_anh_ns.setText("Chọn ảnh");
        btn_them_anh_ns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them_anh_nsActionPerformed(evt);
            }
        });

        textGia_nong_san.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textGia_nong_san.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textGia_nong_sanActionPerformed(evt);
            }
        });

        cbb_loai_sp.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_loai_sp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trái cây", "Củ quả", "Rau" }));
        cbb_loai_sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_loai_spActionPerformed(evt);
            }
        });

        text_link_anh.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabelHinhNS.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 255, 204), null));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel73)
                            .addComponent(jLabel16))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textmaSP)
                            .addComponent(texttenSP)
                            .addComponent(cbb_ma_nong_trai_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addGap(94, 94, 94)
                                        .addComponent(textGia_nong_san, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel18)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(btn_them_anh_ns)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbb_loai_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(text_link_anh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(516, 516, 516)
                        .addComponent(jLabelHinhNS, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textmaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(textGia_nong_san, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(30, 30, 30)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texttenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(cbb_loai_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(30, 30, 30)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb_ma_nong_trai_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_them_anh_ns)
                    .addComponent(text_link_anh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addGap(18, 18, 18)
                .addComponent(jLabelHinhNS, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        table_ds_nong_san.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table_ds_nong_san.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá", "Mã nông trại", "Loại", " Số lượng", " Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_ds_nong_san.setRowHeight(25);
        table_ds_nong_san.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ds_nong_sanMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(table_ds_nong_san);

        btnthemSP.setBackground(new java.awt.Color(244, 211, 94));
        btnthemSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnthemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemSP.setText("Thêm");
        btnthemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemSPActionPerformed(evt);
            }
        });

        btn_reset_product.setBackground(new java.awt.Color(238, 150, 75));
        btn_reset_product.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_reset_product.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset_product.setText("Reset");
        btn_reset_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reset_productActionPerformed(evt);
            }
        });

        btnsuaSP.setBackground(new java.awt.Color(244, 211, 94));
        btnsuaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnsuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaSP.setText("Sửa");
        btnsuaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaSPActionPerformed(evt);
            }
        });

        btntimSP.setBackground(new java.awt.Color(248, 211, 94));
        btntimSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btntimSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimSP.setText("Tìm");
        btntimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimSPActionPerformed(evt);
            }
        });

        btnxoaSP.setBackground(new java.awt.Color(244, 211, 94));
        btnxoaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnxoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaSP.setText("Xóa");
        btnxoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCard1Layout = new javax.swing.GroupLayout(jpnCard1);
        jpnCard1.setLayout(jpnCard1Layout);
        jpnCard1Layout.setHorizontalGroup(
            jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard1Layout.createSequentialGroup()
                .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard1Layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(btnthemSP)
                        .addGap(57, 57, 57)
                        .addComponent(btntimSP)
                        .addGap(54, 54, 54)
                        .addComponent(btnxoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnsuaSP)
                        .addGap(46, 46, 46)
                        .addComponent(btn_reset_product))
                    .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 1257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE))))
                .addContainerGap(608, Short.MAX_VALUE))
        );
        jpnCard1Layout.setVerticalGroup(
            jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsuaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reset_product, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard1, "jpnListPro");

        jpnCard2.setBackground(new java.awt.Color(255, 255, 255));
        jpnCard2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnthemNongTrai.setBackground(new java.awt.Color(244, 211, 94));
        btnthemNongTrai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnthemNongTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemNongTrai.setText("Thêm");
        btnthemNongTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemNongTraiActionPerformed(evt);
            }
        });
        jpnCard2.add(btnthemNongTrai, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 285, -1, 35));

        jScrollPane2.setFocusCycleRoot(true);

        tableNongTrai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableNongTrai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nông trại", "Tên nông trại", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableNongTrai.setFocusable(false);
        tableNongTrai.setPreferredSize(new java.awt.Dimension(200, 250));
        tableNongTrai.setRowHeight(25);
        tableNongTrai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNongTraiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableNongTraiMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableNongTrai);

        jpnCard2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 1266, 260));

        btnxoaNongTrai.setBackground(new java.awt.Color(244, 211, 94));
        btnxoaNongTrai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnxoaNongTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaNongTrai.setText("Xóa");
        btnxoaNongTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaNongTraiActionPerformed(evt);
            }
        });
        jpnCard2.add(btnxoaNongTrai, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 285, -1, 35));

        btnsuaNongTrai.setBackground(new java.awt.Color(244, 211, 94));
        btnsuaNongTrai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnsuaNongTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaNongTrai.setText("Sửa");
        btnsuaNongTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaNongTraiActionPerformed(evt);
            }
        });
        jpnCard2.add(btnsuaNongTrai, new org.netbeans.lib.awtextra.AbsoluteConstraints(747, 285, -1, 35));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setText("Mã nông trại");
        jpnCard2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 109, 110, -1));

        textMaNT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jpnCard2.add(textMaNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 105, 250, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Tên nông trại");
        jpnCard2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 169, -1, -1));

        texttenNT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        texttenNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttenNTActionPerformed(evt);
            }
        });
        jpnCard2.add(texttenNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 165, 250, 30));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Địa chỉ");
        jpnCard2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(459, 229, 74, -1));

        textdiaChiNT.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jpnCard2.add(textdiaChiNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(602, 225, 250, 30));

        btntimNongTrai.setBackground(new java.awt.Color(248, 211, 94));
        btntimNongTrai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btntimNongTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimNongTrai.setText("Tìm");
        btntimNongTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimNongTraiActionPerformed(evt);
            }
        });
        jpnCard2.add(btntimNongTrai, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 285, -1, 35));

        btnresetNT.setBackground(new java.awt.Color(238, 150, 75));
        btnresetNT.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnresetNT.setForeground(new java.awt.Color(255, 255, 255));
        btnresetNT.setText("Reset");
        btnresetNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetNTActionPerformed(evt);
            }
        });
        jpnCard2.add(btnresetNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 285, -1, 35));

        jPanel4.setBackground(new java.awt.Color(0, 179, 179));
        jPanel4.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH NÔNG TRẠI");

        cbb_dx_card2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_dx_card2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_dx_card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbb_dx_card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jpnCard2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jpnCardLayout.add(jpnCard2, "jpnListFarm");

        jpnCard3.setBackground(new java.awt.Color(255, 255, 255));

        txtFTimHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtFTimHoaDon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFTimHoaDonFocusGained(evt);
            }
        });

        btnTaoHoaDonMoi.setBackground(new java.awt.Color(244, 211, 94));
        btnTaoHoaDonMoi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnTaoHoaDonMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnTaoHoaDonMoi.setText("Tạo đơn mới");
        btnTaoHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonMoiActionPerformed(evt);
            }
        });

        jTableListOrderExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã đơn hàng", "Mã khách hàng", "Ngày tạo", "Mã vận chuyển", "Tổng tiền", "Mã giảm giá", "Thành tiền", "Trạng thái vận chuyển"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableListOrderExport.setFocusable(false);
        jTableListOrderExport.setRowHeight(25);
        jTableListOrderExport.setShowHorizontalLines(true);
        jTableListOrderExport.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableListOrderExport);
        if (jTableListOrderExport.getColumnModel().getColumnCount() > 0) {
            jTableListOrderExport.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableListOrderExport.getColumnModel().getColumn(2).setPreferredWidth(40);
            jTableListOrderExport.getColumnModel().getColumn(4).setPreferredWidth(40);
            jTableListOrderExport.getColumnModel().getColumn(6).setPreferredWidth(40);
            jTableListOrderExport.getColumnModel().getColumn(8).setPreferredWidth(100);
        }

        btnTimHoaDon.setBackground(new java.awt.Color(244, 211, 94));
        btnTimHoaDon.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnTimHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btnTimHoaDon.setText("Tìm kiếm");
        btnTimHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHoaDonActionPerformed(evt);
            }
        });

        btnResetTblListHoaDon.setBackground(new java.awt.Color(238, 150, 75));
        btnResetTblListHoaDon.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnResetTblListHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnResetTblListHoaDon.setText("Reset");
        btnResetTblListHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTblListHoaDonActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 179, 179));
        jPanel5.setPreferredSize(new java.awt.Dimension(1285, 75));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DANH SÁCH ĐƠN HÀNG");

        cbb_dx_card3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_dx_card3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addComponent(cbb_dx_card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbb_dx_card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jComboBoxTimHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxTimHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã đơn hàng", "Mã khách hàng", "Trạng thái vận chuyển" }));
        jComboBoxTimHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTimHDActionPerformed(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel85.setText("Tìm kiếm theo:");

        javax.swing.GroupLayout jpnCard3Layout = new javax.swing.GroupLayout(jpnCard3);
        jpnCard3.setLayout(jpnCard3Layout);
        jpnCard3Layout.setHorizontalGroup(
            jpnCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 595, Short.MAX_VALUE))
            .addGroup(jpnCard3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCard3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFTimHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimHoaDon)
                        .addGap(12, 12, 12)
                        .addComponent(btnTaoHoaDonMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetTblListHoaDon)))
                .addContainerGap(610, Short.MAX_VALUE))
        );
        jpnCard3Layout.setVerticalGroup(
            jpnCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(jpnCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDonMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtFTimHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnTimHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnResetTblListHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jComboBoxTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(414, 414, 414))
        );

        jpnCardLayout.add(jpnCard3, "jpnListOrder");

        jpnCard4.setBackground(new java.awt.Color(255, 255, 255));

        btnthemVL.setBackground(new java.awt.Color(244, 211, 94));
        btnthemVL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnthemVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemVL.setText("Thêm");
        btnthemVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemVLActionPerformed(evt);
            }
        });

        table_ds_nguyenVL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table_ds_nguyenVL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nguyên vật liệu", "Tên nguyên vật liệu", "Giá", "Số lượng", "Loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_ds_nguyenVL.setRowHeight(25);
        table_ds_nguyenVL.setShowGrid(false);
        table_ds_nguyenVL.setShowHorizontalLines(true);
        table_ds_nguyenVL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ds_nguyenVLMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(table_ds_nguyenVL);

        btnxoaVL.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaVL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnxoaVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaVL.setText("Xóa");
        btnxoaVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaVLActionPerformed(evt);
            }
        });

        btnsuaVL.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaVL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnsuaVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaVL.setText("Sửa");
        btnsuaVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaVLActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Mã nguyên vật liệu");

        textMaVL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textMaVL.setToolTipText("Không cần nhập ô này khi thêm mới!");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Tên nguyên vật liệu");

        texttenVL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        texttenVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttenVLActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Giá");

        textgiaVL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Loại");

        cbxloaiVL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxloaiVL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bao", "Goi", "Chai", "Cai" }));

        btntimVL.setBackground(new java.awt.Color(248, 211, 94));
        btntimVL.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btntimVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimVL.setText("Tìm");
        btntimVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimVLActionPerformed(evt);
            }
        });

        btn_reset_nguyen_vatlieu.setBackground(new java.awt.Color(238, 150, 75));
        btn_reset_nguyen_vatlieu.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_reset_nguyen_vatlieu.setForeground(new java.awt.Color(255, 255, 255));
        btn_reset_nguyen_vatlieu.setText("Reset");
        btn_reset_nguyen_vatlieu.setToolTipText("");
        btn_reset_nguyen_vatlieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reset_nguyen_vatlieuActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(0, 179, 179));
        jPanel10.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DANH SÁCH NGUYÊN VẬT LIỆU");

        cbb_dx_card4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_dx_card4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_dx_card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(cbb_dx_card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jpnCard4Layout = new javax.swing.GroupLayout(jpnCard4);
        jpnCard4.setLayout(jpnCard4Layout);
        jpnCard4Layout.setHorizontalGroup(
            jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 598, Short.MAX_VALUE))
            .addGroup(jpnCard4Layout.createSequentialGroup()
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard4Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel29))
                        .addGap(27, 27, 27)
                        .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textMaVL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(texttenVL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGap(32, 32, 32)
                        .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textgiaVL, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(cbxloaiVL, 0, 250, Short.MAX_VALUE)))
                    .addGroup(jpnCard4Layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(btntimVL)
                        .addGap(85, 85, 85)
                        .addComponent(btnthemVL)
                        .addGap(88, 88, 88)
                        .addComponent(btnxoaVL)
                        .addGap(67, 67, 67)
                        .addComponent(btnsuaVL)
                        .addGap(64, 64, 64)
                        .addComponent(btn_reset_nguyen_vatlieu))
                    .addGroup(jpnCard4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCard4Layout.setVerticalGroup(
            jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard4Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(textMaVL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(cbxloaiVL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(textgiaVL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(texttenVL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsuaVL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnxoaVL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnthemVL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(btntimVL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addComponent(btn_reset_nguyen_vatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(384, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard4, "jpnListRe");

        jpnCard5.setBackground(new java.awt.Color(255, 255, 255));

        btnthemNCC.setBackground(new java.awt.Color(244, 211, 94));
        btnthemNCC.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnthemNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemNCC.setText("Thêm");
        btnthemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemNCCActionPerformed(evt);
            }
        });

        tableSup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableSup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSup.setRowHeight(25);
        tableSup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSupMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableSupMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tableSup);

        btnxoaNCC.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaNCC.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnxoaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaNCC.setText("Xóa");
        btnxoaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaNCCActionPerformed(evt);
            }
        });

        btnsuaNCC.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaNCC.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnsuaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaNCC.setText("Sửa");
        btnsuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaNCCActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("Mã nhà cung cấp");

        textmaNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("Tên nhà cung cấp");

        texttenNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        texttenNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttenNCCActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Số điện thoại");

        textsdtNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Địa chỉ");

        textdiaChiNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Email");

        textemailNCC.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btntimNCC.setBackground(new java.awt.Color(248, 211, 94));
        btntimNCC.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btntimNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimNCC.setText("Tìm");
        btntimNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimNCCActionPerformed(evt);
            }
        });

        btnresetNCC.setBackground(new java.awt.Color(238, 150, 75));
        btnresetNCC.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnresetNCC.setForeground(new java.awt.Color(255, 255, 255));
        btnresetNCC.setText("Reset");
        btnresetNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetNCCActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(0, 179, 179));
        jPanel11.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("DANH SÁCH NHÀ CUNG CẤP");

        cbb_dx_card5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbb_dx_card5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(387, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(cbb_dx_card5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_dx_card5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jpnCard5Layout = new javax.swing.GroupLayout(jpnCard5);
        jpnCard5.setLayout(jpnCard5Layout);
        jpnCard5Layout.setHorizontalGroup(
            jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard5Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textsdtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(texttenNCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textmaNCC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addComponent(btntimNCC)
                        .addGap(59, 59, 59)
                        .addComponent(btnthemNCC)
                        .addGap(40, 40, 40)
                        .addComponent(btnxoaNCC)))
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnsuaNCC)
                        .addGap(55, 55, 55)
                        .addComponent(btnresetNCC)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textemailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textdiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jpnCard5Layout.createSequentialGroup()
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 598, Short.MAX_VALUE))
        );
        jpnCard5Layout.setVerticalGroup(
            jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard5Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnCard5Layout.createSequentialGroup()
                                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel36))
                                .addGap(34, 34, 34)
                                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel34)))
                            .addGroup(jpnCard5Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(texttenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textmaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(textsdtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addComponent(textdiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(textemailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsuaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnresetNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpnCardLayout.add(jpnCard5, "jpnListSup");

        jpnCard6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(0, 179, 179));
        jPanel12.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel7.setBackground(new java.awt.Color(0, 179, 179));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DANH SÁCH KHÁCH HÀNG");

        cbb_dx_card6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbb_dx_card6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(271, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 835, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_dx_card6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbb_dx_card6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1076, 900));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Mã khách hàng");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Tên khách hàng");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("Giới tính");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setText("Số điện thoại");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Loại");

        textmaKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        texttenKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cbxgenderKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxgenderKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        textsdtKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cbxloaiKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxloaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Silver", "Gold", "Diamond", " " }));
        cbxloaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxloaiKHActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setText("Địa chỉ");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("Ngày sinh");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("Email");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Mã người dùng");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Tiền tích lũy");

        textdiaChiKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        dateBirthKH.setDateFormatString("dd-MMM-yy");

        textemailKH.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        textmaUser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        texttienTL.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btntimKH.setBackground(new java.awt.Color(248, 211, 94));
        btntimKH.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btntimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimKH.setText("Tìm");
        btntimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimKHActionPerformed(evt);
            }
        });

        btnresetKH.setBackground(new java.awt.Color(238, 150, 75));
        btnresetKH.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnresetKH.setForeground(new java.awt.Color(255, 255, 255));
        btnresetKH.setText("Reset");
        btnresetKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetKHActionPerformed(evt);
            }
        });

        tableKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã khách hàng", "Tên khách hàng", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Email", "Loại", "Tiền tích lũy", "Mã người dùng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Long.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableKH.setRowHeight(25);
        tableKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableKHMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tableKH);
        if (tableKH.getColumnModel().getColumnCount() > 0) {
            tableKH.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableKH.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableKH.getColumnModel().getColumn(3).setPreferredWidth(30);
            tableKH.getColumnModel().getColumn(8).setPreferredWidth(40);
            tableKH.getColumnModel().getColumn(10).setPreferredWidth(30);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(14, 14, 14)))
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(btntimKH)
                        .addGap(59, 59, 59)
                        .addComponent(btnresetKH))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(texttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxgenderKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textsdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxloaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(127, 127, 127)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textemailKH)
                                    .addComponent(textmaUser)
                                    .addComponent(textdiaChiKH)
                                    .addComponent(texttienTL)
                                    .addComponent(dateBirthKH, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel41))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(textdiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateBirthKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(textemailKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(textmaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(texttienTL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(textmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(texttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(cbxgenderKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textsdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbxloaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnresetKH, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btntimKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnCard6Layout = new javax.swing.GroupLayout(jpnCard6);
        jpnCard6.setLayout(jpnCard6Layout);
        jpnCard6Layout.setHorizontalGroup(
            jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard6Layout.createSequentialGroup()
                .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 1291, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(589, Short.MAX_VALUE))
        );
        jpnCard6Layout.setVerticalGroup(
            jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard6Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard6, "jpnListCus");

        jpnCard7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(0, 179, 179));
        jPanel15.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("QUẢN LÝ MÃ KHUYẾN MÃI");

        cbb_dx_card7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbb_dx_card7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(195, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(cbb_dx_card7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbb_dx_card7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setPreferredSize(new java.awt.Dimension(1312, 900));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setText("Mã khuyến mãi");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("Mã code");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("Giá trị");

        textgiaTriKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        textmaCode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        textmaKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textmaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textmaKMActionPerformed(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("Loại");

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel49.setText("Ngày bắt đầu");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel50.setText("Ngày kết thúc");

        cbxloaiMaKM.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxloaiMaKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diamond", "Gold", "Silver" }));

        date_start_dis.setDateFormatString("dd-MMM-yy");
        date_start_dis.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                date_start_disAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        date_start_dis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                date_start_disMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                date_start_disMousePressed(evt);
            }
        });

        date_end_dis.setDateFormatString("dd-MMM-yy");

        btntimMaKM.setBackground(new java.awt.Color(248, 211, 94));
        btntimMaKM.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btntimMaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimMaKM.setText("Tìm");
        btntimMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimMaKMActionPerformed(evt);
            }
        });

        btnthemMaKM.setBackground(new java.awt.Color(244, 211, 94));
        btnthemMaKM.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnthemMaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemMaKM.setText("Thêm");
        btnthemMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemMaKMActionPerformed(evt);
            }
        });

        btnxoaMaKM.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaMaKM.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnxoaMaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaMaKM.setText("Xóa");
        btnxoaMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaMaKMActionPerformed(evt);
            }
        });

        btnsuaMaKM.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaMaKM.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnsuaMaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaMaKM.setText("Sửa");
        btnsuaMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaMaKMActionPerformed(evt);
            }
        });

        btnresetKM.setBackground(new java.awt.Color(238, 150, 75));
        btnresetKM.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnresetKM.setForeground(new java.awt.Color(255, 255, 255));
        btnresetKM.setText("Reset");
        btnresetKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetKMActionPerformed(evt);
            }
        });

        tableKM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã khuyến mãi", "Mã code", "Giá trị", "Loại", "Ngày bắt đầu", "Ngày kết thúc", "Tình trạng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableKM.setRowHeight(25);
        tableKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableKMMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tableKM);

        btnTaoMa.setBackground(new java.awt.Color(248, 211, 94));
        btnTaoMa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnTaoMa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/dices.png"))); // NOI18N
        btnTaoMa.setText("Tạo mã");
        btnTaoMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(189, 189, 189)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textgiaTriKM, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(61, 61, 61)
                        .addComponent(textmaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textmaCode, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49)
                    .addComponent(jLabel50))
                .addGap(32, 32, 32)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxloaiMaKM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_start_dis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_end_dis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(258, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntimMaKM)
                .addGap(20, 20, 20)
                .addComponent(btnTaoMa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnthemMaKM)
                .addGap(18, 18, 18)
                .addComponent(btnxoaMaKM)
                .addGap(18, 18, 18)
                .addComponent(btnsuaMaKM)
                .addGap(18, 18, 18)
                .addComponent(btnresetKM)
                .addGap(336, 336, 336))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxloaiMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addComponent(textmaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(30, 30, 30)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(date_start_dis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel49)
                        .addComponent(jLabel46)
                        .addComponent(textmaCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47)
                        .addComponent(textgiaTriKM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel50))
                    .addComponent(date_end_dis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthemMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoaMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsuaMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnresetKM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaoMa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnCard7Layout = new javax.swing.GroupLayout(jpnCard7);
        jpnCard7.setLayout(jpnCard7Layout);
        jpnCard7Layout.setHorizontalGroup(
            jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard7Layout.createSequentialGroup()
                .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 1334, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 1334, Short.MAX_VALUE))
                .addContainerGap(546, Short.MAX_VALUE))
        );
        jpnCard7Layout.setVerticalGroup(
            jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard7Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard7, "jpnListDis");

        jpnCard8.setBackground(new java.awt.Color(255, 255, 255));

        btnthemKho.setBackground(new java.awt.Color(244, 211, 94));
        btnthemKho.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnthemKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemKho.setText("Thêm");
        btnthemKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemKhoActionPerformed(evt);
            }
        });

        jTable8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã kho", "Trạng thái", "Loại"
            }
        ));
        jTable8.setRowHeight(25);
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable8MousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(jTable8);

        btnxoaKho.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaKho.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnxoaKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaKho.setText("Xóa");
        btnxoaKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaKhoActionPerformed(evt);
            }
        });

        btnsuaKho.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaKho.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnsuaKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaKho.setText("Sửa");
        btnsuaKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaKhoActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Mã kho");

        textMaKho.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textMaKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textMaKhoActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText("Trạng thái");

        boxTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        boxTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đầy", "Chưa đầy" }));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("Loại");

        boxLoai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        boxLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sản phẩm", "Vật liệu" }));

        btntimKho.setBackground(new java.awt.Color(248, 211, 94));
        btntimKho.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btntimKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimKho.setText("Tìm");
        btntimKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimKhoActionPerformed(evt);
            }
        });

        btn_resetStock.setBackground(new java.awt.Color(238, 150, 75));
        btn_resetStock.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_resetStock.setForeground(new java.awt.Color(255, 255, 255));
        btn_resetStock.setText("Reset");
        btn_resetStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetStockActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(0, 179, 179));
        jPanel13.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("DANH SÁCH KHO");

        cbb_dx_card8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbb_dx_card8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(344, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(cbb_dx_card8, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbb_dx_card8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnCard8Layout = new javax.swing.GroupLayout(jpnCard8);
        jpnCard8.setLayout(jpnCard8Layout);
        jpnCard8Layout.setHorizontalGroup(
            jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard8Layout.createSequentialGroup()
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard8Layout.createSequentialGroup()
                        .addGap(492, 492, 492)
                        .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel51)
                            .addComponent(jLabel53))
                        .addGap(68, 68, 68)
                        .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textMaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCard8Layout.createSequentialGroup()
                        .addGap(437, 437, 437)
                        .addComponent(btntimKho)
                        .addGap(29, 29, 29)
                        .addComponent(btnthemKho)
                        .addGap(23, 23, 23)
                        .addComponent(btnxoaKho)
                        .addGap(32, 32, 32)
                        .addComponent(btnsuaKho)
                        .addGap(46, 46, 46)
                        .addComponent(btn_resetStock))
                    .addGroup(jpnCard8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(501, 501, 501))
        );
        jpnCard8Layout.setVerticalGroup(
            jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard8Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(textMaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(30, 30, 30)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(30, 30, 30)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimKho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthemKho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsuaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_resetStock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(325, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard8, "jpnListStock");

        jpnCard9.setBackground(new java.awt.Color(255, 255, 255));
        jpnCard9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel14.setBackground(new java.awt.Color(0, 179, 179));
        jPanel14.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("DANH SÁCH NHÂN VIÊN");

        cbb_dx_card9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbb_dx_card9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_dx_card9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbb_dx_card9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(121, 121, 121))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("Mã nhân viên");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("Mã nông trại");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("Tên nhân viên");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Địa chỉ");

        textmaNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        boxFarmID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        boxFarmID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F1", "F2", "F3", "F4" }));
        boxFarmID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxFarmIDActionPerformed(evt);
            }
        });

        texttenNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        texttenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttenNVActionPerformed(evt);
            }
        });

        textdiaChiNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("Email");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("Số điện thoại");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Ngày vào làm");

        textemailNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        textsdtNV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel69.setText("User Role");

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel68.setText("Username");

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel67.setText("Password");

        textpassWord.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        textuserName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        boxUserRole.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        boxUserRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản Lý", "Nhân Viên" }));

        btntimNhanVien.setBackground(new java.awt.Color(248, 211, 94));
        btntimNhanVien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btntimNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimNhanVien.setText("Tìm");
        btntimNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimNhanVienActionPerformed(evt);
            }
        });

        btnthemNhanVien.setBackground(new java.awt.Color(244, 211, 94));
        btnthemNhanVien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnthemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemNhanVien.setText("Thêm");
        btnthemNhanVien.setMinimumSize(null);
        btnthemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemNhanVienActionPerformed(evt);
            }
        });

        btnxoaNhanVien.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnxoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaNhanVien.setText("Xóa");
        btnxoaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaNhanVienActionPerformed(evt);
            }
        });

        btnsuaNhanVien.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnsuaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaNhanVien.setText("Sửa");
        btnsuaNhanVien.setMinimumSize(null);
        btnsuaNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaNhanVienActionPerformed(evt);
            }
        });

        btnResetNV.setBackground(new java.awt.Color(238, 150, 75));
        btnResetNV.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnResetNV.setForeground(new java.awt.Color(255, 255, 255));
        btnResetNV.setText("Reset");
        btnResetNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetNVActionPerformed(evt);
            }
        });

        jTable9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhân viên", "Mã nông trại", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Email", "Ngày vào làm"
            }
        ));
        jTable9.setRowHeight(25);
        jTable9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable9MousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(jTable9);
        if (jTable9.getColumnModel().getColumnCount() > 0) {
            jTable9.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable9.getColumnModel().getColumn(1).setPreferredWidth(30);
            jTable9.getColumnModel().getColumn(2).setPreferredWidth(30);
            jTable9.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable9.getColumnModel().getColumn(4).setPreferredWidth(120);
            jTable9.getColumnModel().getColumn(5).setPreferredWidth(40);
            jTable9.getColumnModel().getColumn(6).setPreferredWidth(100);
            jTable9.getColumnModel().getColumn(7).setPreferredWidth(40);
        }

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56)
                    .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textdiaChiNV)
                    .addComponent(textmaNV)
                    .addComponent(texttenNV)
                    .addComponent(boxFarmID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60))
                .addGap(20, 20, 20)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textsdtNV)
                    .addComponent(textemailNV)
                    .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addComponent(jLabel68)
                    .addComponent(jLabel67))
                .addGap(25, 25, 25)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textpassWord)
                    .addComponent(textuserName)
                    .addComponent(boxUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(btntimNhanVien)
                        .addGap(37, 37, 37)
                        .addComponent(btnthemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(btnxoaNhanVien)
                        .addGap(42, 42, 42)
                        .addComponent(btnsuaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnResetNV))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel69)
                            .addComponent(boxUserRole, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(textuserName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(textpassWord, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(textmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58)
                            .addComponent(textsdtNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel55)
                            .addComponent(textemailNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59)
                            .addComponent(boxFarmID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(texttenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56)
                            .addComponent(jLabel60)
                            .addComponent(txtStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textdiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))))
                .addGap(30, 30, 30)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthemNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsuaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetNV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnCard9Layout = new javax.swing.GroupLayout(jpnCard9);
        jpnCard9.setLayout(jpnCard9Layout);
        jpnCard9Layout.setHorizontalGroup(
            jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard9Layout.createSequentialGroup()
                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 598, Short.MAX_VALUE))
        );
        jpnCard9Layout.setVerticalGroup(
            jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard9Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(194, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard9, "jpnListEmp");

        jpnCard10.setBackground(new java.awt.Color(255, 255, 255));

        jTable10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã vận chuyển", "Mã đơn hàng", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable10.setRowHeight(25);
        jTable10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable10MousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(jTable10);

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel70.setText("Mã vận chuyển");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel71.setText("Mã đơn hàng");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel72.setText("Trạng thái");

        textTransID.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        textOrd_Ex_Num.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        textOrd_Ex_Num.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textOrd_Ex_NumActionPerformed(evt);
            }
        });

        boxStatusTrans.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        boxStatusTrans.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thất bại", "Thành công", "Ðang giao hàng" }));

        btnUpdateTrans.setBackground(new java.awt.Color(248, 211, 94));
        btnUpdateTrans.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnUpdateTrans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnUpdateTrans.setText("Cập nhật");
        btnUpdateTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTransActionPerformed(evt);
            }
        });

        btnSearchTrans.setBackground(new java.awt.Color(248, 211, 94));
        btnSearchTrans.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSearchTrans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btnSearchTrans.setText("Tìm kiếm");
        btnSearchTrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchTransActionPerformed(evt);
            }
        });

        btnResetTransport.setBackground(new java.awt.Color(238, 150, 75));
        btnResetTransport.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnResetTransport.setForeground(new java.awt.Color(255, 255, 255));
        btnResetTransport.setText("Reset");
        btnResetTransport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTransportActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(0, 179, 179));
        jPanel17.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("TRẠNG THÁI VẬN CHUYỂN");

        cbb_dx_card10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbb_dx_card10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(cbb_dx_card10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbb_dx_card10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnCard10Layout = new javax.swing.GroupLayout(jpnCard10);
        jpnCard10.setLayout(jpnCard10Layout);
        jpnCard10Layout.setHorizontalGroup(
            jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard10Layout.createSequentialGroup()
                .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard10Layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel70)
                            .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCard10Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(boxStatusTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnCard10Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textTransID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textOrd_Ex_Num, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 598, Short.MAX_VALUE))
            .addGroup(jpnCard10Layout.createSequentialGroup()
                .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnCard10Layout.createSequentialGroup()
                        .addGap(481, 481, 481)
                        .addComponent(btnUpdateTrans)
                        .addGap(40, 40, 40)
                        .addComponent(btnSearchTrans)
                        .addGap(42, 42, 42)
                        .addComponent(btnResetTransport, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCard10Layout.setVerticalGroup(
            jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard10Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(textTransID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(textOrd_Ex_Num, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(boxStatusTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetTransport, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard10, "jpnListTrans");

        jpnCard11.setBackground(new java.awt.Color(255, 255, 255));

        thongkedoanhthu.setBackground(new java.awt.Color(255, 255, 255));
        thongkedoanhthu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 255, 153), null, null));
        thongkedoanhthu.setPreferredSize(new java.awt.Dimension(1269, 321));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/analysis.png"))); // NOI18N
        jLabel24.setText("THỐNG KÊ DOANH THU");

        javax.swing.GroupLayout thongkedoanhthuLayout = new javax.swing.GroupLayout(thongkedoanhthu);
        thongkedoanhthu.setLayout(thongkedoanhthuLayout);
        thongkedoanhthuLayout.setHorizontalGroup(
            thongkedoanhthuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkedoanhthuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(951, Short.MAX_VALUE))
        );
        thongkedoanhthuLayout.setVerticalGroup(
            thongkedoanhthuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thongkedoanhthuLayout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        top_product.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 255, 153), null, null));
        top_product.setPreferredSize(new java.awt.Dimension(460, 460));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/analysis.png"))); // NOI18N
        jLabel14.setText("THỐNG KÊ TOP SẢN PHẨM");

        javax.swing.GroupLayout top_productLayout = new javax.swing.GroupLayout(top_product);
        top_product.setLayout(top_productLayout);
        top_productLayout.setHorizontalGroup(
            top_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_productLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        top_productLayout.setVerticalGroup(
            top_productLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_productLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 255, 153), null, null));

        jButton3.setText("Xem");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cbb_thong_ke_thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbb_thong_ke_thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_thong_ke_thangActionPerformed(evt);
            }
        });

        cbb_thong_ke_nam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021", "2022" }));

        cbb_thong_ke.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm", "Tháng", "Ngày" }));
        cbb_thong_ke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_thong_keActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel74.setText("Năm");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel75.setText("Tháng");

        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel76.setText("Thống kê theo");

        btnXuatThongKeDoanhThu.setText("Xuất");
        btnXuatThongKeDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatThongKeDoanhThuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbb_thong_ke, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_thong_ke_nam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_thong_ke_thang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(btnXuatThongKeDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_thong_ke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_thong_ke_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_thong_ke_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addGap(36, 36, 36)
                .addComponent(btnXuatThongKeDoanhThu)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 255, 153), null, null));

        cbb_thong_ke_sp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm", "Tháng" }));
        cbb_thong_ke_sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_thong_ke_spActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setText("Thống kê theo");

        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel78.setText("Năm");

        cbb_thong_ke_sp_nam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021", "2022" }));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel79.setText("Tháng");

        cbb_thong_ke_sp_thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbb_thong_ke_sp_thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_thong_ke_sp_thangActionPerformed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel80.setText("Top");

        cbb_top_sp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jButton6.setText("Xem");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setText("Xuất");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_thong_ke_sp_nam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_thong_ke_sp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_thong_ke_sp_thang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_top_sp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbb_thong_ke_sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_thong_ke_sp_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel79)
                .addGap(7, 7, 7)
                .addComponent(cbb_thong_ke_sp_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_top_sp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 255, 153), null, null));

        cbb_thong_ke_sp1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Năm", "Tháng" }));
        cbb_thong_ke_sp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_thong_ke_sp1ActionPerformed(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel81.setText("Thống kê theo");

        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel82.setText("Năm");

        cbb_thong_ke_sp_nam1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021", "2022" }));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel83.setText("Tháng");

        cbb_thong_ke_sp_thang1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cbb_thong_ke_sp_thang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_thong_ke_sp_thang1ActionPerformed(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel84.setText("Top");

        cbb_top_sp1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jButton7.setText("Xem");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Xuất");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb_thong_ke_sp_nam1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_thong_ke_sp1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_thong_ke_sp_thang1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbb_top_sp1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbb_thong_ke_sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_thong_ke_sp_nam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel83)
                .addGap(7, 7, 7)
                .addComponent(cbb_thong_ke_sp_thang1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbb_top_sp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        top_cus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/analysis.png"))); // NOI18N
        jLabel61.setText("THỐNG KÊ TOP KHÁCH HÀNG");

        javax.swing.GroupLayout top_cusLayout = new javax.swing.GroupLayout(top_cus);
        top_cus.setLayout(top_cusLayout);
        top_cusLayout.setHorizontalGroup(
            top_cusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_cusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );
        top_cusLayout.setVerticalGroup(
            top_cusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(top_cusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnCard11Layout = new javax.swing.GroupLayout(jpnCard11);
        jpnCard11.setLayout(jpnCard11Layout);
        jpnCard11Layout.setHorizontalGroup(
            jpnCard11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard11Layout.createSequentialGroup()
                .addGroup(jpnCard11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jpnCard11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(thongkedoanhthu, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCard11Layout.createSequentialGroup()
                        .addComponent(top_product, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(top_cus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 583, Short.MAX_VALUE))
        );
        jpnCard11Layout.setVerticalGroup(
            jpnCard11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard11Layout.createSequentialGroup()
                .addGroup(jpnCard11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(thongkedoanhthu, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jpnCard11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(top_product, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(top_cus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(251, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard11, "jpnListStatis");

        jpnCard12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Mã kho");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Mã nguyên vật liệu");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("Tên nguyên vật liệu");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("Số lượng");

        cbb_makhovl_ton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_makhovl_ton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbb_mavl_ton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_mavl_ton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_mavl_ton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_mavl_tonItemStateChanged(evt);
            }
        });
        cbb_mavl_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_mavl_tonActionPerformed(evt);
            }
        });

        text_soluongvl_ton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        table_ds_inven_re.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table_ds_inven_re.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã kho", "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_ds_inven_re.setRowHeight(25);
        table_ds_inven_re.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ds_inven_reMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(table_ds_inven_re);

        btn_Timvl_ton.setBackground(new java.awt.Color(248, 211, 94));
        btn_Timvl_ton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_Timvl_ton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btn_Timvl_ton.setText("Tìm");
        btn_Timvl_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Timvl_tonActionPerformed(evt);
            }
        });

        btn_Themvl_ton.setBackground(new java.awt.Color(244, 211, 94));
        btn_Themvl_ton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_Themvl_ton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btn_Themvl_ton.setText("Thêm");
        btn_Themvl_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Themvl_tonActionPerformed(evt);
            }
        });

        btn_Xoavl_ton.setBackground(new java.awt.Color(248, 211, 94));
        btn_Xoavl_ton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_Xoavl_ton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btn_Xoavl_ton.setText("Xóa");
        btn_Xoavl_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Xoavl_tonActionPerformed(evt);
            }
        });

        btn_Suavl_ton.setBackground(new java.awt.Color(248, 211, 94));
        btn_Suavl_ton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_Suavl_ton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btn_Suavl_ton.setText("Sửa");
        btn_Suavl_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Suavl_tonActionPerformed(evt);
            }
        });

        btn_datlai_nvl_ton.setBackground(new java.awt.Color(238, 150, 75));
        btn_datlai_nvl_ton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_datlai_nvl_ton.setForeground(new java.awt.Color(255, 255, 255));
        btn_datlai_nvl_ton.setText("Reset");
        btn_datlai_nvl_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datlai_nvl_tonActionPerformed(evt);
            }
        });

        jPanel18.setBackground(new java.awt.Color(0, 179, 179));
        jPanel18.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("QUẢN LÝ TỒN KHO NGUYÊN VẬT LIỆU");

        cbb_dx_card12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbb_dx_card12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(437, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(cbb_dx_card12, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cbb_dx_card12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        cbb_tenvl_ton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_tenvl_ton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_tenvl_ton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_tenvl_tonItemStateChanged(evt);
            }
        });
        cbb_tenvl_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_tenvl_tonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCard12Layout = new javax.swing.GroupLayout(jpnCard12);
        jpnCard12.setLayout(jpnCard12Layout);
        jpnCard12Layout.setHorizontalGroup(
            jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard12Layout.createSequentialGroup()
                .addGroup(jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard12Layout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addGroup(jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGap(43, 43, 43)
                        .addGroup(jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(text_soluongvl_ton, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(cbb_mavl_ton, 0, 250, Short.MAX_VALUE)
                            .addComponent(cbb_makhovl_ton, 0, 250, Short.MAX_VALUE)
                            .addComponent(cbb_tenvl_ton, 0, 250, Short.MAX_VALUE)))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCard12Layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(btn_Timvl_ton)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Themvl_ton)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Xoavl_ton)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Suavl_ton)
                        .addGap(18, 18, 18)
                        .addComponent(btn_datlai_nvl_ton))
                    .addGroup(jpnCard12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(598, Short.MAX_VALUE))
        );
        jpnCard12Layout.setVerticalGroup(
            jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard12Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnCard12Layout.createSequentialGroup()
                        .addGroup(jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(cbb_makhovl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel21))
                    .addComponent(cbb_mavl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(cbb_tenvl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(text_soluongvl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Timvl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Themvl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoavl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Suavl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_datlai_nvl_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard12, "jpnListTonSP");

        jpnCard13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel63.setText("Mã kho");

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel64.setText("Mã nông sản");

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setText("Tên nông sản");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel66.setText("Số lượng");

        cbb_ton_makho_ns.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_ton_makho_ns.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbb_ton_mans.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_ton_mans.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_ton_mans.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_ton_mansItemStateChanged(evt);
            }
        });
        cbb_ton_mans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_ton_mansActionPerformed(evt);
            }
        });

        txt_ton_soluong_ns.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btn_Tim_nongsan_ton.setBackground(new java.awt.Color(248, 211, 94));
        btn_Tim_nongsan_ton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_Tim_nongsan_ton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btn_Tim_nongsan_ton.setText("Tìm");
        btn_Tim_nongsan_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Tim_nongsan_tonActionPerformed(evt);
            }
        });

        btn_them_nongsan_ton.setBackground(new java.awt.Color(244, 211, 94));
        btn_them_nongsan_ton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_them_nongsan_ton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btn_them_nongsan_ton.setText("Thêm");
        btn_them_nongsan_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them_nongsan_tonActionPerformed(evt);
            }
        });

        btn_xoa_nongsan_ton.setBackground(new java.awt.Color(248, 211, 94));
        btn_xoa_nongsan_ton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_xoa_nongsan_ton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btn_xoa_nongsan_ton.setText("Xóa");
        btn_xoa_nongsan_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoa_nongsan_tonActionPerformed(evt);
            }
        });

        btn_sua_nongsan_ton.setBackground(new java.awt.Color(248, 211, 94));
        btn_sua_nongsan_ton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btn_sua_nongsan_ton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btn_sua_nongsan_ton.setText("Sửa");
        btn_sua_nongsan_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sua_nongsan_tonActionPerformed(evt);
            }
        });

        btn_datlai_nongsan_ton.setBackground(new java.awt.Color(238, 150, 75));
        btn_datlai_nongsan_ton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_datlai_nongsan_ton.setForeground(new java.awt.Color(255, 255, 255));
        btn_datlai_nongsan_ton.setText("Reset");
        btn_datlai_nongsan_ton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datlai_nongsan_tonActionPerformed(evt);
            }
        });

        table_ds_ton_ns.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table_ds_ton_ns.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã kho", "Mã nông sản", "Tên nông sản", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_ds_ton_ns.setRowHeight(25);
        table_ds_ton_ns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ds_ton_nsMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(table_ds_ton_ns);

        jPanel19.setBackground(new java.awt.Color(0, 179, 179));
        jPanel19.setPreferredSize(new java.awt.Dimension(1282, 75));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("QUẢN LÝ TỒN KHO NÔNG SẢN");

        cbb_dx_card13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cbb_dx_card13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        cbb_dx_card13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_dx_card13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(415, Short.MAX_VALUE)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213)
                .addComponent(cbb_dx_card13, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(cbb_dx_card13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        cbb_ton_tenns.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbb_ton_tenns.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_ton_tenns.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbb_ton_tennsItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jpnCard13Layout = new javax.swing.GroupLayout(jpnCard13);
        jpnCard13.setLayout(jpnCard13Layout);
        jpnCard13Layout.setHorizontalGroup(
            jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard13Layout.createSequentialGroup()
                .addGroup(jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard13Layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addGroup(jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65)
                            .addComponent(jLabel66)
                            .addComponent(jLabel64)
                            .addComponent(jLabel63))
                        .addGap(18, 18, 18)
                        .addGroup(jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbb_ton_mans, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ton_soluong_ns, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_ton_makho_ns, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_ton_tenns, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnCard13Layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(btn_Tim_nongsan_ton)
                        .addGap(18, 18, 18)
                        .addComponent(btn_them_nongsan_ton)
                        .addGap(18, 18, 18)
                        .addComponent(btn_xoa_nongsan_ton)
                        .addGap(18, 18, 18)
                        .addComponent(btn_sua_nongsan_ton)
                        .addGap(18, 18, 18)
                        .addComponent(btn_datlai_nongsan_ton))
                    .addGroup(jpnCard13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 1256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 1398, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(482, Short.MAX_VALUE))
        );
        jpnCard13Layout.setVerticalGroup(
            jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard13Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(cbb_ton_makho_ns, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(cbb_ton_mans, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(cbb_ton_tenns, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(txt_ton_soluong_ns, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpnCard13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Tim_nongsan_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_them_nongsan_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoa_nongsan_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sua_nongsan_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_datlai_nongsan_ton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard13, "jpnListNVL");

        jSplitPane2.setRightComponent(jpnCardLayout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtManaProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaProActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListPro");
        try {
            showProduct();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtManaProActionPerformed

    private void jbtManaFarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaFarmActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListFarm");
        try {
            showFarm();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jbtManaFarmActionPerformed

    private void jbtManaOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaOrderActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListOrder");
        showOrderExport();
    }//GEN-LAST:event_jbtManaOrderActionPerformed

    private void jbtManaReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaReActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListRe");
        try {
            showResources();
            reset_resources();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jbtManaReActionPerformed

    private void jbtManaSupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaSupActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListSup");
        try {
            showSup();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jbtManaSupActionPerformed

    private void jbtManaCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaCusActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListCus");
        try {
            reset_Cus();
            showCus();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jbtManaCusActionPerformed

    private void jbtManaDisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaDisActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListDis");
        
        try {
            showDis();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jbtManaDisActionPerformed

    //trang quan ly kho
    private void jbtManaStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaStockActionPerformed

        cardlayout.show(jpnCardLayout, "jpnListStock");

        try {
            showStock();
            reset_stock();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtManaStockActionPerformed

    //trang quan ly nhan vien
    private void jbtManaEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaEmpActionPerformed
        cardlayout.show(jpnCardLayout, "jpnListEmp");

        try {
            
            showEmployee();
            reset_Employee();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtManaEmpActionPerformed

    //trang quan ly van chuyen
    private void jbtManaTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaTransActionPerformed
        cardlayout.show(jpnCardLayout, "jpnListTrans");
        reset_transport();
        try {
            showTransport();

        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtManaTransActionPerformed

    private void jbtManaStatisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaStatisActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListStatis");
        reset_statistical();
    }//GEN-LAST:event_jbtManaStatisActionPerformed

    private void btnthemNongTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemNongTraiActionPerformed
        // TODO add your handling code here: 
        boolean check = false;
        String maNT = textMaNT.getText();
        String tenNT = texttenNT.getText();
        String diachiNT = textdiaChiNT.getText();

        if (tenNT.isEmpty() || diachiNT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
        } else {
            if (!maNT.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Không cần nhập mã nông trại khi thêm mới!");
            } else {
                try {

                    Farm fa = new Farm("", tenNT, diachiNT);
                    check = Farm_Controller.insertFarm(fa);

                    if (check == true) {

                        JOptionPane.showMessageDialog(this, "Thêm thành công.");
                        showFarm();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm không thành công",
                                "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
                    }
                    reset_Farm();

                } catch (SQLException ex) {
                    int code = ex.getErrorCode();
                    String msg = ex.getMessage();
                    JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }//GEN-LAST:event_btnthemNongTraiActionPerformed

    private void btnxoaNongTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaNongTraiActionPerformed
        String farmid = textMaNT.getText();
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa không ?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                Farm_Controller.deleteFarm(farmid);
                showFarm();
                reset_Farm();
            }

        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoaNongTraiActionPerformed

    private void btnxoaVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaVLActionPerformed
        // TODO add your handling code here:
        String id = textMaVL.getText();
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Ban co muon xoa?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                Controller_Resource.delete(id);
                showResources();
                reset_resources();
            }

        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoaVLActionPerformed

    private void btnxoaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaNCCActionPerformed
        // TODO add your handling code here:
        String supID = textmaNCC.getText();
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa không ?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                Supplier_Controller.deleteSup(supID);
                showSup();
                reset_Sup();
            }

        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoaNCCActionPerformed

    private void btnsuaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaNCCActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String maNCC = textmaNCC.getText();
        String tenNCC = texttenNCC.getText();
        String sdtNCC = textsdtNCC.getText();
        String diachiNCC = textdiaChiNCC.getText();
        String emailNCC = textemailNCC.getText();
        try {
            if (tenNCC.isEmpty() || diachiNCC.isEmpty() || emailNCC.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            } else {
                Supplier sup = new Supplier(maNCC, tenNCC, sdtNCC, diachiNCC, emailNCC);
                check = Supplier_Controller.updateSup(sup);
                if (check == true) {

                    JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    showSup();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa không thành công",
                            "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
                }
                reset_Sup();
            }
        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnsuaNCCActionPerformed

    private void btnthemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemSPActionPerformed
        // TODO add your handling code here:
        //open AddProduct
        boolean check = false;
        String masp = textmaSP.getText();
        String tenns = texttenSP.getText();
        String mant = (String) cbb_ma_nong_trai_sp.getSelectedItem();
        String loains = (String) cbb_loai_sp.getSelectedItem();
        String anh = text_link_anh.getText();
        Integer giasp = 0;

        if (tenns.equals("") || mant == null || loains == null || anh.equals("")) {
            JOptionPane.showMessageDialog(this, "Hay dien day du thong tin");
        } else if (!masp.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Them moi khong can nhap ma");
        } else {
            try {
                if (!textGia_nong_san.getText().equalsIgnoreCase("")) {
                    giasp = Integer.parseInt(textGia_nong_san.getText());
                }
                Product pro = new Product("", tenns, giasp, mant, loains, anh, 0);
                check = Controller_Product.insert(pro);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Thêm nông sản thành công");
                    showProduct();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                reset_product();
                
            } catch (SQLException e) {
                int code = e.getErrorCode();
                String msg = e.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }catch (NumberFormatException en) {
                JOptionPane.showMessageDialog(this, "Giá nông sản phải có định dạng là số");
            }
        }


    }//GEN-LAST:event_btnthemSPActionPerformed

    private void textmaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textmaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textmaSPActionPerformed

    private void texttenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texttenSPActionPerformed

    private void cbb_loai_spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_loai_spActionPerformed

    }//GEN-LAST:event_cbb_loai_spActionPerformed

    private void textmaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textmaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textmaKMActionPerformed

    private void textMaKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textMaKhoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textMaKhoActionPerformed

    private void btntimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimSPActionPerformed
        // TODO add your handling code here:
        try {
            tableModel6.setRowCount(0);
            String proid = textmaSP.getText();
            String proname = texttenSP.getText();
            String pprice = textGia_nong_san.getText();
            String ffarmid = (String) cbb_ma_nong_trai_sp.getSelectedItem();
            String pprotype = (String) cbb_loai_sp.getSelectedItem();

            productList = Controller_Product.Search_Product(proid, proname, pprice, ffarmid, pprotype);
            int flag = 0;

            for (Product pro : productList) {
                tableModel6.addRow(new Object[]{tableModel6.getRowCount() + 1, pro.getProID(), pro.getProName(),
                    pro.getFarmID(), pro.getProPrice(), pro.getProType(), pro.getQuantity(), pro.getImage()});
                flag = 1;
            }
            if (flag != 1) {
                JOptionPane.showMessageDialog(null, "Không có sản phẩm cần tìm!");
            }

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
        }
    }//GEN-LAST:event_btntimSPActionPerformed

    private void btnsuaVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaVLActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String mavl = textMaVL.getText();
        String tenvl = texttenVL.getText();
        String loaivl = (String) cbxloaiVL.getSelectedItem();
        Integer giavl = 0;
       
        if (textgiaVL.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Giá nguyên vật liệu không được bỏ trống",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if (0 == giavl) {
            JOptionPane.showMessageDialog(this, "Giá nguyên vật liệu không được 0",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if (tenvl.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nguyên vật liệu không được bỏ trống",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                if (!textgiaVL.getText().equalsIgnoreCase("")) {
                    giavl = Integer.parseInt(textgiaVL.getText());
                }
                Resources rc = new Resources(mavl, tenvl, giavl, loaivl, 0);
                check = Controller_Resource.update(rc);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Đã sửa thành công.");
                    showResources();
                } else {
                    JOptionPane.showMessageDialog(this, "sửa không thành công",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                reset_resources();
            } catch (SQLException ex) {
                int code = ex.getErrorCode();
                String msg = ex.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }catch (NumberFormatException en) {
                JOptionPane.showMessageDialog(this, "Giá nguyên vật liệu phải có định dạng là số");
            }
        }

    }//GEN-LAST:event_btnsuaVLActionPerformed

    private void btnthemVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemVLActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String mavl = textMaVL.getText();
        String tenvl = texttenVL.getText();
        String loaivl = (String) cbxloaiVL.getSelectedItem();
        Integer giavl = 0;

        if (tenvl.equals("") || loaivl == null || giavl == 0) {
            JOptionPane.showMessageDialog(this, "Hay dien day du thong tin cho nguyen vat lieu");
        } else if (!mavl.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Không cần nhập mã nguyên vật liệu khi thêm mới!");
        } else {
            try {
                if (!textgiaVL.getText().equalsIgnoreCase("")) {
                    giavl = Integer.parseInt(textgiaVL.getText());
                }
                Resources rc = new Resources("", tenvl, giavl, loaivl, 0);
                check = Controller_Resource.insert(rc);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Thêm nguyên vật liệu thành công");
                    showResources();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                reset_resources();

            } catch (SQLException ex) {
                int code = ex.getErrorCode();
                String msg = ex.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException en) {
                JOptionPane.showMessageDialog(this, "Giá nguyên vật liệu phải có định dạng là số");
            }
        }
        
    }//GEN-LAST:event_btnthemVLActionPerformed

    private void texttenVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttenVLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texttenVLActionPerformed

    private void btntimNongTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimNongTraiActionPerformed
        try {
            tableModel.setRowCount(0);
            String maNT = textMaNT.getText();
            String tenNT = texttenNT.getText();
            String diaChiNT = textdiaChiNT.getText();
            
            listFarm = Farm_Controller.searchFarm(maNT, tenNT, diaChiNT);

            int flag = 0;
            for (Farm f : listFarm) {
                tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, f.getFarmID(), f.getFarmName(),
                                                                            f.getFarmAdd()});
                flag = 1;
            }
            if(flag != 1){
                JOptionPane.showMessageDialog(null, "Không có nông trại cần tìm!");
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btntimNongTraiActionPerformed

    private void btnsuaNongTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaNongTraiActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String maNT = textMaNT.getText();
        String tenNT = texttenNT.getText();
        String diachiNT = textdiaChiNT.getText();

        try {

            Farm fa = new Farm(maNT, tenNT, diachiNT);
            check = Farm_Controller.updateFarm(fa);
            if (tenNT.isEmpty() || diachiNT.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            } else {
                if (check == true) {

                    JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    showFarm();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa không thành công",
                            "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
                }
                reset_Farm();
            }

        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsuaNongTraiActionPerformed

    private void tableNongTraiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNongTraiMouseClicked
//      
    }//GEN-LAST:event_tableNongTraiMouseClicked

    private void btnthemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemNCCActionPerformed

        boolean check = false;
        String maNCC = textmaNCC.getText();
        String tenNCC = texttenNCC.getText();
        String sdtNCC = "";
        String diachiNCC = textdiaChiNCC.getText();
        String emailNCC = textemailNCC.getText();
        if (tenNCC.isEmpty() || diachiNCC.isEmpty() || emailNCC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
        } else {
            if (!maNCC.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Không cần nhập mã nhà cung cấp khi thêm mới!");
            } else {
                try {

                    Supplier sup = new Supplier("", tenNCC, sdtNCC, diachiNCC, emailNCC);
                    check = Supplier_Controller.insertSup(sup);

                    if (check == true) {

                        JOptionPane.showMessageDialog(this, "Thêm thành công.");
                        showSup();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm không thành công",
                                "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
                    }
                    reset_Sup();

                } catch (SQLException ex) {
                    int code = ex.getErrorCode();
                    String msg = ex.getMessage();
                    JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }//GEN-LAST:event_btnthemNCCActionPerformed

    private void tableSupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSupMouseClicked

    }//GEN-LAST:event_tableSupMouseClicked

    private void tableNongTraiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNongTraiMousePressed
        int selectedIndex = tableNongTrai.getSelectedRow();
        if (selectedIndex >= 0) {
            Farm fa = listFarm.get(selectedIndex);
            textMaNT.setText(fa.getFarmID());
            textMaNT.setEditable(false);
            texttenNT.setText(fa.getFarmName());
            textdiaChiNT.setText(fa.getFarmAdd());
        }

    }//GEN-LAST:event_tableNongTraiMousePressed

    private void btnresetNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetNTActionPerformed
        // TODO add your handling code here:
        try {
            showFarm();
            reset_Farm();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnresetNTActionPerformed

    private void btntimNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimNCCActionPerformed
        // TODO add your handling code here:
        try {
            tableModel1.setRowCount(0);
            String s_supid = textmaNCC.getText();
            String s_supname = texttenNCC.getText();
            String s_supphone = textsdtNCC.getText();
            String s_supadd = textdiaChiNCC.getText();
            String s_supemail = textemailNCC.getText();
            
            
            listSup = Supplier_Controller.search_Supplier_forAll(s_supid, s_supname, s_supphone,s_supadd,s_supemail);  
            int flag = 0;
            for (Supplier sup :listSup ) {
                tableModel1.addRow(new Object[]{tableModel1.getRowCount() + 1, sup.getSupID(),sup.getSupName(),sup.getSupPhone(),sup.getSupAdd(),sup.getSupAdd()});
                flag = 1;
            }
            if(flag != 1){
                JOptionPane.showMessageDialog(null, "Không có nà cung cấp cần tìm!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btntimNCCActionPerformed

    private void tableSupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSupMousePressed
        // TODO add your handling code here:
        int selectedIndex = tableSup.getSelectedRow();
        if (selectedIndex >= 0) {
            Supplier sup = listSup.get(selectedIndex);
            textmaNCC.setText(sup.getSupID());
            textmaNCC.setEditable(false);
            texttenNCC.setText(sup.getSupName());
            textsdtNCC.setText(String.valueOf(sup.getSupPhone()));
            textdiaChiNCC.setText(sup.getSupAdd());
            textemailNCC.setText(sup.getSupEmail());
        }
    }//GEN-LAST:event_tableSupMousePressed

    private void btnresetNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetNCCActionPerformed
        try {
            // TODO add your handling code here:
            showSup();
            reset_Sup();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnresetNCCActionPerformed

    private void cbxloaiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxloaiKHActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbxloaiKHActionPerformed

    private void tableKHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKHMousePressed
        // TODO add your handling code here:
        NumberFormat f = NumberFormat.getInstance();
        f.setGroupingUsed(false);
        int selectedIndex = tableKH.getSelectedRow();
        if (selectedIndex >= 0) {
            Customer cus = listCus.get(selectedIndex);
            textmaKH.setText(cus.getCusID());
            textmaKH.setEditable(false);
            texttenKH.setText(cus.getCusName());
            cbxgenderKH.setSelectedItem(cus.getGender());
            //dateBirthKH.setDate((cus.getDateOfBirth()));
            dateBirthKH.setDateFormatString("dd/MM/yyyy");
            dateBirthKH.setDate(cus.getDateOfBirth());
            //SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
            //String d=sdf.format(dateBirthKH.getDate());
            textdiaChiKH.setText(cus.getCusAdd());
            textsdtKH.setText(String.valueOf(cus.getCusPhone()));
            textemailKH.setText(cus.getCusEmail());
            cbxloaiKH.setSelectedItem(cus.getCusType());
            texttienTL.setText(f.format(cus.getAccrued_Money()));
            textmaUser.setText(cus.getUserID());
        }

    }//GEN-LAST:event_tableKHMousePressed

    private void btntimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimKHActionPerformed
        // TODO add your handling code here:
         SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            tableModel2.setRowCount(0);
            String ccusid = textmaKH.getText();
            String ccusname = texttenKH.getText();
            String cgender = (String)cbxgenderKH.getSelectedItem();
            String cadd = textdiaChiKH.getText();
            String cphone = textsdtKH.getText();
            String cemail = textemailKH.getText();
            String ctype = (String)cbxloaiKH.getSelectedItem();
            String cmoney = texttienTL.getText();
            String cuserid = textuserName.getText();
            
            listCus = Customer_Controller.Search_Customer(ccusid,ccusname,cgender,cadd,cphone,cemail,ctype,cmoney, cuserid);
            int flag = 0;
            
            for (Customer cus : listCus) {
            if(cus.getDateOfBirth() != null){
            tableModel2.addRow(new Object[]{tableModel2.getRowCount() + 1, cus.getCusID(),
                cus.getCusName(), cus.getGender(),  f.format(cus.getDateOfBirth()), cus.getCusAdd(),
                cus.getCusPhone(), cus.getCusEmail(), cus.getCusType(), cus.getAccrued_Money(),
                cus.getUserID()});
                flag = 1;}
            else{
                tableModel2.addRow(new Object[]{tableModel2.getRowCount() + 1, cus.getCusID(),
                cus.getCusName(), cus.getGender(),  cus.getDateOfBirth(), cus.getCusAdd(),
                cus.getCusPhone(), cus.getCusEmail(), cus.getCusType(), cus.getAccrued_Money(),
                cus.getUserID()});
                flag = 1;
            }
        }
            if (flag != 1) {
                JOptionPane.showMessageDialog(null, "Không có khách hàng cần tìm!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btntimKHActionPerformed

    private void btnresetKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetKHActionPerformed
        // TODO add your handling code here:
        try {
            showCus();
            reset_Cus();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnresetKHActionPerformed

    private void btnthemMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemMaKMActionPerformed
        boolean check = false;
        String maKM = textmaKM.getText();
        String maCode = textmaCode.getText();
        
        double giatriKM =0;
        if(textgiaTriKM.getText().equalsIgnoreCase("")){
            giatriKM =0;
        }else{
           giatriKM =  Double.parseDouble(textgiaTriKM.getText());
        }
        
        
        
        String loaimaKM = (String) cbxloaiMaKM.getSelectedItem();
        DateFormat dateformat = new SimpleDateFormat("dd-MMM-yy");
        String ngayBD = dateformat.format((java.util.Date) this.date_start_dis.getDate());
        String ngayKT = dateformat.format((java.util.Date) this.date_end_dis.getDate());


        if (maCode.isEmpty() || loaimaKM.isEmpty() || ngayBD.isEmpty() || ngayKT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin ");
        } else {
            if (!maKM.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Không cần nhập mã khi thêm mới!");
            } else {
                try {

                    Discount dis = new Discount("", maCode, giatriKM, loaimaKM, dateformat.parse(ngayBD), dateformat.parse(ngayKT), 0);
                    check = Discount_Controller.insertDis(dis);

                    if (check == true) {

                        JOptionPane.showMessageDialog(this, "Thêm thành công.");
                        showDis();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm không thành công",
                                "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
                    }
                    reset_Dis();

                } catch (SQLException ex) {
                    int code = ex.getErrorCode();
                    String msg = ex.getMessage();
                    JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);

//                } catch (ParseException ex) {
//                    Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }//GEN-LAST:event_btnthemMaKMActionPerformed

    private void tableKMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKMMousePressed
        // TODO add your handling code here:
        int selectedIndex = tableKM.getSelectedRow();
        if (selectedIndex >= 0) {
            Discount dis = listDis.get(selectedIndex);
            textmaKM.setText(dis.getDisID());
            textmaKM.setEditable(false);
            textmaCode.setText(dis.getDisCode());
            textgiaTriKM.setText(String.valueOf(dis.getValue()));
            cbxloaiMaKM.setSelectedItem(dis.getCusType());
            date_start_dis.setDateFormatString("dd/MM/yyyy");
            date_end_dis.setDateFormatString("dd/MM/yyyy");
            date_start_dis.setDate(dis.getStartDate());
            date_end_dis.setDate(dis.getEndDate());
        }
    }//GEN-LAST:event_tableKMMousePressed

    private void btnxoaMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaMaKMActionPerformed
        // TODO add your handling code here:
        String disID = textmaKM.getText();
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa không ?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                Discount_Controller.deleteDis(disID);
                showDis();
                reset_Dis();
            }

        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnxoaMaKMActionPerformed

    private void btnsuaMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaMaKMActionPerformed
        boolean check = false;
        String maKM = textmaKM.getText();
        String maCode = textmaCode.getText();
        double giatriKM = 0;
        String loaimaKM = (String) cbxloaiMaKM.getSelectedItem();
        DateFormat dateformat = new SimpleDateFormat("dd-MMM-yy");
        String ngayBD = dateformat.format((java.util.Date) this.date_start_dis.getDate());
        String ngayKT = dateformat.format((java.util.Date) this.date_end_dis.getDate());
        if (!textgiaTriKM.getText().equalsIgnoreCase("")) {
            giatriKM = Double.parseDouble(textgiaTriKM.getText());
        }

        try {
            if (maCode.isEmpty() || loaimaKM.isEmpty() || ngayBD.isEmpty() || ngayKT.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            } else {
                Discount dis = new Discount(maKM, maCode, giatriKM, loaimaKM, dateformat.parse(ngayBD), dateformat.parse(ngayKT), 0);
                check = Discount_Controller.updateDis(dis);

                if (check == true) {

                    JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    showDis();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa không thành công",
                            "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
                }
                reset_Dis();
            }
        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsuaMaKMActionPerformed

    private void btnresetKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetKMActionPerformed
        try {
            // TODO add your handling code here:
            showDis();
            reset_Dis();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnresetKMActionPerformed

    private void btntimMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimMaKMActionPerformed
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String maKM = textmaKM.getText();
        String maCode = textmaCode.getText();
        double giatriKM =0;
        if(textgiaTriKM.getText().equalsIgnoreCase("")){
            giatriKM =0;
        }else{
           giatriKM =  Double.parseDouble(textgiaTriKM.getText());
        }
 
        
        
        String loaimaKM = (String) cbxloaiMaKM.getSelectedItem();
        DateFormat dateformat = new SimpleDateFormat("dd-MMM-yy");
        Date ngayBD = null;
        Date ngayKT = null;
        
        try {
            tableModel3.setRowCount(0);
            Discount diss = new Discount(maKM, maCode, giatriKM, loaimaKM, ngayBD, ngayKT, 0);
            listDis = Discount_Controller.findDis(diss);
            int flag = 0;
            for (Discount dis : listDis) {
                tableModel3.addRow(new Object[]{tableModel3.getRowCount() + 1, dis.getDisID(),
                    dis.getDisCode(), dis.getValue(), dis.getCusType(), f.format(dis.getStartDate()), f.format(dis.getEndDate()),dis.getStatus()}); 
                flag = 1;
            }
            if (flag != 1) {
                JOptionPane.showMessageDialog(null, "Không có mã khuyến mãi cần tìm!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btntimMaKMActionPerformed

    private void btn_Timvl_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Timvl_tonActionPerformed
        String makho = (String) cbb_makhovl_ton.getSelectedItem();
        String mavl = (String) cbb_mavl_ton.getSelectedItem();
        String tenvl = (String) cbb_tenvl_ton.getSelectedItem();
        Integer soluong ;
        if(text_soluongvl_ton.getText().equalsIgnoreCase("")){
            soluong = null;
        }else{
           soluong = Integer.parseInt(text_soluongvl_ton.getText());
        }
        try {
            tableModel7.setRowCount(0);
            Inventory_Resources rcs = new Inventory_Resources(makho, mavl, tenvl, soluong);
            in_resourcesList = Controller_Inventory_Resources.find_inven_Rc(rcs);
            int flag = 0;
            for (Inventory_Resources rc : in_resourcesList) {
                tableModel7.addRow(new Object[]{tableModel7.getRowCount() + 1, rc.getStockId(), rc.getReId(), rc.getName(), rc.getNum_inventory_re()});
            flag = 1;
            }
            if (flag != 1) {
                JOptionPane.showMessageDialog(null, "Không có sản phẩm cần tìm!");
            }

        } catch (SQLException e) {
            int code = e.getErrorCode();
            String msg = e.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_Timvl_tonActionPerformed

    private void btn_Themvl_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Themvl_tonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        boolean check = false;
        String makho = (String) cbb_makhovl_ton.getSelectedItem();
        String mavl = (String) cbb_mavl_ton.getSelectedItem();
        Integer soluong = 0;
        if (text_soluongvl_ton.getText().equalsIgnoreCase("0") || text_soluongvl_ton.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "số lượng không thể để trống hoặc bằng 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                soluong = Integer.parseInt(text_soluongvl_ton.getText());
                Inventory_Resources inre = new Inventory_Resources(makho, mavl, "", soluong);
                check = Controller_Inventory_Resources.insert(inre);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công.");
                    showInvenResources();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                reset_in_resources();
            } catch (SQLException e) {
                int code = e.getErrorCode();
                String msg = e.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btn_Themvl_tonActionPerformed

    private void btn_Xoavl_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Xoavl_tonActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String mavl = (String) cbb_mavl_ton.getSelectedItem();
        String makho = (String) cbb_makhovl_ton.getSelectedItem();
        try {
            boolean check = Controller_Inventory_Resources.delete(makho, mavl);
            if (check == true) {
                JOptionPane.showMessageDialog(this, "Xóa thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa không thành công",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            reset_in_resources();

        } catch (SQLException e) {
            int code = e.getErrorCode();
            String msg = e.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_Xoavl_tonActionPerformed

    private void btn_Suavl_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Suavl_tonActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String makho = (String) cbb_makhovl_ton.getSelectedItem();
        String mavl = (String) cbb_mavl_ton.getSelectedItem();
        Integer soluong = 0;
        if (text_soluongvl_ton.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "số lượng không thể để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                soluong = Integer.parseInt(text_soluongvl_ton.getText());
                Inventory_Resources inre = new Inventory_Resources(makho, mavl, "", soluong);
                check = Controller_Inventory_Resources.update(inre);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    showInvenResources();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa không thành công",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                reset_in_resources();
            } catch (SQLException e) {
                int code = e.getErrorCode();
                String msg = e.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btn_Suavl_tonActionPerformed

    private void btn_datlai_nvl_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlai_nvl_tonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            reset_in_resources();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_datlai_nvl_tonActionPerformed

    private void btn_Tim_nongsan_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Tim_nongsan_tonActionPerformed
        String tensp = (String) cbb_ton_tenns.getSelectedItem();
        String makho = (String) cbb_ton_makho_ns.getSelectedItem();
        String mans = (String) cbb_ton_mans.getSelectedItem();
        Integer soluong ;
        if(txt_ton_soluong_ns.getText().equalsIgnoreCase("")){
            soluong = null;
        }else{
           soluong = Integer.parseInt(txt_ton_soluong_ns.getText());
        }
        
        try {
            tableModel11.setRowCount(0);
            Inventory_Product pr = new Inventory_Product(makho, mans, tensp, soluong);
            in_productList = Controller_Inventory_Product.find_inven_pro(pr);
            int flag = 0;
            for (Inventory_Product pro : in_productList) {
                tableModel11.addRow(new Object[]{tableModel11.getRowCount() + 1, pro.getStockId(), pro.getProId(), pro.getName(), pro.getNum_inventory_pro()});
            flag = 1;
            }
            if (flag != 1) {
                JOptionPane.showMessageDialog(null, "Không có sản phẩm cần tìm!");
            }

        } catch (SQLException e) {
            int code = e.getErrorCode();
            String msg = e.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_Tim_nongsan_tonActionPerformed

    private void btn_them_nongsan_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them_nongsan_tonActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String makho = (String) cbb_ton_makho_ns.getSelectedItem();
        String mans = (String) cbb_ton_mans.getSelectedItem();
        Integer soluong = 0;
        if (txt_ton_soluong_ns.getText().equalsIgnoreCase("0") || txt_ton_soluong_ns.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "số lượng không thể để trống hoặc bằng 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                soluong = Integer.parseInt(txt_ton_soluong_ns.getText());
                Inventory_Product pro = new Inventory_Product(makho, mans, "", soluong);
                check = Controller_Inventory_Product.insert(pro);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Thêm thành công.");
                    showInvenResources();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm không thành công",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                reset_in_product();
            } catch (SQLException e) {
                int code = e.getErrorCode();
                String msg = e.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btn_them_nongsan_tonActionPerformed

    private void btn_xoa_nongsan_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoa_nongsan_tonActionPerformed
        // TODO add your handling code here:
        String masp = (String) cbb_ton_mans.getSelectedItem();
        String makho = (String) cbb_ton_makho_ns.getSelectedItem();
        try {
            boolean check = Controller_Inventory_Product.delete(makho, masp);
            if (check == true) {
                JOptionPane.showMessageDialog(this, "Xóa thành công.");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa không thành công",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            reset_in_product();

        } catch (SQLException e) {
            int code = e.getErrorCode();
            String msg = e.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_xoa_nongsan_tonActionPerformed

    private void btn_sua_nongsan_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sua_nongsan_tonActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String masp = (String) cbb_ton_mans.getSelectedItem();
        String makho = (String) cbb_ton_makho_ns.getSelectedItem();
        Integer soluong = 0;
        if (txt_ton_soluong_ns.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "số lượng không thể để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                soluong = Integer.parseInt(txt_ton_soluong_ns.getText());
                Inventory_Product pro = new Inventory_Product(makho, masp, "", soluong);
                check = Controller_Inventory_Product.update(pro);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công.");
                    showInvenResources();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa không thành công",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                reset_in_product();
            } catch (SQLException e) {
                int code = e.getErrorCode();
                String msg = e.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btn_sua_nongsan_tonActionPerformed

    private void btn_datlai_nongsan_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datlai_nongsan_tonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            reset_in_product();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_datlai_nongsan_tonActionPerformed

    //tim kho
    private void btntimKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimKhoActionPerformed
        // TODO add your handling code here:
         try {
            tableModel8.setRowCount(0);
            int tmp1 = boxTrangThai.getSelectedIndex();
            int tmp2 = boxLoai.getSelectedIndex();
            String st_stockid = textMaKho.getText();

            String s_stt;
            String s_type;

            if (tmp1 == -1) {
                s_stt = null;
            } else if (tmp1 == 0) {
                s_stt = "0";
            } else {
                s_stt = "1";
            }

            if (tmp2 == -1) {
                s_type = null;
            } else if (tmp2 == 0) {
                s_type = "1";
            } else {
                s_type = "2";
            }

            stockList = Controller_Stock.Search_Stock(st_stockid, s_stt, s_type);
            int flag = 0;

            for (Stock st : stockList) {

                String trangthai, loaikho;

                if (st.getStatusStock() == 1) {
                    trangthai = "Chưa đầy";
                } else {
                    trangthai = "Đầy";
                }

                if (st.getType() == 1) {
                    loaikho = "Sản phẩm";
                } else {
                    loaikho = "Vật liệu";
                }
                tableModel8.addRow(new Object[]{tableModel8.getRowCount() + 1, st.getStockID(),
                    trangthai, loaikho});
                flag = 1;
            }
            if (flag != 1) {
                JOptionPane.showMessageDialog(null, "Không có kho cần tìm!");
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
        } 
    }//GEN-LAST:event_btntimKhoActionPerformed

    private void textOrd_Ex_NumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textOrd_Ex_NumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textOrd_Ex_NumActionPerformed

    //them kho
    private void btnthemKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemKhoActionPerformed
        String statusStock = (String) boxTrangThai.getSelectedItem();
        String typeStock = (String) boxLoai.getSelectedItem();
        String idStock = textMaKho.getText();
        Integer s1;
        Integer s2;

        if (statusStock.equals("Đầy")) {
            s1 = 0;
        } else {
            s1 = 1;
        }

        if (typeStock.equals("Sản phẩm")) {
            s2 = 1;
        } else {
            s2 = 2;
        }
        boolean check = false;
        if (!idStock.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Thêm kho mới không cần nhập mã kho!");
        } else {
            try {
                Stock st = new Stock("", s1, s2);
                check = Controller_Stock.insert(st);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Thêm kho thành công!");
                    showStock();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm kho thất bại!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnthemKhoActionPerformed

    //xoa kho
    private void btnxoaKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaKhoActionPerformed
        Object selected = boxTrangThai.getSelectedItem();
        Object selected2 = boxLoai.getSelectedItem();

        String idStock = textMaKho.getText();
        Integer statusStock;//= Integer.parseInt(selected.toString());
        Integer typeStock; //= Integer.parseInt(selected2.toString());

        if (selected == "Chưa đầy") {
            statusStock = 1;
        } else {
            statusStock = 0;
        }

        if (selected2 == "Sản phẩm") {
            typeStock = 1;
        } else {
            typeStock = 2;
        }
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có muốn xóa không?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                boolean delKho = Controller_Stock.delete(idStock);
                if (delKho == true) {
                    JOptionPane.showMessageDialog(this, "Xóa kho thành công!");
                    showStock();
                    reset_stock();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa kho thất bại!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    showStock();
                    reset_stock();
                }
            }
        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnxoaKhoActionPerformed

    //sua kho
    private void btnsuaKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaKhoActionPerformed
        String selected = (String) boxTrangThai.getSelectedItem();
        String selected2 = (String) boxLoai.getSelectedItem();

        String idStock = textMaKho.getText();
        Integer statusStock;//= Integer.parseInt(selected.toString());
        Integer typeStock; //= Integer.parseInt(selected2.toString());

        if (selected == "Chưa Đầy") {
            statusStock = 1;
        } else {
            statusStock = 0;
        }

        if (selected2 == "Sản phẩm") {
            typeStock = 1;
        } else {
            typeStock = 2;
        }

        boolean check = false;

        try {
            Stock st = new Stock(idStock, statusStock, typeStock);
            check = Controller_Stock.update(st);
            if (check == true) {
                JOptionPane.showMessageDialog(this, "Sửa kho thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa kho thất bại!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            showStock();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsuaKhoActionPerformed

    //reset kho
    private void btn_resetStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetStockActionPerformed
        try {
            showStock();
            reset_stock();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_resetStockActionPerformed

    //mouse press cua kho
    private void jTable8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MousePressed
        int selectedIndex = jTable8.getSelectedRow();
        if (selectedIndex >= 0) {
            Stock st = stockList.get(selectedIndex);
            int stt;
            int type;
            if (st.getStatusStock() == 1) {
                stt = 1;
            } else {
                stt = 0;
            }
            if (st.getType() == 1) {
                type = 0;
            } else {
                type = 1;
            }
            textMaKho.setText(st.getStockID());
            boxTrangThai.setSelectedIndex(stt);
            boxLoai.setSelectedIndex(type);
        }
    }//GEN-LAST:event_jTable8MousePressed

    //mouse press cua nhan vien
    private void jTable9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable9MousePressed
        int selectedIndex = jTable9.getSelectedRow();
        if (selectedIndex >= 0) {
            Employee emp = employeeList.get(selectedIndex);

            String farm_id = emp.getFarmID();
            int stt = Integer.parseInt(farm_id.substring(1));

            textmaNV.setText(emp.getEmpID());
            boxFarmID.setSelectedIndex(stt - 1);

            texttenNV.setText(emp.getEmpName());
            texttenNV.setText(emp.getEmpName());
            textdiaChiNV.setText(emp.getEmpAdd());
            textsdtNV.setText(emp.getEmpPhone());
            textemailNV.setText(emp.getEmpEmail());
            txtStartDate.setDateFormatString("dd/MM/yyyy");
            txtStartDate.setDate(emp.getStartDate());

            boxUserRole.setSelectedIndex(-1);
            textuserName.setText("");
            textpassWord.setText("");
        }
    }//GEN-LAST:event_jTable9MousePressed

    //tim nhan vien
    private void btntimNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimNhanVienActionPerformed
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            tableModel9.setRowCount(0);
            String e_emid = textmaNV.getText();
            String e_farmid;
            String e_empname = texttenNV.getText();
            String e_empadd = textdiaChiNV.getText();
            String e_empphone = textsdtNV.getText();
            String e_empemail = textemailNV.getText();
            String e_userrole ;
            String e_username = textuserName.getText();
            
            if(boxFarmID.getSelectedItem() == null){
                e_farmid = "";
            }
            else {
                e_farmid = boxFarmID.getSelectedItem().toString();
            }
            
            if(boxUserRole.getSelectedItem() == null){
                e_userrole = "";
            }
            else {
                if(boxUserRole.getSelectedIndex() == 0)
                    e_userrole = "UR1";
                else {
                    e_userrole = "UR2";
                }
            }
            
            employeeList = Controller_Employee.Search_Employee(e_emid,e_farmid,e_empname,
            e_empadd,e_empphone,e_empemail, e_userrole, e_username);  
            int flag = 0;
            for (Employee emp : employeeList) {
                tableModel9.addRow(new Object[]{tableModel9.getRowCount() + 1, emp.getEmpID(),
                    emp.getFarmID(), emp.getEmpName(), emp.getEmpAdd(), emp.getEmpPhone()
                , emp.getEmpEmail(), f.format(emp.getStartDate()), emp.getUserID()});
                flag = 1;
            }
            if(flag != 1){
                JOptionPane.showMessageDialog(null, "Không có nhân viên cần tìm!");
            }
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
        }
    }//GEN-LAST:event_btntimNhanVienActionPerformed

    //them nhan vien
    private void btnthemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemNhanVienActionPerformed
        String UserName = textuserName.getText();
        String UserPassword = textpassWord.getText();
        String UserRole = (String) boxUserRole.getSelectedItem();
        String FarmID = (String) boxFarmID.getSelectedItem();

        String EmpID = textmaNV.getText();
        String EmpName = texttenNV.getText();
        String EmpAdd = textdiaChiNV.getText();
        String EmpPhone = textsdtNV.getText();
        String EmpEmail = textemailNV.getText();

        java.util.Date utilStartDate = txtStartDate.getDate();
        java.sql.Date start_date = new java.sql.Date(utilStartDate.getTime());

        String urole;
        if (UserRole.equals("Quản Lý")) {
            urole = "UR1";
        } else {
            urole = "UR2";
        }

        boolean check = false;
        if (UserName.trim().equals("") || UserPassword.trim().equals("") || UserRole.trim().equals("")
                || FarmID.trim().equals("") || EmpName.trim().equals("") || EmpAdd.trim().equals("")
                || EmpPhone.trim().equals("") || EmpEmail.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Thông tin không được để trống ");
        }
        if (!EmpID.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên mới không cần nhập mã nhân viên!");
        } else {
            try {
                User us = new User("", UserName, UserPassword, "", urole);
                Employee emp = new Employee("", FarmID, EmpName, EmpAdd, EmpPhone, EmpEmail, start_date, "");
                check = Controller_Employee.insert(us, emp);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                    showEmployee();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                int code = ex.getErrorCode();
                String msg = ex.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnthemNhanVienActionPerformed

    //xoa nhan vien
    private void btnxoaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaNhanVienActionPerformed
        String EmpID = textmaNV.getText();
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có muốn xóa không?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                boolean delEmp = Controller_Employee.delete(EmpID);
                if (delEmp == true) {
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                    showEmployee();
                    reset_Employee();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    showEmployee();
                    reset_Employee();
                }
            }
        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoaNhanVienActionPerformed

    //sua nhan vien
    private void btnsuaNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaNhanVienActionPerformed
        String FarmID = (String) boxFarmID.getSelectedItem();
        String EmpName = texttenNV.getText();
        String EmpID = textmaNV.getText();
        String EmpAdd = textdiaChiNV.getText();
        String EmpPhone = textsdtNV.getText();
        String EmpEmail = textemailNV.getText();
        java.util.Date utilStartDate = txtStartDate.getDate();
        java.sql.Date start_date = new java.sql.Date(utilStartDate.getTime());

        boolean check = false;

        try {
            Employee emp = new Employee(EmpID, FarmID, EmpName, EmpAdd, EmpPhone, EmpEmail, start_date, "");
            check = Controller_Employee.update(emp);
            if (check == true) {
                JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa nhân viên thất bại!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            showEmployee();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnsuaNhanVienActionPerformed

    //button reset cua nhan vien
    private void btnResetNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetNVActionPerformed
        try {
            showEmployee();
            reset_Employee();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnResetNVActionPerformed

    //mouse pressed cua bang quan ly Transport
    private void jTable10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable10MousePressed
        int selectedIndex = jTable10.getSelectedRow();
        if (selectedIndex >= 0) {
            Transport tr = transportList.get(selectedIndex);
            int stt;

            if (tr.getStatusTrans() == 2) {
                stt = 2;
            } else if (tr.getStatusTrans() == 1){
                stt = 1;
            }else{
                stt = 0;
            }
            textTransID.setText(tr.getTransID());
            boxStatusTrans.setSelectedIndex(stt);

            textOrd_Ex_Num.setText(tr.getOrd_Ex_Num());
        }
    }//GEN-LAST:event_jTable10MousePressed

    //cap nhat van chuyen
    private void btnUpdateTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTransActionPerformed
        String selected = (String) boxStatusTrans.getSelectedItem();
        String idTrans = textTransID.getText();
        String idOdr_Ex = textOrd_Ex_Num.getText();
        Integer statusTrans;//= Integer.parseInt(selected.toString());

        if (selected == "Thành công") {
            statusTrans = 1;
        } else {
            statusTrans = 0;
        }
        boolean check = false;
        try {
            Transport tr = new Transport(idTrans, idOdr_Ex, statusTrans);
            check = Controller_Transport.update(tr);
            if (check == true) {
                JOptionPane.showMessageDialog(this, "Sửa vận chuyển thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Sửa vận chuyển thất bại!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            showTransport();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateTransActionPerformed

    //tim kiem van chuyen
    private void btnSearchTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchTransActionPerformed
        try {
            tableModel10.setRowCount(0);
            String maVC = textTransID.getText();
            String maHD = textOrd_Ex_Num.getText();
            String status = "";
            int viTri;
            int flag = 0;

            viTri = boxStatusTrans.getSelectedIndex();
            switch (viTri) {
                case 0:
                    status = "0";
                    break;
                case 1:
                    status = "1";
                    break;
                case 2:
                    status = "2";
                    break;
                case -1:
                    status = null;
                    break;
            }

            transportList = Controller_Transport.searchTrans(maVC, maHD, status);
            String stt;

            for (Transport ts : transportList) {
                switch (ts.getStatusTrans()) {
                    case 1:
                        stt = "Thành công";
                        break;
                    case 0:
                        stt = "Thất bại";
                        break;
                    default:
                        stt = "Ðang giao hàng";
                        break;
                }

                tableModel10.addRow(new Object[]{tableModel10.getRowCount() + 1, ts.getTransID(),
                    ts.getOrd_Ex_Num(), stt});
                flag = 1;
            }

            if (flag == 0) {
                JOptionPane.showMessageDialog(this, "Không có vận chuyển cần tìm");
            }

        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
        }

    }//GEN-LAST:event_btnSearchTransActionPerformed

    //button reset van chuyen
    private void btnResetTransportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTransportActionPerformed
        try {
            showTransport();
            reset_transport();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnResetTransportActionPerformed

    private void btnxoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaSPActionPerformed
        // TODO add your handling code here:
        String id = textmaSP.getText();
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Ban co muon xoa?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                Controller_Product.delete(id);
                showProduct();
                reset_product();
            }

        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoaSPActionPerformed

    private void btnsuaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaSPActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String id = textmaSP.getText();
        String tenns = texttenSP.getText();
        String mant = (String) cbb_ma_nong_trai_sp.getSelectedItem();
        String loains = (String) cbb_loai_sp.getSelectedItem();
        String anh = text_link_anh.getText();
        Integer giasp = 0;

        if (!textGia_nong_san.getText().equalsIgnoreCase("")) {
            giasp = Integer.parseInt(textGia_nong_san.getText());
        }
        if (tenns.equals("") || mant == null || loains == null || anh.equals("") || giasp == 0) {
            JOptionPane.showMessageDialog(this, "Hay dien day du thong tin");
        } else {
            try {
                Product pro = new Product(id, tenns, giasp, mant, loains, anh, 0);
                check = Controller_Product.update(pro);
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Đã sửa thành công.");
                    showResources();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa không thành công",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                reset_product();
            } catch (SQLException ex) {
                int code = ex.getErrorCode();
                String msg = ex.getMessage();
                JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnsuaSPActionPerformed

    private void btn_reset_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reset_productActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            reset_product();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_reset_productActionPerformed

    private void btn_them_anh_nsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them_anh_nsActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Hình", "jpg", "png", "jpeg");
            fileChooser.setFileFilter(imageFilter);
            fileChooser.setMultiSelectionEnabled(false);
            int x = fileChooser.showDialog(this, "Chọn file");
            if (x == JFileChooser.APPROVE_OPTION) {
                File file_in = fileChooser.getSelectedFile();
                String path = file_in.getAbsolutePath();

                File inStream = new File(path);

                File file_out = new File("");
                text_link_anh.setText(inStream.getName());
                File outStream = new File(file_out.getAbsolutePath() + "/src/Resources/" + inStream.getName());

                Files.copy(inStream.toPath(), outStream.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_them_anh_nsActionPerformed

    private void table_ds_nguyenVLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ds_nguyenVLMouseClicked
        // TODO add your handling code here:
        int selectedIndex = table_ds_nguyenVL.getSelectedRow();
        if (selectedIndex >= 0) {
            Resources rc = resourcesList.get(selectedIndex);
            textMaVL.setText(rc.getReID());
            textMaVL.setEditable(false);
            texttenVL.setText(rc.getResourcesName());
            cbxloaiVL.setSelectedItem(rc.getUnit());
            textgiaVL.setText(String.valueOf(rc.getRePrice()));
        }
    }//GEN-LAST:event_table_ds_nguyenVLMouseClicked

    private void btntimVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimVLActionPerformed
        // TODO add your handling code here:
        try {
            //List<Resources> resourcesList = Controller_Resource.findAll();
            tableModel5.setRowCount(0);
            String loaiVL;
            int flag = 0;
            String maVL = textMaVL.getText();
            String tenVL = texttenVL.getText();
            String giaVL = textgiaVL.getText();
            
            if (cbxloaiVL.getSelectedItem() == null)
                loaiVL = "";
            else
                loaiVL = cbxloaiVL.getSelectedItem().toString();
            
            resourcesList = Controller_Resource.searchResources(maVL, tenVL, loaiVL, giaVL);

            for (Resources rcs : resourcesList) {
                flag = 1;
                tableModel5.addRow(new Object[]{tableModel5.getRowCount() + 1, rcs.getReID(),
                    rcs.getResourcesName(), rcs.getRePrice(), rcs.getQuantity(), rcs.getUnit()});
            }
            
            if (flag == 0) {
                JOptionPane.showMessageDialog(this, "Không có nguyên vật liệu cần tìm");
            }
            
        } catch (ClassNotFoundException | NumberFormatException | SQLException e) {
        }
    }//GEN-LAST:event_btntimVLActionPerformed

    private void btn_reset_nguyen_vatlieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reset_nguyen_vatlieuActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            showResources();
            reset_resources();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_reset_nguyen_vatlieuActionPerformed

    private void btntonKhovlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntonKhovlActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            cardlayout.show(jpnCardLayout, "jpnListTonSP");
            showInvenResources();
            reset_in_resources();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btntonKhovlActionPerformed

    private void table_ds_inven_reMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ds_inven_reMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        int selectedIndex = table_ds_inven_re.getSelectedRow();
        if (selectedIndex >= 0) {
            Inventory_Resources rc = in_resourcesList.get(selectedIndex);
            cbb_makhovl_ton.setSelectedItem(rc.getStockId());
            cbb_mavl_ton.setSelectedItem(rc.getReId());
            cbb_tenvl_ton.setSelectedItem(rc.getName());
            text_soluongvl_ton.setText(String.valueOf(rc.getNum_inventory_re()));
            cbb_makhovl_ton.setEnabled(false);
            cbb_mavl_ton.setEnabled(false);
        }
    }//GEN-LAST:event_table_ds_inven_reMouseClicked

    private void cbb_mavl_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_mavl_tonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_mavl_tonActionPerformed

    private void btntonSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntonSanPhamActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            cardlayout.show(jpnCardLayout, "jpnListNVL");

            reset_in_product();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btntonSanPhamActionPerformed

    private void table_ds_ton_nsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ds_ton_nsMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        int selectedIndex = table_ds_ton_ns.getSelectedRow();
        if (selectedIndex >= 0) {
            Inventory_Product pro = in_productList.get(selectedIndex);
            cbb_ton_makho_ns.setSelectedItem(pro.getStockId());
            cbb_ton_mans.setSelectedItem(pro.getProId());
            cbb_ton_tenns.setSelectedItem(pro.getName());
            txt_ton_soluong_ns.setText(String.valueOf(pro.getNum_inventory_pro()));
            cbb_ton_makho_ns.setEnabled(false);
            cbb_ton_mans.setEnabled(false);
        }
    }//GEN-LAST:event_table_ds_ton_nsMouseClicked

    private void cbb_ton_mansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_ton_mansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_ton_mansActionPerformed

    private void btnTaoHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonMoiActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        try {
            new ChooseProductPage(id_login).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTaoHoaDonMoiActionPerformed

    private void btnTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHoaDonActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy"); 
        try {
            // TODO add your handling code here:
            ArrayList<Order_Export> arrLInvoice = new ArrayList<>();
            String data = txtFTimHoaDon.getText();
            int index = jComboBoxTimHD.getSelectedIndex();
            switch (index) {
                case 0:
                    arrLInvoice = C_AdminHome.searchOrderExport(data, null, null);
                    break;
                case 1:
                    arrLInvoice = C_AdminHome.searchOrderExport(null, data, null);
                    break;
                case 2:
                    if (data.equalsIgnoreCase("Thanh cong"))
                        data = "1";
                    else if (data.equalsIgnoreCase("That bai"))
                        data = "0";
                    else 
                        data = "2";
                    
                    arrLInvoice = C_AdminHome.searchOrderExport(null, null, data);
                    break;
            }

            // load du lieu tim kiem vao bang
            tableModelHD.setRowCount(0);
           
            arrLInvoice.forEach((oe) -> {
                String trangThaiVanChuyen;
                if (oe.getTransID() != null) {
                    switch (oe.getStatusTrans()) {
                        case 0:
                            trangThaiVanChuyen = "Thất bại";
                            break;
                        case 1:
                            trangThaiVanChuyen = "Thành công";
                            break;
                        default:
                            trangThaiVanChuyen = "Đang giao hàng";
                            break;
                    }
                }else{
                    trangThaiVanChuyen = "";
                }
                
                tableModelHD.addRow(new Object[]{tableModelHD.getRowCount() + 1, oe.getOrd_Ex_Num(), oe.getCusID(), f.format(oe.getDateOrdered()), oe.getTransID(),
                    oe.getPreTotal(), oe.getDisID(), oe.getOrderTotal(), trangThaiVanChuyen});
            });
            
            if (tableModelHD.getRowCount() == 0) 
                JOptionPane.showMessageDialog(this, "Không có hóa đơn cần tìm");

        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnTimHoaDonActionPerformed

    private void btnResetTblListHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTblListHoaDonActionPerformed
        // TODO add your handling code here:
        txtFTimHoaDon.setText("");

        // xoa du lieu bang
        showOrderExport();
    }//GEN-LAST:event_btnResetTblListHoaDonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            String year = (String) cbb_thong_ke_nam.getSelectedItem();
            String month = (String) cbb_thong_ke_thang.getSelectedItem();
            Integer thongke = cbb_thong_ke.getSelectedIndex();
            Controller_Statistical controller = new Controller_Statistical();
            switch (thongke) {
                case 0:
                    controller.setDataToChart_year(thongkedoanhthu);
                    break;
                case 1:
                    controller.setDataToChart_month(thongkedoanhthu, year);
                    break;
                case 2:
                    controller.setDataToChart_day(thongkedoanhthu, year, month);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbb_thong_ke_thangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_thong_ke_thangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_thong_ke_thangActionPerformed

    private void cbb_thong_keActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_thong_keActionPerformed
        // TODO add your handling code here:
        Integer thongke = cbb_thong_ke.getSelectedIndex();
        switch (thongke) {
            case 0:
                cbb_thong_ke_nam.setEnabled(false);
                cbb_thong_ke_thang.setEnabled(false);
                break;
            case 1:
                cbb_thong_ke_nam.setEnabled(true);
                cbb_thong_ke_thang.setEnabled(false);
                break;
            case 2:
                cbb_thong_ke_nam.setEnabled(true);
                cbb_thong_ke_thang.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_cbb_thong_keActionPerformed

    private void cbb_thong_ke_spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_thong_ke_spActionPerformed
        // TODO add your handling code here:
        Integer thongke = cbb_thong_ke_sp.getSelectedIndex();
        switch (thongke) {
            case 0:
                cbb_thong_ke_sp_nam.setEnabled(true);
                cbb_thong_ke_sp_thang.setEnabled(false);
                break;
            case 1:
                cbb_thong_ke_sp_nam.setEnabled(true);
                cbb_thong_ke_sp_thang.setEnabled(true);
                break;
        }

    }//GEN-LAST:event_cbb_thong_ke_spActionPerformed

    private void cbb_thong_ke_sp_thangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_thong_ke_sp_thangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_thong_ke_sp_thangActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            Integer thongke = cbb_thong_ke_sp.getSelectedIndex();
            String nam = (String) cbb_thong_ke_sp_nam.getSelectedItem();
            String thang = (String) cbb_thong_ke_sp_thang.getSelectedItem();
            String top = (String) cbb_top_sp.getSelectedItem();
            Controller_Statistical controller = new Controller_Statistical();
            switch (thongke) {
                case 0:
                    controller.setDataToChart_product_year(top_product, nam, top);
                    break;
                case 1:
                    controller.setDataToChart_product_month(top_product, nam, thang, top);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void cbb_thong_ke_sp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_thong_ke_sp1ActionPerformed
        // TODO add your handling code here:
        Integer thongke = cbb_thong_ke_sp1.getSelectedIndex();
        switch (thongke) {
            case 0:
                cbb_thong_ke_sp_nam1.setEnabled(true);
                cbb_thong_ke_sp_thang1.setEnabled(false);
                break;
            case 1:
                cbb_thong_ke_sp_nam1.setEnabled(true);
                cbb_thong_ke_sp_thang1.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_cbb_thong_ke_sp1ActionPerformed

    private void cbb_thong_ke_sp_thang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_thong_ke_sp_thang1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_thong_ke_sp_thang1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {
            Integer thongke = cbb_thong_ke_sp1.getSelectedIndex();
            String nam = (String) cbb_thong_ke_sp_nam1.getSelectedItem();
            String thang = (String) cbb_thong_ke_sp_thang1.getSelectedItem();
            String top = (String) cbb_top_sp1.getSelectedItem();
            Controller_Statistical controller = new Controller_Statistical();
            switch (thongke) {
                case 0:
                    controller.setDataToChart_cus_year(top_cus, nam, top);
                    break;
                case 1:
                    controller.setDataToChart_cus_month(top_cus, nam, thang, top);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void texttenNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttenNTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texttenNTActionPerformed

    private void texttenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texttenNVActionPerformed

    private void textGia_nong_sanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textGia_nong_sanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textGia_nong_sanActionPerformed

    private void texttenNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttenNCCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texttenNCCActionPerformed

    private void boxFarmIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxFarmIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxFarmIDActionPerformed

    private void table_ds_nong_sanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ds_nong_sanMouseClicked
        // TODO add your handling code here:
        int selectedIndex = table_ds_nong_san.getSelectedRow();
        if (selectedIndex >= 0) {
            Product pro = productList.get(selectedIndex);
            textmaSP.setText(pro.getProID());
            texttenSP.setText(pro.getProName());
            cbb_ma_nong_trai_sp.setSelectedItem(pro.getFarmID());
            textGia_nong_san.setText(String.valueOf(pro.getProPrice()));
            cbb_loai_sp.setSelectedItem(pro.getProType());
            text_link_anh.setText(pro.getImage());
            textmaSP.setEditable(false);
            jLabelHinhNS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/" + pro.getImage())));
        }
    }//GEN-LAST:event_table_ds_nong_sanMouseClicked

    private void cbb_dx_card2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card2ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card2.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card2ActionPerformed

    private void cbb_dx_card1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card1ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card1.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card1ActionPerformed

    private void cbb_dx_card3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card3ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card3.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card3ActionPerformed

    private void cbb_dx_card4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card4ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card4.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card4ActionPerformed

    private void cbb_dx_card5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card5ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card5.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card5ActionPerformed

    private void cbb_dx_card6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card6ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card6.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card6ActionPerformed

    private void cbb_dx_card7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card7ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card7.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card7ActionPerformed

    private void cbb_dx_card8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card8ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card8.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card8ActionPerformed

    private void cbb_dx_card9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card9ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card9.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card9ActionPerformed

    private void cbb_dx_card10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card10ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card10.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card10ActionPerformed

    private void cbb_dx_card12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card12ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card12.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card12ActionPerformed

    private void cbb_dx_card13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_dx_card13ActionPerformed
        // TODO add your handling code here:
        if (cbb_dx_card13.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_cbb_dx_card13ActionPerformed

    private void date_start_disAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_date_start_disAncestorAdded
        // TODO add your handling code here:
        date_start_dis.setDateFormatString("dd/MM/yyyy");
        date_end_dis.setDateFormatString("dd/MM/yyyy");
    }//GEN-LAST:event_date_start_disAncestorAdded

    private void date_start_disMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_start_disMouseClicked
        // TODO add your handling code here:
        date_start_dis.setDateFormatString("dd/MM/yyyy");
        date_end_dis.setDateFormatString("dd/MM/yyyy");
    }//GEN-LAST:event_date_start_disMouseClicked

    private void date_start_disMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_start_disMousePressed
        // TODO add your handling code here:
        date_start_dis.setDateFormatString("dd/MM/yyyy");
        date_end_dis.setDateFormatString("dd/MM/yyyy");
    }//GEN-LAST:event_date_start_disMousePressed

    private void btnTaoMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMaActionPerformed
        // TODO add your handling code here:
        RandomStringGenerator rand = new RandomStringGenerator();
        String code = rand.randomAlphaNumeric(5);
        //String maKM=textmaKM.getText();
        String maCode = textmaCode.getText();
        //if (textgiaTriKM.getText()) {
            if (cbxloaiMaKM.getSelectedItem().equals("Diamond")) {
                textmaCode.setText("D" + code + textgiaTriKM.getText());
            }
            if (cbxloaiMaKM.getSelectedItem().equals("Gold")) {
                textmaCode.setText("G" + code + textgiaTriKM.getText());
            }
            if (cbxloaiMaKM.getSelectedItem().equals("Silver")) {
                textmaCode.setText("S" + code + textgiaTriKM.getText());
            }
    }//GEN-LAST:event_btnTaoMaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         try {
            Integer thongke = cbb_thong_ke_sp.getSelectedIndex();
            String nam = (String) cbb_thong_ke_sp_nam.getSelectedItem();
            String thang = (String) cbb_thong_ke_sp_thang.getSelectedItem();
            String top = (String) cbb_top_sp.getSelectedItem();
            Controller_Statistical controller = new Controller_Statistical();
            switch (thongke) {
                case 0:
                    controller.setData_report_pro_year(nam, top);
                    break;
                case 1:
                    controller.setData_report_pro_month(nam, thang, top);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            Integer thongke = cbb_thong_ke_sp1.getSelectedIndex();
            String nam = (String) cbb_thong_ke_sp_nam1.getSelectedItem();
            String thang = (String) cbb_thong_ke_sp_thang1.getSelectedItem();
            String top = (String) cbb_top_sp1.getSelectedItem();
            Controller_Statistical controller = new Controller_Statistical();
            switch (thongke) {
                case 0:
                    controller.setData_report_cus_year(nam, top);
                    break;
                case 1:
                    controller.setData_report_cus_month( nam, thang, top);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnXuatThongKeDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatThongKeDoanhThuActionPerformed
        // TODO add your handling code here:
                 try {
            Integer thongke = cbb_thong_ke.getSelectedIndex();
            String nam = (String) cbb_thong_ke_nam.getSelectedItem();
            String thang = (String) cbb_thong_ke_thang.getSelectedItem();
            Controller_Statistical controller = new Controller_Statistical();
            switch (thongke) {
                case 0:
                    controller.setData_report_revenue_year();
                    break;
                case 1:
                    controller.setData_report_revenue_month(nam);
                    break;
                case 2:
                    controller.setData_report_revenue_day(thang, nam);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXuatThongKeDoanhThuActionPerformed

    private void jComboBoxTimHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTimHDActionPerformed
        // TODO add your handling code here:
        int index = jComboBoxTimHD.getSelectedIndex();
        switch (index) {
            case 0:
                txtFTimHoaDon.setText("Nhap ma hoa don can tim");
                break;
            case 1:
                txtFTimHoaDon.setText("Nhap ma khach hang");
                break;
            case 2:
                txtFTimHoaDon.setText("Nhap trang thai van chuyen: Thanh cong/That bai/ Dang giao hang");
                break;
        }
    }//GEN-LAST:event_jComboBoxTimHDActionPerformed

    private void cbb_tenvl_tonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_tenvl_tonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_tenvl_tonActionPerformed

    private void cbb_mavl_tonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_mavl_tonItemStateChanged
        // TODO add your handling code here:
                if(evt.getStateChange() == ItemEvent.SELECTED)
                {
                    try {
                        show_cbb_name_resources();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }//GEN-LAST:event_cbb_mavl_tonItemStateChanged

    private void cbb_tenvl_tonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_tenvl_tonItemStateChanged
        // TODO add your handling code here:
         if(evt.getStateChange() == ItemEvent.SELECTED)
                {
                    try {
                        show_cbb_id_resources();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }//GEN-LAST:event_cbb_tenvl_tonItemStateChanged

    private void cbb_ton_mansItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_ton_mansItemStateChanged
        // TODO add your handling code here:
         if(evt.getStateChange() == ItemEvent.SELECTED)
                {
                    try {
                        show_cbb_name_product();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }//GEN-LAST:event_cbb_ton_mansItemStateChanged

    private void cbb_ton_tennsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbb_ton_tennsItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED)
                {
                    try {
                        show_cbb_id_product();
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
    }//GEN-LAST:event_cbb_ton_tennsItemStateChanged

    private void txtFTimHoaDonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFTimHoaDonFocusGained
        // TODO add your handling code here:
        txtFTimHoaDon.setText(null);
    }//GEN-LAST:event_txtFTimHoaDonFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxFarmID;
    private javax.swing.JComboBox<String> boxLoai;
    private javax.swing.JComboBox<String> boxStatusTrans;
    private javax.swing.JComboBox<String> boxTrangThai;
    private javax.swing.JComboBox<String> boxUserRole;
    private javax.swing.JButton btnResetNV;
    private javax.swing.JButton btnResetTblListHoaDon;
    private javax.swing.JButton btnResetTransport;
    private javax.swing.JButton btnSearchTrans;
    private javax.swing.JButton btnTaoHoaDonMoi;
    private javax.swing.JButton btnTaoMa;
    private javax.swing.JButton btnTimHoaDon;
    private javax.swing.JButton btnUpdateTrans;
    private javax.swing.JButton btnXuatThongKeDoanhThu;
    private javax.swing.JButton btn_Suavl_ton;
    private javax.swing.JButton btn_Themvl_ton;
    private javax.swing.JButton btn_Tim_nongsan_ton;
    private javax.swing.JButton btn_Timvl_ton;
    private javax.swing.JButton btn_Xoavl_ton;
    private javax.swing.JButton btn_datlai_nongsan_ton;
    private javax.swing.JButton btn_datlai_nvl_ton;
    private javax.swing.JButton btn_resetStock;
    private javax.swing.JButton btn_reset_nguyen_vatlieu;
    private javax.swing.JButton btn_reset_product;
    private javax.swing.JButton btn_sua_nongsan_ton;
    private javax.swing.JButton btn_them_anh_ns;
    private javax.swing.JButton btn_them_nongsan_ton;
    private javax.swing.JButton btn_xoa_nongsan_ton;
    private javax.swing.JButton btnresetKH;
    private javax.swing.JButton btnresetKM;
    private javax.swing.JButton btnresetNCC;
    private javax.swing.JButton btnresetNT;
    private javax.swing.JButton btnsuaKho;
    private javax.swing.JButton btnsuaMaKM;
    private javax.swing.JButton btnsuaNCC;
    private javax.swing.JButton btnsuaNhanVien;
    private javax.swing.JButton btnsuaNongTrai;
    private javax.swing.JButton btnsuaSP;
    private javax.swing.JButton btnsuaVL;
    private javax.swing.JButton btnthemKho;
    private javax.swing.JButton btnthemMaKM;
    private javax.swing.JButton btnthemNCC;
    private javax.swing.JButton btnthemNhanVien;
    private javax.swing.JButton btnthemNongTrai;
    private javax.swing.JButton btnthemSP;
    private javax.swing.JButton btnthemVL;
    private javax.swing.JButton btntimKH;
    private javax.swing.JButton btntimKho;
    private javax.swing.JButton btntimMaKM;
    private javax.swing.JButton btntimNCC;
    private javax.swing.JButton btntimNhanVien;
    private javax.swing.JButton btntimNongTrai;
    private javax.swing.JButton btntimSP;
    private javax.swing.JButton btntimVL;
    private javax.swing.JButton btntonKhovl;
    private javax.swing.JButton btntonSanPham;
    private javax.swing.JButton btnxoaKho;
    private javax.swing.JButton btnxoaMaKM;
    private javax.swing.JButton btnxoaNCC;
    private javax.swing.JButton btnxoaNhanVien;
    private javax.swing.JButton btnxoaNongTrai;
    private javax.swing.JButton btnxoaSP;
    private javax.swing.JButton btnxoaVL;
    private javax.swing.JComboBox<String> cbb_dx_card1;
    private javax.swing.JComboBox<String> cbb_dx_card10;
    private javax.swing.JComboBox<String> cbb_dx_card12;
    private javax.swing.JComboBox<String> cbb_dx_card13;
    private javax.swing.JComboBox<String> cbb_dx_card2;
    private javax.swing.JComboBox<String> cbb_dx_card3;
    private javax.swing.JComboBox<String> cbb_dx_card4;
    private javax.swing.JComboBox<String> cbb_dx_card5;
    private javax.swing.JComboBox<String> cbb_dx_card6;
    private javax.swing.JComboBox<String> cbb_dx_card7;
    private javax.swing.JComboBox<String> cbb_dx_card8;
    private javax.swing.JComboBox<String> cbb_dx_card9;
    private javax.swing.JComboBox<String> cbb_loai_sp;
    private javax.swing.JComboBox<String> cbb_ma_nong_trai_sp;
    private javax.swing.JComboBox<String> cbb_makhovl_ton;
    private javax.swing.JComboBox<String> cbb_mavl_ton;
    private javax.swing.JComboBox<String> cbb_tenvl_ton;
    private javax.swing.JComboBox<String> cbb_thong_ke;
    private javax.swing.JComboBox<String> cbb_thong_ke_nam;
    private javax.swing.JComboBox<String> cbb_thong_ke_sp;
    private javax.swing.JComboBox<String> cbb_thong_ke_sp1;
    private javax.swing.JComboBox<String> cbb_thong_ke_sp_nam;
    private javax.swing.JComboBox<String> cbb_thong_ke_sp_nam1;
    private javax.swing.JComboBox<String> cbb_thong_ke_sp_thang;
    private javax.swing.JComboBox<String> cbb_thong_ke_sp_thang1;
    private javax.swing.JComboBox<String> cbb_thong_ke_thang;
    private javax.swing.JComboBox<String> cbb_ton_makho_ns;
    private javax.swing.JComboBox<String> cbb_ton_mans;
    private javax.swing.JComboBox<String> cbb_ton_tenns;
    private javax.swing.JComboBox<String> cbb_top_sp;
    private javax.swing.JComboBox<String> cbb_top_sp1;
    private javax.swing.JComboBox<String> cbxgenderKH;
    private javax.swing.JComboBox<String> cbxloaiKH;
    private javax.swing.JComboBox<String> cbxloaiMaKM;
    private javax.swing.JComboBox<String> cbxloaiVL;
    private com.toedter.calendar.JDateChooser dateBirthKH;
    private com.toedter.calendar.JDateChooser date_end_dis;
    private com.toedter.calendar.JDateChooser date_start_dis;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBoxTimHD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelHinhNS;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTable jTableListOrderExport;
    private javax.swing.JButton jbtManaCus;
    private javax.swing.JButton jbtManaDis;
    private javax.swing.JButton jbtManaEmp;
    private javax.swing.JButton jbtManaFarm;
    private javax.swing.JButton jbtManaOrder;
    private javax.swing.JButton jbtManaPro;
    private javax.swing.JButton jbtManaRe;
    private javax.swing.JButton jbtManaStatis;
    private javax.swing.JButton jbtManaStock;
    private javax.swing.JButton jbtManaSup;
    private javax.swing.JButton jbtManaTrans;
    private javax.swing.JPanel jpnCard1;
    private javax.swing.JPanel jpnCard10;
    private javax.swing.JPanel jpnCard11;
    private javax.swing.JPanel jpnCard12;
    private javax.swing.JPanel jpnCard13;
    private javax.swing.JPanel jpnCard2;
    private javax.swing.JPanel jpnCard3;
    private javax.swing.JPanel jpnCard4;
    private javax.swing.JPanel jpnCard5;
    private javax.swing.JPanel jpnCard6;
    private javax.swing.JPanel jpnCard7;
    private javax.swing.JPanel jpnCard8;
    private javax.swing.JPanel jpnCard9;
    private javax.swing.JPanel jpnCardLayout;
    private javax.swing.JTable tableKH;
    private javax.swing.JTable tableKM;
    private javax.swing.JTable tableNongTrai;
    private javax.swing.JTable tableSup;
    private javax.swing.JTable table_ds_inven_re;
    private javax.swing.JTable table_ds_nguyenVL;
    private javax.swing.JTable table_ds_nong_san;
    private javax.swing.JTable table_ds_ton_ns;
    private javax.swing.JTextField textGia_nong_san;
    private javax.swing.JTextField textMaKho;
    private javax.swing.JTextField textMaNT;
    private javax.swing.JTextField textMaVL;
    private javax.swing.JTextField textOrd_Ex_Num;
    private javax.swing.JTextField textTransID;
    private javax.swing.JTextField text_link_anh;
    private javax.swing.JTextField text_soluongvl_ton;
    private javax.swing.JTextField textdiaChiKH;
    private javax.swing.JTextField textdiaChiNCC;
    private javax.swing.JTextField textdiaChiNT;
    private javax.swing.JTextField textdiaChiNV;
    private javax.swing.JTextField textemailKH;
    private javax.swing.JTextField textemailNCC;
    private javax.swing.JTextField textemailNV;
    private javax.swing.JTextField textgiaTriKM;
    private javax.swing.JTextField textgiaVL;
    private javax.swing.JTextField textmaCode;
    private javax.swing.JTextField textmaKH;
    private javax.swing.JTextField textmaKM;
    private javax.swing.JTextField textmaNCC;
    private javax.swing.JTextField textmaNV;
    private javax.swing.JTextField textmaSP;
    private javax.swing.JTextField textmaUser;
    private javax.swing.JTextField textpassWord;
    private javax.swing.JTextField textsdtKH;
    private javax.swing.JTextField textsdtNCC;
    private javax.swing.JTextField textsdtNV;
    private javax.swing.JTextField texttenKH;
    private javax.swing.JTextField texttenNCC;
    private javax.swing.JTextField texttenNT;
    private javax.swing.JTextField texttenNV;
    private javax.swing.JTextField texttenSP;
    private javax.swing.JTextField texttenVL;
    private javax.swing.JTextField texttienTL;
    private javax.swing.JTextField textuserName;
    private javax.swing.JPanel thongkedoanhthu;
    private javax.swing.JPanel top_cus;
    private javax.swing.JPanel top_product;
    private javax.swing.JTextField txtFTimHoaDon;
    private com.toedter.calendar.JDateChooser txtStartDate;
    private javax.swing.JTextField txt_ton_soluong_ns;
    // End of variables declaration//GEN-END:variables
}
