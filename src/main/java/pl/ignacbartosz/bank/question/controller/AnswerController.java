package pl.ignacbartosz.bank.question.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.ignacbartosz.bank.question.domain.model.Answer;
import pl.ignacbartosz.bank.question.service.AnswerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/questions/{question-id}/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    List<Answer> getAnswers(@PathVariable("question-id") UUID questionID) {
        return answerService.getAnswers(questionID);
    }

    @GetMapping("{answer-id}")
    Answer getAnswer(@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerID) {
        return answerService.getAnswer(answerID);
    }

    @PostMapping
    Answer createAnswer(@PathVariable("question-id") UUID questionId, @RequestBody Answer answer) {
        return answerService.createAnswer(questionId, answer);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{answer-id}")
    Answer updateAnswer(@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerID, @RequestBody Answer answer) {
        return answerService.updateAnswer(answerID, answer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{answer-id}")
    void deleteAnswer(@PathVariable("answer-id") UUID answerID) {
        answerService.deleteAnswer(answerID);
    }

}
