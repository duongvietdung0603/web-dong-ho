package com.web_dong_ho.repository;

 import com.web_dong_ho.model.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;

public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {

    List<HoaDonChiTiet> findByHoaDonUserId(Long id_user);

    List<HoaDonChiTiet> findByHoaDonId(Long id);
}
