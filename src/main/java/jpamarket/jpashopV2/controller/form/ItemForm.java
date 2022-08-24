package jpamarket.jpashopV2.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {

    // 기본 요소
    private Long id;
    private String name;
    private int price;
    private int amount;

    // Item 종류별 추가 요소
    private String artist; // Album

    private String author; // Book
    private String isbn;

    private String director; // Movie
    private String actor;

}
