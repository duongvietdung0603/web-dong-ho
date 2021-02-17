package com.web_dong_ho.model;

import javax.persistence.*;

@Entity
@Table(name = "gio_hang")
public class GioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int soLuong;

    private double tongTien;

    @ManyToOne
    @JoinColumn(name = "sanPham_id", nullable = false)
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public GioHang() {
    }

    public GioHang(int soLuong, SanPham sanPham, User user) {
        this.soLuong = soLuong;
        this.sanPham = sanPham;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
