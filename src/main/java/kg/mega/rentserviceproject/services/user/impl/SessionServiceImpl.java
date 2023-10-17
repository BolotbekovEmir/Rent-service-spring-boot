package kg.mega.rentserviceproject.services.user.impl;

import kg.mega.rentserviceproject.models.user.User;
import kg.mega.rentserviceproject.services.user.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final Map<String, User> userSession = new ConcurrentHashMap<>();

    @Override
    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void addUserSession(String sessionId, User user) {
        userSession.put(sessionId, user);
    }

    @Override
    public boolean isCurrentSession(String sessionId) {
        return userSession.containsKey(sessionId);
    }

    @Override
    public User getUserSession(String sessionId) {
        return userSession.get(sessionId);
    }

    @Override
    public void removeUserSession(String sessionId) {
        userSession.remove(sessionId);
    }
}
