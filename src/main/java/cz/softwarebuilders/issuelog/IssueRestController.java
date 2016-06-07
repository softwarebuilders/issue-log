package cz.softwarebuilders.issuelog;

import cz.softwarebuilders.issuelog.persistence.dao.CrudDao;
import cz.softwarebuilders.issuelog.persistence.entity.Issue;
import cz.softwarebuilders.issuelog.persistence.entity.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("issueLog")
@Transactional
public class IssueRestController {
    @Autowired
    @Qualifier("issueDao")
    private CrudDao<Issue> issueDao;

    @RequestMapping(value = "/getAllIssues", method = RequestMethod.GET, produces = "application/json")
    public List<Issue> getAllIssues() {
        return issueDao.getAll();
    }

    @RequestMapping(value = "/getPriorities", method = RequestMethod.GET, produces = "application/json")
    public List<Priority> getPriorities() {
        return Arrays.asList(Priority.values());
    }

    @RequestMapping(value = "/createNewIssue", method = RequestMethod.GET, produces = "application/json")
    public Issue createNewIssue(
            @RequestParam("subject") String subject,
            @RequestParam("description") String description,
            @RequestParam("priority") String priority) {
        Issue issue = new Issue();
        issue.setSubject(subject);
        issue.setDescription(description);
        issue.setPriority(Priority.fromCode(Integer.valueOf(priority)));
        issueDao.save(issue);
        return issue;
    }
}
