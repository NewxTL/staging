package com.newx.staging.util;

import java.math.BigDecimal;

/**
 * Created by Newx on 2017/9/12.
 */
public class MoneyUtil {

    public static BigDecimal amountDecimal(BigDecimal bigDecimal){
        BigDecimal returnDate = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return returnDate;
    }
}
