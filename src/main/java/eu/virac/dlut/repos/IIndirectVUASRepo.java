package eu.virac.dlut.repos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.IndirectVUAS;
import org.springframework.data.repository.query.Param;

public interface IIndirectVUASRepo extends CrudRepository<IndirectVUAS, Integer> {

    @Query(value = "CALL GetIndirectVuasByEmployeeAndDate(:year, :month, :employeeId);", nativeQuery = true)
    ArrayList<IndirectVUAS> findByDateAndEmployee(@Param("year") int year,
                                                  @Param("month") int month,
                                                  @Param("employeeId") int employeeId);


}
