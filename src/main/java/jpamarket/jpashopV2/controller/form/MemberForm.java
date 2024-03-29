package jpamarket.jpashopV2.controller.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 항목입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
