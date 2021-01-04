package biblioteca;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Classe de interação do teclado com o usuario
public class Input implements KeyListener{ 

	private String[] teclas = new String[200]; //numero maxima da numeracao de identificacao da tecla
	private boolean[] teclas_pressionadas = new boolean[200];
	private boolean[] teclas_soltas = new boolean[200];
	

	public Input(){}

	//armazena num array de strings nome de chaves de forma inteligente
	public void mapearTecla(String tecla_nome, int tecla_codigo){
		teclas[tecla_codigo] = tecla_nome;
	}

	//metodos que perguntam se a tecla foi pressionada
	public boolean isKeyPressed(String tecla){
		for (int i = 0; i < 200; i++) {
			if(tecla == teclas[i] && teclas_pressionadas[i]){
				return true;
			}
		}
		return false;
	}

//implementa interfaces de leitura de teclado
	
	//evento ignorado mas esperado pelo uso da classe keyEvent
	
	@Override
	public void keyTyped(KeyEvent e){} // keyTyped metodo da classe KeyEvent
	@Override
	public void keyPressed(KeyEvent e){
		teclas_pressionadas[e.getKeyCode()] = true;
	}
	@Override
	public void keyReleased(KeyEvent e){
		teclas_pressionadas[e.getKeyCode()] = false;
		teclas_soltas[e.getKeyCode()] = true;
	}

}