package com.ruoyi.project.zxsd.sys.util;



import com.ruoyi.project.zxsd.sys.domain.GoodsEntity;
import com.ruoyi.project.zxsd.sys.util.SystemCodeUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
/**
 * Author: Dreamer-1
 * Date: 2019-03-01
 * Time: 10:21
 * Description: 读取Excel内容
 */
public class ExcelReader {

    private static Logger logger = Logger.getLogger(ExcelReader.class.getName()); // 日志打印类

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     * @param inputStream 读取文件的输入流
     * @param fileType 文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }
    /**
     * 读取Excel文件内容
     * @param file 上传的Excel文件
     * @return 读取结果列表，读取失败时返回null
     */
    public static List<GoodsEntity> readExcel(MultipartFile file){
        Workbook workbook = null;
        try {
            // 获取Excel后缀名，校验文件名是否合法
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty() || fileName.lastIndexOf(".") < 0) {
                logger.warning("解析Excel失败，因为获取到的Excel文件名非法！");
                return null;
            }
            //获取文件名后缀
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

            // 获取Excel工作簿对象
            workbook = getWorkbook(file.getInputStream(), fileType);

            // 读取excel中的数据
            List<GoodsEntity> resultDataList = parseExcel(workbook);

            return resultDataList;
        } catch (Exception e) {
            logger.warning("解析Excel失败，文件名：" + file.getOriginalFilename() + " 错误信息：" + e.getMessage());
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
            } catch (Exception e) {
                logger.warning("关闭数据流出错！错误信息：" + e.getMessage());
                return null;
            }
        }
    }


    /**
     * 解析Excel数据
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<GoodsEntity> parseExcel(Workbook workbook){
        List<GoodsEntity> resultDataList = new ArrayList<>();
        // 解析sheet,遍历sheet页
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (null == firstRow) {
                logger.warning("解析Excel失败，在第一行没有读取到任何数据！");
                return null;
            }

            // 解析每一行的数据，构造数据对象，加1是因为第一行是表头，不解析，从第二行开始。
            int rowStart = firstRowNum + 1;
            //当前sheet页有效数据行数
            int rowEnd = sheet.getPhysicalNumberOfRows();
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (null == row) {
                    continue;
                }
                //解析后的数据封装，start,需要自己实现。。。。。
                GoodsEntity resultData = convertRowToData(row);
                //解析后的数据封装，end,需要自己实现。。。。。
                if (null == resultData) {
                    logger.warning("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }
                resultDataList.add(resultData);
            }
        }

        return resultDataList;
    }
    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     *
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static GoodsEntity convertRowToData(Row row) {
        GoodsEntity resultData = new GoodsEntity();

        Cell cell;
        int cellNum = 0;//一个单元格

        //生成sku编码
        resultData.setSkuNo(SystemCodeUtil.getUserCode());


        // 获取SKU名称
        cell = row.getCell(cellNum++);
        String skuName = convertCellValueToString(cell);
        resultData.setSkuName(skuName);

        // 门店编号
        cell = row.getCell(cellNum++);
        String storeNo = convertCellValueToString(cell);
        resultData.setOutsideNo(storeNo);
        // 门店名称
        cell = row.getCell(cellNum++);
        String storeName = convertCellValueToString(cell);
        //resultData.setOutsideNo(storeNo);

        // 获取分类
        cell = row.getCell(cellNum++);
        String goodsCateCode = convertCellValueToString(cell);
        resultData.setCateCode(goodsCateCode);

        // 获取定价售价成本价
        cell = row.getCell(cellNum++);
        String fixPrice = convertCellValueToString(cell);
        resultData.setFixPrice(Double.valueOf(fixPrice));
        cell = row.getCell(cellNum++);
        String costPrice = convertCellValueToString(cell);
        resultData.setCostPrice(Double.valueOf(costPrice));
        cell = row.getCell(cellNum++);
        String sellingPrice = convertCellValueToString(cell);
        resultData.setSellingPrice(Double.valueOf(sellingPrice));

        //获取渠道
        cell = row.getCell(cellNum++);
        String channelType = convertCellValueToString(cell);
        resultData.setChannelType(channelType);

        //获取介绍
        cell = row.getCell(cellNum++);
        String detail = convertCellValueToString(cell);
        resultData.setDetail(detail);

        //状态
        cell = row.getCell(cellNum++);
        String status = convertCellValueToString(cell);
        resultData.setStatus(status);

        //作者
        cell = row.getCell(cellNum++);
        String author = convertCellValueToString(cell);
        resultData.setAuthor(author);

        //广告词
        cell = row.getCell(cellNum++);
        String advertising = convertCellValueToString(cell);
        resultData.setAdvertising(advertising);

        //ISBN
        cell = row.getCell(cellNum++);
        String isbn = convertCellValueToString(cell);
        resultData.setIsbn(isbn);

        //出版社
        cell = row.getCell(cellNum++);
        String press = convertCellValueToString(cell);
        resultData.setPress(press);

        //库存
        cell = row.getCell(cellNum++);
        String amountCnt = convertCellValueToString(cell);
        resultData.setAmountCnt(Integer.parseInt(amountCnt));

        return resultData;
    }
    /**
     * 将单元格内容转换为字符串
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:   //数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //注：format格式 yyyy-MM-dd hh:mm:ss 中小时为12小时制，若要24小时制，则把小h变为H即可，yyyy-MM-dd HH:mm:ss
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    returnValue=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                    break;
                } else {
                    Double doubleValue = cell.getNumericCellValue();
                    // 格式化科学计数法，取一位整数
                    DecimalFormat df = new DecimalFormat("0");
                    returnValue = df.format(doubleValue);
                }
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            default:
                break;
        }
        return returnValue;
    }
}