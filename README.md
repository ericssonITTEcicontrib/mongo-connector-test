[comment]: <> (This is a comment, it will not be included)
# mongo-connector-test

[![Build Status](https://travis-ci.org/ericssonITTEcicontrib/mongo-connector-test.svg?branch=master)](https://travis-ci.org/ericssonITTEcicontrib/mongo-connector-test)

Test mongo connector plugin with Elastic Search and MongoDB using Rest Assured, AspectJ, ElasticSearch REST and MongoDB APIs.
* Mongo Connector is [containerized](https://hub.docker.com/r/ericssoneitte/mongo-connector/)
* Mongo Replicate Config is [containerized](https://hub.docker.com/r/ericssoneitte/mongo-replication-utility/)
* A sample docker-compose configuration file used with specific versions of the
container image of mongo connector, MongoDB, and Elasticsearch

## Development Standards
* All contributions must be done via Pull Requests (no exceptions)
* Java files contributed must conform to Google java standards

## Important Contribution Conditions
* This product is licensed to third parties under terms explained on LICENSE file found at the same location
* Contributors understand and accept that all contributions are made to this project with explicit understanding that contributors
only contribute their original work and transfer all rights, privileges and copyrights associated with these contributions
to Ericsson Canada Inc.

## Running tests
```{r, engine='bash', test}
$ docker-compose -f docker-compose-mongoconnector.yml up -d
$ ./gradlew clean cucumber
```
