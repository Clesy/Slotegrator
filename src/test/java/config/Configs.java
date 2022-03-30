package config;

import utils.ConfigReader;
import utils.NumberUtils;

import java.util.Properties;

public class Configs {
    private final static String CONFIG_FILE_NAME = "configs.properties";
    private final static String BASE_URL_KEY = "base.url";
    private final static String BASE_DRIVER_PATH_KEY = "base.driver.path";
    private final static String BASE_BROWSER_KEY = "base.browser";
    private final static String USERNAME_KEY = "base.username";
    private final static String PASSWORD_KEY = "base.password";
    private final static String PAGE_LOAD_TIMEOUT_KEY = "page.load.timeout";
    private final static String EXPLICIT_TIMEOUT_KEY = "explicit.timeout";

    private static final Properties CONFIG;
    public static final String USERNAME;
    public static final String PASSWORD;
    public static final String BASE_URL;
    public static final String BASE_DRIVER_PATH;
    public static final String BASE_BROWSER;
    public static final Integer PAGE_LOAD_TIMEOUT;
    public static final Integer EXPLICIT_TIMEOUT;

    static {
        CONFIG = ConfigReader.readConfig(CONFIG_FILE_NAME);
        USERNAME = CONFIG.getProperty(USERNAME_KEY);
        PASSWORD = CONFIG.getProperty(PASSWORD_KEY);
        BASE_URL = CONFIG.getProperty(BASE_URL_KEY);
        BASE_DRIVER_PATH = CONFIG.getProperty(BASE_DRIVER_PATH_KEY);
        BASE_BROWSER = CONFIG.getProperty(BASE_BROWSER_KEY);
        PAGE_LOAD_TIMEOUT = NumberUtils.parseInt(CONFIG.getProperty(PAGE_LOAD_TIMEOUT_KEY));
        EXPLICIT_TIMEOUT = NumberUtils.parseInt(CONFIG.getProperty(EXPLICIT_TIMEOUT_KEY));
    }

    private Configs() {
    }
}