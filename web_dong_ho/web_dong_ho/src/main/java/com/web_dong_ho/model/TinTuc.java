package com.web_dong_ho.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tin_tuc")
public class TinTuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img;

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String contentShort = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in congue mi. Donec non tellus ac massa ultrices blandit eget et metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In ut mollis erat. Cras accumsan justo suscipit, iaculis lectus at, rhoncus ipsum. Quisque elementum tortor eu sapien molestie vehicula. Donec quis tellus a leo dapibus varius. Quisque non maximus elit. Praesent maximus ante ac turpis suscipit vulputate. Praesent tempus, magna nec feugiat ullamcorper, purus augue egestas lacus, ut tempus felis odio vel mi. Duis id ante quis sapien condimentum ultricies quis vitae ex. In at tortor ligula.";

    @Column(columnDefinition = "LONGTEXT")
    private String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in congue mi. Donec non tellus ac massa ultrices blandit eget et metus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In ut mollis erat. Cras accumsan justo suscipit, iaculis lectus at, rhoncus ipsum. Quisque elementum tortor eu sapien molestie vehicula. Donec quis tellus a leo dapibus varius. Quisque non maximus elit. Praesent maximus ante ac turpis suscipit vulputate. Praesent tempus, magna nec feugiat ullamcorper, purus augue egestas lacus, ut tempus felis odio vel mi. Duis id ante quis sapien condimentum ultricies quis vitae ex. In at tortor ligula.";

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ngayDang;

    public TinTuc() {
    }

    public TinTuc(String img, String title) {
        this.img = img;
        this.title = title;
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

    public String getContentShort() {
        return contentShort;
    }

    public void setContentShort(String contentShort) {
        this.contentShort = contentShort;
    }
}
