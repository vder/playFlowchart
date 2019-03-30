import React, { Component } from 'react';

import './App.css';
import Persons from './Components/Persons/Persons';
import UserInput from './Components/UserInput/UserInput';

class App extends Component {
  state = {
    persons: [{name:"Piotr",age:30},{name:"Jurek",age:30},{name:"Magda",age:30},{name:"Nata",age:30}],
    outputVisible: false
  }

  nameChangeHandler = (event) => { 
    const curState = [...this.state.persons]
    curState[0].name = event.target.value
    this.setState({persons:curState})

  }

  delHandler = (idx) =>{
    const curState = [...this.state.persons]
     curState.splice(idx,1)
     this.setState({persons:curState} )
  }

  clickHandler = (newName) => {

    const curState = [...this.state.persons]
    curState[0].name = newName
    this.setState({persons:curState})
  }
   outputVisibleHandler = () =>{
     const visibility = this.state.outputVisible
     this.setState({outputVisible: !visibility})
   }

  render() {

    let output = null

    if (this.state.outputVisible) {
      output = (
          <Persons persons={this.state.persons} delBox={this.delHandler}/>
      )
    }

    return (
      <div className="App">
        <button onClick={this.outputVisibleHandler}>New name</button>
        <UserInput valueChanger={this.nameChangeHandler}
          label="Przycisk"
          click={this.clickHandler.bind(this, "MuzaKola")}
          val={this.state.name} />
           {output}
      </div>
    );
  }
}

export default App;
