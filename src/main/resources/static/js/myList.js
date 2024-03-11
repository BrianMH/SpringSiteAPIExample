/**
 * Allows us to perform some removal operations
 */

async function deleteMovieById(mID) {
    let response = await fetch("http://localhost:8080/deleteMovie?movieId=" + mID, {
        method: "DELETE",
    })

    return response;
}

/**
 * Removes movies from the saved movie repo list
 * @param mID
 * @returns {Promise<void>}
 */
async function removeMovieFromList(mID) {
    // we first get the movie that we need from the request (it's guaranteed to exist)
    await deleteMovieById(mID);

    // Instead of reloading the page, we can hide the row itself instead
    let rowElem = document.getElementById("row-" + mID);
    rowElem.style.display = 'none';
}