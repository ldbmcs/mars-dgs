package com.ldbmcs.dgs.infra.context;


import com.ldbmcs.dgs.core.domain.user.models.User;

public final class CurrentContext {

    private CurrentContext() {
    }

    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<String> AUTH_TOKEN = new ThreadLocal<>();

    public static User user() {
        return USER_THREAD_LOCAL.get();
    }

    public static String authToken() {
        return AUTH_TOKEN.get();
    }

    public static void setUser(User user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static void setAuthToken(String token) {
        AUTH_TOKEN.set(token);
    }

    public static void clear() {
        USER_THREAD_LOCAL.remove();
        AUTH_TOKEN.remove();
    }
}
