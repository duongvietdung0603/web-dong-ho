package com.web_dong_ho.service;

import com.web_dong_ho.model.ImgChiTiet;
import com.web_dong_ho.repository.ImgChiTietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgChiTietService {
    @Autowired
    private ImgChiTietRepository imgChiTietRepository;


    public List<ImgChiTiet> findAll() {
        return imgChiTietRepository.findAll();
    }

    public void save(ImgChiTiet imgChiTiet) {
        imgChiTietRepository.save(imgChiTiet);
    }

    public ImgChiTiet findById(long id) {
        return imgChiTietRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id sản phẩm"));
    }

    public void deleteById(Long id) {
        imgChiTietRepository.deleteById(id);
    }

}
