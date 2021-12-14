package qlks.CTDL;

public class KhachHang {

    private String cMND, ho, ten, diaChi, sDT, maSoThue;

    public KhachHang() {
        this.cMND = "";
        this.ho = "";
        this.ten = "";
        this.diaChi = "";
        this.sDT = "";
        this.maSoThue = "";
    }

    public KhachHang(String cMND, String ho, String ten, String diaChi, String sDT, String maSoThue) {
        this.cMND = cMND;
        this.ho = ho;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.maSoThue = maSoThue;
    }

    public String getCMND() {
        return cMND;
    }

    public void setCMND(String cMND) {
        this.cMND = cMND;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setdiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }
}
