Feature: Template Loader Feature
    Scenario: Resume has content
        Given resume has content
        When the clear resume button is pressed
        Then content should be removed

    Scenario: Load content
        Given resume has no content
        When load template pressed
        Then content appears