/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import ConnectDB.ConnectionUtils;
import Process.C_Product;
import Model.Product;
import Process.C_NewOrderExport;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DoQuynhChi
 */
public class ChooseProductPage extends javax.swing.JFrame {
    
    private ArrayList<Product> arrLProductDB = C_Product.listProduct();
    private static DefaultTableModel tableModel;
    private ArrayList<Object> rowList = new ArrayList<>();
    private final int j = 4;
    private  int size = arrLProductDB.size();
    private  double a = (double)size/j;
    private final int i = (int)Math.ceil(a);
    private JNode[][] panelHolder = new JNode[i][j];
    private int k;
    private static long total = 0;
    private String id_login;
    private String name;

    /**
     * Creates new form NewOrderExport
     */
    public ChooseProductPage(String id) throws SQLException, ClassNotFoundException {
        initComponents();
         C_NewOrderExport.arrLProPurchase.clear();
        tableModel = (DefaultTableModel) getjTableCTHD().getModel();
        tableModel.setRowCount(0);
        showProductPage();
        
        id_login = id;
        String name = C_NewOrderExport.getUserName(id_login);
        
        jComboBox3.addItem(name);
        jComboBox3.setSelectedIndex(1);
        
    }

    /*
    * Hien thi JNode theo so luong nong san luu trong he thong
     */
    public void showProductPage() {
        jSanPham.setLayout(new GridLayout(i, j));
        int k = 0;

        for (int m = 0; m < i; m++) {
            for (int n = 0; n < j; n++) {
                if (k < arrLProductDB.size()) {
                    String hinhAnh = "/Resources/" + arrLProductDB.get(k).getImage();
                    panelHolder[m][n] = new JNode();
                    try {
                        panelHolder[m][n].getjLabelHinh().setIcon(new javax.swing.ImageIcon(getClass().getResource(hinhAnh)));
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    panelHolder[m][n].getjLabelTenSP().setText(arrLProductDB.get(k).getProName());
                    panelHolder[m][n].getjLabelSoLuong().setText(String.valueOf(arrLProductDB.get(k).getQuantity()));

                    // Neu so luong nong san ton kho la 0 thi vo hieu hoa nut +
                    if (panelHolder[m][n].getjLabelSoLuong().getText().equals("0")) {
                        panelHolder[m][n].jButtonThem.setEnabled(false);
                    }

                    panelHolder[m][n].getjLabelGia().setText("" + arrLProductDB.get(k).getProPrice());
                    jSanPham.add(panelHolder[m][n]);
                    k += 1;
                }
            }

        }
    }

    public class JNode extends javax.swing.JPanel {

        private int[][] clicked = new int[i][j];

        public JNode() {
            initComponents();

            for (int m = 0; m < i; m++) {
                for (int n = 0; n < j; n++) {
                    clicked[m][n] = 0;
                }
            }

        }

// code khoi tao cua JNode()
        private void initComponents() {

            jLabelTenSP = new javax.swing.JLabel();
            jLabel1 = new javax.swing.JLabel();
            jLabelHinh = new javax.swing.JLabel();
            jLabelSoLuong = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            jLabelGia = new javax.swing.JLabel();
            jButtonGiam = new javax.swing.JButton();
            jButtonThem = new javax.swing.JButton();

            setBackground(new java.awt.Color(255, 184, 77));
            setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            setPreferredSize(new java.awt.Dimension(234, 266));

            jLabelTenSP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabelTenSP.setForeground(new java.awt.Color(255, 255, 255));
            jLabelTenSP.setText("Vải");

            jLabel1.setBackground(new java.awt.Color(255, 255, 255));
            jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setText("Số lượng:");

            jLabelHinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cam.jpg"))); // NOI18N

            jLabelSoLuong.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

            jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setText("Giá:");

            jLabelGia.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
            jLabelGia.setPreferredSize(new java.awt.Dimension(17, 17));

            jButtonGiam.setBackground(new java.awt.Color(46, 184, 184));
            jButtonGiam.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
            jButtonGiam.setForeground(new java.awt.Color(255, 255, 255));
            jButtonGiam.setText("-");
            jButtonGiam.setAlignmentY(0.0F);
            jButtonGiam.setEnabled(false);
            jButtonGiam.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonGiamActionPerformed(evt);
                }
            });

