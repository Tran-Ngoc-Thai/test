package test.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import test.test.entity.Position;
import test.test.repository.PositionRepo;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepo repo;

    public List<Position> getAll() {
        return repo.findAll();
    }

    public Position getById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    public Position create(Position p) {
        return repo.save(p);
    }

    public Position update(Integer id, Position p) {
        Position old = getById(id);
        old.setPositionName(p.getPositionName());
        old.setDescription(p.getDescription());
        return repo.save(old);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}