package models

import play.api.libs.json.Json

case class Flowchart(id: Long, name: String, side: String, start: Long)


object Flowchart {
  implicit val flowchartFormats = Json.format[Flowchart]
}