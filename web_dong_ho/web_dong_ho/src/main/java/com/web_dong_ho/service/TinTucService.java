package com.web_dong_ho.service;

 import com.web_dong_ho.model.TinTuc;
 import com.web_dong_ho.repository.TinTucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TinTucService {
    @Autowired
    private TinTucRepository tinTucRepository;

    public List<TinTuc> findAll() {
        return tinTucRepository.findAll();
    }

    public List<TinTuc> findTop3ByOrderByNgayDangDesc() {
        return tinTucRepository.findTop3ByOrderByNgayDangDesc();
    }

    public void save(TinTuc tinTuc1) {
        tinTucRepository.save(tinTuc1);
    }

    public List<TinTuc> index(String search) {
        List<TinTuc> tinTucs = new ArrayList<>();
        if(search.equals("")){
            tinTucs = findAll();
        }else {
            tinTucs = tinTucRepository.findByTitleContaining(search);
        }
        return tinTucs;
    }

    public TinTuc findById(Long id) {
        return tinTucRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id tin tức"));
    }

    public void deleteById(Long id) {
        tinTucRepository.deleteById(id);
    }



    public TinTuc findTop1() {
        return tinTucRepository.findFirstByOrderByIdAsc();
    }

    public List<TinTuc> findTop2() {
        return tinTucRepository.findTop2ByOrderByIdDesc();
    }

    public List<TinTuc> findTop6New() {
        return tinTucRepository.findTop6ByOrderByNgayDangDesc();
    }
}
