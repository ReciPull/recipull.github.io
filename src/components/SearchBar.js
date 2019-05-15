import React, { Component } from 'react';
import ScaleText from "react-scale-text";

export class SearchBar extends Component {
    state = {
        title: ''
    }

    onSubmit = (e) => {
        e.preventDefault();
        this.props.addIngredient(this.state.title);
        this.setState({ title: ''});
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
    width: '25%'
}
export default SearchBar