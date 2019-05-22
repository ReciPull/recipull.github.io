import React, { Component } from 'react';
import Card from './Card';
import PropTypes from 'prop-types';

class Cards extends Component {
    render() {
        return this.props.recipeList.map((recipe) => (
            <Card key={recipe.id} category={recipe.tag} image={recipe.image} title={recipe.title} text={recipe.text} numIngredients={recipe.numIngredients}></Card>
        ));
    }
}

Cards.propTypes = {
    recipeList: PropTypes.array.isRequired
}

export default Cards; 