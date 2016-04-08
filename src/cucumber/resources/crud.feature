Feature: crud features of mongo-connector
  Background:
    Given a mongo server is started with replica set initiated
    And an es server is started
    And mongo-connector is started to sync the data between the mongo server and the es server

  @test_mc_create
  Scenario: sync works for creation
    Given that I create 10 documents in the mongo server
    When I query es after 10 seconds
    Then I see all created documents

  @test_mc_update
  Scenario: sync works for update
    Given that there are 10 documents in the mongo server
    When I update randomly one of the documents in the mongo server
    And I query es after 10 seconds
    Then I see the document updated

  @test_mc_delete
  Scenario: sync works for delete
    Given that there are 10 documents in the mongo server
    When I delete randomly one of the documents in the mongo server
    And I query es after 10 seconds
    Then I see the document deleted
