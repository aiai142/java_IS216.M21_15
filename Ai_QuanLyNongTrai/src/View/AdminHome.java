package View;
import Model.Farm;
import Model.Supplier;
import Model.Customer;
import Model.Discount;
import Process.FarmController;
import Process.SupplierController;
import Process.CustomerController;
import Process.DiscountController;
import Process.Random_Discount;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author diuai
 */
public class AdminHome extends javax.swing.JFrame {

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

    /**
     * Creates new form AdminHome
     */
    CardLayout cardlayout;

    public AdminHome() {
        initComponents();
        cardlayout = (CardLayout) jpnCardLayout.getLayout();
        //Set table cho quan li nong trai
        tableModel = (DefaultTableModel) tableNongTrai.getModel();
        setCell(tableNongTrai, SwingConstants.CENTER);
        //Set table cho quan li nha cung cap
        tableModel1 = (DefaultTableModel) tableSup.getModel();
        setCell(tableSup, SwingConstants.CENTER);
        //Sel table cho quan li khach hang
        tableModel2 = (DefaultTableModel) tableKH.getModel();
        setCell(tableKH, SwingConstants.CENTER);
        //Set table chp quan li ma khuyen mai
        tableModel3 = (DefaultTableModel) tableKM.getModel();
        setCell(tableKM, SwingConstants.CENTER);
    }

