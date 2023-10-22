Feature: Layout Feature

    Background:
        Given default layout is top

    Scenario: Edit existing content on resume and top layout remains the same
        When existing content on resume is edited
        Then top layout remains

    Scenario: Add new content to resume and top layout remains the same
        When new content is added
        Then layout is still top

    Scenario: Edit new content on resume and top layout remains the same
        When new content is edited
        Then layout remains top

    Scenario: Change layout to left, then edit existing content on resume and left layout remains the same
        When layout is changed to left and existing content is edited
        Then left layout remains

    Scenario: Change layout to left, then add new content to resume and left layout remains the same
        When layout is changed to left and new content is added
        Then layout is still left

    Scenario: Change layout to left, edit new content on resume and left layout remains the same
        When layout is changed to left and new content is edited
        Then layout remains left

    Scenario: Change layout to right, then edit existing content on resume and right layout remains the same
        When layout is changed to right and existing content is edited
        Then right layout remains

    Scenario: Change layout to right, then add new content to resume and right layout remains the same
        When layout is changed to right and new content is added
        Then layout is still right

    Scenario: Change layout to right, edit new content on resume and right layout remains the same
        When layout is changed to right and new content is edited
        Then layout remains right