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

#    @Ignore
    Scenario Outline: Employee creation fails when the data has wrong format
        Given I'm at the main interface view
        When I click on the Create CTA
        And I fill out the employee's "<firstName>" with an invalid firstName
        And I fill out the employee's "<lastName>" with an invalid lastName
        And I fill out the employee's "<startDate>" with a bad formatted startDate
        And I fill out the employee's "<mail>" with a wrong email address
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

    Scenario: Delete employee's information
        Given I'm at the main interface view
        When I select the employee to delete
        And I click on the Delete CTA
        And I confirm that I want to delete the employee's information
        Then I should not be able to find the employee