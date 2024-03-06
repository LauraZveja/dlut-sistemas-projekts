package eu.virac.dlut.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.AcademicWorkLoad;
import eu.virac.dlut.models.BaseFin;
import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.Project;
import eu.virac.dlut.models.helpers.EmployeeAndHourDTO;
import eu.virac.dlut.services.IEmployeeService;
import eu.virac.dlut.services.IExcelCreatorService;
import eu.virac.dlut.services.IFinanceSourceService;
import eu.virac.dlut.services.ITableExportService;

@Service
public class ExcelCreatorServiceImpl implements IExcelCreatorService{

	@Autowired
	ITableExportService tableExportService;
	
	@Autowired
	IFinanceSourceService financeSourceService;
	
	@Autowired
	IEmployeeService employeeService;
	
	
	@Override
	public void createFinSourceTableExcel(String excelPath, int finSourceId, int year, int month) throws Exception {
		
		ArrayList<EmployeeAndHourDTO> allEmplInFinSource = new ArrayList<>();
		
		FinanceSource finSource = financeSourceService.selectOneFinanceSourceById(finSourceId);
		
		if(finSource instanceof Project) 
			allEmplInFinSource = tableExportService.selectEmployeesAndHoursForProjectForYearMonth(finSource.getIdFinSource(), year, month);
		else if (finSource instanceof BaseFin)
			allEmplInFinSource = tableExportService.selectEmployeesAndHoursForBaseFinForYearMonth(finSource.getIdFinSource(), year, month);
		else if (finSource instanceof AcademicWorkLoad)
			allEmplInFinSource = tableExportService.selectEmployeesAndHoursForAcademicWorkLoadForYearMonth(finSource.getIdFinSource(), year, month);
		else 
			allEmplInFinSource = tableExportService.selectEmployeesAndHoursForIndirectVuasForYearMonth(finSource.getIdFinSource(), year, month);
					
		//FileInputStream fis =new FileInputStream(new File("C:\\Users\\elina\\eclipsePrakseI\\dlut\\src\\main\\resources\\templates\\finance-source-excel-template.xlsx"));
		//TODO jāmaina uz Tavu ceļu, bet labāk uzlikt, lai ielasa no classresources.
		FileInputStream fis =new FileInputStream(new File("C:\\Users\\elina\\eclipsePrakseI\\dlut\\src\\main\\resources\\templates\\excel-template-finance-source.xlsx"));
		//Workbook wb= new XSSFWorkbook(fis);
		Workbook wb= WorkbookFactory.create(fis);
		
		Workbook workbook = wb;

			//Sheet sheet = workbook.createSheet(finSource.getTitle()+ ", "+ month + "/" + year);
			Sheet sheet = workbook.getSheetAt(0);
			workbook.setSheetName(workbook.getSheetIndex(sheet), (month+"."+year+"."));
			
			Map<Integer, String> monthNumberAndName = new HashedMap<>();
			monthNumberAndName.put(1, "janvāri");
			monthNumberAndName.put(2, "februāri");
			monthNumberAndName.put(3, "martu");
			monthNumberAndName.put(4, "aprīli");
			monthNumberAndName.put(5, "maiju");
			monthNumberAndName.put(6, "jūniju");
			monthNumberAndName.put(7, "jūliju");
			monthNumberAndName.put(8, "augustu");
			monthNumberAndName.put(9, "septembri");
			monthNumberAndName.put(10, "oktobri");
			monthNumberAndName.put(11, "novembri");
			monthNumberAndName.put(12, "decembri");

			sheet.getRow(2).getCell(0).setCellValue(finSource.getCode()); 
			sheet.getRow(5).getCell(0).setCellValue(finSource.getTitle()); //6.rinda A 
			sheet.getRow(10).getCell(0).setCellValue("par "+year+". gada "+ monthNumberAndName.get(month)); //11.rinda A
			//13. rinda no 3 suunas līdz dienu beigām "mēneša dienas", viena tukša tad 1 suuna ar stundas likmi un  1 suuna ar atvalinaajuma h "Nostrādātās" pāri 2 suunaam, darbā(zem tā visi apzīmējumi
			//14. rinda no 3 suunas briivdiena rakstīts
			sheet.getRow(14).getCell(3); //saakot no sii dienu cipari
			
			int daysInMonth = allEmplInFinSource.get(0).getHoursInMonth().size();
			sheet.addMergedRegion(new CellRangeAddress(12, 12, 3, daysInMonth+2));
			sheet.getRow(12).getCell(3).setCellValue("Mēneša dienas");
			
			CellStyle cellStyleCenter = wb.createCellStyle();
			cellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
			cellStyleCenter.setVerticalAlignment(VerticalAlignment.BOTTOM);

			sheet.getRow(12).getCell(3).setCellStyle(cellStyleCenter);
			
			//Row header = sheet.createRow(16);
			Row header = sheet.getRow(16);

			Map<Integer, String> map = allEmplInFinSource.get(0).getHoursInMonth();
			
			int number = 1;
			for(int j = 3; j <=daysInMonth+2; j++) {
				if(map.get(number) == "BR") 
					sheet.getRow(13).getCell(j).setCellValue("Brīvdiena");
				if(map.get(number) == "SV") 
					sheet.getRow(13).getCell(j).setCellValue("Svētki");
				
				sheet.getRow(14).getCell(j).setCellValue(number++);
				
			}
	
			int rowNum = 16;
			
			for (int i = 0; i < allEmplInFinSource.size(); i++) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(i+1);
				row.createCell(1).setCellValue(allEmplInFinSource.get(i).getSurname() + ", " + allEmplInFinSource.get(i).getName());
				row.createCell(2).setCellValue(allEmplInFinSource.get(i).getPosition()); //ko dariit ar vairaakaam poziicijaam??
			
				Map<Integer, String> mapSpec = allEmplInFinSource.get(i).getHoursInMonth();
				
				int k = 3; //cell number
					for (Map.Entry<Integer, String> e : mapSpec.entrySet()) {
					//row.createCell(k++).setCellValue(e.getValue());
						Cell cell = row.createCell(k++);
						cell.setCellValue(e.getValue());
						if (e.getValue() == "BR") {
							CellStyle styleGreen = wb.createCellStyle();
							styleGreen.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
							styleGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);
							Font fontGreen = wb.createFont();
							fontGreen.setColor(IndexedColors.DARK_GREEN.getIndex());
							styleGreen.setFont(fontGreen);
							cell.setCellStyle(styleGreen);
						}
					}

