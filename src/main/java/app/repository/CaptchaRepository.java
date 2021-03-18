package app.repository;

import app.model.CaptchaItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vasil
 */
@Repository
public interface CaptchaRepository extends CrudRepository<CaptchaItem, String> {

}
