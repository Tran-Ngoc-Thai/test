package test.test.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.test.entity.Users;
import test.test.entity.WorkingHour;

@Repository
public interface WorkingHoursRepo extends JpaRepository<WorkingHour, Integer>{

    List<WorkingHour> findByUser(Users user);

    List<WorkingHour> findByUserAndWorkDateBetween(Users user, Date startDate, Date endDate);

    WorkingHour findByUserAndWorkDate(Users user, Date workDate);

}
