package aplicacao;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import biblioteca.Jogo;
import biblioteca.Input;

//Importando do pacote para desenhar
public class Main extends Canvas implements Runnable{ //Canvas é a classe que tem metodos de tela e reconhecimento de tecla

	private static final long serialVersionUID = 1L;
	private double fps;
	private boolean gameOn = false;
	private Thread thread;
	private Input input;
	private Jogo jogo;

	
	public static void main(String []args){
		JFrame janela = new JFrame();
		Main jogo = new Main();

		janela.setTitle("Jogo da Cobrinha 2.0");
		janela.add(jogo); //canvas em janela
		janela.pack();
		janela.setVisible(true);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLocationRelativeTo(null);

		jogo.start();
	}
	
	public Main(){ //construtor da classe Main
		this.jogo = new Jogo();
		this.input = new Input(); //inicializa input e passa ele pra Game
		this.jogo.init(input); 
		this.jogo.setup();
		this.fps = 1000/jogo.getFps();
		this.setPreferredSize(new Dimension(jogo.getLargura()-10,jogo.getAltura()-10));
		this.addKeyListener(input); //método que passa enventos de teclado para o Canvas
	}

	public void start(){
		gameOn = true;
		thread = new Thread(this);
		thread.start();
		requestFocus();
	}

	@Override //implementando a interface Runnable
	//Inicio da aplicacao
	public void run(){
		long seg = System.currentTimeMillis();
		long tempo = 0;
		while(gameOn){
			tempo += System.currentTimeMillis() - seg;
			seg = System.currentTimeMillis();
			if(tempo >= fps){     //
				checkInput();     // da classe jogo pra checar o input
				update();	      //construtor da classe Game pra atualizar o jogo
				tempo = 0;
			}
			desenho();
		}
	}


	public void desenho(){
		BufferStrategy buffer = getBufferStrategy();
		if(buffer == null){
			createBufferStrategy(3);
			return;
		}

		Graphics grafico = buffer.getDrawGraphics();

		jogo.desenho(grafico);

		grafico.dispose();
		buffer.show();
	}

	public void checkInput(){
		jogo.checkInput();
	}
	public void update(){
		jogo.update();
	}


}