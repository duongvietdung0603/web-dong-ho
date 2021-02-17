package com.web_dong_ho.repository;

 import com.web_dong_ho.model.TinTuc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TinTucRepository extends JpaRepository<TinTuc, Long> {

    List<TinTuc> findByTitleContaining(String search);

    List<TinTuc> findTop3ByOrderByNgayDangDesc();

    TinTuc findFirstByOrderByIdAsc();

    List<TinTuc> findTop2ByOrderByIdDesc();

    List<TinTuc> findTop6ByOrderByNgayDangDesc();
}
