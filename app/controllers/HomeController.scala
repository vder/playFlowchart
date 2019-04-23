package controllers

import javax.inject._
import models.{Flow, FlowBlock, FlowBlockRepository, FlowResponse, Flowchart, FlowchartRepository, PersonRepository}
import play.api.Logging
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(
    repo: PersonRepository,
    flowchartRepo: FlowchartRepository,
    flowBlockRepo: FlowBlockRepository,
    cc: MessagesControllerComponents
)(implicit ec: ExecutionContext)
    extends MessagesAbstractController(cc) with Logging {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def hello(name: String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.hello(name))
  }

  def getPersons = Action.async { implicit request: Request[AnyContent] =>
    {
      repo.list().map { people =>
        Ok(Json.toJson(people))
      }
    }

  }

  def getFlow(id: Long) = Action.async {
    implicit request: Request[AnyContent] =>
      flowchartRepo.findById(id).map(x => Ok(Json.toJson(x)))
  }

  def createFlowResponse(
      in: Seq[(FlowBlock, Option[Flow])]
  ): FlowResponse = {
    logger.info("jakiś moj logger")
    val (flowBlockSeq, flowSeq) = in.unzip

    val finalFlow = flowSeq
      .filter({
        case None    => false
        case Some(_) => true
      })
      .map({
        case Some(x) => x
      })
    FlowResponse(
      flowBlockSeq.head.id,
      flowBlockSeq.head.question,
      finalFlow.toList
    )

  }

  def getFlowBlock(id: Long) = Action.async {
    implicit request: Request[AnyContent] =>
      flowBlockRepo
        .findById(id)
        .map(x => {
          if (!x.isEmpty)
            Ok(
              Json.toJson(createFlowResponse(x))
            )
          else Ok("")
        })

  }

}
