package com.web_dong_ho.service;

import com.web_dong_ho.Bean.DoanhThuThang;
import com.web_dong_ho.model.GioHang;
import com.web_dong_ho.model.HoaDon;
import com.web_dong_ho.model.HoaDonChiTiet;
import com.web_dong_ho.model.User;
import com.web_dong_ho.repository.HoaDonRepository;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private UserService userService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    public void save(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    public void postHoaDon(Long id_User, String hoTen, String soDT, String email, String diaChi, String ghiChu, double tongTien) {
        User user = userService.findById(id_User);
        List<GioHang> gioHangs = gioHangService.findByUserId(id_User);
        int soLuong = gioHangs.size();
        tongTien = tongTien + 30.0;

        LocalDate ngayDD = LocalDate.now();
        Date ngayDat = Date.from(ngayDD.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());


        HoaDon hoaDon = new HoaDon();
        hoaDon.setUser(user);
        hoaDon.setHoTen(hoTen);
        hoaDon.setSoDT(soDT);
        hoaDon.setEmail(email);
        hoaDon.setDiaChi(diaChi);
        hoaDon.setGhiChu(ghiChu);
        hoaDon.setSoLuong(soLuong);
        hoaDon.setTongTien(tongTien);
        hoaDon.setNgayDat(ngayDat);
        save(hoaDon);

        gioHangs.forEach(element->{
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setSoLuong(element.getSoLuong());
            hoaDonChiTiet.setTongTien(element.getTongTien());
            hoaDonChiTiet.setSanPham(element.getSanPham());
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTietService.save(hoaDonChiTiet);
        });

        gioHangService.deleteAll(id_User);
    }

    public List<HoaDon> findByUserId(Long id_user) {
        return hoaDonRepository.findByUserId(id_user);
    }

    public void deleteById(Long id) {
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.findByHoaDonId(id);
        hoaDonChiTiets.forEach(element->{
            hoaDonChiTietService.deleteById(element.getId());
        });
        hoaDonRepository.deleteById(id);
    }

    public List<DoanhThuThang> doanhThu() {
        List<DoanhThuThang> doanhThuThangs = new ArrayList<>();

        for (int i = 1; i <= 12 ; i++) {
            List<HoaDon> hoaDons = hoaDonRepository.doanhThuNam(i);
            DoanhThuThang thang12 = new DoanhThuThang(i);
            hoaDons.forEach(element->{
                int tong = (int) element.getTongTien();
                thang12.setData(thang12.getData()+ tong);
            });
            doanhThuThangs.add(thang12);
        }
        return doanhThuThangs;
    }

    public Double doanhThuNgay() {
        LocalDate ngayDD = LocalDate.now();
        Date ngayNow = Date.from(ngayDD.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        List<HoaDon> hoaDonList = hoaDonRepository.findByNgayDat(ngayNow);

        double tong= 0;
        for(HoaDon hoaDon: hoaDonList){
            tong += hoaDon.getTongTien();
        }
        return tong;
    }

    public void suaStatus(Long id) {
        HoaDon hoaDon = findById(id);
        hoaDon.setStatus((byte) 1);
        save(hoaDon);
    }

    private HoaDon findById(Long id) {
        return hoaDonRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id hoa đơn"));
    }
}
