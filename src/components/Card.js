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
            
        </header>
        )
    }
}

class Button extends React.Component {
    render() {
        return (
        <button className="button button-primary">
            <i className="fa fa-chevron-right"></i><p className="recipeSearch" >See the Recipe</p>
        </button>
        )
    }
}

/*class Voting extends React.Component { 
    state = {
        numVotes: 0, 
    }

    upVote = (e) => {
        e.preventDefault(); 
        this.state.numVotes = this.state.numVotes + 1; 
        this.setState({numVotes: this.state.numVotes}); 
    }

    downVote = (e) => {
        e.preventDefault(); 
        this.state.numVotes = this.state.numVotes - 1; 
        this.setState({numVotes: this.state.numVotes}); 
    }

    render() {
        return(
            <div>
                <button onClick={this.upVote}>
                    Upvote
                </button>
                <h>{this.state.numVotes}</h>
                <button onClick={this.downVote}>
                    Downvote
                </button>
            </div>
        )
    }
}*/
  
class CardBody extends React.Component {
    state = {
        numVotes: this.props.numVotes, 
    }

    upVote = (e) => {
        e.preventDefault(); 
        this.state.numVotes = this.state.numVotes + 1; 
        this.setState({numVotes: this.state.numVotes}); 
        document.getElementById("vote").innerHTML = this.props.title + "`" + this.state.numVotes; 
        console.log(document.getElementById("vote").innerHTML); 
    }

    downVote = (e) => {
        e.preventDefault(); 
        this.state.numVotes = this.state.numVotes - 1; 
        this.setState({numVotes: this.state.numVotes}); 
        document.getElementById("vote").innerHTML = this.props.title + "`" + this.state.numVotes;
        console.log(document.getElementById("vote").innerHTML);  
    }

    render() {
        const { numVotes } = this.props; 
        return (
        <div className="fullBody">
            <div className="card-body">
                <p className="date">{this.props.numIngredients}</p>

                <h2>{this.props.title}</h2>
                
                <div className='descSect'><p className="body-content">{this.props.text}</p></div>
                <div className='voteSect'>
                    <button name = 'upVoteBtn' className = 'upVoteBtn' onClick={this.upVote}> ↑ </button>
                    <div className='vote'>{this.state.numVotes}</div>
                    <button name='downVoteBtn' className = 'downVoteBtn' onClick={this.downVote}> ↓ </button>
                </div>
                
                
            </div>
            <div className='tagSect'>Tags: {this.props.category}</div>
            <div className="linkButton">
                <a href={this.props.link}>
                <Button />
                </a>
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
            <CardBody link={this.props.link} numVotes={this.props.numVotes} numIngredients={this.props.numIngredients} date={this.props.date} title={this.props.title} text={this.props.text} category={this.props.category}/>
        </article>
        )
    }
}

export default Card; 