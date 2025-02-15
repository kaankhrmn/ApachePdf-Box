package com.kaan;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;

public class KisiKarti implements PdfGenerator {

    @Override
    public void generateKisiKarti(String ad_soyad,String adres, String dogumYeri, String tckn, String cinsiyet) {
        String filename = "KisiKarti_" + ad_soyad + ".pdf";
        PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(font, 16);
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(ad_soyad);
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText(dogumYeri);
            contentStream.newLineAtOffset(100, 650);
            contentStream.showText(tckn);
            contentStream.newLineAtOffset(100, 600);
            contentStream.showText(cinsiyet);
            contentStream.newLineAtOffset(100, 550);
            contentStream.showText(adres);
            contentStream.endText();
            contentStream.close();

//            document.save(filename);
            System.out.println(filename + " oluşturuldu.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateBasariBelgesi(String ad_soyad, String tarih) {

    }
}
