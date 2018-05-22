package com.example.deyvi.gerenciamentoderepublica.constantsApp;

import com.pixplicity.easyprefs.library.Prefs;

public class ManagerPrefs {

    private static final String FIST_UTILIS = "FIST_UTILIS";

     public static void isFistUtils() {
        Prefs.getBoolean(FIST_UTILIS, true);
        Prefs.putBoolean(FIST_UTILIS, true);
    }




}
