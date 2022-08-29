package com.example.demo.util;


import com.example.demo.exception.EarthquakeException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.hssf.usermodel.HSSFDataValidationHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POIUtils {
    // 扩展名
    public final static String XLS = "xls";
    public final static String XLSX = "xlsx";


    /**
     * * 读取excel文件
     *
     * @param excelFile excel文件
     * @param startRow  读取数据的起始行, 行号从0开始
     * @return
     * @throws IOException
     */
    public static List<String[]> readExcelFile(MultipartFile excelFile, int startRow, int lastCellNum) throws IOException, EarthquakeException {
        // 检查文件
        checkFile(excelFile);
        // 获得工作簿对象
        Workbook workbook = getWorkBook(excelFile);

        // 创建返回对象，把每行中的值作为一个数组，所有的行作为一个集合返回
        List<String[]> list = new ArrayList<>();

        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                // 获取当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                int lastRowNum = sheet.getLastRowNum();

                // 对比表头数据列数 检查模板
                Row headRow = sheet.getRow(0);
                if(headRow.getPhysicalNumberOfCells() != lastCellNum){
                    throw new EarthquakeException(-1, "数据列数不一致，请检查模板");
                }

                // 循环除了第一行之外的所有行
                for (int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {
                    // 获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    // 获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();

                    String[] cells = new String[lastCellNum];
                    // 循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
        }
        return list;
    }

    /**
     * * 读取excel文件
     *
     * @param sheet sheet页内容
     * @param startRow  读取数据的起始行, 行号从0开始
     * @return
     * @throws IOException
     */
    public static List<String[]> readExcelFile(Sheet sheet, int startRow, int lastCellNum) throws IOException, EarthquakeException {

        // 创建返回对象，把每行中的值作为一个数组，所有的行作为一个集合返回
        List<String[]> list = new ArrayList<>();

                int lastRowNum = sheet.getLastRowNum();

                // 对比表头数据列数 检查模板
                Row headRow = sheet.getRow(0);
                if(headRow.getPhysicalNumberOfCells() != lastCellNum){
                    throw new EarthquakeException(-1, "数据列数不一致，请检查模板");
                }

                // 循环除了第一行之外的所有行
                for (int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {
                    // 获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    // 获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();

                    String[] cells = new String[lastCellNum];
                    // 循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }

        return list;
    }

    /**
     * 生成excel文件
     *
     * @param sheetName  生成sheet的名称
     * @param attributes 表头名称
     * @param dataList   数据
     * @return
     */
    public static Workbook createExcelFile(String sheetName, List<String> attributes, List<List<String>> dataList) {
        // 1. 创建workbook
        Workbook workbook = null;

        workbook = new XSSFWorkbook();

        //存储最大列宽
        Map<Integer, Integer> maxWidth = new HashMap<Integer, Integer>();

        if (workbook != null) {
            // 2. 创建sheet
            Sheet sheet = workbook.createSheet(sheetName);

            // 3. 创建row: 添加属性行
            Row row0 = sheet.createRow(0);
            for (int i = 0; i < attributes.size(); i++) {
                // 设置表头文字
                Cell cell = row0.createCell(i);
                cell.setCellValue(attributes.get(i).trim());
                // 设置表头样式
                Font font = workbook.createFont();

                CellStyle cellStyle = workbook.createCellStyle();
                font.setFontName("微软雅黑");//设置字体名称
                font.setFontHeightInPoints((short) 16);//设置字号
                cellStyle.setFont(font);
                cell.setCellStyle(cellStyle);

                maxWidth.put(i, cell.getStringCellValue().getBytes().length * 700 + 700);
            }
            // 4. 插入数据
            if (CollectionUtils.isNotEmpty(dataList)) {
                for (int i = 0; i < dataList.size(); i++) {
                    List<String> rowInfo = dataList.get(i);
                    Row row = sheet.createRow(i + 1);
                    // 添加数据
                    for (int j = 0; j < rowInfo.size(); j++) {
                        Cell createCell = row.createCell(j);
                        String value = rowInfo.get(j);
                        if ("null".equals(value)) {
                            value = "";
                        }
                        createCell.setCellValue(value);

                        int length = createCell.getStringCellValue().getBytes().length * 300 + 300;
                        //这里把宽度最大限制到15000
                        if (length > 15000) {
                            length = 15000;
                        }
                        maxWidth.put(j, Math.max(length, maxWidth.get(j)));
                    }
                }
            }
            for (int i = 0; i < attributes.size(); i++) {
                sheet.setColumnWidth(i, maxWidth.get(i));
            }
        }
        return workbook;
    }


    public static Workbook createExcelFileWithDataValidations(String sheetName, List<String> attributes, HashMap<Integer, String> dictionaryValidations, List<Integer> dateValidations) {
        // 1. 创建workbook
        HSSFWorkbook workbook = null;

        workbook = new HSSFWorkbook();

        //存储最大列宽
        Map<Integer, Integer> maxWidth = new HashMap<Integer, Integer>();

        if (workbook != null) {
            // 2. 创建sheet
            HSSFSheet sheet = workbook.createSheet(sheetName);
            // 设置单元格数据验证 字典项
            dictionaryValidation(sheet, dictionaryValidations);
            // 设置单元格日期验证
            if(null != dateValidations){
                dateValidation(sheet, dateValidations);
            }
            // 3. 创建row: 添加属性行
            Row row0 = sheet.createRow(0);
            for (int i = 0; i < attributes.size(); i++) {
                // 设置表头文字
                Cell cell = row0.createCell(i);
                cell.setCellValue(attributes.get(i).trim());
                // 设置表头样式
                Font font = workbook.createFont();

                CellStyle cellStyle = workbook.createCellStyle();
                font.setFontName("微软雅黑");//设置字体名称
                font.setFontHeightInPoints((short) 16);//设置字号
                cellStyle.setFont(font);
                cell.setCellStyle(cellStyle);

                maxWidth.put(i, cell.getStringCellValue().getBytes().length * 700 + 700);
            }
        }
        return workbook;
    }
    public static Workbook createExcelFile(Map<String,List<String>> sheetMap, Map<String,List<List<String>>> dataListMap) {
        // 1. 创建workbook
        Workbook workbook = null;

        workbook = new XSSFWorkbook();

        //存储最大列宽
        Map<Integer, Integer> maxWidth = new HashMap<Integer, Integer>();
        if (workbook != null) {
            for (String sheetName : sheetMap.keySet()) {
                List<String> attributes = sheetMap.get(sheetName);
                // 2. 创建sheet
                Sheet sheet = workbook.createSheet(sheetName);

                // 3. 创建row: 添加属性行
                Row row0 = sheet.createRow(0);
                for (int i = 0; i < attributes.size(); i++) {
                    // 设置表头文字
                    Cell cell = row0.createCell(i);
                    cell.setCellValue(attributes.get(i).trim());
                    // 设置表头样式
                    Font font = workbook.createFont();

                    CellStyle cellStyle = workbook.createCellStyle();
                    font.setFontName("微软雅黑");//设置字体名称
                    font.setFontHeightInPoints((short) 16);//设置字号
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);

                    maxWidth.put(i, cell.getStringCellValue().getBytes().length * 700 + 700);
                }

                List<List<String>> dataList = dataListMap.get(sheetName);
                // 4. 插入数据
                if (CollectionUtils.isNotEmpty(dataList)) {
                    for (int i = 0; i < dataList.size(); i++) {
                        List<String> rowInfo = dataList.get(i);
                        Row row = sheet.createRow(i + 1);
                        // 添加数据
                        for (int j = 0; j < rowInfo.size(); j++) {
                            Cell createCell = row.createCell(j);
                            String value = rowInfo.get(j);
                            if ("null".equals(value)) {
                                value = "";
                            }
                            createCell.setCellValue(value);

                            int length = createCell.getStringCellValue().getBytes().length * 300 + 300;
                            //这里把宽度最大限制到15000
                            if (length > 15000) {
                                length = 15000;
                            }
                            maxWidth.put(j, Math.max(length, maxWidth.get(j)));
                        }
                    }
                }
                for (int i = 0; i < attributes.size(); i++) {
                    sheet.setColumnWidth(i, maxWidth.get(i));
                }
            }
        }
        return workbook;
    }
    public static Workbook createExcelFileWithDataValidations(Map<String,List<String>> sheetMap, HashMap<String, HashMap> dataValidations, List<Integer> dateValidations) {
        // 1. 创建workbook
        HSSFWorkbook workbook = null;

        workbook = new HSSFWorkbook();

        //存储最大列宽
        Map<Integer, Integer> maxWidth = new HashMap<Integer, Integer>();

        if (workbook != null) {
            for (String sheetName : sheetMap.keySet()) {
                List<String> attributes = sheetMap.get(sheetName);
                // 2. 创建sheet
                HSSFSheet sheet = workbook.createSheet(sheetName);
                    // 设置单元格数据验证 字典项
                    dictionaryValidation(sheet, dataValidations.get(sheetName));
                // 设置单元格日期验证
                if(null != dateValidations){
                    dateValidation(sheet, dateValidations);
                }
                // 3. 创建row: 添加属性行
                Row row0 = sheet.createRow(0);
                for (int i = 0; i < attributes.size(); i++) {
                    // 设置表头文字
                    Cell cell = row0.createCell(i);
                    cell.setCellValue(attributes.get(i).trim());
                    // 设置表头样式
                    Font font = workbook.createFont();

                    CellStyle cellStyle = workbook.createCellStyle();
                    font.setFontName("微软雅黑");//设置字体名称
                    font.setFontHeightInPoints((short) 16);//设置字号
                    cellStyle.setFont(font);
                    cell.setCellStyle(cellStyle);

                    maxWidth.put(i, cell.getStringCellValue().getBytes().length * 700 + 700);
                }
            }
        }
        return workbook;
    }

    // 设置单元格下拉验证数据
    private static void dictionaryValidation(HSSFSheet sheet, HashMap<Integer, String> dataValidations) {
        for (Integer colNum : dataValidations.keySet()) {
            String dataValidation = MapUtils.getString(dataValidations, colNum);
            String[] datas = dataValidation.split(",");
            HSSFDataValidationHelper dvHelper = new HSSFDataValidationHelper(sheet);
            DataValidationConstraint explicitListConstraint = dvHelper.createExplicitListConstraint(datas);
            // 验证单元格范围 firstRow  endRow  0~1000行    firstCol  endCol 第 firstCol列~endCol列 设置规则
            CellRangeAddressList addressList = new CellRangeAddressList(0, 1000, colNum, colNum);
            DataValidation validation = dvHelper.createValidation(explicitListConstraint, addressList);
            sheet.addValidationData(validation);
        }
    }

    /**
     * 生成的日期校验规则
     * @param sheet
     * @param dataValidations
     */
    private static void dateValidation(HSSFSheet sheet, List<Integer> dataValidations) {
        for (Integer colNum : dataValidations) {
            //创建数据验证类
            DataValidationHelper helper = sheet.getDataValidationHelper();
            //设置验证生效的范围 四个参数 开始行,结束行,开始列,结束列
            CellRangeAddressList addressList = new CellRangeAddressList(1, 1000, colNum, colNum);
            //设置验证方式 四个参数,第一个参数常量,第二,第三是时间范
            //围 字符串格式要写成Date(2100, 1, 1)这种,第四时间格式
            DataValidationConstraint constraint = helper.createDateConstraint(DataValidationConstraint.OperatorType.BETWEEN,
                    "1900-1-1", "2100-1-1", "yyyy-MM-dd");
            //创建验证对象
            DataValidation dataValidation = helper.createValidation(constraint, addressList);
            //错误提示信息
            dataValidation.createErrorBox("提示", "请输入[yyyy-MM-dd]格式日期,范围1900-1-1,2100-1-1");
            dataValidation.setShowErrorBox(true);
            //验证和工作簿绑定
            sheet.addValidationData(dataValidation);
        }
    }

    /**
     * 获取当前列数据
     *
     * @param cell 列
     * @return 列值
     */
    private static String getCellValue(Cell cell) {
        String cellValue = "";

        if (cell == null) {
            return cellValue;
        }

        // 把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == CellType.NUMERIC) {
            cell.setCellType(CellType.STRING);
        }
        // 判断数据的类型
        switch (cell.getCellType()) {
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellValue = "";
                break;
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


    /**
     * 获得工作簿对象
     *
     * @param excelFile excel文件
     * @return 工作簿对象
     */
    public static Workbook getWorkBook(MultipartFile excelFile) {
        // 获得文件名
        String fileName = excelFile.getOriginalFilename();
        // 创建Workbook工作簿对象，表示整个excel
        Workbook workbook = null;
        try {
            // 获得excel文件的io流
            InputStream is = excelFile.getInputStream();
            // 根据文件后缀名不同(xls和xlsx)获得不同的workbook实现类对象
            if (fileName.endsWith(XLS)) {
                // 2003版本
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(XLSX)) {
                // 2007版本
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


    /**
     * 检查文件
     *
     * @param excelFile excel文件
     * @throws IOException
     */
    public static void checkFile(MultipartFile excelFile) throws IOException {
        //判断文件是否存在
        if (null == excelFile) {
            throw new FileNotFoundException("文件不存在");
        }
        //获得文件名
        String fileName = excelFile.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            throw new IOException(fileName + "不是excel文件");
        }
    }

}

