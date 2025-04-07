package test.test.entity;



import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Entity;
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
@Table(name = "WorkingHours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workingHourID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private Users user;

    private LocalTime checkInTime;

   
    private LocalTime checkOutTime;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workDate;

    // public Date getWorkDate() {
    //     return workDate;
    // }

    // public void setWorkDate(Date workDate) {
    //     this.workDate = workDate;
    // }
}
