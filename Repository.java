import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.journaldev.hibernate.util.HibernateUtil;

publlic class Repository<T> {
	public T save(T saveMe){
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			///Начинаем транзакцию
			session.beginTransaction();
			///Сохраняем объект
			session.saveOrUpdate(saveMe);
			///Коммит транзакции
			session.getTransaction().commit();
		}catch (Exception e){
			//Откат транзакции в случае ошибки
			if (session.getTransaction().isActive()){
				session.getTransaction().rollback();
			}

		throw new RuntimeException("Error during save: " + e.getMessage(), e);
		}finally {
			//Закрываем сессию
			if (session.isOpen()){
				session.close();
		}
		return saveMe;
	}

	public T getById(Class<T> entitClass, Serializable id){
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		T entity = null;
		try {
			///Начинаем транзакцию
			session.beginTransaction();
			///Получаем объект по ID
			entity = session.get(entitClass, id);
			///Коммит транзакции
			session.getTransaction().commit();
		}catch (Exception e){
			//Откат транзакции в случае ошибки
			if (session.getTransaction().isActive()){
				session.getTransaction().rollback();
			}

			throw new RuntimeException("Error during getById: " + e.getMessage(), e);
		}finally {
			//Закрываем сессию
			if (session.isOpen()){
				session.close();
			}
		}
		return result;

	}
	public void delete(Class <T> entitClass, Serializable id){
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		try {
			///Начинаем транзакцию
			session.beginTransaction();
			///Удаляем объект
			session.delete(deleteMe);
			///Коммит транзакции
			session.getTransaction().commit();
		}catch (Exception e){
			//Откат транзакции в случае ошибки
			if (session.getTransaction().isActive()){
				session.getTransaction().rollback();
			}

			throw new RuntimeException("Error during delete: " + e.getMessage(), e);
		}finally {
			//Закрываем сессию
			if (session.isOpen()){
				session.close();
			}
		}

	}

	public List<T> getAll(Class<T> entitClass){
		SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
		Session session = sessionFactory.getCurrentSession();
		List<T> entities = null;
		try {
			///Начинаем транзакцию
			session.beginTransaction();
			///Получаем все объекты
			Query query = session.createQuery("from " + entitClass.getSimpleName());
			entities = query.list();
			///Коммит транзакции
			session.getTransaction().commit();
		}catch (Exception e){
			//Откат транзакции в случае ошибки
			if (session.getTransaction().isActive()){
				session.getTransaction().rollback();
			}

			throw new RuntimeException("Error during getAll: " + e.getMessage(), e);
		}finally {
			//Закрываем сессию
			if (session.isOpen()){
				session.close();
			}
		}
		return entities;

	}