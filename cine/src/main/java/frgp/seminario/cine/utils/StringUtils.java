package frgp.seminario.cine.utils;

import java.util.StringTokenizer;

public class StringUtils {
/**
 * Calcula la cantidad de palabras de un String dado
 * @param texto String con el que se trabajará
 * @return un int con la cantidad de palabras del String
 */
	public int contarPalabras(String texto){
		return new StringTokenizer(texto).countTokens();
	}
}
