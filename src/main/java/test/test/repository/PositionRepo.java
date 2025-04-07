package test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.test.entity.Position;

@Repository
public interface PositionRepo extends JpaRepository<Position, Integer> {
}
