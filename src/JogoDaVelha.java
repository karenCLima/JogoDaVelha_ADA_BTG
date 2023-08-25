import java.util.Scanner;

public class JogoDaVelha {

	public static void main(String[] args) {
		
		Scanner sc =new Scanner(System.in);
		
		int jogaDeNovo = 0;
		boolean jog1TaOn =true;
		int contadorJogadas = 0;
		
		
		while(jogaDeNovo != 2) {
			String [][] jogo = inicializandoJogoETabuleiro();
			do {
				if(jog1TaOn) {
					int [] recebeLinhaEColuna = entradaDePosicoes(1);
					int linha = recebeLinhaEColuna[0];
					int coluna = recebeLinhaEColuna[1];
					
					
					if (jogo[linha-1][coluna-1] == "-") {
						jogo[linha-1][coluna-1] = "X";
						System.out.println("");
						imprimiTabuleiro(jogo);
						boolean ganhouJogo = VerificaSeGanhouJogo(jogo, linha, coluna);
						contadorJogadas++;
						System.out.println(" ");
						if(ganhouJogo) {
							System.out.println("Parabéns Jogador 1 você venceu o jogo!");
							break;
						}else if (contadorJogadas == 9){
							System.out.println("Houve um empate!");
							break;
						}else {
							jog1TaOn = false;
							System.out.println("_______________________");
						}
					}else {
						System.out.println("Esta posição já está ocupada!");
						System.out.println("_______________________");
					}
					
				}else {
					int [] recebeLinhaEColuna = entradaDePosicoes(2);
					int linha = recebeLinhaEColuna[0];
					int coluna = recebeLinhaEColuna[1];
					
					if (jogo[linha-1][coluna-1] == "-") {
						jogo[linha-1][coluna-1] = "O";
						System.out.println("");
						imprimiTabuleiro(jogo);
						boolean ganhouJogo = VerificaSeGanhouJogo(jogo, linha, coluna);
						contadorJogadas++;
						System.out.println(" ");
						if(ganhouJogo) {
							System.out.println("Parabéns Jogador 2 você venceu o jogo!");
							break;
						}else if (contadorJogadas == 9) {
							System.out.println("Houve um empate!");
							break;
						}else {
							jog1TaOn = true;
							System.out.println("_______________________");
						}
					}else {
						System.out.println("Posição inválida!");
						System.out.println("_______________________");
					}
				}
			}while(true);
		System.out.println("");
		System.out.println("Deseja jogar novamente? [1]-Sim, [2]- Não");
		jogaDeNovo = sc.nextInt();
		contadorJogadas = 0;
		}
		System.out.println("Saindo do Jogo!");

	}
	
	
	
	public static String [][] inicializandoJogoETabuleiro() {
		System.out.println("Vamos começar o Jogo da Velha!");
		System.out.println("");
		System.out.println("Tabuleiro:");
		
		String [][] tabuleiro = new String [3][3];
		
		//imprimindo o tabuleiro
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				tabuleiro[i][j]="-";
				System.out.print(tabuleiro[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("O Jogador 1 vai ser 'X' e Jogador 2 vai ser 'O'. ");
		System.out.println("Prontos? Valendo...");
		System.out.println("");
		
		return tabuleiro;
	}
	
	public static int [] entradaDePosicoes(int numeroDoJogador) {
		Scanner sc =new Scanner(System.in);
		int [] posicoes = new int [2];
		System.out.println("Jogador " + numeroDoJogador+ ": ");
		System.out.println("Escolha o numero de uma linha entre 1 e 3: ");
		int linha = sc.nextInt();
		while(linha <1 || linha>3 ) {
			System.out.println("Número inválido. Por favor, escolha um número entre 1 e 3: ");
			linha = sc.nextInt();
		}
		System.out.println("Escolha o numero de uma coluna entre 1 e 3: ");
		int coluna = sc.nextInt();
		while(coluna <1 || coluna>3 ) {
			System.out.println("Número inválido. Por favor, escolha um número entre 1 e 3: ");
			coluna = sc.nextInt();
		}
		posicoes[0] = linha;
		posicoes[1] = coluna;
		return posicoes;
	}
	
	public static void imprimiTabuleiro(String [][] array) {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println("");
		}
		
	}

	
	public static boolean VerificaSeGanhouJogo(String [][] array, int linha, int coluna) {
		boolean ganhouJogo = false;
		boolean ganhouLinha = verificarLinha(array,linha-1);
		boolean ganhouColuna = verificarColuna(array,coluna-1);
		boolean ganhouDiagonalP = verificarDiagonalPrincipal(array);
		boolean ganhouDiagonalS = verificarDiagonalSecundaria(array);
		if(ganhouLinha || ganhouColuna || ganhouDiagonalP || ganhouDiagonalS) {
			return !ganhouJogo;
		}else {
			return ganhouJogo;
		}
		
	}
	
	public static Boolean verificarLinha(String [][] array, int linha) {
		//encontrou elemento diferente na linha
		String elementoL = array[linha][0];
		for(int j=0; j<array[linha].length; j++) {
			if(array[linha][j] != elementoL || array[linha][j] == "-" ) {
				return false;
			}
		}
		return true;
	}
	
	public static Boolean verificarColuna(String [][] array, int coluna) {
		//encontrou elemento diferente na coluna
		String elementoC = array[0][coluna];
		for(int j=0; j<array.length; j++) {
			if(array[j][coluna] == "-" || array[j][coluna] != elementoC ) {
				return false;
			}
		}
		return true;
	}
	
	public static Boolean verificarDiagonalPrincipal(String [][] array) {
		//encontra elementos diferentes na diagonal
		String elementoD = array[0][0];
		for(int i= 0; i<array.length; i++) {
			for (int j=0; j< array[i].length; j++) {
				if(i==j) {
					if(array[i][j] != elementoD || array[i][j] == "-") {
						return false;
					}
				}
			}
		}
		return true;
	}
		
	public static Boolean verificarDiagonalSecundaria(String [][] array) {
		//encontra elementos iguais na diagonal secundaria
		if(array[0][2]==array[1][1] && array[0][2] == array[2][0] && array [1][1] == array[2][0] && array[0][2]!="-" && array[1][1] != "-" && array[2][0]!= "-") {
			return true;
		}
		return false;
	}

}
