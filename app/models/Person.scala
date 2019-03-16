package models

import play.api.libs.json.Json

case class Person(id: Long, firstName: String, surname: String)


object Person {
  implicit val personFormats = Json.format[Person]
}