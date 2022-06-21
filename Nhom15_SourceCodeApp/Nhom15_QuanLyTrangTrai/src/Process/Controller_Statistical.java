/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Process;

import Model.Statistical_year;
import java.util.ArrayList;
import java.util.List;
import ConnectDB.ConnectionUtils;
import Model.Resources;
import Model.Statistical_day;
import Model.Statistical_month;
import Model.Statistical_top_cus;
import Model.Statistical_top_product;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.text.Element;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.TextAnchor;

/**
 *
 * @author vothanhphuong
 */
public class Controller_Statistical {

    public void setDataToChart_year(JPanel jpnItem) throws SQLException, ClassNotFoundException {
        List<Statistical_year> yearList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_theo_nam(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            long total = rs.getLong("TONGSO");
            String year = rs.getString("nam");
            yearList.add(new Statistical_year(total, year));
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (yearList != null) {
            for (Statistical_year item : yearList) {
                // dataset.addValue(item.getTotal(), "Học viên", item.getYear());
                dataset.addValue(item.getTotal() / 1000000, "Triệu VNĐ", item.getYear());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "TỔNG DOANH THU THEO NĂM".toUpperCase(),
                "Năm", "Tổng tiền(Triệu VNĐ)",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

        conn.close();
    }

    public void setDataToChart_month(JPanel jpnItem, String nam) throws SQLException, ClassNotFoundException {
        List<Statistical_month> monthList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_theo_thang(?,?)}");
        stmt.setString(1, nam);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);

        while (rs.next()) {
            long total = rs.getLong("TONGSO");
            String month = rs.getString("THANG");
            monthList.add(new Statistical_month(total, month));
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (monthList != null) {
            for (Statistical_month item : monthList) {
                // dataset.addValue(item.getTotal(), "Học viên", item.getYear());
                dataset.addValue(item.getTotal() / 1000000, "Triệu VNĐ", item.getMonth());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "TỔNG DOANH THU NĂM " + nam,
                "Tháng", "Tổng tiền(Triệu VNĐ)",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

        conn.close();
    }

    public void setDataToChart_day(JPanel jpnItem, String nam, String thang) throws SQLException, ClassNotFoundException {
        List<Statistical_day> dayhList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_theo_ngay(?,?,?)}");
        stmt.setString(1, nam);
        stmt.setString(2, thang);
        stmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(3);

        while (rs.next()) {
            long total = rs.getLong("TONGSO");
            String day = rs.getString("NGAY");
            dayhList.add(new Statistical_day(total, day));
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (dayhList != null) {
            for (Statistical_day item : dayhList) {
                // dataset.addValue(item.getTotal(), "Học viên", item.getYear());
                dataset.addValue(item.getTotal() / 1000000, "Triệu VNĐ", item.getDay());
            }
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "TỔNG DOANH THU THÁNG " + thang + " NĂM " + nam.toUpperCase(),
                "Ngày", "Tổng tiền(Triệu VNĐ)",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

        conn.close();
    }

    public void setDataToChart_product_year(JPanel jpnItem, String nam, String nump) throws SQLException, ClassNotFoundException {
        List<Statistical_top_product> proList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_sp_theo_nam(?,?)}");
        stmt.setString(1, nam);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);
        double sum = 0;
        while (rs.next()) {
            sum = sum + rs.getDouble("TONGSO");
        }

        CallableStatement stmt2 = conn.prepareCall("{call thong_ke_sp_theo_nam_top(?,?,?)}");
        stmt2.setString(1, nam);
        stmt2.setString(2, nump);
        stmt2.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(3);

        while (rs2.next()) {
            String name = rs2.getString("PRONAME");
            double total = rs2.getDouble("TONGSO");
            proList.add(new Statistical_top_product(name, total));
        }

        double sumtop = 0;
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (proList != null) {
            for (Statistical_top_product item : proList) {
                dataset.setValue(item.getProname(), item.getNum());
                sumtop += item.getNum();
            }
            dataset.setValue("Nong san con lai", (sum - sumtop));
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "TOP " + nump + " NÔNG SẢN BÁN CHẠY NHẤT  NĂM " + nam, dataset, true, true, true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

        conn.close();
    }

    public void setDataToChart_product_month(JPanel jpnItem, String nam, String thang, String nump) throws SQLException, ClassNotFoundException {
        List<Statistical_top_product> proList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_sp_theo_thang(?,?,?)}");
        stmt.setString(1, nam);
        stmt.setString(2, thang);
        stmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(3);
        double sum = 0;
        while (rs.next()) {
            sum = sum + rs.getDouble("TONGSO");
        }

        CallableStatement stmt2 = conn.prepareCall("{call thong_ke_sp_theo_thang_top(?,?,?,?)}");
        stmt2.setString(1, nam);
        stmt2.setString(2, thang);
        stmt2.setString(3, nump);
        stmt2.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(4);

        while (rs2.next()) {
            String name = rs2.getString("PRONAME");
            double total = rs2.getDouble("TONGSO");
            proList.add(new Statistical_top_product(name, total));
        }

        double sumtop = 0;
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (proList != null) {
            for (Statistical_top_product item : proList) {
                dataset.setValue(item.getProname(), item.getNum());
                sumtop += item.getNum();
            }
            dataset.setValue("Nong san con lai", (sum - sumtop) / 1000000);
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "TOP " + nump + " NÔNG SẢN BÁN CHẠY NHẤT THÁNG " + thang + " NĂM " + nam, dataset, true, true, true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

        conn.close();
    }

    public void setDataToChart_cus_year(JPanel jpnItem, String nam, String nump) throws SQLException, ClassNotFoundException {
        List<Statistical_top_cus> cusList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_kh_theo_nam(?,?)}");
        stmt.setString(1, nam);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);
        long sum = 0;
        while (rs.next()) {
            sum = sum + rs.getLong("TONGTIEN");
        }

        CallableStatement stmt2 = conn.prepareCall("{call thong_ke_kh_theo_nam_top(?,?,?)}");
        stmt2.setString(1, nam);
        stmt2.setString(2, nump);
        stmt2.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(3);

        while (rs2.next()) {
            String name = rs2.getString("CUSNAME");
            long total = rs2.getLong("TONGTIEN");
            cusList.add(new Statistical_top_cus(name, total));
        }

        long sumtop = 0;
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (cusList != null) {
            for (Statistical_top_cus item : cusList) {
                dataset.setValue(item.getName(), item.getNum() / 1000000);
                sumtop += item.getNum();
            }
            dataset.setValue("Khách hàng còn lai", (sum - sumtop) / 1000000);
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "TOP " + nump + " KHÁCH HÀNG MUA NHIỀU NHẤT NĂM " + nam, dataset, true, true, true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

        conn.close();
    }

    public void setDataToChart_cus_month(JPanel jpnItem, String nam, String thang, String nump) throws SQLException, ClassNotFoundException {
        List<Statistical_top_cus> cusList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_kh_theo_thang(?,?,?)}");
        stmt.setString(1, nam);
        stmt.setString(2, thang);
        stmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(3);
        long sum = 0;
        while (rs.next()) {
            sum = sum + rs.getLong("TONGTIEN");
        }

        CallableStatement stmt2 = conn.prepareCall("{call thong_ke_kh_theo_thang_top(?,?,?,?)}");
        stmt2.setString(1, nam);
        stmt2.setString(2, thang);
        stmt2.setString(3, nump);
        stmt2.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(4);

        while (rs2.next()) {
            String name = rs2.getString("CUSNAME");
            long total = rs2.getLong("TONGTIEN");
            cusList.add(new Statistical_top_cus(name, total));
        }

        long sumtop = 0;
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (cusList != null) {
            for (Statistical_top_cus item : cusList) {
                dataset.setValue(item.getName(), item.getNum() / 1000000);
                sumtop += item.getNum();
            }
            dataset.setValue("Khách hàng còn lai", (sum - sumtop) / 1000000);
        }
        JFreeChart chart = ChartFactory.createPieChart(
                "TOP " + nump + " KHÁCH HÀNG MUA NHIỀU NHẤT THÁNG " + thang + " NĂM " + nam, dataset, true, true, true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

        conn.close();
    }

    public void setData_report_cus_year(String nam, String nump) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        List<Statistical_top_cus> cusList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt2 = conn.prepareCall("{call thong_ke_kh_theo_nam_top(?,?,?)}");
        stmt2.setString(1, nam);
        stmt2.setString(2, nump);
        stmt2.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(3);
        //tao file pdf
        Document doc = new Document();
        String filename = "thong ke khach hang theo nam";
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/Report/" + filename + ".pdf"));

            doc.open();
            File fileFontTieuDe = new File("src/fonts/vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fTieuDe1 = new Font(bfTieuDe, 16);
            //fTieuDe1.setColor(BaseColor.GREEN);

            Font fTieuDe2 = new Font(bfTieuDe, 13);
            // fTieuDe2.setColor(BaseColor.GREEN);

            Font fTieuDe3 = new Font(bfTieuDe, 13);
            Font fTieuDe4 = new Font(bfTieuDe, 12);

            File fileFontNoiDung = new File("src/fonts/vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fNoiDung1 = new Font(bfNoiDung, 13);
            Font fNoiDung2 = new Font(bfNoiDung, 12);
            Font fNoiDung3 = new Font(bfNoiDung, 11);

            //chèn logo anh
            Image logo = Image.getInstance("src/Resources/agriculture.png");
            logo.setAbsolutePosition(150, 750);
            logo.scaleAbsolute(50, 50);
            doc.add(logo);

            Paragraph tenTrangTrai = new Paragraph("FRESH FOOD", fTieuDe1);
            tenTrangTrai.setIndentationLeft(180);
            doc.add(tenTrangTrai);

            Paragraph diaChiTrangTrai = new Paragraph("Địa chỉ: Linh Trung, khu phố 6, TP.Thủ Đức, TP.HCM", fNoiDung2);
            diaChiTrangTrai.setIndentationLeft(180);
            doc.add(diaChiTrangTrai);

            Paragraph hotline = new Paragraph("Hotline: 086.8247.806", fNoiDung2);
            hotline.setIndentationLeft(180);
            doc.add(hotline);

            Paragraph tieuDe = new Paragraph("BÁO CÁO TOP " + nump + " KHÁCH HÀNG TRONG NĂM " + nam, fTieuDe1);
            tieuDe.setAlignment(1);
            tieuDe.setSpacingBefore(40);
            tieuDe.setSpacingAfter(20);
            doc.add(tieuDe);

            Paragraph danhSachKH = new Paragraph("Thông tin các khách hàng: ", fTieuDe3);
            danhSachKH.setSpacingBefore(10);
            danhSachKH.setSpacingAfter(10);
            doc.add(danhSachKH);

            PdfPTable tableKH = new PdfPTable(5);
            tableKH.setWidthPercentage(80);
            tableKH.setSpacingBefore(5);
            tableKH.setSpacingAfter(5);
            float[] tableKH_columnwidth = {50, 150, 150, 150, 150};
            tableKH.setWidths(tableKH_columnwidth);
            //chen cot
            PdfPCell stt = new PdfPCell(new Paragraph("STT", fTieuDe4));
            stt.setBorderColor(BaseColor.BLACK);
            stt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setMinimumHeight(30);
            tableKH.addCell(stt);

            PdfPCell makh = new PdfPCell(new Paragraph("Mã khách hàng", fTieuDe4));
            makh.setBorderColor(BaseColor.BLACK);
            makh.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            makh.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(makh);

            PdfPCell tenkh = new PdfPCell(new Paragraph("Tên khách hàng", fTieuDe4));
            tenkh.setBorderColor(BaseColor.BLACK);
            tenkh.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tenkh.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(tenkh);

            PdfPCell sdt = new PdfPCell(new Paragraph("Số điện thoại", fTieuDe4));
            sdt.setBorderColor(BaseColor.BLACK);
            sdt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            sdt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(sdt);

            PdfPCell tongtien = new PdfPCell(new Paragraph("Tổng tiền đã mua hàng", fTieuDe4));
            tongtien.setBorderColor(BaseColor.BLACK);
            tongtien.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tongtien.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(tongtien);

            int Stt = 1;
            int tongTien = 0;

            while (rs2.next()) {
                PdfPCell stt_text = new PdfPCell(new Paragraph(String.valueOf(Stt), fNoiDung3));
                stt_text.setBorderColor(BaseColor.BLACK);
                stt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setMinimumHeight(30);
                tableKH.addCell(stt_text);

                PdfPCell makh_text = new PdfPCell(new Paragraph(rs2.getString("CUSID"), fNoiDung3));
                makh_text.setBorderColor(BaseColor.BLACK);
                makh_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                makh_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(makh_text);

                PdfPCell tenkh_text = new PdfPCell(new Paragraph(rs2.getString("CUSNAME"), fNoiDung3));
                tenkh_text.setBorderColor(BaseColor.BLACK);
                tenkh_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tenkh_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(tenkh_text);

                PdfPCell sdt_text = new PdfPCell(new Paragraph(rs2.getString("CUSPHONE"), fNoiDung3));
                sdt_text.setBorderColor(BaseColor.BLACK);
                sdt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                sdt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(sdt_text);

                PdfPCell tongtien_text = new PdfPCell(new Paragraph(dinhDangTienTe(rs2.getLong("TONGTIEN")), fNoiDung3));
                tongtien_text.setBorderColor(BaseColor.BLACK);
                tongtien_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tongtien_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(tongtien_text);
                Stt++;
            }
            Paragraph bieudo = new Paragraph("Biểu đồ: ", fTieuDe3);
            bieudo.setSpacingBefore(10);
            bieudo.setSpacingAfter(5);

            BufferedImage bf = null;
            CallableStatement stmt = conn.prepareCall("{call thong_ke_kh_theo_nam(?,?)}");
            stmt.setString(1, nam);
            stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(2);
            long sum = 0;
            while (rs.next()) {
                sum = sum + rs.getLong("TONGTIEN");
            }
            CallableStatement stmt3 = conn.prepareCall("{call thong_ke_kh_theo_nam_top(?,?,?)}");
            stmt3.setString(1, nam);
            stmt3.setString(2, nump);
            stmt3.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            stmt3.execute();
            ResultSet rs3 = (ResultSet) stmt2.getObject(3);

            while (rs3.next()) {
                String name = rs3.getString("CUSNAME");
                long total = rs3.getLong("TONGTIEN");
                cusList.add(new Statistical_top_cus(name, total));
            }
            long sumtop = 0;
            DefaultPieDataset dataset = new DefaultPieDataset();
            if (cusList != null) {
                for (Statistical_top_cus item : cusList) {
                    dataset.setValue(item.getName(), item.getNum() / 1000000);
                    sumtop += item.getNum();
                }
                dataset.setValue("Khách hàng còn lai", (sum - sumtop) / 1000000);
            }
            JFreeChart chart = ChartFactory.createPieChart("", dataset, false, true, false);
            chart.getTitle().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 120));
            PiePlot piePlot = (PiePlot) chart.getPlot();
            piePlot.setShadowPaint(null);
            piePlot.setBackgroundPaint(Color.white);
            piePlot.setOutlinePaint(null);
            piePlot.setLabelBackgroundPaint(Color.white);
            piePlot.setLabelOutlinePaint(Color.white);
            piePlot.setLabelShadowPaint(null);
            piePlot.setLabelFont(new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 10));
            piePlot.setLabelLinkStroke(new BasicStroke(2.0f));
            PieSectionLabelGenerator lb = new StandardPieSectionLabelGenerator("{0} - {2}");
            piePlot.setLabelGenerator(lb);

            bf = chart.createBufferedImage(450, 400);
            Image img = Image.getInstance(writer, bf, 1.0f);
            img.setAlignment(1);
            doc.add(tableKH);
            doc.add(bieudo);
            doc.add(img);

            doc.close();
            writer.close();
            conn.close();

        } catch (DocumentException | IOException | SQLException e) {
        }

        try {
            File file = new File("src/Report/" + filename + ".pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("no");
                return;
            }

            Desktop desk = Desktop.getDesktop();
            if (file.exists()) {
                desk.open(file);
            }

        } catch (IOException e) {
        }
    }

