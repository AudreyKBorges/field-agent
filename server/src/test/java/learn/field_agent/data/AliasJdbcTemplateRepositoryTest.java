package learn.field_agent.data;

import learn.field_agent.models.Alias;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {
    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        Alias jamesBond = new Alias(1, "James Bond", "Secret Agent", 1);
        Alias agentX = new Alias(2, "Agent X", "Secret Agent", 2);

        Alias actual = repository.findById(1);
        assertEquals(jamesBond, actual);

        actual = repository.findById(2);
        assertEquals(agentX, actual);

        actual = repository.findById(6);
        assertEquals(null, actual);
    }

    @Test
    void shouldCreate() {
        Alias alias = new Alias();
        alias.setAliasId(7);
        alias.setName("Agent 13");
        alias.setPersona("Special Agent");
        alias.setAgentId(7);

        Alias result = repository.add(alias);

        assertNotNull(result);
        assertEquals(7, result.getAliasId());

        assertEquals(result, repository.findById(5));
    }

    @Test
    void shouldUpdate() {
        Alias alias = new Alias();
        alias.setAliasId(2);
        alias.setName("Austin Powers");

        assertTrue(repository.update(alias));
        assertEquals(alias, repository.findById(2));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(3));
    }

}