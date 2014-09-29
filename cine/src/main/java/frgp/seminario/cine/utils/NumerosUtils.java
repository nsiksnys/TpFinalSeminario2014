package frgp.seminario.cine.utils;

//Funciones utiles relacionadas con numeros. (Mas que nada parseos)
public class NumerosUtils {
/**
 * Convierte un string en un int
 * @param el objeto a parsear
 * @return el objeto convertido a int
 * @throws NumberFormatException si no puede parsear el String o si el numero en cuestion es menor o igual a cero.
 **/
	public int parseStringToInt(String object){
		try {
			if (Integer.parseInt(object) <= 0)//en ningún caso el numero puede ser menor o igual a cero
				throw new NumberFormatException();
			return Integer.parseInt(object);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
/*
 * Convierte un String en un float
 * @param el objeto a parsear
 * @return el objeto convertido a int
 * @throws NumberFormatException si no puede parsear el String o si el numero en cuestion es menor o igual a cero.
 */
	public float parseStringToFloat(String object){
		if (Float.parseFloat(object) <= 0){//en ningún caso el numero puede ser menor o igual a cero
			throw new NumberFormatException();
		}
		return Float.parseFloat(object);
	}
}
