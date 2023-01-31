package com.savia.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Esta clase no ayuda obtener el nombre del metodo de una clase en especifico
*/
public class ClassUtil {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);
    
    public static String getCurrentMethodName() {
        return getCurrentMethodName(null);
    }
    
    public static <T> String getCurrentMethodName(Class<T> clase) {
        String nombreMetodo = "";
        try {
            if (clase != null) {
                nombreMetodo += clase.getSimpleName() + ".";
            }
            nombreMetodo += Thread.currentThread().getStackTrace()[2].getMethodName();
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.info("Se produjo un error al momento de obtener el nombre del método: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.info("Se produjo un error al momento de obtener el nombre del método: " + e.getMessage());
        }
        return nombreMetodo;
    }
}
