Feature: Personal Details Feature

    Background:
        Given resume contains content

    Scenario: Changing the default information test 1
        When personal details are edited 1
        Then changes reflected on resume 1

    Scenario: Changing the default information test 2
        When personal details are edited 2
        Then changes reflected on resume 2


