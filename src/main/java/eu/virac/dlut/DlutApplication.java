package eu.virac.dlut;

import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import eu.virac.dlut.models.AcademicWorkLoad;
import eu.virac.dlut.models.BaseFin;
import eu.virac.dlut.models.BasicActivities;
import eu.virac.dlut.models.BasicActivitiesItems;
import eu.virac.dlut.models.DayStatus;
import eu.virac.dlut.models.Department;
import eu.virac.dlut.models.Employee;
import eu.virac.dlut.models.EmployeeTimeSheet;
import eu.virac.dlut.models.FinanceOrderVacation;
import eu.virac.dlut.models.FinanceOrderWork;
import eu.virac.dlut.models.FinancePlanning;
import eu.virac.dlut.models.FinanceSource;
import eu.virac.dlut.models.FinanceSourceType;
import eu.virac.dlut.models.FullTimeEquivalent;
import eu.virac.dlut.models.HolidayPreholidayTransferedHoliday;
import eu.virac.dlut.models.HoursInMonth;
import eu.virac.dlut.models.IndirectVUAS;
import eu.virac.dlut.models.OrderMission;
import eu.virac.dlut.models.OrderVacation;
import eu.virac.dlut.models.OrderWork;
import eu.virac.dlut.models.Project;
import eu.virac.dlut.models.ProjectCategory;
import eu.virac.dlut.models.ProjectCharacter;
import eu.virac.dlut.repos.IAcademicWorkLoadRepo;
import eu.virac.dlut.repos.IBaseFinRepo;
import eu.virac.dlut.repos.IBasicActivitiesItemsRepo;
import eu.virac.dlut.repos.IBasicActivitiesRepo;
import eu.virac.dlut.repos.IDayStatusRepo;
import eu.virac.dlut.repos.IDepartmentRepo;
import eu.virac.dlut.repos.IEmployeeRepo;
import eu.virac.dlut.repos.IEmployeeTimeSheetRepo;
import eu.virac.dlut.repos.IFinanceOrderVacationRepo;
import eu.virac.dlut.repos.IFinanceOrderWorkRepo;
import eu.virac.dlut.repos.IFinancePlanningRepo;
import eu.virac.dlut.repos.IFinanceSourceRepo;
import eu.virac.dlut.repos.IFinanceSourceTypeRepo;
import eu.virac.dlut.repos.IFullTimeEquivalentRepo;
import eu.virac.dlut.repos.IHolidayPreholidayTransferedHolidayRepo;
import eu.virac.dlut.repos.IHoursInMonthRepo;
import eu.virac.dlut.repos.IIndirectVUASRepo;
import eu.virac.dlut.repos.IOrderMissionRepo;
import eu.virac.dlut.repos.IOrderOtherRepo;
import eu.virac.dlut.repos.IOrderVacationRepo;
import eu.virac.dlut.repos.IOrderWorkRepo;
import eu.virac.dlut.repos.IProjectCategoryRepo;
import eu.virac.dlut.repos.IProjectCharacterRepo;
import eu.virac.dlut.repos.IProjectRepo;

@SpringBootApplication
public class DlutApplication {

	public static void main(String[] args) {
		SpringApplication.run(DlutApplication.class, args);
	}
	
