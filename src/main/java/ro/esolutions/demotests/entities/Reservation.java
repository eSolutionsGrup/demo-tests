package ro.esolutions.demotests.entities;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate fromDate;

    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate toDate;

    @ManyToOne
    private Client client;

    @Type(type = "org.hibernate.type.LocalDateType")
    private LocalDate reservationDate;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        NEW, CANCELED, CONFIRMED
    }

    public enum RoomType {
        SINGLE, DOUBLE, TWIN
    }

    @PrePersist
    private void prePersist() {
        this.status = Status.NEW;
        this.reservationDate = LocalDate.now();
    }
}
