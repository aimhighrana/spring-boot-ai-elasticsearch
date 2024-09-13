'use client'
import { useState } from "react";

const Mode = () => {
  const [isCheck, setIsCheck] = useState(false);

  const sendData = (event) => {
    localStorage.setItem('mode', event.target.checked  ? 'advance' : 'basic');
  };

  // setIsCheck(localStorage.getItem('mode') === 'advance' ? true : false);



  return (

    <>
      <input className="form-check-input" type="checkbox" id="flexSwitchCheckChecked" placeholder="" onChange={sendData}/>
      <label className="form-check-label fs-10" htmlFor="flexSwitchCheckChecked">&nbsp; Switch to Advance mode</label>
    </>
  );
};
export default Mode;
