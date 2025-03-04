import java.util.Scanner;

public class JogoDaVelha { //variaveis
    private char[][] tabuleiro; //pode-se usar char, devido ser apenas 1 caractere;
    private char jogadorAtual;

    public JogoDaVelha() { //construtor da classe
        tabuleiro = new char[3][3];
        jogadorAtual = 'X';
        inicializarTabuleiro();
    }

    public static void main(String[] args) {
        JogoDaVelha jogo = new JogoDaVelha(); //será necessario instanciar pois os metodos nao são staticos;
        Scanner sc = new Scanner(System.in); //entrada de dados pelo usuário
        boolean gamePlay = true; //se o está rodando ou não o jogo

        while (gamePlay) {
            jogo.exibirTabuleiro(); //mostra um tabuleiro 3x3 com '-' ;
            System.out.printf("Jogador [%s] faça sua Jogada, (linha e coluna): \n", jogo.jogadorAtual);
            System.out.print("Linha: ");
            int linha = sc.nextInt(); //recebe valor da linha a ser inserido
            System.out.print("Coluna: ");
            int coluna = sc.nextInt(); //recebe valor da coluna a ser inserido

            //no local indicado, irá receber o valor do jogadorAtual;
            if (jogo.fazerJogada(linha, coluna)){ //se for retornado true
                if(jogo.verificarVitoria()){ //verifica se com essa jogada teve vencedor
                    jogo.exibirTabuleiro();
                    System.out.printf("Jogador [%s] VENCEEUU!!", jogo.jogadorAtual);
                    gamePlay = false; //jogo não está mais rodando, encerra o while
                }
                else if(jogo.verificarEmpate()){ //verifica se com essa jogada houve empate
                    jogo.exibirTabuleiro();
                    System.out.println("Jogo deu VELHAAAA!");
                    gamePlay = false; //jogo não está mais rodando, encerra o while
                }
                else{
                    jogo.alternarJogador(); //se nao tiver dado vitoria e nem empate, troca de Jogador
                }
            }
        }
        sc.close();
    }

    private void inicializarTabuleiro(){ //nao é estatico porque nao será chamada no main
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                tabuleiro[i][j] = '-'; //identificar o espaço que foi criado, com aspas simples pois é char
            }
        }
    }

    //metodo para fazer a jogada
    private boolean fazerJogada(int linha, int coluna){
        if (linha >= 0 && linha < 3 && coluna >=0  && coluna < 3 && tabuleiro[linha][coluna] == '-'){ 
            //é utilizada == inves de equals devido utilizar char e '';
            tabuleiro[linha][coluna] = jogadorAtual; //a posição que foi passada como parametro receber o valor de jogador
            return true;
        }
        System.err.println("\n ==> Posicao ja preenchida ou não existente, tente novamente <==\n");
        return false;
    }   

    //verificar Vitoria
    private boolean verificarVitoria(){
        for (int i = 0; i < 3; i++ ){
            //verificando vitoria na horizontal e vertical
            if (tabuleiro[i][0] != '-' && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]) return true;
            if (tabuleiro[0][i] != '-' && tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]) return true;
        }

        //verificarndo na diagonal
        //diagonal principal
        if(tabuleiro[0][0] != '-' && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) return true;
        //diagonal secundaria
        if(tabuleiro[0][2] != '-' && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) return true;
        
        //se caso nao for não nenhuma dessas linhas
        return false; //ninguem ganhou
    }

    private boolean verificarEmpate(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++ ){
                if (tabuleiro[i][j] == '-') return false;
            }
        }
        return !verificarVitoria(); // se tiver dado vitoria (true) aqui será false, o contrario do true do metodo
        //se em verificarVitoria() der false, aqui será true, confirmando que deu empate
    }

    private void alternarJogador(){
        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
    }

    private void exibirTabuleiro(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++ ){
                if (j == 2) {
                    System.out.println(tabuleiro[i][j]);
                }else{
                    System.out.print(tabuleiro[i][j]+" ");
                }
            }
        }
    }
}