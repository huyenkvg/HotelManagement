package Huong;

import DangNhapBanDau.DangNhapForm;
import Data.DBConection;
import QuanLy.QuanLyDichVu;
import QuanLy.QuanLyKhachHang;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import qlks.CTDL.CTDichVu;
import qlks.CTDL.CTThue;
import qlks.CTDL.DichVu;
import qlks.CTDL.HoaDon;
import qlks.CTDL.KhachHang;
import qlks.CTDL.NhanVien;
import static qlks.QLKS.layKetNoi;

public class XuatHoaDonHuong extends javax.swing.JFrame {

    HoaDon hoadon = null;
    int MaPT = 7;
    NhanVien nhanVienDangTruc;
    ThongTinHoaDon thongTin = null;

    public XuatHoaDonHuong(int mapt) {
        MaPT = mapt;
        initComponents();
        TXT_KhachHang.setEditable(false);
        TXT_MaHD.setEditable(false);
        TXT_NhanVien.setEditable(false);
        TXT_ThoiGian.setEditable(false);
        //nhanVienDangTruc = new NhanVien("NV01", "Ngo Thu", "HA", "Nam", new java.sql.Date(1, 1, 2000), "NTH@gmail.com", "0123456789", "MN");
        nhanVienDangTruc = DangNhapForm.nhanVienDangDangNhap;
        hoadon = timHoaDon(MaPT);
        if (hoadon == null) {
            // TÌM THẤY LÀ OUT CT NÀY LUÔN, VÌ CÓ HÓA ĐƠN RỒI THÌ MÉO XUẤT NỮA :D .........
            if (!LapHoaDonMoi()) {
                return;
            }

            System.out.println("Không tìm thấy HD theo mapt, lập HD mới thành công");
            thongTin = thongTinHoaDon();
            hienThiDichVu(mapt);
            hienThiTienPhong();
            UpDateTienSauKhiTinh();
            TXT_MaHD.setText(thongTin.getMahd() + "");
            TXT_KhachHang.setText(thongTin.getTenkhach());
            TXT_NhanVien.setText(thongTin.getTennhanvien());
            TXT_ThoiGian.setText(thongTin.getNgayLap().toString());

            jLabel_tongTien.setText("" + hoadon.getGia());

            JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công!!!", "Thông báo", PLAIN_MESSAGE);
            xuatThongTin(mapt);
            this.setVisible(false);
            return;

        } else {
            JOptionPane.showMessageDialog(null, "Phiếu thuê này đã lập hóa đơn rồi!!!", "Thông báo", PLAIN_MESSAGE);
            this.setVisible(false);
            return;

        }
    }

    public void UpDateTienSauKhiTinh() {
        Connection ketNoi = DBConection.layKetNoi();
        try {

            Statement st = ketNoi.createStatement();
            st.executeUpdate("UpDate HOADON set Gia = '" + hoadon.getGia() + "' where mahd ='" + hoadon.getMaHD() + "'");
            ArrayList<TienPhong> dsphong = layDanhSachPhong(MaPT);
            for (TienPhong phong : dsphong) {
                st.executeUpdate("UpDate PHONG set TRANGTHAI = N'Dơ' where maphong ='" + phong.getMaPhong() + "'");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
// lấy hóa đơn

    public HoaDon timHoaDon(int MaPT) {
        HoaDon hoaDon = null;
        Connection ketNoi = DBConection.layKetNoi();
        String sql = "SELECT * FROM HOADON WHERE MAPT = '" + MaPT + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hoaDon = new HoaDon(rs.getInt("MAHD"), rs.getString("MANV"), rs.getInt("MAPT"), rs.getDate("NGAYLAP"), rs.getInt("GIA"));
            };
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);

        }
        return hoaDon;
    }
// lấy tên khách thông qua MAKH 

    public KhachHang thongTinKH(int MaPT) {
        KhachHang khach = null;
        Connection ketNoi = DBConection.layKetNoi();
        String sql = "SELECT HO, TEN, SDT\n"
                + "FROM KHACHHANG, PHIEUTHUE\n"
                + "WHERE KHACHHANG.CMND = PHIEUTHUE.MAKH AND PHIEUTHUE.MAPT = '" + MaPT + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                khach = new KhachHang(null, rs.getNString("HO"), rs.getNString("TEN"), null, rs.getNString("SDT"), null);
            };
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }

        return khach;
    }
