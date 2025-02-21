package com.kaan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf/generate")
public class PDFController {

    private final BasariBelgesi basariBelgesi;
    private final KisiKarti kisiKarti;

    public PDFController(BasariBelgesi basariBelgesi, KisiKarti kisiKarti) {
        this.basariBelgesi = basariBelgesi;
        this.kisiKarti = kisiKarti;
    }


    @PostMapping("/BasariBelgesi")
    public ResponseEntity<byte[]> generatePdfBasariBelgesi(
            @RequestParam String type,
            @RequestParam String ad_soyad,
            @RequestParam String tarih
    ) {
        return basariBelgesi.generateDocument(type, ad_soyad, tarih);
    }


    @PostMapping("/KisiKarti")
    public ResponseEntity<byte[]> generatePdfKisiKarti(
            @RequestParam String type,
            @RequestParam String ad_soyad,
            @RequestParam String adres,
            @RequestParam String dogumYeri,
            @RequestParam String tckn,
            @RequestParam String cinsiyet

    ) {
        return kisiKarti.generateDocument(type, ad_soyad, adres, dogumYeri, tckn, cinsiyet);
    }
}
