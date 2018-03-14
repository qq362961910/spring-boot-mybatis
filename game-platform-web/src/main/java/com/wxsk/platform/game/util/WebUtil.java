package com.wxsk.platform.game.util;

import com.wxsk.passport.model.User;

public class WebUtil {

    private static final ThreadLocal<User> requestThreadUser = new ThreadLocal<>();

    /**
     * 设置当前线程User
     */
    public static void setCurrentUser(User user) {
        if (user == null) {
            return;
        }
        requestThreadUser.set(user);
    }

    /**
     * 获取当前线程User
     */
    public static User getCurrentUser() {
        return requestThreadUser.get();
    }

    /**
     * 去除当前线程User
     */
    public static void removeCurrentUser() {
        requestThreadUser.remove();
    }
}
