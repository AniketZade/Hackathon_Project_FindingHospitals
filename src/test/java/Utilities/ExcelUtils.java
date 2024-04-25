package Utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
		
		
		public static void  saveToExceldoctordetails(Map<String,String> doctorDetails ) throws FileNotFoundException {
			FileOutputStream  file = new FileOutputStream(System.getProperty("user.dir")+"/output_excel_.xlsx");
			XSSFWorkbook work = new XSSFWorkbook();
			XSSFSheet sheet1 = work.createSheet("Doctor's Details");
			XSSFRow row1 = sheet1.createRow(0);
			row1.createCell(0).setCellValue("Doctor Name");
			row1.createCell(1).setCellValue("Doctor Description");
			
			Set<String> keys = doctorDetails.keySet();
			
			int i =1;
			for(String s:keys)  {
				XSSFRow row = sheet1.createRow(i);
				row.createCell(0).setCellValue(s);
				row.createCell(1).setCellValue(doctorDetails.get(s));
				i++;
			}
			
			try {
				work.write(file);
				work.close(); 
				file.close();
			} 
			catch (IOException e) {
				
			}	
		}
		
		public static void saveToExcelSurgeriesList(String sheetname , List<String>listSurgeries) throws IOException {
			
			FileInputStream file = new FileInputStream(new File(".\\output_excel_.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet s = wb.createSheet(sheetname);
			XSSFRow r0 = s.createRow(0);
			r0.createCell(0).setCellValue("Popular Surgeries");
			for(int i=1;i<=listSurgeries.size();i++) {
				XSSFRow r = s.createRow(i);
				r.createCell(0).setCellValue(listSurgeries.get(i-1));	
			}
			FileOutputStream f = new FileOutputStream(".\\output_excel_.xlsx");
			wb.write(f);
			wb.close();
			f.close();
			file.close();
			System.out.println("write sucessfully");
		}
			
		
		
}
