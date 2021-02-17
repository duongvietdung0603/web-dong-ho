package com.web_dong_ho.service;


import com.web_dong_ho.model.HoaDonChiTiet;
import com.web_dong_ho.repository.HoaDonChiTietRepository;
import com.web_dong_ho.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class HoaDonChiTietService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public List<HoaDonChiTiet> findAll() {
        return hoaDonChiTietRepository.findAll();
    }

    public void save(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }


    public List<HoaDonChiTiet> findByHoaDonUserId(Long id_user) {
        return hoaDonChiTietRepository.findByHoaDonUserId(id_user);
    }

    public List<HoaDonChiTiet> findByHoaDonId(Long id) {
        return hoaDonChiTietRepository.findByHoaDonId(id);
    }

    public void deleteById(Long id) {
        hoaDonChiTietRepository.deleteById(id);
    }
}
