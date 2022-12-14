package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;

    @Test
    void shouldFindTwoSecurityClearances() {
        when(repository.findAll()).thenReturn(List.of(
                new SecurityClearance(1, "Uno"),
                new SecurityClearance(2, "Dos")
        ));
        List<SecurityClearance> securityClearances = service.findAll();
        assertEquals(2, securityClearances.size());
    }

    @Test
    void shouldFindSecurityClearanceWithAnIdOfThree() {
        when(repository.findById(3)).thenReturn(new SecurityClearance());
        SecurityClearance securityClearance= service.findById(3);
        assertNotNull(securityClearance);
    }

    @Test
    void shouldNotCreateNull() {
        SecurityClearance securityClearance = null;

        Result<SecurityClearance> result = service.add(securityClearance);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("Security clearance cannot be null", result.getMessages().get(0));
    }

    @Test
    void shouldNotCreateNullName() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName(null);

        Result<SecurityClearance> result = service.add(securityClearance);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("name is required", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWhenInvalid() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("");

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());

        actual = service.add(securityClearance);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldAdd() {
        SecurityClearance securityClearance = makeSecurityClearance();
        SecurityClearance mockOut = makeSecurityClearance();
        mockOut.setSecurityClearanceId(5);

        when(repository.add(securityClearance)).thenReturn(mockOut);

        Result<SecurityClearance> actual = service.add(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotUpdateNonExistentSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setSecurityClearanceId(1000);
        securityClearance.setName("Audrey");
        when(repository.update(securityClearance)).thenReturn(false);
        Result result = service.update(securityClearance);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("securityClearanceId: 1000, was not found", result.getMessages().get(0));
    }

    @Test
    void shouldUpdate() {
        SecurityClearance securityClearance = makeSecurityClearance();
        securityClearance.setSecurityClearanceId(1);

        when(repository.update(securityClearance)).thenReturn(true);

        Result<SecurityClearance> actual = service.update(securityClearance);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldDelete() {
        when(repository.deleteById(1)).thenReturn(true);
        boolean result = service.deleteById(1);

        assertTrue(result);
    }

    SecurityClearance makeSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Area51");
        return securityClearance;
    }
}