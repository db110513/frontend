package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import db.tictactoe.R

class MainActivity : AppCompatActivity() {

    private lateinit var gridLayout: GridLayout
    private lateinit var statusTextView: TextView
    private var currentPlayer = "X"
    private var gameState = Array(3) { arrayOfNulls<String>(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridLayout = findViewById(R.id.gridLayout)
        statusTextView = findViewById(R.id.statusTextView)

        for (i in 0 until 9) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener { onCellClicked(button) }
        }
    }

    private fun onCellClicked(button: Button) {
        if (button.text.isNotEmpty()) return // Evita que es pugui fer clic en una cel·la ocupada

        button.text = currentPlayer
        updateGameState(button.id)

        if (checkForWin()) {
            statusTextView.text = "Jugador $currentPlayer ha guanyat!"
            disableButtons()
            return
        }

        currentPlayer = if (currentPlayer == "X") "O" else "X"
        statusTextView.text = "Jugador $currentPlayer, és el teu torn!"
    }

    private fun updateGameState(buttonId: Int) {
        val row = when (buttonId) {
            R.id.button1 -> 0 to 0
            R.id.button2 -> 0 to 1
            R.id.button3 -> 0 to 2
            R.id.button4 -> 1 to 0
            R.id.button5 -> 1 to 1
            R.id.button6 -> 1 to 2
            R.id.button7 -> 2 to 0
            R.id.button8 -> 2 to 1
            R.id.button9 -> 2 to 2
            else -> return
        }

        gameState[row.first][row.second] = currentPlayer
    }

    private fun checkForWin(): Boolean {
        // Comprova files, columnes i diagonals per guanyador.

        for (i in 0..2) {
            if (gameState[i][0] == currentPlayer && gameState[i][1] == currentPlayer && gameState[i][2] == currentPlayer) return true // Files

            if (gameState[0][i] == currentPlayer && gameState[1][i] == currentPlayer && gameState[2][i] == currentPlayer) return true // Columnes
        }

        if (gameState[0][0] == currentPlayer && gameState[1][1] == currentPlayer && gameState[2][2] == currentPlayer) return true // Diagonal principal

        if (gameState[0][2] == currentPlayer && gameState[1][1] == currentPlayer && gameState[2][0] == currentPlayer) return true // Diagonal secundària

        return false
    }

    private fun disableButtons() {
        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.isEnabled = false // Desactiva tots els botons després d'una victòria.
        }
    }
}