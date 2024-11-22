package edu.uoc.epcsd.user.application.rest;

import edu.uoc.epcsd.user.application.rest.request.CreateDigitalSessionRequest;
import edu.uoc.epcsd.user.domain.DigitalSession;
import edu.uoc.epcsd.user.domain.service.DigitalSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/digital")
public class DigitalSessionRESTController {

    private final DigitalSessionService digitalSessionService;

    @GetMapping("/allDigital")
    @ResponseStatus(HttpStatus.OK)
    public List<DigitalSession> getAllDigitalSession() {
        log.trace("getAllDigitalSession");

        return digitalSessionService.findAllDigitalSession();
    }

    @GetMapping("/digitalByUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DigitalSession> getDigitalSession(@PathVariable @NotNull Long userId) {
        log.trace("getDigitalSessionsByUser");

        return digitalSessionService.findDigitalSession(userId);
    }

    @PostMapping("/addDigital")
    public ResponseEntity<Long> addDigitalSession(@RequestBody @Valid CreateDigitalSessionRequest createDigitalSessionRequest) {
        log.trace("addDigitalSession");
		return null;

        // TODO: add the code for the missing system operations here:
        // get data request and call digitalSessionService method
    }

    @PostMapping("/updateDigital/{digitalSessionId}")
    public ResponseEntity<Boolean> updateDigitalSession(@PathVariable @NotNull Long digitalSessionId, @RequestBody @Valid CreateDigitalSessionRequest updateDigitalSessionRequest) {
        log.trace("updateDigitalSession");
		return null;

        // TODO: add the code for the missing system operations here:
        // call digitalSessionService method
    }

    @PostMapping("/dropDigital/{digitalSessionId}")
    public ResponseEntity<Boolean> dropDigitalSession(@PathVariable @NotNull Long digitalSessionId) {
        log.trace("dropDigitalSession");
		return null;
 
        // TODO: add the code for the missing system operations here:
        // call digitalSessionService method
    }    
}
