package com.ingroinfo.ubm.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import com.ingroinfo.ubm.entity.Master_Product;

public class ExcelHelper {

	/* this to check file Excel type or not */

	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	// convert Excel to list of Mater_Product

	public static List<Master_Product> convertExcelToListOfProduct(InputStream is) {

		List<Master_Product> list = new ArrayList<>();

		try {

			try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
				XSSFSheet sheet = workbook.getSheet("data");
				int rowNumber = 0;
				Iterator<Row> iterator = sheet.iterator();

				while (iterator.hasNext()) {
					Row row = iterator.next();

					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}

					Iterator<Cell> cells = row.iterator();

					int cid = 0;
					Master_Product mp = new Master_Product();

					while (cells.hasNext()) {
						Cell cell = cells.next();

						switch (cid) {

						case 0:
							mp.setMasterProductId((int) cell.getNumericCellValue());
							System.out.print(mp);
							break;
						case 1:
							mp.setBarCode(cell.getNumericCellValue());
							System.out.print(mp);
							break;
						case 2:
							mp.setProductName(cell.getStringCellValue());
							break;
						case 3:
							mp.setAlias(cell.getStringCellValue());
							break;
						case 4:
							mp.setCataregy(cell.getStringCellValue());
							break;
						case 5:
							mp.setBrand(cell.getStringCellValue());
							break;
						case 6:
							mp.setUnit(cell.getNumericCellValue());
							break;
						default:
							break;

						}
						cid++;
					}

					list.add(mp);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
