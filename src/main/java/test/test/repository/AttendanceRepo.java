package test.test.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.test.dto.Status;
import test.test.entity.Attendance;
import test.test.entity.Users;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByUser(Users user);

    Attendance findByUserAndWorkDate(Users user, Date workDate);

    List<Attendance> findByWorkDate(Date workDate);

    List<Attendance> findByStatus(Status status);

    List<Attendance> findByUserAndWorkDateBetween(Users user, Date startDate, Date endDate);
}

