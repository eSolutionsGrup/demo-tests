package ro.esolutions.demotests.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.BindingResult
import org.springframework.web.context.WebApplicationContext
import ro.esolutions.demotests.repositories.ClientRepository
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ContextConfiguration
@SpringBootTest
class ClientControllerSpecIT extends Specification {

    @Autowired
    def WebApplicationContext context

    def MockMvc mvc

    @Autowired
    def ClientRepository clientRepository


    def setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    def "View all clients"() {
        given:
        def url = "/client/view"
        when:
        def resultActions = mvc.perform(get(url))

        then:
        resultActions.andExpect(status().isOk())
                .andExpect(view().name("client/view"))
                .andExpect(model().attributeExists("clients"))
    }

    def "Save client has validation errors"() {
        given:
        def url = "/client/save"

        when:
        def resultActions = mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "")
                .param("lastName", "Masinuta")
                .param("address", "Bucuresti")
                .param("emailAddress", "gigi@email.com")
                .param("phoneNumber", "07669504")
                .param("active", "true")

        )

        then:
        resultActions.andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:edit"))
                .andExpect(redirectedUrl("edit"))
                .andExpect(flash().attributeExists(BindingResult.name + ".client"))
                .andExpect(flash().attributeExists("client"))

        and:
        clientRepository.findAll().size() == 1

    }

    def "Save client with success"() {
        given:
        def url = "/client/save"

        when:
        def resultActions = mvc.perform(post(url)
                .param("firstName", "Gigi2")
                .param("lastName", "Masinuta")
                .param("address", "Bucuresti")
                .param("emailAddress", "gigi@email.com")
                .param("phoneNumber", "07669504")
                .param("active", "true")

        )

        then:
        resultActions.andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(view().name("redirect:view"))
                .andExpect(redirectedUrl("view"))

        and:
        clientRepository.findAll().size() == 2

    }

}