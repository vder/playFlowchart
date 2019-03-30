import React from 'react';
import './Card.css'

const Card = (props) => (
    <div className="box"><span className="header">{props.question}</span>
        <div className="responsebox">
            {props.answers.map(answer =>
                (<span key={answer.id} onClick={ ()=> props.click(answer.nextBlock)}><div className="response"><span className="margins">{answer.response} </span></div></span>)
            )}
        </div>
    </div>
)

export default Card;