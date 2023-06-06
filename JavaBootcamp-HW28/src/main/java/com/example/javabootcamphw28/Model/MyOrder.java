package com.example.javabootcamphw28.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//
//    @NotNull(message = "quantity can't be empty")
//    @Column(columnDefinition = "int not null")
    private Integer quantity;

//    @NotNull(message = "price can't be empty")
//    @Column(columnDefinition = "int not null")
    private Integer totalPrice;

//    @NotEmpty(message = "date can't be empty")
    private String dateReceived;

//    @NotEmpty(message = "")
//    @Column(columnDefinition = "varchar(20) check(status='new' or status='inProgress' or status='completed')")
     @Pattern(regexp = "\\b(?:new|inProgress|completed)\\b",message = "status Not Valid")
    @Column(columnDefinition = "varchar(10) check(status='new' or status='inProgress' or status='completed')")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}
