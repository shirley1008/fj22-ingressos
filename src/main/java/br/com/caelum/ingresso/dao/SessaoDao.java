package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Sessao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class SessaoDao {

    @PersistenceContext
    private EntityManager manager;

    public Sessao findOne(Integer id) {

        return manager.find(Sessao.class, id);
    }

    public void save(Sessao sessao) {
        manager.merge(sessao);
    }

    public List<Sessao> findAll() {
        return manager.createQuery("select s from Sessao s", Sessao.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
