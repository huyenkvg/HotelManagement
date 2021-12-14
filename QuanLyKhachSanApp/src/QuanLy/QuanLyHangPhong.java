package QuanLy;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import qlhp.CTDL.HangPhong;
import qlhp.CTDL.GiaHangPhong;
import qlhp.CTDL.TienNghi;
import qlhp.CTDL.CTTienNghi;
import static qlks.QLKS.layKetNoi;

public final class QuanLyHangPhong extends javax.swing.JFrame {

    public QuanLyHangPhong() {
        initComponents();
        layDSTenHangPhong();
        hienThiDanhSachHangPhong();
        layDSTenTienNghi();
        hienThiDSHPTN();
    }
    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs;
    HangPhong p;
    GiaHangPhong g;
    TienNghi ttn;
    String HP;
    String TN;
    String DG;
    ArrayList<TienNghi> dsttn, dstn, dshptn;
    ArrayList<HangPhong> dshp;

//Thông tin hạng Phòng
    public void layDSTenHangPhong() {
        conn = layKetNoi();
        String SQL = "SELECT TENHP FROM HANGPHONG";
        try {
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                jComboBox_TenHangPhong.addItem(rs.getString("TENHP"));
            }
        } catch (SQLException ex) {
        }
    }

    public boolean checkChonHP(String HP) {
        boolean kt = true;
        if (HP.equals("...")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn Hạng Phòng!");
            kt = false;
        }
        return kt;
    }

    public ArrayList<TienNghi> layDSTienNghi() {
        dsttn = new ArrayList<>();
        conn = layKetNoi();
        final String SQL = "SELECT DISTINCT ST2.HANGPHONG, "
                + "SUBSTRING( "
                + "(SELECT ', '+ST3.TENTIENNGHI  AS [text()] "
                + "FROM CT_TIENNGHI ST1, TIENNGHI ST3 "
                + "WHERE ST1.HANGPHONG = ST2.HANGPHONG "
                + "and ST1.MATN=ST3.MATN "
                + "ORDER BY ST1.HANGPHONG "
                + "FOR XML PATH ('') "
                + "), 2, 1000) [TENTIENNGHI] "
                + "FROM CT_TIENNGHI ST2";
        try {
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                ttn = new TienNghi(rs.getString("TENTIENNGHI") + ", ");
                dsttn.add(ttn);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
        }
        return dsttn;
    }

    public ArrayList<HangPhong> layDSHangPhong() {
        dshp = new ArrayList<>();
        conn = layKetNoi();
        final String SQL = "SELECT TENHP FROM HANGPHONG";
        try {
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                p = new HangPhong(rs.getString("TENHP"));
                dshp.add(p);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
        }
        return dshp;
    }

    String layDonGia(String hangPhong) {
        conn = layKetNoi();
        try {
            CallableStatement c = conn.prepareCall("{call loadGIAHANGPHONG (?)}");
            c.setString(1, hangPhong);
            rs = c.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHangPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void hienThiDanhSachHangPhong() {
        String colTieuDe1[] = new String[]{"Tên hạng phòng", "Giá", "Tên tiện nghi"};
        dsttn = layDSTienNghi();
        dshp = layDSHangPhong();
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
        Object[] row;
        for (int i = 0; i < dsttn.size() && i < dshp.size(); i++) {
            row = new Object[3];
            row[0] = dshp.get(i).gettenHP();
            row[1] = layDonGia(dshp.get(i).gettenHP());
            row[2] = dsttn.get(i).getTenTN();
            model.addRow(row);
        }
        jTable_DSHP.setModel(model);
    }

//Kiểm tra tồn tại tiện nghi + kiểm tra kiểu giá    
    private boolean checkThemTienNghi(String hangPhong, String tienNghi) {
        boolean kt = true;
        conn = layKetNoi();
        try {
            String SQL = "SELECT CT_TIENNGHI.HANGPHONG,TIENNGHI.TENTIENNGHI\n"
                    + "FROM  CT_TIENNGHI\n"
                    + "INNER JOIN TIENNGHI ON CT_TIENNGHI.MATN = TIENNGHI.MATN\n"
                    + "WHERE HANGPHONG =N'" + hangPhong + "' AND TENTIENNGHI=N'" + tienNghi + "'";
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            if (rs.next()) {
                kt = false;

            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
        }
        if (kt == false) {
            JOptionPane.showMessageDialog(this, "Tiện nghi đã tồn tại trong hạng phòng");
        }
        return kt;
    }

    private boolean checkXoaTienNghi(String hangPhong, String tienNghi) {
        boolean kt = true;
        try {
            conn = layKetNoi();
            pst = conn.prepareStatement("SELECT CT_TIENNGHI.HANGPHONG,TIENNGHI.TENTIENNGHI\n"
                    + "FROM  CT_TIENNGHI\n"
                    + "INNER JOIN TIENNGHI ON CT_TIENNGHI.MATN = TIENNGHI.MATN\n"
                    + "WHERE HANGPHONG=N'" + hangPhong + "' AND TENTIENNGHI=N'" + tienNghi + "'");
            rs = pst.executeQuery();
            if (rs.next()) {
                kt = false;
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
        }

//        if (kt == true) {
//            JOptionPane.showMessageDialog(this, "Tiện nghi đã được xóa khỏi hạng phòng");
//        }
        return kt;
    }

// Thêm Tiện Nghi
    public void layDSTenTienNghi() {
        conn = layKetNoi();
        String SQL = "SELECT TENTIENNGHI FROM TIENNGHI";
        try {
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                this.jComboBox_TenTN.addItem(rs.getString("TENTIENNGHI"));
            }
        } catch (java.sql.SQLException ex) {
        }
    }

    public ArrayList<TienNghi> layDSTNHP() {
        dstn = new ArrayList<>();
        conn = layKetNoi();
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        final String SQL = "SELECT CT_TIENNGHI.HANGPHONG,TIENNGHI.TENTIENNGHI\n"
                + "FROM  CT_TIENNGHI\n"
                + "INNER JOIN TIENNGHI ON TIENNGHI.MATN=CT_TIENNGHI.MATN \n"
                + "WHERE CT_TIENNGHI.HANGPHONG=N'" + HP + "'";
        try {
            pst = conn.prepareStatement(SQL);
            rs = pst.executeQuery();
            while (rs.next()) {
                ttn = new TienNghi(rs.getString("TENTIENNGHI"));
                dstn.add(ttn);
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (java.sql.SQLException ex) {
        }
        return dstn;
    }

    public void hienThiDSHPTN() {
        String colTieuDe1[] = new String[]{"Tiện nghi của hạng phòng này là:"};
        dshptn = layDSTNHP();
        DefaultTableModel model = new DefaultTableModel(colTieuDe1, 0);
        Object[] row;
        for (int i = 0; i < dshptn.size(); i++) {
            row = new Object[1];
            row[0] = dshptn.get(i).getTenTN();
            model.addRow(row);
        }
        jTable_TNHP.setModel(model);
    }

    public void themTienNghi() {
        conn = layKetNoi();
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        TN = (String) jComboBox_TenTN.getSelectedItem();

        String SQL = "DECLARE @matn nvarchar(10)\n"
                + "set @matn=(SELECT MATN FROM TIENNGHI WHERE TENTIENNGHI=N'" + TN + "') \n"
                + "INSERT INTO CT_TIENNGHI(MATN,HANGPHONG) VALUES (@matn,N'" + HP + "')";
        try {

            pst = conn.prepareStatement(SQL);
            pst.execute();
            pst.close();
            conn.close();

        } catch (java.sql.SQLException ex) {

        }

    }

    public void xoaTienNghi() {
        conn = layKetNoi();
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        TN = (String) jComboBox_TenTN.getSelectedItem();

        String SQL = "DECLARE @matn nvarchar(10) "
                + "set @matn=(SELECT MATN FROM TIENNGHI WHERE TENTIENNGHI=N'" + TN + "') "
                + "DELETE FROM CT_TIENNGHI WHERE CT_TIENNGHI.MATN =@matn AND CT_TIENNGHI.HANGPHONG=N'" + HP + "'";
        try {
            pst = conn.prepareStatement(SQL);
            pst.execute();
            pst.close();
            conn.close();
        } catch (java.sql.SQLException ex) {
        }
    }
//

    private boolean checkKieuGia(String gia) {
        boolean kt = true;
        String mau = "[0-9]+";
        if (gia.equals("") == true || gia.matches(mau) == false) {
            JOptionPane.showMessageDialog(this, "Đơn giá hạng phòng không hợp lệ!");
            jLabel8.setText("Đơn giá phải là số và không được để trống!");
            kt = false;

        } else {
            jLabel8.setText(" ");
        }
        return kt;

    }
// Thay đổi Giá Hạng Phòng

    public void thayDoiGiaHP() {
        conn = layKetNoi();
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        DG = (String) txt_Gia.getText();

        String SQL = "INSERT INTO GIAHANGPHONG(HANGPHONG,NGAY,GIA,MAKM) "
                + "VALUES (N'" + HP + "',CURRENT_TIMESTAMP," + DG + ",NULL)";
        try {
            pst = conn.prepareStatement(SQL);
            pst.execute();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
        }

    }

//Hiển thị thông tin trên txt        
    public void hienThiTN() {
        conn = layKetNoi();
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        if (!"...".equals(HP)) {
            String SQL = "SELECT DISTINCT HANGPHONG = N'" + HP + "', "
                    + "SUBSTRING( "
                    + "( SELECT ', '+ST3.TENTIENNGHI  AS [text()] "
                    + "FROM CT_TIENNGHI ST1, TIENNGHI ST3 "
                    + "WHERE ST1.HANGPHONG = N'" + HP + "' "
                    + "AND ST1.MATN=ST3.MATN "
                    + "ORDER BY ST1.HANGPHONG "
                    + "FOR XML PATH ('') "
                    + "), 2, 1000) [TENTIENNGHI] "
                    + "FROM CT_TIENNGHI ST2";
            try {
                pst = conn.prepareStatement(SQL);
                rs = pst.executeQuery();
                while (rs.next()) {
                    txt_TienNghi.setText(rs.getString("TENTIENNGHI"));
                }
                rs.close();
                pst.close();
                conn.close();
            } catch (java.sql.SQLException ex) {
            }
        } else {
            txt_TienNghi.setText("");
        }
    }

//    public void hienThiGiaHP() {
//        Connection conn = qlks.QLKS.layKetNoi();
//        String HP = (String) jComboBox_TenHangPhong.getSelectedItem();
//        if(!"...".equals(HP)){
//        final String SQL = "SELECT GIA FROM GIAHANGPHONG WHERE HANGPHONG=N'" + HP + "'";
//        try {
//            pst = conn.prepareStatement(SQL);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                txt_DonGiaHangPhong.setText(rs.getString("GIA"));
//                txt_Gia.setText(rs.getString("GIA"));
//            }
//            rs.close();
//            pst.close();
//            conn.close();
//        } catch (SQLException ex) {
//        }
//        }
//        else 
//        
//    }
    public void hienThiGiaHP() {
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        if (!"...".equals(HP)) {

            txt_DonGiaHangPhong.setText(layDonGia(HP));
            txt_Gia.setText(layDonGia(HP));

        } else {
            txt_DonGiaHangPhong.setText("");
            txt_Gia.setText("");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jComboBox_TenTN = new javax.swing.JComboBox<>();
        jButton_XoaTN = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_TNHP = new javax.swing.JTable();
        jButton_ThemTN = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField_THP = new javax.swing.JTextField();
        jLabel_TN = new javax.swing.JLabel();
        jFrame2 = new javax.swing.JFrame();
        jButton_Luu1 = new javax.swing.JButton();
        txt_Gia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_THP1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanelNenFrame = new javax.swing.JPanel();
        jPanelThongTinPhong = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_DonGiaHangPhong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_TienNghi = new javax.swing.JTextField();
        jComboBox_TenHangPhong = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jPanelThongTinPhong1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_DSHP = new javax.swing.JTable();
        jPanelThongTinPhong2 = new javax.swing.JPanel();
        btn_ChinhSuaTN = new javax.swing.JButton();
        btn_CapNhatGiaHP = new javax.swing.JButton();

        jFrame1.setTitle("Chỉnh Sửa Tiện Nghi Trong Hạng Phòng");
        jFrame1.setBackground(new java.awt.Color(102, 153, 255));

        jComboBox_TenTN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        jComboBox_TenTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TenTNActionPerformed(evt);
            }
        });

        jButton_XoaTN.setText("XÓA");
        jButton_XoaTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaTNActionPerformed(evt);
            }
        });

        jTable_TNHP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jTable_TNHP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_TNHPMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_TNHP);

        jButton_ThemTN.setText("THÊM");
        jButton_ThemTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemTNActionPerformed(evt);
            }
        });

        jLabel7.setText("CHỈNH SỬA TIỆN NGHI HẠNG PHÒNG");

        jTextField_THP.setEditable(false);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jButton_XoaTN, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_ThemTN, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel7))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_THP)
                            .addComponent(jComboBox_TenTN, 0, 239, Short.MAX_VALUE)
                            .addComponent(jLabel_TN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_THP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_TenTN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel_TN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_XoaTN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ThemTN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        jFrame2.setBackground(new java.awt.Color(102, 153, 255));

        jButton_Luu1.setBackground(new java.awt.Color(51, 153, 255));
        jButton_Luu1.setText("LƯU");
        jButton_Luu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Luu1ActionPerformed(evt);
            }
        });

        txt_Gia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_GiaActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên hạng phòng:");

        jLabel3.setText("Đơn giá:");

        jLabel5.setText("THAY ĐỔI GIÁ HẠNG PHÒNG");

        jTextField_THP1.setEditable(false);

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jButton_Luu1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(txt_Gia, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(jTextField_THP1))
                .addGap(126, 126, 126))
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_THP1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Gia, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Luu1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        setTitle("Quản Lý Hạng Phòng");
        setBackground(new java.awt.Color(153, 153, 255));
        setResizable(false);

        jPanelNenFrame.setBackground(new java.awt.Color(102, 153, 255));
        jPanelNenFrame.setMinimumSize(new java.awt.Dimension(1200, 600));
        jPanelNenFrame.setPreferredSize(new java.awt.Dimension(950, 620));

        jPanelThongTinPhong.setBackground(new java.awt.Color(153, 204, 255));
        jPanelThongTinPhong.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông tin hạng phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 51))); // NOI18N
        jPanelThongTinPhong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanelThongTinPhong.setPreferredSize(new java.awt.Dimension(560, 580));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tên hạng phòng:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Đơn giá:");

        txt_DonGiaHangPhong.setEditable(false);
        txt_DonGiaHangPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DonGiaHangPhongActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Tiện Nghi:");

        txt_TienNghi.setEditable(false);
        txt_TienNghi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TienNghiActionPerformed(evt);
            }
        });

        jComboBox_TenHangPhong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "..." }));
        jComboBox_TenHangPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox_TenHangPhongMouseClicked(evt);
            }
        });
        jComboBox_TenHangPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TenHangPhongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelThongTinPhongLayout = new javax.swing.GroupLayout(jPanelThongTinPhong);
        jPanelThongTinPhong.setLayout(jPanelThongTinPhongLayout);
        jPanelThongTinPhongLayout.setHorizontalGroup(
            jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox_TenHangPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelThongTinPhongLayout.createSequentialGroup()
                        .addGroup(jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_DonGiaHangPhong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                                .addComponent(txt_TienNghi, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelThongTinPhongLayout.setVerticalGroup(
            jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinPhongLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_TenHangPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThongTinPhongLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThongTinPhongLayout.createSequentialGroup()
                        .addComponent(txt_TienNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(jPanelThongTinPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_DonGiaHangPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(138, Short.MAX_VALUE))
        );

        jPanelThongTinPhong1.setBackground(new java.awt.Color(153, 204, 255));
        jPanelThongTinPhong1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Danh sách hạng phòng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 51))); // NOI18N
        jPanelThongTinPhong1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanelThongTinPhong1.setPreferredSize(new java.awt.Dimension(560, 580));

        jTable_DSHP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_DSHP.setGridColor(new java.awt.Color(153, 153, 153));
        jTable_DSHP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_DSHPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_DSHP);
        if (jTable_DSHP.getColumnModel().getColumnCount() > 0) {
            jTable_DSHP.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTable_DSHP.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable_DSHP.getColumnModel().getColumn(2).setPreferredWidth(500);
        }

        javax.swing.GroupLayout jPanelThongTinPhong1Layout = new javax.swing.GroupLayout(jPanelThongTinPhong1);
        jPanelThongTinPhong1.setLayout(jPanelThongTinPhong1Layout);
        jPanelThongTinPhong1Layout.setHorizontalGroup(
            jPanelThongTinPhong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
        );
        jPanelThongTinPhong1Layout.setVerticalGroup(
            jPanelThongTinPhong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );

        jPanelThongTinPhong2.setBackground(new java.awt.Color(153, 204, 255));
        jPanelThongTinPhong2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Hành động", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 51))); // NOI18N
        jPanelThongTinPhong2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanelThongTinPhong2.setPreferredSize(new java.awt.Dimension(560, 580));

        btn_ChinhSuaTN.setBackground(new java.awt.Color(51, 153, 255));
        btn_ChinhSuaTN.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_ChinhSuaTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/SUA.png"))); // NOI18N
        btn_ChinhSuaTN.setText("CHỈNH SỬA TIỆN NGHI");
        btn_ChinhSuaTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChinhSuaTNActionPerformed(evt);
            }
        });

        btn_CapNhatGiaHP.setBackground(new java.awt.Color(51, 153, 255));
        btn_CapNhatGiaHP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_CapNhatGiaHP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/UPDATE.png"))); // NOI18N
        btn_CapNhatGiaHP.setText("CẬP NHẬT GIÁ");
        btn_CapNhatGiaHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatGiaHPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelThongTinPhong2Layout = new javax.swing.GroupLayout(jPanelThongTinPhong2);
        jPanelThongTinPhong2.setLayout(jPanelThongTinPhong2Layout);
        jPanelThongTinPhong2Layout.setHorizontalGroup(
            jPanelThongTinPhong2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinPhong2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanelThongTinPhong2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_ChinhSuaTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_CapNhatGiaHP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 48, Short.MAX_VALUE))
        );
        jPanelThongTinPhong2Layout.setVerticalGroup(
            jPanelThongTinPhong2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinPhong2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(btn_ChinhSuaTN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(btn_CapNhatGiaHP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelNenFrameLayout = new javax.swing.GroupLayout(jPanelNenFrame);
        jPanelNenFrame.setLayout(jPanelNenFrameLayout);
        jPanelNenFrameLayout.setHorizontalGroup(
            jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNenFrameLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelThongTinPhong1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelNenFrameLayout.createSequentialGroup()
                        .addComponent(jPanelThongTinPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelThongTinPhong2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelNenFrameLayout.setVerticalGroup(
            jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNenFrameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelThongTinPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(jPanelThongTinPhong2, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jPanelThongTinPhong1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelNenFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelNenFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("Quản Lý Hạng Phòng");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CapNhatGiaHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatGiaHPActionPerformed
        // TODO add your handling code here:
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        if (checkChonHP(HP) == true) {
            jFrame2.setSize(500, 350);
            jFrame2.setLocation(500, 200);
            jFrame2.setVisible(true);
            jTextField_THP1.setText(HP);
            hienThiGiaHP();
            thayDoiGiaHP();
            hienThiDanhSachHangPhong();
        }
    }//GEN-LAST:event_btn_CapNhatGiaHPActionPerformed


    private void jTable_DSHPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_DSHPMouseClicked
        // TODO add your handling code here:
        int i = jTable_DSHP.getSelectedRow();
        TableModel model = jTable_DSHP.getModel();
        String TenHP = model.getValueAt(i, 0).toString();
        jComboBox_TenHangPhong.setSelectedItem(TenHP);
        txt_TienNghi.setText(model.getValueAt(i, 2).toString());
        txt_DonGiaHangPhong.setText(model.getValueAt(i, 1).toString());
    }//GEN-LAST:event_jTable_DSHPMouseClicked

    private void jComboBox_TenHangPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox_TenHangPhongMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TenHangPhongMouseClicked


    private void jComboBox_TenHangPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TenHangPhongActionPerformed
        // TODO add your handling code here:
        hienThiTN();
        hienThiGiaHP();
    }//GEN-LAST:event_jComboBox_TenHangPhongActionPerformed

    private void txt_DonGiaHangPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DonGiaHangPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DonGiaHangPhongActionPerformed


    private void btn_ChinhSuaTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChinhSuaTNActionPerformed
        // TODO add your handling code here:
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        if (checkChonHP(HP) == true) {
            jFrame1.setSize(500, 400);
            jFrame1.setLocation(500, 200);
            jFrame1.setVisible(true);
            jTextField_THP.setText(HP);
            hienThiDSHPTN();
        }
    }//GEN-LAST:event_btn_ChinhSuaTNActionPerformed


    private void jButton_Luu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Luu1ActionPerformed
        // TODO add your handling code here:
        DG = (String) txt_Gia.getText();
        if (checkKieuGia(DG) == true) {
            int selected = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn lưu đơn giá mới này không?", "Thông báo",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (selected == JOptionPane.YES_NO_OPTION) {
                thayDoiGiaHP();
                hienThiGiaHP();
                hienThiDanhSachHangPhong();
                jFrame2.dispose();
            }
        }
    }//GEN-LAST:event_jButton_Luu1ActionPerformed

    private void jComboBox_TenTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TenTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TenTNActionPerformed


    private void jButton_ThemTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemTNActionPerformed
        // TODO add your handling code here:
        TN = (String) jComboBox_TenTN.getSelectedItem();
        if (!"...".equals(TN)) {
            if (checkThemTienNghi(HP, TN) == true) {
                themTienNghi();
                hienThiDSHPTN();
                hienThiTN();
                hienThiDanhSachHangPhong();
            }
        } else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn tiện nghi để thêm vào hạng phòng này!");
    }//GEN-LAST:event_jButton_ThemTNActionPerformed


    private void jButton_XoaTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaTNActionPerformed
        // TODO add your handling code here:
        HP = (String) jComboBox_TenHangPhong.getSelectedItem();
        TN = (String) jComboBox_TenTN.getSelectedItem();
        if (!"...".equals(TN)) {
            if (checkXoaTienNghi(HP, TN) == false) {
                int selected = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa tiện nghi này không", "Thông báo",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (selected == JOptionPane.YES_NO_OPTION) {
                    xoaTienNghi();
                    hienThiDSHPTN();
                    hienThiTN();
                    hienThiDanhSachHangPhong();
                }
            }
        } else
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn tiện nghi để xóa khỏi hạng phòng này!");
    }//GEN-LAST:event_jButton_XoaTNActionPerformed

    private void txt_GiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_GiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_GiaActionPerformed

    private void txt_TienNghiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TienNghiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TienNghiActionPerformed

    private void jTable_TNHPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_TNHPMouseClicked
        // TODO add your handling code here:
        int i = jTable_TNHP.getSelectedRow();
        TableModel model = jTable_TNHP.getModel();
        String TenHP = model.getValueAt(i, 0).toString();
        jComboBox_TenTN.setSelectedItem(TenHP);

    }//GEN-LAST:event_jTable_TNHPMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHangPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new QuanLyHangPhong().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CapNhatGiaHP;
    private javax.swing.JButton btn_ChinhSuaTN;
    private javax.swing.JButton jButton_Luu1;
    private javax.swing.JButton jButton_ThemTN;
    private javax.swing.JButton jButton_XoaTN;
    private javax.swing.JComboBox<String> jComboBox_TenHangPhong;
    private javax.swing.JComboBox<String> jComboBox_TenTN;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_TN;
    private javax.swing.JPanel jPanelNenFrame;
    private javax.swing.JPanel jPanelThongTinPhong;
    private javax.swing.JPanel jPanelThongTinPhong1;
    private javax.swing.JPanel jPanelThongTinPhong2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_DSHP;
    private javax.swing.JTable jTable_TNHP;
    private javax.swing.JTextField jTextField_THP;
    private javax.swing.JTextField jTextField_THP1;
    private javax.swing.JTextField txt_DonGiaHangPhong;
    private javax.swing.JTextField txt_Gia;
    private javax.swing.JTextField txt_TienNghi;
    // End of variables declaration//GEN-END:variables
}
