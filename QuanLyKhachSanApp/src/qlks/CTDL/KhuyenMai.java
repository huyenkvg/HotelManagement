package qlks.CTDL;

public class KhuyenMai {

    private String maKM, moTa;

    public KhuyenMai() {
        this.maKM = "";
        this.moTa = "";
    }

    public KhuyenMai(String maKM, String moTa) {
        this.maKM = maKM;
        this.moTa = moTa;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
