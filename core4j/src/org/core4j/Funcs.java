package org.core4j;

import java.lang.reflect.Field;

public class Funcs {

	
	public static <T,TField> Predicate1<T> byField(Class<T> clazz, String fieldName, Class<TField> fieldClass, final TField fieldValue){
		final Field field = CoreUtils.getField(clazz, fieldName);
		field.setAccessible(true);
		return wrap(new ThrowingPredicate1<T>(){	
			@SuppressWarnings("unchecked")
			public boolean apply(T input) throws Exception {
				TField value = (TField) field.get(input);
				if (value==null)
					return fieldValue==null;
				else
					return value.equals(fieldValue);
			}});
	}
	
	public static <T> Predicate1<T> not(final Predicate1<T> predicate){
		return new Predicate1<T>(){	
			public boolean apply(T input) {
				return !predicate.apply(input);
			}};
	}
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
	
	public static <T> Predicate1<T> wrap(final ThrowingPredicate1<T> fn){
		return new Predicate1<T>(){
			public boolean apply(T input) {
				try {
					return fn.apply(input);
				} catch(Exception e){
					throw new RuntimeException(e);
				}
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
}
