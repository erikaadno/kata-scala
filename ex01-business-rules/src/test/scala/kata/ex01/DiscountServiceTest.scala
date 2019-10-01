package kata.ex01

import java.time.LocalDateTime

import kata.ex01.model.RouteType.RURAL
import kata.ex01.model.VehicleFamily.STANDARD
import kata.ex01.model.{Driver, HighwayDrive}
import org.scalatest.FunSpec


/**
 * @author erikaadno
 */
class DiscountServiceTest extends FunSpec {
  val discountService = new DiscountServiceImpl

  describe("DiscountService") {
    it("平日朝夕割引") {
      val drive: HighwayDrive = HighwayDrive(
        enteredAt = LocalDateTime.of(2016, 3, 31, 23, 0),
        exitedAt = LocalDateTime.of(2016, 4, 1, 6, 30),
        vehicleFamily = STANDARD,
        routeType = RURAL,
        driver = Driver(10)
      )

      val actual: Long = discountService.calc(drive)
      val expected: Long = 50L
      assert(actual == expected)
    }


    it("休日朝夕は休日割が適用される") {
      val drive = HighwayDrive(
        enteredAt = LocalDateTime.of(2016, 4, 1, 23, 0),
        exitedAt = LocalDateTime.of(2016, 4, 2, 6, 30),
        vehicleFamily = STANDARD,
        routeType = RURAL,
        driver = Driver(10)

      )
      val actual: Long = discountService.calc(drive)
      val expected: Long = 30L
      assert(actual == expected)
    }
  }
}
