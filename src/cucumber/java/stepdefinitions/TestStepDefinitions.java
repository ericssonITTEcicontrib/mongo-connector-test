package stepdefinitions;


import com.ericsson.itte.mongoconnectortest.Params;
import com.ericsson.itte.mongoconnectortest.util.MongoUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * Step definitions for functional tests
 */
public class TestStepDefinitions {

    @Given("^replica set on the mongo server has been initiated$")
    public void replica_set_on_the_mongo_server_has_been_initiated() throws Throwable {
        // the assumption here is that
        // the rs.initiate() command is executed already on the server
    }

    @When("^I check the configuration on the mongo server$")
    public void I_check_the_configuration_on_the_mongo_server() throws Throwable {
    }

    @Then("^I see right replica set configuration$")
    public void I_see_right_replica_set_configuration() throws Throwable {
        MongoUtil.checkReplicaSet(Params.MONGO_IP, Params.MONGO_HTTP_PORT, Params.MONGO_HOSTNAME, Params.MONGO_PORT);
    }
}
