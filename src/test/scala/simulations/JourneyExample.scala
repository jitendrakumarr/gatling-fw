package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class JourneyExample extends Simulation {

  // request configurations.
  val httpConf = http
    .baseUrl("https://reqres.in")
    .headers(Map("Content-Type" -> "application/json"));

  // API Call 1
  val getRequest = scenario("GET-Request-Example")
    .exec(
      http("Testing GET Request")
        .get("/api/users?page=1")
        .check(status is 200)
    )

  // API Call 2
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

  var journey = scenario("Journey-example-with-multiple-api-calls").exec(getRequest).exec(postRequest)

  // test load setup for the APIs
  var testSetup =
    setUp(
      journey.inject(
        constantConcurrentUsers(10) during (10 seconds), //Constant 1 user for 1 seconds
      )
    ).protocols(httpConf)
}