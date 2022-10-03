package learn.field_agent.controllers;

import learn.field_agent.domain.Result;
import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.SecurityClearance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/security/clearance")
public class SecurityClearanceController {

    private final SecurityClearanceService service;

    public SecurityClearanceController(SecurityClearanceService service) {
        this.service = service;
    }

    @GetMapping("/setKnownGoodState")
    public void callSetKnownGoodState() {
        service.setKnownGoodState();
    }

    @GetMapping
    public List<SecurityClearance> findAll() {
        return service.findAll();
    }

    @GetMapping("/{securityClearanceId}")
    public ResponseEntity<SecurityClearance> findById(@PathVariable int securityClearanceId) {
        SecurityClearance securityClearance = service.findById(securityClearanceId);
        if (securityClearance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(securityClearance);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody SecurityClearance securityClearance){
        Result result = service.add(securityClearance);
        if(!result.isSuccess()){
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{securityClearanceId}")
    public ResponseEntity<?> update(@PathVariable int securityClearanceId, @RequestBody SecurityClearance securityClearance) {
        if (securityClearanceId != securityClearance.getSecurityClearanceId()) { //AKA the url ID does not match the id in the body of the request
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<SecurityClearance> result = service.update(securityClearance);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{securityClearanceId}")
    public ResponseEntity<Void> deleteById(@PathVariable int securityClearanceId) {
        SecurityClearance securityClearance = service.findById(securityClearanceId);
        if (securityClearance == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (service.deleteById(securityClearanceId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if(!service.deleteById(securityClearanceId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
