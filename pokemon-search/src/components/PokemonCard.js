
import React, { useEffect, useState } from 'react';
import { fetchPokemonDetails } from '../utils/api';
import '../styles/PokemonCard.css'; // Import the CSS file for styles

const PokemonCard = ({ url }) => {
  const [pokemon, setPokemon] = useState(null);

  useEffect(() => {
    const loadPokemonDetails = async () => {
      try {
        const data = await fetchPokemonDetails(url);
        setPokemon(data);
      } catch (error) {
        console.error('Error fetching pokemon details:', error);
      }
    };
    loadPokemonDetails();
  }, [url]);

  if (!pokemon) {
    return <div className="pokemon-card">Loading...</div>;
  }

  const { name, sprites, stats, types } = pokemon;
  const attack = stats.find((stat) => stat.stat.name === 'attack').base_stat;
  const hp = stats.find((stat) => stat.stat.name === 'hp').base_stat;
  const defense = stats.find((stat) => stat.stat.name === 'defense').base_stat;
  const type = types.map((type) => type.type.name).join(", ")

  return (
    <div className="pokemon-card">
      <h3 id='name'>{name.toUpperCase()}</h3>
      <img src={sprites.other.dream_world.front_default===null?sprites.front_default:sprites.other.dream_world.front_default} alt={name} />
      <p><strong>Attack</strong>: {attack}</p>
      <p><strong>HP</strong>: {hp}</p>
      <p><strong>Defense</strong>: {defense}</p>
      <p><strong>Type</strong>: {type}</p>

    </div>
  );
};

export default PokemonCard;
