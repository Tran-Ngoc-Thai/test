package test.test.service;

import java.util.List;

import test.test.entity.WorkingHour;

public interface WorkingHoursService {
    List<WorkingHour> getAll();
    WorkingHour getById(Integer id);
    WorkingHour create(WorkingHour workingHour);
    WorkingHour update(Integer id, WorkingHour workingHour);
    void delete(Integer id);
}
