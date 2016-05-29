/**
 * Appends table row to result table
 * @param index index of processed issue
 * @param issue issue to process
 */
function appendTableRow(index, issue) {
    $("<tr>").append(
        $("<td>").text(index),
        $("<td>").text(issue.id),
        $("<td>").text(issue.value)
    ).appendTo("#result-table");
}

/**
 * Processes issues list
 * @param issues list
 */
function processIssues(issues) {
    $.each(issues, appendTableRow);
}

/**
 * Loads issues using ajax
 */
function loadIssues() {
    $.getJSON("api/issueLog/getAllIssues").done(processIssues);
}

$(loadIssues());

