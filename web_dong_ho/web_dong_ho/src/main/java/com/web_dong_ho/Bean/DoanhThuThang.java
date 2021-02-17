package com.web_dong_ho.Bean;

public class DoanhThuThang {
    private int thang;
    private int data;

    public DoanhThuThang(int thang, int data) {
        this.thang = thang;
        this.data = data;
    }

    public DoanhThuThang(int thang) {
        this.thang = thang;
    }

    public DoanhThuThang() {
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
