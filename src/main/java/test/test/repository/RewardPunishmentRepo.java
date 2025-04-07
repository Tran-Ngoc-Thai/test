package test.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import test.test.entity.RewardPunishment;
import test.test.entity.Users;

@Controller
public interface RewardPunishmentRepo extends JpaRepository<RewardPunishment, Integer> {
     List<RewardPunishment> findByUser(Users user);

    List<RewardPunishment> findByUserAndDateBetween(Users user, Date startDate, Date endDate);

    List<RewardPunishment> findByType(RewardPunishment.Type type);
}
