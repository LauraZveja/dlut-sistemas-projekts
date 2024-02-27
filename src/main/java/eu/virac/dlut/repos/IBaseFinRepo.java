package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.BaseFin;
import org.springframework.data.repository.query.Param;

public interface IBaseFinRepo extends CrudRepository<BaseFin, Integer> {

    @Query(value = "CALL getBaseFinForEmployee(:yearParam, :monthParam, :employeeIdParam);", nativeQuery = true)
    ArrayList<BaseFin> findByDateAndEmployee(@Param("yearParam") int year,
                                             @Param("monthParam") int month,
                                             @Param("employeeIdParam") int employeeId);

}
