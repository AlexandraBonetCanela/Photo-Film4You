package edu.uoc.epcsd.user.domain.service;

import edu.uoc.epcsd.user.application.rest.request.CreateDigitalSessionRequest;
import edu.uoc.epcsd.user.domain.DigitalSession;
import edu.uoc.epcsd.user.domain.repository.DigitalSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class DigitalSessionServiceImpl implements DigitalSessionService {

    private final DigitalSessionRepository digitalSessionRepository;

    public List<DigitalSession> findAllDigitalSession() {
        return digitalSessionRepository.findAllDigitalSession();
    }

    @Override
	public List<DigitalSession> findDigitalSession(Long userId) {
		return null;
        // TODO: add the code for the missing system operations here:
        // call digitalSessionRepository method
	}
    
    @Override
	public Optional<DigitalSession> findDigitalSessionById(Long id) {
        return digitalSessionRepository.findDigitalSessionById(id);
	}

	@Override
	public Long addDigitalSession(DigitalSession digitalSession) {
		return null;
        // TODO: add the code for the missing system operations here:
        // call digitalSessionRepository method
		
	}

	@Override
	public Long updateDigitalSession(Long digitalSessionId, CreateDigitalSessionRequest updateDigitalSessionRequest) {
		return digitalSessionId;
       
        // TODO: add the code for the missing system operations here:
        // search digitalSession by id, get data request and call digitalSessionRepository method
	}

	@Override
	public void dropDigitalSession(Long id) {
        // TODO: add the code for the missing system operations here:
        // search if it exists and call digitalSessionRepository method		
	}
	
}
