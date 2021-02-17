package com.web_dong_ho.controller;

 import com.web_dong_ho.model.SanPham;
 import com.web_dong_ho.model.User;
 import com.web_dong_ho.service.*;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.domain.Page;
 import org.springframework.data.domain.PageRequest;
 import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
 import org.springframework.web.bind.annotation.*;

 import javax.servlet.http.HttpSession;
 import java.util.ArrayList;
 import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ImgChiTietService imgChiTietService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private UserService userService;
    @Autowired
    private GioHangService gioHangService;
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TinTucService tinTucService;


    @GetMapping("/test")
    public ResponseEntity<List<SanPham>> doanhThu(){
        return new ResponseEntity<>(sanPhamService.findAll(), HttpStatus.OK);
    }

    @GetMapping("")
    public String TrangChu(Model model, HttpSession session){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("============"+username+"======================");
        User user = userService.findByEmail(username);

        if(!username.equals("anonymousUser")){
            session.setAttribute("card", gioHangService.findByUserId(user.getId()));
            session.setAttribute("card_soLuong", gioHangService.soLuongGioHang(user.getId()));
        }
        model.addAttribute("TinTuc", tinTucService.findAll());
        model.addAttribute("SanPhamMoi", sanPhamService.sanPhamMoi());
        model.addAttribute("SanPhamNoiBat", sanPhamService.sanPhamNoiBat());
        model.addAttribute("LoaiSanPham", loaiSanPhamService.findTop8ByOrderByIdAsc());
        return "trang_chu";
    }

//    @GetMapping("phan-trang")
//    public String test_1(Model model,@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//                         @RequestParam(name = "size", required = false, defaultValue = "5") Integer size){
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<SanPham> sanPhamPage = sanPhamService.findAllPageable(pageable);
//
//        model.addAttribute("SanPham",sanPhamPage);
//        model.addAttribute("tongSoPage", sanPhamPage.getTotalPages());
//        model.addAttribute("pageHienTai", page);
//
//        return "test";
//    }

    @GetMapping("/danh-sach")
    public String danhSach(Model model,
                           @RequestParam(required = false, value = "loai-san-pham", defaultValue = "0") Long loai,
                           @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(name = "size", required = false, defaultValue = "9") Integer size
                           ){
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhamPage = sanPhamService.index(loai, pageable);
        model.addAttribute("SanPham", sanPhamPage);
        model.addAttribute("LoaiSanPham", loaiSanPhamService.findAll());
        model.addAttribute("Loai", loai);

        model.addAttribute("tongSoPage", sanPhamPage.getTotalPages());
        model.addAttribute("pageHienTai", page);
        return "danh_sach";
    }

    @GetMapping("/chi-tiet/{id}")
    public String chiTiet(Model model, @PathVariable Long id){
        model.addAttribute("SanPham", sanPhamService.findById(id));
        model.addAttribute("SanPhamLienQuan", sanPhamService.sanPhamLienQuan(id));
        return "chi_tiet";
    }

