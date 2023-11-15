package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "dtype") //구분이 될때 book이면 어떻게 작동할지
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한 테이블에 item book album movie의 속성을 다 넣는 방법
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    //name price stockquantity는 Book Album Movie의 공통 속성이다
    private String name;
    private int price;
    private int stockQuantity;
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();




}
