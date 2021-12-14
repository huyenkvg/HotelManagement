package qlks.CTDL;

public class PhieuThue {

    private String  maNV, maKH;
    private int maPD, maPT;

    public PhieuThue(String maNV, String maKH, int maPD, int maPT) {
        this.maNV = maNV;
        this.maKH = maKH;
        this.maPD = maPD;
        this.maPT = maPT;
    }

    public PhieuThue() {
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getMaPD() {
        return maPD;
    }

    public void setMaPD(int maPD) {
        this.maPD = maPD;
    }

    public int getMaPT() {
        return maPT;
    }

    public void setMaPT(int maPT) {
        this.maPT = maPT;
    }
    

}
