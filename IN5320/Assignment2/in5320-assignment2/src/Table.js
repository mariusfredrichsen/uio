import React from 'react';
import "./Table.css";


function Table(props) {
  console.log(props);

  if (!props.apiData.results) {
    // If the API request isn't completed return "loading...""
    return <p>Loading...</p>;
  } else {
    // Write your code here:
    return <>
    <button onClick={props.filterCountry("ASD")}>ASD</button>
    <table>
      <tr>
        <th>Country</th>
        <th>Continent</th>
        <th>Population</th>
        <th>Population Growth</th>
      </tr>

      {props.apiData.results.map((result) => (
        <tr>
          <td>{result.Country}</td>
          <td>{result.Continent}</td>
          <td>{result.Population}</td>
          <td>{result.PopulationGrowth}</td>
        </tr>
      ))}


    </table></>;
  }
}

export default Table;
