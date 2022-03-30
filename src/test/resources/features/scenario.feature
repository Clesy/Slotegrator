# language: en
@scenario
Feature: Authorization on the website

  Scenario: Success authorization
    Given I'm open "http://test-app.d6.dev.devcaz.com/admin/login" site
    When User input login "admin1" on field
    And User input password "[9k<k8^z!+$$GkuP" on field
    And User click button 'Sign in'
    Then Dashboard is loaded

  Scenario: Go to the player table
    Given I'm open "http://test-app.d6.dev.devcaz.com/admin/login" site
    When User input login "admin1" on field
    And User input password "[9k<k8^z!+$$GkuP" on field
    And User click button 'Sign in'
    And User click button 'Users'
    And User click links 'Player'
    Then Users table is loaded

  Scenario: Sorting the player table
    Given I'm open "http://test-app.d6.dev.devcaz.com/admin/login" site
    When User input login "admin1" on field
    And User input password "[9k<k8^z!+$$GkuP" on field
    And User click button 'Sign in'
    And User click button 'Users'
    And User click links 'Player'
    And User click links 'ExternalId'
    Then Table sorted by 'ExternalId' row