    public void setData_report_cus_month(String nam, String thang, String nump) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        List<Statistical_top_cus> cusList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt2 = conn.prepareCall("{call thong_ke_kh_theo_thang_top(?,?,?,?)}");
        stmt2.setString(1, nam);
        stmt2.setString(2, thang);
        stmt2.setString(3, nump);
        stmt2.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(4);
        //tao file pdf

        Document doc = new Document();
        String filename = "bao cao khach hang theo thang";
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/Report/" + filename + ".pdf"));

            doc.open();
            File fileFontTieuDe = new File("src/fonts/vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fTieuDe1 = new Font(bfTieuDe, 16);
            //fTieuDe1.setColor(BaseColor.GREEN);

            Font fTieuDe2 = new Font(bfTieuDe, 13);
            // fTieuDe2.setColor(BaseColor.GREEN);

            Font fTieuDe3 = new Font(bfTieuDe, 13);
            Font fTieuDe4 = new Font(bfTieuDe, 12);

            File fileFontNoiDung = new File("src/fonts/vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fNoiDung1 = new Font(bfNoiDung, 13);
            Font fNoiDung2 = new Font(bfNoiDung, 12);
            Font fNoiDung3 = new Font(bfNoiDung, 11);

            //chèn logo anh
            Image logo = Image.getInstance("src/Resources/agriculture.png");
            logo.setAbsolutePosition(150, 750);
            logo.scaleAbsolute(50, 50);
            doc.add(logo);

            Paragraph tenTrangTrai = new Paragraph("FRESH FOOD", fTieuDe1);
            tenTrangTrai.setIndentationLeft(180);
            doc.add(tenTrangTrai);

            Paragraph diaChiTrangTrai = new Paragraph("Địa chỉ: Linh Trung, khu phố 6, TP.Thủ Đức, TP.HCM", fNoiDung2);
            diaChiTrangTrai.setIndentationLeft(180);
            doc.add(diaChiTrangTrai);

            Paragraph hotline = new Paragraph("Hotline: 086.8247.806", fNoiDung2);
            hotline.setIndentationLeft(180);
            doc.add(hotline);

            Paragraph tieuDe = new Paragraph("BÁO CÁO TOP " + nump + " KHÁCH HÀNG TRONG THÁNG " + thang + " NĂM " + nam, fTieuDe1);
            tieuDe.setAlignment(1);
            tieuDe.setSpacingBefore(40);
            tieuDe.setSpacingAfter(20);
            doc.add(tieuDe);

            Paragraph danhSachKH = new Paragraph("Thông tin các khách hàng: ", fTieuDe3);
            danhSachKH.setSpacingBefore(10);
            danhSachKH.setSpacingAfter(10);
            doc.add(danhSachKH);

            PdfPTable tableKH = new PdfPTable(5);
            tableKH.setWidthPercentage(80);
            tableKH.setSpacingBefore(5);
            tableKH.setSpacingAfter(5);
            float[] tableKH_columnwidth = {50, 150, 150, 150, 150};
            tableKH.setWidths(tableKH_columnwidth);
            //chen cot
            PdfPCell stt = new PdfPCell(new Paragraph("STT", fTieuDe4));
            stt.setBorderColor(BaseColor.BLACK);
            stt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setMinimumHeight(30);
            tableKH.addCell(stt);

            PdfPCell makh = new PdfPCell(new Paragraph("Mã khách hàng", fTieuDe4));
            makh.setBorderColor(BaseColor.BLACK);
            makh.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            makh.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(makh);

            PdfPCell tenkh = new PdfPCell(new Paragraph("Tên khách hàng", fTieuDe4));
            tenkh.setBorderColor(BaseColor.BLACK);
            tenkh.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tenkh.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(tenkh);

            PdfPCell sdt = new PdfPCell(new Paragraph("Số điện thoại", fTieuDe4));
            sdt.setBorderColor(BaseColor.BLACK);
            sdt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            sdt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(sdt);

            PdfPCell tongtien = new PdfPCell(new Paragraph("Tổng tiền đã mua hàng", fTieuDe4));
            tongtien.setBorderColor(BaseColor.BLACK);
            tongtien.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tongtien.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(tongtien);

            int Stt = 1;
            int tongTien = 0;

            while (rs2.next()) {
                PdfPCell stt_text = new PdfPCell(new Paragraph(String.valueOf(Stt), fNoiDung3));
                stt_text.setBorderColor(BaseColor.BLACK);
                stt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setMinimumHeight(30);
                tableKH.addCell(stt_text);

                PdfPCell makh_text = new PdfPCell(new Paragraph(rs2.getString("CUSID"), fNoiDung3));
                makh_text.setBorderColor(BaseColor.BLACK);
                makh_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                makh_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(makh_text);

                PdfPCell tenkh_text = new PdfPCell(new Paragraph(rs2.getString("CUSNAME"), fNoiDung3));
                tenkh_text.setBorderColor(BaseColor.BLACK);
                tenkh_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tenkh_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(tenkh_text);

                PdfPCell sdt_text = new PdfPCell(new Paragraph(rs2.getString("CUSPHONE"), fNoiDung3));
                sdt_text.setBorderColor(BaseColor.BLACK);
                sdt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                sdt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(sdt_text);

                PdfPCell tongtien_text = new PdfPCell(new Paragraph(dinhDangTienTe(rs2.getLong("TONGTIEN")), fNoiDung3));
                tongtien_text.setBorderColor(BaseColor.BLACK);
                tongtien_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tongtien_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(tongtien_text);
                Stt++;
            }
            Paragraph bieudo = new Paragraph("Biểu đồ: ", fTieuDe3);
            bieudo.setSpacingBefore(10);
            bieudo.setSpacingAfter(5);
            BufferedImage bf = null;

            CallableStatement stmt = conn.prepareCall("{call thong_ke_kh_theo_thang(?,?,?)}");
            stmt.setString(1, nam);
            stmt.setString(2, thang);
            stmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(3);
            long sum = 0;
            while (rs.next()) {
                sum = sum + rs.getLong("TONGTIEN");
            }

            CallableStatement stmt3 = conn.prepareCall("{call thong_ke_kh_theo_thang_top(?,?,?,?)}");
            stmt3.setString(1, nam);
            stmt3.setString(2, thang);
            stmt3.setString(3, nump);
            stmt3.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            stmt3.execute();
            ResultSet rs3 = (ResultSet) stmt2.getObject(4);

            while (rs3.next()) {
                String name = rs3.getString("CUSNAME");
                long total = rs3.getLong("TONGTIEN");
                cusList.add(new Statistical_top_cus(name, total));
            }

            long sumtop = 0;
            DefaultPieDataset dataset = new DefaultPieDataset();
            if (cusList != null) {
                for (Statistical_top_cus item : cusList) {
                    dataset.setValue(item.getName(), item.getNum() / 1000000);
                    sumtop += item.getNum();
                }
                dataset.setValue("Khách hàng còn lai", (sum - sumtop) / 1000000);
            }

            JFreeChart chart = ChartFactory.createPieChart("", dataset, false, true, false);
            chart.getTitle().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 120));
            PiePlot piePlot = (PiePlot) chart.getPlot();
            piePlot.setShadowPaint(null);
            piePlot.setBackgroundPaint(Color.white);
            piePlot.setOutlinePaint(null);
            piePlot.setLabelBackgroundPaint(Color.white);
            piePlot.setLabelOutlinePaint(Color.white);
            piePlot.setLabelShadowPaint(null);
            piePlot.setLabelFont(new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 10));
            piePlot.setLabelLinkStroke(new BasicStroke(2.0f));
            PieSectionLabelGenerator lb = new StandardPieSectionLabelGenerator("{0} - {2}");
            piePlot.setLabelGenerator(lb);

            bf = chart.createBufferedImage(450, 350);
            Image img = Image.getInstance(writer, bf, 1.0f);
            img.setAlignment(1);
            doc.add(tableKH);
            doc.add(bieudo);
            doc.add(img);

            doc.close();
            writer.close();
            conn.close();

        } catch (Exception e) {
        }
        try {
            File file = new File("src/Report/" + filename + ".pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("no");
                return;
            }

            Desktop desk = Desktop.getDesktop();
            if (file.exists()) {
                desk.open(file);
            }
        } catch (Exception e) {
        }
    }

    public void setData_report_pro_month(String nam, String thang, String nump) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        List<Statistical_top_product> cusList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt2 = conn.prepareCall("{call thong_ke_sp_theo_thang_top(?,?,?,?)}");
        stmt2.setString(1, nam);
        stmt2.setString(2, thang);
        stmt2.setString(3, nump);
        stmt2.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(4);
        //tao file pdf

        Document doc = new Document();
        String filename = "bao cao san pham theo thang";
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/Report/" + filename + ".pdf"));

            doc.open();
            File fileFontTieuDe = new File("src/fonts/vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fTieuDe1 = new Font(bfTieuDe, 16);
            //fTieuDe1.setColor(BaseColor.GREEN);

            Font fTieuDe2 = new Font(bfTieuDe, 13);
            // fTieuDe2.setColor(BaseColor.GREEN);

            Font fTieuDe3 = new Font(bfTieuDe, 13);
            Font fTieuDe4 = new Font(bfTieuDe, 12);

            File fileFontNoiDung = new File("src/fonts/vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fNoiDung1 = new Font(bfNoiDung, 13);
            Font fNoiDung2 = new Font(bfNoiDung, 12);
            Font fNoiDung3 = new Font(bfNoiDung, 11);

            //chèn logo anh
            Image logo = Image.getInstance("src/Resources/agriculture.png");
            logo.setAbsolutePosition(150, 750);
            logo.scaleAbsolute(50, 50);
            doc.add(logo);

            Paragraph tenTrangTrai = new Paragraph("FRESH FOOD", fTieuDe1);
            tenTrangTrai.setIndentationLeft(180);
            doc.add(tenTrangTrai);

            Paragraph diaChiTrangTrai = new Paragraph("Địa chỉ: Linh Trung, khu phố 6, TP.Thủ Đức, TP.HCM", fNoiDung2);
            diaChiTrangTrai.setIndentationLeft(180);
            doc.add(diaChiTrangTrai);

            Paragraph hotline = new Paragraph("Hotline: 086.8247.806", fNoiDung2);
            hotline.setIndentationLeft(180);
            doc.add(hotline);

            Paragraph tieuDe = new Paragraph("BÁO CÁO TOP " + nump + " SẢN PHẨM BÁN CHẠY TRONG THÁNG " + thang + " NĂM " + nam, fTieuDe1);
            tieuDe.setAlignment(1);
            tieuDe.setSpacingBefore(40);
            tieuDe.setSpacingAfter(20);
            doc.add(tieuDe);

            Paragraph danhSachKH = new Paragraph("Thông tin các sản phẩm: ", fTieuDe3);
            danhSachKH.setSpacingBefore(10);
            danhSachKH.setSpacingAfter(10);
            doc.add(danhSachKH);

            PdfPTable tableKH = new PdfPTable(5);
            tableKH.setWidthPercentage(80);
            tableKH.setSpacingBefore(5);
            tableKH.setSpacingAfter(5);
            float[] tableKH_columnwidth = {50, 150, 150, 150, 150};
            tableKH.setWidths(tableKH_columnwidth);
            //chen cot
            PdfPCell stt = new PdfPCell(new Paragraph("STT", fTieuDe4));
            stt.setBorderColor(BaseColor.BLACK);
            stt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setMinimumHeight(30);
            tableKH.addCell(stt);

            PdfPCell makh = new PdfPCell(new Paragraph("Mã sản phẩm", fTieuDe4));
            makh.setBorderColor(BaseColor.BLACK);
            makh.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            makh.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(makh);

            PdfPCell tenkh = new PdfPCell(new Paragraph("Tên sản phẩm", fTieuDe4));
            tenkh.setBorderColor(BaseColor.BLACK);
            tenkh.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tenkh.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(tenkh);

            PdfPCell sdt = new PdfPCell(new Paragraph("Loại sản phẩm", fTieuDe4));
            sdt.setBorderColor(BaseColor.BLACK);
            sdt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            sdt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(sdt);

            PdfPCell tongtien = new PdfPCell(new Paragraph("Tổng số lượng đã bán", fTieuDe4));
            tongtien.setBorderColor(BaseColor.BLACK);
            tongtien.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tongtien.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(tongtien);

            int Stt = 1;
            int tongTien = 0;

            while (rs2.next()) {
                PdfPCell stt_text = new PdfPCell(new Paragraph(String.valueOf(Stt), fNoiDung3));
                stt_text.setBorderColor(BaseColor.BLACK);
                stt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setMinimumHeight(30);
                tableKH.addCell(stt_text);

                PdfPCell makh_text = new PdfPCell(new Paragraph(rs2.getString("PROID"), fNoiDung3));
                makh_text.setBorderColor(BaseColor.BLACK);
                makh_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                makh_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(makh_text);

                PdfPCell tenkh_text = new PdfPCell(new Paragraph(rs2.getString("PRONAME"), fNoiDung3));
                tenkh_text.setBorderColor(BaseColor.BLACK);
                tenkh_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tenkh_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(tenkh_text);

                PdfPCell sdt_text = new PdfPCell(new Paragraph(rs2.getString("PROTYPE"), fNoiDung3));
                sdt_text.setBorderColor(BaseColor.BLACK);
                sdt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                sdt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(sdt_text);

                PdfPCell tongtien_text = new PdfPCell(new Paragraph(dinhDangTienTe(rs2.getLong("TONGSO")), fNoiDung3));
                tongtien_text.setBorderColor(BaseColor.BLACK);
                tongtien_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tongtien_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(tongtien_text);
                Stt++;
            }
            Paragraph bieudo = new Paragraph("Biểu đồ: ", fTieuDe3);
            bieudo.setSpacingBefore(10);
            bieudo.setSpacingAfter(5);
            BufferedImage bf = null;

            CallableStatement stmt = conn.prepareCall("{call thong_ke_sp_theo_thang(?,?,?)}");
            stmt.setString(1, nam);
            stmt.setString(2, thang);
            stmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(3);
            long sum = 0;
            while (rs.next()) {
                sum = sum + rs.getLong("TONGSO");
            }

            CallableStatement stmt3 = conn.prepareCall("{call thong_ke_sp_theo_thang_top(?,?,?,?)}");
            stmt3.setString(1, nam);
            stmt3.setString(2, thang);
            stmt3.setString(3, nump);
            stmt3.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            stmt3.execute();
            ResultSet rs3 = (ResultSet) stmt2.getObject(4);

            while (rs3.next()) {
                String name = rs3.getString("PRONAME");
                long total = rs3.getLong("TONGSO");
                cusList.add(new Statistical_top_product(name, total));
            }

            long sumtop = 0;
            DefaultPieDataset dataset = new DefaultPieDataset();
            if (cusList != null) {
                for (Statistical_top_product item : cusList) {
                    dataset.setValue(item.getProname(), item.getNum() / 1000000);
                    sumtop += item.getNum();
                }
                dataset.setValue("Sản phẩm còn lai", (sum - sumtop) / 1000000);
            }

            JFreeChart chart = ChartFactory.createPieChart("", dataset, false, true, false);
            chart.getTitle().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 120));
            PiePlot piePlot = (PiePlot) chart.getPlot();
            piePlot.setShadowPaint(null);
            piePlot.setBackgroundPaint(Color.white);
            piePlot.setOutlinePaint(null);
            piePlot.setLabelBackgroundPaint(Color.white);
            piePlot.setLabelOutlinePaint(Color.white);
            piePlot.setLabelShadowPaint(null);
            piePlot.setLabelFont(new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 10));
            piePlot.setLabelLinkStroke(new BasicStroke(2.0f));
            PieSectionLabelGenerator lb = new StandardPieSectionLabelGenerator("{0} - {2}");
            piePlot.setLabelGenerator(lb);

            bf = chart.createBufferedImage(450, 350);
            Image img = Image.getInstance(writer, bf, 1.0f);
            img.setAlignment(1);
            doc.add(tableKH);
            doc.add(bieudo);
            doc.add(img);

            doc.close();
            writer.close();
            conn.close();

        } catch (Exception e) {
        }
        try {
            File file = new File("src/Report/" + filename + ".pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("no");
                return;
            }

            Desktop desk = Desktop.getDesktop();
            if (file.exists()) {
                desk.open(file);
            }
        } catch (Exception e) {
        }
    }

    public void setData_report_pro_year(String nam, String nump) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        List<Statistical_top_product> proList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt2 = conn.prepareCall("{call thong_ke_sp_theo_nam_top(?,?,?)}");
        stmt2.setString(1, nam);
        stmt2.setString(2, nump);
        stmt2.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(3);
        //tao file pdf

        Document doc = new Document();
        String filename = "bao cao san pham theo nam";
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/Report/" + filename + ".pdf"));

            doc.open();
            File fileFontTieuDe = new File("src/fonts/vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fTieuDe1 = new Font(bfTieuDe, 16);
            //fTieuDe1.setColor(BaseColor.GREEN);

            Font fTieuDe2 = new Font(bfTieuDe, 13);
            // fTieuDe2.setColor(BaseColor.GREEN);

            Font fTieuDe3 = new Font(bfTieuDe, 13);
            Font fTieuDe4 = new Font(bfTieuDe, 12);

            File fileFontNoiDung = new File("src/fonts/vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fNoiDung1 = new Font(bfNoiDung, 13);
            Font fNoiDung2 = new Font(bfNoiDung, 12);
            Font fNoiDung3 = new Font(bfNoiDung, 11);

            //chèn logo anh
            Image logo = Image.getInstance("src/Resources/agriculture.png");
            logo.setAbsolutePosition(150, 750);
            logo.scaleAbsolute(50, 50);
            doc.add(logo);

            Paragraph tenTrangTrai = new Paragraph("FRESH FOOD", fTieuDe1);
            tenTrangTrai.setIndentationLeft(180);
            doc.add(tenTrangTrai);

            Paragraph diaChiTrangTrai = new Paragraph("Địa chỉ: Linh Trung, khu phố 6, TP.Thủ Đức, TP.HCM", fNoiDung2);
            diaChiTrangTrai.setIndentationLeft(180);
            doc.add(diaChiTrangTrai);

            Paragraph hotline = new Paragraph("Hotline: 086.8247.806", fNoiDung2);
            hotline.setIndentationLeft(180);
            doc.add(hotline);

            Paragraph tieuDe = new Paragraph("BÁO CÁO TOP " + nump + " SẢN PHẨM BÁN CHẠY TRONG" + " NĂM " + nam, fTieuDe1);
            tieuDe.setAlignment(1);
            tieuDe.setSpacingBefore(40);
            tieuDe.setSpacingAfter(20);
            doc.add(tieuDe);

            Paragraph danhSachKH = new Paragraph("Thông tin các sản phẩm: ", fTieuDe3);
            danhSachKH.setSpacingBefore(10);
            danhSachKH.setSpacingAfter(10);
            doc.add(danhSachKH);

            PdfPTable tableKH = new PdfPTable(5);
            tableKH.setWidthPercentage(80);
            tableKH.setSpacingBefore(5);
            tableKH.setSpacingAfter(5);
            float[] tableKH_columnwidth = {50, 150, 150, 150, 150};
            tableKH.setWidths(tableKH_columnwidth);
            //chen cot
            PdfPCell stt = new PdfPCell(new Paragraph("STT", fTieuDe4));
            stt.setBorderColor(BaseColor.BLACK);
            stt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setMinimumHeight(30);
            tableKH.addCell(stt);

            PdfPCell makh = new PdfPCell(new Paragraph("Mã sản phẩm", fTieuDe4));
            makh.setBorderColor(BaseColor.BLACK);
            makh.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            makh.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(makh);

            PdfPCell tenkh = new PdfPCell(new Paragraph("Tên sản phẩm", fTieuDe4));
            tenkh.setBorderColor(BaseColor.BLACK);
            tenkh.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tenkh.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(tenkh);

            PdfPCell sdt = new PdfPCell(new Paragraph("Loại sản phẩm", fTieuDe4));
            sdt.setBorderColor(BaseColor.BLACK);
            sdt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            sdt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(sdt);

            PdfPCell tongtien = new PdfPCell(new Paragraph("Tổng số lượng đã bán", fTieuDe4));
            tongtien.setBorderColor(BaseColor.BLACK);
            tongtien.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tongtien.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableKH.addCell(tongtien);

            int Stt = 1;
            int tongTien = 0;

            while (rs2.next()) {
                PdfPCell stt_text = new PdfPCell(new Paragraph(String.valueOf(Stt), fNoiDung3));
                stt_text.setBorderColor(BaseColor.BLACK);
                stt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setMinimumHeight(30);
                tableKH.addCell(stt_text);

                PdfPCell makh_text = new PdfPCell(new Paragraph(rs2.getString("PROID"), fNoiDung3));
                makh_text.setBorderColor(BaseColor.BLACK);
                makh_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                makh_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(makh_text);

                PdfPCell tenkh_text = new PdfPCell(new Paragraph(rs2.getString("PRONAME"), fNoiDung3));
                tenkh_text.setBorderColor(BaseColor.BLACK);
                tenkh_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tenkh_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(tenkh_text);

                PdfPCell sdt_text = new PdfPCell(new Paragraph(rs2.getString("PROTYPE"), fNoiDung3));
                sdt_text.setBorderColor(BaseColor.BLACK);
                sdt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                sdt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(sdt_text);

                PdfPCell tongtien_text = new PdfPCell(new Paragraph(dinhDangTienTe(rs2.getLong("TONGSO")), fNoiDung3));
                tongtien_text.setBorderColor(BaseColor.BLACK);
                tongtien_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tongtien_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableKH.addCell(tongtien_text);
                Stt++;
            }
            Paragraph bieudo = new Paragraph("Biểu đồ: ", fTieuDe3);
            bieudo.setSpacingBefore(10);
            bieudo.setSpacingAfter(5);
            BufferedImage bf = null;

            CallableStatement stmt = conn.prepareCall("{call thong_ke_sp_theo_nam(?,?)}");
            stmt.setString(1, nam);
            stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();
            ResultSet rs = (ResultSet) stmt.getObject(2);
            double sum = 0;
            while (rs.next()) {
                sum = sum + rs.getDouble("TONGSO");
            }

            CallableStatement stmt3 = conn.prepareCall("{call thong_ke_sp_theo_nam_top(?,?,?)}");
            stmt3.setString(1, nam);
            stmt3.setString(2, nump);
            stmt3.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            stmt3.execute();
            ResultSet rs3 = (ResultSet) stmt3.getObject(3);

            while (rs3.next()) {
                String name = rs3.getString("PRONAME");
                double total = rs3.getDouble("TONGSO");
                proList.add(new Statistical_top_product(name, total));
            }

            double sumtop = 0;
            DefaultPieDataset dataset = new DefaultPieDataset();
            if (proList != null) {
                for (Statistical_top_product item : proList) {
                    dataset.setValue(item.getProname(), item.getNum());
                    sumtop += item.getNum();
                }
                dataset.setValue("Nong san con lai", (sum - sumtop));
            }

            JFreeChart chart = ChartFactory.createPieChart("", dataset, false, true, false);
            chart.getTitle().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 120));
            PiePlot piePlot = (PiePlot) chart.getPlot();
            piePlot.setShadowPaint(null);
            piePlot.setBackgroundPaint(Color.white);
            piePlot.setOutlinePaint(null);
            piePlot.setLabelBackgroundPaint(Color.white);
            piePlot.setLabelOutlinePaint(Color.white);
            piePlot.setLabelShadowPaint(null);
            piePlot.setLabelFont(new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 10));
            piePlot.setLabelLinkStroke(new BasicStroke(2.0f));
            PieSectionLabelGenerator lb = new StandardPieSectionLabelGenerator("{0} - {2}");
            piePlot.setLabelGenerator(lb);

            bf = chart.createBufferedImage(450, 350);
            Image img = Image.getInstance(writer, bf, 1.0f);
            img.setAlignment(1);
            doc.add(tableKH);
            doc.add(bieudo);
            doc.add(img);

            doc.close();
            writer.close();
            conn.close();

        } catch (Exception e) {
        }
        try {
            File file = new File("src/Report/" + filename + ".pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("no");
                return;
            }

            Desktop desk = Desktop.getDesktop();
            if (file.exists()) {
                desk.open(file);
            }
        } catch (Exception e) {
        }
    }

    public void setData_report_revenue_year() throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        List<Statistical_top_product> proList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();

        CallableStatement stmt2 = conn.prepareCall("{call THONG_KE_THEO_NAM(?)}");
        stmt2.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt2.execute();
        ResultSet rs2 = (ResultSet) stmt2.getObject(1);
        //tao file pdf

        Document doc = new Document();
        String filename = "bao_cao_doanh_thu_theo_nam";
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/Report/" + filename + ".pdf"));

            doc.open();
            File fileFontTieuDe = new File("src/fonts/vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fTieuDe1 = new Font(bfTieuDe, 16);

            Font fTieuDe2 = new Font(bfTieuDe, 13);

            Font fTieuDe3 = new Font(bfTieuDe, 13);
            Font fTieuDe4 = new Font(bfTieuDe, 12);

            File fileFontNoiDung = new File("src/fonts/vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fNoiDung1 = new Font(bfNoiDung, 13);
            Font fNoiDung2 = new Font(bfNoiDung, 12);
            Font fNoiDung3 = new Font(bfNoiDung, 11);

            //chèn logo anh
            Image logo = Image.getInstance("src/Resources/agriculture.png");
            logo.setAbsolutePosition(150, 750);
            logo.scaleAbsolute(50, 50);
            doc.add(logo);

            Paragraph tenTrangTrai = new Paragraph("FRESH FOOD", fTieuDe1);
            tenTrangTrai.setIndentationLeft(180);
            doc.add(tenTrangTrai);

            Paragraph diaChiTrangTrai = new Paragraph("Địa chỉ: Linh Trung, khu phố 6, TP.Thủ Đức, TP.HCM", fNoiDung2);
            diaChiTrangTrai.setIndentationLeft(180);
            doc.add(diaChiTrangTrai);

            Paragraph hotline = new Paragraph("Hotline: 086.8247.806", fNoiDung2);
            hotline.setIndentationLeft(180);
            doc.add(hotline);

            Paragraph tieuDe = new Paragraph("BÁO CÁO DOANH THU THEO NĂM", fTieuDe1);
            tieuDe.setAlignment(1);
            tieuDe.setSpacingBefore(40);
            tieuDe.setSpacingAfter(20);
            doc.add(tieuDe);

            Paragraph danhSachDT = new Paragraph("Thông tin doanh thu: ", fTieuDe3);
            danhSachDT.setSpacingBefore(10);
            danhSachDT.setSpacingAfter(10);
            doc.add(danhSachDT);

            PdfPTable tableDT = new PdfPTable(3);
            tableDT.setWidthPercentage(80);
            tableDT.setSpacingBefore(5);
            tableDT.setSpacingAfter(5);
            float[] tableKH_columnwidth = {50, 150, 150};
            tableDT.setWidths(tableKH_columnwidth);
            //chen cot
            PdfPCell stt = new PdfPCell(new Paragraph("STT", fTieuDe4));
            stt.setBorderColor(BaseColor.BLACK);
            stt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setMinimumHeight(30);
            tableDT.addCell(stt);

            PdfPCell namdt = new PdfPCell(new Paragraph("Năm", fTieuDe4));
            namdt.setBorderColor(BaseColor.BLACK);
            namdt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            namdt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableDT.addCell(namdt);

            PdfPCell celldoanhthu = new PdfPCell(new Paragraph("Doanh Thu", fTieuDe4));
            celldoanhthu.setBorderColor(BaseColor.BLACK);
            celldoanhthu.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            celldoanhthu.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableDT.addCell(celldoanhthu);

            int Stt = 1;
            int tongTien = 0;

            while (rs2.next()) {
                PdfPCell stt_text = new PdfPCell(new Paragraph(String.valueOf(Stt), fNoiDung3));
                stt_text.setBorderColor(BaseColor.BLACK);
                stt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setMinimumHeight(30);
                tableDT.addCell(stt_text);

                PdfPCell nam_text = new PdfPCell(new Paragraph(rs2.getString("NAM"), fNoiDung3));
                nam_text.setBorderColor(BaseColor.BLACK);
                nam_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                nam_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableDT.addCell(nam_text);

                PdfPCell doanhthu_text = new PdfPCell(new Paragraph(rs2.getString("TONGSO"), fNoiDung3));
                doanhthu_text.setBorderColor(BaseColor.BLACK);
                doanhthu_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                doanhthu_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableDT.addCell(doanhthu_text);

                tongTien = tongTien + rs2.getInt("TONGSO");

                Stt++;
            }

            PdfPCell cellTongCong = new PdfPCell(new Paragraph("TỔNG CỘNG:", fTieuDe4));
            cellTongCong.setColspan(2);
            cellTongCong.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cellTongCong.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            cellTongCong.setMinimumHeight(20);
            tableDT.addCell(cellTongCong);

            PdfPCell cellTongtien = new PdfPCell(new Paragraph(dinhDangTienTe(tongTien), fTieuDe4));
            cellTongtien.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            cellTongtien.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            tableDT.addCell(cellTongtien);

            Paragraph bieudo = new Paragraph("Biểu đồ: ", fTieuDe3);
            bieudo.setSpacingBefore(10);
            bieudo.setSpacingAfter(5);
            BufferedImage bf = null;

            doc.add(tableDT);
            BufferedImage bufferedImage = CreateBarChartForYear();
            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
            image.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            image.scaleAbsolute(400, 400);
            doc.add(image);

           

            doc.close();
            writer.close();
            conn.close();

        } catch (DocumentException | IOException | ClassNotFoundException | SQLException e) {
        }
        try {
            File file = new File("src/Report/" + filename + ".pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("no");
                return;
            }

            Desktop desk = Desktop.getDesktop();
            if (file.exists()) {
                desk.open(file);
            }
        } catch (IOException e) {
        }
    }

    public String dinhDangTienTe(long soTien) {
        Locale localeEn = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEn);

        String str = en.format(soTien);
        return str;
    }

    public BufferedImage CreateBarChartForYear() throws SQLException, ClassNotFoundException {
        List<Statistical_year> yearList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_theo_nam(?)}");
        stmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            long total = rs.getLong("TONGSO");
            String year = rs.getString("nam");
            yearList.add(new Statistical_year(total, year));
        }

        //Tao dataset cho bieu do cot
        DefaultCategoryDataset bcdt_dataset = new DefaultCategoryDataset();
        if (yearList != null) {
            for (Statistical_year item : yearList) {
                bcdt_dataset.addValue(item.getTotal() / 1000000, "Triệu VNĐ", item.getYear());
            }
        }
        BufferedImage bufferedImage = null;
        try {
            JFreeChart chart = ChartFactory.createBarChart(
                    "TỔNG DOANH THU THEO NĂM".toUpperCase(),
                    "Năm", "Tổng tiền(Triệu VNĐ)",
                    bcdt_dataset, PlotOrientation.VERTICAL, false, true, false);

            //set cac dinh dang font
            java.awt.Font bc_fontTieuDe = new java.awt.Font("Tahoma",
                    java.awt.Font.BOLD, 120);

            java.awt.Font bc_fontNoiDung1 = new java.awt.Font("Tahoma",
                    java.awt.Font.PLAIN, 80);

            java.awt.Font bc_fontNoiDung2 = new java.awt.Font("Tahoma",
                    java.awt.Font.ITALIC, 80);

            //set font cho chart title
            chart.getTitle().setFont(bc_fontTieuDe);

            //set mau nen, an border, tat shadow cua bieu do va label
            CategoryPlot Plot = (CategoryPlot) chart.getPlot();
            ((BarRenderer) Plot.getRenderer()).setBarPainter(new StandardBarPainter());
            Plot.setBackgroundPaint(Color.white);
            Plot.setOutlinePaint(null);

            CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();

            //set hien thi value tren cac cot
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setDefaultItemLabelsVisible(true);

            ItemLabelPosition position = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
            renderer.setDefaultNegativeItemLabelPosition(position);
            renderer.setDefaultItemLabelFont(bc_fontNoiDung1);

            //Dinh dang doumain axis ~nam
            CategoryAxis bcdt_DoumainAxis = Plot.getDomainAxis();
            bcdt_DoumainAxis.setTickLabelFont(bc_fontNoiDung1);
            bcdt_DoumainAxis.setLabelFont(bc_fontNoiDung2);

            //Dinh dang range axis ~doanhthu
            ValueAxis bcdt_RangeAxis = Plot.getRangeAxis();
            bcdt_RangeAxis.setTickLabelFont(bc_fontNoiDung1);
            bcdt_RangeAxis.setLabelFont(bc_fontNoiDung2);

            bufferedImage = chart.createBufferedImage(5000, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn.close();
        return bufferedImage;
    }

    public BufferedImage CreateBarChartForMonth(String nam) throws SQLException, ClassNotFoundException {
        List<Statistical_month> monthList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call THONG_KE_THEO_THANG(?,?)}");
        stmt.setString(1, nam);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(2);

        while (rs.next()) {
            long total = rs.getLong("TONGSO");
            String month = rs.getString("THANG");
            monthList.add(new Statistical_month(total, month));
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (monthList != null) {
            for (Statistical_month item : monthList) {
                dataset.addValue(item.getTotal() / 1000000, "Triệu VNĐ", item.getMonth());
            }
        }

        BufferedImage bufferedImage = null;
        try {
            JFreeChart barChart = ChartFactory.createBarChart(
                    "TỔNG DOANH THU NĂM " + nam,
                    "Tháng", "Tổng tiền(Triệu VNĐ)",
                    dataset, PlotOrientation.VERTICAL, false, true, false);

            //set cac dinh dang font
            java.awt.Font bc_fontTieuDe = new java.awt.Font("Tahoma",
                    java.awt.Font.BOLD, 120);

            java.awt.Font bc_fontNoiDung1 = new java.awt.Font("Tahoma",
                    java.awt.Font.PLAIN, 80);

            java.awt.Font bc_fontNoiDung2 = new java.awt.Font("Tahoma",
                    java.awt.Font.ITALIC, 80);

            //set font cho chart title
            barChart.getTitle().setFont(bc_fontTieuDe);

            //set mau nen, an border, tat shadow cua bieu do va label
            CategoryPlot Plot = (CategoryPlot) barChart.getPlot();
            ((BarRenderer) Plot.getRenderer()).setBarPainter(new StandardBarPainter());
            Plot.setBackgroundPaint(Color.white);
            Plot.setOutlinePaint(null);

            CategoryItemRenderer renderer = ((CategoryPlot) barChart.getPlot()).getRenderer();

            //set hien thi value tren cac cot
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setDefaultItemLabelsVisible(true);

            ItemLabelPosition position = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
            renderer.setDefaultNegativeItemLabelPosition(position);
            renderer.setDefaultItemLabelFont(bc_fontNoiDung1);

            //Dinh dang doumain axis ~nam
            CategoryAxis bcdt_DoumainAxis = Plot.getDomainAxis();
            bcdt_DoumainAxis.setTickLabelFont(bc_fontNoiDung1);
            bcdt_DoumainAxis.setLabelFont(bc_fontNoiDung2);

            //Dinh dang range axis ~doanhthu
            ValueAxis bcdt_RangeAxis = Plot.getRangeAxis();
            bcdt_RangeAxis.setTickLabelFont(bc_fontNoiDung1);
            bcdt_RangeAxis.setLabelFont(bc_fontNoiDung2);

            bufferedImage = barChart.createBufferedImage(5000, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn.close();
        return bufferedImage;
    }

    public void setData_report_revenue_month(String nam) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        List<Statistical_month> monthList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call THONG_KE_THEO_THANG(?,?)}");
        stmt.setString(1, nam);
        stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs3 = (ResultSet) stmt.getObject(2);

        //tao file pdf
        Document doc = new Document();
        String filename = "bao_cao_doanh_thu_theo_thang";
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/Report/" + filename + ".pdf"));

            doc.open();
            File fileFontTieuDe = new File("src/fonts/vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fTieuDe1 = new Font(bfTieuDe, 16);
            //fTieuDe1.setColor(BaseColor.GREEN);

            Font fTieuDe2 = new Font(bfTieuDe, 13);
            // fTieuDe2.setColor(BaseColor.GREEN);

            Font fTieuDe3 = new Font(bfTieuDe, 13);
            Font fTieuDe4 = new Font(bfTieuDe, 12);

            File fileFontNoiDung = new File("src/fonts/vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fNoiDung1 = new Font(bfNoiDung, 13);
            Font fNoiDung2 = new Font(bfNoiDung, 12);
            Font fNoiDung3 = new Font(bfNoiDung, 11);

            //chèn logo anh
            Image logo = Image.getInstance("src/Resources/agriculture.png");
            logo.setAbsolutePosition(150, 750);
            logo.scaleAbsolute(50, 50);
            doc.add(logo);

            Paragraph tenTrangTrai = new Paragraph("FRESH FOOD", fTieuDe1);
            tenTrangTrai.setIndentationLeft(180);
            doc.add(tenTrangTrai);

            Paragraph diaChiTrangTrai = new Paragraph("Địa chỉ: Linh Trung, khu phố 6, TP.Thủ Đức, TP.HCM", fNoiDung2);
            diaChiTrangTrai.setIndentationLeft(180);
            doc.add(diaChiTrangTrai);

            Paragraph hotline = new Paragraph("Hotline: 086.8247.806", fNoiDung2);
            hotline.setIndentationLeft(180);
            doc.add(hotline);

            Paragraph tieuDe = new Paragraph("BÁO CÁO DOANH THU THEO THÁNG TRONG NĂM " + nam, fTieuDe1);
            tieuDe.setAlignment(1);
            tieuDe.setSpacingBefore(40);
            tieuDe.setSpacingAfter(20);
            doc.add(tieuDe);

            Paragraph danhSachKH = new Paragraph("Thông tin doanh thu: ", fTieuDe3);
            danhSachKH.setSpacingBefore(10);
            danhSachKH.setSpacingAfter(10);
            doc.add(danhSachKH);

            PdfPTable tableDT = new PdfPTable(3);
            tableDT.setWidthPercentage(80);
            tableDT.setSpacingBefore(5);
            tableDT.setSpacingAfter(5);
            float[] tableKH_columnwidth = {50, 150, 150};
            tableDT.setWidths(tableKH_columnwidth);
            //chen cot
            PdfPCell stt = new PdfPCell(new Paragraph("STT", fTieuDe4));
            stt.setBorderColor(BaseColor.BLACK);
            stt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setMinimumHeight(30);
            tableDT.addCell(stt);

            PdfPCell cellthang = new PdfPCell(new Paragraph("Tháng", fTieuDe4));
            cellthang.setBorderColor(BaseColor.BLACK);
            cellthang.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cellthang.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableDT.addCell(cellthang);

            PdfPCell celldoanhthu = new PdfPCell(new Paragraph("Doanh Thu", fTieuDe4));
            celldoanhthu.setBorderColor(BaseColor.BLACK);
            celldoanhthu.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            celldoanhthu.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableDT.addCell(celldoanhthu);

            int Stt = 1;
            int tongTien = 0;

            while (rs3.next()) {
                PdfPCell stt_text = new PdfPCell(new Paragraph(String.valueOf(Stt), fNoiDung3));
                stt_text.setBorderColor(BaseColor.BLACK);
                stt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setMinimumHeight(30);
                tableDT.addCell(stt_text);

                PdfPCell thang_text = new PdfPCell(new Paragraph(rs3.getString("THANG"), fNoiDung3));
                thang_text.setBorderColor(BaseColor.BLACK);
                thang_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                thang_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableDT.addCell(thang_text);

                PdfPCell doanhthu_text = new PdfPCell(new Paragraph(rs3.getString("TONGSO"), fNoiDung3));
                doanhthu_text.setBorderColor(BaseColor.BLACK);
                doanhthu_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                doanhthu_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableDT.addCell(doanhthu_text);

                tongTien = tongTien + rs3.getInt("TONGSO");

                Stt++;
            }

            PdfPCell cellTongCong = new PdfPCell(new Paragraph("TỔNG CỘNG:", fTieuDe4));
            cellTongCong.setColspan(2);
            cellTongCong.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cellTongCong.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            cellTongCong.setMinimumHeight(20);
            tableDT.addCell(cellTongCong);

            PdfPCell cellTongtien = new PdfPCell(new Paragraph(dinhDangTienTe(tongTien), fTieuDe4));
            cellTongtien.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            cellTongtien.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            tableDT.addCell(cellTongtien);

            Paragraph bieudo = new Paragraph("Biểu đồ: ", fTieuDe3);
            bieudo.setSpacingBefore(10);
            bieudo.setSpacingAfter(5);
            BufferedImage bf = null;

            doc.add(tableDT);
            BufferedImage bufferedImage = CreateBarChartForMonth(nam);
            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
            image.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            image.scaleAbsolute(400, 400);
            doc.add(image);
            
            
            

            doc.close();
            writer.close();
            conn.close();

        } catch (DocumentException | IOException | ClassNotFoundException | SQLException e) {
        }
        try {
            File file = new File("src/Report/" + filename + ".pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("no");
                return;
            }

            Desktop desk = Desktop.getDesktop();
            if (file.exists()) {
                desk.open(file);
            }
        } catch (IOException e) {
        }

    }

    public BufferedImage CreateBarChartForDay(String thang, String nam) throws SQLException, ClassNotFoundException {
        List<Statistical_day> dayhList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_theo_ngay(?,?,?)}");
        stmt.setString(1, nam);
        stmt.setString(2, thang);
        stmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(3);

        while (rs.next()) {
            long total = rs.getLong("TONGSO");
            String day = rs.getString("NGAY");
            dayhList.add(new Statistical_day(total, day));
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (dayhList != null) {
            for (Statistical_day item : dayhList) {
                dataset.addValue(item.getTotal() / 1000000, "Triệu VNĐ", item.getDay());
            }
        }

        BufferedImage bufferedImage = null;
        try {
            JFreeChart barChart = ChartFactory.createBarChart(
                    "TỔNG DOANH THU NĂM " + nam,
                    "Tháng", "Tổng tiền(Triệu VNĐ)",
                    dataset, PlotOrientation.VERTICAL, false, true, false);

            //set cac dinh dang font
            java.awt.Font bc_fontTieuDe = new java.awt.Font("Tahoma",
                    java.awt.Font.BOLD, 120);

            java.awt.Font bc_fontNoiDung1 = new java.awt.Font("Tahoma",
                    java.awt.Font.PLAIN, 80);

            java.awt.Font bc_fontNoiDung2 = new java.awt.Font("Tahoma",
                    java.awt.Font.ITALIC, 80);

            //set font cho chart title
            barChart.getTitle().setFont(bc_fontTieuDe);

            //set mau nen, an border, tat shadow cua bieu do va label
            CategoryPlot Plot = (CategoryPlot) barChart.getPlot();
            ((BarRenderer) Plot.getRenderer()).setBarPainter(new StandardBarPainter());
            Plot.setBackgroundPaint(Color.white);
            Plot.setOutlinePaint(null);

            CategoryItemRenderer renderer = ((CategoryPlot) barChart.getPlot()).getRenderer();

            //set hien thi value tren cac cot
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setDefaultItemLabelsVisible(true);

            ItemLabelPosition position = new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
            renderer.setDefaultNegativeItemLabelPosition(position);
            renderer.setDefaultItemLabelFont(bc_fontNoiDung1);

            //Dinh dang doumain axis ~nam
            CategoryAxis bcdt_DoumainAxis = Plot.getDomainAxis();
            bcdt_DoumainAxis.setTickLabelFont(bc_fontNoiDung1);
            bcdt_DoumainAxis.setLabelFont(bc_fontNoiDung2);

            //Dinh dang range axis ~doanhthu
            ValueAxis bcdt_RangeAxis = Plot.getRangeAxis();
            bcdt_RangeAxis.setTickLabelFont(bc_fontNoiDung1);
            bcdt_RangeAxis.setLabelFont(bc_fontNoiDung2);
            //bcdt_RangeAxis.getRangeAxis().setFixedDimension(100);

//             DecimalFormat pctFormat = new DecimalFormat("##.0");
//             pctFormat.setMultiplier(1);
//             bcdt_RangeAxis.setNumberFormatOverride(pctFormat);
            bufferedImage = barChart.createBufferedImage(5000, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        conn.close();
        return bufferedImage;
    }

    public void setData_report_revenue_day(String thang, String nam) throws SQLException, ClassNotFoundException, FileNotFoundException, DocumentException {
        List<Statistical_day> dayhList = new ArrayList<>();
        Connection conn = null;
        conn = ConnectionUtils.getMyConnection();
        CallableStatement stmt = conn.prepareCall("{call thong_ke_theo_ngay(?,?,?)}");
        stmt.setString(1, nam);
        stmt.setString(2, thang);
        stmt.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
        stmt.execute();
        ResultSet rs = (ResultSet) stmt.getObject(3);

        //tao file pdf
        Document doc = new Document();
        String filename = "bao_cao_doanh_thu_theo_ngay";
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("src/Report/" + filename + ".pdf"));

            doc.open();
            File fileFontTieuDe = new File("src/fonts/vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fTieuDe1 = new Font(bfTieuDe, 16);
            //fTieuDe1.setColor(BaseColor.GREEN);

            Font fTieuDe2 = new Font(bfTieuDe, 13);
            // fTieuDe2.setColor(BaseColor.GREEN);

            Font fTieuDe3 = new Font(bfTieuDe, 13);
            Font fTieuDe4 = new Font(bfTieuDe, 12);

            File fileFontNoiDung = new File("src/fonts/vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(fileFontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fNoiDung1 = new Font(bfNoiDung, 13);
            Font fNoiDung2 = new Font(bfNoiDung, 12);
            Font fNoiDung3 = new Font(bfNoiDung, 11);

            //chèn logo anh
            Image logo = Image.getInstance("src/Resources/agriculture.png");
            logo.setAbsolutePosition(150, 750);
            logo.scaleAbsolute(50, 50);
            doc.add(logo);

            Paragraph tenTrangTrai = new Paragraph("FRESH FOOD", fTieuDe1);
            tenTrangTrai.setIndentationLeft(180);
            doc.add(tenTrangTrai);

            Paragraph diaChiTrangTrai = new Paragraph("Địa chỉ: Linh Trung, khu phố 6, TP.Thủ Đức, TP.HCM", fNoiDung2);
            diaChiTrangTrai.setIndentationLeft(180);
            doc.add(diaChiTrangTrai);

            Paragraph hotline = new Paragraph("Hotline: 086.8247.806", fNoiDung2);
            hotline.setIndentationLeft(180);
            doc.add(hotline);

            Paragraph tieuDe = new Paragraph("BÁO CÁO DOANH THU THEO NGÀY TRONG THÁNG " + thang + " NĂM " + nam, fTieuDe1);
            tieuDe.setAlignment(1);
            tieuDe.setSpacingBefore(40);
            tieuDe.setSpacingAfter(20);
            doc.add(tieuDe);

            Paragraph danhSachTTDT = new Paragraph("Thông tin doanh thu: ", fTieuDe3);
            danhSachTTDT.setSpacingBefore(10);
            danhSachTTDT.setSpacingAfter(10);
            doc.add(danhSachTTDT);

            PdfPTable tableDT = new PdfPTable(3);
            tableDT.setWidthPercentage(80);
            tableDT.setSpacingBefore(5);
            tableDT.setSpacingAfter(5);
            float[] tableKH_columnwidth = {50, 150, 150};
            tableDT.setWidths(tableKH_columnwidth);

            //chen cot
            PdfPCell stt = new PdfPCell(new Paragraph("STT", fTieuDe4));
            stt.setBorderColor(BaseColor.BLACK);
            stt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            stt.setMinimumHeight(30);
            tableDT.addCell(stt);

            PdfPCell ngaydt = new PdfPCell(new Paragraph("Ngày", fTieuDe4));
            ngaydt.setBorderColor(BaseColor.BLACK);
            ngaydt.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            ngaydt.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableDT.addCell(ngaydt);

            PdfPCell celldoanhthunngay = new PdfPCell(new Paragraph("Doanh Thu", fTieuDe4));
            celldoanhthunngay.setBorderColor(BaseColor.BLACK);
            celldoanhthunngay.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            celldoanhthunngay.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            tableDT.addCell(celldoanhthunngay);

            int Stt = 1;
            int tongTien = 0;

            while (rs.next()) {
                PdfPCell stt_text = new PdfPCell(new Paragraph(String.valueOf(Stt), fNoiDung3));
                stt_text.setBorderColor(BaseColor.BLACK);
                stt_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                stt_text.setMinimumHeight(30);
                tableDT.addCell(stt_text);

                PdfPCell ngay_text = new PdfPCell(new Paragraph(rs.getString("NGAY"), fNoiDung3));
                ngay_text.setBorderColor(BaseColor.BLACK);
                ngay_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                ngay_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableDT.addCell(ngay_text);

                PdfPCell doanhthu_text = new PdfPCell(new Paragraph(rs.getString("TONGSO"), fNoiDung3));
                doanhthu_text.setBorderColor(BaseColor.BLACK);
                doanhthu_text.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                doanhthu_text.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                tableDT.addCell(doanhthu_text);

                tongTien = tongTien + rs.getInt("TONGSO");

                Stt++;
            }

            PdfPCell cellTongCong = new PdfPCell(new Paragraph("TỔNG CỘNG:", fTieuDe4));
            cellTongCong.setColspan(2);
            cellTongCong.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cellTongCong.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            cellTongCong.setMinimumHeight(20);
            tableDT.addCell(cellTongCong);

            PdfPCell cellTongtien = new PdfPCell(new Paragraph(dinhDangTienTe(tongTien), fTieuDe4));
            cellTongtien.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            cellTongtien.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
            tableDT.addCell(cellTongtien);
            doc.add(tableDT);
            Paragraph bieudo = new Paragraph("Biểu đồ: ", fTieuDe3);
            bieudo.setSpacingBefore(10);
            bieudo.setSpacingAfter(5);
            BufferedImage bf = null;

            
            BufferedImage bufferedImage = CreateBarChartForDay(thang, nam);
            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
            image.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            image.scaleAbsolute(400, 400);
            doc.add(image);

            doc.close();
            writer.close();
            conn.close();

        } catch (Exception e) {
        }
        try {
            File file = new File("src/Report/" + filename + ".pdf");
            if (!Desktop.isDesktopSupported()) {
                System.out.println("no");
                return;
            }

            Desktop desk = Desktop.getDesktop();
            if (file.exists()) {
                desk.open(file);
            }
        } catch (Exception e) {
        }
    }

}
