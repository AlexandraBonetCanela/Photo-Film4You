package edu.uoc.epcsd.user.infrastructure.repository.jpa;

import edu.uoc.epcsd.user.domain.DigitalSession;
import edu.uoc.epcsd.user.domain.repository.DigitalSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DigitalSessionRepositoryImpl implements DigitalSessionRepository {

    private final SpringDataDigitalSessionRepository jpaDigitalSessionRepository;

    private final SpringDataUserRepository jpaUserRepository;

    @Override
    public List<DigitalSession> findAllDigitalSession() {
        return jpaDigitalSessionRepository.findAll().stream().map(DigitalSessionEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<DigitalSession> findDigitalSessionById(Long id) {
        return jpaDigitalSessionRepository.findDigitalSessionById(id).map(DigitalSessionEntity::toDomain);
    }
    
    @Override
    public List<DigitalSession> findDigitalSession(Long userId) {
		return null;
        // TODO: add the code for the missing system operations here:
        // call SpringDataDigitalSessionRepository method    
    }   
    
    @Override
    public Long addDigitalSession(DigitalSession digitalSession) {
		return null;
        // TODO: add the code for the missing system operations here:
        // search if it exists and saves
    }

	@Override
	public Long updateDigitalSession(DigitalSession digitalSession) {
		return null;
        // TODO: add the code for the missing system operations here:
        // search if it exists, changes and saves
	}

	@Override
	public void dropDigitalSession(DigitalSession digitalSession) {   
        // TODO: add the code for the missing system operations here:
        // delete digitalSession		
	}
	
    
}
