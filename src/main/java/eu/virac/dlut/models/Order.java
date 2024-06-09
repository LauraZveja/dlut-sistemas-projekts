package eu.virac.dlut.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * Rīkojuma mātes klase. 
 * Sastāv no rīkojuma numura, datuma, darbinieka (viens(darbinieks) pret daudzi(rīkojums) saite)
 * sākuma laika (diena, mēnesis, gads) un beigu laika (diena, mēnesis, gads). 
 * (Nepieciešamības gadījumā jādomā par nosaukuma pievienošanu)
 * Ir divi argumentu konstruktori - bez beigu datuma un ar to.
 * 
 * pievienots arii atribuuts - designation. Kas cita starpaa var buut "IA" (ikgadeejais atvalinaajums)
 * 
 * Tiek izveidota attiecīgā tabula DB. Pārmantošanai izvēlēta anotācija Inheritance, 
 * jo šajā tabulā būs kolonna, kas nāks no darbinieka.
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table
@Entity(name = "OrderTable")
@Getter @Setter @NoArgsConstructor @ToString
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	@Column(name = "idOrder")
	private int idOrder;
	
	@Column(name = "OrderNo")
	//@NotNull
	private String orderNo;
	
	@Column(name = "Date")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private LocalDate date;
	
	@Column(name = "StartDate")
	//@NotNull
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private LocalDate startDate;
	
	@Column(name = "EndDate")
	@DateTimeFormat(pattern = "dd.MM.yyyy.")
	private LocalDate endDate;
	
	@Column(name = "designation")
	private String designation;
	
	//saite ar darbinieku
	@ManyToOne
	@JoinColumn(name = "idEmployeeOrder", referencedColumnName = "idEmployee"/*, nullable = false*/)
	private Employee employee;
	
	//private String orderTitle;

	//bez beigu datuma
	public Order(String orderNo, LocalDate date, LocalDate startDate, Employee employee) {
		this.orderNo = orderNo;
		this.date = date;
		this.startDate = startDate;
		this.employee = employee;
	}

	//ar beigu datumu
	public Order(String orderNo, LocalDate date, LocalDate startDate, LocalDate endDate, Employee employee) {
		this.orderNo = orderNo;
		this.date = date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employee = employee;
	}
	
	

}
