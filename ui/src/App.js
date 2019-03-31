import React, { Component } from 'react';

import './App.css';
import Persons from './Components/Persons/Persons';
import UserInput from './Components/UserInput/UserInput';
import Card from './Components/CardsList/Card/Card'
import axios from 'axios';

class App extends Component {
  state = {
    persons: [{ name: "Piotr", age: 30 }, { name: "Jurek", age: 30 }, { name: "Magda", age: 30 }, { name: "Nata", age: 30 }],
    outputVisible: false,
    cards: []
  }

  componentDidUpdate() {
    window.scroll(0, 500 * this.state.cards.length)
  }
  componentDidMount() {
    axios.get("http://localhost:9000/flow/1").then(response => {
      this.setState({ cards: [response.data] })
    })
  }

  responseClickHandler = (currId,nextId) => {

    axios.get(`http://localhost:9000/flow/${nextId}`).then(response => {
      console.log(response)
      const results = [...this.state.cards].slice(0,currId)
      console.log("results:"+currId)
      console.log(results)
      if (response.data !== "") {
        results.push(response.data)
        this.setState({ cards: results })
        console.log(this.state.cards)
      }
    })

  }

  nameChangeHandler = (event) => {
    const curState = [...this.state.persons]
    curState[0].name = event.target.value
    this.setState({ persons: curState })

  }

  delHandler = (idx) => {
    const curState = [...this.state.persons]
    curState.splice(idx, 1)
    this.setState({ persons: curState })
  }

  clickHandler = (newName) => {

    const curState = [...this.state.persons]
    curState[0].name = newName
    this.setState({ persons: curState })
  }
  outputVisibleHandler = () => {
    const visibility = this.state.outputVisible
    this.setState({ outputVisible: !visibility })
  }

  render() {

    let output = null
    const posts = this.state.cards.map((card, idx) => (<Card key={card.id}
      question={card.question}
      answers={card.answers}
      click={this.responseClickHandler} />))

    if (this.state.outputVisible) {
      output = (
        <Persons persons={this.state.persons} delBox={this.delHandler} />
      )
    }

    return (
      <div className="App">
        {/* <button onClick={this.outputVisibleHandler}>New name</button>
        <UserInput valueChanger={this.nameChangeHandler}
          label="Przycisk"
          click={this.clickHandler.bind(this, "MuzaKola")}
          val={this.state.name} />
           {output} */}
        {posts}
      </div>
    );
  }
}

export default App;
