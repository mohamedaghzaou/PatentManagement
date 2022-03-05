package com.ismo.brevets.metier;

import java.util.List;
import java.util.Optional;

import com.ismo.brevets.dao.DAO;
import com.ismo.brevets.dao.DaoFactory;
import com.ismo.brevets.dao.IDAO;
import com.ismo.brevets.models.Domaine;

public class MetierDomaine implements IMetier<Domaine> {

	private   static  IDAO<Domaine> daoDomaine;
	public final static MetierDomaine INSTANCE = new MetierDomaine();
	
	
	
	private MetierDomaine() {
		if (Optional.ofNullable(daoDomaine).isEmpty()) {
			daoDomaine = DaoFactory.getDAO(DAO.DOMAINE);
		}
	}
	
	@Override
	public List<Domaine> getAll() {
		return daoDomaine.getAll();
	}

	@Override
	public Domaine getOne(int id) {
		return daoDomaine.getOne(id);
	}

	@Override
	public boolean save(Domaine obj) {
		return daoDomaine.save(obj);
	}

	@Override
	public boolean update(Domaine obj) {
		return  daoDomaine.update(obj);
	}

	@Override
	public boolean delete(Domaine obj) {
		return  daoDomaine.delete(obj);
	}
}
