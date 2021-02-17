package com.web_dong_ho.controller;

import com.web_dong_ho.model.ImgChiTiet;
import com.web_dong_ho.model.LoaiSanPham;
import com.web_dong_ho.model.SanPham;
import com.web_dong_ho.model.TinTuc;
import com.web_dong_ho.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Random;

@Controller
public class InitData {
    @Autowired
    private ImgChiTietService imgChiTietService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private TinTucService tinTucService;



    @PostConstruct
    public void intidata(){


//
//        ImgChiTiet imgChiTiet1 = new ImgChiTiet("sp_ct_1.jpg");
//        ImgChiTiet imgChiTiet2 = new ImgChiTiet("sp_ct_2.jpg");
//        ImgChiTiet imgChiTiet3 = new ImgChiTiet("sp_ct_3.jpg");
//        ImgChiTiet imgChiTiet4 = new ImgChiTiet("sp_ct_4.jpg");
//        ImgChiTiet imgChiTiet5 = new ImgChiTiet("sp_ct_5.jpg");
//        ImgChiTiet imgChiTiet6 = new ImgChiTiet("sp_ct_6.jpg");
//        imgChiTietService.save(imgChiTiet1);
//        imgChiTietService.save(imgChiTiet2);
//        imgChiTietService.save(imgChiTiet3);
//        imgChiTietService.save(imgChiTiet4);
//        imgChiTietService.save(imgChiTiet5);
//        imgChiTietService.save(imgChiTiet6);
//
//        LoaiSanPham loaiSanPham1 = new LoaiSanPham("Đồng hồ Rolex Swiss Made");
//        LoaiSanPham loaiSanPham2 = new LoaiSanPham("Đồng hồ Thụy Sỹ Tag Heuer");
//        LoaiSanPham loaiSanPham3 = new LoaiSanPham("Đồng hồ Patek Philippe");
//        LoaiSanPham loaiSanPham4 = new LoaiSanPham("Đồng hồ Omega");
//        LoaiSanPham loaiSanPham5 = new LoaiSanPham("Đồng hồ Orient");
//        LoaiSanPham loaiSanPham6 = new LoaiSanPham("Đồng hồ Citizen");
//        LoaiSanPham loaiSanPham7 = new LoaiSanPham("Đồng hồ Ogival");
//        LoaiSanPham loaiSanPham8 = new LoaiSanPham("Đồng hồ Anne Klein");
//        LoaiSanPham loaiSanPham9 = new LoaiSanPham("Đồng hồ Timex");
//        LoaiSanPham loaiSanPham10 = new LoaiSanPham("Đồng hồ Calvin Klein");
//
//
//        loaiSanPhamService.save(loaiSanPham1);
//        loaiSanPhamService.save(loaiSanPham2);
//        loaiSanPhamService.save(loaiSanPham3);
//        loaiSanPhamService.save(loaiSanPham4);
//        loaiSanPhamService.save(loaiSanPham5);
//        loaiSanPhamService.save(loaiSanPham6);
//        loaiSanPhamService.save(loaiSanPham7);
//        loaiSanPhamService.save(loaiSanPham8);
//        loaiSanPhamService.save(loaiSanPham9);
//        loaiSanPhamService.save(loaiSanPham10);
//
//
//        HashSet<ImgChiTiet> imgChiTiets = new HashSet<>();
//        imgChiTiets.add(imgChiTiet1);
//        imgChiTiets.add(imgChiTiet2);
//        imgChiTiets.add(imgChiTiet3);
//        imgChiTiets.add(imgChiTiet4);
//        imgChiTiets.add(imgChiTiet5);
//        imgChiTiets.add(imgChiTiet6);
//
//        for (int i = 1 ; i <= 16; i++) {
//            Random generator = new Random();
//            int value = generator.nextInt((16 - 1) + 1) + 1;
//            int tryCap = generator.nextInt(100) + 50;
//            int sale = generator.nextInt((5 - 0) + 0) + 0;
//            int tien = generator.nextInt((30 - 10) + 10) + 10;
//
//            String img = "sp_"+value+".jpg";
//            SanPham sanPham = new SanPham("Sản phẩm "+i,tien*1000,  sale * 10, img, tryCap, loaiSanPham1, imgChiTiets );
//            sanPhamService.save(sanPham);
//        }
//
//        for (int i = 17; i <= 32; i++) {
//            Random generator = new Random();
//            int value = generator.nextInt((16 - 1) + 1) + 1;
//            int tryCap = generator.nextInt(100) + 50;
//            int sale = generator.nextInt((5 - 0) + 0) + 0;
//            int tien = generator.nextInt((30 - 10) +10) + 10;
//
//            String img = "sp_"+value+".jpg";
//            SanPham sanPham = new SanPham("Sản phẩm "+i,tien*1000,  sale * 10, img, tryCap, loaiSanPham2, imgChiTiets );
//            sanPhamService.save(sanPham);
//        }
//
//        for (int i = 33; i <= 48; i++) {
//            Random generator = new Random();
//            int value = generator.nextInt((16 - 1) + 1) + 1;
//            int tryCap = generator.nextInt(100) + 50;
//            int sale = generator.nextInt((5 - 0) + 0) + 0;
//            int tien = generator.nextInt((30 - 10) + 10) + 10;
//            String img = "sp_"+value+".jpg";
//            SanPham sanPham = new SanPham("Sản phẩm "+i, tien*1000, sale * 10, img, tryCap, loaiSanPham3, imgChiTiets );
//            sanPhamService.save(sanPham);
//        }
//
//        TinTuc tinTuc1 = new TinTuc("tt_1.jpg","Sản phẩm hot nhất 2020");
//        TinTuc tinTuc2 = new TinTuc("tt_2.jpg","Cách trang điểm cực chất");
//        TinTuc tinTuc3 = new TinTuc("tt_3.jpg","Top 10 sản phẩm được ưu thích thất");
//        TinTuc tinTuc4 = new TinTuc("tt_1.jpg","Sản phẩm hot nhất 2020");
//        TinTuc tinTuc5 = new TinTuc("tt_2.jpg","Cách trang điểm cực chất");
//        TinTuc tinTuc6 = new TinTuc("tt_3.jpg","Top 10 sản phẩm được ưu thích thất");
//        tinTucService.save(tinTuc1);
//        tinTucService.save(tinTuc2);
//        tinTucService.save(tinTuc3);
//        tinTucService.save(tinTuc4);
//        tinTucService.save(tinTuc5);
//        tinTucService.save(tinTuc6);

    }
}
