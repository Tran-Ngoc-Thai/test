package test.test.service;

import java.util.List;
import java.util.Optional;

import test.test.entity.Salary;

public interface SalaryService {
    List<Salary> getAll();
    Optional<Salary> getById(Integer id);
    Salary create(Salary salary);
    Salary update(Integer id, Salary salary);
    void delete(Integer id);
}
