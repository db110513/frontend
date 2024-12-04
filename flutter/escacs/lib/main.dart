import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_chess_board/flutter_chess_board.dart';

// s'executa quan l'aplicació comença
void main()  => runApp(Escacs());

// estableix la l'estructura de l'app
class Escacs extends StatelessWidget {

  Widget build(BuildContext context) {

    return MaterialApp(
      theme: ThemeData(
        scaffoldBackgroundColor: Colors.black,
        appBarTheme: AppBarTheme(
          backgroundColor: Colors.black,
          titleTextStyle: TextStyle(color: Colors.red, fontSize: 30, fontWeight: FontWeight.bold),
        ),
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: Tauler(),
      debugShowCheckedModeBanner: false,
    );
  }
}

// necessito un StatefulWidget ja que el tauler es modifica
class Tauler extends StatefulWidget {
  const Tauler({super.key});

  _TaulerState createState() => _TaulerState();
}

// State<Tauler> forma part del StatefulWidget
class _TaulerState extends State<Tauler> {
  late ChessBoardController chessBoard;

  int puntuacioBlanc = 0;
  int puntuacioNegre = 0;

  String tornActual = "Blanc";

  void initState() {
    super.initState();
    // situa el controlador al tauler a l'inici de compilació
    chessBoard = ChessBoardController();
  }

  // funció per iniciar contra un nou rival, marcador canvia
  void reiniciar() {
    setState(() {
      puntuacioNegre = 0;
      puntuacioBlanc = 0;
      chessBoard = ChessBoardController();
      tornActual = "Blanc";
    });
  }

  // funció per reiniciar pel mateix rival, marcador no canvia
  void reiniciarPartida() {
    setState(() {
      chessBoard = ChessBoardController();
      tornActual = "Blanc";
    });
  }

  void movimentBlanques() {
    List<Move> moviments = chessBoard.getPossibleMoves();

    if (moviments.isNotEmpty) comprovarMat();
    else print("La partida ha finalitzat, reinicia'm!!");

  }

  void movimentNegres() {
    setState(() {
      if (!comprovarMat()) {
        movimentBlanques();
        comprovarEscac();
      }
    });
  }

  bool comprovarMat() {
    if (chessBoard.isCheckMate()) {
      setState(() {
        if (tornActual == "Blanc") {
          puntuacioNegre++;
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(
              content: Text("Escac i mat.\nNegres guanyen!!"),
              duration: Duration(seconds: 3),
            ),
          );
        }
        else {
          puntuacioBlanc++;
          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(
              content: Text("Escac i mat.\nBlanques guanyen!!"),
              duration: Duration(seconds: 3),
            ),
          );
        }
      });
      reiniciarPartida();
      return true;
    }
    return false;
  }

  void comprovarEscac() {
    if (chessBoard.isInCheck()) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text("Escac al rei!"),
          duration: Duration(seconds: 3),
        ),
      );
    }
  }

  Widget build(BuildContext context) {
    // fa ús del widget del tauler
    return AnnotatedRegion<SystemUiOverlayStyle>(
      value: SystemUiOverlayStyle(
        statusBarColor: Colors.white,
        systemNavigationBarColor: Colors.white,
        systemNavigationBarIconBrightness: Brightness.dark,
      ),
      child: Scaffold(
        backgroundColor: Colors.white,
        body: SafeArea(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 16.0),
                child: Column(
                  children: [
                    Text(
                      'Escacs DBG',
                      style: TextStyle(
                        fontSize: 35,
                        fontWeight: FontWeight.bold,
                        color: Colors.red,
                      ),
                    ),
                    const SizedBox(height: 35),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          "Blanques: $puntuacioBlanc",
                          style: const TextStyle(fontSize: 18, fontWeight:FontWeight.bold),
                        ),
                        Text(
                          "Negres: $puntuacioNegre",
                          style: const TextStyle(fontSize: 18, fontWeight:FontWeight.bold),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
              SizedBox(height: 20),
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 16.0),
                child: Center(
                  child: ChessBoard(
                    controller: chessBoard,
                    enableUserMoves: true,
                    onMove: () {
                      setState(() {
                        tornActual = tornActual == "Blanc" ? "Negre" : "Blanc";
                      });
                      movimentNegres();
                    },
                  ),
                ),
              ),
              SizedBox(height: 60),
              ElevatedButton(
                onPressed: reiniciar,
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.red,
                ),
                child: Icon(Icons.refresh, color: Colors.white),
              ),

            ],
          ),
        ),
      ),
    );
  }

}
