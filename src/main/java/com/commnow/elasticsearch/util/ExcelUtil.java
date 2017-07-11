package com.commnow.elasticsearch.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



public class ExcelUtil {
	public static String trimFormat(String str){
		if(null == str){
			return "";
		}
		return str.trim();
	}
	
	 public void createExcel(OutputStream os) throws WriteException,IOException{
	        //创建工作薄
	        WritableWorkbook workbook = Workbook.createWorkbook(os);
	        //创建新的一页
	        WritableSheet sheet = workbook.createSheet("First Sheet",0);
	        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
	        Label xuexiao = new Label(0,0,"学校");
	        sheet.addCell(xuexiao);
	        Label zhuanye = new Label(1,0,"专业");
	        sheet.addCell(zhuanye);
	        Label jingzhengli = new Label(2,0,"专业竞争力");
	        sheet.addCell(jingzhengli);
	        
	        Label qinghua = new Label(0,1,"清华大学");
	        sheet.addCell(qinghua);
	        Label jisuanji = new Label(1,1,"计算机专业");
	        sheet.addCell(jisuanji);
	        Label gao = new Label(2,1,"高");
	        sheet.addCell(gao);
	        
	        Label beida = new Label(0,2,"北京大学");
	        sheet.addCell(beida);
	        Label falv = new Label(1,2,"法律专业");
	        sheet.addCell(falv);
	        Label zhong = new Label(2,2,"中");
	        sheet.addCell(zhong);
	        
	        Label ligong = new Label(0,3,"北京理工大学");
	        sheet.addCell(ligong);
	        Label hangkong = new Label(1,3,"航空专业");
	        sheet.addCell(hangkong);
	        Label di = new Label(2,3,"低");
	        sheet.addCell(di);
	        
	        //把创建的内容写入到输出流中，并关闭输出流
	        workbook.write();
	        workbook.close();
	        os.close();
	    }
	
	public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
		ExcelUtil ex = new ExcelUtil();
		File fileWrite = new File("d:/test2.xls");
		fileWrite.createNewFile();
		OutputStream os = new FileOutputStream(fileWrite);
		ex.createExcel(os);
//		WritableWorkbook wwb = Workbook.createWorkbook(os);
//		WritableSheet ws = wwb.createSheet("陶氏",0);
		
//		ws.addCell(new Label(0,0,"新闻引自"));
//		ws.addCell(new Label(1,0,"新闻标题"));
//		ws.addCell(new Label(2,0,"作者/转载自"));
//		ws.addCell(new Label(3,0,"内容"));
//		ws.addCell(new Label(4,0,"关键字"));
//		ws.addCell(new Label(5,0,"刊载日期"));
//		ws.addCell(new Label(6,0,"原文链接"));
//		ws.addCell(new Label(7,0,"创建时间"));
	}
}
