package com.jam.desafios.desafio07.service;

import com.jam.desafios.desafio07.dto.Operation;
import com.jam.desafios.desafio07.entity.OperationResult;
import com.jam.desafios.desafio07.enums.Operator;
import com.jam.desafios.desafio07.exceptions.InvalidMathematicalOperationResultException;
import com.jam.desafios.desafio07.exceptions.MathematicalOperationNotSupportedException;
import com.jam.desafios.desafio07.exceptions.MathematicalOperationNotValidException;
import com.jam.desafios.desafio07.exceptions.OperationNotFoundException;
import com.jam.desafios.desafio07.repository.OperationResultRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @InjectMocks
    CalculatorService calculatorService;

    @Mock
    OperationResultRepository operationResultRepository;

    @Test
    void calculateAdd() {

        Operation operationIncoming = new Operation(10.0, Operator.ADD, 10.0);

        OperationResult operationResultExpected = new OperationResult(10.0, Operator.ADD, 10.0, 20.0);

        OperationResult result = calculatorService.calculate(operationIncoming);


        assertThat(result).isEqualTo(operationResultExpected);
    }

    @Test
    void calculateDifference() {

        Operation operationIncoming = new Operation(10.0, Operator.DIFFERENCE, 10.0);

        OperationResult operationResultExpected = new OperationResult(10.0, Operator.DIFFERENCE, 10.0, 0.0);

        OperationResult result = calculatorService.calculate(operationIncoming);


        assertThat(result).isEqualTo(operationResultExpected);
    }

    @Test
    void calculateMultiply() {

        Operation operationIncoming = new Operation(10.0, Operator.MULTIPLY, 10.0);

        OperationResult operationResultExpected = new OperationResult(10.0, Operator.MULTIPLY, 10.0, 100.0);

        OperationResult result = calculatorService.calculate(operationIncoming);


        assertThat(result).isEqualTo(operationResultExpected);
    }

    @Test
    void calculateDivide() {

        Operation operationIncoming = new Operation(10.0, Operator.DIVIDE, 10.0);

        OperationResult operationResultExpected = new OperationResult(10.0, Operator.DIVIDE, 10.0, 1.0);

        OperationResult result = calculatorService.calculate(operationIncoming);


        assertThat(result).isEqualTo(operationResultExpected);
    }

    @Test
    void calculateDivideByZero() {

        Operation operationIncoming = new Operation(10.0, Operator.DIVIDE, 0.0);


        assertThrows(MathematicalOperationNotValidException.class, () -> calculatorService.calculate(operationIncoming));
    }

    @Test
    void calculateOperationNotSupported() {

        Operation operationIncoming = new Operation(10.0, Operator.POW, 2.0);


        assertThrows(MathematicalOperationNotSupportedException.class, () -> calculatorService.calculate(operationIncoming));
    }

    @Test
    void validate() {

        OperationResult operationResultIncoming = new OperationResult(10.0, Operator.ADD, 10.0, 20.0);


        calculatorService.validate(operationResultIncoming);
    }

    @Test
    void validateThrows() {

        OperationResult operationResultIncoming = new OperationResult(10.0, Operator.ADD, 10.0, 19.0);


        assertThrows(InvalidMathematicalOperationResultException.class, () -> calculatorService.validate(operationResultIncoming));
    }

    @Test
    void save() {

        Operation operationIncoming = new Operation(10.0, Operator.ADD, 10.0);

        OperationResult operationResultPersisted = new OperationResult(10.0, Operator.ADD, 10.0, 20.0);


        Mockito.when(operationResultRepository.save(operationResultPersisted)).thenReturn(operationResultPersisted);

        assertNotNull(calculatorService.save(operationIncoming));
    }

    @Test
    void findAll() {

        List<OperationResult> operationResultListPersisted = new ArrayList<>();

        OperationResult operationResultPersisted = new OperationResult(10.0, Operator.ADD, 10.0, 20.0);

        operationResultPersisted.setId(1L);
        operationResultListPersisted.add(operationResultPersisted);


        Mockito.when(operationResultRepository.findAll()).thenReturn(operationResultListPersisted);

        assertNotNull(calculatorService.findAll());
    }

    @Test
    void findById() {

        OperationResult operationResultPersisted = new OperationResult(10.0, Operator.ADD, 10.0, 20.0);
        operationResultPersisted.setId(1L);


        Mockito.when(operationResultRepository.findById(1L)).thenReturn(Optional.of(operationResultPersisted));

        assertThat(calculatorService.findById(1L)).isEqualTo(operationResultPersisted);
    }

    @Test
    void findByIdThrow() {

        Mockito.when(operationResultRepository.findById(1L)).thenThrow(OperationNotFoundException.class);


        assertThrows(OperationNotFoundException.class, () -> calculatorService.findById(1L));
    }

    @Test
    void delete() {

        OperationResult operationResultPersisted = new OperationResult(10.0, Operator.ADD, 10.0, 20.0);
        operationResultPersisted.setId(1L);


        Mockito.when(operationResultRepository.findById(1L)).thenReturn(Optional.of(operationResultPersisted));

        calculatorService.delete(1L);
    }

    @Test
    void update() {

        OperationResult operationResultPersisted = new OperationResult(10.0, Operator.ADD, 10.0, 20.0);

        Operation operationIncoming = new Operation(10.0, Operator.DIFFERENCE, 10.0);

        OperationResult operationResultPersistedUpdated = new OperationResult(10.0, Operator.DIFFERENCE, 10.0, 0.0);


        Mockito.when(operationResultRepository.findById(1L)).thenReturn(Optional.of(operationResultPersisted));

        Mockito.when(operationResultRepository.save(operationResultPersistedUpdated)).thenReturn(operationResultPersistedUpdated);

        assertThat(calculatorService.update(1L, operationIncoming)).isEqualTo(operationResultPersistedUpdated);
    }
}