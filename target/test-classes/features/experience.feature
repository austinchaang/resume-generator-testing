Feature: Experience Feature

    Background:
        Given existing experience is present

    Scenario: Edit existing experience
        When user deletes and edits the existing experience
        Then changes are reflected

    Scenario: Hide existing experience
        When user hides existing experience
        Then existing experience is hidden

    Scenario: Add new experience
        When new experience is added
        Then new experience is on resume

    Scenario: Edit new experience
        When new experience is saved and edited
        Then new experience edits appear on resume

    Scenario: Hide new experience
        When new experience is saved and hidden
        Then new experience is hidden

    Scenario: Delete new experience
        When new experience is saved and then deleted
        Then new experience is deleted from resume

    Scenario: Delete existing experience
        When existing experience is deleted
        Then existing experience is not on resume

    Scenario: Use cancel button for existing experience
        When edits are made to an existing experience and cancel button is pressed
        Then changes will not be made on resume

    Scenario: Use cancel button for new experience
        When new experience is added, saved, edited again, and cancelled
        Then the edit on the new experience does not appear