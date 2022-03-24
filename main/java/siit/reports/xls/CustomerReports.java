package siit.reports.xls;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import siit.model.Customer;
import siit.model.Order;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static siit.common.Constants.reportsPath;

@Service
public class CustomerReports {

    public String generateSalesReport(Customer customer){

        String fileName = customer.getName().replaceAll("\\.","-") + ".xlsx";

        try (OutputStream fileOut = new FileOutputStream(reportsPath + "/" + fileName);
             Workbook workbook = new XSSFWorkbook();
            ){
            for (Order order : customer.getOrders()){
                Sheet sheet =  workbook.createSheet(order.getNumber());
                generateContentForWorkBook(sheet, customer, order);
            }
            workbook.write(fileOut);
        }catch (IOException e ){
            throw new RuntimeException("Error when creating XLS: " + e.getMessage());
        }

        return fileName;
    }

    public void generateContentForWorkBook(Sheet sheet, Customer customer, Order order){

        Map<String, CellStyle> styles = createStyles(sheet.getWorkbook());

        int rowCounter = 3;

        Row titleRow = sheet.createRow(rowCounter++);
        titleRow.setHeightInPoints(30);
        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("Sales for " + customer.getName() + " / " + order.getNumber());
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$" + rowCounter + ":$G$" + rowCounter));

        List<String> columnsList = new ArrayList<>(Arrays.asList("Product", "Quantity", "Image", "Value"));
        Row headerRow = sheet.createRow(rowCounter++);
        int corectieStartColoane = 2;

        for ( int i = 0; i < columnsList.size(); i++){
            Cell cell = headerRow.createCell((i + corectieStartColoane));
            cell.setCellValue(columnsList.get(i));
            cell.setCellStyle(styles.get("header"));
        }


        for ( int i = 0; i < order.getOrderProducts().size(); i++){
            String styleName = "";
            Row itemsRow = sheet.createRow(rowCounter++);

            itemsRow.setHeightInPoints(40);

            int counterCols = corectieStartColoane;
            Cell cell = itemsRow.createCell((counterCols++));
            cell.setCellValue(order.getOrderProducts().get(i).getProduct().getName());
            cell.setCellStyle(styles.get("month"));

            cell = itemsRow.createCell((counterCols++));
            cell.setCellValue(order.getOrderProducts().get(i).getQuantity());
            cell.setCellStyle(styles.get("month"));

            cell = itemsRow.createCell((counterCols++));
            cell.setCellValue("");
            createXLSXPicture(order.getOrderProducts().get(i).getProduct().getImage(), cell, sheet);
            cell.setCellStyle(styles.get("month"));

            cell = itemsRow.createCell((counterCols++));
            cell.setCellValue(order.getOrderProducts().get(i).getValue());
            cell.setCellStyle(styles.get("month"));
        }


        for ( int i = 0; i < columnsList.size(); i++){
            sheet.autoSizeColumn(i + corectieStartColoane);
        }
    }
    private void createXLSXPicture(String fileName, Cell cell, Sheet sheet ){
        Path path = Paths.get("./src/main/resources/static/" + fileName);
        try (InputStream is = new FileInputStream(path.toFile())){

            byte[] bytes = IOUtils.toByteArray(is);
            int imageInput = sheet.getWorkbook().addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

            Drawing<?> drawing = sheet.createDrawingPatriarch();
            CreationHelper helper = sheet.getWorkbook().getCreationHelper();
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(cell.getColumnIndex());
            anchor.setRow1(cell.getRowIndex());
            Picture pict = drawing.createPicture(anchor, imageInput);
            pict.resize();

        } catch (IOException e){
            throw new RuntimeException("Eroare la adaugare imagine in XLS: " + e.getMessage());
        }

    }

    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<>();

        short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();

        CellStyle style;
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short)20);
        titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleFont);
        styles.put("title", style);

        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.AUTOMATIC.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short)12);
        monthFont.setColor(IndexedColors.BLACK.getIndex());
        monthFont.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(monthFont);
        styles.put("itemEven", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(monthFont);
        styles.put("itemOdd", style);

        Font dayFont = wb.createFont();
        dayFont.setFontHeightInPoints((short)14);
        dayFont.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(borderColor);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(borderColor);
        style.setFont(dayFont);
        styles.put("weekend_left", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(borderColor);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(borderColor);
        styles.put("weekend_right", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setBorderLeft(BorderStyle.THIN);
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setLeftBorderColor(borderColor);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(borderColor);
        style.setFont(dayFont);
        styles.put("workday_left", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(borderColor);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(borderColor);
        styles.put("workday_right", style);

        style = wb.createCellStyle();
        style.setBorderLeft(BorderStyle.THIN);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(borderColor);
        styles.put("grey_left", style);

        style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(borderColor);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(borderColor);
        styles.put("grey_right", style);

        return styles;
    }

}
