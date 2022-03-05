package com.ismo.brevets.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.utils.HibernateUtils;
import com.ismo.brevets.models.Entreprise;

public class DaoEntreprise implements IDAO<Entreprise> {

	@Override
	public List<Entreprise> getAll() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Entreprise> ents = s.createQuery("from Entreprise", Entreprise.class).getResultList();
		t.commit();
		s.close();
		return ents;
	}

	public List<Object[]> InventionParEntreprise() {

		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Object[]> ents = s
				.createQuery("select e.nom, count(*) from Entreprise e inner join e.inventeurs i inner join i.brevets group by e")
				.getResultList();
		t.commit();
		s.close();
		return ents;
	}

	@Override
	public Entreprise getOne(int id) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Entreprise e = s.get(Entreprise.class, id);
		t.commit();
		s.close();
		return e;
	}

	@Override
	public boolean save(Entreprise obj) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		s.save(obj);
		t.commit();
		s.close();
		return Optional.ofNullable(getOne(obj.getNum())).isPresent();

	}

	@Override
	public boolean update(Entreprise obj) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		s.update(obj);
		t.commit();
		s.close();
		return true;
	}

	@Override
	public boolean delete(Entreprise obj) {
		int id = obj.getNum();
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		Entreprise entrepriseForDeletion = s.get(Entreprise.class, id);
		s.delete(entrepriseForDeletion);
		t.commit();
		s.close();
		return !Optional.ofNullable(getOne(id)).isPresent();
	}

}
