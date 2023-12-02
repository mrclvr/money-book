package com.lvrmrc.moneybook.data.source.db.dao


const val filterByYearType = "CAST(strftime('%Y', t.date) AS integer) = :year AND t.type = :type";
const val filterByMonthYearType =
    "CAST(strftime('%m', t.date) AS integer) = :month AND CAST(strftime('%Y', t.date) AS integer) = :year AND t.type = :type";