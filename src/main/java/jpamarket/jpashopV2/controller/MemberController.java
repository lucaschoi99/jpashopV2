package jpamarket.jpashopV2.controller;

import jpamarket.jpashopV2.controller.form.MemberForm;
import jpamarket.jpashopV2.domain.Address;
import jpamarket.jpashopV2.domain.Member;
import jpamarket.jpashopV2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "members/join")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "members/join")
    public String postForm(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member(form.getName(), address);

        memberService.join(member);

        return "redirect:/";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalStateException(IllegalStateException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e);
        return "redirect:/members/join/error";
    }

    @GetMapping(value = "members/join/error")
    public String errorPage(@ModelAttribute("errorMessage") ) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleIllegalStateException(IllegalStateException exception) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    protected ModelAndView handleIllegalStateException(HttpServletRequest request, IllegalStateException e) {
//        ModelAndView mav = new ModelAndView("/exceptions/IllegalStateException");
//        mav.addObject("exception", e);
//        mav.addObject("url", request.getRequestURI());
//        return mav;
//    }

}
