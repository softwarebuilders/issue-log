/**
 * Appends table row to result table
 * @param index index of processed person
 * @param person person to process
 */
function appendTableRow(index, person) {
    $("<tr>").append(
        $("<td>").text(index),
        $("<td>").text(person.name),
        $("<td>").text(person.surname),
        $("<td>").text(person.age),
        $("<td>").text(person.address.city),
        $("<td>").text(person.address.street),
        $("<td>").text(person.address.number)
    ).appendTo("#result-table");
}

/**
 * Processes persons list
 * @param persons list
 */
function processPersons(persons) {
    $.each(persons, appendTableRow);
}

/**
 * Loads persons using ajax
 */
function loadPersons() {
    $.getJSON("api/greetings/getPersons?count=500").done(processPersons);
}

$(loadPersons());