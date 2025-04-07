package test.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import test.test.entity.WorkingHour;
import test.test.repository.WorkingHoursRepo;

@Service
@RequiredArgsConstructor
public class WorkingHoursServiceImpl implements WorkingHoursService {

    private final WorkingHoursRepo repo;

    public List<WorkingHour> getAll() {
        return repo.findAll();
    }

    public WorkingHour getById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    public WorkingHour create(WorkingHour wh) {
        return repo.save(wh);
    }

    public WorkingHour update(Integer id, WorkingHour wh) {
        WorkingHour old = getById(id);
        old.setCheckInTime(wh.getCheckInTime());
        old.setCheckOutTime(wh.getCheckOutTime());
        old.setWorkDate(wh.getWorkDate());
        old.setUser(wh.getUser());
        return repo.save(old);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
