package models

import play.api.libs.json.Json

case class Flow(id:Long, block:Long, response:String, nextBlock:Long )

object Flow {
  implicit val flowFormats = Json.format[Flow]
}