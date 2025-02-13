package com.kaan;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class PDFController {
    @PostMapping("/generate")
    public ResponseEntity<String> generatePdf(
            @RequestParam String type,
            @RequestParam String ad_soyad,
            @RequestParam String tarih,
            @RequestParam String adres,
            @RequestParam String dogumYeri,
            @RequestParam String cinsiyet,
            @RequestParam String tckn
    ) {

        try {
            PdfGenerator generator = PdfGeneratorFactory.getPdfGenerator(type);
            generator.generatePdf(ad_soyad, tarih, adres, dogumYeri, tckn, cinsiyet);
            return ResponseEntity.ok("PDF başarıyla oluşturuldu: " + type);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Hata oluştu: " + e.getMessage());
        }
    }
}
