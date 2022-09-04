package com.lfs.excel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel
 *
 * @author fushenliao
 * @version 1.0.0
 * @project tecwealth
 * @create 2022/8/30
 * @modify 2022/8/30
 */
public class Excel {

    static String gradeName = "七年级";
    static String[] classCodes = {"初一1班", "初一2班", "初一3班", "初一4班", "初一5班", "初一6班", "初一7班", "初一8班"};
    static String[] weekCodes = {"星期一", "星期二", "星期三", "星期四", "星期五"};
    static String[] courseSeqs = {"1", "2", "3", "4", "5", "6", "7", "8"};
    static int rowDataGridOffset = 2;
    static int colDataGridOffset = 2;

    static Map<String, ExcelWriter> excelWriterMap = new HashMap<String, ExcelWriter>();

    static String inputFileName = "input.xls";

    static String totalCourseExcelName = "总课表.xlsx";
    static String totalCourseExcelHeaderName = "深圳市红桂中学2022-2023学年度第一学期#gradeName#课表（2022）";
    static String classCourseExcelName = "班级课表.xlsx";
    static String classCourseExcelHeaderName = "#class#课表";
    static String teacherCourseExcelName = "老师课表.xlsx";
    static String teacherCourseExcelHeader = "2022-2023学年第一学期#teacher#的课表";


    public static void main(String[] args) throws IOException {
//        String configJson = FileReader.create(new File(getPath("config.json")))
//                .readString();

        String parPath = getParPath(inputFileName);
        ExcelReader reader = ExcelUtil.getReader(getPath(inputFileName));
        List<List<Object>> table = reader.read();



        ExcelWriter totalWriter = new ExcelWriter(new File(parPath + totalCourseExcelName), gradeName);
        ExcelWriter classWriter = new ExcelWriter(new File(parPath + classCourseExcelName));
        ExcelWriter teacherWriter = new ExcelWriter(new File(parPath + teacherCourseExcelName));

        totalWriter.resetRow();
        classWriter.resetRow();
        teacherWriter.resetRow();

        totalWriter.flush();
        classWriter.flush();
        teacherWriter.flush();


        for (int row = rowDataGridOffset; row < rowDataGridOffset + weekCodes.length * courseSeqs.length; row++) {
            List<Object> rows = table.get(row);
            for (int col = colDataGridOffset; col < colDataGridOffset + classCodes.length; col++) {
                int weekIndex = (row - rowDataGridOffset) / courseSeqs.length;
                int courseSeqIndex = (row - rowDataGridOffset) % courseSeqs.length;
                int classIndex = (col - colDataGridOffset);


                String data = rows.get(col).toString();
                String[] split = data.split("\\s+");
                if (split.length < 2) {
                    String course = split[0];
                    writeTotal(totalWriter, weekIndex, courseSeqIndex, classIndex, "", course, 0);

                } else if (split.length == 2) {
                    String teacher = split[1];
                    String course = split[0];
                    writeTotal(totalWriter, weekIndex, courseSeqIndex, classIndex, teacher, course, 0);

                } else {
                    String teacher1 = split[1].split("\\-")[2];
                    String course1 = split[1].split("\\-")[0];
                    writeTotal(totalWriter, weekIndex, courseSeqIndex, classIndex, teacher1, course1, 1);

                    String teacher2 = split[2].split("\\-")[2];
                    String course2 = split[2].split("\\-")[0];
                    writeTotal(totalWriter, weekIndex, courseSeqIndex, classIndex, teacher2, course2, 2);
                }

            }
        }


        totalWriter.flush();

        System.out.println();
    }

    private static void writeTotal(ExcelWriter writer, int weekIndex, int courseSeqIndex, int classIndex, String teacher, String course, int type) {
        int rows = courseSeqIndex + 3;
        int cols = classIndex + (weekIndex * classCodes.length) + 1;

        String text = course + teacher;
        // col, row
        if (type <= 1) {
            writer.writeCellValue(cols, rows, text);

            writer.setRowHeight(rows, 70);
            writer.setColumnWidth(cols, 3);
            CellStyle cellStyle = writer.getOrCreateCell(cols, rows).getCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
        } else {
            Cell cell = writer.getCell(cols, rows);
            text = cell.getStringCellValue() + " " + text;
//            writer.writeCellValue(cols, rows, text);
            writer.setRowHeight(rows, 70);
            writer.setColumnWidth(cols, 3);


            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            writer.getCell(cols, cols);
            cell.setCellValue(new XSSFRichTextString(text));
            cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex2()	);

//            Font font = cellStyle.;
//            font.setFontHeightInPoints((short) 8);
//            cellStyle.setFont(font);

        }

    }
    private static void writeTeacher(ExcelWriter writer, int weekIndex, int courseSeqIndex, int classIndex, String teacher, String course, int type) {
        if ("".equals(teacher)) {
            return;
        }

        int rows = courseSeqIndex + 3;
        int cols = classIndex + (weekIndex * classCodes.length) + 1;

        String text = course + teacher;
        // col, row
        if (type <= 1) {
            writer.writeCellValue(cols, rows, text);

            writer.setRowHeight(rows, 70);
            writer.setColumnWidth(cols, 3);
            CellStyle cellStyle = writer.getOrCreateCell(cols, rows).getCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
        } else {
            Cell cell = writer.getCell(cols, rows);
            text = cell.getStringCellValue() + " " + text;
//            writer.writeCellValue(cols, rows, text);
            writer.setRowHeight(rows, 70);
            writer.setColumnWidth(cols, 3);


            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
            writer.getCell(cols, cols);
            cell.setCellValue(new XSSFRichTextString(text));
            cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex2()	);

//            Font font = cellStyle.;
//            font.setFontHeightInPoints((short) 8);
//            cellStyle.setFont(font);

        }

    }

    private static ExcelWriter getExcelWriter(String file, String sheet) {
//
//        ExcelWriter totalWriter = new ExcelWriter(new File(parPath + totalCourseExcelName), gradeName);
//        ExcelWriter classWriter = new ExcelWriter(new File(parPath + classCourseExcelName));
//        ExcelWriter teacherWriter = new ExcelWriter(new File(parPath + teacherCourseExcelName));
        return null;
    }

    private static String getPath(String fileName) {
        return Excel.class.getClassLoader().getResource(fileName).getPath();
    }

    private static String getParPath(String fileName) {
        String path = Excel.class.getClassLoader().getResource(fileName).getPath();
        return path.substring(0, path.length() - fileName.length());
    }
}
