package edu.uoc.epcsd.user.infrastructure.repository.jpa;

import edu.uoc.epcsd.user.application.rest.response.GetUserResponse;
import edu.uoc.epcsd.user.domain.DigitalSession;
import edu.uoc.epcsd.user.domain.User;
import edu.uoc.epcsd.user.domain.repository.DigitalSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        // TODO: add the code for the missing system operations here:
        // call SpringDataDigitalSessionRepository method
        return jpaDigitalSessionRepository.findDigitalSession(userId).stream().map(DigitalSessionEntity::toDomain).collect(Collectors.toList());
    }   
    
    @Override
    public Long addDigitalSession(DigitalSession digitalSession) {
        // TODO: add the code for the missing system operations here:
        // search if it exists and saves

        DigitalSessionEntity s = DigitalSessionEntity.fromDomain(digitalSession);

        UserEntity u = jpaUserRepository.getById(digitalSession.getUserId());
        s.setUser(u);
        s = jpaDigitalSessionRepository.save(s);
        return s.getId();
    }

	@Override
	public Long updateDigitalSession(DigitalSession digitalSession) {
        // TODO: add the code for the missing system operations here:
        // search if it exists, changes and saves

        if(findDigitalSessionById(digitalSession.getId()).isPresent()) {
            DigitalSessionEntity entity = DigitalSessionEntity.fromDomain(digitalSession);
            UserEntity userEntity = jpaUserRepository.getById(digitalSession.getUserId());
            entity.setUser(userEntity);
            return jpaDigitalSessionRepository.save(entity).getId();
        }
        return null;
	}

	@Override
	public void dropDigitalSession(DigitalSession digitalSession) {
        DigitalSessionEntity entity = DigitalSessionEntity.fromDomain(digitalSession);
        jpaDigitalSessionRepository.delete(entity);
	}
	
    
}
