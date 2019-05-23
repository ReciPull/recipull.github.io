import React, { Component } from 'react';
import Card from './Card';
import PropTypes from 'prop-types';
import './Cards.css'; 

class Cards extends Component {
    render() {
        if (this.props.hasRecipes > 0) {
            return this.props.recipeList.map((recipe) => (
                <Card key={recipe.id} category={recipe.tag} image={recipe.image} title={recipe.title} text={recipe.text} numIngredients={recipe.numIngredients} numVotes={recipe.numVotes}></Card>
            ));
        }
        else if (this.props.hasRecipes < 0) {
            return (
                <div>
                    <br></br>
                    <div className="noRecipeContainer">
                        <br></br>
                        <h className="noRecipes" >No Recipes Contain Any of Your Ingredients, Sorry! </h>
                    </div>
                </div>
            )
        }
        else {
            return(
                <div></div>
            )
        }
    }
}

Cards.propTypes = {
    recipeList: PropTypes.array.isRequired
}

export default Cards; 