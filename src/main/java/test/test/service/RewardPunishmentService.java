package test.test.service;

import java.util.List;

import test.test.entity.RewardPunishment;

public interface RewardPunishmentService {
    List<RewardPunishment> getAll();
    RewardPunishment getById(Integer id);
    RewardPunishment create(RewardPunishment rp);
    RewardPunishment update(Integer id, RewardPunishment rp);
    void delete(Integer id);
}
