import React from 'react';
import './CardsList.css'
import Card from './Card/Card'

const CardsList = (props) => (
     props.persons.map( (card,idx) =>  <Card question={card.question} answers={card.answers} key={card.id} idx={idx} /> )
)

export default CardsList