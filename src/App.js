import React, { Component } from 'react';
import './App.css';
import IngredientSection from './components/IngredientSection';
import logo from './components/imgs/recipull_transparent.png';

import CardSection from './components/CardSection';

class App extends React.Component {
    render() {
        return (
          <div className="App">
            <div className="TitleBar">
                <div className="WrittenTitle">Reci<div className="WrittenTitle2">Pull</div></div>
                
                <img src={logo}/>
                <div className="TagLine">The Modern Cookbook</div>
            </div>
            <div className="MainSectors">
                <div className="IngredientSector">
                    
                    <IngredientSection />
                </div>
                <div className="CardSector">
                    <CardSection />
                </div>
            </div>
          </div>
        );
      }
}

export default App; 
