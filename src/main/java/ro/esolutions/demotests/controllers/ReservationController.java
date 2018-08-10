package ro.esolutions.demotests.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ro.esolutions.demotests.models.ClientModel;
import ro.esolutions.demotests.models.ReservationModel;
import ro.esolutions.demotests.services.ClientService;
import ro.esolutions.demotests.services.ReservationService;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private static final String VIEW_PATH = "reservation/view";
    private static final String EDIT_PATH = "reservation/edit";
    private static final String MODEL = "reservation";
    private static final String CLIENT_MODEL = "client";

    private final ReservationService reservationService;
    private final ClientService clientService;

    @Autowired
    public ReservationController(final ReservationService reservationService, ClientService clientService) {
        this.reservationService = Objects.requireNonNull(reservationService, "reservationService must not be null");
        this.clientService = Objects.requireNonNull(clientService, "clientService must not be null");
    }


    @GetMapping("view")
    public String view(@RequestParam(name = "clientId", required = true) final Long clientId, final Model model) {
        final ClientModel client = clientService.getClientModel(clientId);
        final List<ReservationModel> reservations = reservationService.getReservationsForClient(clientId);
        model.addAttribute("reservations", reservations);
        model.addAttribute(CLIENT_MODEL, client);
        return VIEW_PATH;
    }

    @PostMapping("save")
    public String save(@Valid @ModelAttribute(MODEL) final ReservationModel reservationModel, final BindingResult bindingResult,
                       final RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            redirect.addFlashAttribute(BindingResult.class.getName() + "." + MODEL, bindingResult);
            redirect.addFlashAttribute(MODEL, reservationModel);
            redirect.addFlashAttribute(CLIENT_MODEL, reservationModel.getClient());
            return Redirect.toEdit();
        }

        reservationService.save(reservationModel);

        return Redirect.toView() + "?clientId=" + reservationModel.getClient().getId();
    }

    @GetMapping("edit")
    public String edit(@RequestParam(name = "id", required = false, defaultValue = "0") final Long id, final Model model) {
        if (!model.containsAttribute(MODEL)) {
            final ReservationModel reservationModel = reservationService.getReservationModel(id);
            model.addAttribute(MODEL, reservationModel);
            model.addAttribute(CLIENT_MODEL, reservationModel.getClient());
        }

        return EDIT_PATH;
    }

    @GetMapping("add")
    public String add(@RequestParam(name = "clientId", required = true) final Long clientId, final Model model) {

        final ClientModel clientModel = clientService.getClientModel(clientId);
        model.addAttribute(CLIENT_MODEL, clientModel);
        model.addAttribute(MODEL, ReservationModel.builder().client(clientModel).build());

        return EDIT_PATH;
    }

    @GetMapping("cancel")
    public String cancelReservation(@RequestParam(name = "id", required = true) final Long id) {
        ReservationModel reservationModel = reservationService.cancelReservation(id);
        return Redirect.toView() + "?clientId=" + reservationModel.getClient().getId();
    }

    @GetMapping("cancelEdit")
    public String cancelEdit(@RequestParam(name = "clientId", required = true) final Long clientId) {
        return Redirect.toView() + "?clientId=" + clientId;
    }

}
