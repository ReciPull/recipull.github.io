import React from 'react';
import IngredientSection from './IngredientSection';
import { configure,mount,shallow,render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
configure({ adapter: new Adapter() });

describe('<IngredientSection />', () =>  {
    const component = mount(<IngredientSection />);

    const comInst = component.instance();

    it('adds Ingredients', () => {
        comInst.addIngredient("salt");
        comInst.addIngredient("pepper");
        comInst.addIngredient("sugar");
        expect(comInst.state.ingredientList[0].title).toEqual("salt");
        expect(comInst.state.ingredientList[1].title).toEqual("pepper");
        expect(comInst.state.ingredientList[2].title).toEqual("sugar");
    });

    it('removes Ingredients', () => {
        
        const id1 = comInst.state.ingredientList[0].id
        const id2 = comInst.state.ingredientList[1].id
        const id3 = comInst.state.ingredientList[2].id

        const in1 = {
            id: id1,
            title: "salt"
        }
        const in2 = {
            id: id2,
            title: "pepper"
        }
        const in3 = {
            id: id3,
            title: "sugar"
        }
        comInst.delIngredient(id2);
        expect(comInst.state.ingredientList).toEqual([in1, in3]);
        comInst.delIngredient(id3);
        expect(comInst.state.ingredientList).toEqual([in1]);
        comInst.delIngredient(id1);
        expect(comInst.state.ingredientList).toEqual([]);
    });

    it('All Added', () => {
        console.log("oof");
        comInst.addIngredient("salt");
        comInst.addIngredient("pepper");
        comInst.addIngredient("sugar");

        const id4 = comInst.state.ingredientList[0].id
        const id5 = comInst.state.ingredientList[1].id
        const id6 = comInst.state.ingredientList[2].id

        const in4 = {
            id: id4,
            title: "salt"
        }
        const in5 = {
            id: id5,
            title: "pepper"
        }
        const in6 = {
            id: id6,
            title: "sugar"
        }
        expect(comInst.state.ingredientList).toEqual([in4, in5, in6]);
    });
    
    it('All Cleared', () => {
        comInst.clearAll();
        expect(comInst.state.ingredientList).toEqual([]);
    });



});