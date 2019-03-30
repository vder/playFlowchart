import React from 'react'
import Person from './Person/Person';

const Persons = (props) =>   {
  console.log(props)
  return props.persons.map( (prsn,idx) =>  <Person name={prsn.name} age={prsn.age} key={idx} idx={idx} delBox={props.delBox}/> )
}
export default Persons