	//@Bean
	public CommandLineRunner saveData(IEmployeeRepo emplRepo, IDepartmentRepo depRepo,
			IAcademicWorkLoadRepo acworkloRepo, IBaseFinRepo baseFinRepo, IIndirectVUASRepo indirVUASRepo,
			IBasicActivitiesItemsRepo basActItemRepo, IBasicActivitiesRepo baseActRepo, IDayStatusRepo dayStatRepo,
			IEmployeeTimeSheetRepo emplTimeSheetRepo, IFinanceOrderVacationRepo finVacRepo, IFinanceOrderWorkRepo finWorkRepo, 
			IFinancePlanningRepo finPlanRepo, IFinanceSourceRepo finSourceRepo, IFinanceSourceTypeRepo finSourceTypRepo, 
			IHolidayPreholidayTransferedHolidayRepo holidayRepo, IOrderMissionRepo ordMisRepo, IOrderOtherRepo ordOtherRepo,
			IOrderVacationRepo ordVacRepo, IOrderWorkRepo ordWorkRepo, IProjectCategoryRepo projCatRepo,
			IProjectCharacterRepo projCharRepo, IProjectRepo projRepo, IFullTimeEquivalentRepo fullTimeEquivalentRepo, 
			IHoursInMonthRepo hoursInMonthRepo) {
		
		Department d1 = new Department("Augstas veiktspējas skaitļošanas nodaļa", "HCP");
		Department d2 = new Department("Astronomijas un astrofizikas nodaļa", null);
		Department d5 = new Department("Tehniskais dienests", "TD");
		Department d6 = new Department("Elektronika un satelīttehnoloģijas nodaļa", "ESN");
		depRepo.save(d1);
		depRepo.save(d2);
		depRepo.save(d5);
		depRepo.save(d6);
		
		Employee e1 = new Employee("Karina", "Šķirmante", "Pētniece", true, "P77/1, 2011.11.12", d1);
		Employee e2 = new Employee("Vladislavs", "Bezrukovs", "Pētnieks", true, "P888/11, 2008.05.02", d2);
		Employee e3 = new Employee("Artūrs", "Orbidāns", "Zinātniskais asistents / elektronikas inženieris", true, "P78/13, 2013.08.23", d5);
		Employee e4 = new Employee("Romass", "Pauliks", "Vadošais pētnieks", true, "P12/02. 2018.11.12", d6);
		emplRepo.save(e1);
		emplRepo.save(e2);
		emplRepo.save(e3);
		emplRepo.save(e4);
		
		ProjectCharacter pCh1 = new ProjectCharacter("Saimnieciska darbība");
		ProjectCharacter pCh2 = new ProjectCharacter("Nesaimnieciska darbība");
		projCharRepo.save(pCh1);
		projCharRepo.save(pCh2);
		
		ProjectCategory pCat1 = new ProjectCategory("Fundamentālais");
		ProjectCategory pCat2 = new ProjectCategory("Lietišķais");
		projCatRepo.save(pCat1);
		projCatRepo.save(pCat2);
		
		Project p1 = new Project(/*"Projekts", "proj", */"Saules sistēmas mazo ķermeņu pētījumi", "lzp-2018/1-0401", "z-K.Š.", null, pCh2, pCat1, 36, LocalDate.of(2018, 9, 1), LocalDate.of(2021, 8, 31));
		Project p2 = new Project(/*"Projekts", "proj", */"Galaktisko māzeru pētījumi (GMP)", "lzp-2018/1-0292", "z-I.Š", null, pCh2, pCat1, 36, LocalDate.of(2018, 9, 1), LocalDate.of(2021, 8, 31));
		Project p3 = new Project(/*"Projekts", "proj", */"Ilgtspējīga Latvijas energosistēmas attīstība un integrācija Eiropā", "VPP-EM-INFRA-2018/1-006", "VPP FP", null, pCh2, pCat1, 34, LocalDate.of(2019, 3, 1), LocalDate.of(2021, 12, 6));
		Project p4 = new Project(/*"Projekts", "proj", */"“Development and production of a universal measuring system that meets", "Nr.4000132235/20/NL/SC", "MR", null, pCh1, pCat2, 20, LocalDate.of(2020, 11, 1), LocalDate.of(2022, 6, 30));
		projRepo.save(p1);
		finSourceRepo.save(p1);
		projRepo.save(p2);
		projRepo.save(p3);
		projRepo.save(p4);
		p1.setActive(true);
		p2.setActive(true);
		p3.setActive(false);
		p4.setActive(true);
		projRepo.save(p1);
		projRepo.save(p2);
		projRepo.save(p3);
		projRepo.save(p4);
		
		
		BaseFin b1 = new BaseFin("Bāze", "zb"); //F2
		baseFinRepo.save(b1);
		
		IndirectVUAS indVuas1 = new IndirectVUAS("VeA netiešās", "ni"); //F3
		indirVUASRepo.save(indVuas1);
		
		AcademicWorkLoad acWorkLoad1 = new AcademicWorkLoad("Akadēmiskā slodze", null); //F4
		acworkloRepo.save(acWorkLoad1);
		
		FinanceSourceType fst1 = new FinanceSourceType("Pētnieciskais");
		FinanceSourceType fst2 = new FinanceSourceType("Akadēmiskais");
		FinanceSourceType fst3 = new FinanceSourceType("Tehniskais atbalsts");
		FinanceSourceType fst4 = new FinanceSourceType("Administratīvais");
		finSourceTypRepo.save(fst1);
		finSourceTypRepo.save(fst2);
		finSourceTypRepo.save(fst3);
		finSourceTypRepo.save(fst4);
		
		OrderWork ow1 = new OrderWork("R/2010-11", LocalDate.of(2010, 11, 11), LocalDate.of(2010, 12, 12), e1, true);
		OrderWork ow2 = new OrderWork("R/2021-4", LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 21), e2, true);
		OrderWork ow3 = new OrderWork("R/2021-1", LocalDate.of(2021, 3, 20), LocalDate.of(2021, 3, 21), e3, true);
		OrderWork ow4 = new OrderWork("R/2021-2", LocalDate.of(2021, 4, 20), LocalDate.of(2021, 4, 21), e4, true);
		ordWorkRepo.save(ow1);
		ordWorkRepo.save(ow2);
		ordWorkRepo.save(ow3);
		ordWorkRepo.save(ow4);
		
