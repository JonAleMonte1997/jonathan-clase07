package com.jam.desafios.desafio07.service;

import com.jam.desafios.desafio07.dto.Operation;
import com.jam.desafios.desafio07.entity.OperationResult;
import com.jam.desafios.desafio07.enums.Operator;
import com.jam.desafios.desafio07.exceptions.InvalidMathematicalOperationResultException;
import com.jam.desafios.desafio07.exceptions.MathematicalOperationNotSupportedException;
import com.jam.desafios.desafio07.exceptions.MathematicalOperationNotValidException;
import com.jam.desafios.desafio07.exceptions.OperationNotFoundException;
import com.jam.desafios.desafio07.repository.OperationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

    @Autowired
    OperationResultRepository operationResultRepository;

    public OperationResult save(Operation operation)  {

        return operationResultRepository.save(calculate(operation));
    }

    public OperationResult calculate(Operation operation) {

        if (Operator.ADD.equals(operation.getOperator())) {

            return this.add(operation.getLeft(), operation.getRight());

        } else if (Operator.DIFFERENCE.equals(operation.getOperator())) {

            return this.difference(operation.getLeft(), operation.getRight());

        } else if (Operator.MULTIPLY.equals(operation.getOperator())) {

            return this.multiply(operation.getLeft(), operation.getRight());

        } else if (Operator.DIVIDE.equals(operation.getOperator())) {

            if (operation.getRight() == 0.0) {

                throw new MathematicalOperationNotValidException("Trying to perform division against 0");
            }
            return this.divide(operation.getLeft(), operation.getRight());
        }
        throw new MathematicalOperationNotSupportedException("We only support ADD/DIFFERENCE/MULTIPLY/DIVIDE operations");
    }

    private OperationResult divide(Double left, Double right) {

        return new OperationResult(left, Operator.DIVIDE, right, left / right);
    }

    private OperationResult difference(Double left, Double right) {

        return new OperationResult(left, Operator.DIFFERENCE, right, left - right);
    }

    private OperationResult add(Double left, Double right) {

        return new OperationResult(left, Operator.ADD, right, left + right);
    }

    private OperationResult multiply(Double left, Double right) {

        return new OperationResult(left, Operator.MULTIPLY, right, left * right);
    }

    public void validate(OperationResult operation) {

        Operation previousOperation = new Operation(operation.getLeft(), operation.getOperator(), operation.getRight());

        Double realResult = this.calculate(previousOperation).getResult();

        if (!operation.getResult().equals(realResult)) {
            throw new InvalidMathematicalOperationResultException(String.format("We were expecting as a result %f, but you gave to us %f", realResult, operation.getResult()));
        }
    }

    public List<OperationResult> findAll() {

        return operationResultRepository.findAll();
    }

    public OperationResult findById(Long id) {

        return operationResultRepository.findById(id).orElseThrow(() -> new OperationNotFoundException("OperationResult not found"));
    }

    public void delete(Long id) {

        operationResultRepository.delete(findById(id));
    }

    public OperationResult update(Long id, Operation operation) {

        OperationResult operationResultToUpdate = findById(id);

        operationResultToUpdate.setOperator(operation.getOperator());

        operationResultToUpdate.setRight(operation.getRight());

        operationResultToUpdate.setLeft(operation.getLeft());

        operationResultToUpdate.setResult(calculate(operation).getResult());

        return operationResultRepository.save(operationResultToUpdate);
    }
}
