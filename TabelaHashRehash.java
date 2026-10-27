import java.util.Random;

public class TabelaHashRehash {

    public static void main(String[] args) {
        int tamanho = 100;
        int limite = 90;
        int vazio = -1;

        int[] matriz = new int[tamanho];
        int[] elementos = new int[tamanho];
        int[] colisoesElemento = new int[tamanho];
        int[] posicaoElemento = new int[tamanho];

        int i = 0;
        while (i < tamanho) {
            matriz[i] = vazio;
            i = i + 1;
        }

        Random aleatorio = new Random(1090);

        int inseridos = 0;
        int totalColisoes = 0;
        int bloqueados = 0;

        for (int n = 0; n < 100; n++) {
            int numero = aleatorio.nextInt(100001);

            if (inseridos >= limite) {
                bloqueados = bloqueados + 1;
            } else {
                int posicao = numero % tamanho;
                int colisoes = 0;

                while (matriz[posicao] != vazio) {
                    colisoes = colisoes + 1;
                    posicao = (posicao + 1) % tamanho;
                }

                matriz[posicao] = numero;
                elementos[inseridos] = numero;
                colisoesElemento[inseridos] = colisoes;
                posicaoElemento[inseridos] = posicao;
                totalColisoes = totalColisoes + colisoes;
                inseridos = inseridos + 1;
            }
        }

        int indice1 = -1;
        int indice2 = -1;
        int indice3 = -1;

        for (int j = 0; j < inseridos; j++) {
            if (indice1 == -1 || colisoesElemento[j] > colisoesElemento[indice1]) {
                indice3 = indice2;
                indice2 = indice1;
                indice1 = j;
            } else if (indice2 == -1 || colisoesElemento[j] > colisoesElemento[indice2]) {
                indice3 = indice2;
                indice2 = j;
            } else if (indice3 == -1 || colisoesElemento[j] > colisoesElemento[indice3]) {
                indice3 = j;
            }
        }

        System.out.println("Total de colisoes na insercao: " + totalColisoes);
        System.out.println("Elementos inseridos: " + inseridos);
        System.out.println("Elementos bloqueados ao chegar em 90%: " + bloqueados);

        System.out.println("Tres elementos que mais colidiram:");
        System.out.println("1. valor " + elementos[indice1] + " com " + colisoesElemento[indice1] + " colisoes, na posicao " + posicaoElemento[indice1]);
        System.out.println("2. valor " + elementos[indice2] + " com " + colisoesElemento[indice2] + " colisoes, na posicao " + posicaoElemento[indice2]);
        System.out.println("3. valor " + elementos[indice3] + " com " + colisoesElemento[indice3] + " colisoes, na posicao " + posicaoElemento[indice3]);

        System.out.println("Posicoes livres:");
        int livres = 0;
        for (int p = 0; p < tamanho; p++) {
            if (matriz[p] == vazio) {
                System.out.println(p);
                livres = livres + 1;
            }
        }
        System.out.println("Total de posicoes livres: " + livres);

        System.out.println("Teste de busca:");

        int busca = 0;
        while (busca < 3) {
            int indice = indice1;
            if (busca == 1) {
                indice = indice2;
            } else if (busca == 2) {
                indice = indice3;
            }

            int valor = elementos[indice];
            int posicao = valor % tamanho;
            int colisoesBusca = 0;
            int encontrada = -1;
            int visitadas = 0;

            while (visitadas < tamanho && encontrada == -1) {
                if (matriz[posicao] == vazio) {
                    visitadas = tamanho;
                } else if (matriz[posicao] == valor) {
                    encontrada = posicao;
                } else {
                    colisoesBusca = colisoesBusca + 1;
                    posicao = (posicao + 1) % tamanho;
                    visitadas = visitadas + 1;
                }
            }

            if (encontrada != -1) {
                System.out.println("Valor " + valor + " encontrado na posicao " + encontrada + " com " + colisoesBusca + " colisoes na busca.");
            } else {
                System.out.println("Valor " + valor + " nao encontrado.");
            }

            busca = busca + 1;
        }
    }
}