		OrderMission om1 = new OrderMission("XX1", LocalDate.of(2021, 4, 28), p1, LocalDate.of(2021, 5, 6), LocalDate.of(2021, 5, 6), e1, (short) 0);
		ordMisRepo.save(om1);
		om1.setDesignation("KD");
		ordMisRepo.save(om1);
		
		OrderVacation ov1 = new OrderVacation(null, null, null, null, e1);
		ordVacRepo.save(ov1);
		ov1.setDesignation("AI");
		ordVacRepo.save(ov1);
		
		FinanceOrderWork fod1 = new FinanceOrderWork(p1, ow1, LocalDate.of(2021, 8, 31), 2400, 0, 0, fst1, "Pozīcija 1");
		FinanceOrderWork fod2 = new FinanceOrderWork(acWorkLoad1, ow1, null, 0, 0.25, 0, fst2, null);
		FinanceOrderWork fod3 = new FinanceOrderWork(indVuas1, ow1, LocalDate.of(2021, 8, 31), 0, 0.4, 0, null, null);
		FinanceOrderWork fod4 = new FinanceOrderWork(p3, ow2, LocalDate.of(2021, 12, 31), 0, 0.05, 0, null, null);
		FinanceOrderWork fod5 = new FinanceOrderWork(p1, ow2, LocalDate.of(2021, 8, 31), 0, 0, 0, null, "Pozīcija 2");
		FinanceOrderWork fod6 = new FinanceOrderWork(p2, ow2, LocalDate.of(2021, 12, 31), 0, 0, 0, null, null);
		FinanceOrderWork fod7 = new FinanceOrderWork(p2, ow3, null, 0, 0.12, 0, null, null);
		FinanceOrderWork fod8 = new FinanceOrderWork(p4, ow3, null, 0, 0, 0, null, null);
		FinanceOrderWork fod9 = new FinanceOrderWork(indVuas1, ow3, LocalDate.of(2021, 12, 31), 0, 0, 0, null, null);
		FinanceOrderWork fod10 = new FinanceOrderWork(acWorkLoad1, ow3, LocalDate.of(2022, 1, 31), 0, 0.283, 0, null, null);
		FinanceOrderWork fod11 = new FinanceOrderWork(p4, ow4, LocalDate.of(2021, 12, 31), 0, 0.2, 0, null, null);
		FinanceOrderWork fod12 = new FinanceOrderWork(b1, ow4, LocalDate.of(2022, 1, 31), 0, 0, 0, null, null);
		finWorkRepo.save(fod1);
		finWorkRepo.save(fod2);
		finWorkRepo.save(fod3);
		finWorkRepo.save(fod4);
		finWorkRepo.save(fod5);
		finWorkRepo.save(fod6);
		finWorkRepo.save(fod7);
		finWorkRepo.save(fod8);
		finWorkRepo.save(fod9);
		finWorkRepo.save(fod10);
		finWorkRepo.save(fod11);
		finWorkRepo.save(fod12);
		
