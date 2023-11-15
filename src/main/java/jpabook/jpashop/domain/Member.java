package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //Address 클래스의 Embeddable둘중에 하나만있어야하는데 편의상 2군데 다하겠다.
    private Address address;

    @OneToMany(mappedBy = "member") //order테이블에있는 member변수 //이렇게 설정하면 여기에 값을 넣어도 fk값이 변하지않음 //Order의 member의 거울이됨
    private List<Order> orders = new ArrayList<>(); //컬렉션은 필드에서 바로 초기화하고 손대지말자

}
