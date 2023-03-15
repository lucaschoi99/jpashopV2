package jpamarket.jpashopV2.controller;

import jakarta.validation.Valid;
import jpamarket.jpashopV2.controller.form.ItemForm;
import jpamarket.jpashopV2.domain.Category;
import jpamarket.jpashopV2.domain.Item;
import jpamarket.jpashopV2.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String registerItem(Model model) {
        model.addAttribute("form", new ItemForm());
        return "items/createItemForm";
    }

    @PostMapping(value = "items/new")
    public String postRegisterItem(@Valid ItemForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "items/createItemForm";
        }
        Item item = Item.builder()
                .category(form.getCategory())
                .name(form.getName())
                .price(form.getPrice())
                .amount(form.getAmount())
                .build();
        log.info(">>> Item Category={}", item.getCategory());

        itemService.saveItem(item);
        return "redirect:/";
    }

}
