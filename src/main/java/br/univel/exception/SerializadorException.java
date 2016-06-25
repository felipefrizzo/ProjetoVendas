package br.univel.exception;

public class SerializadorException extends Exception{
	private static final long serialVersionUID = 613321105934181094L;

	public SerializadorException(Exception e) {
		super(e);
	}
	
	public SerializadorException(String string){
		super(string);
	}
}
