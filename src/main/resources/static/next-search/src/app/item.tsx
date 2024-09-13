import React from "react";

const Item = (props: any) => {
  const { data, message } = props;
  if (!data?.length) {
    return (
      <div className="alert alert-success" role="alert">
        Nothing to see here... please modify the search
      </div>
    );
  }

  return (
    <div className="ol">
      <ol className="list-group list-group-numbered">
        {data?.map((m, index) => (
          <li
            className="list-group-item d-flex justify-content-between align-items-start"
            key={index}
          >
            <div className="ms-2 me-auto">
              <div className="fw-bold">{index}</div>
              {Object.keys(m).map((key) => {
                return (
                  <li key={key}>
                    {key}:{m[key]}
                  </li>
                );
              })}
            </div>
          </li>
        ))}
      </ol>
    </div>
  );
};

export default Item;
