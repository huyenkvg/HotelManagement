package QuanLy;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.util.Date;
import qlks.CTDL.NhanVien;

public class QuanLyNhanVien extends javax.swing.JFrame {

    private Vector ds = null;

    public QuanLyNhanVien() {
        initComponents();
        this.setTitle("QUẢN LÝ NHÂN VIÊN");
        khoiTaoBaoLoi();
        layDSNhanVien();
    }

    private void layDSNhanVien() {
        DefaultTableModel dtm = (DefaultTableModel) jTable_NhanVien.getModel();
        dtm.setNumRows(0);
        Connection ketNoi = qlks.QLKS.layKetNoi();
        Vector vt;
        ds = new Vector();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("SELECT * FROM NHANVIEN");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString("MANV"));
                String temp = rs.getString("HO") + " " + rs.getString("TEN");
                vt.add(temp);
                vt.add(rs.getString("PHAI"));
                vt.add(rs.getString("NGAYSINH"));
                vt.add(rs.getString("EMAIL"));
                vt.add(rs.getString("SDT"));
                vt.add(rs.getString("MABP"));
                ds.add(vt);
                dtm.addRow(vt);
            }
            jTable_NhanVien.setModel(dtm);
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void khoiTaoBaoLoi() {
        jLabel_MaNV.setText(" ");
        jLabel_HoTen.setText(" ");
        jLabel_NgaySinh.setText(" ");
        jLabel_Email.setText(" ");
        jLabel_GioiTinh.setText(" ");
        jLabel_SDT.setText(" ");
        jLabel_MABP.setText(" ");
    }

    private void khoiTaoTXT() {
        txt_MaNV.setEditable(true);
        txt_MaNV.setText("");
        txt_HoTenNV.setText("");
        txt_MaBPNV.setText("");
        txt_SDT.setText("");
        txt_Email.setText("");
    }

    private void themNhanVien(String maNV, String hoTen, String gioiTinh, Date d, String sDT, String email, String maBP) {
        String ho = "", ten = "";
        String ngaySinh = "";
        String[] temp = hoTen.split(" ");
        for (int i = 0; i < temp.length; i++) {
            if (i < temp.length - 1) {
                ho += temp[i];
                if (i < temp.length - 2) {
                    ho += " ";
                }
            } else {
                ten = temp[i];
            }
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ngaySinh = df.format(d);
        } catch (Exception e) {
            jLabel_NgaySinh.setText("Không chuyển ngày sinh được!");
        }
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("INSERT INTO NHANVIEN VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, maNV);
            ps.setString(2, ho);
            ps.setString(3, ten);
            ps.setString(4, gioiTinh);
            ps.setString(5, ngaySinh);
            ps.setString(6, email);
            ps.setString(7, sDT);
            ps.setString(8, maBP);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void xoaNhanVien(String maNV) {
        Connection ketNoi = qlks.QLKS.layKetNoi();
        String sql = "delete from NHANVIEN where MANV = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void suaNhanVien(String maNV, String hoTen, String gioiTinh, Date d, String sDT, String email, String maBP) {
        String ngaySinh = "";
        String ho = "", ten = "";
        String[] temp = hoTen.split(" ");
        for (int i = 0; i < temp.length; i++) {
            if (i < temp.length - 1) {
                ho += temp[i];
                if (i < temp.length - 2) {
                    ho += " ";
                }
            } else {
                ten = temp[i];
            }
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ngaySinh = df.format(d);
        } catch (Exception e) {
            jLabel_NgaySinh.setText("Không chuyển ngày sinh được!");
        }
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            PreparedStatement ps = ketNoi.prepareStatement("UPDATE NHANVIEN SET HO = ?, TEN = ?, PHAI = ?, NGAYSINH = ?, EMAIL = ?, SDT = ?, MABP =? WHERE MANV = ?");
            ps.setString(1, ho);
            ps.setString(2, ten);
            ps.setString(3, gioiTinh);
            ps.setString(4, ngaySinh);
            ps.setString(5, email);
            ps.setString(6, sDT);
            ps.setString(7, maBP);
            ps.setString(8, maNV);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean kiemTraNhanVienDeXoa(String maNV) {
        String temp;
        boolean kt = false;
        Connection ketNoi = qlks.QLKS.layKetNoi();
        if (maNV.equals("") == true) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!");
        } else {
            try {
                PreparedStatement ps = ketNoi.prepareStatement("select * from NHANVIEN");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    temp = rs.getString("MANV");
                    if (temp.equals(maNV) == true) {
                        kt = true;
                        break;
                    }
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (kt == false) {
                JOptionPane.showMessageDialog(this, "Nhân viên này không tồn tại!");
            } else {
                try {
                    ketNoi = qlks.QLKS.layKetNoi();
                    PreparedStatement ps = ketNoi.prepareStatement("select * from PHIEUDAT where MANV = '" + maNV + "'");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        kt = false;
                    }
                    rs.close();
                    ps.close();
                    ketNoi.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (kt == true)try {
                    ketNoi = qlks.QLKS.layKetNoi();
                    PreparedStatement ps = ketNoi.prepareStatement("select * from PHIEUDAT where MANV = '" + maNV + "'");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        kt = false;
                    }
                    rs.close();
                    ps.close();
                    ketNoi.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (kt == true)try {
                    ketNoi = qlks.QLKS.layKetNoi();
                    PreparedStatement ps = ketNoi.prepareStatement("select * from HOADON where MANV = '" + maNV + "'");
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        kt = false;
                    }
                    rs.close();
                    ps.close();
                    ketNoi.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (kt == false) {
                    JOptionPane.showMessageDialog(this, "Không thể xóa nhân viên này!");
                }
            }
        }
        return kt;
    }

    private boolean kiemTraNhanVienDeSua(String maNV) {
        String temp;
        if (maNV.equals("") == true) {
            jLabel_MaNV.setText("Không được bỏ trống mã nhân viên!");
            return false;
        } else {
            String mau = "[A-Za-z0-9]{1,10}";
            if (maNV.matches(mau) == false) {
                jLabel_MaNV.setText("Mã nhân viên không hợp lệ!");
                return false;
            }
        }
        Connection ketNoi = qlks.QLKS.layKetNoi();
        if (maNV.equals("") == true) {
            jLabel_MaNV.setText("Mã nhân viên không được để trống!");
            return false;
        } else {
            try {
                PreparedStatement ps = ketNoi.prepareStatement("select * from NHANVIEN");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    temp = rs.getString("MANV");
                    if (temp.equals(maNV) == true) {
                        return true;
                    }
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        jLabel_MaNV.setText("Mã nhân viên không tồn tại để sửa!");
        return false;
    }
    

    private String hoTenThuong(String hoTen) {
        hoTen = hoTen.trim();
        hoTen = hoTen.replaceAll("\\s+", " ");
        String[] temp = hoTen.split(" ");
        hoTen = "";
        for (int i = 0; i < temp.length; i++) {
            hoTen += temp[i].substring(0).toLowerCase();
            if (i < temp.length - 1) {
                hoTen += " ";
            }
        }
        return hoTen;
    }

    private String chuanHoaHoTen(String hoTen) {
        hoTen = hoTen.trim();
        hoTen = hoTen.replaceAll("\\s+", " ");
        String[] temp = hoTen.split(" ");
        hoTen = "";
        for (int i = 0; i < temp.length; i++) {
            hoTen += temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1).toLowerCase();
            if (i < temp.length - 1) {
                hoTen += " ";
            }
        }
        return hoTen;
    }

    private boolean checkMaNV(String maNV) {
        boolean kt = true;
        if (maNV.equals("") == true) {
            jLabel_MaNV.setText("Không được bỏ trống mã nhân viên!");
            return false;
        } else {
            String mau = "[A-Za-z0-9]{1,10}";
            if (maNV.matches(mau) == false) {
                jLabel_MaNV.setText("Mã nhân viên không hợp lệ!");
                return false;
            }
        }
        try {
            Connection ketNoi = qlks.QLKS.layKetNoi();
            PreparedStatement ps = ketNoi.prepareStatement("select * from NHANVIEN where MANV = '" + maNV + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = false;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        jLabel_MaNV.setText(" ");
        if (kt == false) {
            jLabel_MaNV.setText("Mã nhân viên đã tồn tại!");
        }
        return kt;
    }

    private boolean checkHoTen(String hoTen) {
        if (hoTen.equals("") == true) {
            jLabel_HoTen.setText("Họ tên không được để trống!");
            return false;
        } else {
            String mau = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{1,30}";
            if (hoTen.matches(mau) == false) {
                jLabel_HoTen.setText("Họ và tên không hợp lệ!");
                return false;
            }
        }
        jLabel_HoTen.setText(" ");
        return true;
    }

    private boolean checkGioiTinh(String gioiTinh) {
        if (gioiTinh.equals("") == true) {
            jLabel_GioiTinh.setText("Giới tính không được để trống");
            return false;
        }
        jLabel_GioiTinh.setText(" ");
        return true;
    }

    private boolean checkNgaySinh(Date d) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //Date date = df.parse(ngaySinh);
            String temp = df.format(d);
            jLabel_NgaySinh.setText(" ");
            return true;
        } catch (Exception e) {
            jLabel_NgaySinh.setText("Ngày sinh không hợp lệ!");
            return false;
        }
    }

    private boolean checkSDT(String sDT) {
        if (sDT.equals("") == true) {
            jLabel_SDT.setText("Số điện thoại không được để trống!");
            return false;
        } else {
            String mau = "[0-9]{10,11}";
            if (sDT.matches(mau) == false) {
                jLabel_SDT.setText("Số điện thoại không hợp lệ!");
                return false;
            }
        }
        jLabel_SDT.setText(" ");
        return true;
    }

    private boolean checkEmail(String email) {
        if (email.equals("") == true) {
            jLabel_Email.setText("Email không được để trống!");
            return false;
        } else {
            String mau = "\\w+@\\w+([.]\\w+){1,3}";
            if (email.matches(mau) == false) {
                jLabel_Email.setText("Email không hợp lệ!");
                return false;
            }
        }
        jLabel_Email.setText(" ");
        return true;
    }

    private boolean checkMaBP(String maBP) {
        String temp;
        boolean kt = false;
        if (maBP.equals("") == true) {
            jLabel_MABP.setText("Mã bộ phận không được để trống!");
        } else {
            Connection ketNoi = qlks.QLKS.layKetNoi();
            String sql = "select * from BOPHAN";
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    temp = rs.getString("MABP");
                    if (temp.equals(maBP) == true) {
                        kt = true;
                        break;
                    }
                }
                rs.close();
                ps.close();
                ketNoi.close();
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (kt == false) {
                jLabel_MABP.setText("Mã bộ phận không tồn tại!");
            }
        }
        if (kt == true) {
            jLabel_MABP.setText(" ");
        }
        return kt;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGioiTinh = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_ = new javax.swing.JLabel();
        txt_MaNV = new javax.swing.JTextField();
        jLabel_MaNV = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_HoTen = new javax.swing.JLabel();
        txt_HoTenNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel_NgaySinh = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        jLabel_Email = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel_SDT = new javax.swing.JLabel();
        txt_SDT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel_MABP = new javax.swing.JLabel();
        txt_MaBPNV = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel_GioiTinh = new javax.swing.JLabel();
        jRadioButton_Nam = new javax.swing.JRadioButton();
        jRadioButton_Nu = new javax.swing.JRadioButton();
        jButton_Them = new javax.swing.JButton();
        jButton_Sua = new javax.swing.JButton();
        jButton_SuaTXT = new javax.swing.JButton();
        jButton_Xoa = new javax.swing.JButton();
        jCalendar = new com.toedter.calendar.JDateChooser();
        jLabel_TimNV = new javax.swing.JLabel();
        txt_TimNhanVien = new javax.swing.JTextField();
        jButton_TimNhanVien = new javax.swing.JButton();
        jButton_LoadLai = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_NhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.setForeground(new java.awt.Color(102, 153, 255));

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Thông tin nhân viên");

        jLabel_.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_.setText("Mã nhân viên");

        jLabel_MaNV.setForeground(new java.awt.Color(255, 0, 51));
        jLabel_MaNV.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Họ và tên");

        jLabel_HoTen.setForeground(new java.awt.Color(255, 0, 51));
        jLabel_HoTen.setText("jLabel4");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Ngày sinh");

        jLabel_NgaySinh.setForeground(new java.awt.Color(255, 0, 51));
        jLabel_NgaySinh.setText("jLabel4");

        jLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel.setText("Email");

        jLabel_Email.setForeground(new java.awt.Color(255, 0, 51));
        jLabel_Email.setText("jLabel4");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Số điện thoại");

        jLabel_SDT.setForeground(new java.awt.Color(255, 0, 51));
        jLabel_SDT.setText("jLabel4");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Mã bộ phận");

        jLabel_MABP.setForeground(new java.awt.Color(255, 0, 51));
        jLabel_MABP.setText("jLabel4");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Giới tính");

        jLabel_GioiTinh.setForeground(new java.awt.Color(255, 0, 51));
        jLabel_GioiTinh.setText("jLabel4");

        buttonGioiTinh.add(jRadioButton_Nam);
        jRadioButton_Nam.setText("Nam");
        jRadioButton_Nam.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        buttonGioiTinh.add(jRadioButton_Nu);
        jRadioButton_Nu.setText("Nữ");

        jButton_Them.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/THEM.png"))); // NOI18N
        jButton_Them.setText("THÊM");
        jButton_Them.setToolTipText("");
        jButton_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThemActionPerformed(evt);
            }
        });

        jButton_Sua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/SUA.png"))); // NOI18N
        jButton_Sua.setText("SỬA");
        jButton_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SuaActionPerformed(evt);
            }
        });

        jButton_SuaTXT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_SuaTXT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/RESET.png"))); // NOI18N
        jButton_SuaTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SuaTXTActionPerformed(evt);
            }
        });

        jButton_Xoa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/DELETE1.png"))); // NOI18N
        jButton_Xoa.setText("XÓA");
        jButton_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_XoaActionPerformed(evt);
            }
        });

        jLabel_TimNV.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel_TimNV.setText("Tìm nhân viên");

        txt_TimNhanVien.setBackground(new java.awt.Color(255, 204, 204));

        jButton_TimNhanVien.setText("Tìm");
        jButton_TimNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TimNhanVienActionPerformed(evt);
            }
        });

        jButton_LoadLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/UPDATE.png"))); // NOI18N
        jButton_LoadLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoadLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel_)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_Email)
                                        .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_HoTen)
                                            .addComponent(txt_HoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel_NgaySinh)
                                            .addComponent(jLabel_MaNV)
                                            .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton_TimNhanVien)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton_LoadLai)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel15))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel_SDT)
                                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_GioiTinh)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jRadioButton_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(jRadioButton_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel_MABP)
                                        .addComponent(txt_MaBPNV, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_SuaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)))
                        .addGap(98, 98, 98))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_TimNV)
                            .addComponent(txt_TimNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 825, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_MaNV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_HoTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_HoTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_NgaySinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_Email))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_Nam)
                            .addComponent(jRadioButton_Nu)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_GioiTinh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_SDT)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_MaBPNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_MABP)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_SuaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_TimNV))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_TimNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_TimNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_LoadLai, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jTable_NhanVien.setBackground(new java.awt.Color(125, 141, 242));
        jTable_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NHÂN VIÊN", "HỌ VÀ TÊN", "GIỚI TÍNH", "NGÀY SINH", "EMAIL", "SỐ ĐIỆN THOẠI", "MÃ BỘ PHẬN"
            }
        ));
        jTable_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_NhanVien);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("DANH SÁCH NHÂN VIÊN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThemActionPerformed
        Boolean kt = true;
        String maNV = txt_MaNV.getText();
        String hoTen = txt_HoTenNV.getText();
        String gioiTinh = "";
        if (buttonGioiTinh.isSelected(jRadioButton_Nu.getModel()) == true) {
            gioiTinh = "Nữ";
        } else if (buttonGioiTinh.isSelected(jRadioButton_Nam.getModel()) == true) {
            gioiTinh = "Nam";
        }
        Date d = jCalendar.getDate();
        String sDT = txt_SDT.getText();
        String email = txt_Email.getText();
        String maBP = txt_MaBPNV.getText();

        if (checkMaNV(maNV) == false) {
            kt = false;
        }
        if (hoTen.equals("") == false) {
            hoTen = chuanHoaHoTen(hoTen);
        }
        if (checkHoTen(hoTen) == false) {
            kt = false;
        }
        if (checkGioiTinh(gioiTinh) == false) {
            kt = false;
        }
        if (checkNgaySinh(d) == false) {
            kt = false;
        }
        if (checkSDT(sDT) == false) {
            kt = false;
        }
        if (checkEmail(email) == false) {
            kt = false;
        }
        if (checkMaBP(maBP) == false) {
            kt = false;
        }

        if (kt == true) {
            themNhanVien(maNV, hoTen, gioiTinh, d, sDT, email, maBP);
            layDSNhanVien();
            khoiTaoTXT();
            khoiTaoBaoLoi();
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
        }
    }//GEN-LAST:event_jButton_ThemActionPerformed

    private void jButton_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_XoaActionPerformed
        String maNV = txt_MaNV.getText();
        boolean kt = true;

        kt = kiemTraNhanVienDeXoa(maNV);

        if (kt == true) {
            int ret = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa nhân viên này?", "Xác nhận", 0);
            if (ret == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (ret == JOptionPane.OK_OPTION) {
                xoaNhanVien(maNV);
                layDSNhanVien();
                khoiTaoTXT();
                khoiTaoBaoLoi();
                txt_MaNV.setEditable(true);
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
            }
        }
    }//GEN-LAST:event_jButton_XoaActionPerformed

    private void jButton_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SuaActionPerformed
        Boolean kt = true;
        String maNV = txt_MaNV.getText();
        String hoTen = txt_HoTenNV.getText();
        String gioiTinh = "";
        if (buttonGioiTinh.isSelected(jRadioButton_Nu.getModel()) == true) {
            gioiTinh = "Nữ";
        } else if (buttonGioiTinh.isSelected(jRadioButton_Nam.getModel()) == true) {
            gioiTinh = "Nam";
        }
        Date d = jCalendar.getDate();
        String sDT = txt_SDT.getText();
        String email = txt_Email.getText();
        String maBP = txt_MaBPNV.getText();

