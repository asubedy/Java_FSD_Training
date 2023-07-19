// App.js

import React from 'react';
import './App.css'; // Import the CSS file for styles
import PokemonList from './components/PokemonList';
import Header from "./components/Header";

const App = () => {
  return (
    <div className="app-container">
     <Header/>
      <PokemonList />
    </div>
  );
};

export default App;
