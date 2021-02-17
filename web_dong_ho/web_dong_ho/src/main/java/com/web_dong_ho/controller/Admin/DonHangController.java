package com.web_dong_ho.controller.Admin;

import com.web_dong_ho.model.HoaDon;
import com.web_dong_ho.service.HoaDonChiTietService;
import com.web_dong_ho.service.HoaDonService;
import com.web_dong_ho.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/don-hang")
public class DonHangController {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;



    @GetMapping("")
    public String index(Model model){
        model.addAttribute("DonHang", hoaDonService.findAll());
        model.addAttribute("DonHangChiTiet", hoaDonChiTietService.findAll());
        return "Admin/hoa_don";
    }

    @GetMapping("/sua-status/{id}")
    public String status(Model mode, @PathVariable Long id){
         hoaDonService.suaStatus(id);
        return "redirect:/admin/don-hang";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(Model mode, @PathVariable Long id){
        hoaDonService.deleteById(id);
        return "redirect:/admin/don-hang";
    }
}
