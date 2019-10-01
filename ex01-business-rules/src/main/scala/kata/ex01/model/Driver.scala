package kata.ex01.model

import java.io.Serializable


/**
 * @author erikaadno
 */
case class Driver(
  countPerMonth: Int
) extends Serializable {

//  override def toString: String = "Driver(countPerMonth=" + this.getCountPerMonth + ")"
}