//    giỏ hàng
    @PostMapping("/gio-hang/{id}")
    public String gioHangAdd(Model model, @PathVariable Long id, HttpSession session,
                             @RequestParam(name = "soLuong", required = false, defaultValue = "0") Integer soLuong,
                             @RequestParam(name = "size", required = false, defaultValue = "0") String size,
                             @RequestParam(name = "mauSac", required = false, defaultValue = "0") String mauSac
                             ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("============"+username+"======================");
        User user = userService.findByEmail(username);

        SanPham sanPham = sanPhamService.findById(id);
        gioHangService.addNew(user, sanPham, soLuong, size, mauSac);

        session.setAttribute("card", gioHangService.findByUserId(user.getId()));
        session.setAttribute("card_soLuong", gioHangService.soLuongGioHang(user.getId()));

        return "redirect:/chi-tiet/"+id;
     }

    @GetMapping("/gio-hang")
    public String gioHang(Model model, HttpSession session){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("============"+username+"======================");
        User user = userService.findByEmail(username);

        session.setAttribute("card", gioHangService.findByUserId(user.getId()));
        session.setAttribute("card_soLuong", gioHangService.soLuongGioHang(user.getId()));


        model.addAttribute("GioHang", gioHangService.findByUserId(user.getId()));
        model.addAttribute("TongTien", gioHangService.tongTien(user.getId()));

        return "gio_hang";
    }

    @GetMapping("/gio-hang/xoa/{id}")
    public String gioHangDelete(@PathVariable Long id){
        gioHangService.deleteById(id);
        return "redirect:/gio-hang";
    }


    //thanh toán
    @GetMapping("/thanh-toan")
    public String thanhToan(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("============"+username+"======================");
        User user = userService.findByEmail(username);

        model.addAttribute("GioHang", gioHangService.findByUserId(user.getId()));
        model.addAttribute("TongTien", gioHangService.tongTien(user.getId()));
        return "thanh_toan";
    }

    @PostMapping("/hoa-don")
    public String hoaDon(@RequestParam String hoTen,
                         @RequestParam String soDT,
                         @RequestParam String email,
                         @RequestParam String diaChi,
                         @RequestParam String ghiChu
     ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("============"+username+"======================");
        User user = userService.findByEmail(username);

        hoaDonService.postHoaDon(user.getId(), hoTen,soDT,email,diaChi,ghiChu, gioHangService.tongTien(user.getId()));
        return "redirect:/don-hang";
    }

    @GetMapping("/don-hang")
    public String donHang(Model model, HttpSession session
    ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("============"+username+"======================");
        User user = userService.findByEmail(username);

        session.setAttribute("card", gioHangService.findByUserId(user.getId()));
        session.setAttribute("card_soLuong", gioHangService.soLuongGioHang(user.getId()));

        model.addAttribute("user",user);
        model.addAttribute("DonHang", hoaDonService.findByUserId(user.getId()));
        model.addAttribute("DonHangChiTiet", hoaDonChiTietService.findByHoaDonUserId(user.getId()));
        return "don_hang";
    }

    @PostMapping("/thong-tin-ca-nhan")
    public String ThongTin(@ModelAttribute User user, HttpSession session){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        System.out.println("============"+username+"======================");
        User user1 = userService.findByEmail(username);

        user.setId(user1.getId());
        if(user.getPassword() != null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.save(user);
        return "redirect:/don-hang";
    }


    //    liên hệ giới thiệu
    @GetMapping("/gioi-thieu")
    public String gioiThieu(){
        return "gioi_thieu";
    }

    @GetMapping("/chinh-sach")
    public String chinh_sach(){
        return "chinh_sach";
    }

    @GetMapping("/lien-he")
    public String lienHe(){
        return "lien_he";
    }

    //blog
    @GetMapping("/blog")
    public String blog(Model model){
        model.addAttribute("DanhSach",tinTucService.findAll());
        model.addAttribute("DanhSachTop1",tinTucService.findTop1());
        model.addAttribute("DanhSachTop2",tinTucService.findTop2());
        model.addAttribute("DanhSachNew",tinTucService.findTop6New());
        return "blog";
    }
    @GetMapping("/blog/{id}")
    public String blogItem(Model model, @PathVariable Long id){
        model.addAttribute("ChiTietId", tinTucService.findById(id));
        model.addAttribute("DanhSachNew",tinTucService.findTop6New());

        return "blogItem";
    }


    @PostMapping("/search")
    public String blogItem(Model model, @RequestParam(name = "search", required = false, defaultValue = "") String search){
        model.addAttribute("SanPham", sanPhamService.findByNameContaining(search));
        model.addAttribute("LoaiSanPham", loaiSanPhamService.findAll());
        model.addAttribute("Loai", 0);
        model.addAttribute("search", search);

        return "danh_sach";
     }
}
