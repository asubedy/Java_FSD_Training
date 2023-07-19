// Header.js

import React from 'react';
import '../styles/Header.css'; // Import the CSS file for styles
import pokemonLogo from '../images/pokemon_logo.png'; // Import the image

const Header = () => {
  return (
    <div className="header">
      <img src={pokemonLogo} alt="Pokemon Logo" className="logo" />
    </div>
  );
};

export default Header;
