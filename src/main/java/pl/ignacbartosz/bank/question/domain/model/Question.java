package pl.ignacbartosz.bank.question.domain.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.ignacbartosz.bank.category.domain.model.Category;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString
public class Question {

    @Id
    private UUID id;
    private String name;

    @ManyToOne
    private Category category;


    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    private LocalDateTime created;

    private LocalDateTime modified;

    public Question() {
        this.id = UUID.randomUUID();
    }

    public Question(String name) {
        this();
        this.name = name;
    }

    @PrePersist
    void prePersist() {
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        modified = LocalDateTime.now();
    }

    public Set<Answer> getAnswers() {
        return Collections.unmodifiableSet(answers);
    }

    public Question addAnswer(Answer answer) {
        if (answers == null) {
            answers = new LinkedHashSet<>();
        }

        answer.setQuestion(this);
        answers.add(answer);

        return this;
    }

}
