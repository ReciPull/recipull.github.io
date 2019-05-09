import React from 'react';
import './Card.css';
import { Z_FULL_FLUSH } from 'zlib';

class CardHeader extends React.Component {
    render() {
        const { image } = this.props;
        var style = { 
            backgroundImage: 'url(' + image + ')',
        };
        return (
        <header style={style} id={image} className="card-header">
            <h4 className="card-header--title">{this.props.category}</h4>
        </header>
        )
    }
}

class Button extends React.Component {
    render() {
        return (
        <button className="button button-primary">
            <i className="fa fa-chevron-right"></i>See the Recipe
        </button>
        )
    }
}
  
class CardBody extends React.Component {
    render() {
        return (
        <div className="fullBody">
            <div className="card-body">
                <p className="date">{this.props.date}</p>
                
                <h2>{this.props.title}</h2>
                
                <p className="body-content">{this.props.text}</p>
                
                
            </div>
            <div className="linkButton">
                <Button />
            </div>
        </div>
        )
    }
}
  
class Card extends React.Component {
    render() {
        return (
        <article className="card">
            <CardHeader category={this.props.category} image={this.props.image}/>
            <CardBody date={this.props.date} title={this.props.title} text={this.props.text}/>
        </article>
        )
    }
}

export default Card; 