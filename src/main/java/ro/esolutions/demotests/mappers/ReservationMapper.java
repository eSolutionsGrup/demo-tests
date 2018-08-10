package ro.esolutions.demotests.mappers;

import ro.esolutions.demotests.entities.Reservation;
import ro.esolutions.demotests.models.ReservationModel;

public final class ReservationMapper {

    private ReservationMapper() {}

    public static ReservationModel toModel(final Reservation entity) {
        return ReservationModel.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .reservationDate(entity.getReservationDate())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .client(ClientMapper.toModel(entity.getClient()))
                .roomType(entity.getRoomType())
                .build();
    }

    public static Reservation toEntity(final ReservationModel model) {
        return Reservation.builder()
                .id(model.getId())
                .status(model.getStatus())
                .reservationDate(model.getReservationDate())
                .fromDate(model.getFromDate())
                .toDate(model.getToDate())
                .client(ClientMapper.toEntity(model.getClient()))
                .roomType(model.getRoomType())
                .build();
    }
}
