package controllers

import models.{Flow, FlowResponse, Flowchart}
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  *
  * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
  */
class HomeControllerSpec
    extends PlaySpec
    with GuiceOneAppPerTest
    with Injecting {

  "HomeController GET /api/flow/" should {

    "return valid Json response" when {
      "I go to the route /api/flow/1" in {
        val result = route(
          app,
          FakeRequest(controllers.routes.HomeController.getFlowBlock(1))
        )
        contentType(result.get) must be(Some("application/json"))
        contentAsJson(result.get) must be(
          Json.toJson(
            FlowResponse(
              1,
              "Reassessment possible?",
              List(Flow(1, 1, "No", 2), Flow(2, 1, "Yes", 6))
            )
          )
        )
      }
    }
    "return Json without Flow" when {
      "I go to the route /api/flow/14" in {
        val result = route(
          app,
          FakeRequest(controllers.routes.HomeController.getFlowBlock(14))
        )
        contentAsJson(result.get) must be(
          Json.toJson(
            FlowResponse(14, "WOI Muslim at highest DRM", List())
          )
        )
      }
    }
    "return empty response" when {
      "I go to the route /api/flow/30" in {
        val result = route(
          app,
          FakeRequest(controllers.routes.HomeController.getFlowBlock(30))
        )
        status(result.get) must be(OK)
        contentAsString(result.get) must be(empty)
      }
    }
  }

  "HomeController GET /api/flows/" should {
    "return valid flow for id = 1" in {
      val result = route(
        app,
        FakeRequest(controllers.routes.HomeController.getFlow(1))
      )
      contentAsJson(result.get) must be(
        Json.toJson(
          Flowchart(1, "Post-Alert Resolution Flowchart", "US", 1)
        )
      )
    }
  }
}
