package frgp.seminario.cine.utils;

import java.util.Date;
import java.util.GregorianCalendar;

public class FechaUtils {
 /**
 * Calcula una fecha nueva cuyo mes tenga una diferencia de una unidad con respecto al mes de la fecha inicial.
 * Ej: Dada una fecha 2013.01.01, la función devuelve 2013.02.01
 * @param fecha la fecha en la que se basa para hacer el cálculo
 * @return un Date con la fecha
 **/
	public Date getFechaUnMesMas(Date fecha) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		calendar.add(GregorianCalendar.MONTH, 1);
		return calendar.getTime();
	}
	
/**
* Calcula una fecha nueva cuyo dia el máximo del mes anterior del de la fecha inicial.
* Ej: Dada una fecha 2013.01.01, la función devuelve 2012.12.31
* @param fecha la fecha en la que se basa para hacer el cálculo
* @return un Date con la fecha
**/
	public Date getFechaUnDiaMenos(Date fecha) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		calendar.add(GregorianCalendar.MONTH, -1);
		calendar.set(GregorianCalendar.DAY_OF_MONTH, calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	
/**
 * Calcula una fecha nueva cuyo dia sea uno mas que el provisto por la fecha dada.
 * Ej: Dada una fecha 2013.01.01, la función devuelve 2013.01.02
 * @param fecha Date con la fecha con la que se trabajara
 * @return un Date con la fecha calculada
 **/
	public Date getFechaUnDiaMas(Date fecha) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1); 
		return calendar.getTime();
	}
}
