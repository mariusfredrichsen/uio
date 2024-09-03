
function add_country(country) {
    const countryList = document.getElementById("countries");
    const newListItem = document.createElement("li");
    const countryNode = document.createTextNode(country);

    newListItem.appendChild(countryNode)

    const countryButton = document.createElement("button");
    const countryButtonText = document.createTextNode("X");

    countryButton.appendChild(countryButtonText)
    countryButton.onclick = function () {
        countryList.removeChild(newListItem);
        countryButton.remove();
    }

    countryButton.onmouseenter = function () {
        countryButton.style.background = "red";
    }
    countryButton.onmouseleave = function () {
        countryButton.style.background = "lightgray";
    }

    newListItem.appendChild(countryButton)

    countryList.appendChild(newListItem)
}

function get_input() {
    const inputElement = document.getElementById('inputCountry')
    const inputValue = inputElement.value
    inputElement.value = ""

    return inputValue
}