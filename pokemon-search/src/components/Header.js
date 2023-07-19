
import React from 'react';
import '../styles/Header.css'; 
import pokemonLogo from '../images/pokemon_logo.png'; 

const Header = () => {
  return (
    <div className="header">
      <img src={pokemonLogo} alt="Pokemon Logo" className="logo" />
    </div>
  );
};

export default Header;
