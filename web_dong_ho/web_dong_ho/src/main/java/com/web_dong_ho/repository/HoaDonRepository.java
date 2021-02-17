package com.web_dong_ho.repository;

 import com.web_dong_ho.model.HoaDon;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;

 import javax.xml.crypto.Data;
 import java.util.Date;
 import java.util.List;

public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    List<HoaDon> findByUserId(Long id_user);


    @Query("SELECT hd FROM HoaDon hd \n" +
            "WHERE YEAR(hd.ngayDat) = 2021 AND MONTH(hd.ngayDat) = ?1")
    List<HoaDon> doanhThuNam(Integer ngay);


    List<HoaDon> findByNgayDat(Date ngay);
}
