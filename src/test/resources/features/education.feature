Feature: Education Feature

    Background:
        Given existing education is present

    Scenario: Edit existing education
        When user edits the existing education
        Then verify edits are made

    Scenario: Hide existing educatio
        When user hides existing education
        Then existing education is hidden

    Scenario: Add new education
        When new education is added
        Then new education is on resume

    Scenario: Edit new education
        When new education is saved and edited
        Then new education edits appear on resume

    Scenario: Hide new education
        When new education is saved and hidden
        Then new education is hidden

    Scenario: Delete new education
        When new education is saved and then deleted
        Then new education is deleted from resume

    Scenario: Delete existing education
        When existing education is deleted
        Then existing education is not on resume

    Scenario: Use cancel button for existing education
        When edits are made to an existing education and cancel button is pressed
        Then changes will not be made to existing education

    Scenario: Use cancel button for new education
        When new education is added, saved, edited again, and cancelled
        Then the edit on the new education does not appear