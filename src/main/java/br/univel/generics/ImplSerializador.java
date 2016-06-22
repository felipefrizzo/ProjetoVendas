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

	public boolean gravar(T t, File file) throws SerializadorException {
		boolean retorno = false;
		Class<?>[] vet = t.getClass().getInterfaces(); // aqui ele armazena em um vetor, todas as classes q extendem de inteface
		
		boolean achou = false;
		
		for(Class<?> class1 : vet){
			if(class1.equals(Serializable.class)){
				achou = true;
				break;
			}
		}
		if(!achou){
			throw new SerializadorException("Classe nao Implementa serializable !"); 
		}
		
		try (FileOutputStream fos = new FileOutputStream(file); // quando utilizo dessa maneira ele
			ObjectOutputStream oos = new ObjectOutputStream(fos);){// da o close automaticamente
			
			oos.writeObject(t);
			retorno = true;
		} catch (Exception e) {
			throw new SerializadorException(e);
		}
		return retorno;
	}

	public T ler(File file) throws SerializadorException {
		try (FileInputStream fis = new FileInputStream(file);
			 ObjectInputStream ois = new ObjectInputStream(fis)){
				
			List<T> objects = null;
			
			while(ois.readObject() != null){
				objects.add((T) ois.readObject());
			}
				
			Class<?> clGenType = (Class<?>) ((ParameterizedType) getClass()
			.getGenericSuperclass())
	    	   .getActualTypeArguments()[0];
				
			if(!objects.getClass().equals(clGenType)){
				throw new SerializadorException("Os tipos sao diferentes !");
			}
				
			return (T) objects;	
		} catch (Exception e) {
			throw new SerializadorException(e);
		}
	}
}
