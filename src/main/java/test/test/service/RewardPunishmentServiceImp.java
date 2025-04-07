package test.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import test.test.entity.RewardPunishment;
import test.test.repository.RewardPunishmentRepo;

@Service
@RequiredArgsConstructor
public class RewardPunishmentServiceImp implements RewardPunishmentService {

    private final RewardPunishmentRepo repo;

    public List<RewardPunishment> getAll() {
        return repo.findAll();
    }

    public RewardPunishment getById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    public RewardPunishment create(RewardPunishment rp) {
        return repo.save(rp);
    }

    public RewardPunishment update(Integer id, RewardPunishment updated) {
        RewardPunishment old = getById(id);
        old.setDate(updated.getDate());
        old.setAmount(updated.getAmount());
        old.setReason(updated.getReason());
        old.setType(updated.getType());
        old.setUser(updated.getUser());
        return repo.save(old);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
