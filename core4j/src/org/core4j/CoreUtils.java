package org.core4j;

import java.lang.reflect.Field;

public class CoreUtils {

	
	public static Field getField(Class<?> type, String name){
		
		while (!type.equals(Object.class)) {
			for(Field f : type.getDeclaredFields()){
				if (f.getName().equals(name)){
					return f;
				}
			}
			type = type.getSuperclass();
		}
		throw new RuntimeException("Field not found: " + name);
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Object obj, String name, Class<T> fieldType){
		try {
			Class<?> type = obj.getClass();
			Field field = getField(type,name);
			field.setAccessible(true);
			return (T)field.get(obj);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
