package pl.ignacbartosz.bank.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ignacbartosz.bank.BankConfiguration;
import pl.ignacbartosz.bank.question.domain.model.Question;
import pl.ignacbartosz.bank.question.service.QuestionService;

import static pl.ignacbartosz.bank.common.controller.ControllerUtils.paging;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchViewController extends BankCommonViewController {

    private final QuestionService questionService;
    private final BankConfiguration bankConfiguration;

    @GetMapping
    public String searchView(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, bankConfiguration.getPagingPageSize());

        if (query != null) {
            Page<Question> questionsPage = questionService.findByQuery(query, pageRequest);

            model.addAttribute("questionsPage", questionsPage);
            model.addAttribute("query", query);

            paging(model, questionsPage);
        }

        addGlobalAttributes(model);

        return "search/index";
    }

}
