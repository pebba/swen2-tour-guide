package technikum.bohrffer.swen2tourguide.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import technikum.bohrffer.swen2tourguide.models.Tour;

import java.io.IOException;
import java.util.List;

public class ReportService {

    public void generateReport(List<Tour> tours, String outputPath) {
        try {
            PdfWriter writer = new PdfWriter(outputPath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Tour Report"));

            for (Tour tour : tours) {
                document.add(new Paragraph("Name: " + tour.getName()));
                document.add(new Paragraph("Description: " + tour.getDescription()));
                document.add(new Paragraph("From: " + tour.getFrom()));
                document.add(new Paragraph("To: " + tour.getTo()));
                document.add(new Paragraph("Transport: " + tour.getTransportType()));
                document.add(new Paragraph("Distance: " + tour.getDistance()));
                document.add(new Paragraph("Estimated Time: " + tour.getEstimatedTime()));
                document.add(new Paragraph("\n"));
            }

            document.close();
            System.out.println("Report generated successfully at " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
