package com.ismo.brevets.metier;

import java.util.List;
import java.util.Optional;

import com.ismo.brevets.dao.DAO;
import com.ismo.brevets.dao.DaoFactory;
import com.ismo.brevets.dao.IDAO;
import com.ismo.brevets.models.Entreprise;

public class MetierEntreprise implements IMetier<Entreprise> {

	private static IDAO<Entreprise> daoEntreprise;
	public  final static MetierEntreprise INSTANCE = new MetierEntreprise();

	private MetierEntreprise() {
		if (Optional.ofNullable(daoEntreprise).isEmpty()) {
			daoEntreprise = DaoFactory.getDAO(DAO.ENTREPRISE);
		}
	}

	@Override
	public List<Entreprise> getAll() {
		return daoEntreprise.getAll();
	}

	@Override
	public Entreprise getOne(int id) {
		return daoEntreprise.getOne(id);
	}

	@Override
	public boolean save(Entreprise obj) {
		return daoEntreprise.save(obj);
	}

	@Override
	public boolean update(Entreprise obj) {
		return daoEntreprise.update(obj);
	}

	@Override
	public boolean delete(Entreprise obj) {
		return daoEntreprise.delete(obj);
	}

}
