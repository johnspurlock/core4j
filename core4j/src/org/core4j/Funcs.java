package org.core4j;

import java.lang.reflect.Field;

import org.core4j.xml.XElement;

public class Funcs {

	
	
	public static <TResult> Func1<TResult,TResult> identity(Class<TResult> clazz){
		return new Func1<TResult,TResult>(){
			public TResult apply(TResult input) {
				return input;
			}};
	}
	public static <TResult> Func<TResult> constant(final TResult value){
		return new Func<TResult>(){
			public TResult apply() {
				return value;
			}};
	}
	

	public static <T,TResult> Func1<T,TResult> wrap(final ThrowingFunc1<T,TResult> fn){
		return new Func1<T,TResult>(){
			public TResult apply(T input) {
				try {
					return fn.apply(input);
				} catch(Exception e){
					throw new RuntimeException(e);
				}
			}};
	}
	public static <TResult> Func<TResult> wrap(final ThrowingFunc<TResult> fn){
		return new Func<TResult>(){
			public TResult apply() {
				try {
					return fn.apply();
				} catch(Exception e){
					throw new RuntimeException(e);
				}
			}};
	}
	public static <TObject,TField> Func1<TObject,TField> field(Class<TObject> objectClass, Class<TField> fieldClass, String fieldName){
		
		try {
			final Field field = objectClass.getField(fieldName);
			
			return wrap(new ThrowingFunc1<TObject,TField>(){
				@SuppressWarnings("unchecked")
				public TField apply(TObject input) throws Exception {
					return (TField)field.get(input);
				}});
			
		} catch(Exception e){
			throw new RuntimeException(e);
		}
		
		
	}
	
	public static Func1<XElement,String> elementValue(){
		return new Func1<XElement,String>(){
			public String apply(XElement input) {
				return input.getValue();
			}};
	}
	
	public static <TWhatever,TConstant> Func1<TWhatever,TConstant> constant(Class<TWhatever> whateverClass, final TConstant constant){
		return new  Func1<TWhatever,TConstant>(){
			public TConstant apply(TWhatever input) {
				return constant;
			}};
	}
}
