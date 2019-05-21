import React, { Component } from 'react';
import './CardSection.css'
import Card from './Card';
import Cards from './Cards';

class CardSection extends Component {
    state = {
        s: "",
        recipeList: [],
    }

    onClick = (e) => {
        e.preventDefault(); 
        var s = ""; 
        s = document.getElementById("recipes").innerHTML; 
        console.log(s); 
    }
    render() {
        function addCard(category, imageURL, date, title, text) {
            return(
                <Card category={category} image={imageURL} date={date} title={title} text={text}/>
            )
        }

            return(
                <div className="inLine">
                    <button onClick={this.onClick} className="outputButton">Output</button>
                    {/* <Card category="Lunch" image='https://hips.hearstapps.com/del.h-cdn.co/assets/17/39/2048x1024/landscape-1506456062-delish-spaghetti-meatballs.jpg?resize=1200:*' date="April 23, 2019"
                        title='Spaghetti & Meatballs' 
                        text='Basic recipe for Spaghetti & Meatballs. Perfect, quick recipe for a college student.'
                    />
                    <Card category="Breakfast" image='https://www.centercutcook.com/wp-content/uploads/2014/09/fruit-yogurt-granola-parfait-3.jpg' date="April 23, 2019"
                        title='Yogurt with Granola' 
                        text='Basic recipe for a balanced breakfast meal. Perfect, quick recipe for a college student to jumpstart their day.'
                    />
                    {addCard("Dinner","https://hips.hearstapps.com/del.h-cdn.co/assets/17/39/2048x1024/landscape-1506456062-delish-spaghetti-meatballs.jpg?resize=1200:*", "April 23, 2019", "Spaghetti","This is a Spaghetti recipe")}
                    {addCard("Lunch","https://hips.hearstapps.com/del.h-cdn.co/assets/17/39/2048x1024/landscape-1506456062-delish-spaghetti-meatballs.jpg?resize=1200:*", "April 23, 2019", "Spaghetti","This is a Spaghetti recipe")} */}
                    {/* {this.props.recipeList.map((ingredient) => (

                    ))} */}
                    <Cards recipeList={this.state.recipeList}></Cards>
                </div>
            )
        }
}

export default CardSection