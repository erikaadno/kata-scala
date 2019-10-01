package kata.ex01.util

import java.time.LocalDate
import org.scalatest.FunSpec

/**
 * @author erikaadno
 */
class HolidayUtilsTest extends FunSpec {
  describe("test元日は休日") {
    assert(HolidayUtils.isHoliday(LocalDate.of(2019, 1, 1)))
    assert(!HolidayUtils.isHoliday(LocalDate.of(2019, 1, 2)))
    assert(!HolidayUtils.isHoliday(LocalDate.of(2019, 1, 3)))
    assert(HolidayUtils.isHoliday(LocalDate.of(2019, 1, 5)))
  }
}
