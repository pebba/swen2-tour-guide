package technikum.bohrffer.swen2tourguide.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import technikum.bohrffer.swen2tourguide.models.Tour;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    private static final String REPORT_TEMPLATE = """
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE jasperReport PUBLIC "-//JasperReports//DTD Report Design//EN" "http://jasperreports.sourceforge.net/dtds/jasperreport.dtd">
        <jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="tour_report" pageWidth="595" pageHeight="842" columnWidth="555" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20">
            <field name="name" class="java.lang.String"/>
            <detail>
                <band height="20">
                    <textField>
                        <reportElement x="0" y="0" width="100" height="20"/>
                        <textFieldExpression><![CDATA[$F{name}]]></textFieldExpression>
                    </textField>
                </band>
            </detail>
        </jasperReport>
        """;

    public void generateReport(List<Tour> tours, String outputPath) {
        try {
            ByteArrayInputStream templateStream = new ByteArrayInputStream(REPORT_TEMPLATE.getBytes(StandardCharsets.UTF_8));
            System.out.println("Template stream created successfully.");

            System.out.println("Report Template: \n" + REPORT_TEMPLATE);

            JasperDesign jasperDesign = JRXmlLoader.load(templateStream);
            System.out.println("JasperDesign loaded successfully.");

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("JasperReport compiled successfully.");

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tours);
            System.out.println("DataSource created successfully.");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Tour Guide Application");
            System.out.println("Parameters set successfully.");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            System.out.println("JasperPrint filled successfully.");

            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);
            System.out.println("Report generated successfully at " + outputPath);
        } catch (JRException e) {
            System.err.println("JRException: Unable to generate report: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
