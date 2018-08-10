package ro.esolutions.demotests.controllers;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

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
import ro.esolutions.demotests.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

    static final String VIEW_PATH = "client/view";
    private static final String EDIT_PATH = "client/edit";
    private static final String MODEL = "client";

    private final ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = Objects.requireNonNull(clientService, "clientService must not be null");
    }

    @GetMapping("view")
    public String view(final Model model) {
        final List<ClientModel> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return VIEW_PATH;
    }

    @PostMapping("save")
    public String save(@Valid @ModelAttribute(MODEL) final ClientModel clientModel, final BindingResult bindingResult,
                            final RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            redirect.addFlashAttribute(BindingResult.class.getName() + "." + MODEL, bindingResult);
            redirect.addFlashAttribute(MODEL, clientModel);
            return Redirect.toEdit();
        }

        clientService.save(clientModel);

        return Redirect.toView();
    }

    @GetMapping("edit")
    public String edit(@RequestParam(name = "id", required = false, defaultValue = "0") final Long id, final Model model) {
        if (!model.containsAttribute(MODEL)) {
            model.addAttribute(MODEL, clientService.getClientModel(id));
        }

        return EDIT_PATH;
    }

    @GetMapping("add")
    public String add(final Model model) {
        if (!model.containsAttribute(MODEL)) {
            model.addAttribute(MODEL, ClientModel.builder().build());
        }

        return EDIT_PATH;
    }

    @GetMapping("delete")
    public String delete(@RequestParam(name = "id", required = false, defaultValue = "0") final Long id) {
        clientService.deleteClient(id);

        return Redirect.toView();
    }

    @GetMapping("cancel")
    public String cancel() {
        return Redirect.toView();
    }
}
