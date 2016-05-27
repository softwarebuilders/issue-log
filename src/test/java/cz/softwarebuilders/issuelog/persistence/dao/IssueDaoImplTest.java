package cz.softwarebuilders.issuelog.persistence.dao;

import cz.softwarebuilders.issuelog.persistence.entity.Issue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application_context.xml")
@ActiveProfiles("test")
@Transactional
public class IssueDaoImplTest implements ApplicationContextAware {
    @Resource
    private CrudDao<Issue> issueDao;

    @Test
    public void save() {
        Issue issue = new Issue();
        issue.setValue("xyz");
        issueDao.save(issue);
        issueDao.flush();
        assertNotNull(issue.getId());
    }

    @Test
    public void getAll() {
        issueDao.save(new Issue());
        issueDao.flushAndClear();
        List<Issue> all = issueDao.getAll();
        assertFalse("issues should not be empty", all.isEmpty());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext.getBeansWithAnnotation(Repository.class));
    }
}