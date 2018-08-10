package ro.esolutions.demotests.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import ro.esolutions.demotests.testdata.ClientData
import spock.lang.Specification

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD

@ContextConfiguration
@SpringBootTest
@SqlGroup([
        @Sql(value = ["/sql/data.sql"], executionPhase = BEFORE_TEST_METHOD),
])
class ClientRepositoryIT extends Specification {

    @Autowired
    def ClientRepository clientRepository

    def "Get active clients"() {
        when:
        def result = clientRepository.findByActive(true)

        then:
        result.size() == 1
        result == [ClientData.aClient()]
    }


}