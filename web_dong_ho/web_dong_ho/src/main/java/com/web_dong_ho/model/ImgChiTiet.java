package com.web_dong_ho.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "img_chi_tiet")
public class ImgChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img;

    @ManyToMany(mappedBy = "imgChiTiet")
    private Set<SanPham> sanPham;

    public ImgChiTiet() {
    }

    public ImgChiTiet(String img) {
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
