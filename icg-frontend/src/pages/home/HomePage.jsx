
import React from 'react'
import Select from 'react-select'


const years = [{value: '2017', label: '2017'}, {value: '2018', label: '2018'}, {
    value: '2019',
    label: '2019'
}, {value: '2020', label: '2020'}, {value: '2021', label: '2021'}, {value: '2022', label: '2022'},]

const HomePage = () => (

    <div className="container">
        <h3 className="col-md-8 text-center mt-5 mb-xxl-5">Generarea Codului IBAN</h3>
        <form>
            <div className="form-group ">

                <div className="row">
                    <label htmlFor="anul" className="col-sm-2 col-form-label">Anul:</label>
                    <div className="col-sm-4">
                        <Select className="sel" options={years}/>
                    </div>
                </div>

                <div className="row mt-4">
                    <label htmlFor="codulEco" className="col-sm-2 col-form-label">Codul Eco:</label>
                    <div className="col-sm-4">
                        <Select className="sel" options={years}/>
                    </div>
                </div>

                <div className="row mt-4">
                    <label htmlFor="raionul" className="col-sm-2 col-form-label">Raionul:</label>
                    <div className="col-sm-4">
                        <Select className="sel" options={years}/>
                    </div>
                </div>

                <div className="row mt-4">
                    <label htmlFor="localitatea" className="col-sm-2 col-form-label">Localitatea:</label>
                    <div className="col-sm-4">
                        <Select className="sel" options={years}/>
                    </div>
                </div>

                <div className="row mt-5 col-sm-4">
                    <button type="button" className="btn btn-primary float-end">Afiseaza Codul IBAN</button>
                </div>
            </div>

        </form>
    </div>)

export {HomePage};
