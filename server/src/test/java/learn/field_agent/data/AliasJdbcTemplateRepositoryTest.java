package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {
    private final int NEXT_ID = 7;
    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findAliasByName() {
        List<Alias> result = repository.findByName("Agent X");
        assertNotNull(result);
    }

    @Test
    void shouldAdd() {
        Alias alias1 = firstAlias();
        Alias actual = repository.add(alias1);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getAliasId());

        Alias alias2 = secondAlias();
        Alias actual2 = repository.add(alias2);
        assertNotNull(actual2);
        assertEquals(NEXT_ID + 1, actual2.getAliasId());
    }

    @Test
    void shouldUpdate() {
        Alias aliasOne = firstAlias();
        aliasOne.setAliasId(1);
        assertTrue(repository.update(aliasOne));
        aliasOne.setAliasId(999);
        assertFalse(repository.update(aliasOne));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(200));
    }

    private Alias firstAlias() {
        Alias alias = new Alias();
        alias.setName("Foxy Cleopatra");
        alias.setPersona("Top Secret Agent");
        alias.setAgentId(1);
        return alias;
    }

    private Alias secondAlias() {
        Alias alias = new Alias();
        alias.setName("Senor Superman");
        alias.setPersona(null);
        alias.setAgentId(2);
        return alias;
    }
}