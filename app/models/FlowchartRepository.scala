package models

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class FlowchartRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  private class FlowchartTable(tag: Tag) extends Table[Flowchart](tag, "FLOWCHARTS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("NAME")
    def side = column[String]("SIDE")
    def start = column[Long]("STARTING_BLOCK")
    def * = (id, name, side,start) <> ((Flowchart.apply _).tupled, Flowchart.unapply)
  }

  private val flowcharts = TableQuery[FlowchartTable]
  def list(): Future[Seq[Flowchart]] = db.run {
    flowcharts.result
  }
  def findById(id:Long):Future[Option[Flowchart]] = db.run {flowcharts.filter(_.id === id).result.headOption}

}
