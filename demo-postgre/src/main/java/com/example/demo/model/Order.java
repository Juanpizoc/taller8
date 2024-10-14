package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.ForeignKey;



@Table(name = "order_table")
@Entity(name = "Order")
@NoArgsConstructor
@Getter
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    @Column
    private Long id;

    @Column(
            nullable = false
    )
    private int totalPrice;
    
    

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "order_customer_fk"
            )
    )
    private Customer customer;
    
    
    @OneToMany(
            orphanRemoval = true,
            mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<OrderItem> orderItems;
    
    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            nullable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    @CreatedDate
    private LocalDateTime createdAt;
}
