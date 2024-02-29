package eu.virac.dlut.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.OrderMission;
import org.springframework.data.repository.query.Param;


public interface IOrderMissionRepo extends CrudRepository<OrderMission, Integer> {

    @Query(value = "CALL FindByDateAndOrderWorkMissionIDAndDesignation(:day, :month, :year, :employeeId);", nativeQuery = true)
    OrderMission findByDateAndOrderWorkMissionIDAndDesignation(@Param("day") int day,
                                                               @Param("month") int month,
                                                               @Param("year") int year,
                                                               @Param("employeeId") int employeeId);


}
