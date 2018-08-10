package ro.esolutions.demotests.services;

import static ro.esolutions.demotests.entities.Reservation.Status.CANCELED;

import java.util.List;
import java.util.stream.Collectors;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.demotests.entities.Reservation;
import ro.esolutions.demotests.mappers.ReservationMapper;
import ro.esolutions.demotests.models.ReservationModel;
import ro.esolutions.demotests.repositories.ReservationRepository;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(final ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationModel> getReservationsForClient(final Long clientId) {
        return reservationRepository.findByClientId(clientId).stream()
                .map(ReservationMapper::toModel)
                .collect(Collectors.toList());
    }

    public void save(final ReservationModel reservationModel) {
        reservationRepository.save(ReservationMapper.toEntity(reservationModel));
    }

    public ReservationModel getReservationModel(final Long id) {
        final ReservationModel reservationModel = reservationRepository.findById(id)
                .map(ReservationMapper::toModel)
                .orElseGet(() -> ReservationModel.builder().build());
        return reservationModel;
    }

    public ReservationModel cancelReservation(final Long id) {
        final Reservation reservation = reservationRepository.findOne(id);
        reservation.setStatus(CANCELED);
        return ReservationMapper.toModel(reservationRepository.save(reservation));
    }
}
