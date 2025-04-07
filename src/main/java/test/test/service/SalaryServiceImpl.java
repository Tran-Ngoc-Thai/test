package test.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import test.test.entity.Salary;
import test.test.repository.SalaryRepo;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryRepo salaryRepository;

    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    public Optional<Salary> getById(Integer id) {
        return salaryRepository.findById(id);
    }

    public Salary create(Salary salary) {
        return salaryRepository.save(salary);
    }

    public Salary update(Integer id, Salary updated) {
        return salaryRepository.findById(id).map(s -> {
            s.setBasicSalary(updated.getBasicSalary());
            s.setBonus(updated.getBonus());
            s.setDeductions(updated.getDeductions());
            s.setTotalSalary(updated.getTotalSalary());
            s.setUser(updated.getUser());
            return salaryRepository.save(s);
        }).orElseThrow();
    }

    public void delete(Integer id) {
        salaryRepository.deleteById(id);
    }

}
