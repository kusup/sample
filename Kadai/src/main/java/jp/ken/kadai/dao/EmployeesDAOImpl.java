package jp.ken.kadai.dao;

import java.util.List;

import jp.ken.kadai.entity.Employees;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmployeesDAOImpl implements EmployeesDAO<Employees> {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employees> allList() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM Employees");
		List<Employees> empList = query.list();
		session.close();
		return empList;
	}

	@Override
	public Employees getById(int id) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("FROM Employees WHERE employee_id = ?");
		query.setParameter(0, id);
		Employees emp = (Employees)query.uniqueResult();
		session.close();
		return emp;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employees> getByName(String name) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("FROM Employees WHERE employee_name LIKE ?");
		query.setParameter(0, "%" + name + "%");
		List<Employees> list = query.list();
		session.close();
		return list;
	}

	@Override
	public boolean insertEmployeesData(Employees employees) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(employees);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public int updateEmployeesData(Employees employees) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(employees);
			tx.commit();
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}
	}

	@Override
	public int deleteEmployeesData(int id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("FROM Employees WHERE employee_id = ?");
			query.setParameter(0, id);
			Employees emp = (Employees)query.uniqueResult();
			session.delete(emp);
			tx.commit();
			return 1;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return 0;
		} finally {
			session.close();
		}
	}
}