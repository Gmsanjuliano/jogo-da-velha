import java.util.Scanner;
public class JogoDaVelha {
    private static int[][] tabuleiro = new int[3][3];
    private static Scanner scan = new Scanner(System.in);
    private static int jogadas = 0;

    private static void mostrarTabuleiro() {
        for(int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (tabuleiro[linha][coluna] == 1) {
                    System.out.print("|X|");
                } else if (tabuleiro[linha][coluna] == 2) {
                    System.out.print("|O|");
                } else {
                    System.out.print("| |");
                }
            }
            System.out.println();
        }
    }

    private static void apagarTabuleiro() {
        jogadas = 0;
        for(int linha = 0; linha < 3; linha++) {
            for(int coluna = 0; coluna < 3; coluna++) {
                tabuleiro[linha][coluna] = 0;
            }
        }
    }

    private static void pedirJogada(int jogador) {
        while(true) {
            int linha, coluna;
            System.out.println("Vez do jogador" + jogador);
            do {
                System.out.print("Linha: ");
                linha = scan.nextInt();
            } while(linha < 0 || linha > 2);

            do {
                System.out.print("coluna: ");
                coluna = scan.nextInt();
            } while(coluna < 0 || coluna > 2);

            if(tabuleiro[linha][coluna] == 0) {
                tabuleiro[linha][coluna] = jogador;
                break;
            } else {
                System.out.print("JOGADADA INVALIDA!\n");
            }
        }
    }

    private static int finished() {
        for (int jogador = 1; jogador <= 2; jogador++) {
            for(int linha = 0; linha <= 2; linha++) {
                if(tabuleiro[linha][0] == jogador && tabuleiro[linha][1] == jogador && tabuleiro[linha][2] == jogador) {
                    return jogador;
                }
            }
            
            for(int coluna = 0; coluna <= 2; coluna++) {
                if(tabuleiro[0][coluna] == jogador && tabuleiro[1][coluna] == jogador && tabuleiro[2][coluna] == jogador) {
                    return jogador;
                }
            }

            if(tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
                return jogador;
            }

            if(tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
                return jogador;
            }
        }

        if(jogadas == 9) {
            return 0;
        }
        return -1;
    }
    public static void main(String[] args) {
        while(true) {
            apagarTabuleiro();
            while(finished() == -1) {
                for (int jogador = 1; jogador <= 2; jogador++) {
                    mostrarTabuleiro();
                    pedirJogada(jogador);
                    if (finished() > -1) {
                        break;
                    } 
                }
            }
            int win = finished();
            if(win == 0) {
                System.out.println("JOGO EMPATADO!");
            } else {
                System.out.println("O jogador " + win + " VENCEU");
            }
            System.out.println("Voce quer jogador denovo? ");
            String resp;
            resp = scan.nextLine();
            if(resp != "s" || resp != "S") {
                break;
            }
        }
    }
}