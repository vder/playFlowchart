package models

import play.api.libs.json.Json


case class FlowResponse(id:Long, question: String, answers:List[Flow] )

object FlowResponse {
  implicit val flowFormats = Json.format[FlowResponse]
}