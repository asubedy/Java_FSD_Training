// PokemonList.js

import React, { useEffect, useState } from 'react';
import { fetchAllPokemons } from './utils/api';
import PokemonCard from './PokemonCard';
import Pagination from './Pagination';
import SearchBar from './SearchBar';
import NoResultsFound from './NoResultsFound';
import './PokemonList.css';

const ITEMS_PER_PAGE = 10;

const PokemonList = () => {
  const [pokemons, setPokemons] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    const fetchPokemonData = async () => {
      try {
        const data = await fetchAllPokemons();
        setPokemons(data.results);
      } catch (error) {
        console.error('Error fetching Pokémon data:', error);
      }
    };

    fetchPokemonData();
  }, []);

  useEffect(() => {
    // When the search term changes, reset to the first page of results
    setCurrentPage(1);
  }, [searchTerm]);

  const handleSearch = (term) => {
    setSearchTerm(term);
  };

  const filteredPokemons = pokemons.filter((pokemon) =>
    pokemon.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const sortedPokemons = filteredPokemons.sort((a, b) =>
    a.name.localeCompare(b.name)
  );

  const totalFilteredPages = Math.ceil(sortedPokemons.length / ITEMS_PER_PAGE);

  const getCurrentPageResults = () => {
    const startIndex = (currentPage - 1) * ITEMS_PER_PAGE;
    const endIndex = startIndex + ITEMS_PER_PAGE;
    return filteredPokemons.slice(startIndex, endIndex);
  };

  return (
    <div className="pokemon-list-container">
      <SearchBar onSearch={handleSearch} />
      {filteredPokemons.length === 0 ? (
        <NoResultsFound />
      ) : (
        <>
          <div className="pokemon-list">
            {getCurrentPageResults().map((pokemon) => (
              <PokemonCard key={pokemon.name} url={pokemon.url} />
            ))}
          </div>
          <Pagination
            currentPage={currentPage}
            totalPages={totalFilteredPages}
            onPageChange={(page) => setCurrentPage(page)}
          />
        </>
      )}
    </div>
  );
};

export default PokemonList;
