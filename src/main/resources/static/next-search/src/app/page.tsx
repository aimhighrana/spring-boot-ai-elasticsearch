"use client";
import { useState } from "react";
import "bootstrap/dist/css/bootstrap.css";
import Result from "./result";
import Search from "./search";
import { debounce } from "lodash";
import { debug } from "console";

export default function Home() {
  const [receivedData, setReceivedData] = useState("");

  const [result, setResult] = useState([]);

  // Function to receive data from child component
  const receiveDataFromChild = (data) => {
    setReceivedData(data);
    console.log(data);
    // debouncedSearch(data);
  };

  const debouncedSearch = debounce(() => {
    // getData(receivedData);
  }, 1000);

  const getData = async () => {
    try {
      const res = await fetch(
        // `${window.origin}/search?_s=${search}&mode=${
        //   localStorage.getItem("mode") || "basic"
        // }`
        `http://localhost:8991/chat?message=${receivedData}`
      );
      const data = await res.json();
      prepareForRander(data);
      console.log(data);
      return data;
    } catch (err) {
      console.log(err);
    }
  };

  const prepareForRander = (data: any) => {
    // const array = [];

    // data?.forEach((f, index)=>{
    //   array.push(f);
    // })

    setResult(data);
  };

  const getAll = async () => {
    const res = await fetch(`${window.origin}/get-all`);
    const data = await res.json();
    prepareForRander(data);
    var anchor = document.createElement("a");
    anchor.href =
      "data:attachment/json;charset=utf-8," + encodeURI(JSON.stringify(data));
    anchor.target = "_blank";
    anchor.download = "all-data.json";

    anchor.click();
    setReceivedData("");
    

    anchor.remove();
  };

  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <div className="z-10 w-full max-w-5xl items-center justify-between font-mono">
        <div className="row">
          <div className="col-10">
            <Search sendDataToParent={receiveDataFromChild} />
          </div>
          <div className="col-2">
            <button className="form-control btn btn-success" onClick={getData}>
              Get
            </button>
          </div>
        </div>
        <div className="p-4"></div>
        <Result result={result} />
      </div>
    </main>
  );
}
