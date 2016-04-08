package stepdefinitions;


import com.ericsson.itte.mongoconnectortest.Params;
import com.ericsson.itte.mongoconnectortest.util.CommonUtil;
import com.ericsson.itte.mongoconnectortest.util.ESUtil;
import com.ericsson.itte.mongoconnectortest.util.MongoUtil;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


/**
 * Step definitions for functional tests
 * <p>
 * Note:
 * The verifications/assertions are implemented in the related util methods.
 * We lose readability, ie, we have to reach the util classes to see what is going on.
 * However, if we keep them as private methods in this class,
 * then this class blows up quickly.
 *
 * The idea of making a step class for each feature is great.
 * But some claims might occur in many features.
 *
 * Let us think about how to improve.
 * </p>
 */
public class TestStepDefinitions {

    @Before public void beforeScenario() {
        CommonUtil.cleanUp("beforeScenario");
    }

    @After public void afterScenario() {
        CommonUtil.cleanUp("afterScenario");
    }

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

    @Given("^a mongo server is started with replica set initiated$")
    public void a_mongo_server_is_started_with_replica_set_initiated() throws Throwable {
        MongoUtil.checkReplicaSet(Params.MONGO_IP, Params.MONGO_HTTP_PORT, Params.MONGO_HOSTNAME, Params.MONGO_PORT);
    }

    @And("^an es server is started$") public void an_es_server_is_started()
        throws Throwable {
        ESUtil.checkESStarted(Params.ES_IP, Params.ES_PORT);
    }

    @And("^mongo-connector is started to sync the data between the mongo server and the es server$")
    public void mongo_connector_is_started_to_sync_the_data_between_the_mongo_server_and_the_es_server()
        throws Throwable {
        // TODO
        // Need to discuss how
        // does mongo connector open any server port for checking?
        // shell command to check docker container name?
        // this would be intrusive to docker impl. of the test
        // other ideas?
    }

    @Given("^that I create (\\d+) documents in the mongo server$")
    public void that_I_create_documents_in_the_mongo_server(int arg1) throws Throwable {
        MongoUtil.create10Persons(Params.MONGO_IP, Params.MONGO_PORT, Params.MONGO_HTTP_PORT);
    }

    @When("^I query es after (\\d+) seconds$") public void I_query_es_after_seconds(int arg1)
        throws Throwable {
        CommonUtil.sleep(arg1);
    }

    @Then("^I see all created documents$") public void I_see_all_created_documents()
        throws Throwable {
        ESUtil.check10Persons(Params.ES_IP, Params.ES_PORT);
    }

    @Given("^that there are (\\d+) documents in the mongo server$")
    public void that_there_are_documents_in_the_mongo_server(int arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @When("^I update randomly one of the documents in the mongo server$")
    public void I_update_randomly_one_of_the_documents_in_the_mongo_server() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^I see the document updated$") public void I_see_the_document_updated()
        throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @When("^I delete randomly one of the documents in the mongo server$")
    public void I_delete_randomly_one_of_the_documents_in_the_mongo_server() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^I see the document deleted$") public void I_see_the_document_deleted()
        throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
