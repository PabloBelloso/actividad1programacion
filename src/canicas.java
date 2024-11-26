
/*
Belloso Dueñas Pablo
*/

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class canicas {
    public static void main(String[] args) {
        // Inicialización de variables para que siempre empecemos con 10 canicas cada uno.
        int canicasJugador1 = 10;
        int canicasJugador2 = 10;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // Decidimos quién empieza (sorteo aleatorio entre 1 y 2).
        int turno = random.nextInt(2) + 1;  // Elige entre 1 o 2, el + 1 es para que no salga el cero.
        System.out.println(turno);
        // con el if pongo en consola que jugador empieza; si tú(Jugador1/turno1) o la máquina(Jugador2/turno2)
        if (turno == 1) {
            System.out.println("Comienza el juego.");
            System.out.println("El Jugador " + turno + " intenta adivinar si el jugador2 tiene un número par o impar.");
        }else{
            System.out.println("Comienza el juego.");
            System.out.println("El Jugador " + turno + " intenta adivinar si el jugador1 tiene un número par o impar.");}

        // El juego se desarrolla dentro de un bucle while que se ejecuta mientras ambos jugadores tengan canicas.
        while (canicasJugador1 > 0 && canicasJugador2 > 0) {
            // ahora con un If tenemos que saber si empezamos con el turno1(jugador1) o el turno 2(jugador2, la máquina)
            if (turno == 1) {
                System.out.println("************************** TURNO JUGADOR 1 **************************");
                System.out.println("Jugador 1: Tienes " + canicasJugador1 + " canicas.");
                int apuestaJugador1 = 0;
                boolean apuestaValida = false;
                // Solicitar apuesta hasta que sea válida
                while (!apuestaValida) {
                    System.out.print("¿Cuántas canicas apuestas? (No puedes apostar más de las que tienes): ");
                    try{
                        apuestaJugador1 = scanner.nextInt();
                        // Validar que la apuesta sea válida
                        if (apuestaJugador1 < 1 || apuestaJugador1 > canicasJugador1) {
                            System.out.println("Apuesta una cantidad acorde a las que tienes.");
                        } else {
                            apuestaValida = true;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(" Por favor ingresa una apuesta menor o igual a su número de canicas.");
                    } catch (InputMismatchException a) {
                        System.out.println("Entrada inválida. Por favor ingresa un número entero.");
                        scanner.nextLine();
                    }
                }
                /* Adivinar si el número de canicas del jugador 2 es par o impar; */
                String adivinanza1 = "";
                boolean adivinanzaValida = false;
                while (!adivinanzaValida) {
                    System.out.print("¿El número de canicas del jugador 2 es par o impar? (par/impar): ");
                    adivinanza1 = scanner.next().toLowerCase();
                    if (adivinanza1.equals("par") || adivinanza1.equals("impar")) {
                        adivinanzaValida = true;
                    } else {
                        System.out.println("Entrada inválida. Debes escribir 'par' o 'impar'.");
                    }
                }
                // El jugador 2, la máquina dispone un número aleatorio de canicas
                int apuestaJugador2 = random.nextInt(Math.min(canicasJugador1, canicasJugador2)) + 1;
                //Esta apuesta estará comprendida entre 1 y el menor número de canicas que tengan ambos jugadores.
                // Esto asegura que ningún jugador apueste más canicas de las que tiene.
                boolean esParJugador2 = (apuestaJugador2 % 2 == 0);
                boolean adivinanzaCorrecta = (adivinanza1.equals("par") && esParJugador2) || (adivinanza1.equals("impar") && !esParJugador2);
                System.out.println("\nResultado");
                if (adivinanzaCorrecta) {
                    System.out.println("Jugador 1 GANA " + apuestaJugador2 + " canicas ya que el Jugador 2 tenía " + apuestaJugador2 + " canicas.");
                    canicasJugador1 += apuestaJugador2;
                    canicasJugador2 -= apuestaJugador2; // le añadimos la apuesta del jugador dos al uno, por lo cual decrementamos dicha apuesta al jugador2.
                } else {
                    System.out.println("Jugador 2 GANA " + apuestaJugador1 + " canicas ya que el Jugador 1 tenía " + apuestaJugador1 + " canicas.");
                    canicasJugador2 += apuestaJugador1;
                    canicasJugador1 -= apuestaJugador1; // lo mismo pero a la inversa.
                }
                turno = 2;  // Cambia el turno al jugador 2. Así una vez que se han actualizado las variables,
                // se cambia al turno 2,
                // y se ejecuta el mismo código adaptándolo a la situación inversa del turno1.
            } else if (turno == 2) {
                // Turno del jugador 2 (ordenador).
                System.out.println("************************** TURNO JUGADOR 2 **************************");
                System.out.println("Jugador 2: Tienes " + canicasJugador2 + " canicas.");
                int apuestaJugador2 = random.nextInt(Math.min(canicasJugador1, canicasJugador2)) + 1;
                // El jugador 1 apuesta un número.
                int apuestaJugador1 = 0;
                boolean apuestaValida = false;
                // Solicitar apuesta hasta que sea válida.
                while (!apuestaValida) {
                    System.out.print("Jugador 1: ¿Cuántas canicas apuestas? (No puedes apostar más de las que tienes): ");
                    // con el try catch capturamos las posibles excepciones que puedan surgir,
                    // como apostar de más, o equivocarse con el tipo de dato.
                    try{
                        apuestaJugador1 = scanner.nextInt();
                        // Validar que la apuesta sea válida. No puedes apostar 0 (menor que uno)
                        if (apuestaJugador1 < 1 || apuestaJugador1 > canicasJugador1) {
                            System.out.println("Apuesta una cantidad acorde a las que tienes.");
                        } else {
                            apuestaValida = true;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(" Por favor ingresa una apuesta menor o igual a su número de canicas.");
                    } catch (InputMismatchException a) {
                        System.out.println("Tipo de dato incorrecto. Por favor ingresa un número entero.");
                        scanner.nextLine();
                    }

                }
                // El jugador 2 elige si el número de canicas del jugador 1 es par o impar.
                String adivinanza2 = (random.nextBoolean()) ? "par" : "impar";

                // El jugador 2 adivina correctamente si el número es par o impar.
                boolean esParJugador1 = (apuestaJugador1 % 2 == 0);
                boolean adivinanzaCorrecta2 = (adivinanza2.equals("par") && esParJugador1) || (adivinanza2.equals("impar") && !esParJugador1);

                // Seguimos la misma estructura que cuando el turno es igual 1; pero modificando las variables adivinanza. Las operaciones funcionan de la misma forma.
                if (adivinanzaCorrecta2) {
                    System.out.println("\nResultado");
                    System.out.println("Jugador 2 GANA " + apuestaJugador1 + " canicas ya que el Jugador 1 tenía " + canicasJugador1 + " canicas.");
                    canicasJugador2 += apuestaJugador1;
                    canicasJugador1 -= apuestaJugador1;
                } else {
                    System.out.println("\nResultado");
                    System.out.println("Jugador 1 GANA " + apuestaJugador2 + " canicas ya que el Jugador 2 tenía " + canicasJugador2 + " canicas.");
                    canicasJugador1 += apuestaJugador2;
                    canicasJugador2 -= apuestaJugador2;
                }
                System.out.println("Jugador 1 tiene " + canicasJugador1 + " canicas.");
                System.out.println("Jugador 2 tiene " + canicasJugador2 + " canicas.");
                System.out.println("************************** FIN TURNO JUGADOR 2 **************************\n");

                turno = 1;  // Cambia el turno al jugador 1
            }
            // El juego termina cuando un jugador se queda sin canicas.
            if (canicasJugador1 == 0) {
                System.out.println("\nEl jugador 2 gana, ya que el jugador 1 se quedó sin canicas.");
            } else {
                System.out.println("\nEl jugador 1 gana, ya que el jugador 2 se quedó sin canicas.");
            }
        }
    }
}

