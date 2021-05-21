package cz.fi.muni.pa165.hockeymanager.rest;

/**
 * Represents the entry points for the API
 * this list can be increased so that it contains all the
 * other URIs also for the sub-resources so that it can
 * reused globally from all the controllers
 */
public abstract class ApiUris {
    public static final String ROOT_URI_MATCH        = "/match";
    public static final String ROOT_URI_USER         = "/user";
    public static final String ROOT_URI_TEAM         = "/team";
    public static final String ROOT_URI_HOCKEYPLAYER = "/hockey_player";
}
