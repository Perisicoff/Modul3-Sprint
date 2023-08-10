import { Button, Col, Form, Row } from "react-bootstrap";
import Axios from "../../apis/Axios";
import { useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const IzmenaZadatka = (props) => {

    var navigate = useNavigate()

    const zadatak = {
        id: props.izabraniEntitet.id,
        ime: props.izabraniEntitet.ime,
        zaduzeni: props.izabraniEntitet.zaduzeni,
        bodovi: props.izabraniEntitet.bodovi,
        sprintId: props.izabraniEntitet.sprintId,
        stanjeId: props.izabraniEntitet.stanjeId

    }

    const [sprintevi, setSprintevi] = useState([])
    const [stanja, setStanja] = useState([])
    const [izmenjenZadatak, setIzmenjenZadatak] = useState(zadatak)

    const getAll2 = useCallback(() => {
        Axios.get('/sprintevi')
        .then(res => {
          console.log(res)
          setSprintevi(res.data)
        })
        .catch(err => {
          console.log(err)
        })
    }, [])

    const getAll3 = useCallback(() => {
        Axios.get('/stanja')
        .then(res => {
          console.log(res)
          setStanja(res.data)
        })
        .catch(err => {
          console.log(err)
        })
    }, [])

    useEffect(() => {
        getAll2()
        getAll3()
      }, [])
    
    const edit = () => {

        const dto = {
            id: izmenjenZadatak.id,
            ime: izmenjenZadatak.ime,
            zaduzeni: izmenjenZadatak.zaduzeni,
            bodovi: izmenjenZadatak.bodovi,
            sprintId: izmenjenZadatak.sprintId,
            stanjeId: izmenjenZadatak.stanjeId
        }

        Axios.put('/zadaci/' + izmenjenZadatak.id, dto)
        .then(res => {
            console.log(res)
            navigate('/zadaci')
          })
          .catch(err => {
            console.log(err)
          })
    }

    const naPromenaImena = (e) => {
        const value = e.target.value;
        setIzmenjenZadatak({...izmenjenZadatak, ime: value})
    }

    const naPromenaZaduzenog = (e) => {
        const value = e.target.value;
        setIzmenjenZadatak({...izmenjenZadatak, zaduzeni: value})
    }

    const naPromenaBodova = (e) => {
        const value = e.target.value;
        setIzmenjenZadatak({...izmenjenZadatak, bodovi: value})
    }

    const naPromenaSprintIda = (e) => {
        const value = e.target.value;
        setIzmenjenZadatak({...izmenjenZadatak, sprintId: value})
    }

    const naPromenaStanjeIda = (e) => {
      const value = e.target.value;
      setIzmenjenZadatak({...izmenjenZadatak, stanjeId: value})
    }

    const rednerAll2 = () => {
        return sprintevi.map((sprint) => {
        return(<option key={sprint.id} value={sprint.id}>{sprint.ime}</option>)
    })

    }

    const rednerAll3 = () => {
        return stanja.map((stanje) => {
        return(<option key={stanje.id} value={stanje.id}>{stanje.ime}</option>)
        })

    }

    return (
        <div>
            <Col>
          <br/><br/>
          <Row><h1>Izmeni zadatak</h1></Row>
          <br/>
          <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
              <Form>
                <Form.Label htmlFor="ime">Ime</Form.Label>
                <Form.Control id="ime" type="text" name="ime" value={izmenjenZadatak.ime} onChange={(e) => naPromenaImena(e)}/>
                <Form.Label htmlFor="zaduzeni">Zaduzeni</Form.Label>
                <Form.Control id="zaduzeni" name="zaduzeni" type="text" value={izmenjenZadatak.zaduzeni} onChange={(e) => naPromenaZaduzenog(e)}/>
                <Form.Label htmlFor="bodovi">Bodovi</Form.Label>
                <Form.Control id="bodovi" name="bodovi" type="number" value={izmenjenZadatak.bodovi} onChange={(e) => naPromenaBodova(e)}/>
                <Form.Label htmlFor="sprintId">Sprint</Form.Label>
                <Form.Select name='sprintId' onChange={(e) => naPromenaSprintIda(e)}>
                        <option value={""}>--izaberi sprint--</option>
                        {rednerAll2()}
                    </Form.Select>
                <Form.Label htmlFor="stanjeId">Stanje</Form.Label>
                <Form.Select name='stanjeId' onChange={(e) => naPromenaStanjeIda(e)}>
                        <option value={""}>--izaberi stanje--</option>
                        {rednerAll3()}
                </Form.Select>
                <br/>
                <Button className="btn btn-primary" onClick={() => edit()}>Izmeni</Button>
              </Form>
            </Col>
            <Col></Col>
          </Row>
        </Col>
        </div>
    );

}

export default IzmenaZadatka;