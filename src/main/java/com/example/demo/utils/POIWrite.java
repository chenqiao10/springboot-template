package com.example.demo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIWrite {

	public HSSFWorkbook writeExcal(String fileName, List<Object> list, String[] fileHead) throws Exception {
		// 创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(fileName);
		// 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		// HSSFRow row = sheet.createRow(0);
		// 创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		// 获取表头属性，传入集合内对象相同时使用，下方创建行从i+1开始
		try {
			// Class<?> objs =
			// Class.forName(list.get(0).getClass().toString().substring(6));//反射获取类对象
			// Field[] field = objs.getDeclaredFields(); //类对象属性数组
			HSSFRow row1 = sheet.createRow(0); // 创建行,从0开始
			HSSFCell cell1 = row1.createCell(0); // 创建行的单元格,也是从0开始
			for (int a = 0; a < fileHead.length; a++) {
				row1.createCell(a).setCellValue(fileHead[a]);// 设置单元格内容,重载
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 遍历集合
		for (int i = 0; i < list.size(); i++) {
			try {
				Class<?> obj = Class.forName(list.get(i).getClass().toString().substring(6)); // 反射获取类对象
				Field[] fields = obj.getDeclaredFields(); // 类对象属性数组
				HSSFRow row = sheet.createRow(i + 1); // 创建行,从i+1（第0行为表头）开始
				// 循环遍历单个对象里的属性
				for (int c = 0; c < fields.length; c++) {
					String allname = fields[c].getName(); // 获取属性名
					String first = allname.substring(0, 1).toUpperCase();
					String after = allname.substring(1);
					String name = "get" + first + after; // 拼接get方法名
					Method m = (Method) list.get(i).getClass().getDeclaredMethod(name); // 方法转换
					row.createCell(c).setCellValue(m.invoke(list.get(i)).toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wb;
	}

}
