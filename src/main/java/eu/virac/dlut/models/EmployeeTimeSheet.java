package eu.virac.dlut.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 *Darbinieka stundas konkrētā mēnesī konkrētā finansējuma avotā. 
 *Sastāv no: gads/mēnesis/diena, 
 *atbilstošā darbinieka, kas sasaistīts ar finansējuma avotu un darba rīkojumu,
 *nostrādātajām stundām konkrētā dienā konkrētā finansējuma avotā, veiktās aktivitātes,
 *atbilstošā rīkojuma (komandējuma, atvaļinajuma, cits), 
 *var būt arī darbnespējas lapa, kam nav rīkojuma, un vai ir rediģējams attiecīgajam darbiniekam.
 *(ievādītājs nāks vēlāk no sesijas)
 *
 *Tiek izveidota attiecīgā tabula DB.
 */

@Table
@Entity(name = "EmployeeTimeSheet")
@Getter @Setter @NoArgsConstructor @ToString
public class EmployeeTimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idTimeSheet")
	private int idTimeSheet;
	
	@Column(name = "YearMonthDay")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private LocalDate yearMonthDay;
	
	@ManyToOne
	@JoinColumn(name = "idFinOrdWork")
	private FinanceOrderWork finOrdWork; //darbinieks no tā
	
	@Column(name = "hoursWorked")
	private double hoursWorkedDayInFinSource;
	
	@Column(name = "Activities")
	private String activities;
	
	//saite ar komand. rīkojumu
	@ManyToOne
	@JoinColumn(name = "idOrdMission")
	private OrderMission ordMission;
	
	//saite ar atvaļ. rīkojumu
	@ManyToOne
	@JoinColumn(name = "idOrdVacation")
	private OrderVacation ordVacation;
	
	//saite ar citu rīkojumu
	@ManyToOne
	@JoinColumn(name = "idOrdOther")
	private OrderOther ordOther;
	
	@Column(name = "SickLeave")
	private String sickLeave;
	
	@Column(name= "Remarks") // piezīmes, kur diena kā atpūtas diena vai N
	private String remarks;
	
	@NotNull
	@Column(name = "IsEditableForEmployee")
	private boolean isEditableForEmployee;
	
	@Column(name = "InputBy")
	private String inputBy; // nāks no sesijas

	//ar komandējuma rīkojumu
	public EmployeeTimeSheet(LocalDate yearMonthDay, FinanceOrderWork finOrdWork, double hoursWorkedDayInFinSource,
			String activities, OrderMission ordMission, boolean isEditableForEmployee) {
		this.yearMonthDay = yearMonthDay;
		this.finOrdWork = finOrdWork;
		this.hoursWorkedDayInFinSource = hoursWorkedDayInFinSource;
		this.activities = activities;
		this.ordMission = ordMission;
		this.isEditableForEmployee = isEditableForEmployee;
	}
	
	//ar atvaļinājuma rīkojumu
	public EmployeeTimeSheet(LocalDate yearMonthDay, FinanceOrderWork finOrdWork, double hoursWorkedDayInFinSource,
			String activities, OrderVacation ordVacation, boolean isEditableForEmployee) {
		this.yearMonthDay = yearMonthDay;
		this.finOrdWork = finOrdWork;
		this.hoursWorkedDayInFinSource = hoursWorkedDayInFinSource;
		this.activities = activities;
		this.ordVacation = ordVacation;
		this.isEditableForEmployee = isEditableForEmployee;
	}
	
	//ar citu rīkojumu
	public EmployeeTimeSheet(LocalDate yearMonthDay, FinanceOrderWork finOrdWork, double hoursWorkedDayInFinSource,
			String activities, OrderOther ordOther, boolean isEditableForEmployee) {
		this.yearMonthDay = yearMonthDay;
		this.finOrdWork = finOrdWork;
		this.hoursWorkedDayInFinSource = hoursWorkedDayInFinSource;
		this.activities = activities;
		this.ordOther = ordOther;
		this.isEditableForEmployee = isEditableForEmployee;
	}
	
	//bez rīkojuma un bez darbnespējas lapas
	public EmployeeTimeSheet(LocalDate yearMonthDay, FinanceOrderWork finOrdWork, double hoursWorkedDayInFinSource,
			String activities, boolean isEditableForEmployee) {
		this.yearMonthDay = yearMonthDay;
		this.finOrdWork = finOrdWork;
		this.hoursWorkedDayInFinSource = hoursWorkedDayInFinSource;
		this.activities = activities;
		this.isEditableForEmployee = isEditableForEmployee;
	}
	
	//ar darbnespējas lapu
	public EmployeeTimeSheet(LocalDate yearMonthDay, FinanceOrderWork finOrdWork, double hoursWorkedDayInFinSource,
			String activities, String sickLeave, boolean isEditableForEmployee) {
		this.yearMonthDay = yearMonthDay;
		this.finOrdWork = finOrdWork;
		this.hoursWorkedDayInFinSource = hoursWorkedDayInFinSource;
		this.activities = activities;
		this.sickLeave = sickLeave;
		this.isEditableForEmployee = isEditableForEmployee;
	}
	
}
