package stepdefinitions;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;


/**
 * Step definitions for functional tests
 */
@Slf4j public class TestStepDefinitions {

    @Given("^replica set on the mongo server has been initiated$")
    public void replica_set_on_the_mongo_server_has_been_initiated() throws Throwable {
        log.info("TODO");
    }

    @When("^I check the configuration on the mongo server$")
    public void I_check_the_configuration_on_the_mongo_server() throws Throwable {
        log.info("TODO");
    }

    @Then("^I see right replica set configuration$")
    public void I_see_right_replica_set_configuration() throws Throwable {
        log.info("TODO");
    }
}
