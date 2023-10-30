package com.example.katche_it.activities.activity

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.katche_it.R
import com.example.katche_it.R.drawable
import com.example.katche_it.R.layout

class PuzzleGameActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var puzzleAdapter: PuzzleAdapter
    private lateinit var puzzlePieces: MutableList<PuzzlePiece>
    private var emptyPosition = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_puzzle_game)

        gridView = findViewById(R.id.gridView)
        puzzlePieces = generatePuzzlePieces().toMutableList()
        puzzleAdapter = PuzzleAdapter(this, puzzlePieces)
        gridView.adapter = puzzleAdapter

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, view, position, _ ->
            handlePieceClick(position)
        }
    }

    private fun generatePuzzlePieces(): List<PuzzlePiece> {
        val pieceResourceIds = listOf(
            drawable.image_part_001,
            drawable.image_part_002,
            drawable.image_part_003,
            drawable.image_part_004,
            drawable.image_part_005,
            drawable.image_part_006,
            drawable.image_part_007,
            drawable.image_part_008,
            drawable.image_part_009
            /*drawable.among_us_cyan_piece9,
            drawable.among_us_cyan_piece8,
            drawable.among_us_cyan_piece7,
            drawable.among_us_cyan_piece6,
            drawable.among_us_cyan_piece5,
            drawable.among_us_cyan_piece4,
            drawable.among_us_cyan_piece3,
            drawable.among_us_cyan_piece2,
            drawable.among_us_cyan_piece1*/

        )

        return pieceResourceIds.mapIndexed { index, resourceId ->
            PuzzlePiece(index, resourceId)
        }
    }

    private fun handlePieceClick(position: Int) {
        if (isAdjacentToEmpty(position)) {
            swapPieces(position, emptyPosition)
            emptyPosition = position

            if (isPuzzleCompleted()) {
                val toastText =
                    "Congratulations! You've successfully earned a reward \uD83D\uDE00\uD83D\uDE00"
                Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CouponActivity::class.java)
                startActivity(intent)
                finish()
            }

            puzzleAdapter.notifyDataSetChanged()
        } else {
            showToast("Invalid Move")
        }
    }

    private fun isAdjacentToEmpty(position: Int): Boolean {
        val row = position / 3
        val col = position % 3
        val emptyRow = emptyPosition / 3
        val emptyCol = emptyPosition % 3
        return (row == emptyRow && Math.abs(col - emptyCol) == 1) || (col == emptyCol && Math.abs(row - emptyRow) == 1)
    }

    private fun swapPieces(position1: Int, position2: Int) {
        val temp = puzzlePieces[position1]
        puzzlePieces[position1] = puzzlePieces[position2]
        puzzlePieces[position2] = temp
    }

    private fun isPuzzleCompleted(): Boolean {
        return puzzlePieces.withIndex().all { (index, piece) -> piece.id == index }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
