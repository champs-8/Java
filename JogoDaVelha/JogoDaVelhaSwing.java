import javax.swing.*; //biblioteca grafica
import java.awt.*; //
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JogoDaVelhaSwing { //variaveis
    private JFrame frame; //criar janelas
    private JButton[][] botoes; //criar botoes
    private char[][] tabuleiro; //pode-se usar char, devido ser apenas 1 caractere;
    private char jogadorAtual;

    public JogoDaVelhaSwing() { //construtor da classe
        frame = new JFrame("Jogo da Velha"); //cria uma nova janela
        frame.setSize(300,350); //dimensões da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Condição padrao para fechar; Sair ao fechar
        frame.setLayout(new BorderLayout()); //tipo de layout

        JPanel painelJogo = new JPanel(new GridLayout(3, 3)); //vai criar um painel 3x3 grid
        botoes = new JButton[3][3];
        tabuleiro = new char[3][3];
        jogadorAtual = 'X';
        inicializarTabuleiro();

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                botoes[i][j] = new JButton(""); //cria um botão vazio
                botoes[i][j].setFont(new Font("Arial", Font.BOLD, 40)); //formatação visual
                botoes[i][j].setFocusPainted(false); //delimitador simples ao redor do texto
                painelJogo.add(botoes[i][j]); //adiciona no grid 3x3

                int finalI = i;
                int finalJ = j;
                botoes[i][j].addActionListener(new ActionListener() { //ação ao pressionar um botão
                    @Override //vai sobreescrever um metodo
                    public void actionPerformed(ActionEvent e) {
                        if (tabuleiro[finalI][finalJ] == '-') { //formato que inicializou o tabuleiro vazio
                            tabuleiro[finalI][finalJ] = jogadorAtual;
                            botoes[finalI][finalJ].setText(String.valueOf(jogadorAtual)); //receberá o texto no botão

                            if (verificarVitoria()) {
                                JOptionPane.showMessageDialog(frame, "Jogador " +jogadorAtual+ " VENCEU!" );
                                reiniciarJogo();
                            }else if(verificarEmpate()){
                                JOptionPane.showMessageDialog(frame, "Jogo deu VELHA!");
                                reiniciarJogo();
                            }else{
                                alternarJogador();
                            }
                        }
                    }
                });
            }
        }

        JButton reiniciarBotao = new JButton("Reiniciar Jogo");
        reiniciarBotao.addActionListener(e -> reiniciarJogo());
    
        frame.add(painelJogo, BorderLayout.CENTER); //painel vai ficar centralizado
        frame.add(reiniciarBotao, BorderLayout.SOUTH); //botao reiniciar vai ficar ao sul
        frame.setVisible(true); //sera´visivel tudo
    }


    private void inicializarTabuleiro(){ //nao é estatico porque nao será chamada no main
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                tabuleiro[i][j] = '-'; //identificar o espaço que foi criado, com aspas simples pois é char
            }
        }
    }

    //reiniciar Jogo
    private void reiniciarJogo(){
        inicializarTabuleiro();
        jogadorAtual = 'X';
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                botoes[i][j].setText("");
            }
        }
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

    public static void main(String[] args) {
        new JogoDaVelhaSwing(); //incializa
    }   
}
