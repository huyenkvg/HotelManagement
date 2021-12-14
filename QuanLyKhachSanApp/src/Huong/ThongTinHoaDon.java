/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huong;

import java.sql.Date;

/**
 *
 * @author thuhu
 */
public class ThongTinHoaDon {

    int mahd;
    String tenkhach, sdtkkhach;
    Date ngayLap;
    String tennhanvien;
    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public String getTenkhach() {
        return tenkhach;
    }

    public void setTenkhach(String tenkhach) {
        this.tenkhach = tenkhach;
    }

    public String getSdtkkhach() {
        return sdtkkhach;
    }

    public void setSdtkkhach(String sdtkkhach) {
        this.sdtkkhach = sdtkkhach;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTennhanvien() {
        return tennhanvien;
    }

    public void setTennhanvien(String tennhanvien) {
        this.tennhanvien = tennhanvien;
    }

    

    public ThongTinHoaDon(int mahd, String tenkhach, Date ngayLap, String tennhanvien, String sdtkhach) {
        this.mahd = mahd;
        this.tenkhach = tenkhach;
        this.sdtkkhach = sdtkhach;
        this.ngayLap = ngayLap;
        this.tennhanvien = tennhanvien;
    }
    
}
