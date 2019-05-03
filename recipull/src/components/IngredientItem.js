import React, { Component } from 'react';
import PropTypes from 'prop-types';

class IngredientItem extends Component {
    
    render() {
        const {id, title} = this.props.ingredient;
        return(
            <div style={itemStyle}>
                <p>
                <button onClick={this.props.delIngredient.bind(this,id)} style={btnStyle}>X</button>
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
    padding: "0px 5px",
    borderRadius: '50%',
    margin: '0px 5px'
}

const itemStyle = {
    backgroundColor: '#f4f4f4'
}
export default IngredientItem