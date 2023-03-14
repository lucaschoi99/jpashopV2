package jpamarket.jpashopV2.domain.items;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
}
