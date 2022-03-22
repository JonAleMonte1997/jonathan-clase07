package com.jam.desafios.desafio07.dto;

import com.jam.desafios.desafio07.enums.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    private Double left;

    private Operator operator;

    private Double right;
}
