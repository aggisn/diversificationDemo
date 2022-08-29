package com.example.demo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;

/**
 * @author ZLJ
 * @description
 * @date 2022/6/6
 */
public class ExcelTest {


    private static Map<String, List<String>> map = new HashMap<>();

    private static  Map<String, List<List<List<String>>>> maps = new HashMap<>();

    private static String[] all = {
            "钻孔综合编号", "钻孔编号", "Y18场地类别",
            "钻孔所在省", "钻孔所在市", "钻孔所在县",
            "钻孔所在乡", "项目负责人", "钻孔勘察单位",
            "项目完成时间", "勘察施工日期", "勘察报告日期",
            "钻孔依托的项目", "孔口记录坐标X轴", "孔口记录坐标Y轴",
            "孔位经度（°）", "孔位纬度（°）", "钻孔深度（m）",
            "地面高程（m）", "稳定水位深度（m）", "坐标系说明",
            "高程系说明", "钻孔勘察负责人", "钻孔说明备注", "项目完成单位",
            "Y04地层岩性分层", "Y05标贯值", "Y06剪切波实测值", "Y08土动力试验参数"
    };

    private static String[] BoreholeInformation = {
            "钻孔综合编号", "钻孔编号", "Y18场地类别",
            "钻孔所在省", "钻孔所在市", "钻孔所在县",
            "钻孔所在乡", "项目负责人", "钻孔勘察单位",
            "项目完成时间", "勘察施工日期", "勘察报告日期",
            "钻孔依托的项目", "孔口记录坐标X轴", "孔口记录坐标Y轴",
            "孔位经度（°）", "孔位纬度（°）", "钻孔深度（m）",
            "地面高程（m）", "稳定水位深度（m）", "坐标系说明",
            "高程系说明", "钻孔勘察负责人", "钻孔说明备注", "项目完成单位"
    };

    private static String[] StrataRockInformation = {
            "钻孔综合编号", "地层序号", "地层编号", "地层年代",
            "时代成因", "层底深度（m）", "分层厚度（m）",
            "岩土特征"
    };

    private static String[] StandardPenetrationValueInformation = {
            "钻孔综合编号", "起始标贯深度(m)", "结束标贯深度(m)", "标贯值"
    };

    private static String[] ShearWaveInformation = {
            "钻孔综合编号", "岩土性名称", "层底深度 (m)", "层厚(m)",
            "剪切波波速V(m/s)"
    };

    private static String[] SoilDynamicsInformation = {
            "钻孔综合编号", "样品编号", "取样深度顶点(m)", "取样深度底点(m)",
            "土层名称", "密度(kg/m3)", "模量比(G/Gmax)剪应变(γ)（10-4×0.05)",
            "模量比(G/Gmax)剪应变(γ)（10-4×0.10)", "模量比(G/Gmax)剪应变(γ)（10-4×0.50)", "模量比(G/Gmax)剪应变(γ)（10-4×1.00)",
            "模量比(G/Gmax)剪应变(γ)（10-4×5.00)", "模量比(G/Gmax)剪应变(γ)（10-4×10.00)", "模量比(G/Gmax)剪应变(γ)（10-4×50.00)",
            "模量比(G/Gmax)剪应变(γ)（10-4×100.00)", "阻尼比(λ)剪应变(γ)（10-4×0.05)", "阻尼比(λ)剪应变(γ)（10-4×0.10)",
            "阻尼比(λ)剪应变(γ)（10-4×0.50)", "阻尼比(λ)剪应变(γ)（10-4×1.00)", "阻尼比(λ)剪应变(γ)（10-4×5.00)",
            "阻尼比(λ)剪应变(γ)（10-4×10.00)", "阻尼比(λ)剪应变(γ)（10-4×50.00)", "阻尼比(λ)剪应变(γ)（10-4×100.00)"
    };

    static {
        map.put("Y02成孔信息", Arrays.asList(BoreholeInformation));
        map.put("Y04地层岩性分层", Arrays.asList(StrataRockInformation));
        map.put("Y05标贯值", Arrays.asList(StandardPenetrationValueInformation));
        map.put("Y06剪切波实测值", Arrays.asList(ShearWaveInformation));
        map.put("Y08土动力试验参数", Arrays.asList(SoilDynamicsInformation));




    }




    public static Workbook createExcelFiles(Map<String, List<String>> sheetMap, Map<String,List<List<List<String>>>> dataListMap) {
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

                List<List<List<String>>> dataList = dataListMap.get(sheetName);

                // 4. 插入数据
                if (CollectionUtils.isNotEmpty(dataList)) {
                    for (List<List<String>> lists : dataList) {

                        test(lists,sheet,maxWidth);

                    }
                }

                for (int i = 0; i < attributes.size(); i++) {
                    sheet.setColumnWidth(i, maxWidth.get(i));
                }
            }
        }
        return workbook;
    }


    private static void test(List<List<String>> lists, Sheet sheet, Map<Integer, Integer> maxWidth) {
        for (int i = 0; i < lists.size(); i++) {

            List<String> rowInfo = lists.get(i);
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



    public static void main(String[] args) {

        List<String> objects = new ArrayList<>();



//        maps.put("Y02成孔信息", boreHoleLists);
//        maps.put("Y04地层岩性分层", strataRockInformationDatas);
//        maps.put("Y05标贯值", standardPenetrationValueInformationDatas);
//        maps.put("Y06剪切波实测值", shearWaveInformationDatas);
//        maps.put("Y08土动力试验参数", soilDynamicsInformationDatas);

         createExcelFiles(map, maps);

    }
}
