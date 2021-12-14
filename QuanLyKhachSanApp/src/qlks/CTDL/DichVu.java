package qlks.CTDL;

public class DichVu {

    private String maDV, tenDV;

    public DichVu() {
        this.maDV = "";
        this.tenDV = "";
    }

    public DichVu(String maDV, String tenDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
    }
    
    public DichVu(String tenDV){
        this.tenDV = tenDV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }
}