//        if (maNV.equals("") == true) {
//            kt = false;
//            jLabel_MaNV.setText("Mã nhân viên không được bỏ trống!");
//        }
        kt = kiemTraNhanVienDeSua(maNV);

        if (hoTen.equals("") == false) {
            hoTen = chuanHoaHoTen(hoTen);
        }
        if (checkHoTen(hoTen) == false) {
            kt = false;
        }
        if (checkGioiTinh(gioiTinh) == false) {
            kt = false;
        }
        if (checkNgaySinh(d) == false) {
            kt = false;
        }
        if (checkSDT(sDT) == false) {
            kt = false;
        }
        if (checkEmail(email) == false) {
            kt = false;
        }
        if (checkMaBP(maBP) == false) {
            kt = false;
        }

        if (kt == true) {
            suaNhanVien(maNV, hoTen, gioiTinh, d, sDT, email, maBP);
            layDSNhanVien();
            khoiTaoTXT();
            khoiTaoBaoLoi();
            txt_MaNV.setEditable(true);
            JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công!");
        }
    }//GEN-LAST:event_jButton_SuaActionPerformed

    private void jButton_SuaTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SuaTXTActionPerformed
        khoiTaoTXT();
        khoiTaoBaoLoi();
    }//GEN-LAST:event_jButton_SuaTXTActionPerformed

    private void jTable_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_NhanVienMouseClicked
        int i = jTable_NhanVien.getSelectedRow();
        Vector vt = (Vector) ds.get(i);
        txt_MaNV.setEditable(false);

        txt_MaNV.setText((String) vt.get(0));
        txt_HoTenNV.setText((String) vt.get(1));
        if (vt.get(2).equals("Nữ")) {
            jRadioButton_Nu.doClick();
        } else {
            jRadioButton_Nam.doClick();
        }

        String temp = (String) vt.get(3);
        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = df.parse(temp);
        } catch (Exception e) {
            jLabel_NgaySinh.setText("Ngày sinh không hợp lệ!");
        }
        jCalendar.setDate(date);

        txt_Email.setText((String) vt.get(4));
        txt_SDT.setText((String) vt.get(5));
        txt_MaBPNV.setText((String) vt.get(6));
    }//GEN-LAST:event_jTable_NhanVienMouseClicked

    private void jButton_TimNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TimNhanVienActionPerformed
        layDSNhanVien();
        String s = hoTenThuong(txt_TimNhanVien.getText());
        String temp = "";
        Vector vt = null;
        int n = ds.size();
        int i = 0;
        DefaultTableModel dtm = (DefaultTableModel) jTable_NhanVien.getModel();
        dtm.setNumRows(0);
        while (i < n) {
            vt = (Vector) ds.get(i);
            temp = hoTenThuong((String) vt.get(1));
            if (temp.contains(s) == false) {
                ds.remove(i);
                n--;
            } else {
                dtm.addRow(vt);
                i++;
            }
        }
        jTable_NhanVien.setModel(dtm);
    }//GEN-LAST:event_jButton_TimNhanVienActionPerformed

    private void jButton_LoadLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoadLaiActionPerformed
        layDSNhanVien();
        txt_TimNhanVien.setText("");
    }//GEN-LAST:event_jButton_LoadLaiActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGioiTinh;
    private javax.swing.JButton jButton_LoadLai;
    private javax.swing.JButton jButton_Sua;
    private javax.swing.JButton jButton_SuaTXT;
    private javax.swing.JButton jButton_Them;
    private javax.swing.JButton jButton_TimNhanVien;
    private javax.swing.JButton jButton_Xoa;
    private com.toedter.calendar.JDateChooser jCalendar;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_;
    private javax.swing.JLabel jLabel_Email;
    private javax.swing.JLabel jLabel_GioiTinh;
    private javax.swing.JLabel jLabel_HoTen;
    private javax.swing.JLabel jLabel_MABP;
    private javax.swing.JLabel jLabel_MaNV;
    private javax.swing.JLabel jLabel_NgaySinh;
    private javax.swing.JLabel jLabel_SDT;
    private javax.swing.JLabel jLabel_TimNV;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton_Nam;
    private javax.swing.JRadioButton jRadioButton_Nu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_NhanVien;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HoTenNV;
    private javax.swing.JTextField txt_MaBPNV;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_TimNhanVien;
    // End of variables declaration//GEN-END:variables
}
