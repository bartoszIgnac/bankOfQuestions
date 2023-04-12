package pl.ignacbartosz.bank.question.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ignacbartosz.bank.BankConfiguration;
import pl.ignacbartosz.bank.category.service.CategoryService;
import pl.ignacbartosz.bank.common.controller.BankCommonViewController;
import pl.ignacbartosz.bank.question.domain.model.Question;
import pl.ignacbartosz.bank.question.service.AnswerService;
import pl.ignacbartosz.bank.question.service.QuestionService;

import java.util.UUID;

import static pl.ignacbartosz.bank.common.controller.ControllerUtils.paging;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionViewController extends BankCommonViewController {

    private final QuestionService questionsService;
    private final AnswerService answerService;
    private final CategoryService categoryService;
    private final BankConfiguration bankConfiguration;


    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("questions", questionsService.getQuestions());
        addGlobalAttributes(model);
        return "question/index";
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id) {
        model.addAttribute("question", questionsService.getQuestion(id));
        model.addAttribute("answers", answerService.getAnswers(id));
        addGlobalAttributes(model);
        return "question/single";
    }

    @GetMapping("add")
    public String addView(Model model) {
        model.addAttribute("question", new Question());

        return "question/add";
    }

    @PostMapping
    public String add(Question question) {
        questionsService.createQuestion(question);

        return "redirect:/questions";
    }

    @GetMapping("hot")
    public String hotView(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, bankConfiguration.getPagingPageSize());

        Page<Question> questionsPage = questionsService.findHot(pageRequest);

        model.addAttribute("questionsPage", questionsPage);
        paging(model, questionsPage);
        addGlobalAttributes(model);

        return "question/index";
    }

    @GetMapping("unanswered")
    public String unansweredView(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, bankConfiguration.getPagingPageSize());

        Page<Question> questionsPage = questionsService.findUnanswered(pageRequest);

        model.addAttribute("questionsPage", questionsPage);
        paging(model, questionsPage);
        addGlobalAttributes(model);

        return "question/index";
    }


}
