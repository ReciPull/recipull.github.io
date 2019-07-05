import React, { Component } from 'react';
//import ScaleText from "react-scale-text";

export class SearchBar extends Component {
    state = {
        title: ''
    }

    onSubmit = (e) => {
        e.preventDefault();
        if (this.state.title == '') {
            return; 
        }
        else {
            this.props.addIngredient(this.state.title);
            var s = new String("");

            var t = this.state.title;
            if(document.getElementById("ingredients") != null)
            {
                s = document.getElementById("ingredients").innerHTML + t+":";
                document.getElementById("ingredients").innerHTML = s;
                console.log(document.getElementById("ingredients").innerHTML);
            }
            this.setState({ title: ''});
        }
    }

    onChange = (e) => this.setState({ [e.target.name]: e.target.value });

    render() {
        return (
            <form onSubmit={this.onSubmit} style={{ display: 'flex' }}>
                <input
                    type="test"
                    name="title"
                    style={bar}
                    placeholder="Add Ingredient"
                    value={this.state.title}
                    onChange={this.onChange}
                    />
                    <input
                        type="submit"
                        value="Submit"
                        className="btn"
                        style={sub}
                    />
            </form>
        )
    }
}

const bar = {
    display: 'flex',
    width: '75%'

}
const sub = {
    display: 'flex',
    width: '25%',
    innerHeight: '100%',
}
export default SearchBar