		FinanceOrderVacation fov1 = new FinanceOrderVacation(p1, ov1);
		FinanceOrderVacation fov2 = new FinanceOrderVacation(acWorkLoad1, ov1);
		finVacRepo.save(fov1);
		finVacRepo.save(fov2);
		
		EmployeeTimeSheet ets37 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 17), fod5, 8, "Novērojumu datu apstrāde", true);
		EmployeeTimeSheet ets22 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 17), fod1, 3, "Novērojumu datu apstrāde", true);
		EmployeeTimeSheet ets23 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 17), fod2, 4, "Nodarbības JAVA", true);
		EmployeeTimeSheet ets24 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 17), fod3, 1, "Dalība operatīvajā sanāksmē", true);
		EmployeeTimeSheet ets38 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 8), fod5, 7, "Novērojumu datu apstrāde", true);
		EmployeeTimeSheet ets19 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 8), fod1, 3, "Novērojumu datu apstrāde", true);
		EmployeeTimeSheet ets20 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 8), fod2, 3, "Nodarbības JAVA", true);
		EmployeeTimeSheet ets21 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 8), fod3, 1, "Dalība operatīvajā sanāksmē", true);
		EmployeeTimeSheet ets39 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 5), fod5, 8, "Novērojumu datu apstrāde", true);
		EmployeeTimeSheet ets1 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 5), fod1, 3, "Novērojumu datu apstrāde", true);
		EmployeeTimeSheet ets2 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 5), fod2, 4, "Nodarbības JAVA", true);
		EmployeeTimeSheet ets3 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 5), fod3, 1, "Dalība operatīvajā sanāksmē", true);
		EmployeeTimeSheet ets4 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 6), fod1, 8, "Dalība LU konferencē, publicējot projektā sasniegtos rezultātus", om1, true);
		EmployeeTimeSheet ets5 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 7), fod2, 8, "Nodarbības Datu struktūrās", true);
		EmployeeTimeSheet ets40 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 10), fod5, 0, null, "S", true);		
		EmployeeTimeSheet ets6 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 10), fod1, 0, null, ov1, true);
		EmployeeTimeSheet ets7 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 10), fod2, 0, null, ov1, true);
		EmployeeTimeSheet ets8 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 10), fod3, 0, null, ov1, true);
		EmployeeTimeSheet ets41 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 11), fod5, 0, null, "S", true);
		EmployeeTimeSheet ets25 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 11), fod1, 0, null, ov1, true);
		EmployeeTimeSheet ets26 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 11), fod2, 0, null, ov1, true);
		EmployeeTimeSheet ets27 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 11), fod3, 0, null, ov1, true);
		EmployeeTimeSheet ets42 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 12), fod5, 0, null, "S", true);
		EmployeeTimeSheet ets28 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 12), fod1, 0, null, ov1, true);
		EmployeeTimeSheet ets29 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 12), fod2, 0, null, ov1, true);
		EmployeeTimeSheet ets30 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 12), fod3, 0, null, ov1, true);
		EmployeeTimeSheet ets31 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 13), fod1, 0, null, ov1, true);
		EmployeeTimeSheet ets32 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 13), fod2, 0, null, ov1, true);
		EmployeeTimeSheet ets33 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 14), fod3, 0, null, ov1, true);
		EmployeeTimeSheet ets34 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 14), fod1, 0, null, ov1, true);
		EmployeeTimeSheet ets35 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 14), fod2, 0, null, ov1, true);
		EmployeeTimeSheet ets36 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 14), fod3, 0, null, ov1, true);
		EmployeeTimeSheet ets9 = new EmployeeTimeSheet(LocalDate.of(2020, 9, 1), fod4, 0, null, false);
		EmployeeTimeSheet ets10 = new EmployeeTimeSheet(LocalDate.of(2020, 9, 1), fod5, 0, null, false);
		EmployeeTimeSheet ets11 = new EmployeeTimeSheet(LocalDate.of(2020, 9, 1), fod6, 4, "Pētniecība", false);
		EmployeeTimeSheet ets12 = new EmployeeTimeSheet(LocalDate.of(2020, 9, 1), fod6, 4, "Publikāciju sagatavošana", false);
		EmployeeTimeSheet ets13 = new EmployeeTimeSheet(LocalDate.of(2020, 5, 14), fod7, 4, "Pētniecība", false);
		EmployeeTimeSheet ets14 = new EmployeeTimeSheet(LocalDate.of(2020, 5, 14), fod10, 2.29, "Lekciju lasīšana", false);
		EmployeeTimeSheet ets15 = new EmployeeTimeSheet(LocalDate.of(2020, 5, 12), fod9, 1.7, "Pētniecība", false);
		EmployeeTimeSheet ets16 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 12), fod10, 2, "Lekciju lasīšana", false);
		EmployeeTimeSheet ets17 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 12), fod9, 0, null, false);
		EmployeeTimeSheet ets18 = new EmployeeTimeSheet(LocalDate.of(2021, 5, 12), fod7, 1, "Pētniecība", false);
		emplTimeSheetRepo.save(ets1);
		emplTimeSheetRepo.save(ets2);
		emplTimeSheetRepo.save(ets3);
		emplTimeSheetRepo.save(ets4);
		emplTimeSheetRepo.save(ets5);
		emplTimeSheetRepo.save(ets6);
		emplTimeSheetRepo.save(ets7);
		emplTimeSheetRepo.save(ets8);
		emplTimeSheetRepo.save(ets9);
		emplTimeSheetRepo.save(ets10);
		emplTimeSheetRepo.save(ets11);
		emplTimeSheetRepo.save(ets12);
		emplTimeSheetRepo.save(ets13);
		emplTimeSheetRepo.save(ets14);
		emplTimeSheetRepo.save(ets15);
		emplTimeSheetRepo.save(ets16);
		emplTimeSheetRepo.save(ets17);
		emplTimeSheetRepo.save(ets18);
		emplTimeSheetRepo.save(ets19);
		emplTimeSheetRepo.save(ets20);
		emplTimeSheetRepo.save(ets21);
		emplTimeSheetRepo.save(ets22);
		emplTimeSheetRepo.save(ets23);
		emplTimeSheetRepo.save(ets24);
		emplTimeSheetRepo.save(ets25);
		emplTimeSheetRepo.save(ets26);
		emplTimeSheetRepo.save(ets27);
		emplTimeSheetRepo.save(ets28);
		emplTimeSheetRepo.save(ets29);
		emplTimeSheetRepo.save(ets30);
		emplTimeSheetRepo.save(ets31);
		emplTimeSheetRepo.save(ets32);
		emplTimeSheetRepo.save(ets33);
		emplTimeSheetRepo.save(ets34);
		emplTimeSheetRepo.save(ets35);
		emplTimeSheetRepo.save(ets36);
		emplTimeSheetRepo.save(ets37);
		emplTimeSheetRepo.save(ets38);
		emplTimeSheetRepo.save(ets39);
		emplTimeSheetRepo.save(ets40);
		emplTimeSheetRepo.save(ets41);
		emplTimeSheetRepo.save(ets42);

		
		
		DayStatus ds1 = new DayStatus("Svētku");
		DayStatus ds2 = new DayStatus("Pirmssvētku");
		DayStatus ds3 = new DayStatus("Pārcelta-B");
		DayStatus ds4 = new DayStatus("Pārcelta-D");
		dayStatRepo.save(ds1);
		dayStatRepo.save(ds2);
		dayStatRepo.save(ds3);
		dayStatRepo.save(ds4);
		
		HolidayPreholidayTransferedHoliday hol1 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 5, 1), ds1);
		HolidayPreholidayTransferedHoliday hol8 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 5, 4), ds1);
		HolidayPreholidayTransferedHoliday hol9 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 5, 8), ds2);
		HolidayPreholidayTransferedHoliday hol10 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 5, 3), ds3);
		HolidayPreholidayTransferedHoliday hol2 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 4, 30), ds2);
		HolidayPreholidayTransferedHoliday hol3 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 6, 22), ds2);
		HolidayPreholidayTransferedHoliday hol4 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 6, 23), ds1);
		HolidayPreholidayTransferedHoliday hol5 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 6, 24), ds1);
		HolidayPreholidayTransferedHoliday hol6 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 6, 25), ds3);
		HolidayPreholidayTransferedHoliday hol7 = new HolidayPreholidayTransferedHoliday(LocalDate.of(2021, 6, 19), ds4);
		holidayRepo.save(hol1);
		holidayRepo.save(hol2);
		holidayRepo.save(hol3);
		holidayRepo.save(hol4);
		holidayRepo.save(hol5);
		holidayRepo.save(hol6);
		holidayRepo.save(hol7);
		holidayRepo.save(hol8);
		holidayRepo.save(hol9);
		holidayRepo.save(hol10);

		FinancePlanning fp1 = new FinancePlanning(p1, e1, /*YearMonth.of(2022, 1)*/ LocalDate.of(2022, 1, 1), 50, 0, 0);
		FinancePlanning fp2 = new FinancePlanning(b1, e1, /*YearMonth.of(2022, 1)*/ LocalDate.of(2022, 1, 1), 0, 0.25, 0);
		FinancePlanning fp3 = new FinancePlanning(indVuas1, e1, /*YearMonth.of(2022, 1)*/ LocalDate.of(2022, 1, 1), 0, 0.4, 0);
		FinancePlanning fp4 = new FinancePlanning(p3, e1, /*YearMonth.of(2022, 1)*/ LocalDate.of(2022, 1, 1), 10, 0, 0);
		FinancePlanning fp5 = new FinancePlanning(p1, e1, /*YearMonth.of(2022, 2)*/ LocalDate.of(2022, 2, 1), 34, 0, 0);
		FinancePlanning fp6 = new FinancePlanning(acWorkLoad1, e1, /*YearMonth.of(2022, 2)*/ LocalDate.of(2022, 2, 1), 0, 0.25, 0);
		FinancePlanning fp7 = new FinancePlanning(indVuas1, e1, /*YearMonth.of(2022, 2)*/ LocalDate.of(2022, 2, 1), 0, 0.4, 0);
		FinancePlanning fp8 = new FinancePlanning(p3, e1, /*YearMonth.of(2022, 2)*/ LocalDate.of(2022, 2, 1), 10, 0, 0);
		
		FinancePlanning fp9 = new FinancePlanning(acWorkLoad1, e4,/*YearMonth.of(2022, 1)*/ LocalDate.of(2022, 1, 1), 0, 0.4, 0);
		FinancePlanning fp10 = new FinancePlanning(p4, e4, /*YearMonth.of(2022, 1)*/ LocalDate.of(2022, 1, 1), 50, 0, 0);
		FinancePlanning fp11 = new FinancePlanning(b1, e4, /*YearMonth.of(2022, 1)*/ LocalDate.of(2022, 1, 1), 0, 0.283, 0);
		FinancePlanning fp12 = new FinancePlanning(acWorkLoad1, e4, /*YearMonth.of(2022, 2)*/ LocalDate.of(2022, 2, 1), 0, 0.2, 0);
		FinancePlanning fp13 = new FinancePlanning(p4, e4, /*YearMonth.of(2022, 2)*/ LocalDate.of(2022, 2, 1), 23, 0, 0);
		FinancePlanning fp14 = new FinancePlanning(b1, e4, /*YearMonth.of(2022, 2)*/ LocalDate.of(2022, 2, 1), 0, 0.283, 0);
		
		finPlanRepo.save(fp1);
		finPlanRepo.save(fp2);
		finPlanRepo.save(fp3);
		finPlanRepo.save(fp4);
		finPlanRepo.save(fp5);
		finPlanRepo.save(fp6);
		finPlanRepo.save(fp7);
		finPlanRepo.save(fp8);
		finPlanRepo.save(fp9);
		finPlanRepo.save(fp10);
		finPlanRepo.save(fp11);
		finPlanRepo.save(fp12);
		finPlanRepo.save(fp13);
		finPlanRepo.save(fp14);
		
