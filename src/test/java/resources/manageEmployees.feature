Feature: As a user I want to be able to create, edit, and delete Employees data through the Townsend applicattion,
    So, I can easily manage them


    Background:
        Given I'm logged in the Townsend application

    Scenario: Successfully create a Employee
        Given I'm at the main interface view
        When I click on the Create CTA
        And I fill out the new employee's information
        And I submit the new employee's information
        Then I should see the employee listed at the main interface view

    Scenario Outline: Creation of employees with duplicated emails shouldn't be possible
        Given I'm at the employees list interface
        When I click on the Create CTA
        And I fill out the employee's "<firstName>" firstName
        And I fill out the employee's "<lastName>" lastName
        And I fill out the employee's "<startDate>" startDate
        And I fill out the employee's mail with an already existing email address
        And I submit the new employee's information
        Then the create employees form should fail

        Examples:
            | firstName | lastName | startDate  |
            | Michaela  | Smith    | 2019-02-18 |


    Scenario Outline: Employee creation fails when the data has wrong format
        Given I'm at the main interface view
        When I click on the Create CTA
        And I fill out the employee's "<firstName>" firstName
        And I fill out the employee's "<lastName>" lastName
        And I fill out the employee's "<startDate>" startDate
        And I fill out the employee's "<mail>" email address
        And I submit the new employee's information
        Then the create employees form should be invalid

        Examples:
            | firstName | lastName | startDate | mail             |
            | s         | l        | 000-00-00 | mail - address   |
            | fN        | lN       | 9999000   | mail@address.com |
            |           |          |           |                  |

    Scenario: Edit employee's information
        Given I'm at the main interface view
        When I select the employee to edit
        And I click on the Edit CTA
        And I update the employee's data
        Then I should view the employee's data updated

    Scenario: Delete employee's information from the main interface
        Given I'm at the main interface view
        When I select the employee to delete
        And I click on the Delete CTA
        And I confirm that I want to delete the employee's information
        Then I should not be able to find the employee

    Scenario: Delete employee's information from the edit interface
        Given I'm at the main interface view
        When I choose an employee to delete
        And I click on the Delete CTA on the Edit interface
        And I confirm that I want to delete the employee's information
        Then I should not be able to find the deleted employee

    Scenario: View employee's detailed information
        Given I'm at the main interface view
        When I double click over an employee's name
        Then I should see the employee's detailed information

