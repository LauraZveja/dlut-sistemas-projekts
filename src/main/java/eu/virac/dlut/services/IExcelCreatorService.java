package eu.virac.dlut.services;

public interface IExcelCreatorService {
	
	void createFinSourceTableExcel(String excelPath, int finSourceId, int year, int month) throws Exception;

	void createEmployeeExcel(String excelPath, int year, int month, int employeeId) throws Exception;
}
