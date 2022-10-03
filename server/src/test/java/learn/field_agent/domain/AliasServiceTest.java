package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.data.LocationRepository;
import learn.field_agent.models.Agency;
import learn.field_agent.models.Alias;
import learn.field_agent.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {
    @Autowired
     AliasService service;

    @MockBean
    AliasRepository repository;

    @Test
    void shouldNotAddWhenInvalid() {
        Alias alias = makeAlias();
        alias.setName("   ");

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        alias = makeAlias();
        alias.setName(null);
        actual = service.add(alias);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldAdd() {
        Alias alias = new Alias(7, "Test", "Test", 7);
        Alias mockOut = new Alias(8, "TEST", "Long Name Test", 8);

        when(repository.add(alias)).thenReturn(mockOut);

        Result<Alias> actual = service.add(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        Alias alias = makeAlias();
        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.INVALID, actual.getType());

        Alias alias1 = makeAlias();
        alias.setAliasId(1);
        alias.setName("");
        actual = service.update(alias1);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdate() {
        Alias alias = makeAlias();
        alias.setAliasId(1);

        when(repository.update(alias)).thenReturn(true);

        Result<Alias> actual = service.update(alias);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldDelete() {
        when(repository.deleteById(1)).thenReturn(true);
        boolean result = service.deleteById(1);

        assertTrue(result);
    }

    Alias makeAlias() {
        Alias alias = new Alias();
        alias.setAliasId(7);
        alias.setName("Mrs. Smith");
        alias.setPersona("Special Agent");
        alias.setAgentId(7);
        return alias;
    }
}