# SLOTEGRATOR

### Automation UI and API Tests for http://test-app.d6.dev.devcaz.com/

### Install driver

We can install driver in thees links, add to: ``src/test/java/resources/driver``
and change param ``base.driver.path`` in ``config.properties``

1. Gecko driver for Firefox [link](https://github.com/mozilla/geckodriver/releases)
2. Driver for Microsoft Edge [link](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/)
3. Driver for Opera / Chrome [link](https://chromedriver.chromium.org/)

### Run Tests

#### API Tests

```.\gradlew test```

#### UI Tests

```.\gradlew cucumberCli```
or
```.\gradlew cucumber```


