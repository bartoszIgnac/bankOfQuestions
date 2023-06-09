package pl.ignacbartosz.bank.question.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.ignacbartosz.bank.common.dto.StatisticsDto;
import pl.ignacbartosz.bank.question.domain.model.Question;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findAllByCategoryId(UUID id, Pageable pageable);


    @Query("from Question q where q.answers.size = 0")
    Page<Question> findUnanswered(Pageable pageable);


    @Query(
            value = "select * from questions q where upper(q.name) like upper('%' || :query || '%') ",
            countQuery = "select count(*) from questions q where upper(q.name) like upper('%' || :query || '%') ",
            nativeQuery = true
    )
    Page<Question> findByQuery(String query, Pageable pageable);

    @Query(value = "select * from questions q order by random() limit :limit", nativeQuery = true)
    List<Question> findRandomQuestions(int limit);

    @Query(value = "select new pl.ignacbartosz.bank.common.dto.StatisticsDto(count(q), count(a)) from Question q join q.answers a")
    StatisticsDto statistics();


}