				row.createCell(35).setCellValue(allEmplInFinSource.get(i).getPayPerHour());
				row.createCell(36).setCellValue(allEmplInFinSource.get(i).sumHours());
				row.createCell(37).setCellValue(allEmplInFinSource.get(i).daysWorkedAccordingToHours());
				row.createCell(38).setCellValue(allEmplInFinSource.get(i).getVacationHours());
				row.createCell(39).setCellValue(allEmplInFinSource.get(i).getSickDaysS());
				row.createCell(40).setCellValue(allEmplInFinSource.get(i).getSickDaysSb());
				row.createCell(41).setCellValue(allEmplInFinSource.get(i).getVacationAnnualDays());
				row.createCell(42).setCellValue(allEmplInFinSource.get(i).getUnpaidVacation());
				row.createCell(43).setCellValue(allEmplInFinSource.get(i).getParentalLeave());
				row.createCell(44).setCellValue(allEmplInFinSource.get(i).getVacationEducation());
				row.createCell(45).setCellValue(allEmplInFinSource.get(i).getVacationExtra());
				row.createCell(46).setCellValue(allEmplInFinSource.get(i).getVacationCreative());
				row.createCell(47).setCellValue(allEmplInFinSource.get(i).getVoluntaryWork());
				row.createCell(48).setCellValue(allEmplInFinSource.get(i).getMissionWorkDays());
				row.createCell(49).setCellValue(allEmplInFinSource.get(i).getMissionEducationDays());
				row.createCell(50).setCellValue(allEmplInFinSource.get(i).getUnjustifiedAbsence());
			}
			
			try {
			workbook.write(new FileOutputStream(excelPath));
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//funkcija darbinieka excel datnes izveidošanai
	@Override
	public void createEmployeeExcel(String excelPath, int employeeId, int year, int month) throws Exception {
		Employee employee = employeeService.selectOneEmployeeById(employeeId);

		ArrayList<EmployeeAndHourDTO> list = tableExportService
				.selectNecessaryDataForEmployeeInAllOtherFinanceSourcesInOneMonth(year, month, employeeId);

		FileInputStream fis = new FileInputStream(new File(
				"C:\\Users\\laura\\SpringBootIntellij\\dlut-sistemas-projekts\\src\\main\\resources\\templates\\excel-template-employee.xlsx"));
		Workbook wb = WorkbookFactory.create(fis);

		Workbook workbook = wb;

		// Sheet sheet = workbook.createSheet(finSource.getTitle()+ ", "+ month + "/" +
		// year);
		Sheet sheet = workbook.getSheetAt(0);
		workbook.setSheetName(workbook.getSheetIndex(sheet), (month + "." + year + "."));

		int daysInMonth = list.get(0).getHoursInMonth().size();
		String start = LocalDate.of(year, month, 1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		String end = LocalDate.of(year, month, daysInMonth).format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
		sheet.getRow(2).getCell(0).setCellValue("Periods, par kuru tiek rakstīts pārskats: " + start + " - " + end); 
		sheet.getRow(3).getCell(0).setCellValue("Darbinieka vārds, uzvārds: " + employee.getName() + " " + employee.getSurname());																								

		
		sheet.getRow(7).getCell(3); //saakot no sii dienu cipari
		
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 3, daysInMonth+2));
		sheet.getRow(5).getCell(3).setCellValue("Mēneša dienas");
		
		CellStyle cellStyleCenter = wb.createCellStyle();
		cellStyleCenter.setAlignment(HorizontalAlignment.CENTER);
		cellStyleCenter.setVerticalAlignment(VerticalAlignment.BOTTOM);

		sheet.getRow(5).getCell(3).setCellStyle(cellStyleCenter);
		
		Row header = sheet.getRow(8);

		Map<Integer, String> map = list.get(0).getHoursInMonth();
		
		int number = 1;
		for(int j = 3; j <=daysInMonth+2; j++) {
			if(map.get(number) == "BR") 
				sheet.getRow(6).getCell(j).setCellValue("Brīvdiena");
			if(map.get(number) == "SV") 
				sheet.getRow(6).getCell(j).setCellValue("Svētki");
			
			sheet.getRow(7).getCell(j).setCellValue(number++);
			
		}
		
		int rowNum = 8;
		
		for (int i = 0; i < list.size(); i++) {
			//Row row = sheet.createRow(rowNum++);
			Row row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(i+1);
			row.createCell(1).setCellValue(list.get(i).getFinanceSourceCode()+", "+ list.get(i).getFinanceSourceTitle());
			row.createCell(2).setCellValue(list.get(i).getPosition());
		
			Map<Integer, String> mapSpec = list.get(i).getHoursInMonth();
			
			int k = 3; //cell number
				for (Map.Entry<Integer, String> e : mapSpec.entrySet()) {
				//row.createCell(k++).setCellValue(e.getValue());
					Cell cell = row.createCell(k++);
					cell.setCellValue(e.getValue());
						CellStyle styleGreen = wb.createCellStyle();
						styleGreen.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
						styleGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						cell.setCellStyle(styleGreen);
				}

				CellStyle styleYellow = wb.createCellStyle();
				styleYellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
				styleYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
				
			row.createCell(36).setCellValue(list.get(i).sumHours());
			row.getCell(36).setCellStyle(styleYellow);
			row.createCell(37).setCellValue(list.get(i).daysWorkedAccordingToHours());
			row.getCell(37).setCellStyle(styleYellow);
			row.createCell(38).setCellValue(list.get(i).getSickDaysS());
			row.getCell(38).setCellStyle(styleYellow);
			row.createCell(39).setCellValue(list.get(i).getSickDaysSb());
			row.getCell(39).setCellStyle(styleYellow);
			row.createCell(40).setCellValue(list.get(i).getVacationAnnualDays());
			row.getCell(40).setCellStyle(styleYellow);
			row.createCell(41).setCellValue(list.get(i).getUnpaidVacation());
			row.getCell(41).setCellStyle(styleYellow);
			row.createCell(42).setCellValue(list.get(i).getParentalLeave());
			row.getCell(42).setCellStyle(styleYellow);
			row.createCell(43).setCellValue(list.get(i).getVacationEducation());
			row.getCell(43).setCellStyle(styleYellow);
			row.createCell(44).setCellValue(list.get(i).getVacationExtra());
			row.getCell(44).setCellStyle(styleYellow);
			row.createCell(45).setCellValue(list.get(i).getVacationCreative());
			row.getCell(45).setCellStyle(styleYellow);
			row.createCell(46).setCellValue(list.get(i).getVoluntaryWork());
			row.getCell(46).setCellStyle(styleYellow);
			row.createCell(47).setCellValue(list.get(i).getMissionWorkDays());
			row.getCell(47).setCellStyle(styleYellow);
			row.createCell(48).setCellValue(list.get(i).getMissionEducationDays());
			row.getCell(48).setCellStyle(styleYellow);
			row.createCell(49).setCellValue(list.get(i).getUnjustifiedAbsence());
			row.getCell(49).setCellStyle(styleYellow);
			
			if(list.get(i).getProjectCharacter() != null) {
				rowNum += 1;
				Row rowForCharacter = sheet.createRow(rowNum);
				rowForCharacter.createCell(1).setCellValue(list.get(i).getProjectCharacter());
//				
			int j = 3; //cell number
			for (Map.Entry<Integer, String> e : mapSpec.entrySet()) {
			//row.createCell(k++).setCellValue(e.getValue());
				Cell cell = rowForCharacter.createCell(j++);
				cell.setCellValue(e.getValue());	
			}
			rowForCharacter.createCell(36).setCellValue(list.get(i).sumHours());
			
			rowForCharacter.createCell(37).setCellValue(list.get(i).daysWorkedAccordingToHours());
			rowForCharacter.createCell(38).setCellValue(0);
			rowForCharacter.createCell(39).setCellValue(0);
			rowForCharacter.createCell(40).setCellValue(0);
			rowForCharacter.createCell(41).setCellValue(0);
			rowForCharacter.createCell(42).setCellValue(0);
			rowForCharacter.createCell(43).setCellValue(0);
			rowForCharacter.createCell(44).setCellValue(0);
			rowForCharacter.createCell(45).setCellValue(0);
			rowForCharacter.createCell(46).setCellValue(0);
			rowForCharacter.createCell(47).setCellValue(0);
			rowForCharacter.createCell(48).setCellValue(0);
			rowForCharacter.createCell(49).setCellValue(0);
			
		}
			rowNum++;
		}
		sheet.createRow(rowNum).createCell(2).setCellValue("KOPĀ");
		Map<Integer, Double> mapRes = tableExportService.allHoursOneEmployeeOnDate(year, month, employeeId);
		int n = 3; //cell number
		for (Map.Entry<Integer, Double> entry : mapRes.entrySet()) {
		//row.createCell(k++).setCellValue(e.getValue());
			Cell cell = sheet.getRow(rowNum).createCell(n++);
			cell.setCellValue(entry.getValue());
		
		}
		double allHWorked = tableExportService.sumAllHoursWorked(mapRes);
		sheet.getRow(rowNum).createCell(36).setCellValue(allHWorked);
		
		
		try {
		workbook.write(new FileOutputStream(excelPath));
		workbook.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}
