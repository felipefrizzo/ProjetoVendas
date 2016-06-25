package br.univel.generics;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.univel.exception.SerializadorException;
import br.univel.util.Serializador;

public class ImplSerializador<T> implements Serializador<T>{

	@Override
	public boolean gravar(T t, File file) throws SerializadorException {
		Class<?>[] vet = t.getClass().getInterfaces();
		
		boolean achou = false;
		boolean resultado = false;
		
		for(Class<?> c: vet){
			if(c.equals(Serializable.class)){
				achou = true;
				break;
			}			
		}
		
		if(!achou){
			throw new SerializadorException("Classe não implementa Serializable.");
		}
		
		try(FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
			
			oos.writeObject(t);
			resultado = true;
		}catch(Exception e){
			throw new SerializadorException(e);
		}
		return resultado;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T ler(File file) throws SerializadorException {
	
		try(FileInputStream fis   = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis)) {
				
			Object object = ois.readObject();
			// nao sei porque nao esta funcionando . entao comentei
			//Class<?> clGenType = (Class<?>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			
			//if(!object.getClass().equals(clGenType)){
				//throw new SerializadorException("Os tipos são diferentes!");
			//}
			
			return (T) object;			
		} catch (Exception e) {
			throw new SerializadorException(e);
		} 	
	}
}
