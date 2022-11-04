package com.deli.project.web.controller;

import javax.servlet.http.HttpSession;

public abstract class SessionValue {
    public static Long getValue(HttpSession session,String key){
        return Long.valueOf(session.getAttribute(key).toString());
    }
}