//		p1.addFinancePlanningToProject(fp1);
//		b1.addFinancePlanningToBaseFin(fp2);
//		indVuas1.addFinancePlanningToIndirectVUAS(fp3);
//		p3.addFinancePlanningToProject(fp4);
//		p1.addFinancePlanningToProject(fp5);
//		acWorkLoad1.addFinancePlanningToAcademicWorkLoad(fp6);
//		indVuas1.addFinancePlanningToIndirectVUAS(fp7);
//		p3.addFinancePlanningToProject(fp8);
//		
//		acWorkLoad1.addFinancePlanningToAcademicWorkLoad(fp9);
//		p4.addFinancePlanningToProject(fp10);
//		b1.addFinancePlanningToBaseFin(fp11);
//		acWorkLoad1.addFinancePlanningToAcademicWorkLoad(fp12);
//		p4.addFinancePlanningToProject(fp13);
//		b1.addFinancePlanningToBaseFin(fp14);
		
		BasicActivitiesItems bai1 = new BasicActivitiesItems("Pētniecība");
		BasicActivitiesItems bai2 = new BasicActivitiesItems("Pētniecības metodoloģijas izstrāde");
		BasicActivitiesItems bai3 = new BasicActivitiesItems("Publikācijas sagatavošana");
		BasicActivitiesItems bai4 = new BasicActivitiesItems("Dalība konferencē");
		BasicActivitiesItems bai5 = new BasicActivitiesItems("Dalība seminārā");
		BasicActivitiesItems bai6 = new BasicActivitiesItems("Projekta vadība");
		BasicActivitiesItems bai7 = new BasicActivitiesItems("Projekta zinātniskā vadība");
		BasicActivitiesItems bai8 = new BasicActivitiesItems("Zinātnisko atskaišu sagatavošana");
		BasicActivitiesItems bai9 = new BasicActivitiesItems("Referāta gatavošana");
		BasicActivitiesItems bai10 = new BasicActivitiesItems("Skype sesija - vāju signalu datu apstrāde un metodikas uzlabojumi");
		BasicActivitiesItems bai11 = new BasicActivitiesItems("Novērojuma plānošana");
		BasicActivitiesItems bai12 = new BasicActivitiesItems("Novērojuma izpilde");
		BasicActivitiesItems bai13 = new BasicActivitiesItems("Atskaišu sagatavošana");
		BasicActivitiesItems bai14 = new BasicActivitiesItems("Datorsimulācija");
		BasicActivitiesItems bai15 = new BasicActivitiesItems("Datorprogrammu izstrāde");
		BasicActivitiesItems bai16 = new BasicActivitiesItems("Datorprogrammu testēšana");
		BasicActivitiesItems bai17 = new BasicActivitiesItems("Novērojumu datu apstrāde");
		BasicActivitiesItems bai18 = new BasicActivitiesItems("Mobilas lietotnes programmēšana");
		BasicActivitiesItems bai19 = new BasicActivitiesItems("Komandējums");
		BasicActivitiesItems bai20 = new BasicActivitiesItems("Dokumentācijas sagatavošana");
		basActItemRepo.save(bai1);
		basActItemRepo.save(bai2);
		basActItemRepo.save(bai3);
		basActItemRepo.save(bai4);
		basActItemRepo.save(bai5);
		basActItemRepo.save(bai6);
		basActItemRepo.save(bai7);
		basActItemRepo.save(bai8);
		basActItemRepo.save(bai9);
		basActItemRepo.save(bai10);
		basActItemRepo.save(bai11);
		basActItemRepo.save(bai12);
		basActItemRepo.save(bai13);
		basActItemRepo.save(bai14);
		basActItemRepo.save(bai15);
		basActItemRepo.save(bai16);
		basActItemRepo.save(bai17);
		basActItemRepo.save(bai18);
		basActItemRepo.save(bai19);
		basActItemRepo.save(bai20);
		
		BasicActivities ba1 = new BasicActivities(fod1, bai1);
		BasicActivities ba2 = new BasicActivities(fod1, bai2);
		BasicActivities ba3 = new BasicActivities(fod1, bai3);
		baseActRepo.save(ba1);
		baseActRepo.save(ba2);
		baseActRepo.save(ba3);
		
		FullTimeEquivalent fte1 = new FullTimeEquivalent(2021, 5, e1, p1);
		fte1.setVacationHours(20);
		fullTimeEquivalentRepo.save(fte1);
		
		
		HoursInMonth h1 = new HoursInMonth(2021, 5, 159);
		hoursInMonthRepo.save(h1);
		
		return(args)->{
			
		};
	}

}
