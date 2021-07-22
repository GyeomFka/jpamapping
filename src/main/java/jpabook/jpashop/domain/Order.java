package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") //DB마다 다른데 order by 예약어때문에 orders로 변환했다.
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    //********************************************************************************
    /*
     * -> 객체 < 관계형 DB에 맞춘 설계
     * */
//    @Column(name="MEMBER_ID")
//    private Long memberId; //누가 주문했는지의 MemberId <-> Member
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

//    private Member member; //-> 객체지향적인 설계
    //********************************************************************************

    private LocalDateTime orderDate; //ORDER_DATE, order_date ... ->Spring Boot에서는 기본 설정을 ORDER_DATE로 가지고간다 Java의 camelCase를 읽어서 uner_score로 가져간다.

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
