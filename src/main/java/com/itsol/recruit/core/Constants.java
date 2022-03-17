package com.itsol.recruit.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Application constants.
 */
public final class Constants {

    private Constants() {
    }

    public static final class Role{
        public static final String ADMIN = "admin";
        public static final String JE = "je";
        public static final String USER = "user";
    }

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

//    public static final String SYSTEM = "system";
//    public static final String DEFAULT_LANGUAGE = "vi";
//    public static final Locale DEFAULT_LOCALE = Locale.forLanguageTag(DEFAULT_LANGUAGE);
//    public static final String API_KEY_JWT = "JWT";


    public static final class Api {

//        public static class Tag {
//            public static final String AUTHENTICATION = "Authentication";
//            public static final String DASHBOARD = "Dashboard";
//            public static final String PROFILE_SETTINGS = "Profile Settings";
//            public static final String ROLE_MANAGEMENT = "Role Management";
//            public static final String CALENDAR_MANAGEMENT = "Calendar Management";
//            public static final String NOTIFICATION = "Notification";
//        }

        public static class Path {
            public static final String PREFIX = "/api";

            public static final String PUBLIC = PREFIX + "/public";
            public static final String ADMIN = PREFIX + "/admin";
            public static final String AUTH = PREFIX + "/auth";
//            public static final String ACCOUNT = PREFIX + "/account";
//            public static final String ON_BOARDING = PUBLIC + "/on-boarding";

            public static class Auth {
                public static final String LOGIN = "/login";
                public static final String REFRESH_TOKEN = "/refresh-token";
                public static final String CHECK_PHONE_NUMBER = "/check-phone-number";
                public static final String OTP = "/otp";
                public static final String OTP_REQUEST = OTP + "/request";
                public static final String OTP_VERIFY = OTP + "/verify";
            }

            public static class Account {
                public static final String REGISTER = "/register";
                public static final String CHANGE_PASSWORD = "/change-password";
                public static final String RESET_PASSWORD = "/reset-password";
                public static final String RESET_PASSWORD_INIT = RESET_PASSWORD + "/init";
                public static final String RESET_PASSWORD_FINISH = RESET_PASSWORD + "/finish";
            }

            public static class Admin {
                public static final String AUTH = ADMIN + "/auth";
            }
        }
    }

}