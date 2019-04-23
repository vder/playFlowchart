package controllers

import models.{Flow, FlowResponse}
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

  "HomeController GET" should {

    "return Json contentType" when {
      "I go to the route /api/flows/1" in {
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
    "return exact Json" when {
      "I go to the route /api/flows/1" in {
        val result = route(
          app,
          FakeRequest(controllers.routes.HomeController.getFlowBlock(1))
        )
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

  }
}
