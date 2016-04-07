Feature: init replica set on mongo server
  @test_replica_set_config
  Scenario: replica set on mongo server is configured
    Given replica set on the mongo server has been initiated
    When I check the configuration on the mongo server
    Then I see right replica set configuration
