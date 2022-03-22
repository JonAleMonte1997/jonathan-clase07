package com.jam.desafios.desafio07.entity;

import com.jam.desafios.desafio07.enums.Operator;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "OPERATION_RESULTS")
public class OperationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPERATION_ID")
    private Long id;

    @Column(name = "OPERATION_LEFT")
    private Double left;

    @Column(name = "OPERATION_OPERATOR")
    @Enumerated(EnumType.STRING)
    private Operator operator;

    @Column(name = "OPERATION_RIGHT")
    private Double right;

    @Column(name = "OPERATION_RESULT")
    private Double result;

    public OperationResult(Double left, Operator operator, Double right, Double result) {
        this.left = left;
        this.operator = operator;
        this.right = right;
        this.result = result;
    }
}
