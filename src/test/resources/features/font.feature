Feature: Font Feature

    Background:
        Given default font is sans

    Scenario: Edit existing content on resume and sans font remains the same
        When existing content on resume is edited in sans font
        Then sans font remains

    Scenario: Add new content to resume and sans font remains the same
        When new content is added in sans font
        Then font is still sans

    Scenario: Edit new content on resume and sans font remains the same
        When new content is edited in sans font
        Then font remains sans

    Scenario: Change font to serif, then edit existing content on resume and serif font remains the same
        When font is changed to serif and existing content is edited
        Then serif font remains

    Scenario: Change font to serif, then add new content to resume and serif font remains the same
        When font is changed to serif and new content is added
        Then font is still serif

    Scenario: Change font to serif, edit new content on resume and serif font remains the same
        When font is changed to serif and new content is edited
        Then font remains serif

    Scenario: Change font to mono, then edit existing content on resume and mono font remains the same
        When font is changed to mono and existing content is edited
        Then mono font remains

    Scenario: Change font to mono, then add new content to resume and mono font remains the same
        When font is changed to mono and new content is added
        Then font is still mono

    Scenario: Change font to mono, edit new content on resume and mono font remains the same
        When font is changed to mono and new content is edited
        Then font remains mono