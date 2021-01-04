package biblioteca;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Jogo extends Janela{
	//cobrinha
	int tamanho;
	int velocidade_x;
	int velocidade_y;
	int tamanho_max;
	int[][] cobra;
	int tamanho_atual;
	int comida_x;
	int comida_y;
	Random randomizador;
	
	public Jogo(){
		super(500, 500, 30);
		this.tamanho=10;
		this.velocidade_x = 10;
		this.velocidade_y = 0;
		this.tamanho_max = 100;
		this.cobra = new int[tamanho_max][2];
		this.tamanho_atual = 4;
		
		//Posição da comida
		this.randomizador = new Random(); // gerando instancia randomizador da classe Random
		this.comida_x =randomizador.nextInt(450)+25; //Gera valor (prox inteiro menor que 450) +25
		this.comida_y =randomizador.nextInt(450)+25;
	}


	public void setup(){ //Modifica os parâmetros de janela
		for (int i = 0;i < tamanho_max; i++ ) {
			for (int j = 0;j < 2; j++ ) {
				cobra[i][j] = -1; //inicializa vazio
			}	
		}
		//diz aonde está cada parte da cobra
		cobra[0][0] = 250;
		cobra[0][1] = 250;
		cobra[1][0] = 240;
		cobra[1][1] = 250;
		cobra[2][0] = 230;
		cobra[2][1] = 250;
		cobra[3][0] = 220;
		cobra[3][1] = 250;
		cobra[4][0] = 210;
		cobra[4][1] = 250;

		mapearTecla("UP",KeyEvent.VK_UP);
		mapearTecla("DOWN",KeyEvent.VK_DOWN);
		mapearTecla("RIGHT",KeyEvent.VK_RIGHT);
		mapearTecla("LEFT",KeyEvent.VK_LEFT);
	}

	public void desenho(Graphics grafico){
		
		//Desenho e coloração
		grafico.setColor(Color.BLACK);
		grafico.fillRect(0,0,getLargura(),getAltura()); //Pegando valor declarado para criar retangulo e pintar

		grafico.setColor(Color.GREEN);
		for (int i = 0;i < tamanho_max;i++) {
			if(cobra[i][0] != -1)
				grafico.fillRect(cobra[i][0],cobra[i][1],tamanho,tamanho);	//Preenchendo cada posição declarada da cobra
		}

		grafico.setColor(Color.RED);
		grafico.fillOval(comida_x,comida_y,tamanho,tamanho);	 //Gerando uma esfera vermelha na posicao da comida gerada
	}

	
	//Leitura dos inputs do teclado
		public void checkInput(){
			if(isKeyPressed("UP") && velocidade_y == 0){
				velocidade_x = 0;
				velocidade_y = -tamanho;
			}else if(isKeyPressed("DOWN") && velocidade_y == 0){
				velocidade_x = 0;
				velocidade_y = tamanho;
			}else if(isKeyPressed("RIGHT") && velocidade_x == 0){
				velocidade_x = tamanho;
				velocidade_y = 0;
			}else if(isKeyPressed("LEFT") && velocidade_x == 0){
				velocidade_x = -tamanho;
				velocidade_y = 0;
			}
		}
	
	public void update(){
		
		//Colisão com a comida
		int dx,dy,ad,d;
		dx = Math.abs(comida_x-cobra[0][0]); //valor absoluto
		dy = Math.abs(comida_y-cobra[0][1]); 
		ad=(int)Math.sqrt((dx*dx)+(dy*dy));
		d = tamanho; //distancia minima para considerar colisao
		if (ad < d) {
			comida_x =randomizador.nextInt(450)+25;
			comida_y =randomizador.nextInt(450)+25;
			cobra[tamanho_atual][0] = 0;
			tamanho_atual++;
		}

		//Crescimento da cobra
		for (int i = tamanho_max-1;i>0;i--) {
			if (cobra[i][0] != -1) {
				cobra[i][0] = cobra[i-1][0];
				cobra[i][1] = cobra[i-1][1];
			}
		}
		cobra[0][0] = cobra[0][0]+=velocidade_x;
		cobra[0][1] = cobra[0][1]+=velocidade_y;

		//Loop da borda
		if (cobra[0][0] > getLargura()) {
			cobra[0][0] = 0;
		}else if (cobra[0][0] < 0) {
			cobra[0][0] = getLargura();
		}else if (cobra[0][1] > getAltura()) {
			cobra[0][1] = 0;
		}else if (cobra[0][1] < 0) {
			cobra[0][1] = getAltura();
		}

		//Colisão da cauda
		for (int i = 5;i<tamanho_max;i++) {
			if(cobra[i][0] != -1){
				dx = Math.abs(cobra[i][0]-cobra[0][0]);
				dy = Math.abs(cobra[i][1]-cobra[0][1]);
				ad=(int)Math.sqrt((dx*dx)+(dy*dy));
				d = tamanho;
				if (ad < d) {
					for (int k = 5;k < tamanho_max; k++) {
						for (int j = 0;j < 2; j++ ) {							
							cobra[k][j] = -1;
						}	
					}
					tamanho_atual=4;
					break;
				}		
			}
		}
	}
		
}