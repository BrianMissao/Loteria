import java.util.Random;
import java.util.Scanner;

public class Loteria {
    private static int vetorDeNumerosSorteados[] = new int[6];
    private static int[] vetorDeTentativasDoUsuario = new int[6];

    public static void main(String[] args) {
        calcularChancesDeGanhar(6, 60);
        obterEArmazenarTentativasDoUsuario();
        realizarSorteio();
        calcularEExibirResultadoDoJogo();
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
        System.out.println("Jogo realizado com sucesso!\nSerá que você acertou algum?");
    }

    private static boolean numeroExiste(int[] vetorAVerificar, int numero) {
        for (int posicaoAVerificar = 0; posicaoAVerificar < vetorAVerificar.length; posicaoAVerificar++) {
            if (vetorAVerificar[posicaoAVerificar] == numero) {
                return true;
            }
        }
        return false;
    }

    private static void calcularChancesDeGanhar(int numeroDeBolasNoJogo, int numerosDoJogo) {
        long numeroDeChances = calcularCombinacoes(numerosDoJogo, numeroDeBolasNoJogo);
        long fatorialDeBolasNoJogo = calcularCombinacoes(numeroDeBolasNoJogo, numeroDeBolasNoJogo);
        numeroDeChances /= fatorialDeBolasNoJogo;
        System.out.println("Sua chance de ganhar é de 1 em " + numeroDeChances);
    }

    private static long calcularCombinacoes(long numeroACalcular, long limiteDaMultiplicacao) {
        long antecessor = numeroACalcular;
        for (int controladorDeLoop = 1; controladorDeLoop < limiteDaMultiplicacao; controladorDeLoop++) {
            antecessor -= 1;
            numeroACalcular *= antecessor;
        }
        return numeroACalcular;
    }

    private static void realizarSorteio() {
        Random random = new Random();
        int numeroSorteado;
        int posicaoDoNumeroSorteado = 0;
        while (posicaoDoNumeroSorteado < vetorDeNumerosSorteados.length) {
            numeroSorteado = random.nextInt(61);
            if (numeroExiste(vetorDeNumerosSorteados, numeroSorteado)) {
                continue;
            }
            vetorDeNumerosSorteados[posicaoDoNumeroSorteado] = numeroSorteado;
            posicaoDoNumeroSorteado++;
        }
    }

    private static void exibirNumerosSorteados() {
        System.out.println("Os números sorteados foram:");
        for (int posicaoDoNumeroSorteado = 0; posicaoDoNumeroSorteado < vetorDeNumerosSorteados.length; posicaoDoNumeroSorteado++) {
            System.out.println(vetorDeNumerosSorteados[posicaoDoNumeroSorteado]);
        }
    }

    private static void calcularEExibirResultadoDoJogo() {
        int numeroDeAcertos = 0;
        for (int posicaoDoNumeroQueOUsuarioDigitou = 0; posicaoDoNumeroQueOUsuarioDigitou < vetorDeTentativasDoUsuario.length; posicaoDoNumeroQueOUsuarioDigitou++) {
            if (numeroExiste(vetorDeNumerosSorteados, vetorDeTentativasDoUsuario[posicaoDoNumeroQueOUsuarioDigitou])) {
                numeroDeAcertos++;
            }
        }
        exibirNumerosSorteados();
        System.out.println("Você acertou " + numeroDeAcertos + " números do jogo.");
    }

    private static void cancelarJogo() {
        System.out.println("Ok, cancelando o jogo...");
        System.exit(0);
    }
}
