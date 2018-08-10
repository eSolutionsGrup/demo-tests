package ro.esolutions.demotests.controllers;

public final class Redirect {
    static final String VIEW = "view";
    static final String EDIT = "edit";

    private Redirect() {
        // no initialization needed
    }

    public static String to(final String path) {
        return "redirect:" + path;
    }

    public static String toView() {
        return to(VIEW);
    }

    public static String toEdit() {
        return to(EDIT);
    }
}
