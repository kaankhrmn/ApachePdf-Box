package com.kaan;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class BasariBelgesi implements PdfGenerator {

    @Override
    public ResponseEntity<byte[]> generateBasariBelgesi(String ad_soyad, String tarih) {

        try {
            ClassPathResource pdfTemplate = new ClassPathResource("templates/BasariBelgesi.pdf");
            InputStream inputStream = pdfTemplate.getInputStream();
            PDDocument document = PDDocument.load(inputStream);
            PDPage page = document.getPage(0);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 40);
            contentStream.setNonStrokingColor(Color.black);

            contentStream.beginText();
            contentStream.newLineAtOffset(370, 250);
            contentStream.showText(ad_soyad);
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(450, 450);
            contentStream.showText(tarih);
            contentStream.endText();

            contentStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            // PDF dosyasını indirme başlığıyla yanıt olarak gönder
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=output.pdf");
            headers.add("Content-Type", "application/pdf");

            System.out.println("Basari Belgesi " + ad_soyad+ " için oluşturuldu.");
            return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<byte[]> generateKisiKarti(String ad_soyad, String adres, String dogumYeri, String tckn, String cinsiyet) {
        return null;
    }
}
