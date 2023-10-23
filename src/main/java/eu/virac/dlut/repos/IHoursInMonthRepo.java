package eu.virac.dlut.repos;

import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.HoursInMonth;

public interface IHoursInMonthRepo extends CrudRepository<HoursInMonth, Integer>{

	HoursInMonth findByYearAndMonthAndHoursInMonth(int year, int month, double hours);

	HoursInMonth findByYearAndMonth(int year, int month);

}
