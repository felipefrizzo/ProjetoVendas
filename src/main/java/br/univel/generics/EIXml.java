package br.univel.generics;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;


public class EIXml<T> {

	public boolean gerarXml(T t, File file){
		boolean retorno = false;
		
		StringWriter out = new StringWriter();
		JAXBContext context = null;
		
		String xml;
		FileWriter fw;
		
		try {
			context = JAXBContext.newInstance(t.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,  // para formatar a o xml
					               Boolean.TRUE);
			marshaller.marshal(t, new StreamResult(out));
			
			fw = new FileWriter(file);
			xml = out.toString();
			fw.write(xml);
	        fw.close();
	        
	        retorno = true;
		} catch (PropertyException e) {
			e.printStackTrace();
		} catch (JAXBException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return retorno ;
	}
	
	public T importarXml(T t, File file){
		String xml = null;
		
		try {
			FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	        
	        StringBuilder sb =  new StringBuilder();
	        String line = null;
	        while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
	        
	        xml = sb.toString();
	        br.close();
	        fr.close();
	        
	        StringReader sr = new StringReader(xml);
	        JAXBContext context =  JAXBContext.newInstance(t.getClass());
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	        t = (T) unmarshaller.unmarshal(sr);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}
}
