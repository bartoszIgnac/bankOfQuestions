package pl.ignacbartosz.bank.category.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ignacbartosz.bank.category.domain.model.Category;
import pl.ignacbartosz.bank.category.service.CategoryService;
import pl.ignacbartosz.bank.common.controller.BankCommonViewController;
import pl.ignacbartosz.bank.question.domain.model.Question;
import pl.ignacbartosz.bank.question.service.QuestionService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryViewController extends BankCommonViewController {


    private final CategoryService categoryService;
    private final QuestionService questionService;


    @GetMapping("{id}")
    public String singleView(@PathVariable UUID id, Model model) {
        Category category = categoryService.getCategory(id);
        List<Question> questions = questionService.findAllByCategoryId(id);

        model.addAttribute("category", category);
        model.addAttribute("questions", questions);
        addGlobalAttributes(model);

        return "category/single";
    }


}

