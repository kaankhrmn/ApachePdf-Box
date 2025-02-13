package com.kaan;

public class PdfGeneratorFactory {
    public static PdfGenerator getPdfGenerator(String type) {
        if (type.equalsIgnoreCase("BasariBelgesi")) {
            return new BasariBelgesi();
        } else if (type.equalsIgnoreCase("KisiKarti")) {
            return new KisiKarti();
        } else {
            throw new IllegalArgumentException("Geçersiz belge türü: " + type);
        }
    }
}
