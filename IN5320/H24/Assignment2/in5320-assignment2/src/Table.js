import React from 'react';
import "./Table.css";


function Table({ apiData, filterContinent, orderBy }) {

  if (!apiData.results) {
    // If the API request isn't completed return "loading...""
    return <p>Loading...</p>;
  } else {
    // Write your code here:
    return <>
      <div>
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
          {
            ["Asia", "Africa", "Europe", "North America", "South America", "Oceania"].map((continent) => (
              <div key={continent} style={{ margin: '5px', display: 'flex', alignItems: 'center' }}>
                <input type="checkbox" onClick={() => filterContinent(continent)} />
                <label htmlFor={continent} >{continent}</label>
              </div>
            ))
          }
        </div>
        <table>
          <thead>
            <tr>
              <th onClick={() => orderBy("Country")} style={{ cursor: 'pointer' }}>Country</th>
              <th onClick={() => orderBy("Continent")} style={{ cursor: 'pointer' }}>Continent</th>
              <th onClick={() => orderBy("Population")} style={{ cursor: 'pointer' }}>Population</th>
              <th onClick={() => orderBy("PopulationGrowth")} style={{ cursor: 'pointer' }}>Population Growth</th>
            </tr>
          </thead>

          <tbody>
            {apiData.results.map((result) => (
              <tr key={result.Country}>
                <td>{result.Country}</td>
                <td>{result.Continent}</td>
                <td>{result.Population}</td>
                <td>{result.PopulationGrowth}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>;

  }
}

export default Table;
