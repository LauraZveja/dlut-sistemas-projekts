package eu.virac.dlut.repos;

import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.FinanceSource;

public interface IFinanceSourceRepo extends CrudRepository<FinanceSource, Integer> {

}
