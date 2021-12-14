package qlks.CTDL;

public class Phong {

    private String trangThai, hangPhong;
    private int maPhong;

    public Phong() {
        this.maPhong = 0;
        this.trangThai = "";
        this.hangPhong = "";
    }

    public Phong(int maPhong, String trangThai, String hangPhong) {
        this.maPhong = maPhong;
        this.trangThai = trangThai;
        this.hangPhong = hangPhong;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getHangPhong() {
        return hangPhong;
    }

    public void setHangPhong(String hangPhong) {
        this.hangPhong = hangPhong;
    }

}
