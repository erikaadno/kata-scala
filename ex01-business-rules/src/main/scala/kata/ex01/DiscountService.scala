package kata.ex01

import kata.ex01.model.HighwayDrive


/**
 * @author erikaadno
 */
trait DiscountService {
    def calc(drive: HighwayDrive): Long
}
