package com.web_dong_ho.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
  import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double giaTien = 1000;
    private double sale = 0;
    private String img = "sp_0.jpg";
    private String title ="Đồng hồ Bentley BL1784-252KCD-S2 là mẫu đồng hồ cơ mới nhất hiện nay, xuất xứ thương hiệu đồng hồ của Đức nổi tiếng hàng đầu về sự chính xác và bền bỉ trong nghệ thuật chế tác đồng hồ";
    @Column(columnDefinition = "LONGTEXT")
    private String content = "Đang cập nhật";

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ngayDang;

    private int luotTruyCap = 100;
    private byte status = 0; // 1 là ẩn

    @ManyToOne
    @JoinColumn(name = "loaiSanPham_id", nullable = false)
    private LoaiSanPham loaiSanPham;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "san_pham_img_chi_tiet",
            joinColumns = @JoinColumn(name = "san_pham_id"),
            inverseJoinColumns = @JoinColumn(name = "imgChiTiet_id")
    )
    private Set<ImgChiTiet> imgChiTiet;

    @OneToMany( mappedBy = "sanPham", cascade = CascadeType.ALL)
    private List<GioHang> gioHang;

    @OneToMany( mappedBy = "sanPham", cascade = CascadeType.ALL)
    private List<HoaDonChiTiet> hoaDonChiTiet;

    public SanPham() {
    }

    public SanPham(String name, double giaTien, double sale, String img, int luotTruyCap, LoaiSanPham loaiSanPham, Set<ImgChiTiet> imgChiTiet) {
        this.name = name;
        this.giaTien = giaTien;
        this.sale = sale;
        this.img = img;
        this.luotTruyCap = luotTruyCap;
        this.loaiSanPham = loaiSanPham;
        this.imgChiTiet = imgChiTiet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public int getLuotTruyCap() {
        return luotTruyCap;
    }

    public void setLuotTruyCap(int luotTruyCap) {
        this.luotTruyCap = luotTruyCap;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public LoaiSanPham getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(LoaiSanPham loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public Set<ImgChiTiet> getImgChiTiet() {
        return imgChiTiet;
    }

    public void setImgChiTiet(Set<ImgChiTiet> imgChiTiet) {
        this.imgChiTiet = imgChiTiet;
    }
}
