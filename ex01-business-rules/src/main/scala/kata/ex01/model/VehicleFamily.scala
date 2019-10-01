package kata.ex01.model

/**
 * 車種.
 *
 * @author erikaadno
 */
object VehicleFamily extends Enumeration {
  type VehicleFamily = Value
  val

  /** 普通車 */
  STANDARD,

  /** 軽自動車 */
  MINI,

  /** 二輪車 */
  MOTORCYCLE,

  /** その他 */
  OTHER = Value
}
