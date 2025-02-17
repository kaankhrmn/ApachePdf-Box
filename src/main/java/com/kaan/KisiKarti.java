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

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class KisiKarti implements PdfGenerator {

    @Override
    public ResponseEntity<byte[]> generateKisiKarti(String ad_soyad, String adres, String dogumYeri, String tckn, String cinsiyet) {

        try {
            ClassPathResource pdfTemplate = new ClassPathResource("templates/KisiKarti.pdf");
            InputStream inputStream = pdfTemplate.getInputStream();
            PDDocument document = PDDocument.load(inputStream);
            PDPage page = document.getPage(0);

            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 13);
            contentStream.setNonStrokingColor(Color.black);


            contentStream.beginText();
            contentStream.newLineAtOffset(480, 355);
            contentStream.showText(ad_soyad);
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(480, 300);
            contentStream.showText(dogumYeri);
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(480, 240);
            contentStream.showText(tckn);
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(480, 185);
            contentStream.showText(cinsiyet);
            contentStream.endText();

            contentStream.beginText();
            contentStream.newLineAtOffset(480, 130);
            contentStream.showText(adres);
            contentStream.endText();

            contentStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            // PDF dosyasını indirme başlığıyla yanıt olarak gönder
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=output.pdf");
            headers.add("Content-Type", "application/pdf");
            System.out.println("KisiKarti " + ad_soyad + " için oluşturuldu");
            return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<byte[]> generateBasariBelgesi(String ad_soyad, String tarih) {
        return null;
    }
}
