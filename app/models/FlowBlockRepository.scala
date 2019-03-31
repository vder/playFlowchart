package models

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class FlowBlockRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import profile.api._

  private class FlowTable(tag: Tag) extends Table[Flow](tag, "FLOWS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def block = column[Long]("STARTING_BLOCK")
    def response = column[String]("RESPONSE")
    def nextBlock = column[Long]("NEXT_BLOCK")
    def * = (id, block, response,nextBlock) <> ((Flow.apply _).tupled, Flow.unapply)
  }

  private class FlowBlockTable(tag: Tag) extends Table[FlowBlock](tag, "FLOWCHART_BLOCKS") {
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def question = column[String]("QUESTION")
    def * = (id, question) <> ((FlowBlock.apply _).tupled, FlowBlock.unapply)
  }
  private val flows = TableQuery[FlowTable]
  private val flowBlocks = TableQuery[FlowBlockTable]

  def list(): Future[Seq[(FlowBlock,Flow)]] = db.run {
    ( flowBlocks join flows on ( _.id === _.block) ).result
  }

  def findById(id: Long): Future[Seq[(FlowBlock, Option[Flow])]] = db.run {
    (flowBlocks.filter(_.id === id) joinLeft flows on (_.id === _.block)).result
  }

}
