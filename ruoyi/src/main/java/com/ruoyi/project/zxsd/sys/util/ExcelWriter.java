package com.ruoyi.project.zxsd.sys.util;
import com.ruoyi.project.zxsd.sys.domain.GoodsEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author gkm
 * @version 1.0
 * @date 2020/5/25 10:30
 */
public class ExcelWriter {

    private static List<String> CELL_HEADS; //列头

    static{
        // 类装载时就载入指定好的列头信息，如有需要，可以考虑做成动态生成的列头
        CELL_HEADS = new ArrayList<>();
        CELL_HEADS.add("SKU编号");
        CELL_HEADS.add("SKU名称");
        CELL_HEADS.add("门店名称");
        CELL_HEADS.add("一级分类名称");
        CELL_HEADS.add("二级分类名称");
        CELL_HEADS.add("定价");
        CELL_HEADS.add("成本价");
        CELL_HEADS.add("售价");
        CELL_HEADS.add("渠道");
        CELL_HEADS.add("介绍");
        CELL_HEADS.add("状态");
        CELL_HEADS.add("广告词");
        CELL_HEADS.add("ISBN");
        CELL_HEADS.add("出版社");
        CELL_HEADS.add("库存数量");
        CELL_HEADS.add("销量");
    }

    /**
     * 生成Excel并写入数据信息
     * @param dataList 数据列表
     * @return 写入数据后的工作簿对象
     */
    public static Workbook exportData(List<GoodsEntity> dataList){
        // 生成xlsx的Excel
        Workbook workbook = new SXSSFWorkbook();

        // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
        //Workbook workbook = new HSSFWorkbook();

        // 生成Sheet表，写入第一行的列头
        Sheet sheet = buildDataSheet(workbook);
        //构建每行的数据内容
        int rowNum = 1;
        for (Iterator<GoodsEntity> it = dataList.iterator(); it.hasNext(); ) {
            GoodsEntity data = it.next();
            if (data == null) {
                continue;
            }
            //输出行数据
            Row row = sheet.createRow(rowNum++);
            convertDataToRow(data, row);
        }
        return workbook;
    }

    /**
     * 生成sheet表，并写入第一行数据（列头）
     * @param workbook 工作簿对象
     * @return 已经写入列头的Sheet
     */
    private static Sheet buildDataSheet(Workbook workbook) {
        Sheet sheet = workbook.createSheet();
        // 设置列头宽度
        for (int i=0; i<CELL_HEADS.size(); i++) {
            sheet.setColumnWidth(i, 4000);
        }
        // 设置默认行高
        sheet.setDefaultRowHeight((short) 400);
        // 构建头单元格样式
        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
        // 写入第一行各列的数据
        Row head = sheet.createRow(0);
        for (int i = 0; i < CELL_HEADS.size(); i++) {
            Cell cell = head.createCell(i);
            cell.setCellValue(CELL_HEADS.get(i));
            cell.setCellStyle(cellStyle);
        }
        return sheet;
    }

    /**
     * 设置第一行列头的样式
     * @param workbook 工作簿对象
     * @return 单元格样式对象
     */
    private static CellStyle buildHeadCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.CENTER);
        //边框颜色和宽度设置
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    /**
     * 将数据转换成行
     * @param data 源数据
     * @param row 行对象
     * @return
     */
    private static void convertDataToRow(GoodsEntity data, Row row){
        int cellNum = 0;
        Cell cell;
        // SKU编号
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getSkuNo() ? "" : data.getSkuNo());
        // SKU名称
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getSkuName() ? "" : data.getSkuName());
        // 门店名称
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getStoreName() ? "" : data.getStoreName());
        // 商品一级分类名称
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getCateOneName() ? "" : data.getCateOneName());
        // 商品二级分类名称
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getCateTwoName() ? "" : data.getCateTwoName());
        // 定价
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getFixPrice());
        // 成本价
        cell = row.createCell(cellNum++);
        //cell.setCellType(CellType.NUMERIC);
        cell.setCellValue(data.getCostPrice());
        // 售价
        cell = row.createCell(cellNum++);
        //cell.setCellType(CellType.NUMERIC);
        cell.setCellValue(data.getSellingPrice());

        // 渠道
        cell = row.createCell(cellNum++);
        if("0".equals(data.getChannelType())){
            cell.setCellValue("行走书店");
        }else{
            cell.setCellValue("京东万家");
        }

        // 介绍
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getDetail() ? "" : data.getDetail());

        // 状态
        cell = row.createCell(cellNum++);
        if("0".equals(data.getStatus())){
            cell.setCellValue("下架");
        }else{
            cell.setCellValue("上架");
        }
        // 广告词
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getAdvertising() ? "" : data.getAdvertising());
        // ISBN
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getIsbn() ? "" : data.getIsbn());
        // 出版社
        cell = row.createCell(cellNum++);
        cell.setCellValue(null == data.getPress() ? "" : data.getPress());
        // 库存
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getAmountCnt());
        // 销量
        cell = row.createCell(cellNum++);
        cell.setCellValue(data.getSaleCnt());
    }
}