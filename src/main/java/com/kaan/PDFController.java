package com.kaan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf/generate")
public class PDFController {
    @PostMapping("/BasariBelgesi")
    public ResponseEntity<byte[]> generatePdfBasariBelgesi(
            @RequestParam String type,
            @RequestParam String ad_soyad,
            @RequestParam String tarih
    )
    {

        try {
            PdfGenerator generator = PdfGeneratorFactory.getPdfGenerator(type);
            return generator.generateBasariBelgesi(ad_soyad , tarih);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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

        try {
            PdfGenerator generator = PdfGeneratorFactory.getPdfGenerator(type);
            return generator.generateKisiKarti(ad_soyad, adres, dogumYeri, tckn, cinsiyet);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
