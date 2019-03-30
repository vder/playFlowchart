import React from 'react';
import './Person.css'

const Person = (props) => (
    <div className="Person">
        <p>Hi my name is {props.name} and I'm {props.age} yo</p>
        <p>Some paragraph #2</p>
        <div className="btn" onClick={() => props.delBox(props.idx)} />
    </div>
)

export default Person