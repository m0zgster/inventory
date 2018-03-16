package com.aligntech.inventory.utils;

import com.aligntech.inventory.entities.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;

/**
 * Created by mozg on 16.03.2018.
 * inventory
 */
public class ExcelHelper {

    private static final String MIME_TYPE = "application/vnd.ms-excel";

    public static void createExcelData(Collection<Product> products, HttpServletResponse response) throws IOException {
        if (products != null && !products.isEmpty()) {
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Products");

            Row header = sheet.createRow(0);

            header.createCell(0, CellType.STRING).setCellValue("Name");
            header.createCell(1, CellType.STRING).setCellValue("Brand");
            header.createCell(2, CellType.NUMERIC).setCellValue("Price");
            header.createCell(3, CellType.NUMERIC).setCellValue("Quantity");

            int index = header.getRowNum() + 1;

            for (Product product : products) {
                Row row = sheet.createRow(index++);

                Cell cellName = row.createCell(0);
                cellName.setCellValue(product.getName());

                Cell cellBrand = row.createCell(1);
                cellBrand.setCellValue(product.getBrand());

                Cell cellPrice = row.createCell(2);
                cellPrice.setCellValue(product.getPrice());

                Cell cellQuantity = row.createCell(3);
                cellQuantity.setCellValue(product.getQuantity());
            }

            response.setContentType(MIME_TYPE);
            response.setHeader("Content-Disposition", "inline; filename=\"result.xls\"");
            wb.write(response.getOutputStream());
        }
    }
}
