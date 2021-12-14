/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huong;

/**
 *
 * @author thuhu
 */
public class ThongTinMaPT {

    private int maPTCu;
    private int phongTT;

   
    public ThongTinMaPT() {
        this.maPTCu = 0;
        this.phongTT = 0;
    }

    public ThongTinMaPT(int maPTCu, int phongTT) {
        this.maPTCu = maPTCu;
        this.phongTT = phongTT;
    }

    public int getMaPTCu() {
        return maPTCu;
    }

    public int getPhongTT() {
        return phongTT;
    }

    public void setMaPTCu(int maPTCu) {
        this.maPTCu = maPTCu;
    }

    public void setPhongTT(int phongTT) {
        this.phongTT = phongTT;
    }
    

   
}
