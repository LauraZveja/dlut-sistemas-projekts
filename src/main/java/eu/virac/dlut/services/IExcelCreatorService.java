package eu.virac.dlut.services;
import org.apache.poi.ss.usermodel.Workbook;

public interface IExcelCreatorService {

	Workbook createFinSourceTableExcel(int finSourceId, int year, int month) throws Exception;

	Workbook createEmployeeExcel(int year, int month, int employeeId) throws Exception;
}
