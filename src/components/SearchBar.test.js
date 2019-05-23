import React from 'react';
import SearchBar from './SearchBar';
import IngredientSection from './IngredientSection';
import { configure,mount,shallow,render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
configure({ adapter: new Adapter() });

describe('<SearchBar />', () =>  {
    const props = {addIngredient: (title) => {}};
    const component = mount(<SearchBar {...props}/>);

    const comInst = component.instance();

    var event = document.createEvent('Event');
    event.initEvent('click', true, false);

    /*it('Changes', () => {
        comInst.onChange(event);
    });*/

    it('Submits Search', () => {
        
        comInst.setState({title: "salt"});
        expect(comInst.state.title).toEqual("salt");
        comInst.onSubmit(event);
        expect(comInst.state.title).toEqual("");
    });
    



});