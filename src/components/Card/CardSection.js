import React, { Component } from 'react';
import './CardSection.css'
import uuid from 'uuid'
import { isAbsolute } from 'path';
import Card from './Card';
import Cards from './Cards';

class CardSection extends Component {
    state = {
        recipeList: [
        ],
    }

    addRecipe = (e) => {
        if (document.getElementById("recipes").innerHTML === "") {
            return;
        }
        else {
            e.preventDefault(); 
            var s = ""; 
            s = document.getElementById("recipes").innerHTML; 
            console.log(s); 
            var s1 = s.split("|"); 
            console.log(s1); 
            for (var i=0; i<s1.length; i++) {
                var s2 = s1[i].split("`"); 
                const newRecipe = {
                    id: uuid.v4(),
                    tag: s2[2],
                    image: s2[4], 
                    title: s2[1], 
                    text: s2[5],
                    numIngredients: s2[6],
                }
                console.log(newRecipe); 
                this.state.recipeList.push(newRecipe); 
                this.setState({ recipeList: this.state.recipeList}); 
                console.log(this.state.recipeList); 
            }
            console.log("done");  
        }  
    }
    render() {
            return(
                <div className="inLine">
                    <button onClick={this.addRecipe} className="outputButton">Output</button>
                    <Cards recipeList={this.state.recipeList}></Cards>
                </div>
            )
        }
}

export default CardSection