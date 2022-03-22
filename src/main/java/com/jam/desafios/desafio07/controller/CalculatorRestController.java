package com.jam.desafios.desafio07.controller;

import com.jam.desafios.desafio07.dto.Operation;
import com.jam.desafios.desafio07.entity.OperationResult;
import com.jam.desafios.desafio07.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculator")
public class CalculatorRestController {

    private final CalculatorService calculatorService;

    public CalculatorRestController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public OperationResult doOperation(@RequestBody Operation operation) {

        return this.calculatorService.save(operation);
    }

    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void validateOperation(@RequestBody OperationResult operation) {

        this.calculatorService.validate(operation);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OperationResult> getAll() {

        return calculatorService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationResult getById(@PathVariable Long id) {

        return calculatorService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperationResult update(@PathVariable Long id, @RequestBody Operation operation) {

        return calculatorService.update(id, operation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {

        calculatorService.delete(id);
    }
}
