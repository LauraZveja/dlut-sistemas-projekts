package eu.virac.dlut.repos;

import org.springframework.data.repository.CrudRepository;

import eu.virac.dlut.models.UserToken;

public interface IUserTokenRepo extends CrudRepository<UserToken, Integer> {

    UserToken findByToken(String token);

    boolean existsByDn(String userDn);

    UserToken findByDn(String userDn);

}
