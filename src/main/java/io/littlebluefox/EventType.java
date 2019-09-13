package io.littlebluefox;

/** The Enum EventType. */
public enum EventType {

    /** When using it has successfully authenticated (good username and password). */
    AUTHENTICATION_SUCCESS("authentication_success"),

    /** When a user asks to display the login form. */
    AUTHENTICATION_REQUEST("authentication_request"),

    /** When an error occurred during the authentication (bad password for example). */
    AUTHENTICATION_FAILURE("authentication_failure"),

    /** When emai/login is unknown in your services. */
    AUTHENTICATION_FAILURE_UNKNOWN_USER("authentication_failure_unknown_user"),

    /** A user has requested to display the password change page or form. */
    PASSWORD_UPDATE_REQUEST("password_update_request"),

    /** The user tried to change a password but the rules did not. */
    PASSWORD_UPDATE_FAILURE("password_update_failure"),

    /** A user has successfully changed their password. */
    PASSWORD_UPDATE_SUCCESS("password_update_success"),

    /** When a user has successfully created an account. */
    ACCOUNT_CREATION_SUCCESS("account_creation_success"),

    /**
     * When a user asked to create an account but there was a failure (examples: bad captcha for, expiration of a
     * uniqueness link, etc.).
     */
    ACCOUNT_CREATION_FAILURE("account_creation_failure"),

    /** A user accesses sensitive or personal data (user profile, etc.). */
    ACCESS_SENSITIVE_DATA("access_sensitive_data"),

    /** A user to modify sensitive or personal data. */
    SENSITIVE_DATA_UPDATED("sensitive_data_updated"),

    /** A user has generated a payment failure. */
    PAYMENT_FAILURE("payment_failure");

    /** The value. */
    private String value;

    /**
     * Instantiates a new event type.
     *
     * @param value
     *        the value
     */
    private EventType(String value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
