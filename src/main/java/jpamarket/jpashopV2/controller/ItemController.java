package jpamarket.jpashopV2.controller;

import jpamarket.jpashopV2.controller.form.ItemForm;
import jpamarket.jpashopV2.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String registerItem(Model model) {
        model.addAttribute("form", new ItemForm());
        return "items/createItemForm";
    }
}
