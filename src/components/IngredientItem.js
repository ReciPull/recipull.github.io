import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './IngredientSection.css';

class IngredientItem extends Component {
    
    render() {
        const {id, title} = this.props.ingredient;
        return(
            <div className="ingredientSlot">
                <p>
                <button className="ingredientButton" onClick={this.props.delIngredient.bind(this,id)} >X</button>
                {this.props.ingredient.title}
                </p>
            </div>
        )
    }
}

IngredientItem.propTypes = {
    ingredient: PropTypes.object.isRequired
}

const btnStyle = {
    float: "left",
    backgroundColor: "transparent",
    border: "0px solid transparent",
    padding: "0px 5px",
    borderRadius: '50%',
    margin: '0px 5px'
}

const itemStyle = {
    backgroundColor: '#AAA',
    border:'1px solid #111',
    padding: "5px 0px 5px 0px"
}
export default IngredientItem