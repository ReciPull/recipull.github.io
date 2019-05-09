import React, { Component } from 'react';
import IngredientItem from './IngredientItem'
import PropTypes from 'prop-types';

class Ingredients extends Component {
    render() {
        return this.props.ingredientList.map((ingredient) => (
            <IngredientItem key={ingredient.id} ingredient={ingredient} delIngredient={this.props.delIngredient} />
        ));
    }
}

Ingredients.propTypes = {
    ingredientList: PropTypes.array.isRequired
}

export default Ingredients