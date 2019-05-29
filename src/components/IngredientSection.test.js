import React from 'react';
import IngredientSection from './IngredientSection';
import { configure,mount,shallow,render } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';
configure({ adapter: new Adapter() });

describe('<IngredientSection />', () =>  {
    const component = mount(<IngredientSection />);

    const comInst = component.instance();

    var id1 = new String();
    var id2 = new String();
    var id3 = new String();
    var id4 = new String();
    var id5 = new String();
    var id6 = new String();

    var in1 = { id: "", title:"" };
    var in2 = { id: "", title:"" };
    var in3 = { id: "", title:"" };
    var in4 = { id: "", title:"" };
    var in5 = { id: "", title:"" };
    var in6 = { id: "", title:"" };


    it('adds Ingredients 1', () => {
        comInst.addIngredient("salt");
        comInst.addIngredient("pepper");
        comInst.addIngredient("sugar");
        expect(comInst.state.ingredientList[0].title).toEqual("salt");
    });
    it('adds Ingredients 2', () => {
        expect(comInst.state.ingredientList[1].title).toEqual("pepper");
    });
    it('adds Ingredients 3', () => {
        expect(comInst.state.ingredientList[2].title).toEqual("sugar");
    });

    it('removes Ingredients 1', () => {
        
        id1 = comInst.state.ingredientList[0].id
        id2 = comInst.state.ingredientList[1].id
        id3 = comInst.state.ingredientList[2].id

        in1 = {
            id: id1,
            title: "salt"
        }
        in2 = {
            id: id2,
            title: "pepper"
        }
        in3 = {
            id: id3,
            title: "sugar"
        }
        comInst.delIngredient(id2);
        expect(comInst.state.ingredientList).toEqual([in1, in3]);
    });
    it('removes Ingredients 2', () => {
        comInst.delIngredient(id3);
        expect(comInst.state.ingredientList).toEqual([in1]);
    });
    it('removes Ingredients 3', () => {
        comInst.delIngredient(id1);
        expect(comInst.state.ingredientList).toEqual([]);
    });

    it('All Added', () => {

        comInst.addIngredient("salt");
        comInst.addIngredient("pepper");
        comInst.addIngredient("sugar");

        id4 = comInst.state.ingredientList[0].id
        id5 = comInst.state.ingredientList[1].id
        id6 = comInst.state.ingredientList[2].id

        in4 = {
            id: id4,
            title: "salt"
        }
        in5 = {
            id: id5,
            title: "pepper"
        }
        in6 = {
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