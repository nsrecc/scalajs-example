package example

import org.scalajs.dom
import dom.html

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scalatags.JsDom.all._

@JSExport
object ScalaJSExample {

  @JSExport
  def matchList(target: html.Div): Unit = {
    val fruits = List("apple", "banana", "clementine", "dragon fruit", "elderberry")

    val myInput = input.render
    val output = div.render

    myInput.onkeyup = (e: dom.Event) => {
      output.innerHTML = ""
      output.appendChild(
        ul(
          for {
            fruit <- fruits
            if fruit.contains(myInput.value)
          } yield {
            myInput.value match {
              case "" => li(hidden)
              case _ => li(fruit)
            }
          }
        ).render
      )
    }

    target.appendChild(
      div(
        p("Given the list of fruits below, type some letters and the fruits containing the string of letters typed will show up."),
        p("Fruits: " + fruits.mkString(", ")),
        myInput,
        output
      ).render
    )
  }

  @JSExport
  def mirror(mirrorInput: html.Input, box: html.Div): Unit = {
    mirrorInput.onkeyup = (e: dom.Event) => {
      box.textContent = mirrorInput.value
    }
  }

  @JSExport
  def mirror(box: html.Div): Unit = {
    val mirrorInput2 = input.render
    val output2 = div.render

    mirrorInput2.onkeyup = (e: dom.Event) => {
      output2.textContent = mirrorInput2.value
    }

    box.appendChild(
      div(
        p("Scala.js / ScalaTags Text Mirror"),
        mirrorInput2,
        output2
      ).render
    )
  }

  case class TwoVarMath(n1: Int, mS: String, n2: Int)

  @JSExportTopLevel("example.computeMath")
  def computeMath(num1: html.Input, mathSym: html.Select, num2: html.Input, target: html.Div): Unit = {
    val number1 = num1.valueAsNumber
    val mathSymbol = mathSym.value
    val number2 = num2.valueAsNumber
    val operation = TwoVarMath(number1, mathSymbol, number2)
    val result = operation match {
      case TwoVarMath(_, "*", _) => number1 * number2
      case TwoVarMath(_, "/", _) => number1 / number2
      case TwoVarMath(_, "+", _) => number1 + number2
      case TwoVarMath(_, "-", _) => number1 - number2
      case TwoVarMath(_, "%", _) => number1 % number2
    }
    target.textContent = "Result: " + result
  }

  @JSExportTopLevel("example.computeMath")
  def computeMath(target: html.Div): Unit = {
    val num1 = input(`type`:="number", required).render
    val operator = select(
      option("*"),
      option("/"),
      option("+"),
      option("-"),
      option("%")
    ).render
    val num2 = input(`type`:="number", required).render
    val compMath = input(`type`:="submit", value:="Compute math!").render
    val mathForm = form(onsubmit:="return false;", num1, operator, num2, compMath).render
    val output = div.render

    compMath.onclick = (e: dom.Event) => {
      val number1 = num1.valueAsNumber
      val mathSymbol = operator.value
      val number2 = num2.valueAsNumber
      val operation = TwoVarMath(number1, mathSymbol, number2)
      val result = operation match {
        case TwoVarMath(_, "*", _) => number1 * number2
        case TwoVarMath(_, "/", _) => number1 / number2
        case TwoVarMath(_, "+", _) => number1 + number2
        case TwoVarMath(_, "-", _) => number1 - number2
        case TwoVarMath(_, "%", _) => number1 % number2
      }
      output.textContent = "Result: " + result
    }

    target.appendChild(
      div(
        p("Scala.js / ScalaTags Two Integer Math"),
        mathForm,
        output
      ).render
    )
  }
}