            jButtonThem.setBackground(new java.awt.Color(46, 184, 184));
            jButtonThem.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
            jButtonThem.setForeground(new java.awt.Color(255, 255, 255));
            jButtonThem.setText("+");
            jButtonThem.setAlignmentY(0.0F);
            jButtonThem.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonThemActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(15, 15, 15)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createSequentialGroup()
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                            .addGroup(layout.createSequentialGroup()
                                                                                    .addComponent(jLabel2)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(jLabelGia, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                            .addGroup(layout.createSequentialGroup()
                                                                                    .addComponent(jLabel1)
                                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                    .addComponent(jLabelSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                    .addGap(12, 12, 12)
                                                                    .addComponent(jButtonGiam)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(jButtonThem))
                                                            .addComponent(jLabelTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(16, 16, 16)
                                                    .addComponent(jLabelHinh)))
                                    .addContainerGap(16, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(25, 25, 25)
                                    .addComponent(jLabelHinh)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabelTenSP)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel1)
                                                            .addComponent(jLabelSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jLabelGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addGap(25, 25, 25)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jButtonGiam)
                                                            .addComponent(jButtonThem))))
                                    .addContainerGap())
            );
            jButtonGiam.getAccessibleContext().setAccessibleName("-");
        }// </editor-fold>                                                 

        /*
        * Cap nhat lai so luong va gia tien khi click button +
         */
        private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
            // TODO add your handling code here:
            boolean changed = false;
            for (int row = 0; row < i; row++) {
                for (int col = 0; col < j; col++) {
                    if (panelHolder[row][col].jButtonThem == evt.getSource()) {
                        clicked[row][col]++;

                        //khi nhan nut them 1 san pham, so luong ton kho cua san pham giam di 1
                        int soLuongCon = Integer.parseInt(panelHolder[row][col].jLabelSoLuong.getText());
                        soLuongCon--;
                        panelHolder[row][col].jLabelSoLuong.setText(String.valueOf(soLuongCon));
                        if (soLuongCon == 0)
                            jButtonThem.setEnabled(false);

                        if (!rowList.contains(JNode.this)) {
                            tableModel.addRow(new Object[]{arrLProductDB.get((row * j) + col).getProName(), clicked[row][col],
                                arrLProductDB.get((row * j) + col).getProPrice()});
                            rowList.add(JNode.this);
                            jButtonGiam.setEnabled(true);
                        } else {
                            tableModel.setValueAt(clicked[row][col], rowList.indexOf(JNode.this), 1);
                            tableModel.setValueAt(arrLProductDB.get((row * j) + col).getProPrice() * clicked[row][col], rowList.indexOf(JNode.this), 2);
                        }
                        changed = true;
                        break;
                    }
                }
                if (changed) {
                    break;
                }
            }
            
            tableModel.fireTableDataChanged();
            total = C_NewOrderExport.updateTotalAmount(tableModel);
             jLabelTongTien.setText(String.valueOf(total));
        }
        
        
    /*
    * Cap nhat lai so luong va gia tien khi click button -
    */
          private void jButtonGiamActionPerformed(java.awt.event.ActionEvent evt) {
              // TODO add your handling code here:
              boolean changed = false;

              for (int row = 0; row < i; row++) {
                  for (int col = 0; col < j; col++) {
                      if (panelHolder[row][col].jButtonGiam == evt.getSource()) {
                          clicked[row][col]--;

                          //khi nhan nut giam 1 san pham, so luong ton kho cua san pham tang them 1
                          int soLuongCon = Integer.parseInt(panelHolder[row][col].jLabelSoLuong.getText());
                          soLuongCon++;
                          panelHolder[row][col].jLabelSoLuong.setText(String.valueOf(soLuongCon));
                          if (soLuongCon > 0)
                              jButtonThem.setEnabled(true);

                          if (clicked[row][col] == 0) {
                              tableModel.removeRow(rowList.indexOf(JNode.this));
                              rowList.remove(JNode.this);

                              // khong cho nguoi dung nhan nut - khi so luong chon mua cua 1 JNode giam ve 0
                              jButtonGiam.setEnabled(false);

                          } else {
                              tableModel.setValueAt(clicked[row][col], rowList.indexOf(JNode.this), 1);
                              tableModel.setValueAt(arrLProductDB.get((row * j) + col).getProPrice() * clicked[row][col], rowList.indexOf(JNode.this), 2);

                          }

                          changed = true;
                          break;
                      }
                  }
                  if (changed) {
                      break;
                  }

              }
              tableModel.fireTableDataChanged();
              total = C_NewOrderExport.updateTotalAmount(tableModel);
              jLabelTongTien.setText(String.valueOf(total));
        }//GEN-LAST:event_jButtonThemActionPerformed

          
        /*
        * Getter và Settter cho JNode();
         */
        public JButton getjButtonThem() {
            return jButtonThem;
        }

        public void setjButtonThem(JButton jButtonThem) {
            this.jButtonThem = jButtonThem;
        }

        public JLabel getjLabelGia() {
            return jLabelGia;
        }

        public void setjLabelGia(JLabel jLabelGia) {
            this.jLabelGia = jLabelGia;
        }

        public JLabel getjLabelHinh() {
            return jLabelHinh;
        }

        public void setjLabelHinh(JLabel jLabelHinh) {
            this.jLabelHinh = jLabelHinh;
        }

        public JLabel getjLabelSoLuong() {
            return jLabelSoLuong;
        }

        public void setjLabelSoLuong(JLabel jLabelSoLuong) {
            this.jLabelSoLuong = jLabelSoLuong;
        }

        public JLabel getjLabelTenSP() {
            return jLabelTenSP;
        }

        public void setjLabelTenSP(JLabel jLabelTenSP) {
            this.jLabelTenSP = jLabelTenSP;
        }

        // Variables declaration - do not modify                     
        private javax.swing.JButton jButtonGiam;
        private javax.swing.JButton jButtonThem;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabelGia;
        private javax.swing.JLabel jLabelHinh;
        private javax.swing.JLabel jLabelSoLuong;
        private javax.swing.JLabel jLabelTenSP;
        // End of variables declaration                   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel31 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jSanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jButtonXacNhan = new javax.swing.JButton();
        jLabelTongTien = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCTHD = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel31.setBackground(new java.awt.Color(0, 179, 179));
        jPanel31.setPreferredSize(new java.awt.Dimension(550, 649));

        jLabel50.setFont(new java.awt.Font("Leelawadee UI", 1, 30)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("FRESH FOOD");
        jLabel50.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jComboBox3.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đăng Xuất" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1002, 2981));

        javax.swing.GroupLayout jSanPhamLayout = new javax.swing.GroupLayout(jSanPham);
        jSanPham.setLayout(jSanPhamLayout);
        jSanPhamLayout.setHorizontalGroup(
            jSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1049, Short.MAX_VALUE)
        );
        jSanPhamLayout.setVerticalGroup(
            jSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jSanPham);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("SẢN PHẨM CỦA CHÚNG TÔI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(294, 294, 294))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(334, 630));

        jLabel44.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel44.setText("Tổng cộng:");

        jButtonXacNhan.setBackground(new java.awt.Color(46, 184, 184));
        jButtonXacNhan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        jButtonXacNhan.setText("XÁC NHẬN");
        jButtonXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXacNhanActionPerformed(evt);
            }
        });

        jLabelTongTien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelTongTien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabelTongTien.setRequestFocusEnabled(false);
        jLabelTongTien.setVerifyInputWhenFocusTarget(false);

        jTableCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sản phẩm", "Số lượng", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCTHD.setPreferredSize(new java.awt.Dimension(334, 550));
        jTableCTHD.setRowHeight(25);
        jScrollPane4.setViewportView(jTableCTHD);
        if (jTableCTHD.getColumnModel().getColumnCount() > 0) {
            jTableCTHD.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTableCTHD.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableCTHD.getColumnModel().getColumn(2).setPreferredWidth(80);
        }

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/return.png"))); // NOI18N
        jButton1.setText("Quay lại");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonXacNhan)
                .addGap(19, 19, 19))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44)
                    .addComponent(jLabelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(477, 477, 477))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129))))))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel51)
                .addGap(69, 69, 69))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 1581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        if (jComboBox3.getSelectedIndex() == 0) {
            this.setVisible(false);
            new LoginPage().setVisible(true);
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButtonXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXacNhanActionPerformed
        // TODO add your handling code here:
        if (total != 0) {
            this.setVisible(false);
            C_NewOrderExport.luuThongTinCTHD(tableModel);
            try {
                new NewOrderExport(total, id_login).setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ChooseProductPage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChooseProductPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            JOptionPane.showMessageDialog(this, "Ban chua chon nong san nao!");        
    }//GEN-LAST:event_jButtonXacNhanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            new AdminHome(id_login).setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ChooseProductPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChooseProductPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public JTable getjTableCTHD() {
        return jTableCTHD;
    }

    public void setjTableCTHD(JTable jTableCTHD) {
        this.jTableCTHD= jTableCTHD;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonXacNhan;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabelTongTien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jSanPham;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableCTHD;
    // End of variables declaration//GEN-END:variables
}
