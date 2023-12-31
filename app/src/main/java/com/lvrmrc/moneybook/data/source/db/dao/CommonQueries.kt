package com.lvrmrc.moneybook.data.source.db.dao

const val filterByDayType = "strftime('%Y%m%d', date) = :day AND type = :type";
const val filterByMonthYearType =
    "CAST(strftime('%m', date) AS integer) = :month AND CAST(strftime('%Y', date) AS integer) = :year AND type = :type";
const val filterByYearType = "CAST(strftime('%Y', date) AS integer) = :year AND type = :type";