//  lấy tên nhân viên

    public NhanVien thongTinNV(int MaPT) {
        NhanVien nhanVien = null;
        Connection ketNoi = DBConection.layKetNoi();
        String sql = "SELECT HO, TEN\n"
                + "FROM NHANVIEN, PHIEUTHUE\n"
                + "WHERE NHANVIEN.MANV = PHIEUTHUE.MANV AND MAPT = '" + MaPT + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nhanVien = new NhanVien();
                nhanVien.setHo(rs.getString("HO"));
                nhanVien.setTen(rs.getString("TEN"));
            };
            rs.close();
            ps.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nhanVien;
    }

//    public ThongTinHoaDon thongTinHoaDon() {
//        return null;
//    }
    public ThongTinHoaDon thongTinHoaDon() {
        NhanVien nhanVien = thongTinNV(MaPT);
        KhachHang khach = thongTinKH(MaPT);
        ThongTinHoaDon thongTin = new ThongTinHoaDon(hoadon.getMaHD(), khach.getHo() + " " + khach.getTen(), hoadon.getNgayLap(), nhanVien.getHo()+ " " + nhanVien.getTen(), khach.getSDT());

        System.out.println(thongTin.getNgayLap());
        return thongTin;
    }

//=========== Tiền phòng ===============
    public ArrayList<TienPhong> layDanhSachPhong(int MaPT) {
        Connection ketNoi = DBConection.layKetNoi();
        String sql = "SELECT CT_THUE.*, DATEDIFF(HOUR, CT_THUE.NGAYDEN, CT_THUE.NGAYDI) AS THOIGIAN\n"
                + "FROM CT_THUE\n"
                + "WHERE CT_THUE.MAPT = '" + MaPT + "'"; // này phải lấy giống với tên csdl trong sql

        ArrayList<TienPhong> tienPhong = new ArrayList<>();
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql); // Câu lệnh truy vấn sql 
            ResultSet rs = ps.executeQuery(); // dùng cho câu lệnh select
            while (rs.next()) {
                TienPhong tp = new TienPhong(rs.getInt("MAPHONG"), rs.getDate("NGAYDEN"), rs.getDate("NGAYDI"), rs.getInt("THOIGIAN"), 0, 0);
                tienPhong.add(tp);
            }

            ps.close();
            rs.close();
            ketNoi.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tienPhong;
    }

    public ArrayList<TienPhong> layDanhSachGiaPhong(int MaPT) {
        ArrayList<TienPhong> giaPhong = layDanhSachPhong(MaPT);
        if (giaPhong != null && !giaPhong.isEmpty()) {
            for (int i = 0; i < giaPhong.size(); i++) {
                Connection ketNoi = DBConection.layKetNoi();
                String sql = "SELECT GIAHANGPHONG.GIA\n"
                        + "FROM PHONG, GIAHANGPHONG\n"
                        + "WHERE PHONG.HANGPHONG = GIAHANGPHONG.HANGPHONG AND PHONG.MAPHONG = '" + giaPhong.get(i).getMaPhong() + "'"; // này phải lấy giống với tên csdl trong sql
                try {
                    PreparedStatement ps = ketNoi.prepareStatement(sql); // Câu lệnh truy vấn sql 
                    ResultSet rs = ps.executeQuery(); // dùng cho câu lệnh select
                    while (rs.next()) {
                        giaPhong.get(i).setGiaPhong(rs.getInt("GIA"));
                        giaPhong.get(i).setTien(giaPhong.get(i).getThoiGian() * rs.getInt("GIA"));
                    }
                    ps.close();
                    rs.close();
                    ketNoi.close();
                } catch (SQLException | NullPointerException ex) {
                    Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return giaPhong;
    }

    public void resetNgayDiThanhHomNay() {

    }

    public boolean LapHoaDonMoi() {
        Connection ketNoi = DBConection.layKetNoi();

        try {
            System.out.println("");
            Statement stmt = ketNoi.createStatement();
            stmt.executeUpdate("UPDATE CT_THUE SET NGAYDI = GETDATE() Where MAPT ='" + MaPT + "';");
            int row = (stmt.executeUpdate("Insert into HOADON (MAPT, NGAYLAP, MANV, GIA) values (" + MaPT + ", GETDATE(), '" + nhanVienDangTruc.getMaNV() + "', 0);", Statement.RETURN_GENERATED_KEYS));
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                row = generatedKeys.getInt(1);
            }
            ResultSet rs = stmt.executeQuery("SELECT GETDATE() as DAY");
            rs.next();
            hoadon = new HoaDon(row, nhanVienDangTruc.getMaNV(), MaPT, rs.getDate("DAY"), 0);
            System.out.println("MA HD VUA UPDATE: " + row + "  :  ");
            return true;

        } catch (SQLException ex) {
            System.out.println("Không Update được: ADD HOADON Vao SQL");
            ex.printStackTrace();
        }
        return false;

    }

    public void hienThiTienPhong() {
        ArrayList<TienPhong> TienPhong = layDanhSachGiaPhong(MaPT);
        int tongTien = 0;
        DefaultTableModel dtm = (DefaultTableModel) jTable_tienPhong.getModel();
        dtm.setRowCount(0); // đặt để sau khi chạy hàm này một lần nữa dữ liệu không bị trùng
        Object[] row;
        for (int i = 0; i < TienPhong.size(); i++) {
            row = new Object[6];
            row[0] = TienPhong.get(i).getMaPhong();
            row[1] = TienPhong.get(i).getNgayDen();
            row[2] = TienPhong.get(i).getNgayDi();
            row[3] = TienPhong.get(i).getThoiGian();
            row[4] = TienPhong.get(i).getGiaPhong();
            row[5] = TienPhong.get(i).getTien();
            dtm.addRow(row);
        }
        for (int i = 0; i < TienPhong.size(); i++) {
            tongTien += TienPhong.get(i).getTien();
        }
        hoadon.setGia(hoadon.getGia() + tongTien);
        jLabel_tienPhong.setText(ChuyenInt(tongTien));
        jTable_tienPhong.setModel(dtm);
    }

//================ Tiền dịch vụ ==================
    public ArrayList<TienDichVu> layDSDV(int maPT) {
        Connection ketNoi = DBConection.layKetNoi();
        ArrayList<TienDichVu> layDSDV = new ArrayList<>();
        String sql = "SELECT DICHVU.MADV,DICHVU.TENDICHVU, CT_DICHVU.NGAYSUDUNG, CT_DICHVU.MAPHONG\n"
                + "FROM  DICHVU \n"
                + "INNER JOIN CT_DICHVU ON DICHVU.MADV=CT_DICHVU.MADV \n"
                + "WHERE CT_DICHVU.MAPT = '" + maPT + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DichVu DV = new DichVu(rs.getString("MADV"), rs.getString("TENDICHVU"));
                CTDichVu CTDV = new CTDichVu(null, 0, rs.getInt("MAPHONG"), rs.getDate("NGAYSUDUNG"));
                TienDichVu tienDV = new TienDichVu(DV, CTDV);
                layDSDV.add(tienDV);

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return layDSDV;
    }

    String layDonGia(String maDV) {
        Connection ketNoi = DBConection.layKetNoi();
        try {
            CallableStatement c = ketNoi.prepareCall("{call loadDG (?)}");
            c.setString(1, maDV);
            ResultSet rs = c.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDichVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void hienThiDichVu(int maPT) {
        ArrayList<TienDichVu> dichVu = layDSDV(maPT);
        int tongTien = 0;
        DefaultTableModel dtm = (DefaultTableModel) jTable_tienDichVu.getModel();
        dtm.setRowCount(0); // đặt để sau khi chạy hàm này một lần nữa dữ liệu không bị trùng
        Object[] row;
        for (int i = 0; i < dichVu.size(); i++) {
            row = new Object[4];
            row[0] = dichVu.get(i).getCtDichVu().getMaPhong();
            row[1] = dichVu.get(i).getDichVu().getTenDV();
            row[2] = dichVu.get(i).getCtDichVu().getNgaySuDung();
            row[3] = layDonGia(dichVu.get(i).getDichVu().getMaDV());
            dtm.addRow(row);
        }
        for (int i = 0; i < dichVu.size(); i++) {

            tongTien += Integer.parseInt(layDonGia(dichVu.get(i).getDichVu().getMaDV()));
        }
        hoadon.setGia(hoadon.getGia() + tongTien);
        jLabel_tienDichVu.setText(ChuyenInt(tongTien));
        jTable_tienDichVu.setModel(dtm);
    }

    public String ChuyenDate(java.sql.Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat = formatter.format(date);
        return dateFormat;
    }

    public String ChuyenInt(int ma) {
        String chuyen = Integer.toString(ma);
        return chuyen;
    }

//    public int ChuyenStringSangInt(String ma) {
//        int i = Integer.parseInt(ma);
//        return i;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TXT_MaHD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TXT_KhachHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TXT_ThoiGian = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TXT_NhanVien = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_tienPhong = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_tienDichVu = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel_tienPhong = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel_tienDichVu = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel_tongTien = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel1.setText("HÓA ĐƠN");

        jPanel1.setLayout(new java.awt.GridLayout(4, 2));

        jLabel2.setText("Mã hóa đơn:");
        jPanel1.add(jLabel2);
        jPanel1.add(TXT_MaHD);

        jLabel3.setText("Khách hàng:");
        jPanel1.add(jLabel3);
        jPanel1.add(TXT_KhachHang);

        jLabel4.setText("Thời gian:");
        jPanel1.add(jLabel4);
        jPanel1.add(TXT_ThoiGian);

        jLabel5.setText("Nhân viên:");
        jPanel1.add(jLabel5);
        jPanel1.add(TXT_NhanVien);

        jTable_tienPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Phòng", "Thời gian đến", "Thời gian đi", "Thời gian", "Giá phòng", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(jTable_tienPhong);

        jLabel6.setText("Tiền phòng");

        jTable_tienDichVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Mã phòng", "Dịch vụ", "Ngày sử dụng", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(jTable_tienDichVu);

        jLabel7.setText("Dịch vụ");

        jLabel8.setText("Tiền phòng:");

        jLabel_tienPhong.setText("0");

        jLabel10.setText("Tiền dịch vụ:");

        jLabel_tienDichVu.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_tienPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(jLabel_tienDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_tienPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_tienDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel12.setText("--------------------------------------------------------------------------------");

        jLabel13.setText("TỔNG TIỀN:");

        jLabel_tongTien.setText("000,000,0000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(351, 351, 351)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)))
                        .addGap(0, 284, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addGap(87, 87, 87))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(58, 58, 58)
                            .addComponent(jLabel_tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel_tongTien))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //============== Xuất excel =============

    public void xuatThongTin(int MaPT) {
        XSSFWorkbook Workbook = new XSSFWorkbook();
        XSSFSheet sheet = Workbook.createSheet();
        ThongTinHoaDon tt = thongTinHoaDon();
        int rowNum = 0;
        Row firstRow = sheet.createRow(rowNum++);
        Cell firstCell = firstRow.createCell(0);
        firstCell.setCellValue("HÓA ĐƠN");
        Row row2 = sheet.createRow(rowNum++);
        Cell cell1 = row2.createCell(0);
        cell1.setCellValue("Mã hóa đơn: ");
        Cell cell2 = row2.createCell(1);
        cell2.setCellValue(tt.getMahd());

        Row row3 = sheet.createRow(rowNum++);
        Cell cell3 = row3.createCell(0);
        cell3.setCellValue("Khách hàng: ");
        Cell cell4 = row3.createCell(1);
        cell4.setCellValue(tt.getTenkhach());

        Row row4 = sheet.createRow(rowNum++);
        Cell cell5 = row4.createCell(0);
        cell5.setCellValue("Thời gian: ");
        Cell cell6 = row4.createCell(1);
        cell6.setCellValue(ChuyenDate((java.sql.Date) tt.getNgayLap()));

        Row row5 = sheet.createRow(rowNum++);
        Cell cell7 = row5.createCell(0);
        cell7.setCellValue("Nhân viên: ");
        Cell cell8 = row5.createCell(1);
        cell8.setCellValue(tt.getTennhanvien());

        Row rowTienPhong = sheet.createRow(rowNum++);
        Cell cell9 = rowTienPhong.createCell(0);
        cell9.setCellValue("Tiền phòng");

        Row row6 = sheet.createRow(rowNum++);
        Cell cell10 = row6.createCell(0);
        cell10.setCellValue("Phòng");
        Cell cell11 = row6.createCell(1);
        cell11.setCellValue("Thời gian đến");
        Cell cell12 = row6.createCell(2);
        cell12.setCellValue("Thời gian đi");
        Cell cell13 = row6.createCell(3);
        cell13.setCellValue("Thời gian");
        Cell cell14 = row6.createCell(4);
        cell14.setCellValue("Giá phòng");
        Cell cell15 = row6.createCell(5);
        cell15.setCellValue("Thành tiền");

        DefaultTableModel dtm = (DefaultTableModel) jTable_tienPhong.getModel();
        for (int i = 0; dtm.getRowCount() > i; i++) {
            XSSFRow row = sheet.createRow(rowNum++);
            for (int j = 0; dtm.getColumnCount() > j; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(dtm.getValueAt(i, j).toString());
            }
        }

        Row rowTienDV = sheet.createRow(rowNum++);
        Cell cell16 = rowTienDV.createCell(0);
        cell16.setCellValue("Tiền dịch vụ");

        Row row7 = sheet.createRow(rowNum++);
        Cell cell17 = row7.createCell(2);
        cell17.setCellValue("Phòng");
        Cell cell18 = row7.createCell(3);
        cell18.setCellValue("Dịch vụ");
        Cell cell19 = row7.createCell(4);
        cell19.setCellValue("Ngày sử dụng");
        Cell cell20 = row7.createCell(5);
        cell20.setCellValue("Thành tiền");

        DefaultTableModel dtmDV = (DefaultTableModel) jTable_tienDichVu.getModel();
        for (int i = 0; dtmDV.getRowCount() > i; i++) {
            XSSFRow row = sheet.createRow(rowNum++);
            for (int j = 0; dtmDV.getColumnCount() > j; j++) {
                XSSFCell cell = row.createCell(j + 2);
                cell.setCellValue(dtmDV.getValueAt(i, j).toString());
            }
        }

        Row row8 = sheet.createRow(rowNum++);
        Cell cell21 = row8.createCell(4);
        cell21.setCellValue("Tiền dịch vụ:");
        Cell cell22 = row8.createCell(5);
        cell22.setCellValue(jLabel_tienDichVu.getText());
        int t = Integer.parseInt(jLabel_tienDichVu.getText());
        Row row9 = sheet.createRow(rowNum++);
        Cell cell23 = row9.createCell(4);
        cell23.setCellValue("Tiền phòng:");
        Cell cell24 = row9.createCell(5);
        cell24.setCellValue(jLabel_tienPhong.getText());
        t += Integer.parseInt(jLabel_tienPhong.getText());
        Row row10 = sheet.createRow(rowNum++);
        Cell cell25 = row10.createCell(4);
        cell25.setCellValue("TỔNG TIỀN:");
        Cell cell26 = row10.createCell(5);
        cell26.setCellValue(t);

        try {
            FileOutputStream outputStream = new FileOutputStream("E:\\HoaDon " + tt.getMahd() + ".xlsx");// NÀY LÀ ĐƯỜNG DẪN ĐẾN FILE, MỖI LẦN CHẠY LẠI THÌ SẼ XÓA HẾT THÔNG TIN TRONG FILE NÀY VÀ IN 
            Workbook.write(outputStream);
            Workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("xuất hóa đơn nè má!");
        JOptionPane.showMessageDialog(null, "Xuất excel rồi nha!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void jButton_xuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_xuatExcelActionPerformed
//        // ====================Xuất excel qua jTable
//        XSSFWorkbook Workbook = new XSSFWorkbook();
//        XSSFSheet sheet = Workbook.createSheet();
//        DefaultTableModel dtm = (DefaultTableModel) jTable_tienPhong.getModel();
//        for (int i = 0; dtm.getRowCount() > i; i++) {
//            XSSFRow row = sheet.createRow(i);
//            for (int j = 0; dtm.getColumnCount() > j; j++) {
//                XSSFCell cell = row.createCell(j);
//                cell.setCellValue(dtm.getValueAt(i, j).toString());
//            }
//        }
//        try {
//            FileOutputStream outputStream = new FileOutputStream("D:\\DRAFT\\huong1.xlsx");
//            Workbook.write(outputStream);
//            Workbook.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Done");
    }//GEN-LAST:event_jButton_xuatExcelActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDonHuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDonHuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDonHuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XuatHoaDonHuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new XuatHoaDonHuong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXT_KhachHang;
    private javax.swing.JTextField TXT_MaHD;
    private javax.swing.JTextField TXT_NhanVien;
    private javax.swing.JTextField TXT_ThoiGian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_tienDichVu;
    private javax.swing.JLabel jLabel_tienPhong;
    private javax.swing.JLabel jLabel_tongTien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_tienDichVu;
    private javax.swing.JTable jTable_tienPhong;
    // End of variables declaration//GEN-END:variables

}
