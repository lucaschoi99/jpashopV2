package jpamarket.jpashopV2.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // Constructor - 직접 생성 금지
    protected Address() {
    }

    public Address(String city) {
        this.city = city;
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
