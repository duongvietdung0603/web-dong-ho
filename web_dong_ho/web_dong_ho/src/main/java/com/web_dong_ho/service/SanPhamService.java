package com.web_dong_ho.service;

import com.web_dong_ho.model.LoaiSanPham;
import com.web_dong_ho.model.SanPham;
import com.web_dong_ho.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;


    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }

    public void save(SanPham sanPham1) {
        sanPhamRepository.save(sanPham1);
    }

    public SanPham findById(long id) {
        return sanPhamRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id sản phẩm"));
    }

    public void deleteById(Long id) {
        sanPhamRepository.deleteById(id);
    }


    public List<SanPham> sanPhamLienQuan(long id) {
        SanPham sanPham = findById(id);
        return sanPhamRepository.findTop6ByStatusAndLoaiSanPhamIdAndIdNot((byte) 0,sanPham.getLoaiSanPham().getId(), id);
    }


    public Page<SanPham> index(Long loai,Pageable pageable) {
        if(loai == 0){
            return findAllPageable(pageable);
        }else {
            return findByLoaiSanPhamIdPageable(loai,pageable);
        }
    }

    private Page<SanPham> findByLoaiSanPhamIdPageable(Long loai, Pageable pageable) {
        return sanPhamRepository.findByLoaiSanPhamIdAndStatus(loai,(byte) 0,pageable);
    }

    private List<SanPham> findByLoaiSanPhamId(Long loai) {
        return sanPhamRepository.findByLoaiSanPhamId(loai);
    }

    public List<SanPham> sanPhamMoi() {
        return sanPhamRepository.findTop8ByStatusOrderByNgayDangDesc((byte) 0);
    }
    public List<SanPham> sanPhamNoiBat() {
        return sanPhamRepository.findTop8ByStatusOrderByLuotTruyCapDesc((byte) 0);
    }

    public Page<SanPham> findAllPageable(Pageable pageable) {
        return sanPhamRepository.findByStatus((byte) 0,pageable);
    }

    public Page<SanPham> sanPhamAdmin(String search, Pageable pageable) {
        return sanPhamRepository.findByNameContainingOrderByIdDesc(search, pageable);
    }

    public List<SanPham> findByNameContaining(String search) {
        return sanPhamRepository.findByNameContaining(search);
    }
}
