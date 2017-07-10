package io.github.devas.net;

class TimeStamp {

    private static boolean enabled = true;

    static void enableTimeStamp() {
        enabled = true;
    }

    static void disableTimeStamp() {
        enabled = false;
    }

    static String getTimeStamp() {
        return enabled ? new java.util.Date().toString() + " | " : "";
    }

}
