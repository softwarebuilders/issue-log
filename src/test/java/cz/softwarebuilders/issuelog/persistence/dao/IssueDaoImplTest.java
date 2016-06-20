package cz.softwarebuilders.issuelog.persistence.dao;

import cz.softwarebuilders.issuelog.persistence.entity.Issue;
import cz.softwarebuilders.issuelog.persistence.entity.Priority;
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
        Issue issue = createIssue();
        issueDao.save(issue);
        issueDao.flush();
        assertNotNull(issue.getId());
    }

    private Issue createIssue() {
        Issue issue = new Issue();
        issue.setSubject("xyz");
        issue.setDescription("something went wrong");
        issue.setPriority(Priority.BLOCK);
        String messageBody = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Fusce aliquam vestibulum ipsum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Et harum quidem rerum facilis est et expedita distinctio. Mauris dictum facilisis augue. Mauris dolor felis, sagittis at, luctus sed, aliquam non, tellus. Fusce tellus odio, dapibus id fermentum quis, suscipit id erat. Duis risus. Vestibulum fermentum tortor id mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Integer vulputate sem a nibh rutrum consequat. Integer malesuada. In enim a arcu imperdiet malesuada. Aliquam id dolor. Nullam dapibus fermentum ipsum. Etiam egestas wisi a erat. Maecenas ipsum velit, consectetuer eu lobortis ut, dictum at dui. Duis pulvinar.\n"
                + "\n"
                + "Etiam egestas wisi a erat. Aliquam erat volutpat. Integer vulputate sem a nibh rutrum consequat. Vestibulum fermentum tortor id mi. Nullam faucibus mi quis velit. Fusce wisi. Cras elementum. Aliquam in lorem sit amet leo accumsan lacinia. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Integer vulputate sem a nibh rutrum consequat. Fusce nibh.\n"
                + "\n"
                + "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Nullam faucibus mi quis velit. Aenean id metus id velit ullamcorper pulvinar. Praesent in mauris eu tortor porttitor accumsan. Nulla est. Proin mattis lacinia justo. Proin in tellus sit amet nibh dignissim sagittis. Curabitur sagittis hendrerit ante. Mauris suscipit, ligula sit amet pharetra semper, nibh ante cursus purus, vel sagittis velit mauris vel metus. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Fusce dui leo, imperdiet in, aliquam sit amet, feugiat eu, orci. Praesent dapibus. Proin mattis lacinia justo. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.\n"
                + "\n"
                + "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Aenean fermentum risus id tortor. Et harum quidem rerum facilis est et expedita distinctio. Pellentesque arcu. Curabitur vitae diam non enim vestibulum interdum. Nulla accumsan, elit sit amet varius semper, nulla mauris mollis quam, tempor suscipit diam nulla vel leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Mauris suscipit, ligula sit amet pharetra semper, nibh ante cursus purus, vel sagittis velit mauris vel metus. Phasellus faucibus molestie nisl. In enim a arcu imperdiet malesuada. Nullam sit amet magna in magna gravida vehicula. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Etiam posuere lacus quis dolor. Vestibulum erat nulla, ullamcorper nec, rutrum non, nonummy ac, erat. Etiam egestas wisi a erat. Pellentesque arcu. Nam quis nulla. Aliquam id dolor. Duis ante orci, molestie vitae vehicula venenatis, tincidunt ac pede.\n"
                + "\n"
                + "Integer rutrum, orci vestibulum ullamcorper ultricies, lacus quam ultricies odio, vitae placerat pede sem sit amet enim. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Quisque porta. Phasellus enim erat, vestibulum vel, aliquam a, posuere eu, velit. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis risus. Nam sed tellus id magna elementum tincidunt. Etiam posuere lacus quis dolor. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Fusce nibh. Praesent dapibus. Maecenas aliquet accumsan leo. Nulla non arcu lacinia neque faucibus fringilla. In dapibus augue non sapien.\n"
                + "\n"
                + "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Curabitur vitae diam non enim vestibulum interdum. Nullam eget nisl. Aliquam erat volutpat. Donec iaculis gravida nulla. Phasellus et lorem id felis nonummy placerat. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nullam lectus justo, vulputate eget mollis sed, tempor sed magna. Vivamus luctus egestas leo. Etiam egestas wisi a erat. Suspendisse nisl. In enim a arcu imperdiet malesuada. Nullam at arcu a est sollicitudin euismod. Maecenas libero. In convallis. Curabitur sagittis hendrerit ante. Nulla pulvinar eleifend sem.\n"
                + "\n"
                + "Nam quis nulla. Quisque porta. Integer pellentesque quam vel velit. Fusce dui leo, imperdiet in, aliquam sit amet, feugiat eu, orci. Aliquam id dolor. Etiam sapien elit, consequat eget, tristique non, venenatis quis, ante. Pellentesque arcu. Integer malesuada. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Duis sapien nunc, commodo et, interdum suscipit, sollicitudin et, dolor. In convallis. Duis ante orci, molestie vitae vehicula venenatis, tincidunt ac pede. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Duis sapien nunc, commodo et, interdum suscipit, sollicitudin et, dolor. Phasellus faucibus molestie nisl. Nulla quis diam.\n"
                + "\n"
                + "Vivamus porttitor turpis ac leo. Mauris elementum mauris vitae tortor. Mauris dictum facilisis augue. Nullam justo enim, consectetuer nec, ullamcorper ac, vestibulum in, elit. Curabitur ligula sapien, pulvinar a vestibulum quis, facilisis vel sapien. Vestibulum fermentum tortor id mi. Sed vel lectus. Donec odio tempus molestie, porttitor ut, iaculis quis, sem. Maecenas libero. Etiam ligula pede, sagittis quis, interdum ultricies, scelerisque eu. In laoreet, magna id viverra tincidunt, sem odio bibendum justo, vel imperdiet sapien wisi sed libero. Aliquam erat volutpat.\n"
                + "\n"
                + "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Suspendisse nisl. Nullam lectus justo, vulputate eget mollis sed, tempor sed magna. Aenean placerat. Mauris metus. Aenean placerat. Aliquam erat volutpat. Nunc dapibus tortor vel mi dapibus sollicitudin. Donec ipsum massa, ullamcorper in, auctor et, scelerisque sed, est. Proin in tellus sit amet nibh dignissim sagittis. Praesent dapibus. Duis pulvinar. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Duis ante orci, molestie vitae vehicula venenatis, tincidunt ac pede.\n"
                + "\n"
                + "Vestibulum fermentum tortor id mi. Integer imperdiet lectus quis justo. Etiam neque. Phasellus rhoncus. Maecenas lorem. Nullam rhoncus aliquam metus. Nullam sit amet magna in magna gravida vehicula. Integer in sapien. Cras elementum. Proin pede metus, vulputate nec, fermentum fringilla, vehicula vitae, justo. Curabitur ligula sapien, pulvinar a vestibulum quis, facilisis vel sapien. Quisque porta. Curabitur ligula sapien, pulvinar a vestibulum quis, facilisis vel sapien. Etiam dictum tincidunt diam.";
        issue.setMessageBody(messageBody);
        return issue;
    }

    @Test
    public void getAll() {
        issueDao.save(createIssue());
        issueDao.flushAndClear();
        List<Issue> all = issueDao.getAll();
        assertFalse("issues should not be empty", all.isEmpty());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext.getBeansWithAnnotation(Repository.class));
    }
}