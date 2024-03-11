/*
 * Sets up the add functions needed to add values within the list using the buttons
 */

async function grabMovieFromID(mID) {
    let response = await fetch("http://localhost:8080/getById", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({"mID": mID})
    })

    return response.json();
}

/**
 * Adds a movie to the list by using the movie controller
 * @param mID The ID to add
 */
async function addMovieToList(mID) {
    // we first get the movie that we need from the request (it's guaranteed to exist)
    let requestedMovie = await grabMovieFromID(mID);

    // prompt a message saying the movie was added
    let spanElem = document.getElementById("alertBody");
    spanElem.innerText = "Successfully added " + requestedMovie.Title + " to saved list.";
    document.getElementById("alertDiv").classList.remove("collapse");

    // then we can use this to add the movie to our list
    return fetch("http://localhost:8080/addMovie", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(requestedMovie)
    });
}

function closeAlert() {
    document.getElementById("alertDiv").classList.add("collapse");
}