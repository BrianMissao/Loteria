import java.util.Random;
import java.util.Scanner;

public class Loteria {
    private static int vetorDeNumerosSorteados[] = new int[6];
    private static int[] vetorDeTentativasDoUsuario = new int[6];

    public static void main(String[] args) {
        calcularChancesDeGanhar();
        sortearNumeros();
        obterEArmazenarTentativasDoUsuario();
        calcularEImprimirResultado();
    }

    private static void obterEArmazenarTentativasDoUsuario() {
        Scanner teclado = new Scanner(System.in);
        String numeroDigitadoPeloUsuario;
        String mensagemParaOUsuario;
        int posicaoDaTentativa = 0;
        while (posicaoDaTentativa < vetorDeTentativasDoUsuario.length) {
            mensagemParaOUsuario = "Digite o " + (posicaoDaTentativa + 1) + "O número:";
            System.out.println(mensagemParaOUsuario);
            numeroDigitadoPeloUsuario = teclado.nextLine();
            if (numeroDigitadoPeloUsuario.equals("")) {
                cancelarJogo();
            }
            if (numeroExiste(vetorDeTentativasDoUsuario, Integer.parseInt(numeroDigitadoPeloUsuario))) {
                System.out.println("Número já digitado, digite outro");
                continue;
            }
            vetorDeTentativasDoUsuario[posicaoDaTentativa] = Integer.parseInt(numeroDigitadoPeloUsuario);
            posicaoDaTentativa++;
        }
        System.out.println("Jogo realizado com sucesso!\nSerá que você acertou algum?\nVamos ver os resultados.");
    }

    private static boolean numeroExiste(int[] vetorAVerificar, int numero) {
        for (int posicaoAVerificar = 0; posicaoAVerificar < vetorAVerificar.length; posicaoAVerificar++) {
            if (vetorAVerificar[posicaoAVerificar] == numero) {
                return true;
            }
        }
        return false;
    }

    private static void calcularChancesDeGanhar() {
        long numeroChances = 60;
        long fatorial = numeroChances;
        int quantidadeNaCombinacao = 6;
        int fatorialDaCombinacao = 720;
        for (int controladorDeLoop = 1; controladorDeLoop < quantidadeNaCombinacao; controladorDeLoop++) {
            fatorial -= 1;
            numeroChances *= fatorial;
        }
        numeroChances /= fatorialDaCombinacao;
        System.out.println("Sua chance de ganhar é de 1 em " + numeroChances);
    }

    private static void sortearNumeros() {
        System.out.println("Sorteando números...");
        Random random = new Random();
        int numeroSorteado;
        int posicaoDoNumeroSorteado = 0;
        while (posicaoDoNumeroSorteado < vetorDeNumerosSorteados.length) {
            numeroSorteado = random.nextInt(61);
            if (numeroExiste(vetorDeNumerosSorteados, numeroSorteado)) {
                continue;
            }
            vetorDeNumerosSorteados[posicaoDoNumeroSorteado] = numeroSorteado;
            System.out.println(vetorDeNumerosSorteados[posicaoDoNumeroSorteado]);
            posicaoDoNumeroSorteado++;
        }
    }

    private static void calcularEImprimirResultado() {
        int numeroDeAcertos = 0;
        for (int posicaoDoNumeroQueOUsuarioDigitou = 0; posicaoDoNumeroQueOUsuarioDigitou < vetorDeTentativasDoUsuario.length; posicaoDoNumeroQueOUsuarioDigitou++) {
            if (numeroExiste(vetorDeNumerosSorteados, vetorDeTentativasDoUsuario[posicaoDoNumeroQueOUsuarioDigitou])) {
                numeroDeAcertos++;
            }
        }
        System.out.println("Você acertou " + numeroDeAcertos + " números do jogo.");
    }

    private static void cancelarJogo() {
        System.out.println("Ok, cancelando o jogo...");
        System.exit(0);
    }
}
