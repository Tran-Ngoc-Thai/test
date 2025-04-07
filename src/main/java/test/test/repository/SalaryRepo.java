package test.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.test.entity.Salary;
import test.test.entity.Users;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, Integer> {

    Optional<Salary> findByUser(Users user);

}
