import React, {useEffect, useState} from "react";
import AuthService from "../services/AuthService";
import axios from "axios";
import Select from "react-select";

const Profile = () => {
    const years = [...Array(6).keys()]
        .map((year,index)=>({ label: year + 2017, value: year + 2022 }))

    const [eco, setEco] = useState(null);
    const [region, setRegion] = useState(null);
    const [vil, setVil] = useState(null);
    const[iban, setIban] = useState(null);

    const [ecoSelect, setEcoSelect] = useState(null);
    const [regionSelect, setRegionSelect] = useState(null);
    const [vilSelect, setVilSelect] = useState(null);


    const getEcoCodes = () => {
        axios.get("http://localhost:8080/api/eco")
            .then((response) => {
                setEco(response.data.map(item => ({
                        value: item.code,
                        label: item.label
                    }))
                        .map(item => ({
                            value: item.value,
                            label: `${item.value} - ${item.label}`
                        }))
                        .sort((a, b) => a.value.localeCompare(b.value))
                )
            }).catch((error) => {
            console.log(error)
        })
    }

    const getRegions = () => {
        axios.get("http://localhost:8080/api/locality/regions")
            .then((response) => {
                setRegion(response.data.map(item => ({
                        value: item.localityCode,
                        label: item.localityTitle
                    }))
                        .map(item => ({
                            value: item.value,
                            label: `${item.value} - ${item.label}`
                        }))
                        .sort((a, b) => a.value.localeCompare(b.value))
                )
            }).catch((err) => {
            console.log(err)
        })
    }


    useEffect(() => {
        return getEcoCodes(),getRegions()
    }, []);

    const handleVillage =(code)=>{
        if(code) {
            axios.get(`http://localhost:8080/api/locality/${code}`)
                .then((res) => {
                    console.log(res.data)
                    setVil(res.data.map((item) => ({
                            value: item.parentLocalityCode,
                            label: item.localityTitle
                        }))
                            .filter((vil) => !vil.label.startsWith("Raio"))
                            .map((item)=>({
                                value: item.value,
                                label: `${item.value} - ${item.label}`
                            }))
                    )
                }).catch((err) => {
                console.log(err)
            });
        }
    }



    const handleCall =()=>{
        if(ecoSelect && regionSelect && vilSelect) {
            axios.get(`http://localhost:8080/api/iban/${ecoSelect}/${regionSelect}/${vilSelect}`)
                .then((res) => {
                    console.log(res.data);
                    setIban(res.data);
                }).catch((err) => {
                console.log(err)
            });
        }
    }

    return <div className="container">
        <h3 className="col-md-15 text-center mt-5 mb-xxl-5">Generarea Codului IBAN</h3>
        <form>
            <div className="form-group ">

                <div className="row">
                    <label htmlFor="anul" className="col-sm-2 col-form-label">Anul:</label>
                    <div className="col-sm-5">
                        <Select className="sel" options={years}/>
                    </div>
                </div>

                <div className="row mt-4">
                    <label htmlFor="codulEco" className="col-sm-2 col-form-label">Codul Eco:</label>
                    <div className="col-sm-5">
                        <Select className="sel"
                                options={eco}
                                onChange={(e) => {

                                    setEcoSelect(e.value)
                                }
                                }/>
                    </div>
                </div>

                <div className="row mt-4">
                    <label htmlFor="raionul" className="col-sm-2 col-form-label">Raionul:</label>
                    <div className="col-sm-5">

                        <Select
                            className="sel"
                            options={region}
                            onChange={(e)=>{
                                console.log(e.value)
                                setRegionSelect(e.value);
                                handleVillage(e.value);
                            }}
                        />
                    </div>
                </div>

                { vil && <div className="row mt-4">
                    <label htmlFor="localitatea" className="col-sm-2 col-form-label">Localitatea:</label>
                    <div className="col-sm-5">
                        <Select className="sel" options={vil}
                                onChange={(e)=>{
                                    setVilSelect(e.value);
                                }}
                        />
                    </div>
                </div>}

                { !vil && <div className="row mt-4">
                    <label htmlFor="localitatea" className="col-sm-2 col-form-label">Localitatea:</label>
                    <div className="col-sm-5">
                        <Select className="sel" />
                    </div>
                </div>}

                <div className="row mt-5 col-12">
                    <button type="button" className="btn btn-primary d-block col-4 "
                            onClick={handleCall}
                    >Afiseaza Codul IBAN</button>
                </div>
            </div>
        </form>
        <div className="row  mt-5 ">
            <div className="col-12">
                <div className="alert alert-success col-3">
                    <h5>{iban && iban.iban}</h5>
                </div>
            </div>
        </div>
    </div>
};

export default Profile;
