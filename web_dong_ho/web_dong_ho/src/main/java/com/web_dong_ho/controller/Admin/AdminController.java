package com.web_dong_ho.controller.Admin;

import com.web_dong_ho.Bean.DoanhThuThang;
import com.web_dong_ho.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@CrossOrigin
public class AdminController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private TinTucService tinTucService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private UserService userService;



    @GetMapping("admin")
    public String index(Model model){

        model.addAttribute("TongBlog", tinTucService.findAll().size());
        model.addAttribute("TongSP", sanPhamService.findAll().size());
        model.addAttribute("TongLoaiSP", loaiSanPhamService.findAll().size());
        model.addAttribute("TongUser", userService.findAll().size());
        model.addAttribute("DonHang", hoaDonService.findAll());
        model.addAttribute("DonHangChiTiet", hoaDonChiTietService.findAll());
        model.addAttribute("DoanhThuNgay", hoaDonService.doanhThuNgay());
         return "Admin/dashboard";
    }

    @GetMapping("/doanh-thu-nam")
    public ResponseEntity<List<DoanhThuThang>> doanhThu(){
        return new ResponseEntity<>(hoaDonService.doanhThu(), HttpStatus.OK);
    }

}
