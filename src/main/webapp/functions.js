const MOCK_ENABLED = true;

const IDS = {
    MAIN_ARTICLE: "#main-article",
    NAV_ALL: "#nav-all",
    NAV_NEW: "#nav-new",
    NAV_LOGOUT: "#nav-logout",
    RESULT_TABLE: "#result-table",
    ALL_ISSUES_DIV: "#all-issues",
    NEW_ISSUE_DIV: "#new-issue",
    ISSUE_DETAIL_DIV: '#issue-detail',
    SUBMIT_NEW_ISSUE_BTN: "#submit-new-issue"
};

const CLASSES = {
    NAV_BUTTON_MOUSE_OVER: "nav-button-shown",
    NAV_BUTTON_SELECTED: "nav-button-selected",
    TABLE_ROW_ENTER: "table-row-enter",
    HIDDEN: "hidden"
};

const SESSION = {
    issues: [],
    priorities: [
        {code: 1, value: 'BLOCK'},
        {code: 2, value: 'HIGH'},
        {code: 3, value: 'MEDIUM'},
        {code: 4, value: 'LOW'}
    ],
    getPriorityByValue: function (value) {
        var priority = this.priorities.find(function (priority) {
            return priority.value === value;
        });
        return priority == null ? this.priorities[2] : priority;
    },
    getPriorityByCode: function (code) {
        var priority = this.priorities.find(function (priority) {
            return priority.code === code;
        });
        return priority == null ? this.priorities[2] : priority;
    }
};

/**
 * Appends table row to result table
 * @param index index of processed issue
 * @param issue issue to process
 */
function appendTableRow(issue, index, array) {
    var rowWithId = "<tr id='"+issue.id+"'>";
    return $(rowWithId).append(
        $("<td>").text(index),
        $("<td>").text(issue.id),
        $("<td>").text(issue.subject),
        $("<td>").text(issue.description),
        $("<td>").text(issue.priority)
    );
}

/**
 * Processes issues list
 * @param issues list
 */
function processIssues(issues) {
    var table = "<table id='" + IDS.RESULT_TABLE.replace('#', '') + "'>";
    $(table)
        .append($("<tbody>")
            .append(issues.map(appendTableRow)))
        .appendTo(IDS.ALL_ISSUES_DIV);
}

function generateIssues() {
    var issues = [];
    for (var i = 1; i <= 100; i++) {
        issues.push({id: "id_" + i, value: "value_" + i});
    }
    return issues;
}
/**
 * Loads issues using ajax
 */
function loadIssues() {
    if (!MOCK_ENABLED) {
        $.ajax({
            async: false,
            dataType: "json",
            url: "api/issueLog/getAllIssues",
            success: function (issues) {
                SESSION.issues = issues;
            }
        });
    } else {
        SESSION.issues = generateIssues();
    }
}

function selectNav(id) {
    [IDS.NAV_ALL, IDS.NAV_NEW].forEach(function (item, index, array) {
        if (id == item) {
            $(item).toggleClass(CLASSES.NAV_BUTTON_SELECTED);
        } else {
            $(item).removeClass(CLASSES.NAV_BUTTON_SELECTED);
        }
    });
}

function hookTableMouseEventHandling() {
    var resultTableRowSelector = IDS.RESULT_TABLE + " tbody tr";
    $(IDS.MAIN_ARTICLE).on("mouseenter", resultTableRowSelector, function () {
        $(this).toggleClass(CLASSES.TABLE_ROW_ENTER);
    });

    $(IDS.MAIN_ARTICLE).on("mouseleave", resultTableRowSelector, function () {
        $(this).removeClass(CLASSES.TABLE_ROW_ENTER);
    });

    $(IDS.MAIN_ARTICLE).on("click", resultTableRowSelector, function () {
        hide(IDS.ALL_ISSUES_DIV);
        var id = this.id;
        var issue = SESSION.issues.find(function (issue) {
            return issue.id === id;
        });

        $("#id-issue-detail").val(issue.id);
        $("#subject-issue-detail").val(issue.subject);
        $("#description-issue-detail").val(issue.description);
        $("#priority-issue-detail").val(SESSION.getPriorityByCode(issue.priority).value),
        $("#message-body-issue-detail").val(issue.messageBody);
        $("#stack-trace-issue-detail").val(issue.stackTrace);

        show(IDS.ISSUE_DETAIL_DIV);
    });
}

function hide(id) {
    $(id).toggleClass(CLASSES.HIDDEN, true);
}

function show(id) {
    $(id).removeClass(CLASSES.HIDDEN);
}

function hookNavKeys() {
    $(IDS.NAV_NEW).click(function () {
        selectNav(IDS.NAV_NEW);
        hide(IDS.ALL_ISSUES_DIV);
        hide(IDS.ISSUE_DETAIL_DIV);
        show(IDS.NEW_ISSUE_DIV);
    });

    $(IDS.NAV_ALL).click(function () {
        selectNav(IDS.NAV_ALL);
        hide(IDS.NEW_ISSUE_DIV);
        hide(IDS.ISSUE_DETAIL_DIV);
        $(IDS.ALL_ISSUES_DIV).empty();
        show(IDS.ALL_ISSUES_DIV);
        $(IDS.ALL_ISSUES_DIV).append(processIssues(SESSION.issues));
    });

    //hook mouse enter|leave behaviour
    [IDS.NAV_NEW, IDS.NAV_ALL, IDS.NAV_LOGOUT].forEach(function (id, index, array) {
        $(id).mouseenter(function () {
            $(this).toggleClass(CLASSES.NAV_BUTTON_MOUSE_OVER);
        });
        $(id).mouseleave(function () {
            $(this).removeClass(CLASSES.NAV_BUTTON_MOUSE_OVER);
        });
    });

}

function hookNewIssue() {
    $(IDS.SUBMIT_NEW_ISSUE_BTN).click(handleNewIssue);
}

function handleNewIssue() {
    var subject = $("#subject-new-issue").val();
    var description = $("#description-new-issue").val();
    var priority = $("#priority-new-issue").val();
    var messageBody = $("#message-body-new-issue").val();
    var stackTrace = $("#stack-trace-new-issue").val();

    if (!MOCK_ENABLED) {
        $.ajax({
            async: false,
            dataType: "json",
            url: "api/issueLog/createNewIssue",
            method: "GET",
            data: {
                subject: subject, 
                description: description, 
                priority: SESSION.getPriorityByValue(priority).code,
                messageBody: messageBody,
                stackTrace: stackTrace
            },
            success: function (issue) {
                if (issue) {
                    SESSION.issues.push(issue);
                }
            }
        });
    } else {
        SESSION.issues.push({
            id: String(SESSION.issues.length + 1),
            subject: subject,
            description: description,
            priority: SESSION.getPriorityByValue(priority).code,
            messageBody: messageBody,
            stackTrace: stackTrace
        });
    }
}

function init() {
    loadIssues();
    processIssues(SESSION.issues);
    selectNav(IDS.NAV_ALL);
}

$(init);
$(hookTableMouseEventHandling);
$(hookNavKeys);
$(hookNewIssue);
