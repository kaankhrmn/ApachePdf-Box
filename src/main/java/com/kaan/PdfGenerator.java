package com.kaan;

public interface PdfGenerator {

    void generateKisiKarti(String ad_soyad,String adres, String dogumYeri, String tckn, String cinsiyet);

    void generateBasariBelgesi(String ad_soyad, String tarih);
}
