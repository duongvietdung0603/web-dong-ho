package com.web_dong_ho.service;

import com.web_dong_ho.model.GioHang;
import com.web_dong_ho.model.SanPham;
import com.web_dong_ho.model.User;
import com.web_dong_ho.repository.GioHangRepository;
import org.apache.catalina.session.StandardSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private UserService userService;


    public List<GioHang> findAll() {
        return gioHangRepository.findAll();
    }

    public void save(GioHang gioHang) {
        gioHangRepository.save(gioHang);
    }

    public GioHang postGioHang(GioHang gioHang) {
        SanPham sanPham = sanPhamService.findById(gioHang.getSanPham().getId());
        GioHang gioHang1 = gioHangRepository.findByUserIdAndSanPhamId(gioHang.getUser().getId(), sanPham.getId());
        if(gioHang1 == null){
            System.out.println("chưa có");
        }else {
             gioHang.setId(gioHang1.getId());
        }
        gioHang.setTongTien(gioHang.getSoLuong() * sanPham.getGiaTien());
        save(gioHang);
        return gioHang;
    }

    public List<GioHang> findByUserId(Long id_user) {
        return gioHangRepository.findByUserId(id_user);
    }

    public void upDate(Long id, Integer soLuong) {
        GioHang gioHang1 = findById(id);
        SanPham sanPham = sanPhamService.findById(gioHang1.getSanPham().getId());
        gioHang1.setSoLuong(soLuong);
        gioHang1.setTongTien(gioHang1.getSoLuong() * sanPham.getGiaTien());
        save(gioHang1);
    }

    private GioHang findById(Long id) {
        return gioHangRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k tìm thấy id giỏ hàng"));
    }

    public void deleteById(Long id) {
        gioHangRepository.deleteById(id);
    }

    public void deleteAll(Long id_user) {
        List<GioHang> gioHangs = findByUserId(id_user);
        gioHangs.forEach(element->{
            deleteById(element.getId());
        });
     }

    public double tongTien(Long id_user) {
        double tongTien = 0;
        List<GioHang> gioHangs = findByUserId(id_user);
        for (GioHang element : gioHangs) {
            tongTien += element.getTongTien();
        }
        return tongTien;
    }


    public int soLuongGioHang(Long id) {
         List<GioHang> gioHangs = findByUserId(id);
        System.out.println("so luong "+ gioHangs.size());
        return gioHangs.size();
    }

    public void addNew(User user, SanPham sanPham, Integer soLuong, String size, String mauSac) {
        GioHang gioHang = new GioHang(soLuong, sanPham,user);
        double giaTien = sanPham.getGiaTien() - sanPham.getGiaTien() * sanPham.getSale() / 100;
        gioHang.setTongTien(soLuong * giaTien);

        GioHang gioHang1 = gioHangRepository.findByUserIdAndSanPhamId(gioHang.getUser().getId(), sanPham.getId());
        if(gioHang1 == null){
            System.out.println("chưa có");
        }else {
            gioHang.setId(gioHang1.getId());
        }

        save(gioHang);
     }
}
