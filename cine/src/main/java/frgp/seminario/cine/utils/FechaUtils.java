package frgp.seminario.cine.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;

@Service("FechaUtils")
public class FechaUtils {
 /**
 * Calcula una fecha nueva cuyo mes tenga una diferencia de una unidad con respecto al mes de la fecha inicial.
 * Ej: Dada una fecha 2013.01.01, la función devuelve 2013.02.01
 * @param fecha la fecha en la que se basa para hacer el cálculo
 * @return un Date con la fecha
 **/
	public Date getFechaUnMesMas(Date fecha) {
		if (fecha == null)
			return null;
		
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
		if (fecha == null)
			return null;
		
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
		if (fecha == null)
			return null;
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1); 
		return calendar.getTime();
	}
	
/**
 * Devuelve una fecha con el formato dia-mes-anio	
 * @param fecha Date a formatear
 * @return String con la fecha con el formato indicado
 */
	public String getFormatoDiaMesAnio(Date fecha){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		if (fecha == null)
			return "";
		return format.format(fecha);
	}
	
/**
 * Devuelve una fecha con el formato hora:minutos
 * @param fecha Date a formatear
 * @return String con la fecha con el formato indicado
 */
	public String getFormatoHoraMinuto(Date fecha){
		if (fecha == null)
			return "";
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(fecha);
	}
	
/**
 * Devuelve una fecha con el formato hora:minutos
 * @param fecha Date a formatear
 * @return String con la fecha con el formato indicado
 */
	public Time getFormatoHoraMinuto(String hora){
		int horas, minutos;
		
		if (hora.equals(""))
			return new Time(0);
		
		horas = Integer.parseInt(hora.substring(0, 2));
		minutos=Integer.parseInt(hora.substring(3, 5));
		
		return new Time(new GregorianCalendar(1970,Calendar.JANUARY,1,horas,minutos,0).getTimeInMillis());
	}
	
/**
 * Devuelve una fecha con el fomato dia-mes-anio
 * @param fecha String con la fecha indicada en formato dd/MM/yyyy
 * @return Date con la fecha con el formato indicado
 */
	public Date getFechaFormatoDiaMesAnio(String fecha){
		int dia, mes, anio;
		
		if (fecha == null || fecha.equals(""))
			return new Date(0);
		
		dia=Integer.parseInt(fecha.substring(0, 2));
		mes=Integer.parseInt(fecha.substring(3, 5))-1;
		anio=Integer.parseInt(fecha.substring(6, 10));
		
		return new GregorianCalendar(anio,mes,dia).getTime();
	}
	
	/**
	 * Devuelve la diferencia entre dos objetos Time
	 * @param timeMayor objeto time mayor
	 * @param timeMenor objeto time menor
	 * @return un objeto time con la diferencia entre timeUno y timeDos
	 */
	public Time getDiferenciaTime(Time timeMayor, Time timeMenor)
	{
		long c;
		if (timeMayor.compareTo(timeMenor) > 0)
			c = timeMayor.getTime() - timeMenor.getTime();
		
		else //si se ingresaron los Time en el orden incorrecto
			c = timeMenor.getTime() - timeMayor.getTime();
		
		return new Time(c);
	}
}
