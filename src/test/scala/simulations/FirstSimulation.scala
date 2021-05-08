package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class FirstSimulation extends Simulation {

  //This is required to put all the common request configurations, helps reducing the code in scenarios.
  val httpConf = http
    .baseUrl("https://reqres.in")
    .headers(Map("Content-Type" -> "application/json"));

  // This is API request(GET request example) which will be used for load test.
  val getRequest = scenario("GET-Request-Example")

    .exec(
      http("Testing GET Request")
        .get("/api/users?page=1")
        .check(status is 200)
    )

  // This is API request(PUT request example) which will be used for load test.
  val postRequest = scenario("API Performance as code using scala example.")

    .exec(
      http("Segments API Test post example")
        .post("/api/users")
        .body(StringBody(
          """{
            |    "name": "morpheus",
            |    "job": "leader"
            |}""".stripMargin)).asJson
        .check(status is 201)
    )

  // test load setup for the APIs
  var testSetup =
    setUp(
      postRequest.inject(
        constantConcurrentUsers(1) during (1 seconds), //Constant 1 user for 1 seconds
      )
    ).protocols(httpConf)


  /*
  Other Thread load options.
  constantConcurrentUsers(100) during (1 minutes),  //Constant 100 user for 1 minute
  constantConcurrentUsers(250) during (1 minutes), //Constant 250 user for next 1 minute
  rampConcurrentUsers(500) to (2000) during (5 minutes), //Rampup 500-2000 in given minutes user.
  constantConcurrentUsers(2000) during (1 minutes), // Holding 2000 Users for 1 minute
  rampConcurrentUsers(2000) to (0) during (15 seconds) // Rampup 2000-0 in given minutes user.
*/

}