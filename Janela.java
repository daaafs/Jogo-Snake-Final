package biblioteca;

public class Janela {
	protected Input input;
	protected int largura = 0;
	protected int altura = 0;
	protected int fps = 0;
	
	public Janela(int largura, int altura, int fps) {
	
		 this.largura = largura;
		 this.altura = altura;
		 this.fps = fps;
	}
	
	public void init(Input input){
		this.input = input;
	}

	public int getLargura(){
		return largura;
		}
	public int getAltura(){
		return altura;
		}
	
	public void setLargura(int largura){
		this.largura = largura;
		}
	
	public int getFps(){
		return fps;
		}
	
	public void setFps(int fps){
		this.fps = fps;
		}

	public void setAltura(int altura){
		this.altura = altura;
		}
	


	public void mapearTecla(String tecla, int codigoTecla){
		input.mapearTecla(tecla,codigoTecla);
	}

	public boolean isKeyPressed(String tecla){
		return input.isKeyPressed(tecla);
	}


}