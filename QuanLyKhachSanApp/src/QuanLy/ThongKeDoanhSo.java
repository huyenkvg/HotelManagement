package QuanLy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ThongKeDoanhSo extends javax.swing.JFrame {

    private Vector ds = null;

    public ThongKeDoanhSo() {
        initComponents();
        this.setTitle("THỐNG KÊ DOANH SỐ");
        khoiTaoBaoLoi();
        //jTextField_LuuTai.setEditable(false);
    }

    private void khoiTaoBaoLoi() {
        jLabel_TenFile.setText("");
        jLabel_LuuTai.setText("");
    }

    private void khoiTaoTXT() {
        jTextField_TenFile.setText("");
        jTextField_LuuTai.setText("");
    }

    private String doiDatesangString(Date d) {
        String temp = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            temp = df.format(d);
        } catch (Exception ex) {
            System.out.println("Không chuyển được");
        }
        return temp;
    }

    private boolean layDSHoaDonTheoNgay(Date ngayBD, Date ngayKT) {
        Connection ketNoi = qlks.QLKS.layKetNoi();
        Vector e = null;
        ds = new Vector();
        String d1 = "", d2 = "", temp = "";
        boolean kt = true;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d1 = df.format(ngayBD);
            d2 = df.format(ngayKT);
        } catch (Exception ex) {
            System.out.println("Không chuyển được");
            kt = false;
        }
        if (kt == false) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không hợp lệ!");
            return false;
        } else try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM HOADON WHERE NGAYLAP >= '" + d1 + "' AND NGAYLAP <= '" + d2 + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                e = new Vector();
                e.add(rs.getString("MAHD"));
                e.add(rs.getString("NGAYLAP"));
                e.add(rs.getString("MANV"));
                e.add(rs.getString("MAPT"));
                e.add(rs.getString("GIA"));
                ds.add(e);
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeDoanhSo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void Excel(Vector ds, String path) {
        Vector vt = null;
        int tong = 0;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        int rowNum = 0;

        // Create row
        Row row = sheet.createRow(rowNum++);
        // Create cells
        Cell cell = row.createCell(0);
        cell.setCellValue("MÃ HÓA ĐƠN");
        cell = row.createCell(1);
        cell.setCellValue("NGÀY LẬP");
        cell = row.createCell(2);
        cell.setCellValue("MÃ NHÂN VIÊN");
        cell = row.createCell(3);
        cell.setCellValue("MÃ PHIẾU THUÊ");
        cell = row.createCell(4);
        cell.setCellValue("GIÁ HÓA ĐƠN");

        for (int i = 0; i < ds.size(); i++) {
            row = sheet.createRow(rowNum);
            vt = (Vector) ds.get(i);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue((String) vt.get(0));
            Cell cell2 = row.createCell(1);
            cell2.setCellValue((String) vt.get(1));
            Cell cell3 = row.createCell(2);
            cell3.setCellValue((String) vt.get(2));
            Cell cell4 = row.createCell(3);
            cell4.setCellValue((String) vt.get(3));
            Cell cell6 = row.createCell(4);
            cell6.setCellValue(Integer.parseInt((String) vt.get(4)));
            tong += Integer.parseInt((String) vt.get(4));
            rowNum++;
        }
        row = sheet.createRow(rowNum);
        cell = row.createCell(3);
        cell.setCellValue("TỔNG");
        cell = row.createCell(4);
        cell.setCellValue(tong);

        try {
            FileOutputStream outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Tạo file thành công!");
        khoiTaoBaoLoi();
        khoiTaoTXT();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Nen = new javax.swing.JPanel();
        jPanel_Khung = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton_XuatFile = new javax.swing.JButton();
        jDate_BD = new com.toedter.calendar.JDateChooser();
        jDate_KT = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jTextField_TenFile = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField_LuuTai = new javax.swing.JTextField();
        jLabel_TenFile = new javax.swing.JLabel();
        jLabel_LuuTai = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 51, 255));

        jPanel_Nen.setBackground(new java.awt.Color(102, 153, 255));

        jPanel_Khung.setBackground(new java.awt.Color(153, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Thống kê doanh số");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Từ ngày");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Đến ngày");

        jButton_XuatFile.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_XuatFile.setText("XUẤT FILE");
        jButton_XuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XuatFileActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Tên file (.xlsx)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Lưu tại");

        jTextField_LuuTai.setText("E:");

        jLabel_TenFile.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_TenFile.setText("jLabel6");

        jLabel_LuuTai.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_LuuTai.setText("jLabel7");

        javax.swing.GroupLayout jPanel_KhungLayout = new javax.swing.GroupLayout(jPanel_Khung);
        jPanel_Khung.setLayout(jPanel_KhungLayout);
        jPanel_KhungLayout.setHorizontalGroup(
            jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_KhungLayout.createSequentialGroup()
                .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_KhungLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_KhungLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jDate_KT, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_KhungLayout.createSequentialGroup()
                                .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_TenFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField_TenFile, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addComponent(jTextField_LuuTai)
                                    .addComponent(jLabel_LuuTai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel_KhungLayout.createSequentialGroup()
                                .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addComponent(jDate_BD, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_KhungLayout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jButton_XuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        jPanel_KhungLayout.setVerticalGroup(
            jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_KhungLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDate_BD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jDate_KT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_TenFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_TenFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_KhungLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_LuuTai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_LuuTai)
                .addGap(32, 32, 32)
                .addComponent(jButton_XuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_NenLayout = new javax.swing.GroupLayout(jPanel_Nen);
        jPanel_Nen.setLayout(jPanel_NenLayout);
        jPanel_NenLayout.setHorizontalGroup(
            jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NenLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel_Khung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel_NenLayout.setVerticalGroup(
            jPanel_NenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_NenLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel_Khung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Nen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Nen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean checkTenFile(String tenFile) {
        if (tenFile.equals("") == true) {
            jLabel_TenFile.setText("Tên file không được bỏ trống!");
            return false;
        } else if (tenFile.endsWith(".xlsx") == false) {
            jLabel_TenFile.setText("Tên file không hợp lệ!");
            return false;
        } else {
            String mau = "[A-Za-z0-9.]{1,20}";
            if (tenFile.matches(mau) == false) {
                jLabel_TenFile.setText("Tên file không hợp lệ!");
                return false;
            }
        }
        return true;
    }

    private boolean checkDuongDan(String duongDan) {
        if (duongDan.equals("") == true) {
            jLabel_LuuTai.setText("Đường dẫn không được bỏ trống!");
            return false;
        }
//        else{
//            String mau = "[A-Za-z0-9:\\]{1,30}";
//            if(duongDan.matches(mau) == false){
//                jLabel_LuuTai.setText("Dường dẫn không hợp lệ!");
//                return false;
//            }
//        }
        return true;
    }

    private void jButton_XuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XuatFileActionPerformed
        Date ngayBD = jDate_BD.getDate();
        Date ngayKT = jDate_KT.getDate();
        String ten = "", duongDan = "";
        ten = jTextField_TenFile.getText();
        duongDan = jTextField_LuuTai.getText();
        boolean kt = layDSHoaDonTheoNgay(ngayBD, ngayKT);
        if (checkTenFile(ten) == false) {
            kt = false;
        }
        if (checkDuongDan(duongDan) == false) {
            kt = false;
        }
        if (kt == true)
            Excel(ds, duongDan + "\\" + ten);
    }//GEN-LAST:event_jButton_XuatFileActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeDoanhSo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_XuatFile;
    private com.toedter.calendar.JDateChooser jDate_BD;
    private com.toedter.calendar.JDateChooser jDate_KT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_LuuTai;
    private javax.swing.JLabel jLabel_TenFile;
    private javax.swing.JPanel jPanel_Khung;
    private javax.swing.JPanel jPanel_Nen;
    private javax.swing.JTextField jTextField_LuuTai;
    private javax.swing.JTextField jTextField_TenFile;
    // End of variables declaration//GEN-END:variables
}
