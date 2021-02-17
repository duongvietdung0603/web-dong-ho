package com.web_dong_ho.controller.Admin;

import com.web_dong_ho.model.LoaiSanPham;
import com.web_dong_ho.service.LoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("admin/loai-san-pham")
public class LoaiSanPhamController {

    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("DanhSach", loaiSanPhamService.findAll());
        return "Admin/loai_san_pham";
    }


    @GetMapping("/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
        loaiSanPhamService.deleteById(id);
        return "redirect:/admin/loai-san-pham";
    }

    @GetMapping("/them")
    public String add(Model model){
        model.addAttribute("LoaiSP", new LoaiSanPham());
        model.addAttribute("Active","Thêm mới");
        return "Admin/loai_san_pham_add";
    }

    @PostMapping("/save")
    public String add_post(@ModelAttribute LoaiSanPham theLoaiSach){
        loaiSanPhamService.save(theLoaiSach);
        return "redirect:/admin/loai-san-pham";
    }

    @GetMapping("/sua/{id}")
    public String sua(Model model,@PathVariable Long id){
        model.addAttribute("LoaiSP", loaiSanPhamService.findById(id));
        model.addAttribute("Active","Sửa id "+id);
        return "Admin/loai_san_pham_add";
    }


}
