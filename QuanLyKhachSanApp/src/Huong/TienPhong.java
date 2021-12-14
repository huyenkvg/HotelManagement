/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Huong;

import java.util.Date;

/**
 *
 * @author thuhu
 */
public class TienPhong {

    private int maPhong;
    private Date ngayDen;
    private Date ngayDi;
    private int thoiGian;
    private int giaPhong;
    private int tien;
    

    public TienPhong(int maPhong, Date ngayDen, Date ngayDi, int thoiGian, int giaPhong, int tien) {
        this.maPhong = maPhong;
        this.ngayDen = ngayDen;
        this.ngayDi = ngayDi;
        this.thoiGian = thoiGian;
        this.giaPhong = giaPhong;
        // this.tien = thoiGian*giaPhong;
        this.tien = tien;
    }

    public int getMaPhong() {
        return maPhong;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public int getGiaPhong() {
        return giaPhong;
    }

    public int getTien() {
        return tien;
    }

    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

    public void setGiaPhong(int giaPhong) {
        this.giaPhong = giaPhong;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

}
