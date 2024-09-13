import { useState } from "react";

const Search = ({sendDataToParent}) => {
  const [inputValue, setInputValue] = useState('');

  // Function to send data to parent component
  const sendDataToP = (event) => {
    // Call the function passed from the parent component
    sendDataToParent(event.target.value);
  };

  return (
    <input
      type="text"
      className="form-control"
      title=""
      placeholder="Type something ...."
      width={100}
      onKeyUp={sendDataToP}
    />
  );
};
export default Search;
