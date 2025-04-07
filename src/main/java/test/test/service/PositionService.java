package test.test.service;

import java.util.List;

import test.test.entity.Position;

public interface PositionService {
    List<Position> getAll();
    Position getById(Integer id);
    Position create(Position p );
    Position update(Integer id, Position p);
    void delete(Integer id);
}
