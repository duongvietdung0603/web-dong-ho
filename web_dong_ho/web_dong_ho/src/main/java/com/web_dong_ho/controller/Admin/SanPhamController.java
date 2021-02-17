package com.web_dong_ho.controller.Admin;

import com.web_dong_ho.model.SanPham;
import com.web_dong_ho.service.LoaiSanPhamService;
import com.web_dong_ho.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@CrossOrigin
@RequestMapping("admin/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img/san_pham";

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(name = "search", required = false, defaultValue = "") String search,
                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                        @RequestParam(name = "size", required = false, defaultValue = "5") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhamPage = sanPhamService.sanPhamAdmin(search, pageable);
        model.addAttribute("SanPham", sanPhamPage);
        model.addAttribute("search", search);
        model.addAttribute("tongSoPage", sanPhamPage.getTotalPages());
        model.addAttribute("pageHienTai", page);
        return "Admin/san_pham";
    }

    @GetMapping("/them")
    public String addSanPham(Model model){
        model.addAttribute("Title", "Thêm sản phẩm mới");
        model.addAttribute("SanPham", new SanPham());
        model.addAttribute("LoaiSanPham", loaiSanPhamService.findAll());

        return "Admin/san_pham_add";
    }

    @GetMapping("/sua/{id}")
    public String editSanPham(Model model, @PathVariable Long id){
        SanPham sanPham = sanPhamService.findById(id);
        model.addAttribute("Title", "Sửa sản phẩm "+sanPham.getName());
        model.addAttribute("SanPham", sanPham);
        model.addAttribute("LoaiSanPham", loaiSanPhamService.findAll());

        return "Admin/san_pham_add";
    }


    @PostMapping("/them")
    public String addDichVu(@ModelAttribute("SanPham") SanPham sanPham,
                            @RequestParam(name = "image_file",required = false) MultipartFile[] image_file) {
        SanPham sanPham1 = sanPhamService.findById(sanPham.getId());
        sanPham.setImg(sanPham1.getImg());

        StringBuilder fileNames = new StringBuilder();

        for (MultipartFile file : image_file)
        {
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename() + " ");
            try
            {
                Files.write(fileNameAndPath, file.getBytes());
            }
            catch (IOException e)
            {
                System.out.println("lỗi đăng ảnh "+ e);
            }
        }
        System.out.println("fileNames "+fileNames.toString());

        String[] file = fileNames.toString().split("\\s");

        if(file != null && file.length >0){
            System.out.println("Có ảnh");
            sanPham.setImg(file[0].toString());
        }

        sanPhamService.save(sanPham);
        return "redirect:/admin/san-pham";
    }

    @RequestMapping("/xoa/{id}")
    public String xoa(@PathVariable Long id){
        sanPhamService.deleteById(id);
        return "redirect:/admin/san-pham";
    }
}
