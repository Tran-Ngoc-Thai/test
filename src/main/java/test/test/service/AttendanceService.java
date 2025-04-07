package test.test.service;

import java.util.List;


import test.test.entity.Attendance;

public interface AttendanceService {
    List<Attendance> getAll();
    Attendance getById(Integer id);
    Attendance create(Attendance attendance);
    Attendance update(Integer id, Attendance attendance);
    void delete(Integer id);
}
