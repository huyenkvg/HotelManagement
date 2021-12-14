package qlhp.CTDL;

public class HangPhong {

    private String tenHP, loaiPhong, kieuPhong;
   

    public HangPhong() {
        this.tenHP = "";
        this.loaiPhong = "";
        this.kieuPhong = "";
    }

    public HangPhong(String tenHP, String loaiPhong, String kieuPhong) {
        this.tenHP = tenHP;
        this.loaiPhong = loaiPhong;
        this.kieuPhong = kieuPhong;
    }
    
    public HangPhong(String tenHP){
        this.tenHP = tenHP;
    }



    public String gettenHP() {
        return tenHP;
    }

    public void setMaPhong(String tenHP) {
        this.tenHP = tenHP;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getKieuPhong() {
        return kieuPhong;
    }

    public void setKieuPhong(String kieuPhong) {
        this.kieuPhong = kieuPhong;
    }
}
