import 'bootstrap/dist/css/bootstrap.css';
import Item from './item';
export default function Result({result}) {    
    return(
       <div className='row'>
            <div className='row'>
                    <p className="text-start fs-5">
                        {result?.sqlQuery}
                    </p>
                    
            </div>
            <div className='row'>
                <p className="text-start fs-2">Top results</p>
                <Item data={result?.results} message="basic"/>
            </div>
       </div>
    );
}