document.addEventListener("DOMContentLoaded", function () {
    // Fetch data from the Java REST API
    fetch("http://localhost:8080/freezer/search")
        .then(response => response.json())
        .then(data => {
            // Process the data and render it on the HTML page
            renderPersonList(data);
        })
        .catch(error => console.error("Error fetching data:", error));
});

function renderPersonList(persons) {
    // Get the <ul> element by its ID
    const personList = document.getElementById("personList");

    // Loop through each person and create a <li> element for them
    persons.forEach(person => {
        const listItem = document.createElement("li");
        listItem.textContent = `${person.item} ${person.comment}`;
        personList.appendChild(listItem);
    });
}
