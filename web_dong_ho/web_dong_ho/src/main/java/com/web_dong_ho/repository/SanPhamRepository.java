package com.web_dong_ho.repository;

import com.web_dong_ho.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.List;

public interface SanPhamRepository  extends JpaRepository<SanPham, Long> {

    List<SanPham> findTop6ByStatusAndLoaiSanPhamIdAndIdNot(Byte status,Long id, long id1);

    Page<SanPham> findByNameContainingOrderByIdDesc(String search,Pageable pageable);

    Page<SanPham> findByLoaiSanPhamIdAndStatus(Long loai, Byte status, Pageable pageable);
    List<SanPham> findByLoaiSanPhamId(Long loai);

    List<SanPham> findTop8ByStatusOrderByNgayDangDesc(Byte status);

    List<SanPham> findTop8ByStatusOrderByLuotTruyCapDesc(Byte status);

    Page<SanPham> findByStatus(Byte status, Pageable pageable);

    List<SanPham> findByNameContaining(String search);
}