    //Reset lai cac text field trang cua trang nong trai
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
        cbxgenderKH.setSelectedIndex(0);
        dateBirthKH.setDateFormatString("");
        textdiaChiKH.setText("");
        textsdtKH.setText("");
        textemailKH.setText("");
        cbxloaiKH.setSelectedIndex(0);
        texttienTL.setText("");
        textmaUser.setText("");
    }

    public void reset_Dis() {
        textmaKM.setText("");
        textmaCode.setText("");
        textgiaTriKM.setText("");
        cbxloaiMaKM.setSelectedIndex(0);
        date_start_dis.setDateFormatString("");
        date_end_dis.setDateFormatString("");
    }

    //Can giua cac noi dung trong bang
    public static void setCell(JTable table, int alignment) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    // Ham hien thi danh sach nong trai
    private void showFarm() throws SQLException, ClassNotFoundException {

        listFarm = FarmController.findAllFarm();
        tableModel.setRowCount(0);
        for (Farm fa : listFarm) {
            tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, fa.getFarmID(),
                fa.getFarmName(), fa.getFarmAdd()});
        }

    }

    //Hien thi danh sach nha cung cap
    private void showSup() throws SQLException, ClassNotFoundException {
        listSup = SupplierController.findAllSup();
        tableModel1.setRowCount(0);
        for (Supplier sup : listSup) {
            tableModel1.addRow(new Object[]{tableModel1.getRowCount() + 1, sup.getSupID(),
                sup.getSupName(), sup.getSupPhone(), sup.getSupAdd(), sup.getSupEmail()});
        }
    }

    //Hien thi danh sach khach hang
    private void showCus() throws SQLException, ClassNotFoundException {
        listCus = CustomerController.findAllCus();

        tableModel2.setRowCount(0);
        for (Customer cus : listCus) {
            tableModel2.addRow(new Object[]{tableModel2.getRowCount() + 1, cus.getCusID(),
                cus.getCusName(), cus.getGender(), cus.getDateOfBirth(), cus.getCusAdd(),
                cus.getCusPhone(), cus.getCusEmail(), cus.getCusType(), cus.getAccrued_Money(),
                cus.getUserID()});
        }
    }

    private void showDis() throws SQLException, ClassNotFoundException {

        listDis = DiscountController.findAllDis();
        tableModel3.setRowCount(0);
        for (Discount dis : listDis) {
            tableModel3.addRow(new Object[]{tableModel3.getRowCount() + 1, dis.getDisID(),
                dis.getDisCode(), dis.getValue(), dis.getCusType(), dis.getStartDate(), dis.getEndDate()
            });
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
        jpnCardLayout = new javax.swing.JPanel();
        jpnCard1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnthemSP = new javax.swing.JButton();
        btnxoaSP = new javax.swing.JButton();
        btnsuaSP = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tableListKH = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        textmaSP = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        texttenSP = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbxLoaiPro = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        textSL = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        textGia = new javax.swing.JTextField();
        btntimSP = new javax.swing.JButton();
        jpnCard2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
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
        jpnCard3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jpnCard4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnthemVL = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
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
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel61 = new javax.swing.JLabel();
        jpnCard5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
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
        jpnCard6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnthemKH = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableKH = new javax.swing.JTable();
        btnxoaKH = new javax.swing.JButton();
        btnsuaKH = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        textmaKH = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        texttenKH = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        cbxgenderKH = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        textsdtKH = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        textdiaChiKH = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        textemailKH = new javax.swing.JTextField();
        btntimKH = new javax.swing.JButton();
        btnresetKH = new javax.swing.JButton();
        dateBirthKH = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        textmaUser = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbxloaiKH = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        texttienTL = new javax.swing.JTextField();
        jpnCard7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnthemMaKM = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableKM = new javax.swing.JTable();
        btnxoaMaKM = new javax.swing.JButton();
        btnsuaMaKM = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        textmaKM = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        textmaCode = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        textgiaTriKM = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        cbxloaiMaKM = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        btntimMaKM = new javax.swing.JButton();
        date_start_dis = new com.toedter.calendar.JDateChooser();
        date_end_dis = new com.toedter.calendar.JDateChooser();
        jpnCard8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnthemKho = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        btnxoaKho = new javax.swing.JButton();
        btnsuaKho = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        btntimKho = new javax.swing.JButton();
        jpnCard9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnthemNhanVien = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        btnxoaNhanVien = new javax.swing.JButton();
        btnsuaNhanVien = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        textmaNV = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        textmaNT = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        texttenNV = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        textdiaChiNV = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        textsdtNV = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        textemailNV = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        textngayVL = new javax.swing.JTextField();
        btntimNhanVien = new javax.swing.JButton();
        jpnCard10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jpnCard11 = new javax.swing.JPanel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(778, 672));

        jPanel1.setBackground(new java.awt.Color(0, 179, 179));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
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
        jbtManaStatis.setText("THỐNG KÊ");
        jbtManaStatis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtManaStatis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtManaStatisActionPerformed(evt);
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
            .addComponent(jbtManaEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaTrans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jbtManaStatis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jbtManaEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtManaStatis, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanel1);

        jpnCardLayout.setBackground(new java.awt.Color(255, 255, 255));
        jpnCardLayout.setLayout(new java.awt.CardLayout());

        jpnCard1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DANH SÁCH SẢN PHẨM");

        btnthemSP.setBackground(new java.awt.Color(244, 211, 94));
        btnthemSP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnthemSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemSP.setText("Thêm");
        btnthemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemSPActionPerformed(evt);
            }
        });

        btnxoaSP.setBackground(new java.awt.Color(244, 211, 94));
        btnxoaSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaSP.setText("Xóa");

        btnsuaSP.setBackground(new java.awt.Color(244, 211, 94));
        btnsuaSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsuaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaSP.setText("Sửa");

        tableListKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableListKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Giá ", "Mã nông trại", "Loại", "Có thể bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableListKH.setRowHeight(30);
        tableListKH.setRowSelectionAllowed(false);
        tableListKH.setShowGrid(false);
        jScrollPane11.setViewportView(tableListKH);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Mã sản phẩm");

        textmaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textmaSPActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Tên sản phẩm");

        texttenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttenSPActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Loại");

        cbxLoaiPro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trái cây", "Củ quả", "Rau" }));
        cbxLoaiPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLoaiProActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Số lượng");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel25.setText("Giá");

        btntimSP.setBackground(new java.awt.Color(248, 211, 94));
        btntimSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btntimSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimSP.setText("Tìm");
        btntimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCard1Layout = new javax.swing.GroupLayout(jpnCard1);
        jpnCard1.setLayout(jpnCard1Layout);
        jpnCard1Layout.setHorizontalGroup(
            jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCard1Layout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btntimSP)
                        .addGap(18, 18, 18)
                        .addComponent(btnthemSP)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoaSP)
                        .addGap(18, 18, 18)
                        .addComponent(btnsuaSP))
                    .addGroup(jpnCard1Layout.createSequentialGroup()
                        .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnCard1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(textmaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnCard1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxLoaiPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(texttenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29)
                        .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(18, 18, 18)
                        .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textSL, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(textGia))))
                .addContainerGap(247, Short.MAX_VALUE))
            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
        );
        jpnCard1Layout.setVerticalGroup(
            jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(textmaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(textGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(texttenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(textSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxLoaiPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jpnCard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimSP)
                    .addComponent(btnthemSP)
                    .addComponent(btnxoaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsuaSP))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        jpnCardLayout.add(jpnCard1, "jpnListPro");

        jpnCard2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH NÔNG TRẠI");

        btnthemNongTrai.setBackground(new java.awt.Color(244, 211, 94));
        btnthemNongTrai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnthemNongTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemNongTrai.setText("Thêm");
        btnthemNongTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemNongTraiActionPerformed(evt);
            }
        });

        tableNongTrai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nông trại", "Tên nông trại", "Địa chỉ"
            }
        ));
        tableNongTrai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNongTraiMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableNongTraiMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tableNongTrai);

        btnxoaNongTrai.setBackground(new java.awt.Color(244, 211, 94));
        btnxoaNongTrai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoaNongTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaNongTrai.setText("Xóa");
        btnxoaNongTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaNongTraiActionPerformed(evt);
            }
        });

        btnsuaNongTrai.setBackground(new java.awt.Color(244, 211, 94));
        btnsuaNongTrai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsuaNongTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaNongTrai.setText("Sửa");
        btnsuaNongTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaNongTraiActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel26.setText("Mã nông trại");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel27.setText("Tên nông trại");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setText("Địa chỉ");

        btntimNongTrai.setBackground(new java.awt.Color(248, 211, 94));
        btntimNongTrai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btntimNongTrai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimNongTrai.setText("Tìm");
        btntimNongTrai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimNongTraiActionPerformed(evt);
            }
        });

        btnresetNT.setBackground(new java.awt.Color(248, 211, 94));
        btnresetNT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnresetNT.setText("Reset");
        btnresetNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetNTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCard2Layout = new javax.swing.GroupLayout(jpnCard2);
        jpnCard2.setLayout(jpnCard2Layout);
        jpnCard2Layout.setHorizontalGroup(
            jpnCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(jpnCard2Layout.createSequentialGroup()
                .addGap(140, 236, Short.MAX_VALUE)
                .addGroup(jpnCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpnCard2Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textdiaChiNT, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnCard2Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textMaNT, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(texttenNT, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
            .addGroup(jpnCard2Layout.createSequentialGroup()
                .addGap(347, 347, 347)
                .addComponent(btntimNongTrai)
                .addGap(18, 18, 18)
                .addComponent(btnthemNongTrai)
                .addGap(18, 18, 18)
                .addComponent(btnxoaNongTrai)
                .addGap(18, 18, 18)
                .addComponent(btnsuaNongTrai)
                .addGap(18, 18, 18)
                .addComponent(btnresetNT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCard2Layout.setVerticalGroup(
            jpnCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jpnCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(textMaNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(texttenNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textdiaChiNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jpnCard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimNongTrai)
                    .addComponent(btnthemNongTrai)
                    .addComponent(btnxoaNongTrai)
                    .addComponent(btnsuaNongTrai, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnresetNT))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(469, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard2, "jpnListFarm");

        jpnCard3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DANH SÁCH ĐƠN HÀNG");

        jButton20.setBackground(new java.awt.Color(244, 211, 94));
        jButton20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        jButton20.setText("Tạo đơn mới");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã đơn hàng", "Mã khách hàng", "Ngày tạo", "Mã vận chuyển", "Tổng tiền", "Mã giảm giá", "Thành tiền", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency.png"))); // NOI18N

        javax.swing.GroupLayout jpnCard3Layout = new javax.swing.GroupLayout(jpnCard3);
        jpnCard3.setLayout(jpnCard3Layout);
        jpnCard3Layout.setHorizontalGroup(
            jpnCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jpnCard3Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnCard3Layout.setVerticalGroup(
            jpnCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jpnCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnCard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(780, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard3, "jpnListOrder");

        jpnCard4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DANH SÁCH NGUYÊN VẬT LIỆU");

        btnthemVL.setBackground(new java.awt.Color(244, 211, 94));
        btnthemVL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnthemVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemVL.setText("Thêm");
        btnthemVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemVLActionPerformed(evt);
            }
        });

        jTable4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nguyên vật liệu", "Tên nguyên vật liệu", "Giá", "Số lượng", "Loại"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setRowHeight(30);
        jTable4.setShowGrid(false);
        jTable4.setShowHorizontalLines(true);
        jScrollPane4.setViewportView(jTable4);

        btnxoaVL.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaVL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoaVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaVL.setText("Xóa");
        btnxoaVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaVLActionPerformed(evt);
            }
        });

        btnsuaVL.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaVL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsuaVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaVL.setText("Sửa");
        btnsuaVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaVLActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Mã nguyên vật liệu");

        textMaVL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        textMaVL.setToolTipText("Không cần nhập ô này khi thêm mới!");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Tên nguyên vật liệu");

        texttenVL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        texttenVL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttenVLActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Giá");

        textgiaVL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Loại");

        cbxloaiVL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxloaiVL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----", "Bao", "Goi", "Chay", "Cai" }));

        btntimVL.setBackground(new java.awt.Color(248, 211, 94));
        btntimVL.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btntimVL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimVL.setText("Tìm");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("Kho lưu trữ");

        javax.swing.GroupLayout jpnCard4Layout = new javax.swing.GroupLayout(jpnCard4);
        jpnCard4.setLayout(jpnCard4Layout);
        jpnCard4Layout.setHorizontalGroup(
            jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCard4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addGroup(jpnCard4Layout.createSequentialGroup()
                        .addGap(503, 503, 503)
                        .addComponent(btntimVL)
                        .addGap(35, 35, 35)
                        .addComponent(btnthemVL)
                        .addGap(35, 35, 35)
                        .addComponent(btnxoaVL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsuaVL)))
                .addContainerGap())
            .addGroup(jpnCard4Layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel29)
                    .addComponent(jLabel61))
                .addGap(27, 27, 27)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard4Layout.createSequentialGroup()
                        .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textMaVL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(texttenVL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addGap(32, 32, 32)
                        .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxloaiVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textgiaVL, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCard4Layout.setVerticalGroup(
            jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(28, 28, 28)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(textMaVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(cbxloaiVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(textgiaVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(texttenVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsuaVL)
                    .addGroup(jpnCard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btntimVL)
                        .addComponent(btnthemVL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnxoaVL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        jpnCardLayout.add(jpnCard4, "jpnListRe");

        jpnCard5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("DANH SÁCH NHÀ CUNG CẤP");

        btnthemNCC.setBackground(new java.awt.Color(244, 211, 94));
        btnthemNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnthemNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemNCC.setText("Thêm");
        btnthemNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemNCCActionPerformed(evt);
            }
        });

        tableSup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ", "Email"
            }
        ));
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
        btnxoaNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaNCC.setText("Xóa");
        btnxoaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaNCCActionPerformed(evt);
            }
        });

        btnsuaNCC.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsuaNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaNCC.setText("Sửa");
        btnsuaNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaNCCActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Mã nhà cung cấp");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Tên nhà cung cấp");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Số điện thoại");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Địa chỉ");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("Email");

        btntimNCC.setBackground(new java.awt.Color(248, 211, 94));
        btntimNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btntimNCC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimNCC.setText("Tìm");
        btntimNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimNCCActionPerformed(evt);
            }
        });

        btnresetNCC.setBackground(new java.awt.Color(248, 211, 94));
        btnresetNCC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnresetNCC.setText("Reset");
        btnresetNCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetNCCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnCard5Layout = new javax.swing.GroupLayout(jpnCard5);
        jpnCard5.setLayout(jpnCard5Layout);
        jpnCard5Layout.setHorizontalGroup(
            jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnCard5Layout.createSequentialGroup()
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnCard5Layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(textdiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textemailNCC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnCard5Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnCard5Layout.createSequentialGroup()
                                .addComponent(btntimNCC)
                                .addGap(18, 18, 18)
                                .addComponent(btnthemNCC)
                                .addGap(18, 18, 18)
                                .addComponent(btnxoaNCC))
                            .addGroup(jpnCard5Layout.createSequentialGroup()
                                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textmaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(texttenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textsdtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(btnsuaNCC)
                        .addGap(18, 18, 18)
                        .addComponent(btnresetNCC)))
                .addGap(0, 264, Short.MAX_VALUE))
        );
        jpnCard5Layout.setVerticalGroup(
            jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(textmaNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(textdiaChiNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(texttenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37)
                        .addComponent(textemailNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(textsdtNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCard5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimNCC)
                    .addComponent(btnthemNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoaNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsuaNCC)
                    .addComponent(btnresetNCC))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnCardLayout.add(jpnCard5, "jpnListSup");

        jpnCard6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(0, 179, 179));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("DANH SÁCH KHÁCH HÀNG");

        btnthemKH.setBackground(new java.awt.Color(244, 211, 94));
        btnthemKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnthemKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemKH.setText("Thêm");
        btnthemKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemKHActionPerformed(evt);
            }
        });

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableKHMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tableKH);

        btnxoaKH.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaKH.setText("Xóa");
        btnxoaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaKHActionPerformed(evt);
            }
        });

        btnsuaKH.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsuaKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaKH.setText("Sửa");
        btnsuaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaKHActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Mã khách hàng");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel39.setText("Tên khách hàng");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel40.setText("Giới tính");

        cbxgenderKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nu" }));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel41.setText("Ngày sinh");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel42.setText("Địa chỉ");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel43.setText("Số điện thoại");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setText("Email");

        btntimKH.setBackground(new java.awt.Color(248, 211, 94));
        btntimKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btntimKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimKH.setText("Tìm");
        btntimKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimKHActionPerformed(evt);
            }
        });

        btnresetKH.setBackground(new java.awt.Color(248, 211, 94));
        btnresetKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnresetKH.setText("Reset");
        btnresetKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetKHActionPerformed(evt);
            }
        });

        dateBirthKH.setDateFormatString("dd-MMM-yy");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Mã người dùng");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Loại");

        cbxloaiKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbxloaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxloaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxloaiKHActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Tiền tích lũy");

        javax.swing.GroupLayout jpnCard6Layout = new javax.swing.GroupLayout(jpnCard6);
        jpnCard6.setLayout(jpnCard6Layout);
        jpnCard6Layout.setHorizontalGroup(
            jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
            .addGroup(jpnCard6Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpnCard6Layout.createSequentialGroup()
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(29, 29, 29)
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(textsdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(texttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbxgenderKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxloaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnCard6Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard6Layout.createSequentialGroup()
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textemailKH, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textdiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dateBirthKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpnCard6Layout.createSequentialGroup()
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textmaUser)
                            .addComponent(texttienTL, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnCard6Layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(btntimKH)
                .addGap(18, 18, 18)
                .addComponent(btnthemKH)
                .addGap(18, 18, 18)
                .addComponent(btnxoaKH)
                .addGap(18, 18, 18)
                .addComponent(btnsuaKH)
                .addGap(18, 18, 18)
                .addComponent(btnresetKH)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpnCard6Layout.setVerticalGroup(
            jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(textmaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(textdiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel41)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(texttenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dateBirthKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard6Layout.createSequentialGroup()
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel40)
                                .addComponent(cbxgenderKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(textemailKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43)
                            .addComponent(textsdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(textmaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cbxloaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(texttienTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addGroup(jpnCard6Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addGap(16, 16, 16)))
                .addGroup(jpnCard6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimKH)
                    .addComponent(btnthemKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnxoaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsuaKH)
                    .addComponent(btnresetKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard6, "jpnListCus");

        jpnCard7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("QUẢN LÍ MÃ KHUYẾN MÃI");

        btnthemMaKM.setBackground(new java.awt.Color(244, 211, 94));
        btnthemMaKM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnthemMaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemMaKM.setText("Thêm");
        btnthemMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemMaKMActionPerformed(evt);
            }
        });

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

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableKMMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tableKM);

        btnxoaMaKM.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaMaKM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoaMaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaMaKM.setText("Xóa");

        btnsuaMaKM.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaMaKM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsuaMaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaMaKM.setText("Sửa");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel45.setText("Mã khuyến mãi");

        textmaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textmaKMActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel46.setText("Mã code");

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel47.setText("Giá trị");

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel48.setText("Loại");

        cbxloaiMaKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diamond", "Gold" }));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel49.setText("Ngày bắt đầu");

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel50.setText("Ngày kết thúc");

        btntimMaKM.setBackground(new java.awt.Color(248, 211, 94));
        btntimMaKM.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btntimMaKM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimMaKM.setText("Tìm");

        date_start_dis.setDateFormatString("dd-MMM-yy\n");

        date_end_dis.setDateFormatString("dd-MMM-yy");

        javax.swing.GroupLayout jpnCard7Layout = new javax.swing.GroupLayout(jpnCard7);
        jpnCard7.setLayout(jpnCard7Layout);
        jpnCard7Layout.setHorizontalGroup(
            jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel50)
                    .addGroup(jpnCard7Layout.createSequentialGroup()
                        .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpnCard7Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textgiaTriKM, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnCard7Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textmaCode, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnCard7Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(18, 18, 18)
                                .addComponent(textmaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(57, 57, 57)
                        .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel49))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxloaiMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_start_dis, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(date_end_dis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnCard7Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(btntimMaKM)
                .addGap(18, 18, 18)
                .addComponent(btnthemMaKM)
                .addGap(18, 18, 18)
                .addComponent(btnxoaMaKM)
                .addGap(18, 18, 18)
                .addComponent(btnsuaMaKM)
                .addContainerGap(338, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnCard7Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane7)
        );
        jpnCard7Layout.setVerticalGroup(
            jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(20, 20, 20)
                .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textmaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(cbxloaiMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addGap(18, 18, 18)
                .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(textmaCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpnCard7Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addComponent(jLabel49)))
                    .addComponent(date_start_dis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textgiaTriKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel47)
                        .addComponent(jLabel50))
                    .addComponent(date_end_dis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jpnCard7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimMaKM)
                    .addComponent(btnthemMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnxoaMaKM)
                    .addComponent(btnsuaMaKM))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard7, "jpnListDis");

        jpnCard8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("DANH SÁCH KHO");

        btnthemKho.setBackground(new java.awt.Color(244, 211, 94));
        btnthemKho.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnthemKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemKho.setText("Thêm");

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã kho", "Trạng thái", "Loại"
            }
        ));
        jScrollPane8.setViewportView(jTable8);

        btnxoaKho.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaKho.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoaKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaKho.setText("Xóa");

        btnsuaKho.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaKho.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsuaKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaKho.setText("Sửa");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setText("Mã kho");

        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel52.setText("Trạng thái");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đầy", "Chưa đầy" }));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel53.setText("Loại");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sản phẩm", "Nguyên liệu" }));

        btntimKho.setBackground(new java.awt.Color(248, 211, 94));
        btntimKho.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btntimKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimKho.setText("Tìm");

        javax.swing.GroupLayout jpnCard8Layout = new javax.swing.GroupLayout(jpnCard8);
        jpnCard8.setLayout(jpnCard8Layout);
        jpnCard8Layout.setHorizontalGroup(
            jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
            .addGroup(jpnCard8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntimKho)
                .addGap(18, 18, 18)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard8Layout.createSequentialGroup()
                        .addComponent(btnthemKho)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoaKho)
                        .addGap(18, 18, 18)
                        .addComponent(btnsuaKho))
                    .addGroup(jpnCard8Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpnCard8Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnCard8Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpnCard8Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCard8Layout.setVerticalGroup(
            jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(18, 18, 18)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jpnCard8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimKho)
                    .addComponent(btnthemKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnxoaKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsuaKho, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(650, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard8, "jpnListStock");

        jpnCard9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("DANH SÁCH NHÂN VIÊN");

        btnthemNhanVien.setBackground(new java.awt.Color(244, 211, 94));
        btnthemNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnthemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/plus.png"))); // NOI18N
        btnthemNhanVien.setText("Thêm");

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhân viên", "Mã nông trại", "Tên nhân viên", "Địa chỉ", "Số điện thoại", "Email", "Ngày vào làm"
            }
        ));
        jScrollPane9.setViewportView(jTable9);

        btnxoaNhanVien.setBackground(new java.awt.Color(248, 211, 94));
        btnxoaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnxoaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/trash.png"))); // NOI18N
        btnxoaNhanVien.setText("Xóa");

        btnsuaNhanVien.setBackground(new java.awt.Color(248, 211, 94));
        btnsuaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnsuaNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/pencil.png"))); // NOI18N
        btnsuaNhanVien.setText("Sửa");

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("Mã nhân viên");

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("Mã nông trại");

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel56.setText("Tên nhân viên");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Địa chỉ");

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("Số điện thoại");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("Email");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Ngày vào làm");

        btntimNhanVien.setBackground(new java.awt.Color(248, 211, 94));
        btntimNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btntimNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/transparency (1).png"))); // NOI18N
        btntimNhanVien.setText("Tìm");

        javax.swing.GroupLayout jpnCard9Layout = new javax.swing.GroupLayout(jpnCard9);
        jpnCard9.setLayout(jpnCard9Layout);
        jpnCard9Layout.setHorizontalGroup(
            jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard9Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(jLabel57))
                .addGap(18, 18, 18)
                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnCard9Layout.createSequentialGroup()
                        .addComponent(textdiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnCard9Layout.createSequentialGroup()
                        .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnCard9Layout.createSequentialGroup()
                                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpnCard9Layout.createSequentialGroup()
                                        .addComponent(textmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel58))
                                    .addGroup(jpnCard9Layout.createSequentialGroup()
                                        .addComponent(textmaNT, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel59)))
                                .addGap(18, 18, 18)
                                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textemailNV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textsdtNV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnCard9Layout.createSequentialGroup()
                                .addComponent(texttenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel60)
                                .addGap(18, 18, 18)
                                .addComponent(textngayVL, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 265, Short.MAX_VALUE))))
            .addGroup(jpnCard9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jpnCard9Layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(btntimNhanVien)
                .addGap(18, 18, 18)
                .addComponent(btnthemNhanVien)
                .addGap(18, 18, 18)
                .addComponent(btnxoaNhanVien)
                .addGap(18, 18, 18)
                .addComponent(btnsuaNhanVien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane9)
        );
        jpnCard9Layout.setVerticalGroup(
            jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(textmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(textsdtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textmaNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(textemailNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(texttenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel60)
                    .addComponent(textngayVL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textdiaChiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57))
                .addGap(18, 18, 18)
                .addGroup(jpnCard9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntimNhanVien)
                    .addComponent(btnthemNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnxoaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsuaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(623, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard9, "jpnListEmp");

        jpnCard10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("TRẠNG THÁI VẬN CHUYỂN");

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã vận chuyen", "Mã đơn hàng", "Trạng thái"
            }
        ));
        jScrollPane10.setViewportView(jTable10);

        javax.swing.GroupLayout jpnCard10Layout = new javax.swing.GroupLayout(jpnCard10);
        jpnCard10.setLayout(jpnCard10Layout);
        jpnCard10Layout.setHorizontalGroup(
            jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1009, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnCard10Layout.setVerticalGroup(
            jpnCard10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCard10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(825, Short.MAX_VALUE))
        );

        jpnCardLayout.add(jpnCard10, "jpnListTrans");

        jpnCard11.setBackground(new java.awt.Color(248, 218, 208));

        javax.swing.GroupLayout jpnCard11Layout = new javax.swing.GroupLayout(jpnCard11);
        jpnCard11.setLayout(jpnCard11Layout);
        jpnCard11Layout.setHorizontalGroup(
            jpnCard11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1009, Short.MAX_VALUE)
        );
        jpnCard11Layout.setVerticalGroup(
            jpnCard11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );

        jpnCardLayout.add(jpnCard11, "jpnListStatis");

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

    }//GEN-LAST:event_jbtManaOrderActionPerformed

    private void jbtManaReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaReActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListRe");


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

    private void jbtManaStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaStockActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListStock");
    }//GEN-LAST:event_jbtManaStockActionPerformed

    private void jbtManaEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaEmpActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListEmp");
    }//GEN-LAST:event_jbtManaEmpActionPerformed

    private void jbtManaTransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaTransActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListTrans");
    }//GEN-LAST:event_jbtManaTransActionPerformed

    private void jbtManaStatisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtManaStatisActionPerformed
        // TODO add your handling code here:
        cardlayout.show(jpnCardLayout, "jpnListStatis");
    }//GEN-LAST:event_jbtManaStatisActionPerformed

    private void btnthemNongTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemNongTraiActionPerformed
        // TODO add your handling code here: 
        boolean check = false;
        String maNT = textMaNT.getText();
        String tenNT = texttenNT.getText();
        String diachiNT = textdiaChiNT.getText();

        if (tenNT.isEmpty() || diachiNT.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap ten va dia chi");
        } else {
            if (!maNT.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Không cần nhập mã nong trai khi thêm mới!");
            } else {
                try {

                    Farm fa = new Farm("", tenNT, diachiNT);
                    check = FarmController.insertFarm(fa);

                    if (check == true) {

                        JOptionPane.showMessageDialog(this, "Thêm nong trai thành công.");
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
                    "Are you sure you want to delete?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                FarmController.deleteFarm(farmid);
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
    }//GEN-LAST:event_btnxoaVLActionPerformed

    private void btnxoaNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaNCCActionPerformed
        // TODO add your handling code here:
        String supID = textmaNCC.getText();
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                SupplierController.deleteSup(supID);
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
        Long sdtNCC = Long.parseLong(textsdtNCC.getText());
        String diachiNCC = textdiaChiNCC.getText();
        String emailNCC = textemailNCC.getText();
        try {
            if (tenNCC.isEmpty() || diachiNCC.isEmpty() || emailNCC.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap thong tin");
            } else {
                Supplier sup = new Supplier(maNCC, tenNCC, sdtNCC, diachiNCC, emailNCC);
                check = SupplierController.updateSup(sup);
                if (check == true) {

                    JOptionPane.showMessageDialog(this, "Sua thành công.");
                    showSup();
                } else {
                    JOptionPane.showMessageDialog(this, "Sua không thành công",
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


    }//GEN-LAST:event_btnthemSPActionPerformed

    private void btnthemKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemKHActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String maKH = textmaKH.getText();
        String tenKH = texttenKH.getText();
        String gioitinhKH = (String) cbxgenderKH.getSelectedItem();
        DateFormat dateformat = new SimpleDateFormat("dd-MMM-yy");
        String ngay = dateformat.format(dateBirthKH.getDate().getTime());
        String diachiKH = textdiaChiKH.getText();
        long sdtKH = 0;
        String emailKH = textemailKH.getText();
        String loaiKH = (String) cbxloaiKH.getSelectedItem();
        double tienTL = 0;
        String maUS = textmaUser.getText();
        if (tenKH.isEmpty() || emailKH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap ten va email khach hang");
        } else {
            if (!texttienTL.getText().equalsIgnoreCase("")) {
                tienTL = Double.parseDouble(texttienTL.getText());
            }
            if (!textsdtKH.getText().equalsIgnoreCase("")) {
                sdtKH = Long.parseLong(textsdtKH.getText());
            }
//        if (tenNT.isEmpty() || diachiNT.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui long nhap ten va dia chi");
//        }
            if (!maKH.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Không cần nhập mã khach hang khi thêm mới!");
            } else {
                try {
                    //Date ngaysinhKH =(Date) dateformat.parse(ngay);
                    //Date ngaysinhKH=dateformat.parse(dateBirthKH.getDate());
                    Customer cus;
                    cus = new Customer("", tenKH, gioitinhKH, dateformat.parse(ngay), diachiKH, sdtKH, emailKH, loaiKH, tienTL, maUS);
                    check = CustomerController.insertCus(cus);

                    if (check == true) {

                        JOptionPane.showMessageDialog(this, "Thêm khách hàng thành công.");
                        showCus();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm không thành công",
                                "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
                    }
                    reset_Cus();

                } catch (SQLException ex) {
                    int code = ex.getErrorCode();
                    String msg = ex.getMessage();
                    JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }//GEN-LAST:event_btnthemKHActionPerformed

    private void textmaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textmaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textmaSPActionPerformed

    private void texttenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texttenSPActionPerformed

    private void cbxLoaiProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLoaiProActionPerformed
        // TODO add your handling code here:
        DefaultComboBoxModel model = (DefaultComboBoxModel) this.cbxLoaiPro.getModel();
        List<String> arrayList;
        arrayList = new ArrayList<String>();
        arrayList.add("Trái cây");
        arrayList.add("Củ quả");
        arrayList.add("rau");
        model.addAll(arrayList);
    }//GEN-LAST:event_cbxLoaiProActionPerformed

    private void textmaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textmaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textmaKMActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void btntimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btntimSPActionPerformed

    private void btnsuaVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaVLActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnsuaVLActionPerformed

    private void btnthemVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemVLActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_btnthemVLActionPerformed

    private void texttenVLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttenVLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texttenVLActionPerformed

    private void btntimNongTraiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimNongTraiActionPerformed
        try {
            tableModel.setRowCount(0);
            String tenNT = texttenNT.getText();
            listFarm = FarmController.findFarm(tenNT);
            for (Farm fa : listFarm) {
                tableModel.addRow(new Object[]{tableModel.getRowCount() + 1, fa.getFarmID(),
                    fa.getFarmName(), fa.getFarmAdd()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
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
            check = FarmController.updateFarm(fa);
            if (tenNT.isEmpty() || diachiNT.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Khong duoc de trong thong tin");
            } else {
                if (check == true) {

                    JOptionPane.showMessageDialog(this, "Sua thành công.");
                    showFarm();
                } else {
                    JOptionPane.showMessageDialog(this, "Sua không thành công",
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
//         TODO add your handling code here:
        //int column = 0;
//        int row = tableNongTrai.getSelectedRow();
//        textMaNT.setText(tableNongTrai.getModel().getValueAt(row, 1).toString());
//        texttenNT.setText(tableNongTrai.getModel().getValueAt(row, 2).toString());
//        textdiaChiNT.setText(tableNongTrai.getModel().getValueAt(row, 3).toString());
    }//GEN-LAST:event_tableNongTraiMouseClicked

    private void btnthemNCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemNCCActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String maNCC = textmaNCC.getText();
        String tenNCC = texttenNCC.getText();
        long sdtNCC = 0;
        String diachiNCC = textdiaChiNCC.getText();
        String emailNCC = textemailNCC.getText();
        if (tenNCC.isEmpty() || diachiNCC.isEmpty() || emailNCC.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap thong tin");
        } else {
            if (!maNCC.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Không cần nhập mã nha cung cap khi thêm mới!");
            } else {
                try {

                    Supplier sup = new Supplier("", tenNCC, sdtNCC, diachiNCC, emailNCC);
                    check = SupplierController.insertSup(sup);

                    if (check == true) {

                        JOptionPane.showMessageDialog(this, "Thêm nha cung cap thành công.");
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
        // TODO add your handling code here:
//        int column = 0;
//        int row = tableSup.getSelectedRow();
//        DefaultTableModel tableModel=(DefaultTableModel)tableNongTrai.getModel();
//        textmaNCC.setText(tableNongTrai.getModel().getValueAt(row, 1).toString());
//        texttenNCC.setText(tableNongTrai.getModel().getValueAt(row, 2).toString());
//        textsdtNCC.setText(tableNongTrai.getModel().getValueAt(row, 3).toString());
//        textdiaChiNCC.setText(tableNongTrai.getModel().getValueAt(row, 3).toString());
//        textemailNCC.setText(tableNongTrai.getModel().getValueAt(row, 3).toString());
    }//GEN-LAST:event_tableSupMouseClicked

    private void tableNongTraiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNongTraiMousePressed

        int selectedIndex = tableNongTrai.getSelectedRow();
        if (selectedIndex >= 0) {
            Farm fa = listFarm.get(selectedIndex);
            textMaNT.setText(fa.getFarmID());
            textMaNT.setEditable(false);
            texttenNT.setText(fa.getFarmName());
            textdiaChiNT.setText(fa.getFarmAdd());
    }//GEN-LAST:event_tableNongTraiMousePressed
    }
    private void btnresetNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetNTActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
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
            String tenNCC = texttenNCC.getText();
            listSup = SupplierController.findSup(tenNCC);
            for (Supplier sup : listSup) {
                tableModel1.addRow(new Object[]{tableModel1.getRowCount() + 1, sup.getSupID(),
                    sup.getSupName(), sup.getSupPhone(), sup.getSupAdd(), sup.getSupEmail()});
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
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbxloaiKH.getModel();
        List<String> arrayList;
        arrayList = new ArrayList<String>();
        arrayList.add("----");
        arrayList.add("silver");
        arrayList.add("gold");
        arrayList.add("diamond");
        model.addAll(arrayList);
    }//GEN-LAST:event_cbxloaiKHActionPerformed

    private void tableKHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKHMousePressed
        // TODO add your handling code here:
        int selectedIndex = tableKH.getSelectedRow();
        if (selectedIndex >= 0) {
            Customer cus = listCus.get(selectedIndex);
            textmaKH.setText(cus.getCusID());
            textmaKH.setEditable(false);
            texttenKH.setText(cus.getCusName());
            cbxgenderKH.setSelectedItem(cus.getGender());
            dateBirthKH.setDate(cus.getDateOfBirth());
//            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
//            String d=sdf.format(dateBirthKH.getDate());
            textdiaChiKH.setText(cus.getCusAdd());
            textsdtKH.setText(String.valueOf(cus.getCusPhone()));
            textemailKH.setText(cus.getCusEmail());
            cbxloaiKH.setSelectedItem(cus.getCusType());
            texttienTL.setText(String.valueOf(cus.getAccrued_Money()));
            textmaUser.setText(cus.getUserID());
        }
    }//GEN-LAST:event_tableKHMousePressed

    private void btntimKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimKHActionPerformed
        // TODO add your handling code here:
        try {
            tableModel2.setRowCount(0);
            String tenKH = texttenKH.getText();
            listCus = CustomerController.findCus(tenKH);
            for (Customer cus : listCus) {
                tableModel2.addRow(new Object[]{tableModel2.getRowCount() + 1, cus.getCusID(),
                    cus.getCusName(), cus.getGender(), cus.getDateOfBirth(), cus.getCusAdd(),
                    cus.getCusPhone(), cus.getCusEmail(), cus.getCusType(), cus.getAccrued_Money(),
                    cus.getUserID()});
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btntimKHActionPerformed

    private void btnxoaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaKHActionPerformed
        // TODO add your handling code here:
        String cusID = textmaKH.getText();
        try {
            int n = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete?",
                    "Alert",
                    JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                CustomerController.deleteCus(cusID);
                showCus();
                reset_Cus();
            }

        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnxoaKHActionPerformed

    private void btnsuaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaKHActionPerformed
        // TODO add your handling code here:
        boolean check = false;
        String maKH = textmaKH.getText();
        String tenKH = texttenKH.getText();
        String gioitinhKH = (String) cbxgenderKH.getSelectedItem();
//        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
//        String ngaysinhKH =  dateformat.format(dateBirthKH.getDate());
        Date ngaysinhKH = (Date) dateBirthKH.getDate();
        String diachiKH = textdiaChiKH.getText();
        long sdtKH = 0;
        String emailKH = textemailKH.getText();
        String loaiKH = (String) cbxloaiKH.getSelectedItem();
        double tienTL = 0;
        String maUS = textmaUser.getText();
        if (!texttienTL.getText().equalsIgnoreCase("")) {
            tienTL = Double.parseDouble(texttienTL.getText());
        }
        if (!textsdtKH.getText().equalsIgnoreCase("")) {
            sdtKH = Long.parseLong(textsdtKH.getText());
        }

        try {
            if (tenKH.isEmpty() || emailKH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui long nhap ten va email khach hang");
            } else {
                Customer cus = new Customer(maKH, tenKH, gioitinhKH, ngaysinhKH, diachiKH, sdtKH, emailKH, loaiKH, tienTL, maUS);
                check = CustomerController.updateCus(cus);

                if (check == true) {

                    JOptionPane.showMessageDialog(this, "Sua khách hàng thành công.");
                    showCus();
                } else {
                    JOptionPane.showMessageDialog(this, "Sua không thành công",
                            "Lỗi đăng ký", JOptionPane.ERROR_MESSAGE);
                }
                reset_Cus();
            }
        } catch (SQLException ex) {
            int code = ex.getErrorCode();
            String msg = ex.getMessage();
            JOptionPane.showMessageDialog(this, msg, String.valueOf(code), JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnsuaKHActionPerformed

    private void btnresetKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetKHActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            showCus();
            reset_Cus();
        } catch (SQLException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnresetKHActionPerformed

    private void btnthemMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemMaKMActionPerformed
        // TODO add your handling code here:
        Random_Discount rand_dis=new Random_Discount(textmaKM);
        boolean check = false;
        String maKM = textmaKM.getText();
        String maCode=textmaCode.getText();
        float giatriKM = 0;
        String loaimaKM = (String)cbxloaiMaKM.getSelectedItem();
        //DateFormat dateformat = new SimpleDateFormat("dd-MMM-yy");
        java.util.Date ngayBD = date_start_dis.getDate();
        java.sql.Date bd=new java.sql.Date(ngayBD.getTime());
        java.util.Date ngayKT = date_start_dis.getDate();
        java.sql.Date kt=new java.sql.Date(ngayBD.getTime());
        if (loaimaKM.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "Vui long nhap thong tin");
        } else {
            if (!maKM.equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Không cần nhập mã khi thêm mới!");
            } else {
                try {

                    Discount dis = new Discount("", maCode, giatriKM, loaimaKM, bd,kt);
                    check = DiscountController.insertDis(dis);

                    if (check == true) {

                        JOptionPane.showMessageDialog(this, "Thêm ma khuyen mai thành công.");
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
        
    }//GEN-LAST:event_btnthemMaKMActionPerformed

    private void tableKMMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableKMMousePressed
        // TODO add your handling code here:
         int selectedIndex = tableKM.getSelectedRow();
        if (selectedIndex >= 0) {
            Discount dis = listDis.get(selectedIndex);
            textmaKM.setText(dis.getDisID());
            textmaKM.setEditable(false);
            textmaCode.setText(dis.getDisCode());
            textmaCode.setEditable(false);
            textgiaTriKM.setText(String.valueOf(dis.getValue()));
            cbxloaiMaKM.setSelectedItem(dis.getCusType());
            date_start_dis.setDate(dis.getStartDate());
            date_end_dis.setDate(dis.getEndDate());
        }
    }//GEN-LAST:event_tableKMMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminHome().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnresetKH;
    private javax.swing.JButton btnresetNCC;
    private javax.swing.JButton btnresetNT;
    private javax.swing.JButton btnsuaKH;
    private javax.swing.JButton btnsuaKho;
    private javax.swing.JButton btnsuaMaKM;
    private javax.swing.JButton btnsuaNCC;
    private javax.swing.JButton btnsuaNhanVien;
    private javax.swing.JButton btnsuaNongTrai;
    private javax.swing.JButton btnsuaSP;
    private javax.swing.JButton btnsuaVL;
    private javax.swing.JButton btnthemKH;
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
    private javax.swing.JButton btnxoaKH;
    private javax.swing.JButton btnxoaKho;
    private javax.swing.JButton btnxoaMaKM;
    private javax.swing.JButton btnxoaNCC;
    private javax.swing.JButton btnxoaNhanVien;
    private javax.swing.JButton btnxoaNongTrai;
    private javax.swing.JButton btnxoaSP;
    private javax.swing.JButton btnxoaVL;
    private javax.swing.JComboBox<String> cbxLoaiPro;
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
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField3;
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
    private javax.swing.JTable tableListKH;
    private javax.swing.JTable tableNongTrai;
    private javax.swing.JTable tableSup;
    private javax.swing.JTextField textGia;
    private javax.swing.JTextField textMaNT;
    private javax.swing.JTextField textMaVL;
    private javax.swing.JTextField textSL;
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
    private javax.swing.JTextField textmaNT;
    private javax.swing.JTextField textmaNV;
    private javax.swing.JTextField textmaSP;
    private javax.swing.JTextField textmaUser;
    private javax.swing.JTextField textngayVL;
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
    // End of variables declaration//GEN-END:variables
}
