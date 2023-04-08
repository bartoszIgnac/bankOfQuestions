package pl.ignacbartosz.bank.question.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ignacbartosz.bank.question.domain.model.Answer;
import pl.ignacbartosz.bank.question.domain.model.Question;
import pl.ignacbartosz.bank.question.domain.repository.AnswerRepository;
import pl.ignacbartosz.bank.question.domain.repository.QuestionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional(readOnly = true)
    public List<Answer> getAnswers(UUID questionID) {
        return answerRepository.findByQuestionId(questionID);
    }

    @Transactional(readOnly = true)
    public Answer getAnswer(UUID id) {
        return answerRepository.getById(id);
    }

    @Transactional
    public Answer createAnswer(UUID questionID, Answer answerRequest) {
        Answer answer = new Answer();
        answer.setName(answerRequest.getName());
        Question question = questionRepository.getById(questionID);
        question.addAnswer(answer);

        answerRepository.save(answer);
        questionRepository.save(question);

        return answer;
    }

    @Transactional
    public Answer updateAnswer(UUID answerID, Answer answerRequest) {
        Answer answer = answerRepository.getById(answerID);
        answer.setName(answerRequest.getName());
        return answerRepository.save(answer);
    }

    @Transactional
    public void deleteAnswer(UUID answerID) {
        answerRepository.deleteById(answerID);
    }
}
