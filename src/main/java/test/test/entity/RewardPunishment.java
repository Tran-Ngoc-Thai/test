package test.test.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RewardPunishment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardPunishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rewardPunishID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    public enum Type {
        KhenThuong,
        KyLuat
    }
}
