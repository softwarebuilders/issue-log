package cz.softwarebuilders.issuelog.persistence.dao;

import cz.softwarebuilders.issuelog.persistence.entity.Issue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("issueDao")
@Transactional
public class IssueDaoImpl implements CrudDao<Issue> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Issue entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public Issue load(Long id) {
        return (Issue) getCurrentSession().load(Issue.class, id);
    }

    @Override
    public List<Issue> getAll() {
        String hql = "select i from Issue i";
        return getCurrentSession().createQuery(hql).list();
    }

    @Override
    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public void flushAndClear() {
        flush();
        getCurrentSession().clear();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
