import React, { useState, useEffect } from "react";
import "./App.css";
import Table from "./Table.js";

function App() {
  /* Create state:
        - apiData: List containing dictionaries of countries from API.
        - searchQuery: The query parameter that should be added to &search=
        - pageNumber: The page that is requested
  */

  const [apiData, setApiData] = useState([]);
  const [searchQuery, setSearchQuery] = useState(); // Default = No search query
  const [pageNumber, setPageNumber] = useState(1); //Default = Page 1
  const [pageSize, setPageSize] = useState("10");

  useEffect(() => {
    // All parameters are appended to this URL.
    let apiQuery = "https://dhis2-app-course.ifi.uio.no/api?";

    // If searchQuery isn't empty add &search=searchQuery to the API request.
    if (searchQuery) {
      apiQuery = apiQuery + "&search=" + searchQuery;
    }

    // Add what page we are requesting to the API request.
    apiQuery = apiQuery + "&page=" + pageNumber;

    apiQuery = apiQuery + "&pageSize=" + pageSize;

    // Query data from API.
    console.log("Querying: " + apiQuery);
    fetch(apiQuery)
      .then((results) => results.json())
      .then((data) => {
        // Then add response to state.
        setApiData(data);
        console.log(data);
      });
  }, [searchQuery, pageNumber, pageSize]); // Array containing which state changes that should re-reun useEffect()

  const [value, setValue] = useState("");

  const onChange = (event) => {
    setValue(event.target.value);
  };

  const onSearch = (event) => {
    setSearchQuery(value);
    setPageNumber(1);
  }

  const onChangePageSize = (event) => {
    setPageSize(event.target.value)
    setPageNumber(1);
  }

  return (
    <div className="App">
      <h1>Country lookup</h1>
      <input value={value} onChange={onChange} /><button onClick={onSearch} >Search</button>

      <Table apiData={apiData} />

      <Pager
        apiData={apiData}
        increasePage={() => setPageNumber(pageNumber + 1)}
        decreasePage={() => setPageNumber(pageNumber - 1)}
      />

      <div style={{
        display: 'flex', flexDirection: 'row', alignItems: 'center', justifyContent: 'center'
      }}><p>Size per page: </p><select onChange={onChangePageSize}>
          <option value="10">10</option>
          <option value="20">20</option>
          <option value="50">50</option>
        </select>
      </div>
    </div >
  );
}

function Pager(props) {
  console.log(props)
  if (!props.apiData.pager) {
    return null;
  } else {
    return (
      <div>
        <button disabled={(props.apiData.pager.page === 1)} onClick={props.decreasePage}>prev</button>
        <p>Page {props.apiData.pager.page} of {props.apiData.pager.pageCount}</p>
        <button disabled={(props.apiData.pager.page === props.apiData.pager.pageCount)} onClick={props.increasePage}>next</button>
      </div>
    )
  }
}

export default App;
