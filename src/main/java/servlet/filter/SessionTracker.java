package servlet.filter;

import java.util.HashSet;
import java.util.Set;

public class SessionTracker {
    private Set<String> sessions = new HashSet<String>();
    private static final SessionTracker sessionsInstance = new SessionTracker();

    public static SessionTracker getInstance() {
        return sessionsInstance;
    }

    public void addSession(String sessionid) {
        sessions.add(sessionid);
    }

    public boolean isSessionValid(String session) {
        return sessions.contains(session);
    }

    public void invalidate(String session) {
        sessions.remove(session);
    }
}
