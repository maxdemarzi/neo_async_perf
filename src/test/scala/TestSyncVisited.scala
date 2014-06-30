package simulations

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import akka.util.duration._
import bootstrap._
import util.parsing.json.JSONArray


class TestSyncVisited extends Simulation {
  val httpConf = httpConfig
    .baseURL("http://localhost:7474")
    .acceptHeader("application/json")
    // Uncomment to see Requests
    //    .requestInfoExtractor(request => {
    //    println(request.getStringData)
    //    Nil
    //  })
    // Uncomment to see Response
    //    .responseInfoExtractor(response => {
    //    println(response.getResponseBody)
    //    Nil
    //  })
    .disableResponseChunksDiscarding

  val testfile = csv("requests.csv").circular

  val scn = scenario("User Visited Unmanaged Extension")
    .during(30) {
    feed(testfile)
    .exec(
      http("Post Visited Unmanaged Request")
        .post("/v1/service/${userid}/visited")
        .body("""{"url": "${url}"}""")
        .check(status.is(201))
      )
      .pause(0 milliseconds, 1 milliseconds)
  }


  setUp(
    scn.users(16).protocolConfig(httpConf)
  )
}
