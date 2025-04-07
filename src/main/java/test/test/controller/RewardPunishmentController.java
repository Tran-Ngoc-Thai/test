package test.test.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import test.test.entity.RewardPunishment;
import test.test.service.RewardPunishmentService;

@RestController
@RequestMapping("/api/rewards-punishments")
@RequiredArgsConstructor
public class RewardPunishmentController {

    private final RewardPunishmentService service;

    @GetMapping
    public ResponseEntity<List<RewardPunishment>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RewardPunishment> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<RewardPunishment> create(@RequestBody RewardPunishment rp) {
        return ResponseEntity.ok(service.create(rp));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RewardPunishment> update(@PathVariable Integer id, @RequestBody RewardPunishment rp) {
        return ResponseEntity.ok(service.update(id, rp));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
