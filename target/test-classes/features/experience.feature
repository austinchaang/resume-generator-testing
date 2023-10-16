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

