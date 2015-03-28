package dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Item;

public abstract class AbstractDaoImpl<T extends Item> {
	private SessionFactory sessionFactory;

	private final Class<T> clazz;

	protected AbstractDaoImpl(Class<T> clazz) {
		this.clazz = clazz;
		sessionFactory = new Configuration().configure()
				.buildSessionFactory();


	}


	public T get(Long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "from "+clazz.getName() +" where id=:id";
		
		
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);


		List<T> results =  query.list();

		session.close();

		return results.get(0);
	}
	
	public T getByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "from "+clazz.getName() +" where lower(name)=lower(:name)";
		
		
		Query query = session.createQuery(queryString);
		query.setParameter("name", name);


		List<T> results =  query.list();

		session.close();

		return results.get(0);
	}

	public Long persist(T t){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Serializable id = session.save(t);

		session.getTransaction().commit();
		session.close();
		return (Long) id;

	}

	public Long merge(Long id,T t) {
		T persistedObject = get(id);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		t.setId(persistedObject.getId());
		session.merge(t);
		
		session.getTransaction().commit();
		session.close();
		return  id;

	}

	public List<T> list(Map<String,Object> filters) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "from "+clazz.getName()+" where ";
		if(filters != null && filters.size()>0){
			for(String key : filters.keySet()){
				queryString+=key+" =:"+key+" ";
			}
		}
		Query query = session.createQuery(queryString);
		if(filters != null && filters.size()>0){
			for(String key : filters.keySet()){
				query.setParameter(key, filters.get(key));
			}
		}


		List<T> results =  query.list();

		session.close();

		return results;
	}





	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
