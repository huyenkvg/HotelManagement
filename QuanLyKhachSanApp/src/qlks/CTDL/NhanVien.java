package qlks.CTDL;

import java.util.Date;

public class NhanVien {

    private String maNV, ho, ten, phai, email, sDT, maBP;
    private Date ngaySinh;

    public NhanVien() {
        this.maNV = "";
        this.ho = "";
        this.ten = "";
        this.phai = "";
        this.ngaySinh = new Date();
        this.email = "";
        this.sDT = "";
        this.maBP = "";
    }

    public NhanVien(String maNV, String ho, String ten, String phai, Date ngaySinh, String email, String sDT, String maBP) {
        this.maNV = maNV;
        this.ho = ho;
        this.ten = ten;
        this.phai = phai;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.sDT = sDT;
        this.maBP = maBP;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public Date getNgaysinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public String getMaBP() {
        return maBP;
    }

    public void setMaBP(String maBP) {
        this.maBP = maBP;
    }
}
