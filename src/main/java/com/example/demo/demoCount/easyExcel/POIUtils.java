package com.example.demo.demoCount.easyExcel;



import com.example.demo.exception.EarthquakeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class POIUtils {
    // 扩展名
    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    public static Map<String, Object> readExcelFile(MultipartFile excelFile, String entityName) throws EarthquakeException {
        // 检查文件
        checkFile(excelFile);
        // 获得工作簿对象
        Workbook workbook = getWorkBook(excelFile);
        // 创建返回对象，把每行中的值作为一个数组，所有的行作为一个集合返回
        return getWorkBookData(workbook, entityName);
    }

    public static void checkFile(MultipartFile excelFile) throws EarthquakeException {
        //判断文件是否存在
        if (null == excelFile) {
            throw new EarthquakeException(-1, "文件不存在");
        }
        //获得文件名
        String fileName = excelFile.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            throw new EarthquakeException(-1, fileName + "不是excel文件");
        }
    }

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

    private static Map<String, Object> getWorkBookData(Workbook workbook, String entityName) throws EarthquakeException{

        // 创建返回对象，把每行中的值作为一个数组，所有的行作为一个集合返回
        List<Object> list = new ArrayList<>();
        List<String> errorList = new ArrayList<>();

        if (workbook != null) {
            // 获取当前sheet工作表
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                throw new EarthquakeException(-1, "当前文件表格为空");
            }
            int lastRowNum = sheet.getLastRowNum();

            List<String> headerList = new ArrayList<>();
            Map<String, Field> fieldMap = new HashMap<>();

            // 对比表头数据列数 检查模板
            Row headRow = sheet.getRow(0);
            short headrowLastCellNum = headRow.getLastCellNum();
            // 表头行
            for (int cellNum = 0; cellNum < headrowLastCellNum; cellNum++) {
                Cell cell = headRow.getCell(cellNum);
                String header = cell.getStringCellValue().trim();
                headerList.add(header);
            }
            // 获取类注解属性
            Class entityClass = null;
            try {
                entityClass = Class.forName(entityName);
            } catch (ClassNotFoundException e) {
                throw new EarthquakeException(-1, String.format("需要转换的类型 %s 不存在", entityName));
            }
            // 获取所有字段,public和protected和private,但是不包括父类字段
            Field[] allEntityFields = entityClass.getDeclaredFields();
            for (Field field : allEntityFields) {
                // 加了自定义注解的 作为excel对比字段
                if (field.isAnnotationPresent(ExcelContentProperty.class)) {
                    ExcelContentProperty propertyAnnotation = field.getAnnotation(ExcelContentProperty.class);
                    String tableHeader = propertyAnnotation.tableHeader();
                    if (headerList.contains(tableHeader)) {
                        fieldMap.put(tableHeader, field);
                    }
                }
            }
            // 根据上传的excel头 获取对应属性 并且进行校验
            // 循环数据行
            for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
                // 获得当前行
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                // 获得当前行的序号
                int rowIndex = rowNum + 1;
                // 获取当前entity实例 用于invok字段属性赋值
                // 但如果其他行有错误的 就不需要实例对象
                Object entity = null;
                if (CollectionUtils.isEmpty(errorList)) {
                    try {
                        entity = entityClass.newInstance();
                    } catch (Exception e) {
                        throw new EarthquakeException(-1, "获取实例失败");
                    }
                }

                // 循环当前行的数据cell
                for (int cellNum = 0; cellNum < headerList.size(); cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    String currentHeader = headerList.get(cellNum);
                    Field currentField = (Field) MapUtils.getObject(fieldMap, currentHeader);
                    // 读取单元格 返回map 包含两个key
                    //  errorMsg : 校验返回的报错信息
                    //  value : 无报错  返回属性值
                    if (null != currentField) {
                        Map<String, Object> cellValue = getCellValue(cell, currentField, currentHeader);
                        // 返回报错信息 加入errorList 直接返回
                        if (cell!=null&&!"".equals(cell.toString())&& StringUtils.isNotEmpty(MapUtils.getString(cellValue, "errorMsg"))) {
                            String errorMsg = String.format("序号 %s ,错误内容： %s", rowIndex, MapUtils.getString(cellValue, "errorMsg"));
                            errorList.add(errorMsg);
                            continue;
                        }
                        // 没有错误信息 看之前是否有报错
                        // 无报错 初始化entity
                        if (CollectionUtils.isEmpty(errorList) && null != entity) {
                            // 执行invok 初始化当前字段属性
                            setFieldValue(entity, currentField.getName(), MapUtils.getObject(cellValue, "value"));
                        }
                    }
                }
                if (CollectionUtils.isEmpty(errorList) && null != entity) {
                    list.add(entity);
                }
                System.out.println("当前进度" + rowIndex);
            }
        }

        Map<String, Object> returnMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(errorList)) {
            list = null;
        }
        returnMap.put("data", list);
        returnMap.put("errors", errorList);
        return returnMap;
    }

    private static Map<String, Object> getCellValue(Cell cell, Field currentField, String currentHeader) {

        Map<String, Object> cellMap = new HashMap<>();
        StringBuilder errorBuffer = new StringBuilder();

        ExcelContentProperty propertyAnnotation = currentField.getAnnotation(ExcelContentProperty.class);
        boolean required = propertyAnnotation.required();
        Type genericType = currentField.getGenericType();

        // 1.格式转换校验
        // cell中有内容进行格式校验
        cell.setCellType(CellType.STRING);
        Object value = null;
        if (null != cell) {
            // todo 经度 纬度转换
            switch (genericType.getTypeName()) {
                case "java.lang.String":
                    try {
                        value = cell.getStringCellValue().trim();
                    } catch (Exception e) {
                        log.error("当前cell内容为长数字串");
                        // 如果是编号类的
                        BigDecimal bigDecimal = BigDecimal.valueOf(cell.getNumericCellValue());
                        value = bigDecimal.toPlainString();
                    }
                    break;
                case "java.lang.Integer":
                    try {
                        // double直接拆箱获取integer值
                        Double numericCellValue = cell.getNumericCellValue();
                        value = numericCellValue.intValue();
                    } catch (NumberFormatException e) {
                        errorBuffer.append("属性:").append(currentHeader).append("无法转换成 数字整型");
                    }catch (IllegalStateException e){
                        errorBuffer.append("属性:").append(currentHeader).append("无法转换成 数字浮点型，请勿将数字格式转化为文本格式");
                    }
                    break;
                case "java.lang.Double":
                    try {
                        value = Double.valueOf(cell.getStringCellValue());
                    } catch (NumberFormatException e) {
                        errorBuffer.append("属性:").append(currentHeader).append("无法转换成 数字浮点型");
                    } catch (IllegalStateException e){
                        errorBuffer.append("属性:").append(currentHeader).append("无法转换成 数字浮点型，请勿将数字格式转化为文本格式");
                    }
                    break;
                case "java.util.Date":
                    try {
                        if (cell.getCellType() == CellType.STRING) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            value = simpleDateFormat.parse(cell.getStringCellValue().trim());
                        }
                        if (cell.getCellType() == CellType.NUMERIC) {
                            value = DateUtil.getJavaDate(cell.getNumericCellValue());
                        }
                    } catch (ParseException e) {
                        errorBuffer.append("属性:").append(currentHeader).append("无法转换成 日期型 ");
                    }
                    break;
                case "java.lang.Boolean":
                    try {
                        value = "是".equals(cell.getStringCellValue().trim());
                    } catch (Exception e) {
                        errorBuffer.append("属性:").append(currentHeader).append("无法转换成 布尔型 ");
                    }
                    break;
                default:
                    break;
            }

            if (errorBuffer.length() > 0) {
                cellMap.put("errorMsg", errorBuffer.toString());
                return cellMap;
            }
        }
        // 2.校验必填
        if (required && (null == value || StringUtils.isEmpty(value.toString()))) {
            errorBuffer.append("属性:").append(currentHeader).append("为必填项，请填写");
            cellMap.put("errorMsg", errorBuffer.toString());
            return cellMap;
        }

        // 无异常 返回cell的值
        cellMap.put("value", value);
        return cellMap;
    }

    private static void setFieldValue(Object entity, String fieldName, Object value) throws EarthquakeException {
        Field nameField = null;
        try {
            nameField = entity.getClass().getDeclaredField(fieldName);
            nameField.setAccessible(true);
            nameField.set(entity, value);
        } catch (Exception e) {
            throw new EarthquakeException(-1, "set field value fail");
        }
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
//                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                cellStyle.setFillBackgroundColor(IndexedColors.BLUE1.getIndex());
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

    public static Workbook template(Object object, String serviceName) throws EarthquakeException{
        List<Object> data = new ArrayList<>();
        data.add(object);
        return export(data,serviceName);
    }

    public static Workbook export(Object object, String serviceName) throws EarthquakeException{
        List<Object> data = new ArrayList<>();
        data.add(object);
        return export(data,serviceName);
    }

    public static Workbook export(List data, String serviceName) throws EarthquakeException{
        try {
            SimpleDateFormat sdf = null;
            List<List<String>> dataList = new ArrayList<>();
            List<String> attributes = null;
            for (Object datum : data) {
                List<String> data1 = new ArrayList<>();
                List<String> headerList = new ArrayList<>();
                // 获取类注解属性
                Class entityClass = null;
                entityClass = datum.getClass();
                // 获取所有字段,public和protected和private,但是不包括父类字段
                Field[] allEntityFields = entityClass.getDeclaredFields();
                for (Field field : allEntityFields) {
                    // 加了自定义注解的 作为excel对比字段
                    if (field.isAnnotationPresent(ExcelContentProperty.class)) {
                        ExcelContentProperty propertyAnnotation = field.getAnnotation(ExcelContentProperty.class);
                        int index = propertyAnnotation.index();
                        String header = propertyAnnotation.tableHeader();
                        String format = propertyAnnotation.format();
                        field.setAccessible(true);
                        String value = String.valueOf(field.get(datum));
                        if(field.getType() == Date.class) {
                            sdf = new SimpleDateFormat(format);//设置日期格式
                            Date obj = (Date) field.get(datum);
                            value = sdf.format(obj);
                        }
                        data1.add(index,value);
                        if(attributes==null){
                            headerList.add(index,header);
                        }
                    }
                }
                if(attributes==null){
                    attributes = headerList;
                }
                dataList.add(data1);
            }
            return createExcelFile(serviceName, attributes, dataList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new EarthquakeException(-1,e.getMessage());
        }
    }


}

