package dao;

import java.util.List;
import java.util.Map;

import entities.Item;


public abstract interface AbstractDao<T  extends Item> {
	
	T createPersistentObject();
	
	T get(Long id);
	
	Long persist(T t);
	
	Long merge
	(Long id,T t);
	
	void delete(Long id);
	
	List<T> list(Map<String,Object> filters);
	
	T getByName(String string);
	
}
