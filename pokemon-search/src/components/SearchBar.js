// SearchBar.js

import React, { useState } from 'react';
import './SearchBar.css'; // Import the CSS file for styles

const SearchBar = ({ onSearch }) => {
  const [searchTerm, setSearchTerm] = useState('');

  const handleInputChange = (event) => {
    const inputText = event.target.value;
    setSearchTerm(inputText);
    onSearch(inputText); // Trigger search as user types
  };

  const handleSearch = () => {
    onSearch(searchTerm);
  };

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      handleSearch();
    }
  };

  return (
    <div className="search-container">
      <input
        type="text"
        placeholder="Search Pokémon..."
        value={searchTerm}
        onChange={handleInputChange}
        onKeyDown={handleKeyDown} // Handle "Enter" key press
        className="search-input"
      />
      <button onClick={handleSearch} className="search-button">
        Search
      </button>
    </div>
  );
};

export default SearchBar;
