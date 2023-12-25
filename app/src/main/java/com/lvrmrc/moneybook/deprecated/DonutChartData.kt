package com.lvrmrc.moneybook.deprecated

import androidx.compose.ui.graphics.Color

private const val TOTAL_ANGLE = 360.0f

data class DonutChartData(
    val amount: Double,
    val color: Color,
    val label: String,
)

class DonutChartDataList(
    var items: List<DonutChartData>
) {
    private var totalAmount: Float = items.sumOf { it.amount }.toFloat()

    /**
     * Calculate the gap width between the arcs based on [gapPercentage]. The percentage is applied
     * to the average count to determine the width in pixels.
     */
    private fun calculateGap(gapPercentage: Float): Float {
        if (this.items.isEmpty()) return 0f

        return (this.totalAmount / this.items.size) * gapPercentage
    }

    /**
     * Returns the total data points including the individual gap widths indicated by the
     * [gapPercentage].
     */
    private fun getTotalAmountWithGapIncluded(gapPercentage: Float): Float {
        val gap = this.calculateGap(gapPercentage)
        return this.totalAmount + (this.items.size * gap)
    }

    /**
     * Calculate the sweep angle of an arc including the gap as well. The gap is derived based
     * on [gapPercentage].
     */
    fun calculateGapAngle(gapPercentage: Float): Float {
        val gap = this.calculateGap(gapPercentage)
        val totalAmountWithGap = this.getTotalAmountWithGapIncluded(gapPercentage)

        return (gap / totalAmountWithGap) * TOTAL_ANGLE
    }

    /**
     * Returns the sweep angle of a given point in the [DonutChartDataList]. This calculations
     * takes the gap between arcs into the account.
     */
    fun findSweepAngle(
        index: Int, gapPercentage: Float
    ): Float {
        val amount = items[index].amount
        val gap = this.calculateGap(gapPercentage)
        val totalWithGap = getTotalAmountWithGapIncluded(gapPercentage)
        val gapAngle = this.calculateGapAngle(gapPercentage)
        return (((((amount + gap) / totalWithGap) * TOTAL_ANGLE)) - gapAngle).toFloat()
    }
}
