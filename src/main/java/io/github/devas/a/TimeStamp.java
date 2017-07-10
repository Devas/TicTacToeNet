package io.github.devas.a;

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
