package kg.mega.rentserviceproject.services.user;

import kg.mega.rentserviceproject.models.user.User;

public interface SessionService {
    String generateSessionId();
    void addUserSession(String sessionId, User user);
    boolean isCurrentSession(String sessionId);
    User getUserSession(String sessionId);
    void removeUserSession(String sessionId);
}
