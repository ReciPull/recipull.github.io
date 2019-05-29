import React, { Component } from 'react';
import './CardSection.css'
import uuid from 'uuid'
import { isAbsolute } from 'path';
import Card from './Card';
import Cards from './Cards';

class CardSection extends Component {
    state = {
        hasRecipes: 0, 
        recipeList: [],
        ogList: [],
        inner: "", 
    }

    sortByLunch = (e) => {
        if (this.state.recipeList.length == 0) {
            return;
        }
        else {
            var recipes = this.state.ogList; 
            console.log(recipes); 
            var newRecipes = recipes.filter(recipe => recipe.tag.includes("lunch")); 
            console.log(newRecipes); 
            this.state.recipeList = newRecipes; 
            this.setState({ recipeList: this.state.recipeList });
        }
    }

    sortByBreakfast = (e) => {
        if (this.state.recipeList.length == 0) {
            return;
        }
        else {
            var recipes = this.state.ogList; 
            console.log(recipes); 
            var newRecipes = recipes.filter(recipe => recipe.tag.includes("breakfast")); 
            console.log(newRecipes); 
            this.state.recipeList = newRecipes; 
            this.setState({ recipeList: this.state.recipeList });
        }
    }

    sortAlphabetically = (e) => {
        if (this.state.recipeList.length == 0) {
            return;
        }
        else {
            var recipes = this.state.ogList; 
            recipes.sort(function(a,b) {
                if(a.title < b.title) { return -1; }
                if(a.title > b.title) { return 1; }
                return 0;
            })
            console.log(recipes); 
            this.state.recipeList = recipes; 
            this.setState({ recipeList: this.state.recipeList });
        }
    }

    sortByVegan = (e) => {
        if (this.state.recipeList.length == 0) {
            return;
        }
        else {
            var recipes = this.state.ogList; 
            console.log(recipes); 
            var newRecipes = recipes.filter(recipe => recipe.tag.includes("vegan")); 
            console.log(newRecipes); 
            this.state.recipeList = newRecipes; 
            this.setState({ recipeList: this.state.recipeList });
        }
    }

    sortByRating = (e) => {
        if (this.state.recipeList.length == 0) {
            return;
        }
        else {
            var recipes = this.state.ogList; 
            recipes.sort(function(a,b) {
                if(a.numVotes < b.numVotes) { return 1; }
                if(a.numVotes > b.numVotes) { return -1; }
                return 0;
            })
            console.log(recipes); 
            this.state.recipeList = recipes; 
            this.setState({ recipeList: this.state.recipeList });
        }
    }

    sortByDessert = (e) => {
        if (this.state.recipeList.length == 0) {
            return;
        }
        else {
            var recipes = this.state.ogList; 
            console.log(recipes); 
            var newRecipes = recipes.filter(recipe => recipe.tag.includes("dessert")); 
            console.log(newRecipes); 
            this.state.recipeList = newRecipes; 
            this.setState({ recipeList: this.state.recipeList });
        }
    }

    addRecipe = (e) => {
        if (this.state.recipeList.length != 0 && document.getElementById("recipes").innerHTML === this.state.inner) {
            this.state.recipeList = this.state.ogList; 
            this.setState({ recipeList: this.state.recipeList});
        }
        else if (document.getElementById("recipes").innerHTML === "") {
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
                        link: s2[3],
                    }
                    console.log(newRecipe); 
                    this.state.recipeList.push(newRecipe); 
                    this.setState({ recipeList: this.state.recipeList}); 
                    console.log(this.state.recipeList);
                    var x = document.getElementById("recipes").innerHTML; 
                    document.getElementById("recipes").innerHTML = "";  
                }
                this.state.ogList = this.state.recipeList; 
                this.setState({ ogList: this.state.ogList });
                this.state.inner = document.getElementById("recipes").innerHTML; 
                this.setState({ inner: this.state.inner }); 
                console.log(this.state.ogList); 
                console.log("done");  
            }
        }  
    }
    render() {
            return(
                <div className="inLine">
                    <div className="outputButtonContainer">
                        <button onClick = {this.sortByLunch} name="lunchFilter" className="leftFilter">Lunch</button>
                        <button onClick = {this.sortByBreakfast} name="breakfastFilter" className="leftFilter">Breakfast</button>
                        <button onClick={this.sortAlphabetically} name="abcFilter" className="leftFilter">A-Z</button>
                        <button onClick={this.addRecipe} className="outputButton">Find My Recipes!</button>
                        <button onClick = {this.sortByVegan} name="vegFilter" className="rightFilter">Vegan</button>
                        <button onClick = {this.sortByRating} name="voteFilter" className="rightFilter">Top-Rated</button>
                        <button onClick = {this.sortByDessert} name="dessertFilter" className="rightFilter">Dessert</button>
                    </div>
                    <br></br>
                    <Cards hasRecipes={this.state.hasRecipes} recipeList={this.state.recipeList}></Cards>
                </div>
            )
        }
}

export default CardSection