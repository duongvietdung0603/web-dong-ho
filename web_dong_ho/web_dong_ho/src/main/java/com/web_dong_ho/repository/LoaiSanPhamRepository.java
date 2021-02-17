package com.web_dong_ho.repository;

  import com.web_dong_ho.model.LoaiSanPham;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;


public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Long> {
    List<LoaiSanPham> findByNameContaining(String search);

    List<LoaiSanPham> findTop8ByOrderByIdAsc();
}
