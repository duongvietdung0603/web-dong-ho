package com.web_dong_ho.service;

import com.web_dong_ho.model.LoaiSanPham;
import com.web_dong_ho.repository.LoaiSanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoaiSanPhamService {
    @Autowired
    private LoaiSanPhamRepository loaiSPRepository;

    public List<LoaiSanPham> findAll() {
        return loaiSPRepository.findAll();
    }

    public void save(LoaiSanPham loaiSP1) {
        loaiSPRepository.save(loaiSP1);
    }


    public List<LoaiSanPham> index(String search) {
        List<LoaiSanPham> loaiSanPhams = new ArrayList<>();
        if(search.equals("")){
            loaiSanPhams = findAll();
        }else {
            loaiSanPhams = loaiSPRepository.findByNameContaining(search);
        }
        return loaiSanPhams;
    }


    public void deleteById(Long id) {
        loaiSPRepository.deleteById(id);
    }


    public List<LoaiSanPham> findTop8ByOrderByIdAsc() {
        return loaiSPRepository.findTop8ByOrderByIdAsc();
    }

    public LoaiSanPham findById(Long id) {
        return loaiSPRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id loại sp"));
    }
}
