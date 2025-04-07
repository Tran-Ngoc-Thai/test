package test.test.service;

import java.util.List;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import test.test.entity.Attendance;
import test.test.repository.AttendanceRepo;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepo repo;

    public List<Attendance> getAll() {
        return repo.findAll();
    }

    public Attendance getById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    public Attendance create(Attendance attendance) {
        return repo.save(attendance);
    }

    public Attendance update(Integer id, Attendance updated) {
        return repo.findById(id).map(att -> {
            att.setWorkDate(updated.getWorkDate());
            att.setStatus(updated.getStatus());
            att.setUser(updated.getUser());
            return repo.save(att);
        }).orElseThrow();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
