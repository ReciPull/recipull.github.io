import React, { Component } from 'react';
import './CardSection.css'
import uuid from 'uuid'
import { isAbsolute } from 'path';
import Card from './Card';
import Cards from './Cards';

class CardSection extends Component {
    state = {
        hasRecipes: 0, 
        recipeList: [
        ],
    }

    addRecipe = (e) => {
        if (document.getElementById("recipes").innerHTML === "") {
            return;
        }
        else if (document.getElementById("recipes").innerHTML === x) {
            return; 
        }
        else {
            e.preventDefault(); 
            this.state.recipeList = []; 
            this.setState({recipeList: this.state.recipeList}); 
            var s = ""; 
            s = document.getElementById("recipes").innerHTML; 
            console.log(s); 
            if (s === "No Recipes") {
                this.state.hasRecipes = -1; 
                this.setState({hasRecipes: this.state.hasRecipes});
            }
            else {
                this.state.hasRecipes = 1; 
                this.setState({hasRecipes: this.state.hasRecipes});
                console.log(this.state.hasRecipes); 
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
                        numVotes: parseInt(s2[7]), 
                    }
                    console.log(newRecipe); 
                    this.state.recipeList.push(newRecipe); 
                    this.setState({ recipeList: this.state.recipeList}); 
                    console.log(this.state.recipeList);
                    var x = document.getElementById("recipes").innerHTML; 
                    document.getElementById("recipes").innerHTML = "";  
                }
                console.log("done");  
            }
        }  
    }
    render() {
            return(
                <div className="inLine">
                    <div className="outputButtonContainer">
                        <button name="lunchFilter" className="leftFilter">Lunch</button>
                        <button name="breakfastFilter" className="leftFilter">Breakfast</button>
                        <button name="abcFilter" className="leftFilter">A-Z</button>
                        <button onClick={this.addRecipe} className="outputButton">Find My Recipes!</button>
                        <button name="vegFilter" className="rightFilter">Vegan</button>
                        <button name="voteFilter" className="rightFilter">Top-Rated</button>
                        <button name="dessertFilter" className="rightFilter">Dessert</button>
                    </div>
                    <br></br>
                    <Cards hasRecipes={this.state.hasRecipes} recipeList={this.state.recipeList}></Cards>
                </div>
            )
        }
}

export default CardSection