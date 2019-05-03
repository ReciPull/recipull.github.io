import React, { Component } from 'react';

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
                    style={{flex: 10}}
                    placeholder="Add Ingredient"
                    value={this.state.title}
                    onChange={this.onChange}
                    />
                    <input
                        type="submit"
                        value="Submit"
                        className="btn"
                        style={{flex: 1}}
                    />
            </form>
        )
    }
}

export default SearchBar