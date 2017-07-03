package com.tocersoft.base.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	private static Log logger = LogFactory.getLog(ExcelUtil.class);
	
	/**
	 * 创建Excel行头
	 * @param titleList
	 * @param sheet
	 * @param headRowNumber
	 * @return
	 */
	public static HSSFRow createHSSFRow(List<String> titleList,
			List<Integer> titleWidthList,
			HSSFWorkbook wb,
			HSSFSheet sheet,
			Integer headRowNumber) {
        // 列头
		HSSFRow row = sheet.createRow(headRowNumber);
		if (titleList != null && titleList.size() > 0 ){
			for (int i = 0; i < titleList.size(); i++) {
				sheet.setColumnWidth(i, titleWidthList.get(i) * 256);
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(titleList.get(i));

				HSSFCellStyle cellStyle = wb.createCellStyle();
				// 指定单元格居中对齐
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				// 指定单元格垂直居中对齐
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				// 指定单元格自动换行
				cellStyle.setWrapText(true);

				// 设置单元格字体
				HSSFFont font = wb.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				cellStyle.setFont(font);

				// 设置单元格格式
				cell.setCellStyle(cellStyle);
			}
		}
		return row;
	}
	/**
	 * 创建明细单元格
	 * @param wb
	 * @param row
	 * @param cellNum
	 * @return
	 */
	public static HSSFCell createCell(HSSFCellStyle cellStyle,HSSFRow row,int cellNum){
		HSSFCell cell = row.createCell(cellNum);
		cell.setCellStyle(cellStyle);
		return cell;
	}
	public static String exportAjaxExcelData(String excelFileName,
			HSSFWorkbook wb) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String excelFilePath="";
		//输出文件
		String tmpZipDirectoryName = UUIDUtil.uuid().toLowerCase();
		String tmpZipDirectoryPath = System.getProperty("java.io.tmpdir") + File.separator + tmpZipDirectoryName + File.separator;
		File tmpZipDirectory = new File(tmpZipDirectoryPath);
		if (!tmpZipDirectory.exists()) {
			tmpZipDirectory.mkdirs();
		}
		try{
			excelFilePath = tmpZipDirectoryPath + tmpZipDirectoryName + ".xls";
			fos = new FileOutputStream(excelFilePath);
			wb.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(fos);
		}
		return excelFilePath;
	}
	public static HSSFCellStyle createHSSFCellStyle(HSSFWorkbook wb){
		HSSFCellStyle cellStyles = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyles.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyles.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定单元格自动换行
		cellStyles.setWrapText(true);
		//设置边框
		cellStyles.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyles.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyles.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyles.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellStyles.setFont(font);
		
		return cellStyles;
	}
	
	/**
	 * 创建Excel行头
	 * @param titles
	 * @param sheet
	 * @param headRowNumber
	 * @return
	 */
	public static HSSFRow createHSSFRow(String[] titles,
			Integer[] titleWidths,
			HSSFWorkbook wb,
			HSSFSheet sheet,
			Integer headRowNumber) {
        // 列头
		HSSFRow row = sheet.createRow(headRowNumber);
		if (titles != null && titles.length > 0 ){
			for (int i = 0; i < titles.length; i++) {
				sheet.setColumnWidth(i, titleWidths[i] * 256);
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(titles[i]);
				
				HSSFCellStyle cellStyle = wb.createCellStyle();
				// 指定单元格居中对齐
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				// 指定单元格垂直居中对齐
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				// 指定单元格自动换行
				cellStyle.setWrapText(true);

				// 设置单元格字体
				HSSFFont font = wb.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				cellStyle.setFont(font);

				// 设置单元格格式
				cell.setCellStyle(cellStyle);
			}
		}
		return row;
	}
	
	public static void exportExcelData(String excelFileName,
			HttpServletResponse response,
			HSSFWorkbook wb) {
		String fileName = new Date().getTime() + "";
		//获取文件名
		fileName = excelFileName + CommonUtil.formatDate(new Date(), "yyyyMMdd") + ".xls";
		OutputStream os = null;
		try {
			// 清空输出流
			response.reset();
			// 取得输出流
			os = response.getOutputStream(); 
			// 设定输出文件头
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
			// 定义输出类型
			response.setContentType("application/msexcel");
		} catch (IOException ex) {
			// 捕捉异常   
			ex.printStackTrace();
			logger.error("导出" + excelFileName + "发生异常:" + ex.getMessage());
		}
		try {
			os.flush();
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("导出" + excelFileName + "发生异常:" + e.getMessage());
		}
	}

}
