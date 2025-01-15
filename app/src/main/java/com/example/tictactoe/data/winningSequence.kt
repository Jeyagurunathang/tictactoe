package com.example.tictactoe.data

data class WinningSequencePosition(
    val rowId: Int = 1,
    val colId: Int = 0
)

object WinningSequences {
    fun getWinningSequences(): Map<Int, List<WinningSequencePosition>> {
        return mapOf(
            1 to listOf(WinningSequencePosition(1, 0), WinningSequencePosition(1, 1), WinningSequencePosition(1, 2)),
            2 to listOf(WinningSequencePosition(2, 3), WinningSequencePosition(2, 4), WinningSequencePosition(2, 5)),
            3 to listOf(WinningSequencePosition(3, 6), WinningSequencePosition(3, 7), WinningSequencePosition(3, 8)),
            4 to listOf(WinningSequencePosition(1, 0), WinningSequencePosition(2, 3), WinningSequencePosition(3, 6)),
            5 to listOf(WinningSequencePosition(1, 1), WinningSequencePosition(2, 4), WinningSequencePosition(3, 7)),
            6 to listOf(WinningSequencePosition(1, 2), WinningSequencePosition(2, 5), WinningSequencePosition(3, 8)),
            7 to listOf(WinningSequencePosition(1, 0), WinningSequencePosition(2, 4), WinningSequencePosition(3, 8)),
            8 to listOf(WinningSequencePosition(1, 2), WinningSequencePosition(2, 4), WinningSequencePosition(3, 6))
        )
    }
}