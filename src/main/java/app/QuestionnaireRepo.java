package app;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface QuestionnaireRepo extends CrudRepository<Questionnaire, Long> {
    Questionnaire findById(long id);
    Questionnaire findByEmail(String email);
}
