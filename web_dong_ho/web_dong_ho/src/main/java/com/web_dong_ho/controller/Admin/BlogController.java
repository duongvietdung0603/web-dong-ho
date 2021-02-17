package com.web_dong_ho.controller.Admin;

import com.web_dong_ho.model.SanPham;
import com.web_dong_ho.model.TinTuc;
import com.web_dong_ho.service.TinTucService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("admin/blog")
public class BlogController {

    @Autowired
    private TinTucService tinTucService;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/img/tin_tuc";



    @GetMapping("")
    public String index(Model model,
                        @RequestParam(name = "search", required = false, defaultValue = "") String search){
        model.addAttribute("TinTuc", tinTucService.index(search));
        model.addAttribute("search", search);
        return "Admin/blog";
    }

    @GetMapping("/them")
    public String addSanPham(Model model){
        model.addAttribute("Title", "Thêm tin tức mới");
        model.addAttribute("TinTuc", new TinTuc());
        return "Admin/blog_add";
    }

    @GetMapping("/sua/{id}")
    public String editSanPham(Model model, @PathVariable Long id){
        TinTuc tinTuc = tinTucService.findById(id);
        model.addAttribute("Title", "Sửa tin tức "+tinTuc.getTitle());
        model.addAttribute("TinTuc", tinTuc);
        return "Admin/blog_add";
    }

    @PostMapping("/them")
    public String addDichVu(@ModelAttribute("TinTuc") TinTuc TinTuc,
                            @RequestParam(name = "image_file",required = false) MultipartFile[] image_file) {
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
        System.out.println("fileNames "+fileNames);

        String[] file = fileNames.toString().split("\\s");

        if(file != null && file.length >0){
            System.out.println("có ảnh");
            System.out.println(file[0].toString());
            TinTuc.setImg(file[0].toString());
        }else {
            System.out.println("k có ảnh");
            if(TinTuc.getId()>0){
                TinTuc tinTuc = tinTucService.findById(TinTuc.getId());
                TinTuc.setImg(tinTuc.getImg());
            }
        }

        tinTucService.save(TinTuc);
        return "redirect:/admin/blog";
    }

    @RequestMapping("/xoa/{id}")
    public String xoa(@PathVariable Long id){
        tinTucService.deleteById(id);
        return "redirect:/admin/blog";
    }
}
