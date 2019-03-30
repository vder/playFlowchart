import React from 'react';
const UserInput = (props) => (
    <div>       
            <label>
                Name:
             <input type="text" name="name" onChange={props.valueChanger} value={props.val}/>
            </label>
            <button onClick={props.click}>{props.label}</button>        
    </div>
)

export default UserInput
