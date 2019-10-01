package kata.ex01.model

import java.io.Serializable
import java.time.LocalDateTime

import kata.ex01.model.RouteType.RouteType
import kata.ex01.model.VehicleFamily.VehicleFamily


/**
 * @author erikaadno
 */
case class HighwayDrive(
  enteredAt: LocalDateTime,
  exitedAt: LocalDateTime,
  vehicleFamily: VehicleFamily,
  routeType: RouteType,
  driver: Driver
) extends Serializable {
//  override def toString: String = "HighwayDrive(enteredAt=" + enteredAt + ", exitedAt=" + this.getExitedAt + ", vehicleFamily=" + this.getVehicleFamily + ", routeType=" + this.getRouteType + ", driver=" + this.getDriver + ")"
}
