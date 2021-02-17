package com.web_dong_ho.repository;

 import com.web_dong_ho.model.GioHang;
 import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {

    List<GioHang> findByUserId(Long id_user);

    GioHang findByUserIdAndSanPhamId(Long id, Long id1);

 }
