package com.techandsolve.usecase.compress;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.JPEGFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CompressPDFUtil {

    private static final float TAMANO_MINIMO = 800;
    private static CompressPDFUtil INSTANCE = new CompressPDFUtil();

    private CompressPDFUtil() {
    }


    public static CompressPDFUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CompressPDFUtil();
        }
        return INSTANCE;
    }


    public byte[] reduce(byte[] b, Long peso) {
        byte[] pdfByte = b;

        if (null == b || b.length == 0) {
            System.err.println("La cadena de bits es nula o está vacía");
        } else if (isLessEqualToWeight(b, peso)) {
            System.err.println("El archivo es menor a " + (peso / 1024) + " KB");
        } else {
            pdfByte = executeReduce(pdfByte);
        }
        return isValid(b, pdfByte) ? pdfByte : b;
    }

    private byte[] executeReduce(byte[] b) {
        byte[] pdfByte = null;
        try (PDDocument document = PDDocument.load(b)) {
            PDPageTree allPages = document.getDocumentCatalog().getPages();
            for (PDPage page : allPages) {
                getImageLocationAndSize(document, page);
            }
            pdfByte = getBytesFromFile(document);
        } catch (IOException e) {
            System.err.println("Error en compresión de archivo PDF");
        }
        return pdfByte;
    }

    private void getImageLocationAndSize(PDDocument document, PDPage page) throws IOException {
        GetImageLocationsAndSize printer = new GetImageLocationsAndSize((metadata, image) -> {
            try {
                validateImage(document, page, image.getImage(), metadata);
            } catch (IOException e) {
                System.err.println("Error en compresión de archivo PDF");
            }
            return null;
        });
        printer.processPage(page);
    }

    private void validateImage(PDDocument document, PDPage page, BufferedImage image, Metadata metadata) throws IOException {
        String orientation = getOrientation(page);
        Dimension dimension;
        if ("v".equals(orientation)) {
            dimension = fixHorizontal(metadata);
        } else {
            dimension = fixVertical(metadata);
        }
        resizeImage(document, page.getResources(), image, dimension, metadata.getObjectName());
    }

    private Dimension fixVertical(Metadata metadata) {

        Dimension dimension = new Dimension();
        if (metadata.getHeight() > TAMANO_MINIMO) {
            float factor = TAMANO_MINIMO / metadata.getHeight();
            dimension.setSize(metadata.getWidth() * factor, TAMANO_MINIMO);
        } else {
            dimension.setSize(metadata.getDisplayX(), metadata.getDisplayY());
        }
        return dimension;
    }

    private Dimension fixHorizontal(Metadata metadata) {
        Dimension dimension = new Dimension();

        if (metadata.getWidth() > TAMANO_MINIMO) {
            float factor = TAMANO_MINIMO / metadata.getWidth();
            dimension.setSize(TAMANO_MINIMO, metadata.getHeight() * factor);
        } else {
            dimension.setSize(metadata.getDisplayX(), metadata.getDisplayY());
        }
        return dimension;
    }

    private String getOrientation(PDPage page) {
        String orientation;
        int width = (int) page.getTrimBox().getWidth();
        int height = (int) page.getCropBox().getHeight();
        if (width / height > 1) {
            orientation = "h";
        } else {
            orientation = "v";
        }
        return orientation;
    }

    private void resizeImage(PDDocument document, PDResources pdResources, BufferedImage originalImage, Dimension dimension, COSName cname) throws IOException {

        int newHeight = (int) dimension.getSize().getHeight();
        int newWidth = (int) dimension.getSize().getWidth();

        if (newHeight > 0f && newWidth > 0f) {
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
            g.dispose();

            PDImageXObject newImage = JPEGFactory.createFromImage(document, resizedImage, 0.9f);
            pdResources.put(cname, newImage);
            resizedImage.flush();
        }
    }

    private boolean isLessEqualToWeight(byte[] b, long peso) {
        return b.length <= peso * 1024L;
    }

    private byte[] getBytesFromFile(PDDocument document) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        return baos.toByteArray();
    }

    private boolean isValid(byte[] original, byte[] file) {
        boolean isValid = (null != original && null != file) && (original.length > file.length);

        if (!isValid) {
            System.err.println("Se retorna original");
        }
        return isValid;
    }


}

