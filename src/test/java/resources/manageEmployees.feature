Feature: As a user I want to be able to create, edit, and delete Employees data through the Townsend applicattion,
        So, I can easily manage them


Background:
  Given I'm logged in the Townsend application

      Scenario: Successfully create a Employee
          Given I'm at the main interface view
          When I click on the Create CTA
          And I submit the new employee's information
          Then I should see the employee data at the main interface view
#
#      Scenario: Employee creation fails when the data has wrong formated
#          Given I'm at the main interface view
#          When I fill out with the wrong formated data
#          Then I should see an alert message
#
#      Scenario: Edit employee information
#          Given I'm at the main interface view
#          When I found the employee to edit
#          And I click on the Edit CTA
#          And I update the employee's data
#          Then I should view the employee's data updated
#
#      Scenario: Delete employee information
#          Given I'm at the main interface view
#          When I click on the Delete CTA
#          And I confirm that I want to delete the employee information
#          Then I should not be able to find the employee

