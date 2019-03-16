package models

import play.api.libs.json.Json

case class FlowBlock (id:Long, question:String)

object FlowBlock {
  implicit val flowBlockFormats = Json.format[FlowBlock]
}