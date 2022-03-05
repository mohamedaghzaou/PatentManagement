package com.ismo.brevets.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.utils.HibernateUtils;
import com.ismo.brevets.models.Invention;

public class DaoInvention implements IDAO<Invention> {

	@Override
	public List<Invention> getAll() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Invention> invts = s.createQuery("from Invention i join fetch i.domaine", Invention.class).getResultList();
		t.commit();
		s.close();
		return invts;
	}

	public List<Object[]> inoventionParDomaine() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Object[]> invts = s
				.createQuery(" select d.nom, count(*) from Invention i inner  join i.domaine d group by d")
				.getResultList();
		t.commit();
		s.close();
		return invts;
	}

	@Override
	public Invention getOne(int id) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Invention d = s.get(Invention.class, id);
		t.commit();
		s.close();
		return d;
	}

	@Override
	public boolean save(Invention obj) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		s.save(obj);
		t.commit();
		s.close();
		return Optional.ofNullable(getOne(obj.getNum())).isPresent();

	}

	@Override
	public boolean update(Invention obj) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		s.update(obj);
		t.commit();
		s.close();
		return true;
	}

	@Override
	public boolean delete(Invention obj) {
		int id = obj.getNum();
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Invention inovatonForDelete = s.get(Invention.class, id);
		s.delete(inovatonForDelete);
		t.commit();
		s.close();
		return !Optional.ofNullable(getOne(id)).isPresent();
	}

}
