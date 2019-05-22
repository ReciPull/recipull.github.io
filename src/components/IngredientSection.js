import React, { Component } from 'react';
import SearchBar from './SearchBar';
import Ingredients from './Ingredients';
import uuid from 'uuid'
import { isAbsolute } from 'path';
import './IngredientSection.css';

class IngredientSection extends Component {
    state = {
        ingredientList: [],
      }

    addIngredient = (title) => {
        const newIngredient = {
            id: uuid.v4(),
            title
        }
        this.setState({ ingredientList: [...this.state.ingredientList, newIngredient]});
        console.log(this.state.ingredientList); 
    }

    delIngredient = (id) => {
        var name = this.state.ingredientList.find(function(element){
            return element.id == id
        });
        var s = new String("");
        var t = new String("");
        s = document.getElementById("ingredients").innerHTML;
        s = s.replace(name.title + ':', "");
        document.getElementById("ingredients").innerHTML = s;
        console.log(document.getElementById("ingredients").innerHTML);

        this.setState({ ingredientList: [...this.state.ingredientList.filter(ingredient => ingredient.id !== id)]})
    }

    clearAll = () => {
        document.getElementById("ingredients").innerHTML = "";
        this.setState({ingredientList: []})
    }

    // onClick = (e) => {
    //     var s = new String("");
    //     s = document.getElementById("recipes").innerHTML; 
    //     console.log(s);
    // }

    render() {
        return (
            <div className='searchSection'>
            
                <div className = 'boxTop'>
                <h1>Ingredient List</h1>
                    <SearchBar addIngredient={this.addIngredient}/>
                </div>
                <div className= 'ingredList'>
                    <Ingredients ingredientList = {this.state.ingredientList} delIngredient={this.delIngredient}/>
                </div>
                <div className = 'boxBot'>
                    <button name = 'SearchBtn' className = 'SearchBtn'> Search Recipes </button>
                    
                </div>
                <div className = 'clearSection'>
                        <p style={tail}>Add Ingredients Here <button className = 'clearBtn' onClick={this.clearAll}> Clear All </button></p>
                </div>
                
            </div>
        )
    }
}

const tail = {
        backgroundColor: '#f4f4f4',
        height: 25,
        display: 'flex', justifyContent: 'center', alignItems: 'center'
}

export default IngredientSection
