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

    // `Brownies`dessert`https://www.browneyedbaker.com/chewy-brownies/`https://img.huffingtonpost.com/asset/
    // 5b9db9862200008300d9bdfe.jpeg?ops=scalefit_960_noupscale`Fudgy, rich, and decadent.|`Chocolate Chip 
    // Cookies`dessert`http://everydayannie.com/2008/09/08/thick-and-chewy-chocolate-chip-cookies/`https://img
    // .huffingtonpost.com/asset/5b9db98726000033007ffce0.jpeg?ops=scalefit_960_noupscale`There's nothing like 
    // a chewy, ooey-gooey chocolate chip cookie fresh out of the oven.
    addRecipe = (e) => {
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
                text: s2[5]
            }
            console.log(newRecipe); 
            this.state.recipeList.push(newRecipe); 
            this.setState({ recipeList: this.state.recipeList}); 
            console.log(this.state.recipeList); 
        }
        console.log("done");    
    }
    render() {
        function addCard(category, imageURL, title, text) {
            return(
                <Card category={category} image={imageURL} title={title} text={text}/>
            )
        }

            return(
                <div className="inLine">
                    <button onClick={this.addRecipe} className="outputButton">Output</button>
                    <Cards recipeList={this.state.recipeList}></Cards>
                </div>
            )
        }
}

export default CardSection