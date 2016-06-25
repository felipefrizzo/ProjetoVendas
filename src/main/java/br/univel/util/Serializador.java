package br.univel.util;

import java.io.File;

import br.univel.exception.SerializadorException;

public interface Serializador<T> {
	
	public boolean gravar(T t, File file) throws SerializadorException;
	
	public T ler(File file) throws SerializadorException;	
}
