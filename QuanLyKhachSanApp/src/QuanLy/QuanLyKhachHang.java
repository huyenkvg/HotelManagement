package QuanLy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import qlks.CTDL.KhachHang;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class QuanLyKhachHang extends javax.swing.JFrame {

    public QuanLyKhachHang() {
        initComponents();
        hienThiKHTrenJtable();
        setTitle("QUẢN LÝ KHÁCH HÀNG");
    }

    public void themDanhSach(String cmnd, String ho, String ten, String diaChi, String sdt, String maSoThue) {

        Connection ketNoi = qlks.QLKS.layKetNoi();
        String sql = "INSERT INTO KHACHHANG VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, cmnd);
            ps.setString(2, ho);
            ps.setString(3, ten);
            ps.setString(4, diaChi);
            ps.setString(5, sdt);
            ps.setString(6, maSoThue);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
// lấy bảng khách hàng ở SQL vào ArrayList

    public ArrayList<KhachHang> khachHangList() {
        ArrayList<KhachHang> khachHangList = new ArrayList<>();
        Connection ketNoi = qlks.QLKS.layKetNoi();
        String sql = "SELECT * FROM KHACHHANG"; // này phải lấy giống với tên csdl trong sql
        KhachHang khachHang;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql); // Câu lệnh truy vấn sql 
            ResultSet rs = ps.executeQuery(); // dùng cho câu lệnh select
            while (rs.next()) {
                khachHang = new KhachHang(rs.getString("CMND"), rs.getString("HO"), rs.getString("TEN"), rs.getString("DIACHI"), rs.getString("SDT"), rs.getString("MASOTHUE"));
                khachHangList.add(khachHang);
            }
            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return khachHangList;
    }
// ArrayList đã được đẩy vào jTable

    public void hienThiKHTrenJtable() {
        ArrayList<KhachHang> list = khachHangList();
        DefaultTableModel dtm = (DefaultTableModel) jTable_khachHang.getModel();
        Object[] row;//= new Object[6];
        dtm.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            row = new Object[6];
            row[0] = list.get(i).getCMND();
            row[1] = list.get(i).getHo();
            row[2] = list.get(i).getTen();
            row[3] = list.get(i).getDiaChi();
            row[4] = list.get(i).getSDT();
            row[5] = list.get(i).getMaSoThue();
            dtm.addRow(row);

        }
        jTable_khachHang.setModel(dtm);
    }

    public void suaKhachHang(String cmnd, String ho, String ten, String diaChi, String sdt, String maSoThue) {
        Connection ketNoi = qlks.QLKS.layKetNoi();
        String sql = "UPDATE KHACHHANG SET HO = ?, TEN =?, DIACHI =?, SDT = ?,MASOTHUE =? WHERE CMND = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, ho);
            ps.setString(2, ten);
            ps.setString(3, diaChi);
            ps.setString(4, sdt);
            ps.setString(5, maSoThue);
            ps.setString(6, cmnd);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void xoaKhachHang(String cmnd) {
        Connection ketNoi = qlks.QLKS.layKetNoi();
        String sql = "DELETE FROM KHACHHANG WHERE CMND = ?";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, cmnd);
            ps.executeUpdate();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KhachHang timKhachHang(String cmnd) {
        KhachHang khach = null;
        Connection ketNoi = qlks.QLKS.layKetNoi();
        String sql = "SELECT * FROM KHACHHANG WHERE CMND = '" + cmnd + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                khach = new KhachHang(rs.getString("CMND"), rs.getString("HO"), rs.getString("TEN"), rs.getString("DIACHI"), rs.getString("SDT"), rs.getString("MASOTHUE"));

            };
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return khach;
    }

    public void khoiTaoTXT() {
        txt_MaKH.setEnabled(true);
        txt_MaKH.setText("");
        txt_HoKH.setText("");
        txt_ten.setText("");
        txt_DiaChiKH.setText("");
        txt_SDT_KH.setText("");
        txt_MaSoThueKH.setText("");
        txt_TimKiemKH.setText("");
    }

    public void khoiTaoBaoLoi() {
        jLabel_maKH.setText("");
        jLabel_hoKH.setText("");
        jLabel_tenKH.setText("");
        jLabel_diaChiKH.setText("");
        jLabel_sdtKH.setText("");
        jLabel_maSoThueKH.setText("");
    }

    //-------------Chuẩn hóa chữ-------------
    //vd: lê thị thu     hương-> Lê Thị Thu Hương
    public String chuanHoa(String st) {
        st = st.trim();
        st = st.replaceAll("\\s+", " ");
        String[] temp = st.split(" ");
        st = "";
        if (temp.length >= 0) {
            for (int i = 0; i < temp.length; i++) {
                st += temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1).toLowerCase();
                if (i < temp.length - 1) {
                    st += " ";
                }
            }
        } else {
            for (int i = 0; i < temp.length; i++) {
                st = temp[0].substring(0, 1).toUpperCase() + temp[i].substring(1).toLowerCase();
            }
        }
        return st;
    }

    private boolean kiemTraKhachHangDeXoa(String maKH) {
        String temp;
        boolean kt = false;
        Connection ketNoi = qlks.QLKS.layKetNoi();
        try {
            ketNoi = qlks.QLKS.layKetNoi();
            PreparedStatement ps = ketNoi.prepareStatement("select * from PHIEUDAT where MAKH = '" + maKH + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = false;
            } else {
                kt = true;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (kt == true)try {
            ketNoi = qlks.QLKS.layKetNoi();
            PreparedStatement ps = ketNoi.prepareStatement("select * from PHIEUTHUE where MAKH = '" + maKH + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = false;
            } else {
                kt = true;
            }
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kt;
    }

    private boolean checkMaKH(String cmnd) {
        boolean kt = true;
        if (cmnd.equals("")) {
            jLabel_maKH.setText("Mã khách hàng không được để trống");
            return false;
        } else {
            String mauMaKH = "[0-9]{12}";
            if (cmnd.matches(mauMaKH) != true) {
                jLabel_maKH.setText("Mã khách hàng chứa 12 kí tự chữ số");
                return false;
            }
        }
        jLabel_maKH.setText(" ");
        return kt;
    }

    private boolean checkMaKHDaTonTai(String cmnd) {
        boolean kt = false;
        Connection ketNoi = qlks.QLKS.layKetNoi();
        String sql = "SELECT * FROM KHACHHANG WHERE CMND = '" + cmnd + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                kt = true;
            }
            ps.close();
            rs.close();
            ketNoi.close();

        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return kt;
    }

    private boolean checkHo(String ho) {
        if (ho.equals("") == true) {
            jLabel_hoKH.setText("Họ không được để trống!");
            return false;
        } else {
            String mauHoKH = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{1,20}";
            if (ho.matches(mauHoKH) != true) {
                jLabel_hoKH.setText("Họ khách hàng chứa tối đa 20 chữ cái");
                return false;
            }
        }
        jLabel_hoKH.setText(" ");
        return true;
    }

    private boolean checkTen(String ten) {
        if (ten.equals("") == true) {
            jLabel_tenKH.setText("Tên không được để trống!");
            return false;
        } else {
            String mauTenKH = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{1,10}";
            if (ten.matches(mauTenKH) != true) {
                jLabel_tenKH.setText("Tên khách hàng chứa tối đa 10 chữ cái");
                return false;
            }
        }
        jLabel_tenKH.setText(" ");
        return true;
    }

    private boolean checkSDT(String sDT) {
        if (sDT.equals("") == true) {
            jLabel_sdtKH.setText("Số điện thoại không được để trống!");
            return false;
        } else {
            String mau = "[0-9]{10}";
            if (sDT.matches(mau) == false) {
                jLabel_sdtKH.setText("Số điện thoại chứa 10 kí tự chữ số");
                return false;
            }
        }
        jLabel_sdtKH.setText(" ");
        return true;
    }

    private boolean checkDiaChi(String diaChi) {
        if (diaChi.equals("") != true) {
            String mau = "[[0-9/,.]+^\\p{L}+[\\p{L}\\p{Z}\\p{P}+[0-9/,.]]]{1,30}";
            if (diaChi.matches(mau) == false) {
                jLabel_diaChiKH.setText("Địa chỉ không hợp lệ");
                return false;
            }
        }
        jLabel_diaChiKH.setText("");
        return true;
    }

    private boolean checkMST(String maSoThue) {
        if (maSoThue.equals("") != true) {
            String mauMST = "[0-9]{10}";
            String mau2 = "[0-9]{13}";
            if (maSoThue.matches(mauMST) != true & maSoThue.matches(mau2) != true) {
                jLabel_maSoThueKH.setText("Mã số thuế chứa 10 hoặc 13 chữ số");
                return false;
            }
        }
        jLabel_maSoThueKH.setText("");
        return true;
    }

    /**
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelNenFrame = new javax.swing.JPanel();
        jPanelThongTinKhachHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_MaKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_HoKH = new javax.swing.JTextField();
        txt_TimKiemKH = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_DiaChiKH = new javax.swing.JTextField();
        jButton_them = new javax.swing.JButton();
        jButton_sua = new javax.swing.JButton();
        jButton_xoa = new javax.swing.JButton();
        jButton_timKiem = new javax.swing.JButton();
        txt_SDT_KH = new javax.swing.JTextField();
        txt_MaSoThueKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        jLabel_maKH = new javax.swing.JLabel();
        jLabel_hoKH = new javax.swing.JLabel();
        jLabel_tenKH = new javax.swing.JLabel();
        jLabel_diaChiKH = new javax.swing.JLabel();
        jLabel_sdtKH = new javax.swing.JLabel();
        jLabel_maSoThueKH = new javax.swing.JLabel();
        jButton_lamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_khachHang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Quản Lý Khách Hàng");
        setBackground(new java.awt.Color(153, 153, 255));
        setResizable(false);

        jPanelNenFrame.setBackground(new java.awt.Color(102, 153, 255));
        jPanelNenFrame.setMinimumSize(new java.awt.Dimension(1200, 600));
        jPanelNenFrame.setPreferredSize(new java.awt.Dimension(1200, 620));

        jPanelThongTinKhachHang.setBackground(new java.awt.Color(153, 204, 255));
        jPanelThongTinKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 51))); // NOI18N
        jPanelThongTinKhachHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanelThongTinKhachHang.setPreferredSize(new java.awt.Dimension(1160, 320));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Mã KH:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Họ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("SĐT:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tìm Khách Hàng");

        txt_TimKiemKH.setBackground(new java.awt.Color(255, 204, 204));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Địa Chỉ:");

        jButton_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/THEM.png"))); // NOI18N
        jButton_them.setText("THÊM");
        jButton_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_themActionPerformed(evt);
            }
        });

        jButton_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/SUA.png"))); // NOI18N
        jButton_sua.setText("SỬA");
        jButton_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_suaActionPerformed(evt);
            }
        });

        jButton_xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/DELETE1.png"))); // NOI18N
        jButton_xoa.setText("XÓA");
        jButton_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_xoaActionPerformed(evt);
            }
        });

        jButton_timKiem.setText("TÌM KIẾM");
        jButton_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_timKiemActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Mã số thuế:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Tên");

        jLabel_maKH.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_maKH.setText(" ");

        jLabel_hoKH.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_hoKH.setText(" ");

        jLabel_tenKH.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_tenKH.setText(" ");

        jLabel_diaChiKH.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_diaChiKH.setText(" ");

        jLabel_sdtKH.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_sdtKH.setText(" ");

        jLabel_maSoThueKH.setForeground(new java.awt.Color(255, 0, 0));
        jLabel_maSoThueKH.setText(" ");

        jButton_lamMoi.setBackground(new java.awt.Color(153, 204, 255));
        jButton_lamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qlks/hinh/RESET.png"))); // NOI18N
        jButton_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_lamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelThongTinKhachHangLayout = new javax.swing.GroupLayout(jPanelThongTinKhachHang);
        jPanelThongTinKhachHang.setLayout(jPanelThongTinKhachHangLayout);
        jPanelThongTinKhachHangLayout.setHorizontalGroup(
            jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(21, 21, 21))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_tenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_hoKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(176, 866, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_maKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_HoKH, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                                .addGap(204, 204, 204))
                            .addComponent(txt_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(579, 579, 579))))
            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                        .addGap(592, 592, 592)
                        .addComponent(jButton_them, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_sua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jButton_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                .addComponent(txt_TimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                .addGap(303, 303, 303)
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_DiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_SDT_KH, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_diaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                .addGap(308, 308, 308)
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_sdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_MaSoThueKH, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_maSoThueKH, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelThongTinKhachHangLayout.setVerticalGroup(
            jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                        .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39))
                                    .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel_maKH, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)))
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_HoKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_hoKH)
                                .addGap(19, 19, 19)
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_DiaChiKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_diaChiKH)
                                .addGap(21, 21, 21)
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_SDT_KH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_sdtKH)
                                .addGap(14, 14, 14)
                                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_MaSoThueKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_tenKH)
                            .addComponent(jLabel_maSoThueKH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE))
                    .addGroup(jPanelThongTinKhachHangLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_them, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_TimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1160, 200));

        jTable_khachHang.setBackground(new java.awt.Color(125, 141, 242));
        jTable_khachHang.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable_khachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable_khachHang.setForeground(new java.awt.Color(0, 51, 51));
        jTable_khachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Họ", "Tên", "Địa chỉ", "SDT", "Mã số thuế"
            }
        ));
        jTable_khachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_khachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_khachHang);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText(" Danh sách khách hàng");

        javax.swing.GroupLayout jPanelNenFrameLayout = new javax.swing.GroupLayout(jPanelNenFrame);
        jPanelNenFrame.setLayout(jPanelNenFrameLayout);
        jPanelNenFrameLayout.setHorizontalGroup(
            jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNenFrameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelThongTinKhachHang, 1353, 1353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanelNenFrameLayout.setVerticalGroup(
            jPanelNenFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNenFrameLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanelThongTinKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jLabel1.getAccessibleContext().setAccessibleName("Danh sách nhân viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelNenFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelNenFrame, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_themActionPerformed
        txt_MaKH.setEnabled(true);
        Boolean kt = true;
        //-------PHẦN NÀY LÀ THÊM TRONG SQL-----------------------------------
        String cmnd = txt_MaKH.getText();
        String ho = txt_HoKH.getText();
        String ten = txt_ten.getText();
        String diaChi = txt_DiaChiKH.getText();
        String sdt = txt_SDT_KH.getText();
        String maSoThue = txt_MaSoThueKH.getText();
        if (ho.equals("") == false) {
            ho = chuanHoa(ho);
        }
        if (ten.equals("") == false) {
            ten = chuanHoa(ten);
        }
        if (diaChi.equals("") == false) {
            diaChi = chuanHoa(diaChi);
        }
        if (checkMaKH(cmnd) == false) {
            kt = false;
        }
        if (checkMaKHDaTonTai(cmnd) == true) {
            jLabel_maKH.setText("Mã khách hàng đã tồn tại!");
            kt = false;
        }
        if (checkHo(ho) == false) {
            kt = false;
        }
        if (checkTen(ten) == false) {
            kt = false;
        }
        if (checkDiaChi(diaChi) == false) {
            kt = false;
        }
        if (checkSDT(sdt) == false) {
            kt = false;
        }
        if (checkMST(maSoThue) == false) {
            kt = false;
        }
        if (kt == true) {
            themDanhSach(cmnd, ho, ten, diaChi, sdt, maSoThue);
            JOptionPane.showMessageDialog(this, "Đã thêm thành công");
            khoiTaoBaoLoi();
            khoiTaoTXT();
            this.hienThiKHTrenJtable();
        }

    }//GEN-LAST:event_jButton_themActionPerformed

    private void jButton_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_suaActionPerformed
        Boolean kt = true;
        String cmnd = txt_MaKH.getText();
        String ho = txt_HoKH.getText();
        String ten = txt_ten.getText();
        String diaChi = txt_DiaChiKH.getText();
        String sdt = txt_SDT_KH.getText();
        String maSoThue = txt_MaSoThueKH.getText();
        if (ho.equals("") == false) {
            ho = chuanHoa(ho);
        }
        if (ten.equals("") == false) {
            ten = chuanHoa(ten);
        }
        if (diaChi.equals("") == false) {
            diaChi = chuanHoa(diaChi);
        }
        if (checkMaKH(cmnd) == false) {//lúc sửa thì cmnd phải tồn tại mới sửa được
            kt = false;
        } else if (checkMaKHDaTonTai(cmnd) != true) {
            jLabel_maKH.setText("Mã khách hàng không tồn tại!");
            kt = false;
        }
        if (checkHo(ho) == false) {
            kt = false;
        }
        if (checkTen(ten) == false) {
            kt = false;
        }
        if (checkDiaChi(diaChi) == false) {
            kt = false;
        }
        if (checkSDT(sdt) == false) {
            kt = false;
        }
        if (checkMST(maSoThue) == false) {
            kt = false;
        }

        if (kt == true) {
            suaKhachHang(cmnd, ho, ten, diaChi, sdt, maSoThue);
            khoiTaoBaoLoi();
            khoiTaoTXT();
            JOptionPane.showMessageDialog(this, "Sửa thành công");
            this.hienThiKHTrenJtable();
            jButton_them.setEnabled(true);
        }

    }//GEN-LAST:event_jButton_suaActionPerformed

    private void jTable_khachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_khachHangMouseClicked
        int index = jTable_khachHang.getSelectedRow();
        TableModel model = jTable_khachHang.getModel();
        String cmnd = (String) model.getValueAt(index, 0);
        String ho = (String) model.getValueAt(index, 1);
        String ten = (String) model.getValueAt(index, 2);
        String diaChi = (String) model.getValueAt(index, 3);
        String sdt = (String) model.getValueAt(index, 4);
        String maSoThue = (String) model.getValueAt(index, 5);
        txt_MaKH.setText(cmnd);
        txt_HoKH.setText(ho);
        txt_ten.setText(ten);
        txt_MaSoThueKH.setText(maSoThue);
        txt_SDT_KH.setText(sdt);
        txt_DiaChiKH.setText(diaChi);
        txt_MaKH.setEnabled(false);
        jButton_them.setEnabled(false);
    }//GEN-LAST:event_jTable_khachHangMouseClicked

    private void jButton_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xoaActionPerformed
        String cmnd = txt_MaKH.getText();
        if (checkMaKH(cmnd) == true) {
            if (checkMaKHDaTonTai(cmnd) == true) {
                if (kiemTraKhachHangDeXoa(cmnd) == true) {
                    int n = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (n == 0) { // nếu nhấn YES
                        xoaKhachHang(cmnd);
                        JOptionPane.showMessageDialog(this, "Đã xóa thành công");
                        this.hienThiKHTrenJtable();
                        khoiTaoBaoLoi();
                        khoiTaoTXT();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Khách hàng này không thể xóa được!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã khách hàng không tồn tại!");
            }
        }

    }//GEN-LAST:event_jButton_xoaActionPerformed

    private void jButton_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_timKiemActionPerformed
        String cmnd = txt_TimKiemKH.getText();
        if (checkMaKH(cmnd) == true) {
            if (checkMaKHDaTonTai(cmnd) == true) {
                KhachHang khach = timKhachHang(cmnd);
                JOptionPane.showMessageDialog(this, " Đã tìm kiếm thành công");
                txt_MaKH.setText(khach.getCMND());
                txt_HoKH.setText(khach.getHo());
                txt_ten.setText(khach.getTen());
                txt_DiaChiKH.setText(khach.getDiaChi());
                txt_SDT_KH.setText(khach.getSDT());
                txt_MaSoThueKH.setText(khach.getMaSoThue());
            } else {
                JOptionPane.showMessageDialog(this, "Mã khách hàng không tồn tại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mã khách hàng không hợp lệ!");
        }
    }//GEN-LAST:event_jButton_timKiemActionPerformed

    private void jButton_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_lamMoiActionPerformed
        txt_MaKH.setEnabled(true);
        jButton_them.setEnabled(true);
        khoiTaoBaoLoi();
        khoiTaoTXT();
    }//GEN-LAST:event_jButton_lamMoiActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhachHang.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyKhachHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_lamMoi;
    private javax.swing.JButton jButton_sua;
    private javax.swing.JButton jButton_them;
    private javax.swing.JButton jButton_timKiem;
    private javax.swing.JButton jButton_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_diaChiKH;
    private javax.swing.JLabel jLabel_hoKH;
    private javax.swing.JLabel jLabel_maKH;
    private javax.swing.JLabel jLabel_maSoThueKH;
    private javax.swing.JLabel jLabel_sdtKH;
    private javax.swing.JLabel jLabel_tenKH;
    private javax.swing.JPanel jPanelNenFrame;
    private javax.swing.JPanel jPanelThongTinKhachHang;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_khachHang;
    private javax.swing.JTextField txt_DiaChiKH;
    private javax.swing.JTextField txt_HoKH;
    private javax.swing.JTextField txt_MaKH;
    private javax.swing.JTextField txt_MaSoThueKH;
    private javax.swing.JTextField txt_SDT_KH;
    private javax.swing.JTextField txt_TimKiemKH;
    private javax.swing.JTextField txt_ten;
    // End of variables declaration//GEN-END:variables
}
