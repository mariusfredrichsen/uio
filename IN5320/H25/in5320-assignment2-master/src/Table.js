function Table(props) {
  console.log(props.apiData);

  if (!props.apiData.results) {
    // If the API request isn't completed return "loading...""
    return <p>Loading...</p>;
  } else {
    // Write your code here:
    return <table>

    </table>;
  }
}

export default Table;
