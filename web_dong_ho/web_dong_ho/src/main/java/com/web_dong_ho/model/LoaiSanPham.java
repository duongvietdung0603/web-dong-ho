package com.web_dong_ho.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "loai_san_pham")
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany( mappedBy = "loaiSanPham", cascade = CascadeType.ALL)
    private List<SanPham> sanPham;

    public LoaiSanPham() {
    }

    public LoaiSanPham(String name) {
        this.name = name;
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
}
