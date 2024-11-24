import random


def jugar():
    print("DBG - Endevina el Número - DBG")
    print("Endevina un número entre 1 i 100.")

    numero_secret = random.randint(1, 100)
    intents = 0

    while True:
        try:
            resposta = int(input("\nIntrodueix un número: "))

            if resposta < 1 or resposta > 100:
                print("El número ha d'estar entre 1 i 100!")
                continue

            intents += 1
            if resposta < numero_secret:
                print("Massa baix\n")

            elif resposta > numero_secret:
                print("Massa alt\n")

            else:
                print(f"\nFelicitats!\nL'has encertat en {intents} intents")
                break

        except ValueError:
            print("\nIntrodueix un número vàlid")


if __name__ == "__main__":
    jugar()
