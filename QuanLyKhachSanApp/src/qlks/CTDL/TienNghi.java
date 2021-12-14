package qlhp.CTDL;

public class TienNghi {

    private String maTN, tenTN;

    public TienNghi() {
        this.maTN = "";
        this.tenTN = "";
    }

    public TienNghi(String maTN, String tenTN) {
        this.maTN = maTN;
        this.tenTN = tenTN;

    }

    public TienNghi(String tenTN) {
        this.tenTN = tenTN;
    }

    public String getMaTN() {
        return maTN;
    }

    public void setMaTN(String maTN) {
        this.maTN = maTN;
    }

    public String getTenTN() {
        return tenTN;
    }

    public void setTenTN(String tenTN) {
        this.tenTN = tenTN;
    }
}
