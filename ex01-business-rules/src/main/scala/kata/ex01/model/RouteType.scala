package kata.ex01.model

/**
 * 道路のエリアタイプ.
 *
 * @author erikaadno
 */
object RouteType extends Enumeration {
  type RouteType = Value
  val

  /** 地方部 */
  RURAL,

  /** 都市部 */
  URBAN = Value
}
